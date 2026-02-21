package fr.paladium.palaspawner.common.utils;

import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class DebugUtils {
  public static boolean DEBUG = false;
  
  public static void broadcastDebugMessage(String... message) {
    if (!DEBUG)
      return; 
    PlayerUtils.getOnlinePlayers().forEach(player -> {
          if (!BukkitUtils.hasPermission((Entity)player, "palaspawner.*"))
            return; 
          ChatUtils.sendColoredMessage((ICommandSender)player, message);
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\commo\\utils\DebugUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */