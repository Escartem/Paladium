package fr.paladium.palamod.modules.smeltery.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemToolPart extends Item implements ITooltipWiki {
  public ItemToolPart(String texture, String name) {
    func_77655_b(name);
    func_111206_d("palamod:toolpart/" + texture);
    func_77637_a((CreativeTabs)Registry.SMELTERY_TAB);
  }
  
  public boolean func_77662_d() {
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#8.-grinder";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\ItemToolPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */