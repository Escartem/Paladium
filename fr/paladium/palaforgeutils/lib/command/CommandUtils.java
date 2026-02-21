package fr.paladium.palaforgeutils.lib.command;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class CommandUtils {
  @SideOnly(Side.SERVER)
  public static boolean performCommands(EntityPlayer player, String... commands) {
    try {
      ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
      for (String command : commands)
        Bukkit.dispatchCommand((CommandSender)console, command.replace("{username}", player.func_70005_c_())); 
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\CommandUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */