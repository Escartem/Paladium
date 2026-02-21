package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class PotionAlienEffect extends APotion {
  public PotionAlienEffect() {
    super("alien_effect", true, Color.YELLOW);
  }
  
  public void consume(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tickAfter(EntityPlayer player, PotionEffect effect) {
    super.tickAfter(player, effect);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\potion\PotionAlienEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */