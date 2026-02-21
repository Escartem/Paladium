package fr.paladium.palaforgeutils.lib.subcommand.utils;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class SubCommandUtils {
  private static final int MIN_PROPOSITIONS = 0;
  
  private static final int MAX_PROPOSITIONS = 10;
  
  public static final String USER_NOT_FOUND_MESSAGE = "§cLe joueur §e%s§c ne semble pas en ligne.";
  
  public static final String ARGUMENT_NOT_FOUND_MESSAGE = "§cL'argument §e%s§c n'existe pas.";
  
  public static final String ENUM_NOT_FOUND_MESSAGE = "§cL'enum §e%s§c n'existe pas.";
  
  public static final String INVALID_NUMBER_MESSAGE = "§cLe nombre §e%s§c n'est pas valide. Il doit être compris entre §e%s§c et §e%s§c.";
  
  public static final String NOT_A_NUMBER_MESSAGE = "§cLe nombre §e%s§c n'est pas valide.";
  
  public static String getSubCommandErrorMessage(String value, ASubCommand command) {
    SubCommandBuilder builder;
    SubCommandType type = command.getBuilder().getType();
    if (command.hasSubCommand() && command.getSubCommands().size() == 1) {
      type = ((ASubCommand)command.getSubCommands().get(0)).getBuilder().getType();
    } else {
      return String.format("§cL'argument §e%s§c n'existe pas.", new Object[] { value });
    } 
    switch (type) {
      case ENUM:
        return String.format("§cL'enum §e%s§c n'existe pas.", new Object[] { value });
      case NUMBER:
        builder = command.getBuilder();
        if (builder.getMin().doubleValue() == builder.getMax().doubleValue())
          return String.format("§cLe nombre §e%s§c n'est pas valide.", new Object[] { value }); 
        return String.format("§cLe nombre §e%s§c n'est pas valide. Il doit être compris entre §e%s§c et §e%s§c.", new Object[] { value, builder.getMin(), builder.getMax() });
      case PLAYER:
        return String.format("§cLe joueur §e%s§c ne semble pas en ligne.", new Object[] { value });
    } 
    return String.format("§cL'argument §e%s§c n'existe pas.", new Object[] { value });
  }
  
  public static ASubCommand getSubCommand(ASubCommand sub, String[] args) {
    if (args.length == 0)
      return sub; 
    String arg = args[0];
    List<ASubCommand> subCommands = sub.getSubCommands();
    for (ASubCommand subCommand : subCommands) {
      if (subCommand.getBuilder().getName().equalsIgnoreCase(arg))
        return getSubCommand(subCommand, Arrays.<String>copyOfRange(args, 1, args.length)); 
    } 
    return sub;
  }
  
  public static List<ASubCommand> extractSubCommands(ASubCommand sub) {
    return extractSubCommands(null, sub);
  }
  
  public static List<ASubCommand> extractSubCommands(ICommandSender sender, ASubCommand sub) {
    if (!sub.hasPermission(sender))
      return new ArrayList<>(); 
    List<ASubCommand> subCommands = new ArrayList<>();
    subCommands.add(sub);
    sub.getSubCommands().forEach(subCommand -> {
          if (!subCommand.getBuilder().isFirstNode())
            subCommands.addAll(extractSubCommands(sender, subCommand)); 
        });
    return subCommands;
  }
  
  public static String extractPrintedValue(SubCommandBuilder builder) {
    switch (builder.getType()) {
      case ENUM:
        if (builder.getEnumClass() != null && builder.getEnumClass().isEnum()) {
          List<String> values = new ArrayList<>();
          for (Enum<?> e : (Enum[])builder.getEnumClass().getEnumConstants())
            values.add(e.name()); 
          return values.toString();
        } 
        return builder.getName();
      case NUMBER:
        if (builder.getMin().doubleValue() == builder.getMax().doubleValue())
          return "(number)"; 
        return "(" + builder.getMin() + "-" + builder.getMax() + ")";
      case PLAYER:
        return "(player)";
    } 
    return builder.getName();
  }
  
  public static List<String> extractTabulationValues(String arg, List<ASubCommand> subs) {
    List<String> values = new ArrayList<>();
    subs.forEach(sub -> {
          List<String> args;
          List<String> players;
          SubCommandType type = sub.getBuilder().getType();
          switch (type) {
            case STRING:
              values.add(sub.getBuilder().getName());
              break;
            case FREE_ARGUMENT:
              args = sub.getBuilder().getFreeArgs();
              values.addAll(args);
              break;
            case NUMBER:
              values.add(String.valueOf(sub.getBuilder().getMin()));
              values.add(String.valueOf(sub.getBuilder().getMax()));
              break;
            case PLAYER:
              players = Arrays.asList(MinecraftServer.func_71276_C().func_71213_z());
              values.addAll(sortStringList(arg, players).subList(0, Math.min(players.size(), 10)));
              break;
            case ENUM:
              if (sub.getBuilder().getEnumClass() != null && sub.getBuilder().getEnumClass().isEnum()) {
                List<String> enumValues = (List<String>)Arrays.stream(sub.getBuilder().getEnumClass().getEnumConstants()).map(Enum::name).collect(Collectors.toList());
                values.addAll(enumValues);
              } 
              break;
          } 
        });
    return sortStringList(arg, values);
  }
  
  private static List<String> sortStringList(String arg, List<String> list) {
    list.sort((s1, s2) -> {
          boolean b1 = s1.toLowerCase().startsWith(arg.toLowerCase());
          boolean b2 = s2.toLowerCase().startsWith(arg.toLowerCase());
          return (b1 && !b2) ? -1 : (
            
            (!b1 && b2) ? 1 : s1.compareTo(s2));
        });
    return list;
  }
  
  public static String extractSubCommandName(ASubCommand sub) {
    String name = sub.getBuilder().getName();
    if (sub.getParent() != null)
      name = extractSubCommandName(sub.getParent()) + " " + extractPrintedValue(sub.getBuilder()); 
    return name;
  }
  
  public static List<String> buildHelp(ICommandSender sender, ASubCommand sub) {
    List<ASubCommand> subCommands = extractSubCommands(sender, sub);
    List<String> helps = new ArrayList<>();
    subCommands.forEach(subCommand -> {
          if (!subCommand.hasPermission(sender))
            return; 
          if (!subCommand.hasSubCommand() || subCommand.getBuilder().isExecutable())
            helps.add("§7- §c/" + extractSubCommandName(subCommand) + " §7: §e" + subCommand.getBuilder().getDescription()); 
        });
    return helps;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcomman\\utils\SubCommandUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */