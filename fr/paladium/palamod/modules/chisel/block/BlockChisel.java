package fr.paladium.palamod.modules.chisel.block;

import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.blocks.BaseBlock;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockChisel extends BaseBlock {
  public BlockChisel(Material material) {
    super(material);
    init();
  }
  
  public BlockChisel(Material material, float hardness) {
    super(material, hardness);
    init();
  }
  
  public BlockChisel(Material material, float hardness, String texture) {
    super(material, hardness, texture);
    init();
  }
  
  private void init() {
    func_149647_a((CreativeTabs)Registry.CHISEL_TAB);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\block\BlockChisel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */