package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.data;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.DoubleCuboid;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BalancingData {
  private static final String WIN_MESSAGE = "§aFélicitations, tu as réussi à atteindre l'autre block de bedrock sans tomber !";
  
  private static final String LOSE_MESSAGE = "§cTu as perdu, tu n'as pas réussi à atteindre l'autre block de bedrock sans tomber !";
  
  private final DoubleLocation spawnLocation;
  
  private final UUID playerUniqueId;
  
  private final List<DoubleLocation> bedrockLocations;
  
  private final DoubleCuboid cuboid;
  
  private final TimedSchematic timedSchematic;
  
  public DoubleLocation getSpawnLocation() {
    return this.spawnLocation;
  }
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public List<DoubleLocation> getBedrockLocations() {
    return this.bedrockLocations;
  }
  
  public DoubleCuboid getCuboid() {
    return this.cuboid;
  }
  
  public TimedSchematic getTimedSchematic() {
    return this.timedSchematic;
  }
  
  public BalancingData(EntityPlayerMP player, TimedSchematic timedSchematic, DoubleCuboid cuboid, List<DoubleLocation> woodLocations) {
    this.playerUniqueId = player.func_110124_au();
    this.bedrockLocations = new ArrayList<>(woodLocations);
    this.timedSchematic = timedSchematic;
    this.cuboid = cuboid;
    this.spawnLocation = new DoubleLocation((Entity)player);
  }
  
  public void removeClosestBedrock(double x, double y, double z) {
    DoubleLocation playerLocation = new DoubleLocation(x, y, z);
    this.bedrockLocations
      .stream()
      .min(Comparator.comparingDouble(loc -> loc.distance(playerLocation)))
      .ifPresent(this.bedrockLocations::remove);
  }
  
  private void win(EntityPlayerMP player) {
    if (player == null)
      return; 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aFélicitations, tu as réussi à atteindre l'autre block de bedrock sans tomber !" });
    MonthlyUtils.teleport((EntityPlayer)player, this.spawnLocation.getX(), this.spawnLocation.getY(), this.spawnLocation.getZ());
  }
  
  private void lose(EntityPlayerMP player) {
    if (player == null)
      return; 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cTu as perdu, tu n'as pas réussi à atteindre l'autre block de bedrock sans tomber !" });
    MonthlyUtils.playSound(player, "soft_fail");
    MonthlyUtils.teleport((EntityPlayer)player, this.spawnLocation.getX(), this.spawnLocation.getY(), this.spawnLocation.getZ());
  }
  
  private double getDistanceFromBedrock(EntityPlayerMP player) {
    DoubleLocation playerLocation = new DoubleLocation((Entity)player);
    return this.bedrockLocations
      .stream()
      .mapToDouble(loc -> loc.distance(playerLocation))
      .min()
      .orElse(Double.MAX_VALUE);
  }
  
  public boolean run(EntityPlayerMP player) {
    if (player == null) {
      removeSchematic();
      return true;
    } 
    player.field_70143_R = 0.0F;
    long now = System.currentTimeMillis();
    if (getDistanceFromBedrock(player) <= 1.35D) {
      removeSchematic();
      win(player);
      return true;
    } 
    if (player.field_70163_u <= 180.0D) {
      removeSchematic();
      lose(player);
      return true;
    } 
    return false;
  }
  
  public void removeSchematic() {
    if (this.timedSchematic != null)
      this.timedSchematic.expire(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\luckyevents\data\BalancingData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */