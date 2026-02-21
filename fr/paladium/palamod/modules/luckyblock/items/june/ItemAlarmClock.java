package fr.paladium.palamod.modules.luckyblock.items.june;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAlarmClock extends Item implements ITooltipInformations {
  public ItemAlarmClock() {
    func_77655_b("alarm_clock");
    func_111206_d("palamod:alarm_clock_item");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Objet pouvant être posé.", "Peut être configuré pour sonner à une heure prédéfinie par la personne qui le pose." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\june\ItemAlarmClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */