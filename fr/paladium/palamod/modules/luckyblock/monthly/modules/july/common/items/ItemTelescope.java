package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTelescope extends Item implements ITooltipInformations {
  public static final String DESCRIPTION = "Donne un zoom x2. Permet de zoomer jusqu'à x4 en utilisant la molette de sa souris";
  
  public static final String NAME = "telescope";
  
  public ItemTelescope() {
    func_77655_b("telescope");
    func_111206_d("palamod:telescope");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (!world.field_72995_K)
      return itemStack; 
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return MonthlyUtils.splitDescription("Donne un zoom x2. Permet de zoomer jusqu'à x4 en utilisant la molette de sa souris");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemTelescope.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */