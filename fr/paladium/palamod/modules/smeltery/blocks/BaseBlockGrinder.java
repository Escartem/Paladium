package fr.paladium.palamod.modules.smeltery.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BaseBlockGrinder extends Block implements ITooltipWiki {
  private String unlocalizedName;
  
  public BaseBlockGrinder(String unlocalizedName) {
    super(Material.field_151576_e);
    this.unlocalizedName = unlocalizedName;
    func_149663_c(this.unlocalizedName);
    func_149658_d("palamod:smeltery/" + this.unlocalizedName);
    func_149752_b(8.0F);
    func_149711_c(12.0F);
    setHarvestLevel("pickaxe", 2);
    func_149672_a(Block.field_149769_e);
    func_149647_a((CreativeTabs)Registry.SMELTERY_TAB);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#8.-grinder";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\blocks\BaseBlockGrinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */