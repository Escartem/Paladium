package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemArrowBase extends Item implements ITooltipWiki {
  int effect;
  
  public ItemArrowBase(String name, String texture, int effect) {
    this.effect = effect;
    func_77625_d(64);
    func_77655_b(name);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_111206_d("palamod:" + texture);
  }
  
  public int getEffect() {
    return this.effect;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-armes#6.-les-fleches";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemArrowBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */