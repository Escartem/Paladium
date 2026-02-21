package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class PotionInvincibility extends APotion {
  public PotionInvincibility() {
    super("star_invincibility", false, Color.PINK);
  }
  
  public void consume(EntityPlayer entityPlayer, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer entityPlayer, PotionEffect potionEffect) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\potion\PotionInvincibility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */