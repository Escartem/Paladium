package fr.paladium.palaforgeutils.lib.teleport;

import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class TeleportUtils {
  public static void teleport(EntityPlayer player, EntityPlayer target) {
    try {
      Player bukkitPlayer = Bukkit.getPlayer(player.func_110124_au());
      Player bukkitTarget = Bukkit.getPlayer(target.func_110124_au());
      bukkitPlayer.teleport((Entity)bukkitTarget);
    } catch (NoClassDefFoundError|Exception silent) {
      player.field_70177_z = target.field_70177_z;
      player.field_70125_A = target.field_70125_A;
      player.func_70634_a(target.field_70165_t, target.field_70163_u, target.field_70161_v);
    } 
  }
  
  public static void teleport(EntityPlayer player, double x, double y, double z) {
    try {
      Player bukkitPlayer = Bukkit.getPlayer(player.func_110124_au());
      bukkitPlayer.teleport(new Location(bukkitPlayer.getWorld(), x, y, z));
    } catch (NoClassDefFoundError|Exception silent) {
      player.func_70634_a(x, y, z);
    } 
  }
  
  public static void teleport(EntityPlayer player, double x, double y, double z, float yaw, float pitch) {
    if (player == null)
      return; 
    try {
      Player bukkitPlayer = Bukkit.getPlayer(player.func_110124_au());
      bukkitPlayer.teleport(new Location(bukkitPlayer.getWorld(), x, y, z, yaw, pitch));
    } catch (NoClassDefFoundError|Exception silent) {
      player.field_70177_z = yaw;
      player.field_70125_A = pitch;
      player.func_70634_a(x, y, z);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\teleport\TeleportUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */