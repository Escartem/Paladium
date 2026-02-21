package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.potions.EntityJupiterGravitySplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemJupiterGravitySplashPotion extends Item implements ITooltipInformations {
  public static final String NAME = "splash_potion_jupiter_gravity";
  
  public static final String TEXTURE = "palamod:splash_potion_jupiter_gravity";
  
  private static final String DESCRIPTION = "Tous les joueurs dans le rayon d'effet ne peuvent plus sauter pendant 1min";
  
  public ItemJupiterGravitySplashPotion() {
    func_77655_b("splash_potion_jupiter_gravity");
    func_111206_d("palamod:splash_potion_jupiter_gravity");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      MonthlyUtils.decrementCurrentItem(player, stack);
      world.func_72838_d((Entity)new EntityJupiterGravitySplashPotion(world, player));
    } 
    return stack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Tous les joueurs dans le rayon d'effet ne peuvent plus sauter pendant 1min");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\item\ItemJupiterGravitySplashPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */