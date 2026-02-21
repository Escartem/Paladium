package fr.paladium.palamod.modules.luckyblock.items.halloween;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPumpkinCrush extends Item implements ITooltipWiki {
  public ItemPumpkinCrush() {
    func_77625_d(64);
    func_77655_b("pumpkincrush");
    func_111206_d("palamod:pumpkincrush");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#4.-lucky-block-halloween";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\halloween\ItemPumpkinCrush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */