package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSheepNoiseBox extends Item {
  public static final String NAME = "sheep_noise_box";
  
  public static final String SOUND_NAME = "sheep";
  
  public ItemSheepNoiseBox() {
    func_77655_b("sheep_noise_box");
    func_111206_d("palamod:sheep_noise_box");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77656_e(16);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K)
      MonthlyUtils.playSound((EntityPlayerMP)player, "sheep"); 
    MonthlyUtils.damageCurrentItem(player, item);
    return super.func_77659_a(item, world, player);
  }
  
  public boolean func_77616_k(ItemStack stack) {
    return false;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemSheepNoiseBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */