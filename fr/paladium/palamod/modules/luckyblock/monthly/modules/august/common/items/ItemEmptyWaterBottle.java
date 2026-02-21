package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEmptyWaterBottle extends Item implements ITooltipInformations {
  public static final String NAME = "empty_water_bottle";
  
  public static final String DESCRIPTION = "Suivez les instructions d’été pour la recycler";
  
  public ItemEmptyWaterBottle() {
    func_77655_b("empty_water_bottle");
    func_111206_d("palamod:empty_water_bottle");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Suivez les instructions d’été pour la recycler");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\ItemEmptyWaterBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */