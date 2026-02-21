package fr.paladium.palamod.modules.pillage.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockObsiRedstone extends BlockRedstoneDiode {
  protected BlockObsiRedstone(boolean p_i45400_1_) {
    super(p_i45400_1_);
    func_149711_c(-1.0F);
  }
  
  protected int func_149901_b(int p_149901_1_) {
    return 0;
  }
  
  public boolean func_149910_g(IBlockAccess p_149910_1_, int p_149910_2_, int p_149910_3_, int p_149910_4_, int p_149910_5_) {
    return (func_149902_h(p_149910_1_, p_149910_2_, p_149910_3_, p_149910_4_, p_149910_5_) > 0);
  }
  
  protected boolean func_149908_a(Block p_149908_1_) {
    return func_149909_d(p_149908_1_);
  }
  
  public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
    super.func_149749_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    func_149911_e(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_);
  }
  
  protected BlockRedstoneDiode func_149906_e() {
    return this;
  }
  
  protected BlockRedstoneDiode func_149898_i() {
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\BlockObsiRedstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */