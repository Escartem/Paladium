package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.potions;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionCorruptedPoison extends APotion {
  public PotionCorruptedPoison() {
    super("corrupted_poison", false, Color.GREEN);
  }
  
  public void consume(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {
    if (player.field_70170_p.field_72995_K)
      return; 
    if (player.func_70644_a(Potion.field_76436_u))
      FMLServerScheduler.getInstance().add(new Runnable[] { () -> player.func_82170_o(Potion.field_76436_u.field_76415_H) }); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\potions\PotionCorruptedPoison.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */