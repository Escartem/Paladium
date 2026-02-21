package fr.paladium.palaforgeutils.lib.console;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

@SideOnly(Side.SERVER)
public class ConsoleUtils {
  @SideOnly(Side.SERVER)
  public static void executeCommand(String command) {
    try {
      ConsoleCommandSender consoleCommandSender = Bukkit.getConsoleSender();
      if (!Bukkit.dispatchCommand((CommandSender)consoleCommandSender, command))
        MinecraftServer.func_71276_C().func_71187_D().func_71556_a((ICommandSender)MinecraftServer.func_71276_C(), command); 
    } catch (NoClassDefFoundError|Exception e) {
      MinecraftServer.func_71276_C().func_71187_D().func_71556_a((ICommandSender)MinecraftServer.func_71276_C(), command);
    } 
  }
  
  @SideOnly(Side.SERVER)
  public static void sendConsoleMessage(String message) {
    try {
      ConsoleCommandSender consoleCommandSender = Bukkit.getConsoleSender();
      consoleCommandSender.sendMessage(message);
    } catch (NoClassDefFoundError|Exception e) {
      MinecraftServer.func_71276_C().func_145747_a((IChatComponent)new ChatComponentText(message));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\console\ConsoleUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */