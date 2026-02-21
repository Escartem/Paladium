package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.items.BaseItem;
import net.minecraft.item.ItemStack;

public class ItemMagicalTool extends BaseItem implements ITooltipWiki {
  public ItemMagicalTool() {
    super("magical_tool");
    func_77655_b("magicalTool");
    func_77656_e(10000);
    func_77625_d(1);
  }
  
  public boolean func_82789_a(ItemStack p_82789_1_, ItemStack p_82789_2_) {
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemMagicalTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */