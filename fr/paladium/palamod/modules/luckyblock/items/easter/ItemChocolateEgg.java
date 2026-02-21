package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemChocolateEgg extends ItemFood implements ITooltipInformations {
  public ItemChocolateEgg() {
    super(20, 0.0F, false);
    func_77655_b("chocolate_egg");
    func_111206_d("palamod:chocolate_egg");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(64);
  }
  
  protected void func_77849_c(ItemStack stack, World world, EntityPlayer player) {
    super.func_77849_c(stack, world, player);
    if (!world.field_72995_K)
      player.func_70690_d(pickupRandomEffect(world.field_73012_v)); 
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer player, boolean flag) {
    return new String[] { "Restaure l’intégralité de la barre de nourriture ", "et donne un effet aléatoire pendant une minute.", "N’en mange pas trop, cela te ferait mal au ventre." };
  }
  
  private PotionEffect pickupRandomEffect(Random random) {
    int duration = MonthlyUtils.translateSecondsToTicks(60);
    List<PotionEffect> effects = Arrays.asList(new PotionEffect[] { new PotionEffect(Potion.field_76440_q.field_76415_H, duration, 0), new PotionEffect(Potion.field_76431_k.field_76415_H, duration, 0), new PotionEffect(Potion.field_76422_e.field_76415_H, duration, 0), new PotionEffect(Potion.field_76439_r.field_76415_H, duration, 0), new PotionEffect(Potion.field_76428_l.field_76415_H, duration, 0), new PotionEffect(Potion.field_76436_u.field_76415_H, duration, 0), new PotionEffect(Potion.field_76424_c.field_76415_H, duration, 0), new PotionEffect(Potion.field_82731_v.field_76415_H, duration, 0) });
    return effects.get(random.nextInt(effects.size()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemChocolateEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */