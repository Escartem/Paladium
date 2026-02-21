package fr.paladium.palamod.modules.trixium.tileentities;

import net.minecraft.tileentity.TileEntity;

public class TileEntityCloud extends TileEntity {
  public void setDuration(int duration) {
    this.duration = duration;
  }
  
  private int duration = Integer.MIN_VALUE;
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.duration == Integer.MIN_VALUE)
      return; 
    this.duration--;
    if (this.duration <= 0)
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\tileentities\TileEntityCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */