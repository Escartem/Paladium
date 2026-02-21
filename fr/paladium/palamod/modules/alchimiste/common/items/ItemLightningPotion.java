package fr.paladium.palamod.modules.alchimiste.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.ItemAlchemist;
import net.minecraft.item.ItemStack;

public class ItemLightningPotion extends ItemAlchemist implements ITooltipWiki {
  private String name;
  
  public ItemLightningPotion() {
    this.name = "lightning_potion";
    func_77655_b(this.name);
    func_111206_d("palamod:alchimiste/" + this.name);
    func_77625_d(1);
    func_77627_a(true);
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/autour-du-chaudron#1.-les-composants-du-chaudron";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\items\ItemLightningPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */