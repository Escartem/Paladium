package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items.consumable;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.StayHydratedEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.HydrationData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSummerWaterBottle extends ItemFood implements ITooltipInformations {
  public static final String DESCRIPTION = "Permet de remplir intégralement la barre de soif.";
  
  public static final String NAME = "summer_water_bottle";
  
  public ItemSummerWaterBottle() {
    super(1, 1.0F, false);
    func_77655_b("summer_water_bottle");
    func_77637_a(PLuckyBlock.TAB);
    func_111206_d("palamod:summer_water_bottle");
    func_77625_d(1);
    func_77848_i();
  }
  
  public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    if (LuckyBlockUtils.isInCombat(player, true))
      return stack; 
    EntityPlayerMP playerMP = (EntityPlayerMP)player;
    HydrationData data = StayHydratedEvent.INSTANCE.getData(playerMP);
    if (data != null)
      data.hydrate(playerMP, 100); 
    MonthlyUtils.decrementCurrentItem(player, stack);
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.EMPTY_WATER_BOTTLE));
    return stack;
  }
  
  public EnumAction func_77661_b(ItemStack p_77661_1_) {
    return EnumAction.drink;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    player.func_71008_a(stack, func_77626_a(stack));
    return stack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Permet de remplir intégralement la barre de soif.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\consumable\ItemSummerWaterBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */