package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;

public class PotionZeroGravity extends APotion {
  public PotionZeroGravity() {
    super("zero_gravity", true, Color.RED);
  }
  
  public void consume(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {
    if (player.field_70122_E)
      return; 
    int blocksFromGround = 0;
    for (int i = (int)Math.floor(player.field_70163_u); i > 0 && 
      player.field_70170_p.func_147437_c(MathHelper.func_76128_c(player.field_70165_t), i, MathHelper.func_76128_c(player.field_70161_v)); i--)
      blocksFromGround++; 
    if (blocksFromGround <= 3)
      return; 
    double verticalSpeed = 0.6D;
    if (player.func_70093_af())
      verticalSpeed = 1.0D; 
    player.field_70181_x *= verticalSpeed;
    player.field_70143_R = 0.0F;
  }
  
  public void tickAfter(EntityPlayer player, PotionEffect effect) {
    super.tickAfter(player, effect);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\potion\PotionZeroGravity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */