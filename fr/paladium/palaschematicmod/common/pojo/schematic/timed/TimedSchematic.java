package fr.paladium.palaschematicmod.common.pojo.schematic.timed;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class TimedSchematic {
  private String name;
  
  private long expiration;
  
  private List<TimedBlockData> originalBlocks;
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setExpiration(long expiration) {
    this.expiration = expiration;
  }
  
  public void setOriginalBlocks(List<TimedBlockData> originalBlocks) {
    this.originalBlocks = originalBlocks;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof TimedSchematic))
      return false; 
    TimedSchematic other = (TimedSchematic)o;
    if (!other.canEqual(this))
      return false; 
    if (getExpiration() != other.getExpiration())
      return false; 
    Object this$name = getName(), other$name = other.getName();
    if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name))
      return false; 
    Object<TimedBlockData> this$originalBlocks = (Object<TimedBlockData>)getOriginalBlocks(), other$originalBlocks = (Object<TimedBlockData>)other.getOriginalBlocks();
    return !((this$originalBlocks == null) ? (other$originalBlocks != null) : !this$originalBlocks.equals(other$originalBlocks));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof TimedSchematic;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $expiration = getExpiration();
    result = result * 59 + (int)($expiration >>> 32L ^ $expiration);
    Object $name = getName();
    result = result * 59 + (($name == null) ? 43 : $name.hashCode());
    Object<TimedBlockData> $originalBlocks = (Object<TimedBlockData>)getOriginalBlocks();
    return result * 59 + (($originalBlocks == null) ? 43 : $originalBlocks.hashCode());
  }
  
  public String toString() {
    return "TimedSchematic(name=" + getName() + ", expiration=" + getExpiration() + ", originalBlocks=" + getOriginalBlocks() + ")";
  }
  
  public String getName() {
    return this.name;
  }
  
  public long getExpiration() {
    return this.expiration;
  }
  
  public List<TimedBlockData> getOriginalBlocks() {
    return this.originalBlocks;
  }
  
  public void expire() {
    this.expiration = System.currentTimeMillis();
  }
  
  public void updateExpiration(long millis) {
    this.expiration = System.currentTimeMillis() + millis;
  }
  
  public void revert(World world) {
    if (this.originalBlocks.isEmpty())
      return; 
    int count = 0;
    List<TimedBlockData> blocksToRevert = new ArrayList<>();
    while (count < 10000 && !this.originalBlocks.isEmpty()) {
      blocksToRevert.add(this.originalBlocks.remove(0));
      count++;
    } 
    for (TimedBlockData blockData : blocksToRevert)
      blockData.restoreBlock(world); 
  }
  
  public NBTTagCompound writeToNBT() {
    NBTTagCompound compound = new NBTTagCompound();
    compound.func_74778_a("name", this.name);
    compound.func_74772_a("expiration", this.expiration);
    NBTTagList blocksList = new NBTTagList();
    for (TimedBlockData block : this.originalBlocks)
      blocksList.func_74742_a((NBTBase)block.writeToNBT(new NBTTagCompound())); 
    compound.func_74782_a("originalBlocks", (NBTBase)blocksList);
    return compound;
  }
  
  public void readFromNBT(NBTTagCompound compound) {
    this.name = compound.func_74779_i("name");
    this.expiration = compound.func_74763_f("expiration");
    NBTTagList blocksList = compound.func_150295_c("originalBlocks", 10);
    this.originalBlocks = new ArrayList<>();
    for (int i = 0; i < blocksList.func_74745_c(); i++) {
      TimedBlockData block = new TimedBlockData();
      block.readFromNBT(blocksList.func_150305_b(i));
      this.originalBlocks.add(block);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\pojo\schematic\timed\TimedSchematic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */