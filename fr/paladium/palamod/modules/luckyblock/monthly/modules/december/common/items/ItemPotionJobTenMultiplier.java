package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemPotionJobTenMultiplier extends ItemFood implements ITooltipInformations {
  public static final String NAME = "potion_job_x10";
  
  private static final String DESCRIPTION = "";
  
  public ItemPotionJobTenMultiplier() {
    super(1, 1.0F, false);
    func_77655_b("potion_job_x10");
    func_77637_a(PLuckyBlock.TAB);
    func_111206_d("palamod:potion_job_x10");
    func_77625_d(1);
    func_77848_i();
  }
  
  public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return stack; 
    player.func_70690_d(new PotionEffect(PotionsRegister.JOB_TEN_MULTIPLIER
          .getPotionId(), 
          MonthlyUtils.translateSecondsToTicks(300), 0));
    MonthlyUtils.decrementCurrentItem(player, stack);
    return stack;
  }
  
  public EnumAction func_77661_b(ItemStack stack) {
    return EnumAction.drink;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    player.func_71008_a(stack, func_77626_a(stack));
    return stack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\common\items\ItemPotionJobTenMultiplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */