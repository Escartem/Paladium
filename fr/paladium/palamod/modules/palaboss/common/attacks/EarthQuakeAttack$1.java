package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonPrimitive;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityAzhur;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

class null implements Runnable {
  public void run() {
    try {
      int offset = 40;
      JsonPrimitive damagePrimitive = (JsonPrimitive)EarthQuakeAttack.access$000(EarthQuakeAttack.this).getValue();
      float damage = damagePrimitive.getAsFloat();
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 400; j++) {
          Thread.sleep(50L);
          EventUtils.spawnParticle(world, "smoke", entityHost.field_70165_t + world.field_73012_v.nextInt(30) - 15.0D, entityHost.field_70163_u + world.field_73012_v
              .nextInt(3), entityHost.field_70161_v + world.field_73012_v.nextInt(30) - 15.0D, 1000, 3.0D);
          AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(entityHost.field_70165_t - offset, entityHost.field_70163_u - offset, entityHost.field_70161_v - offset, entityHost.field_70165_t + offset, entityHost.field_70163_u + offset, entityHost.field_70161_v + offset);
          List players = entityHost.field_70170_p.func_72872_a(EntityPlayer.class, scanAbove);
          for (Object entityObject : players) {
            EntityLivingBase entity = (EntityLivingBase)entityObject;
            entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entityHost), damage);
            entity.func_70024_g((
                -MathHelper.func_76126_a((EarthQuakeAttack.access$100(EarthQuakeAttack.this)).field_70177_z * 3.1415927F / 180.0F) * 8.0F * 0.5F), 0.1D, (
                
                MathHelper.func_76134_b((EarthQuakeAttack.access$100(EarthQuakeAttack.this)).field_70177_z * 3.1415927F / 180.0F) * 8.0F * 0.5F));
            entity.field_70181_x += 0.8000000059604645D;
            entity.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 60, 1, true));
          } 
        } 
      } 
      EarthQuakeAttack.access$202(EarthQuakeAttack.this, false);
      azhur.inEarthQuake = false;
    } catch (Exception e) {
      EarthQuakeAttack.access$202(EarthQuakeAttack.this, false);
      azhur.inEarthQuake = false;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\EarthQuakeAttack$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */