package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.paladium.palamod.modules.luckyblock.utils.UsersManager;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(5000L);
      Location randomPlayerLocation = randomPlayerBukkit.getLocation();
      UsersManager.getInvincibles().add(randomPlayer);
      randomPlayerBukkit.teleport(currentPlayer.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
      for (int i = 0; i < 30; i++) {
        PlayerUtils.sendMessage((EntityPlayer)randomPlayer, (30 - i) + " secondes restantes...");
        Thread.sleep(1000L);
      } 
      randomPlayerBukkit.teleport(randomPlayerLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
    } catch (InterruptedException interruptedException) {}
    UsersManager.getInvincibles().remove(randomPlayer);
    PaladiumScheduler.INSTANCE.cancelTask(task.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\WhoAreYou$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */