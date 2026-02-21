package fr.paladium.palamod.modules.luckyblock.tileentity.june;

import net.minecraft.tileentity.TileEntity;

public class TileEntityBrightSound extends TileEntity {
  public int getOnTick() {
    return this.onTick;
  }
  
  public boolean isState() {
    return this.state;
  }
  
  private int onTick = 0;
  
  private boolean state = false;
  
  public void setOn() {
    this.state = true;
    this.onTick = 0;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_70296_d();
    this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 2);
  }
  
  public void setOff() {
    this.state = false;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    func_70296_d();
    this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 2);
  }
  
  public void func_145845_h() {
    if (this.state) {
      this.onTick++;
      if (this.onTick >= 20)
        setOff(); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\june\TileEntityBrightSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */