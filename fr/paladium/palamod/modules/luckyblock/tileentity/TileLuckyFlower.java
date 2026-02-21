package fr.paladium.palamod.modules.luckyblock.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileLuckyFlower extends TileEntity {
  public long placedAt = System.currentTimeMillis();
  
  public void func_145845_h() {
    super.func_145845_h();
    long diff = System.currentTimeMillis() - this.placedAt;
    if (diff >= 259200000L)
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e); 
  }
  
  public void func_145839_a(NBTTagCompound compound) {
    if (compound.func_74764_b("placedAt"))
      this.placedAt = compound.func_74763_f("placedAt"); 
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    compound.func_74772_a("placedAt", this.placedAt);
    super.func_145841_b(compound);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileLuckyFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */