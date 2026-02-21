package fr.paladium.palamod.modules.luckyblock.potions.easter;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palamod.modules.luckyblock.luckyevents.easter.PoissonAvril;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class PotionFishOne extends APotion {
  public PotionFishOne() {
    super("fish_one", true, Color.RED);
  }
  
  public void consume(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {
    PoissonAvril.applyPotionEffect(player);
  }
  
  public void tickAfter(EntityPlayer player, PotionEffect effect) {
    super.tickAfter(player, effect);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\potions\easter\PotionFishOne.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */