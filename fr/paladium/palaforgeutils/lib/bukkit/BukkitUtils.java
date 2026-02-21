package fr.paladium.palaforgeutils.lib.bukkit;

import java.util.UUID;
import net.minecraft.entity.Entity;
import org.bukkit.Bukkit;

public class BukkitUtils {
  public static boolean hasPermission(Entity entity, String permission) {
    return hasPermission(entity.func_110124_au(), permission);
  }
  
  public static boolean hasPermission(UUID uuid, String permission) {
    try {
      return Bukkit.getPlayer(uuid).hasPermission(permission);
    } catch (NoClassDefFoundError|Exception silent) {
      return true;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\bukkit\BukkitUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */