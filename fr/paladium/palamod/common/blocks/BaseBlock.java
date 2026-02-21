package fr.paladium.palamod.common.blocks;

import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BaseBlock extends Block {
  public BaseBlock(Material material, float hardness, String texture) {
    super(material);
    func_149658_d("palamod:" + texture);
    func_149711_c(hardness);
    init(material);
  }
  
  public BaseBlock(Material material, float hardness) {
    super(material);
    func_149711_c(hardness);
    init(material);
  }
  
  public BaseBlock(Material material) {
    super(material);
    init(material);
  }
  
  private void init(Material material) {
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    setSounds(material);
  }
  
  private void setSounds(Material material) {
    if (material == Material.field_151576_e) {
      func_149672_a(Block.field_149769_e);
    } else if (material == Material.field_151573_f) {
      func_149672_a(Block.field_149777_j);
    } else if (material == Material.field_151575_d) {
      func_149672_a(Block.field_149766_f);
    } else if (material == Material.field_151592_s) {
      func_149672_a(Block.field_149778_k);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\blocks\BaseBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */