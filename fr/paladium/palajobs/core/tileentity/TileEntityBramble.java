package fr.paladium.palajobs.core.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBramble extends TileEntity {
  private int oldBlock;
  
  private int oldMeta;
  
  private int tickExisted;
  
  private int life;
  
  public void setOldBlock(int oldBlock) {
    this.oldBlock = oldBlock;
  }
  
  public void setOldMeta(int oldMeta) {
    this.oldMeta = oldMeta;
  }
  
  public void setTickExisted(int tickExisted) {
    this.tickExisted = tickExisted;
  }
  
  public void setLife(int life) {
    this.life = life;
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (this.field_145850_b.field_72995_K)
      return; 
    this.tickExisted++;
    if (this.life <= 0) {
      Block b = Block.func_149729_e(this.oldBlock);
      if (b != Blocks.field_150350_a) {
        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, b);
        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.oldMeta, 2);
      } else {
        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150348_b);
      } 
      return;
    } 
    if (this.life > 0 && this.tickExisted == this.life) {
      Block b = Block.func_149729_e(this.oldBlock);
      if (b != Blocks.field_150350_a) {
        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, b);
        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.oldMeta, 2);
      } else {
        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150348_b);
      } 
      return;
    } 
  }
  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    nbt.func_74768_a("brambleOldBlock", this.oldBlock);
    nbt.func_74768_a("brambleOldMeta", this.oldMeta);
    nbt.func_74768_a("brambleTickExisted", this.tickExisted);
    nbt.func_74768_a("brambleLife", this.life);
  }
  
  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    this.oldBlock = nbt.func_74762_e("brambleOldBlock");
    this.oldMeta = nbt.func_74762_e("brambleOldMeta");
    this.oldBlock = nbt.func_74762_e("brambleTickExisted");
    this.life = nbt.func_74762_e("brambleLife");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\tileentity\TileEntityBramble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */