package fr.paladium.palamod.modules.luckyblock.tileentity.black;

import net.minecraft.tileentity.TileEntity;

public class TileEntityFakeFire extends TileEntity {
  private int tickExisted;
  
  public void func_145845_h() {
    if (++this.tickExisted > 600)
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\black\TileEntityFakeFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */