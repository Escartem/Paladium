package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion;

import fr.paladium.palaforgeutils.lib.potion.APotion;
import java.awt.Color;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

public class PotionWarStar extends APotion {
  private static final float DAMAGE = 2.0F;
  
  public PotionWarStar() {
    super("war_star", false, Color.YELLOW);
  }
  
  public void consume(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {
    player.func_70606_j(player.func_110138_aP());
    List<Entity> nearbyEntities = player.field_70170_p.func_72872_a(EntityPlayer.class, 
        AxisAlignedBB.func_72330_a(player.field_70165_t - 2.0D, player.field_70163_u - 2.0D, player.field_70161_v - 2.0D, player.field_70165_t + 2.0D, player.field_70163_u + 2.0D, player.field_70161_v + 2.0D));
    for (Entity entity : nearbyEntities) {
      if (entity instanceof EntityPlayer && entity != player)
        ((EntityPlayer)entity).func_70097_a(DamageSource.field_76376_m, 2.0F); 
    } 
    if (player.field_70170_p.field_72995_K)
      for (int i = 0; i < 100; i++) {
        double offsetX = (Math.random() - 0.5D) * 2.0D;
        double offsetY = Math.random() * 2.0D;
        double offsetZ = (Math.random() - 0.5D) * 2.0D;
        double particleRed = Math.random();
        double particleGreen = Math.random();
        double particleBlue = Math.random();
        player.field_70170_p.func_72869_a("reddust", player.field_70165_t + offsetX, player.field_70163_u + offsetY - 2.0D, player.field_70161_v + offsetZ, particleRed, particleGreen, particleBlue);
      }  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\potion\PotionWarStar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */