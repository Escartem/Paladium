package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSailorTooth extends Item implements ITooltipInformations {
  public static final String DESCRIPTION = "Peut être transformé en 8 poudres d'os dans une table de craft. Elle a peut être d'autres utilisations";
  
  public static final String NAME = "sailor_tooth";
  
  public ItemSailorTooth() {
    func_77655_b("sailor_tooth");
    func_111206_d("palamod:sailor_tooth");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return MonthlyUtils.splitDescription("Peut être transformé en 8 poudres d'os dans une table de craft. Elle a peut être d'autres utilisations");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemSailorTooth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */