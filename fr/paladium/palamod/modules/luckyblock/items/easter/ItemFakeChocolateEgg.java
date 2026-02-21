package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFakeChocolateEgg extends ItemFood implements ITooltipInformations {
  public ItemFakeChocolateEgg() {
    super(0, 0.0F, false);
    func_77655_b("fake_chocolate_egg");
    func_111206_d("palamod:chocolate_egg");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(64);
  }
  
  public void func_77849_c(ItemStack itemStack, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      player.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 100, 0));
      PlayerUtils.sendMessage(player, "Tu as mangé un oeuf périmé");
    } 
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Restaure l’intégralité de la barre de nourriture.", "N’en mange pas trop, cela te ferait mal au ventre." };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemFakeChocolateEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */