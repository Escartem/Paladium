package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items;

import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAugustFakeMoney extends Item {
  public static final String NAME = "august_fake_money";
  
  private static final String MESSAGE = "&cCe faux billet ne vaut rien !";
  
  public ItemAugustFakeMoney() {
    func_77625_d(1);
    func_77655_b("august_fake_money");
    func_111206_d("customnpcs:npcMoney");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public ItemStack func_77659_a(ItemStack stack, World worldIn, EntityPlayer player) {
    if (worldIn.field_72995_K) {
      MonthlyUtils.sendMessage(player, new String[] { "&cCe faux billet ne vaut rien !" });
      SoundUtils.CHICKEN_HURT.playClientSound(player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 1.0F);
      return stack;
    } 
    MonthlyUtils.decrementCurrentItem(player, stack);
    return stack;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\ItemAugustFakeMoney.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */