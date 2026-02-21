package fr.paladium.palaschematicmod.common.pojo.schematic.timed;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TimedBlockData {
  private Block block;
  
  private int x;
  
  private int y;
  
  private int z;
  
  private int metadata;
  
  private NBTTagCompound tileEntityData;
  
  public void setBlock(Block block) {
    this.block = block;
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public void setZ(int z) {
    this.z = z;
  }
  
  public void setMetadata(int metadata) {
    this.metadata = metadata;
  }
  
  public void setTileEntityData(NBTTagCompound tileEntityData) {
    this.tileEntityData = tileEntityData;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof TimedBlockData))
      return false; 
    TimedBlockData other = (TimedBlockData)o;
    if (!other.canEqual(this))
      return false; 
    if (getX() != other.getX())
      return false; 
    if (getY() != other.getY())
      return false; 
    if (getZ() != other.getZ())
      return false; 
    if (getMetadata() != other.getMetadata())
      return false; 
    Object this$block = getBlock(), other$block = other.getBlock();
    if ((this$block == null) ? (other$block != null) : !this$block.equals(other$block))
      return false; 
    Object this$tileEntityData = getTileEntityData(), other$tileEntityData = other.getTileEntityData();
    return !((this$tileEntityData == null) ? (other$tileEntityData != null) : !this$tileEntityData.equals(other$tileEntityData));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof TimedBlockData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getX();
    result = result * 59 + getY();
    result = result * 59 + getZ();
    result = result * 59 + getMetadata();
    Object $block = getBlock();
    result = result * 59 + (($block == null) ? 43 : $block.hashCode());
    Object $tileEntityData = getTileEntityData();
    return result * 59 + (($tileEntityData == null) ? 43 : $tileEntityData.hashCode());
  }
  
  public String toString() {
    return "TimedBlockData(block=" + getBlock() + ", x=" + getX() + ", y=" + getY() + ", z=" + getZ() + ", metadata=" + getMetadata() + ", tileEntityData=" + getTileEntityData() + ")";
  }
  
  public Block getBlock() {
    return this.block;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public int getMetadata() {
    return this.metadata;
  }
  
  public NBTTagCompound getTileEntityData() {
    return this.tileEntityData;
  }
  
  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    compound.func_74778_a("block", Block.field_149771_c.func_148750_c(this.block).toString());
    compound.func_74768_a("x", this.x);
    compound.func_74768_a("y", this.y);
    compound.func_74768_a("z", this.z);
    compound.func_74768_a("metadata", this.metadata);
    if (this.tileEntityData != null)
      compound.func_74782_a("tileEntityData", (NBTBase)this.tileEntityData); 
    return compound;
  }
  
  public void readFromNBT(NBTTagCompound compound) {
    this.block = Block.func_149684_b(compound.func_74779_i("block"));
    this.x = compound.func_74762_e("x");
    this.y = compound.func_74762_e("y");
    this.z = compound.func_74762_e("z");
    this.metadata = compound.func_74762_e("metadata");
    if (compound.func_74764_b("tileEntityData"))
      this.tileEntityData = compound.func_74775_l("tileEntityData"); 
  }
  
  public void restoreBlock(World world) {
    world.func_147465_d(this.x, this.y, this.z, this.block, this.metadata, 2);
    if (this.tileEntityData != null) {
      TileEntity tileEntity = TileEntity.func_145827_c(this.tileEntityData);
      if (tileEntity != null)
        world.func_147455_a(this.x, this.y, this.z, tileEntity); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\common\pojo\schematic\timed\TimedBlockData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */