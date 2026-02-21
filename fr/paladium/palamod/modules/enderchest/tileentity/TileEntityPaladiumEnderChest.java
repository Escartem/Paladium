package fr.paladium.palamod.modules.enderchest.tileentity;

import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityEnderChest;

public class TileEntityPaladiumEnderChest extends TileEntityEnderChest {
  private int field_145974_k;
  
  public void func_145845_h() {
    super.func_145845_h();
    if (++this.field_145974_k % 20 * 4 == 0)
      this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, (Block)BlocksRegister.PALADIUM_ENDER_CHEST, 1, this.field_145973_j); 
    this.field_145975_i = this.field_145972_a;
    float f = 0.1F;
    if (this.field_145973_j > 0 && this.field_145972_a == 0.0F) {
      double d0 = this.field_145851_c + 0.5D;
      double d1 = this.field_145849_e + 0.5D;
      this.field_145850_b.func_72908_a(d0, this.field_145848_d + 0.5D, d1, "random.chestopen", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
    } 
    if ((this.field_145973_j == 0 && this.field_145972_a > 0.0F) || (this.field_145973_j > 0 && this.field_145972_a < 1.0F)) {
      float f2 = this.field_145972_a;
      if (this.field_145973_j > 0) {
        this.field_145972_a += 0.1F;
      } else {
        this.field_145972_a -= 0.1F;
      } 
      if (this.field_145972_a > 1.0F)
        this.field_145972_a = 1.0F; 
      float f1 = 0.5F;
      if (this.field_145972_a < 0.5F && f2 >= 0.5F) {
        double d1 = this.field_145851_c + 0.5D;
        double d2 = this.field_145849_e + 0.5D;
        this.field_145850_b.func_72908_a(d1, this.field_145848_d + 0.5D, d2, "random.chestclosed", 0.5F, this.field_145850_b.field_73012_v.nextFloat() * 0.1F + 0.9F);
      } 
      if (this.field_145972_a < 0.0F)
        this.field_145972_a = 0.0F; 
    } 
  }
  
  public void func_145969_a() {
    this.field_145973_j++;
    this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, (Block)BlocksRegister.PALADIUM_ENDER_CHEST, 1, this.field_145973_j);
  }
  
  public void func_145970_b() {
    this.field_145973_j--;
    this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, (Block)BlocksRegister.PALADIUM_ENDER_CHEST, 1, this.field_145973_j);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\tileentity\TileEntityPaladiumEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */