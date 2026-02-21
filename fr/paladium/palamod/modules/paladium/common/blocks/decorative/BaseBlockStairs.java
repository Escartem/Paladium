package fr.paladium.palamod.modules.paladium.common.blocks.decorative;

import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;

public class BaseBlockStairs extends BlockStairs {
  public BaseBlockStairs(Block base, String unlocalizedName, float resistance, float hardness, String toolClass, int levelTools) {
    super(base, 0);
    func_149647_a((CreativeTabs)Registry.DECORATIVE_TAB);
    func_149663_c(unlocalizedName);
    func_149752_b(resistance);
    func_149711_c(hardness);
    setHarvestLevel(toolClass, levelTools);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\decorative\BaseBlockStairs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */