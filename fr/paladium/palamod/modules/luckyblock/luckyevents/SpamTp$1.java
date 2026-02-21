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
    for (int i = 0; i < 4; i++) {
      try {
        Thread.sleep(25000L);
        EventUtils.spawnParticle(player.field_70170_p, "smoke", (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 200, 0.0D);
        try {
          Player pl = Bukkit.getPlayer(player.func_110124_au());
          if (pl != null && pl.isOnline()) {
            World bukkitWorld = pl.getWorld();
            Location location = new Location(bukkitWorld, player.field_70165_t, (player.field_70170_p.func_72976_f((int)player.field_70165_t, (int)player.field_70161_v) + 201), player.field_70161_v);
            pl.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
          } 
        } catch (Exception e) {
          player.func_70634_a(player.field_70165_t, (player.field_70170_p
              .func_72976_f((int)player.field_70165_t, (int)player.field_70161_v) + 201), player.field_70161_v);
        } 
      } catch (Exception e) {
        e.printStackTrace();
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\SpamTp$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */