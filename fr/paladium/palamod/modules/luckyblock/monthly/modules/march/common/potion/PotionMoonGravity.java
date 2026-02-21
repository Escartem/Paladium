package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionMoonGravity extends APotion {
  public PotionMoonGravity() {
    super("moon_gravity", true, Color.YELLOW);
  }
  
  public void consume(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tickAfter(EntityPlayer player, PotionEffect effect) {
    super.tickAfter(player, effect);
    player.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, MonthlyUtils.translateSecondsToTicks(1), 2));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\potion\PotionMoonGravity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */