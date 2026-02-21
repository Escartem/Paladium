package fr.paladium.palamod.modules.alchimiste.common.items;

import fr.paladium.common.utils.ITooltipWiki;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAlchemistKey extends ItemAlchemistBase implements ITooltipWiki {
  public ItemAlchemistKey(String name) {
    super(name);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer p_77624_2_, List<String> l, boolean p_77624_4_) {
    if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("totalSeve")) {
      l.add("§6Sève stockée: §a" + stack.func_77978_p().func_74762_e("totalSeve"));
    } else {
      l.add("§6Aucune sève stockée");
    } 
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/autour-du-chaudron#4.-les-portails";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\items\ItemAlchemistKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */