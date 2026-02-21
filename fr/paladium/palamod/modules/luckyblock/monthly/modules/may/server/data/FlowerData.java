package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.server.data;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class FlowerData {
  private static final long DURATION = TimeUnit.SECONDS.toMillis(15L);
  
  private final UUID playerUniqueId;
  
  private final DoubleLocation spawnLocation;
  
  private final long eventExpirationMillis;
  
  private final TimedSchematic schematic;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public DoubleLocation getSpawnLocation() {
    return this.spawnLocation;
  }
  
  public long getEventExpirationMillis() {
    return this.eventExpirationMillis;
  }
  
  public TimedSchematic getSchematic() {
    return this.schematic;
  }
  
  public FlowerData(EntityPlayerMP player, TimedSchematic schematic) {
    this.playerUniqueId = player.func_110124_au();
    this.spawnLocation = new DoubleLocation((Entity)player);
    this.eventExpirationMillis = System.currentTimeMillis() + DURATION;
    this.schematic = schematic;
  }
  
  private void removeSchematic() {
    if (this.schematic == null)
      return; 
    this.schematic.expire();
  }
  
  private void teleportPlayer(EntityPlayerMP player) {
    if (player == null)
      return; 
    this.spawnLocation.teleportServer((EntityPlayer)player);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aTu as été téléporté à ton point de départ !" });
  }
  
  public boolean isExpired(EntityPlayerMP player, long now) {
    if (player == null) {
      removeSchematic();
      return true;
    } 
    if (now >= this.eventExpirationMillis) {
      removeSchematic();
      teleportPlayer(player);
      return true;
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\server\data\FlowerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */