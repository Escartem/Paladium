package fr.paladium.palamod.modules.paladium.common.blocks.decorative;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockObsidianTrapDoor extends BlockTrapDoor implements ITooltipWiki {
  private String unlocalizedName = "obsidianTrapDoor";
  
  public BlockObsidianTrapDoor() {
    super(Material.field_151576_e);
    func_149663_c(this.unlocalizedName);
    func_149658_d("palamod:decorative/" + this.unlocalizedName);
    func_149752_b(100.0F);
    func_149711_c(50.0F);
    setHarvestLevel("pickaxe", 2);
    func_149647_a((CreativeTabs)Registry.DECORATIVE_TAB);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-la-construction#1.-les-objets-a-base-dobsidienne";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\decorative\BlockObsidianTrapDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */