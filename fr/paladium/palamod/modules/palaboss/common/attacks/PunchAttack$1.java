package fr.paladium.palamod.modules.palaboss.common.attacks;

import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

class null implements Runnable {
  public void run() {
    try {
      for (int i = 0; i < 3; i++) {
        Thread.sleep(1000L);
        toAttackEntity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entityHost), damage);
      } 
      toAttackEntity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entityHost), damage * 2.0F);
      toAttackEntity.func_70024_g((
          -MathHelper.func_76126_a(entityHost.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (
          
          MathHelper.func_76134_b(entityHost.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F));
      toAttackEntity.field_70181_x += 0.4000000059604645D;
      entityHost.field_70159_w *= 0.6D;
      entityHost.field_70179_y *= 0.6D;
    } catch (Exception exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\PunchAttack$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */