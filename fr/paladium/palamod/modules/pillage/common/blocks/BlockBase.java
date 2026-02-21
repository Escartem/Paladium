package fr.paladium.palamod.modules.pillage.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockBase extends Block {
  public BlockBase(Material material, String unlocalizedName) {
    super(material);
    func_149663_c(unlocalizedName);
    func_149658_d("palamod:pillage/" + unlocalizedName);
    func_149647_a((CreativeTabs)Registry.PILLAGE_TAB);
    register();
  }
  
  public void register() {
    GameRegistry.registerBlock(this, func_149739_a());
  }
  
  public int func_149692_a(int metadata) {
    return metadata;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\BlockBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */