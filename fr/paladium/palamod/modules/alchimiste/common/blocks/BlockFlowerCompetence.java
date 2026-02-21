package fr.paladium.palamod.modules.alchimiste.common.blocks;

import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;

public class BlockFlowerCompetence extends BlockBush {
  public BlockFlowerCompetence(String name) {
    super(Material.field_151585_k);
    func_149663_c(name);
    func_149658_d("palamod:flowers/" + name);
    func_149676_a(0.3F, 0.0F, 0.3F, 0.8F, 1.0F, 0.8F);
    func_149711_c(0.0F);
    func_149672_a(Block.field_149779_h);
    func_149647_a(PAlchimiste.TAB);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\blocks\BlockFlowerCompetence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */