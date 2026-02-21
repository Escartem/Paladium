package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.potions;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class PotionJobTenMultiplier extends APotion {
  public PotionJobTenMultiplier() {
    super("x10_job_xp", false, Color.GREEN);
  }
  
  public void consume(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\common\potions\PotionJobTenMultiplier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */