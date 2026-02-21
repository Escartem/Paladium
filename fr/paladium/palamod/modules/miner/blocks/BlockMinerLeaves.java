package fr.paladium.palamod.modules.miner.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.world.blocks.BlockLeaveBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;

public class BlockMinerLeaves extends BlockLeaveBase {
  public BlockMinerLeaves(String unlocalizedName) {
    super(unlocalizedName, 100, null);
    func_149647_a((CreativeTabs)Registry.MINER_TAB);
    func_149658_d("palamod:miner/" + unlocalizedName);
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149635_D() {
    double d0 = 0.5D;
    double d1 = 1.0D;
    return ColorizerFoliage.func_77470_a(0.5D, 1.0D);
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149741_i(int p_149741_1_) {
    return ColorizerFoliage.func_77468_c();
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149720_d(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
    int l = 0;
    int i1 = 0;
    int j1 = 0;
    for (int k1 = -1; k1 <= 1; k1++) {
      for (int l1 = -1; l1 <= 1; l1++) {
        int i2 = p_149720_1_.func_72807_a(p_149720_2_ + l1, p_149720_4_ + k1).func_150571_c(p_149720_2_ + l1, p_149720_3_, p_149720_4_ + k1);
        l += (i2 & 0xFF0000) >> 16;
        i1 += (i2 & 0xFF00) >> 8;
        j1 += i2 & 0xFF;
      } 
    } 
    return (l / 9 & 0xFF) << 16 | (i1 / 9 & 0xFF) << 8 | j1 / 9 & 0xFF;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\blocks\BlockMinerLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */