package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSkeletonSkull extends Item implements ITooltipInformations {
  public static final String DESCRIPTION = "Crâne auquel il manque 5 dents. Il serait sûrement heureux de les retrouver.";
  
  public static final String NAME = "tooth_skeleton_skull";
  
  public ItemSkeletonSkull() {
    func_77655_b("tooth_skeleton_skull");
    func_111206_d("palamod:tooth_skeleton_skull");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Crâne auquel il manque 5 dents. Il serait sûrement heureux de les retrouver.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemSkeletonSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */