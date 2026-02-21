package fr.paladium.palamod.modules.paladium.common.items.upgrade;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBowSpeedModifier extends Item implements ITooltipWiki {
  public ItemBowSpeedModifier() {
    func_77655_b("bowspeedmodifier");
    func_111206_d("palamod:machine/bowSpeedModifier");
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#3.-box-machine";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\item\\upgrade\ItemBowSpeedModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */