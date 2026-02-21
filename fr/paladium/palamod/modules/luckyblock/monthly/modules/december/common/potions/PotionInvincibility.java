package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.potions;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class PotionInvincibility extends APotion {
  public PotionInvincibility() {
    super("invincibility", false, Color.MAGENTA);
  }
  
  public void consume(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\common\potions\PotionInvincibility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */