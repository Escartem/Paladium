package fr.paladium.palamod.modules.luckyblock.tileentity.easter;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEggOfPlenty extends TileEntity {
  public static final String TAG_PLACED_MILLIS = "placedMillis";
  
  public void setPlacedMillis(long placedMillis) {
    this.placedMillis = placedMillis;
  }
  
  public long getPlacedMillis() {
    return this.placedMillis;
  }
  
  private long placedMillis = System.currentTimeMillis();
  
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
    this.placedMillis = compound.func_74763_f("placedMillis");
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
    compound.func_74772_a("placedMillis", this.placedMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\easter\TileEntityEggOfPlenty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */