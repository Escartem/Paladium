package fr.paladium.palamod.modules.ajobs.common.blocks.root;

import fr.paladium.palajobs.core.tab.JobsTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockRootPlank extends Block {
  public BlockRootPlank(String texture) {
    super(Material.field_151575_d);
    func_149663_c(texture);
    func_149658_d("palajobs:" + texture);
    func_149647_a((CreativeTabs)JobsTab.getInstance());
    func_149711_c(3.0F);
    func_149672_a(field_149766_f);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\blocks\root\BlockRootPlank.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */