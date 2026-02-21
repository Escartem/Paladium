package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemRainbowLeather extends Item implements ITooltipInformations {
  public static final String NAME = "rainbow_leather";
  
  private static final String DESCRIPTION = "Peut être utilisé pour faire un morceau d’armure arc-en-ciel";
  
  public ItemRainbowLeather() {
    func_77655_b("rainbow_leather");
    func_111206_d("palamod:rainbow_leather");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Peut être utilisé pour faire un morceau d’armure arc-en-ciel");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemRainbowLeather.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */