package fr.paladium.palaforgeutils.lib.command.annotated;

import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.command.annotated.exception.CommandConditionException;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.dto.CommandEntry;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.dto.SubCommandEntry;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.StringUtils;
import scala.actors.threadpool.Arrays;

public final class AnnotatedCommand implements ICommand {
  private final CommandEntry command;
  
  public AnnotatedCommand(CommandEntry command) {
    this.command = command;
  }
  
  public CommandEntry getCommand() {
    return this.command;
  }
  
  public String func_71517_b() {
    return this.command.getCommand();
  }
  
  public void func_71515_b(@NonNull ICommandSender sender, @NonNull String[] args) {
    if (sender == null)
      throw new NullPointerException("sender is marked non-null but is null"); 
    if (args == null)
      throw new NullPointerException("args is marked non-null but is null"); 
    CommandContext context = CommandContext.create(sender, func_71517_b(), args);
    if (this.command.getPermission() != null && !this.command.getPermission().isEmpty() && !context.hasPermission(this.command.getPermission())) {
      context.error("Vous n'avez pas la permission d'executer cette commande.");
      return;
    } 
    if (args.length == 0) {
      SubCommandEntry rootSubCommand = this.command.getRoot();
      if (rootSubCommand != null && rootSubCommand.canExecute(context)) {
        try {
          rootSubCommand.execute(this.command, context);
        } catch (Exception e) {
          if (e instanceof InvocationTargetException && ((InvocationTargetException)e).getTargetException() instanceof CommandConditionException) {
            CommandConditionException condition = (CommandConditionException)((InvocationTargetException)e).getTargetException();
            String error = condition.getMessage().substring(0, 1).toLowerCase() + condition.getMessage().substring(1);
            ChatComponentText component = new ChatComponentText("§8[§6Paladium§8] §cUne erreur est survenue, " + error);
            ChatStyle style = new ChatStyle();
            style.func_150238_a(EnumChatFormatting.RED);
            style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§8» §cCliquez §7pour modifier la commande.")));
            style.func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + context.getFull()));
            component.func_150255_a(style);
            context.send((IChatComponent)component);
            return;
          } 
          context.error("Une erreur est survenue lors de l'execution de la commande.");
          e.printStackTrace();
        } 
        return;
      } 
    } 
    SubCommandEntry bestMatch = null;
    SubCommandEntry.SubCommandScore bestMatchScore = null;
    for (SubCommandEntry subCommand : this.command.getSubCommandList()) {
      if (subCommand.isRoot() || !subCommand.canExecute(context))
        continue; 
      SubCommandEntry.SubCommandScore score = subCommand.score(context);
      if (score.equals(SubCommandEntry.SubCommandScore.ZERO))
        continue; 
      if (bestMatch == null) {
        bestMatch = subCommand;
        bestMatchScore = score;
        continue;
      } 
      float bestMatchPercentage = bestMatchScore.getPercentage();
      float currentPercentage = score.getPercentage();
      if (currentPercentage > bestMatchPercentage || (currentPercentage == bestMatchPercentage && score.getScore() > bestMatchScore.getScore())) {
        bestMatch = subCommand;
        bestMatchScore = score;
        continue;
      } 
      if (currentPercentage == bestMatchPercentage && score.getScore() == bestMatchScore.getScore() && score.getMaxScore() < bestMatchScore.getMaxScore()) {
        bestMatch = subCommand;
        bestMatchScore = score;
        continue;
      } 
      if (currentPercentage == bestMatchPercentage && score.getScore() == bestMatchScore.getScore() && score.getMaxScore() == bestMatchScore.getMaxScore() && (
        subCommand.getParameters()).length > (bestMatch.getParameters()).length) {
        bestMatch = subCommand;
        bestMatchScore = score;
      } 
    } 
    if (bestMatch != null && bestMatchScore.getPercentage() >= 0.5F) {
      if (bestMatch.test(context)) {
        try {
          bestMatch.execute(this.command, context);
        } catch (Exception e) {
          if (e instanceof InvocationTargetException && ((InvocationTargetException)e).getTargetException() instanceof CommandConditionException) {
            CommandConditionException condition = (CommandConditionException)((InvocationTargetException)e).getTargetException();
            String error = condition.getMessage().substring(0, 1).toLowerCase() + condition.getMessage().substring(1);
            ChatComponentText component = new ChatComponentText("§8[§6Paladium§8] §cUne erreur est survenue, " + error);
            ChatStyle chatStyle = new ChatStyle();
            chatStyle.func_150238_a(EnumChatFormatting.RED);
            chatStyle.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§8» §cCliquez §7pour modifier la commande.")));
            chatStyle.func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + context.getFull()));
            component.func_150255_a(chatStyle);
            context.send((IChatComponent)component);
            return;
          } 
          context.error("Une erreur est survenue lors de l'execution de la commande.");
          e.printStackTrace();
        } 
        return;
      } 
      String header = "§8[§c/" + this.command.getCommand() + "§8]";
      String spacer = "§7§m" + StringUtils.repeat("------------------------------------".charAt(0), ("------------------------------------".length() - header.length() - 6) / 2);
      int length = header.length() - 6 + (spacer.length() - 4) * 2 - 1;
      ChatComponentText headerComponent = new ChatComponentText(spacer + header + spacer);
      if (this.command.getDescription() != null && !this.command.getDescription().isEmpty()) {
        ChatStyle chatStyle = new ChatStyle();
        chatStyle.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§7" + StringUtils.capitalize(this.command.getDescription().endsWith(".") ? this.command.getDescription() : (this.command.getDescription() + ".")))));
        chatStyle.func_150238_a(EnumChatFormatting.GRAY);
        headerComponent.func_150255_a(chatStyle);
      } 
      context.breakLine();
      context.send((IChatComponent)headerComponent);
      context.breakLine();
      context.send((new ChatComponentText(" §8» §cLa syntaxe de la commande est incorrecte.")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.RED)));
      bestMatch.help(context);
      context.breakLine();
      ChatComponentText nextComponent = new ChatComponentText(" §8[§c?§8] §7Afficher la page d'aide");
      ChatStyle style = new ChatStyle();
      style.func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)new ChatComponentText("§8» §cCliquez §7pour afficher la page d'aide")));
      style.func_150241_a(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + this.command.getCommand() + " help"));
      style.func_150238_a(EnumChatFormatting.GRAY);
      nextComponent.func_150255_a(style);
      context.send((IChatComponent)nextComponent);
      context.breakLine();
      context.send((IChatComponent)new ChatComponentText("§7§m" + StringUtils.repeat("------------------------------------".charAt(0), length)));
      context.breakLine();
      return;
    } 
    if (!this.command.isHelp()) {
      context.error("Impossible de trouver une commande correspondante.");
      return;
    } 
    this.command.help(context, 0);
  }
  
  public String func_71518_a(ICommandSender sender) {
    return "/" + func_71517_b();
  }
  
  public List<?> func_71514_a() {
    return Arrays.asList((Object[])this.command.getAliases());
  }
  
  public List<?> func_71516_a(ICommandSender sender, String[] args) {
    CommandContext context = CommandContext.create(sender, func_71517_b(), args);
    List<CompletableFuture<List<String>>> futures = new ArrayList<>();
    if (args.length == 0 || (args.length == 1 && args[0].isEmpty())) {
      for (SubCommandEntry subCommand : this.command.getSubCommandList()) {
        if (!subCommand.canExecute(context) || (subCommand.getParameters()).length == 0)
          continue; 
        CompletableFuture<List<String>> future = subCommand.getParameters()[0].getTabComplete(context);
        if (future != null)
          futures.add(future); 
      } 
    } else {
      for (SubCommandEntry subCommand : this.command.getSubCommandList()) {
        if (!subCommand.canExecute(context) || args.length > (subCommand.getParameters()).length)
          continue; 
        boolean valid = true;
        for (int i = 0; i < args.length - 1; i++) {
          String arg = args[i];
          SubCommandEntry.SubCommandParameter subCommandParameter = subCommand.getParameters()[i];
          if (subCommandParameter != null)
            if (!subCommandParameter.test(context, arg)) {
              valid = false;
              break;
            }  
        } 
        if (!valid)
          continue; 
        SubCommandEntry.SubCommandParameter parameter = subCommand.getParameters()[args.length - 1];
        if (parameter == null)
          continue; 
        CompletableFuture<List<String>> future = parameter.getTabComplete(context);
        if (future != null)
          futures.add(future); 
      } 
    } 
    if (!futures.isEmpty())
      CompletableFuture.allOf((CompletableFuture<?>[])futures.<CompletableFuture>toArray(new CompletableFuture[0]))
        .thenApply(v -> (List)futures.stream().map(CompletableFuture::join).filter(()).flatMap(Collection::stream).distinct().collect(Collectors.toList()))
        .thenAccept(options -> sendTabComplete(context, options))
        .exceptionally(ex -> {
            ex.printStackTrace();
            return null;
          }); 
    return null;
  }
  
  private void sendTabComplete(@NonNull CommandContext context, List<String> options) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    String[] args = context.getArgs();
    if (options == null || options.isEmpty() || args == null || args.length == 0 || !context.isPlayer())
      return; 
    List<String> filteredOptions = null;
    String last = args[args.length - 1];
    if (last.isEmpty()) {
      filteredOptions = (List<String>)options.stream().collect(Collectors.toList());
    } else {
      filteredOptions = (List<String>)options.stream().filter(s -> s.regionMatches(true, 0, last, 0, last.length())).collect(Collectors.toList());
    } 
    if (filteredOptions.isEmpty())
      return; 
    try {
      String[] tabComplete = filteredOptions.<String>toArray(new String[0]);
      FMLServerScheduler.getInstance().add(new Runnable[] { () -> (context.getPlayer()).field_71135_a.func_147359_a((Packet)new S3APacketTabComplete(tabComplete)) });
    } catch (Exception exception) {}
  }
  
  public boolean func_71519_b(ICommandSender sender) {
    return true;
  }
  
  public boolean func_82358_a(String[] args, int index) {
    return false;
  }
  
  public int compareTo(Object o) {
    return func_71517_b().compareTo(((ICommand)o).func_71517_b());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\AnnotatedCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */