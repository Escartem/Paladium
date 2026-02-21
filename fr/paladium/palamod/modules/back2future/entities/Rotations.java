package fr.paladium.palamod.modules.back2future.entities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;

public class Rotations {
  protected final float x;
  
  protected final float y;
  
  protected final float z;
  
  public Rotations(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public Rotations(NBTTagList nbt) {
    this.x = nbt.func_150308_e(0);
    this.y = nbt.func_150308_e(1);
    this.z = nbt.func_150308_e(2);
  }
  
  public NBTTagList writeToNBT() {
    NBTTagList nbttaglist = new NBTTagList();
    nbttaglist.func_74742_a((NBTBase)new NBTTagFloat(this.x));
    nbttaglist.func_74742_a((NBTBase)new NBTTagFloat(this.y));
    nbttaglist.func_74742_a((NBTBase)new NBTTagFloat(this.z));
    return nbttaglist;
  }
  
  public boolean equals(Object p_equals_1_) {
    if (!(p_equals_1_ instanceof Rotations))
      return false; 
    Rotations rotations = (Rotations)p_equals_1_;
    return (this.x == rotations.x && this.y == rotations.y && this.z == rotations.z);
  }
  
  public float getX() {
    return this.x;
  }
  
  public float getY() {
    return this.y;
  }
  
  public float getZ() {
    return this.z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\Rotations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */