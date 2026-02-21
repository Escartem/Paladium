package fr.paladium.palamod.modules.stats.dto;

import com.allatori.annotations.ControlFlowObfuscation;
import net.minecraft.nbt.NBTTagCompound;

@ControlFlowObfuscation("disable")
public class PlayerStats {
  private long walkDistance;
  
  private long hangGliderDistance;
  
  private long boatDistance;
  
  private long mountDistance;
  
  private long witherSpawned;
  
  private long witherKilled;
  
  private long dynamiteUsed;
  
  private long unclaimFinderUse;
  
  public void setWalkDistance(long walkDistance) {
    this.walkDistance = walkDistance;
  }
  
  public void setHangGliderDistance(long hangGliderDistance) {
    this.hangGliderDistance = hangGliderDistance;
  }
  
  public void setBoatDistance(long boatDistance) {
    this.boatDistance = boatDistance;
  }
  
  public void setMountDistance(long mountDistance) {
    this.mountDistance = mountDistance;
  }
  
  public void setWitherSpawned(long witherSpawned) {
    this.witherSpawned = witherSpawned;
  }
  
  public void setWitherKilled(long witherKilled) {
    this.witherKilled = witherKilled;
  }
  
  public void setDynamiteUsed(long dynamiteUsed) {
    this.dynamiteUsed = dynamiteUsed;
  }
  
  public void setUnclaimFinderUse(long unclaimFinderUse) {
    this.unclaimFinderUse = unclaimFinderUse;
  }
  
  public PlayerStats() {}
  
  public PlayerStats(long walkDistance, long hangGliderDistance, long boatDistance, long mountDistance, long witherSpawned, long witherKilled, long dynamiteUsed, long unclaimFinderUse) {
    this.walkDistance = walkDistance;
    this.hangGliderDistance = hangGliderDistance;
    this.boatDistance = boatDistance;
    this.mountDistance = mountDistance;
    this.witherSpawned = witherSpawned;
    this.witherKilled = witherKilled;
    this.dynamiteUsed = dynamiteUsed;
    this.unclaimFinderUse = unclaimFinderUse;
  }
  
  public long getWalkDistance() {
    return this.walkDistance;
  }
  
  public long getHangGliderDistance() {
    return this.hangGliderDistance;
  }
  
  public long getBoatDistance() {
    return this.boatDistance;
  }
  
  public long getMountDistance() {
    return this.mountDistance;
  }
  
  public long getWitherSpawned() {
    return this.witherSpawned;
  }
  
  public long getWitherKilled() {
    return this.witherKilled;
  }
  
  public long getDynamiteUsed() {
    return this.dynamiteUsed;
  }
  
  public long getUnclaimFinderUse() {
    return this.unclaimFinderUse;
  }
  
  public PlayerStats(NBTTagCompound tag) {
    this.walkDistance = tag.func_74763_f("walkDistance");
    this.hangGliderDistance = tag.func_74763_f("hangGliderDistance");
    this.boatDistance = tag.func_74763_f("boatDistance");
    this.mountDistance = tag.func_74763_f("mountDistance");
    this.witherSpawned = tag.func_74763_f("witherSpawned");
    this.witherKilled = tag.func_74763_f("witherKilled");
    this.dynamiteUsed = tag.func_74763_f("dynamiteUsed");
    this.unclaimFinderUse = tag.func_74763_f("unclaimFinderUse");
  }
  
  public NBTTagCompound toNBT() {
    NBTTagCompound tag = new NBTTagCompound();
    tag.func_74772_a("walkDistance", this.walkDistance);
    tag.func_74772_a("hangGliderDistance", this.hangGliderDistance);
    tag.func_74772_a("boatDistance", this.boatDistance);
    tag.func_74772_a("mountDistance", this.mountDistance);
    tag.func_74772_a("witherSpawned", this.witherSpawned);
    tag.func_74772_a("witherKilled", this.witherKilled);
    tag.func_74772_a("dynamiteUsed", this.dynamiteUsed);
    tag.func_74772_a("unclaimFinderUse", this.unclaimFinderUse);
    return tag;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\stats\dto\PlayerStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */