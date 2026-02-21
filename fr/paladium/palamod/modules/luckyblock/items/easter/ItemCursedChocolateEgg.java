package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCursedChocolateEgg extends Item implements ITooltipInformations {
  public static final String NAME = "cursed_chocolate_egg";
  
  private static final String DESCRIPTION = "Permet de rendre 10% des points de vie à un wither. Il sera ensuite insensible à cet effet pendant 30 minutes. L’objet ne pourra plus être utilisé pendant 1h00.";
  
  public ItemCursedChocolateEgg() {
    func_77655_b("cursed_chocolate_egg");
    func_111206_d("palamod:cursed_chocolate_egg");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77656_e(20);
  }
  
  public boolean func_77616_k(ItemStack stack) {
    return false;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Permet de rendre 10% des points de vie à un wither. Il sera ensuite insensible à cet effet pendant 30 minutes. L’objet ne pourra plus être utilisé pendant 1h00.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemCursedChocolateEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */