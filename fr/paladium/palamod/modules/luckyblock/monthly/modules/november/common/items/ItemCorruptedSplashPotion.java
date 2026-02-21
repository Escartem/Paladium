package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.entities.EntityCorruptedSplashPotion;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCorruptedSplashPotion extends Item implements ITooltipInformations {
  public static final String NAME = "corrupted_splash_potion";
  
  private static final String DESCRIPTION = "DESC.TXT : Le ENTITY:PLAYER touché par la ITEM:POTION subit un EFFECT:CORRUPTION de 10 secondes";
  
  public ItemCorruptedSplashPotion() {
    func_77655_b("corrupted_splash_potion");
    func_111206_d("palamod:corrupted_splash_potion");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      MonthlyUtils.decrementCurrentItem(player, stack);
      world.func_72838_d((Entity)new EntityCorruptedSplashPotion(world, player));
    } 
    return stack;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("DESC.TXT : Le ENTITY:PLAYER touché par la ITEM:POTION subit un EFFECT:CORRUPTION de 10 secondes");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\items\ItemCorruptedSplashPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */