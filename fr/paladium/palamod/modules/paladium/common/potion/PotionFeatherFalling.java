package fr.paladium.palamod.modules.paladium.common.potion;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.PotionEffect;

public class PotionFeatherFalling extends APotion {
  public PotionFeatherFalling() {
    super("feather_falling", false, Color.WHITE);
    setIcon(Items.field_151008_G);
  }
  
  public void consume(EntityPlayer player, PotionEffect effect) {}
  
  public void tick(EntityPlayer player, PotionEffect effect) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\potion\PotionFeatherFalling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */