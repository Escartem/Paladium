package fr.paladium.palajobs.utils.forge.server;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class ServerUtils {
  public static void performConsoleCommand(String command) {
    try {
      ConsoleCommandSender consoleCommandSender = Bukkit.getConsoleSender();
      if (!Bukkit.dispatchCommand((CommandSender)consoleCommandSender, command))
        MinecraftServer.func_71276_C().func_71187_D().func_71556_a((ICommandSender)MinecraftServer.func_71276_C(), command); 
    } catch (Exception e) {
      MinecraftServer.func_71276_C().func_71187_D().func_71556_a((ICommandSender)MinecraftServer.func_71276_C(), command);
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajob\\utils\forge\server\ServerUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */