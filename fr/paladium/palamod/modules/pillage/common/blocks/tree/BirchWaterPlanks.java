package fr.paladium.palamod.modules.pillage.common.blocks.tree;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BirchWaterPlanks extends Block implements ITooltipWiki {
  public BirchWaterPlanks(String name) {
    super(Material.field_151575_d);
    func_149711_c(2.0F);
    func_149752_b(5.0F);
    func_149672_a(Block.field_149766_f);
    func_149663_c(name);
    func_149658_d("palamod:pillage/" + name);
    func_149647_a((CreativeTabs)Registry.PILLAGE_TAB);
    GameRegistry.registerBlock(this, func_149739_a());
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-ressources-naturelles#2.-les-arbres";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\tree\BirchWaterPlanks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */