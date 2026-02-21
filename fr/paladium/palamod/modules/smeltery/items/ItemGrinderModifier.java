package fr.paladium.palamod.modules.smeltery.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGrinderModifier extends Item implements ITooltipWiki {
  public ItemGrinderModifier(String name, String texture) {
    func_77655_b(name);
    func_111206_d("palamod:modifier/" + texture);
    func_77637_a((CreativeTabs)Registry.SMELTERY_TAB);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-les-crafts#4.-les-modifieurs";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\ItemGrinderModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */