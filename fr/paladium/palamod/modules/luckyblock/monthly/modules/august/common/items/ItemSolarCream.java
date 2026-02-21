package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SunburnEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyBlockUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemSolarCream extends Item implements ITooltipInformations {
  public static final String DESCRIPTION = " Permet de se protéger contre les coups de soleil";
  
  public static final String NAME = "solar_cream";
  
  private static final String NO_SUNBURN_MESSAGE = "&cVous ne pouvez pas utiliser cet item car vous n'avez pas d'insolation.";
  
  public ItemSolarCream() {
    func_77655_b("solar_cream");
    func_111206_d("palamod:solar_cream");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
    if (world.field_72995_K)
      return itemStack; 
    if (LuckyBlockUtils.isInCombat(player, true))
      return itemStack; 
    if (!MonthlyUtils.hasPotionEffect((EntityLivingBase)player, (Potion)PLuckyBlock.SUNBURN)) {
      MonthlyUtils.sendMessage(player, new String[] { "&cVous ne pouvez pas utiliser cet item car vous n'avez pas d'insolation." });
      return itemStack;
    } 
    player.func_70690_d(getPotionEffect());
    MonthlyUtils.stopHeliosEvent((EntityPlayerMP)player, SunburnEvent.class);
    MonthlyUtils.decrementCurrentItem(player, itemStack);
    return itemStack;
  }
  
  private PotionEffect getPotionEffect() {
    PotionEffect effect = new PotionEffect(PLuckyBlock.SOLAR_CREAM.func_76396_c(), 2400, 0, false);
    effect.getCurativeItems().clear();
    return effect;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return MonthlyUtils.splitDescription(" Permet de se protéger contre les coups de soleil");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\ItemSolarCream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */