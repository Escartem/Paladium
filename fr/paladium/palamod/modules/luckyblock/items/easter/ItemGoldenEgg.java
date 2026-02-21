package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemGoldenEgg extends ItemFood implements ITooltipInformations {
  public ItemGoldenEgg() {
    super(0, 0.0F, false);
    func_77655_b("golden_egg");
    func_111206_d("palamod:golden_egg");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(64);
  }
  
  public void func_77849_c(ItemStack itemStack, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      player.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 100, 0));
      player.func_70097_a(DamageSource.field_76376_m, 0.0F);
    } 
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Peut être transformé en lingots d’or", "dans une table de craft.", "Mais il est si joli, tu veux vraiment le faire ?" };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemGoldenEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */