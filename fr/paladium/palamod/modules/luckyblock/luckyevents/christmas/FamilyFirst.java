package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class FamilyFirst extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    try {
      Player pl = Bukkit.getPlayer(player.func_110124_au());
      if (pl == null || !pl.isOnline())
        return; 
      World bukkitWorld = pl.getWorld();
      Location spawnLocation = new Location(bukkitWorld, PLuckyBlock.familyFirstConfig.getX(), PLuckyBlock.familyFirstConfig.getY(), PLuckyBlock.familyFirstConfig.getZ());
      pl.teleport(spawnLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
    } catch (Exception e) {
      player.func_70634_a(PLuckyBlock.familyFirstConfig.getX(), PLuckyBlock.familyFirstConfig
          .getY(), PLuckyBlock.familyFirstConfig.getZ());
    } 
  }
  
  public String getName() {
    return "Family First";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 120;
  }
  
  public String getTexture() {
    return "family_first";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\FamilyFirst.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */