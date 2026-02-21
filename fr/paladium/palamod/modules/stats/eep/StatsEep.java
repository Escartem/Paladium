package fr.paladium.palamod.modules.stats.eep;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palamod.modules.stats.dto.PlayerStats;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class StatsEep extends ExtendedEntityProperties {
  public static final String PROP_NAME = "stats_eep";
  
  private PlayerStats stats;
  
  public PlayerStats getStats() {
    return this.stats;
  }
  
  public static StatsEep get(Entity entity) {
    return (StatsEep)entity.getExtendedProperties("stats_eep");
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    initStats();
    compound.func_74782_a("stats_eep", (NBTBase)this.stats.toNBT());
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (!compound.func_74764_b("stats_eep")) {
      initStats();
      return;
    } 
    NBTTagCompound tag = compound.func_74775_l("stats_eep");
    this.stats = new PlayerStats(tag);
  }
  
  private void initStats() {
    if (this.stats == null)
      this.stats = new PlayerStats(); 
  }
  
  public void increaseWalkDistance(int distance) {
    initStats();
    this.stats.setWalkDistance(this.stats.getWalkDistance() + distance);
  }
  
  public void increaseHangGliderDistance(int distance) {
    initStats();
    this.stats.setHangGliderDistance(this.stats.getHangGliderDistance() + distance);
  }
  
  public void increaseBoatDistance(int distance) {
    initStats();
    this.stats.setBoatDistance(this.stats.getBoatDistance() + distance);
  }
  
  public void increaseMountDistance(int distance) {
    initStats();
    this.stats.setMountDistance(this.stats.getMountDistance() + distance);
  }
  
  public void increaseWitherSpawned() {
    initStats();
    this.stats.setWitherSpawned(this.stats.getWitherSpawned() + 1L);
  }
  
  public void increaseWitherKilled() {
    initStats();
    this.stats.setWitherKilled(this.stats.getWitherKilled() + 1L);
  }
  
  public void increaseDynamiteUsed() {
    initStats();
    this.stats.setDynamiteUsed(this.stats.getDynamiteUsed() + 1L);
  }
  
  public void increaseUnclaimFinderUse() {
    initStats();
    this.stats.setUnclaimFinderUse(this.stats.getUnclaimFinderUse() + 1L);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\stats\eep\StatsEep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */