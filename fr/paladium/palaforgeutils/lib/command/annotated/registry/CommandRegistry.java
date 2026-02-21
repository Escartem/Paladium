package fr.paladium.palaforgeutils.lib.command.annotated.registry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.command.annotated.AnnotatedCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.CommandParser;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.dto.CommandEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommand;
import net.minecraft.server.MinecraftServer;

@SideOnly(Side.SERVER)
public final class CommandRegistry {
  private static final List<Class<?>> registering = new ArrayList<>();
  
  private static final Map<String, CommandEntry> commands = new HashMap<>();
  
  private static boolean started = false;
  
  public static void register() {
    started = true;
    for (Class<?> clazz : registering)
      registerCommand(clazz); 
    registering.clear();
  }
  
  public static void register(Class<?>... classes) {
    for (Class<?> clazz : classes)
      registerCommand(clazz); 
  }
  
  public static void register(Object... objects) {
    for (Object object : objects)
      registerCommand(object.getClass()); 
  }
  
  private static void registerCommand(Class<?> clazz) {
    CommandEntry command = CommandParser.parseCommand(clazz);
    if (started) {
      ((CommandHandler)MinecraftServer.func_71276_C().func_71187_D()).func_71560_a((ICommand)new AnnotatedCommand(command));
      commands.put(command.getCommand(), command);
      System.out.println("[CommandRegistry] Command " + command.getCommand() + " registered successfully.");
      return;
    } 
    registering.add(clazz);
  }
  
  public static CommandEntry getCommand(@NonNull String command) {
    if (command == null)
      throw new NullPointerException("command is marked non-null but is null"); 
    return commands.get(command.startsWith("/") ? command.substring(1) : command);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\registry\CommandRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */