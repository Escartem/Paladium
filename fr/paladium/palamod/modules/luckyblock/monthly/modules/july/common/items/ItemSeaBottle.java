package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSeaBottle extends Item implements ITooltipInformations {
  public static final String DESCRIPTION = "Tu peux ouvrir la bouteille en effectuant un clic droit";
  
  public static final String NAME = "sea_bottle";
  
  public ItemSeaBottle() {
    func_77655_b("sea_bottle");
    func_111206_d("palamod:sea_bottle");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    itemStack.field_77994_a--;
    InventoryUtils.giveOrDropitems(player, new ItemStack(ItemsRegister.DRY_MESSAGE));
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(Items.field_151069_bo));
    return itemStack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return MonthlyUtils.splitDescription("Tu peux ouvrir la bouteille en effectuant un clic droit");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemSeaBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */