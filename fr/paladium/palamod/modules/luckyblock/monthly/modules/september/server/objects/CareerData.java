package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.CareerEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.structures.CareerStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CareerData {
  public static final int MAX_WAVE = 4;
  
  private UUID playerUniqueId;
  
  private UUID entityUniqueId;
  
  private Location spawnLocation;
  
  private int careerLevel;
  
  private boolean started;
  
  private int breakCount;
  
  private int breakObjective;
  
  private long expirationMillis;
  
  private CareerStructure structure;
  
  private boolean gived;
  
  public void setPlayerUniqueId(UUID playerUniqueId) {
    this.playerUniqueId = playerUniqueId;
  }
  
  public void setEntityUniqueId(UUID entityUniqueId) {
    this.entityUniqueId = entityUniqueId;
  }
  
  public void setSpawnLocation(Location spawnLocation) {
    this.spawnLocation = spawnLocation;
  }
  
  public void setCareerLevel(int careerLevel) {
    this.careerLevel = careerLevel;
  }
  
  public void setStarted(boolean started) {
    this.started = started;
  }
  
  public void setBreakCount(int breakCount) {
    this.breakCount = breakCount;
  }
  
  public void setBreakObjective(int breakObjective) {
    this.breakObjective = breakObjective;
  }
  
  public void setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
  }
  
  public void setStructure(CareerStructure structure) {
    this.structure = structure;
  }
  
  public void setGived(boolean gived) {
    this.gived = gived;
  }
  
  public UUID getPlayerUniqueId() {
    return this.playerUniqueId;
  }
  
  public UUID getEntityUniqueId() {
    return this.entityUniqueId;
  }
  
  public Location getSpawnLocation() {
    return this.spawnLocation;
  }
  
  public int getCareerLevel() {
    return this.careerLevel;
  }
  
  public boolean isStarted() {
    return this.started;
  }
  
  public int getBreakCount() {
    return this.breakCount;
  }
  
  public int getBreakObjective() {
    return this.breakObjective;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public CareerStructure getStructure() {
    return this.structure;
  }
  
  public boolean isGived() {
    return this.gived;
  }
  
  public CareerData(EntityPlayerMP player, Location spawnLocation) {
    this.playerUniqueId = player.func_110124_au();
    this.spawnLocation = spawnLocation;
    this.entityUniqueId = null;
    this.careerLevel = 0;
    this.breakCount = 0;
    this.breakObjective = 0;
    this.started = false;
    this.gived = false;
    this.expirationMillis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30L);
    spawnEntity(player, player.field_70170_p);
  }
  
  public void incrementBreakCount(EntityPlayerMP player) {
    this.breakCount++;
    if (this.breakCount >= this.breakObjective) {
      this.gived = false;
      stopWave(player);
    } 
  }
  
  public void spawnEntity(EntityPlayerMP player, World world) {
    EntityNPC entity = new EntityNPC(world, "Carrière", "palamod:textures/entity/npc/career.png", this.spawnLocation.getBlockX(), (this.spawnLocation.getBlockY() - 2), this.spawnLocation.getBlockZ(), CareerEvent.DURATION, CareerEvent.DURATION, true);
    world.func_72838_d((Entity)entity);
    this.entityUniqueId = entity.func_110124_au();
    TeleportUtils.teleport((EntityPlayer)player, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
  }
  
  public void stopWave(EntityPlayerMP player) {
    this.started = false;
    this.breakCount = 0;
    this.breakObjective = 0;
    this.expirationMillis = 0L;
    spawnEntity(player, player.field_70170_p);
    respawnStructure();
  }
  
  public void giveReward(EntityPlayerMP player) {
    removeStructure();
    this.gived = true;
    switch (this.careerLevel) {
      case 1:
        ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.BLOCK_AMETHYST, 5));
        break;
      case 2:
        ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.BLOCK_TITANE, 5));
        break;
      case 3:
        ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.BLOCK_PALADIUM, 5));
        break;
      case 4:
        ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.BLOCK_GPALADIUM, 5));
        break;
    } 
  }
  
  public void startWave(EntityPlayerMP player, int breakObjective) {
    this.careerLevel++;
    this.started = true;
    this.breakCount = 0;
    this.breakObjective = breakObjective;
    this.expirationMillis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30L);
    if (this.careerLevel == 1) {
      spawnStructure(player);
    } else {
      respawnStructure();
    } 
  }
  
  public boolean isExpired(long now) {
    return (this.expirationMillis <= now);
  }
  
  public void removeStructure() {
    if (this.structure != null)
      this.structure.setExpirationMillis(0L); 
  }
  
  public void removeEntity(World world) {
    if (this.entityUniqueId == null)
      return; 
    EntityLivingBase entityLivingBase = MonthlyUtils.getLivingEntityByUniqueId(world, this.entityUniqueId);
    if (entityLivingBase == null || ((Entity)entityLivingBase).field_70128_L)
      return; 
    entityLivingBase.func_70106_y();
  }
  
  public void spawnStructure(EntityPlayerMP player) {
    if (this.structure == null || this.structure.isExpired())
      this.structure = new CareerStructure((EntityPlayer)player, this.spawnLocation, this); 
    if (!this.structure.canSpawn()) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§cImpossible de générer la structure." });
      return;
    } 
    this.structure.spawn();
  }
  
  public void respawnStructure() {
    if (this.structure == null)
      return; 
    this.structure.setExpirationMillis(this.expirationMillis);
    this.structure.cleanArray();
    this.structure.spawn();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\server\objects\CareerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */