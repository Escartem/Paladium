package fr.paladium.palaforgeutils.lib.command.annotated.parser.dto;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.CommandParser;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.CommandValidatorParser;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.validator.Validators;
import fr.paladium.palaforgeutils.lib.validator.exception.ValidatorException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.StringUtils;

public final class SubCommandEntry {
  private final String name;
  
  private final String description;
  
  private final String permission;
  
  private final SenderType[] sender;
  
  private final boolean help;
  
  private final int priority;
  
  private final Object instance;
  
  private final Method method;
  
  private final SubCommandParameter[] parameters;
  
  private BiConsumer<CommandContext, CommandEntry> callback;
  
  public SubCommandEntry(String name, String description, String permission, SenderType[] sender, boolean help, int priority, Object instance, Method method, SubCommandParameter[] parameters) {
    this.name = name;
    this.description = description;
    this.permission = permission;
    this.sender = sender;
    this.help = help;
    this.priority = priority;
    this.instance = instance;
    this.method = method;
    this.parameters = parameters;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getPermission() {
    return this.permission;
  }
  
  public SenderType[] getSender() {
    return this.sender;
  }
  
  public boolean isHelp() {
    return this.help;
  }
  
  public int getPriority() {
    return this.priority;
  }
  
  public Object getInstance() {
    return this.instance;
  }
  
  public Method getMethod() {
    return this.method;
  }
  
  public SubCommandParameter[] getParameters() {
    return this.parameters;
  }
  
  public BiConsumer<CommandContext, CommandEntry> getCallback() {
    return this.callback;
  }
  
  public void execute(@NonNull CommandEntry command, @NonNull CommandContext context) throws Exception {
    if (command == null)
      throw new NullPointerException("command is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (this.parameters.length == 0) {
      if (this.method != null)
        this.method.invoke(this.instance, new Object[] { context }); 
      if (this.callback != null)
        this.callback.accept(context, command); 
      return;
    } 
    if (this.method != null) {
      List<Object> arguments = new ArrayList();
      arguments.add(context);
      for (int i = 0; i < this.parameters.length; i++) {
        SubCommandParameter parameter = this.parameters[i];
        if (!(parameter instanceof StaticSubCommandParameter)) {
          DynamicSubCommandParameter dynamicParameter = (DynamicSubCommandParameter)parameter;
          if (dynamicParameter.isInfinite()) {
            List<String> values = new ArrayList<>();
            for (int j = i; j < context.length(); j++)
              values.add(context.get(j)); 
            String argument = String.join(" ", (Iterable)values);
            arguments.add(dynamicParameter.isOptional() ? ((i < context.size() && !argument.isEmpty()) ? Optional.<String>of(argument) : Optional.empty()) : argument);
          } else {
            Object value = context.get(i, dynamicParameter.getType());
            arguments.add(dynamicParameter.isOptional() ? ((value == null) ? Optional.empty() : Optional.<Object>of(value)) : value);
            if (dynamicParameter.getParameter() != null && (!dynamicParameter.isOptional() || (dynamicParameter.isOptional() && value != null))) {
              ValidatorException exception = Validators.validate(parameter.getName(), Arrays.asList(dynamicParameter.getParameter().getAnnotations()), value, false);
              if (exception != null) {
                String error = (dynamicParameter.getError() != null && !dynamicParameter.getError().isEmpty()) ? dynamicParameter.getError() : CommandValidatorParser.parse(exception);
                if (error == null || error.isEmpty())
                  error = "argument invalide"; 
                if (!error.endsWith("."))
                  error = error + "."; 
                error = error.substring(0, 1).toLowerCase() + error.substring(1);
                ChatComponentText component = new ChatComponentText("§8[§6Paladium§8] §cL'argument §6" + parameter.getName() + "§c est invalide, " + error);
                ChatStyle style = new ChatStyle();
                style.func_150238_a(EnumChatFormatting.RED);
                ChatComponentText hover = new ChatComponentText((dynamicParameter.getDescription() != null && !dynamicParameter.getDescription().isEmpty()) ? ("§7" + StringUtils.capitalize(dynamicParameter.getDescription().endsWith(".") ? dynamicParameter.getDescription() : (dynamicParameter.getDescription() + "."))) : "§8» §cCliquez §7pour modifier la commande.");
                if (dynamicParameter.getDescription() != null && !dynamicParameter.getDescription().isEmpty()) {
                  hover.func_150258_a("\n\n");
                  hover.func_150257_a((IChatComponent)new ChatComponentText("§8» §cCliquez §7pour modifier la commande."));
                } 
                style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)hover));
                style.func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + context.getFull()));
                component.func_150255_a(style);
                context.send((IChatComponent)component);
                return;
              } 
            } 
          } 
        } 
      } 
      this.method.invoke(this.instance, arguments.toArray());
    } 
    if (this.callback != null)
      this.callback.accept(context, command); 
  }
  
  public void help(@NonNull CommandContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    ChatComponentText fullName = new ChatComponentText("§6/" + this.name);
    fullName.func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GOLD));
    for (SubCommandParameter parameter : this.parameters) {
      ChatComponentText component = new ChatComponentText("§6" + parameter.getName());
      component.func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GOLD));
      if (parameter instanceof DynamicSubCommandParameter) {
        DynamicSubCommandParameter dynamicParameter = (DynamicSubCommandParameter)parameter;
        if (dynamicParameter.getDescription() != null && !dynamicParameter.getDescription().isEmpty()) {
          ChatStyle chatStyle = component.func_150256_b();
          chatStyle.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§7" + StringUtils.capitalize(dynamicParameter.description.endsWith(".") ? dynamicParameter.description : (dynamicParameter.description + ".")))));
          component.func_150255_a(chatStyle);
        } 
      } 
      fullName.func_150258_a(" ");
      fullName.func_150257_a((IChatComponent)component);
    } 
    ChatComponentText root = new ChatComponentText(" §8» ");
    root.func_150257_a((IChatComponent)fullName);
    if (this.description != null && !this.description.isEmpty())
      root.func_150257_a((new ChatComponentText(" §7" + StringUtils.capitalize(this.description.endsWith(".") ? this.description : (this.description + ".")))).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GRAY))); 
    ChatStyle style = new ChatStyle();
    style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§8» §cCliquez §7pour compléter la commande.")));
    style.func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, fullName.func_150260_c().replace("§6", "").replace("§7", "")));
    root.func_150255_a(style);
    context.send((IChatComponent)root);
  }
  
  public boolean canExecute(@NonNull CommandContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (this.permission != null && !this.permission.isEmpty() && !context.hasPermission(this.permission))
      return false; 
    for (SenderType sender : this.sender) {
      if (sender.isAllowed(context.getSender()))
        return true; 
    } 
    return false;
  }
  
  public boolean test(@NonNull CommandContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    for (int i = 0; i < this.parameters.length; i++) {
      SubCommandParameter parameter = this.parameters[i];
      if (parameter instanceof StaticSubCommandParameter) {
        if (i >= context.length() || !parameter.test(context, context.get(i)))
          return false; 
      } else {
        DynamicSubCommandParameter dynamicParameter = (DynamicSubCommandParameter)parameter;
        if (!dynamicParameter.isOptional() && i >= context.length())
          return false; 
        if (dynamicParameter.isInfinite()) {
          for (int j = i; j < context.length(); j++) {
            if (!dynamicParameter.test(context, context.get(j)))
              return false; 
          } 
          return true;
        } 
        if (!dynamicParameter.test(context, context.get(i)))
          return false; 
      } 
    } 
    return true;
  }
  
  public SubCommandScore score(@NonNull CommandContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (context.length() > this.parameters.length && !isInfinite())
      return SubCommandScore.ZERO; 
    int score = 0;
    int maxScore = 0;
    for (int i = 0; i < this.parameters.length; i++) {
      SubCommandParameter parameter = this.parameters[i];
      if (parameter instanceof StaticSubCommandParameter) {
        if (i >= context.length()) {
          score += 0;
          maxScore += 3;
        } else {
          if (!parameter.test(context, context.get(i)))
            return SubCommandScore.ZERO; 
          score += 3;
          maxScore += 3;
        } 
      } else {
        DynamicSubCommandParameter dynamicParameter = (DynamicSubCommandParameter)parameter;
        if (dynamicParameter.isInfinite()) {
          for (int j = i; j < context.length(); j++) {
            if (!dynamicParameter.test(context, context.get(j))) {
              score++;
              maxScore += 2;
            } 
          } 
          score += 2;
          maxScore += 2;
          break;
        } 
        if (!dynamicParameter.isOptional() && i >= context.length()) {
          score += 0;
          maxScore += 2;
        } else if (!dynamicParameter.test(context, context.get(i))) {
          score++;
          maxScore += 2;
        } else {
          score += 2;
          maxScore += 2;
        } 
      } 
    } 
    return new SubCommandScore(score, maxScore);
  }
  
  @NonNull
  public SubCommandEntry callback(BiConsumer<CommandContext, CommandEntry> callback) {
    this.callback = callback;
    return this;
  }
  
  public boolean isRoot() {
    return (this.parameters.length == 0 || (this.parameters[0] instanceof DynamicSubCommandParameter && ((DynamicSubCommandParameter)this.parameters[0]).isOptional()));
  }
  
  public boolean isInfinite() {
    return (this.parameters.length > 0 && this.parameters[this.parameters.length - 1] instanceof DynamicSubCommandParameter && ((DynamicSubCommandParameter)this.parameters[this.parameters.length - 1]).isInfinite());
  }
  
  public static final class DynamicSubCommandParameter implements SubCommandParameter {
    private final String name;
    
    private final String description;
    
    private final String error;
    
    private final TabCompleteEntry tabComplete;
    
    private final boolean optional;
    
    private final boolean infinite;
    
    private final Parameter parameter;
    
    private final Class<?> type;
    
    public DynamicSubCommandParameter(String name, String description, String error, TabCompleteEntry tabComplete, boolean optional, boolean infinite, Parameter parameter, Class<?> type) {
      this.name = name;
      this.description = description;
      this.error = error;
      this.tabComplete = tabComplete;
      this.optional = optional;
      this.infinite = infinite;
      this.parameter = parameter;
      this.type = type;
    }
    
    public String getName() {
      return this.name;
    }
    
    public String getDescription() {
      return this.description;
    }
    
    public String getError() {
      return this.error;
    }
    
    public TabCompleteEntry getTabComplete() {
      return this.tabComplete;
    }
    
    public boolean isOptional() {
      return this.optional;
    }
    
    public boolean isInfinite() {
      return this.infinite;
    }
    
    public Parameter getParameter() {
      return this.parameter;
    }
    
    public Class<?> getType() {
      return this.type;
    }
    
    public boolean test(@NonNull CommandContext context, String argument) {
      if (context == null)
        throw new NullPointerException("context is marked non-null but is null"); 
      if (this.optional && (argument == null || argument.isEmpty()))
        return true; 
      Class<?> type = this.infinite ? String.class : this.type;
      try {
        CommandParser.parseArgument(context, argument, type);
      } catch (Exception e) {
        return false;
      } 
      return true;
    }
    
    public CompletableFuture<List<String>> getTabComplete(@NonNull CommandContext context) {
      if (context == null)
        throw new NullPointerException("context is marked non-null but is null"); 
      if (this.tabComplete != null && !this.tabComplete.isEmpty() && context.isPlayer())
        return this.tabComplete.getTabComplete(context); 
      List<String> list = new ArrayList<>();
      if (Number.class.isAssignableFrom(this.type) || this.type == int.class || this.type == double.class || this.type == float.class || this.type == long.class || this.type == short.class || this.type == byte.class) {
        list.add("0");
      } else if (Boolean.class.isAssignableFrom(this.type) || this.type == boolean.class) {
        list.add("true");
        list.add("false");
      } else if (Enum.class.isAssignableFrom(this.type)) {
        Enum[] arrayOfEnum = (Enum[])this.type.getEnumConstants();
        for (Enum<?> constant : arrayOfEnum)
          list.add(constant.name()); 
      } else if ((EntityPlayer.class.isAssignableFrom(this.type) || OfflinePlayer.class.isAssignableFrom(this.type)) && FMLCommonHandler.instance().getSide() == Side.SERVER) {
        list.addAll(Arrays.asList(MinecraftServer.func_71276_C().func_71213_z()));
      } 
      return CompletableFuture.completedFuture(list);
    }
  }
  
  public static final class StaticSubCommandParameter implements SubCommandParameter {
    private final String name;
    
    public StaticSubCommandParameter(String name) {
      this.name = name;
    }
    
    public String getName() {
      return this.name;
    }
    
    public boolean test(@NonNull CommandContext context, @NonNull String argument) {
      if (context == null)
        throw new NullPointerException("context is marked non-null but is null"); 
      if (argument == null)
        throw new NullPointerException("argument is marked non-null but is null"); 
      return this.name.equalsIgnoreCase(argument);
    }
    
    public CompletableFuture<List<String>> getTabComplete(@NonNull CommandContext context) {
      if (context == null)
        throw new NullPointerException("context is marked non-null but is null"); 
      return CompletableFuture.completedFuture(Arrays.asList(new String[] { this.name }));
    }
  }
  
  public static interface SubCommandParameter {
    String getName();
    
    boolean test(@NonNull CommandContext param1CommandContext, @NonNull String param1String);
    
    CompletableFuture<List<String>> getTabComplete(@NonNull CommandContext param1CommandContext);
  }
  
  public static class SubCommandScore implements Comparable<SubCommandScore> {
    public static final SubCommandScore ZERO = new SubCommandScore(0, 0);
    
    private final int score;
    
    private final int maxScore;
    
    private final float percentage;
    
    public int getScore() {
      return this.score;
    }
    
    public int getMaxScore() {
      return this.maxScore;
    }
    
    public float getPercentage() {
      return this.percentage;
    }
    
    public SubCommandScore(int score, int maxScore) {
      this.score = score;
      this.maxScore = maxScore;
      this.percentage = this.score / this.maxScore;
    }
    
    public int compareTo(SubCommandScore o) {
      if (o == null)
        return 1; 
      if (this.percentage == o.percentage) {
        if (this.score == o.score)
          return Integer.compare(this.maxScore, o.maxScore); 
        return Integer.compare(this.score, o.score);
      } 
      return Float.compare(this.percentage, o.percentage);
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(this.maxScore), Integer.valueOf(this.score) });
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (!(obj instanceof SubCommandScore))
        return false; 
      SubCommandScore other = (SubCommandScore)obj;
      return (this.maxScore == other.maxScore && this.score == other.score);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\parser\dto\SubCommandEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */