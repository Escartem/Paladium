package fr.paladium.palamod.modules.luckyblock.tileentity.black;

import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntitySifflet extends TileEntity {
  public byte note;
  
  public boolean previousRedstoneState;
  
  public void func_145841_b(NBTTagCompound p_145841_1_) {
    super.func_145841_b(p_145841_1_);
    p_145841_1_.func_74774_a("note", this.note);
  }
  
  public void func_145839_a(NBTTagCompound p_145839_1_) {
    super.func_145839_a(p_145839_1_);
    this.note = p_145839_1_.func_74771_c("note");
    if (this.note < 0)
      this.note = 0; 
    if (this.note > 24)
      this.note = 24; 
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    int note = 0;
    for (int y0 = this.field_145848_d + 1; y0 < this.field_145848_d + 23; ) {
      Block b = this.field_145850_b.func_147439_a(this.field_145851_c, y0, this.field_145849_e);
      if (b instanceof fr.paladium.palamod.modules.luckyblock.blocks.black.BlockSifflet) {
        note++;
        y0++;
      } 
    } 
    this.note = (byte)(note % 25);
    func_70296_d();
  }
  
  public void triggerNote(World p_145878_1_, int p_145878_2_, int p_145878_3_, int p_145878_4_) {
    p_145878_1_.func_147452_c(p_145878_2_, p_145878_3_, p_145878_4_, BlocksRegister.SIFFLET, 0, this.note);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\black\TileEntitySifflet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */