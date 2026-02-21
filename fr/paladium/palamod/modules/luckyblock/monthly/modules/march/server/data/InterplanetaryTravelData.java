package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.data;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.DoubleCuboid;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palaschematicmod.common.pojo.schematic.timed.TimedSchematic;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class InterplanetaryTravelData {
  private static final long TELEPORTATION_DELAY = TimeUnit.SECONDS.toMillis(10L);
  
  private static final int TELEPORTATION_COUNT = 5;
  
  private static final String TELEPORTATION_MESSAGE = "§eVous avez été téléporté sur un autre emplacement §7(§e%s§7/§6%s§7)";
  
  private static final String END_MESSAGE = "§eVous avez terminé votre voyage interplanétaire !";
  
  private final UUID playerUniqueId;
  
  private final TimedSchematic schematic;
  
  private final DoubleLocation playerStartLocation;
  
  private final DoubleLocation structureLocation;
  
  private final DoubleCuboid cuboid;
  
  private int teleportationCount;
  
  private long lastTeleportationMillis;
  
  private boolean manualExpire;
  
  private List<DoubleLocation> randomLocations;
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public TimedSchematic getSchematic() {
    return this.schematic;
  }
  
  public DoubleLocation getPlayerStartLocation() {
    return this.playerStartLocation;
  }
  
  public DoubleLocation getStructureLocation() {
    return this.structureLocation;
  }
  
  public DoubleCuboid getCuboid() {
    return this.cuboid;
  }
  
  public int getTeleportationCount() {
    return this.teleportationCount;
  }
  
  public long getLastTeleportationMillis() {
    return this.lastTeleportationMillis;
  }
  
  public boolean isManualExpire() {
    return this.manualExpire;
  }
  
  public List<DoubleLocation> getRandomLocations() {
    return this.randomLocations;
  }
  
  public InterplanetaryTravelData(EntityPlayerMP player, TimedSchematic schematic, DoubleCuboid cuboid, DoubleLocation defaultLocation, DoubleLocation teleportLocation, List<DoubleLocation> randomLocations) {
    this.playerUniqueId = player.func_110124_au();
    this.schematic = schematic;
    this.playerStartLocation = defaultLocation;
    this.structureLocation = teleportLocation;
    this.teleportationCount = 0;
    this.cuboid = cuboid;
    this.lastTeleportationMillis = System.currentTimeMillis();
    this.manualExpire = false;
    this.randomLocations = randomLocations;
  }
  
  public void teleport(long now, EntityPlayerMP player) {
    if (now - this.lastTeleportationMillis < TELEPORTATION_DELAY)
      return; 
    this.lastTeleportationMillis = now;
    this.teleportationCount++;
    boolean expired = isExpired();
    if (player != null) {
      getRandomTeleportationLocation().teleportServer((EntityPlayer)player);
      if (!expired)
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("§eVous avez été téléporté sur un autre emplacement §7(§e%s§7/§6%s§7)", new Object[] { Integer.valueOf(this.teleportationCount), Integer.valueOf(4) }) }); 
    } 
    if (expired) {
      expire(player);
      this.manualExpire = true;
    } 
  }
  
  public boolean isExpired() {
    return (this.teleportationCount >= 5 || this.manualExpire);
  }
  
  public void expire(EntityPlayerMP player) {
    if (this.schematic != null)
      this.schematic.expire(); 
    if (player == null)
      return; 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§eVous avez terminé votre voyage interplanétaire !" });
    this.playerStartLocation.teleportServer((EntityPlayer)player);
  }
  
  public DoubleLocation getRandomTeleportationLocation() {
    if (this.randomLocations.isEmpty())
      return this.structureLocation; 
    Random random = new Random();
    return this.randomLocations.get(random.nextInt(this.randomLocations.size()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\server\data\InterplanetaryTravelData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */