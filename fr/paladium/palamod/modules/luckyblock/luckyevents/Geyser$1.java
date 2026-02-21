package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

class null implements Runnable {
  public void run() {
    try {
      for (int oy = 0; oy < 100; oy++) {
        if (player.field_70170_p.func_147437_c(x, y + oy / 5, z)) {
          Thread.sleep(5L);
          try {
            Player pl = Bukkit.getPlayer(player.func_110124_au());
            if (pl != null && pl.isOnline()) {
              World bukkitWorld = pl.getWorld();
              Location location = new Location(bukkitWorld, x, (y + oy / 5), z);
              pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            } 
          } catch (Exception e) {
            player.func_70634_a(x, (y + oy / 5), z);
          } 
          EventUtils.spawnParticle(player.field_70170_p, "splash", x, (y + oy / 5), z, 50, 0.0D);
        } 
      } 
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Geyser$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */