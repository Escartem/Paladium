package fr.paladium.palamod.modules.paladium.common.blocks.decorative;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BaseBlockSlab extends BlockSlab {
  private Block block;
  
  public BaseBlockSlab(Block thisblock, Material material, String unlocalizedName, String texture, float resistance, float hardness, String toolClass, int levelTool) {
    super(false, material);
    this.block = thisblock;
    func_149647_a((CreativeTabs)Registry.DECORATIVE_TAB);
    func_149663_c(unlocalizedName);
    func_149658_d(texture);
    func_149752_b(resistance);
    func_149711_c(hardness);
    setHarvestLevel(toolClass, levelTool);
  }
  
  public String func_150002_b(int p_150002_1_) {
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
    return Item.func_150898_a(this.block);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\decorative\BaseBlockSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */