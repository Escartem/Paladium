package fr.paladium.palamod.modules.luckyblock.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWirelessLever extends TileEntity {
  int x;
  
  int y;
  
  int z;
  
  boolean active;
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
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
  
  public boolean isActive() {
    return this.active;
  }
  
  public void setActive(boolean active) {
    this.active = active;
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    if (compound.func_74764_b("x")) {
      this.x = compound.func_74762_e("x");
      this.y = compound.func_74762_e("y");
      this.z = compound.func_74762_e("z");
      this.active = compound.func_74767_n("active");
    } 
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74768_a("x", this.x);
    compound.func_74768_a("y", this.y);
    compound.func_74768_a("z", this.z);
    compound.func_74757_a("active", this.active);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityWirelessLever.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */