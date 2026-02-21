package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.LittleIceCreamEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemIceCreamEgg extends Item implements ITooltipInformations {
  public static final String DESCRIPTION = "desc";
  
  public static final String NAME = "ice_cream_egg";
  
  public ItemIceCreamEgg() {
    func_77655_b("ice_cream_egg");
    func_111206_d("palamod:ice_cream_egg");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    if (LuckyBlockUtils.isInCombat(player, true))
      return itemStack; 
    int x = (int)player.field_70165_t;
    int y = (int)player.field_70163_u;
    int z = (int)player.field_70161_v;
    if (!EventUtils.canInteract(player, x, y, z))
      return itemStack; 
    LittleIceCreamEvent.INSTANCE.perform((EntityPlayerMP)player, x, y, z, false);
    MonthlyUtils.decrementCurrentItem(player, itemStack);
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return MonthlyUtils.splitDescription("desc");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\ItemIceCreamEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */