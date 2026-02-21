package fr.paladium.palamod.modules.paladium.common.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BlockCave extends Block implements ITooltipWiki {
  protected String field_149770_b;
  
  public BlockCave(String unlocalizedName) {
    super(Material.field_151592_s);
    this.field_149770_b = unlocalizedName;
    func_149663_c(this.field_149770_b);
    func_149658_d("palamod:caveblock/" + this.field_149770_b);
    func_149711_c(4.0F);
    func_149752_b(4.0F);
    setHarvestLevel("pickaxe", 1);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public int func_149645_b() {
    return 0;
  }
  
  public boolean func_149662_c() {
    return true;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pillage#7.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockCave.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */