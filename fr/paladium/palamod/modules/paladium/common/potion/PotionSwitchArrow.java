package fr.paladium.palamod.modules.paladium.common.potion;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionSwitchArrow extends APotion {
  public PotionSwitchArrow() {
    super("switch_arrow", false, Color.BLACK);
    setIcon(new ResourceLocation("palamod", "textures/potions/switch_arrow.png"));
  }
  
  public void consume(EntityPlayer player, PotionEffect effect) {}
  
  public void tick(EntityPlayer player, PotionEffect effect) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\potion\PotionSwitchArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */