package fr.paladium.palaforgeutils.lib.command.annotated.parser;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameter;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommandClass;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.dto.CommandEntry;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.dto.SubCommandEntry;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.dto.TabCompleteEntry;
import fr.paladium.palaforgeutils.lib.itemstack.ItemStackSerializer;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.NonNull;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

@SideOnly(Side.SERVER)
public final class CommandParser {
  public static CommandEntry parseCommand(@NonNull Class<?> clazz) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    if (!clazz.isAnnotationPresent((Class)Command.class))
      throw new IllegalArgumentException("Unable to parse command: " + clazz.getName() + " as it does not have the @Command annotation"); 
    Command command = clazz.<Command>getAnnotation(Command.class);
    String[] aliases = command.command();
    if (aliases.length == 0)
      throw new IllegalArgumentException("Unable to parse command: " + clazz.getName() + " as it does not have any aliases"); 
    if (aliases.length > 0)
      for (int i = 0; i < aliases.length; i++) {
        aliases[i] = aliases[i].startsWith("/") ? aliases[i].substring(1) : aliases[i];
        if (aliases[i].isEmpty())
          throw new IllegalArgumentException("Unable to parse command: " + clazz.getName() + " as an alias is empty"); 
      }  
    String commandName = aliases[0];
    String description = command.description();
    String permission = command.permission();
    SenderType[] sender = command.sender();
    boolean help = command.help();
    if (commandName.isEmpty())
      throw new IllegalArgumentException("Unable to parse command: " + clazz.getName() + " as the command name is empty"); 
    if (sender == null)
      throw new IllegalArgumentException("Unable to parse command: " + clazz.getName() + " as the sender type is null"); 
    try {
      Object instance = clazz.newInstance();
      List<SubCommandEntry> subCommandList = parseSubCommands(clazz, sender, instance);
      if (subCommandList.isEmpty())
        throw new IllegalArgumentException("Unable to parse command: " + clazz.getName() + " as it does not have any sub-commands"); 
      if (help)
        subCommandList.add((new SubCommandEntry(commandName, "affiche l'aide de la commande", permission, new SenderType[] { SenderType.ALL }, true, 2147483647, instance, null, new SubCommandEntry.SubCommandParameter[] { (SubCommandEntry.SubCommandParameter)new SubCommandEntry.StaticSubCommandParameter("help"), (SubCommandEntry.SubCommandParameter)new SubCommandEntry.DynamicSubCommandParameter("[<page>]", "numéro de la page d'aide", "", null, true, false, null, Integer.class) })).callback((ctx, cmd) -> cmd.help(ctx, (ctx.get(1, Integer.class) == null) ? 0 : (((Integer)ctx.get(1, Integer.class)).intValue() - 1)))); 
      return new CommandEntry(instance, commandName, aliases, description, permission, sender, help, (List)subCommandList.stream().sorted(Comparator.comparing(SubCommandEntry::getPriority).reversed()).collect(Collectors.toList()));
    } catch (Exception e) {
      throw new IllegalArgumentException("Unable to parse command: " + clazz.getName(), e);
    } 
  }
  
  public static List<SubCommandEntry> parseSubCommands(Class<?> clazz, @NonNull SenderType[] defaultSenderTypes, Object instance) {
    if (defaultSenderTypes == null)
      throw new NullPointerException("defaultSenderTypes is marked non-null but is null"); 
    List<SubCommandEntry> subCommandList = new ArrayList<>();
    for (Method method : clazz.getMethods()) {
      if (method.isAnnotationPresent((Class)SubCommand.class)) {
        SubCommandEntry subCommand = parseSubCommand(defaultSenderTypes, instance, method);
        if (subCommand != null)
          subCommandList.add(subCommand); 
      } 
    } 
    for (Field field : clazz.getDeclaredFields()) {
      if (field.isAnnotationPresent((Class)SubCommandClass.class))
        try {
          field.setAccessible(true);
          Object fieldInstance = field.get(instance);
          if (fieldInstance == null) {
            fieldInstance = field.getType().newInstance();
            field.set(instance, fieldInstance);
          } 
          subCommandList.addAll(parseSubCommands(field.getType(), defaultSenderTypes, fieldInstance));
        } catch (Exception e) {
          e.printStackTrace();
        }  
    } 
    return subCommandList;
  }
  
  public static SubCommandEntry parseSubCommand(@NonNull SenderType[] defaultSenderTypes, Object instance, @NonNull Method method) {
    if (defaultSenderTypes == null)
      throw new NullPointerException("defaultSenderTypes is marked non-null but is null"); 
    if (method == null)
      throw new NullPointerException("method is marked non-null but is null"); 
    if (!method.isAnnotationPresent((Class)SubCommand.class))
      throw new IllegalArgumentException("Unable to parse sub-command: " + method.getName() + " as it does not have the @SubCommand annotation"); 
    method.setAccessible(true);
    SubCommand command = method.<SubCommand>getAnnotation(SubCommand.class);
    List<String> arguments = Arrays.asList(command.command().split(" "));
    String commandName = ((String)arguments.get(0)).startsWith("/") ? ((String)arguments.get(0)).substring(1) : arguments.get(0);
    String description = command.description();
    String permission = command.permission();
    SenderType[] sender = ((command.sender()).length == 0 || command.sender()[0] == SenderType.NONE) ? defaultSenderTypes : command.sender();
    boolean help = command.help();
    int priority = command.priority();
    if (commandName.isEmpty())
      throw new IllegalArgumentException("Unable to parse sub-command: " + method.getName() + " as the command name is empty"); 
    if ("help".equalsIgnoreCase(commandName))
      throw new IllegalArgumentException("Unable to parse sub-command: " + method.getName() + " as the command name is reserved"); 
    if (sender == null)
      throw new IllegalArgumentException("Unable to parse sub-command: " + method.getName() + " as the sender type is null"); 
    if (method.getParameterCount() == 0 || !method.getParameterTypes()[0].isAssignableFrom(CommandContext.class))
      throw new IllegalArgumentException("Unable to parse sub-command: " + method.getName() + " as the first parameter is not CommandContext"); 
    int count = 0;
    List<SubCommandEntry.SubCommandParameter> parameters = new ArrayList<>();
    for (int i = 1; i < arguments.size(); i++) {
      String argument = arguments.get(i);
      if ((argument.startsWith("<") && argument.endsWith(">")) || (argument.startsWith("[<") && argument.endsWith(">]"))) {
        Parameter parameter = method.getParameters()[count + 1];
        Class<?> type = method.getParameterTypes()[count + 1];
        if (type == null || type == CommandContext.class)
          throw new IllegalArgumentException("Unable to parse sub-command: " + method.getName() + " as the parameter at index " + i + " is not a valid type"); 
        boolean optional = (argument.startsWith("[<") && argument.endsWith(">]"));
        if ((optional && !type.equals(Optional.class)) || (type.equals(Optional.class) && !optional))
          throw new IllegalArgumentException("Unable to parse sub-command: " + method.getName() + " as an optional argument is not Optional<T>"); 
        if (optional && i != arguments.size() - 1)
          throw new IllegalArgumentException("Unable to parse sub-command: " + method.getName() + " as an optional argument is not the last argument"); 
        if (optional)
          type = (Class)((ParameterizedType)parameter.getParameterizedType()).getActualTypeArguments()[0]; 
        try {
          CommandParameter commandParameter = parameter.<CommandParameter>getAnnotation(CommandParameter.class);
          boolean infinite = ((commandParameter == null || commandParameter.infinite()) && type.equals(String.class) && i == arguments.size() - 1);
          String parameterDescription = (commandParameter == null) ? null : commandParameter.description();
          String parameterError = (commandParameter == null) ? null : commandParameter.error();
          TabCompleteEntry parameterAutocomplete = (commandParameter == null || commandParameter.tabComplete() == null) ? null : new TabCompleteEntry(commandParameter.tabComplete(), instance);
          parameters.add(new SubCommandEntry.DynamicSubCommandParameter(argument, parameterDescription, parameterError, parameterAutocomplete, optional, infinite, parameter, type));
          count++;
        } catch (Exception e) {
          throw new IllegalArgumentException("Unable to parse sub-command: " + method.getName() + " as the parameter at index " + i + " is not a valid argument", e);
        } 
      } else {
        parameters.add(new SubCommandEntry.StaticSubCommandParameter(argument));
      } 
    } 
    if (count != method.getParameterCount() - 1)
      throw new IllegalArgumentException("Unable to parse sub-command: " + method.getName() + " as the number of parameters does not match the number of arguments"); 
    return new SubCommandEntry(commandName, description, permission, sender, help, priority, instance, method, parameters.<SubCommandEntry.SubCommandParameter>toArray(new SubCommandEntry.SubCommandParameter[0]));
  }
  
  public static <T> T parseArgument(CommandContext context, String argument, @NonNull Class<T> type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (argument == null || argument.isEmpty())
      throw new IllegalArgumentException("Unable to parse argument: " + argument + " as it is null or empty"); 
    try {
      if (OfflinePlayer.class.isAssignableFrom(type) || EntityPlayer.class.isAssignableFrom(type)) {
        EntityPlayerMP entityPlayerMP;
        UUID uuid = null;
        EntityPlayer onlinePlayer = null;
        if (UUIDUtils.isUUID(argument))
          try {
            uuid = UUIDUtils.from(argument);
            for (WorldServer worldServer : (MinecraftServer.func_71276_C()).field_71305_c) {
              EntityPlayer entityPlayer = worldServer.func_152378_a(uuid);
              if (entityPlayer != null) {
                onlinePlayer = entityPlayer;
                break;
              } 
            } 
          } catch (Exception exception) {} 
        if (onlinePlayer == null)
          entityPlayerMP = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(argument); 
        if (entityPlayerMP == null && uuid == null)
          entityPlayerMP = PlayerSelector.func_82386_a(context.getSender(), argument); 
        if (EntityPlayer.class.isAssignableFrom(type))
          return type.cast(entityPlayerMP); 
        OfflinePlayer player = null;
        if (entityPlayerMP != null) {
          player = OfflinePlayer.load((EntityPlayer)entityPlayerMP).get();
        } else if (uuid != null) {
          player = OfflinePlayer.load(uuid).get();
        } else {
          player = OfflinePlayer.load(argument).get();
        } 
        if (player == null)
          throw new IllegalArgumentException("Unable to parse argument: " + argument + " as it is not a valid player"); 
        return type.cast(player);
      } 
      if (UUID.class.isAssignableFrom(type))
        try {
          return type.cast(UUIDUtils.from(argument));
        } catch (Exception e) {
          throw new IllegalArgumentException("Unable to parse argument: " + argument + " as it is not a valid UUID");
        }  
      if (World.class.isAssignableFrom(type))
        for (WorldServer worldServer : (MinecraftServer.func_71276_C()).field_71305_c) {
          if (argument.equalsIgnoreCase(worldServer.func_72912_H().func_76065_j()))
            return type.cast(worldServer); 
        }  
      if (ItemStack.class.isAssignableFrom(type))
        try {
          String[] parts = argument.split(":");
          int id = Integer.parseInt(parts[0]);
          int damage = (parts.length > 1) ? Integer.parseInt(parts[1]) : 0;
          ItemStack stack = new ItemStack(Item.func_150899_d(id), 1, damage);
          return type.cast(stack);
        } catch (Exception exception) {
          ItemStack stack = ItemStackSerializer.read(new String(Base64.getDecoder().decode(argument), StandardCharsets.UTF_8), null);
          if (stack != null)
            return type.cast(stack); 
          String[] parts = argument.split(":");
          if (parts.length < 2) {
            stack = GameRegistry.findItemStack("minecraft", parts[0], 1);
          } else {
            String modid = parts[0];
            String name = parts[1];
            stack = GameRegistry.findItemStack(modid, name, 1);
          } 
          if (stack != null)
            return type.cast(stack); 
          throw new IllegalArgumentException("Unable to parse argument: " + argument + " as it is not a valid item");
        }  
      if (type.isEnum())
        try {
          return (T)Enum.valueOf((Class)type, argument.toUpperCase());
        } catch (Exception|NoClassDefFoundError e) {
          try {
            int ordinal = Integer.parseInt(argument);
            return type.getEnumConstants()[ordinal];
          } catch (Exception|NoClassDefFoundError e1) {
            throw new IllegalArgumentException("Unable to parse argument: " + argument + " as it is not a valid enum");
          } 
        }  
      if (Integer.class.isAssignableFrom(type) || int.class.isAssignableFrom(type))
        return (T)Integer.valueOf(argument); 
      if (Double.class.isAssignableFrom(type) || double.class.isAssignableFrom(type))
        return (T)Double.valueOf(argument); 
      if (Float.class.isAssignableFrom(type) || float.class.isAssignableFrom(type))
        return (T)Float.valueOf(argument); 
      if (Long.class.isAssignableFrom(type) || long.class.isAssignableFrom(type))
        return (T)Long.valueOf(argument); 
      if (Short.class.isAssignableFrom(type) || short.class.isAssignableFrom(type))
        return (T)Short.valueOf(argument); 
      if (Byte.class.isAssignableFrom(type) || byte.class.isAssignableFrom(type))
        return (T)Byte.valueOf(argument); 
      if (Boolean.class.isAssignableFrom(type) || boolean.class.isAssignableFrom(type)) {
        if ("yes".equalsIgnoreCase(argument) || "allow".equalsIgnoreCase(argument))
          return (T)Boolean.TRUE; 
        if ("no".equalsIgnoreCase(argument) || "deny".equalsIgnoreCase(argument))
          return (T)Boolean.FALSE; 
        return (T)Boolean.valueOf(argument);
      } 
      return type.cast(argument);
    } catch (Exception e) {
      throw new IllegalArgumentException("Unable to parse argument: " + argument + " as it is not a valid " + type.getSimpleName());
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\parser\CommandParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */