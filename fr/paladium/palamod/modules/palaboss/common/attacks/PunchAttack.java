package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PunchAttack extends ShortRangeAttack {
  public static final String NAME = "punch";
  
  private transient AttackParamEntry attackDamage;
  
  private transient AttackParamEntry knockback;
  
  private transient JsonObject jsonObject;
  
  public PunchAttack(JsonObject jsonObject) {
    super("punch");
    this.jsonObject = jsonObject;
    registerParam(this.attackDamage = new AttackParamEntry("attackDamage", Integer.valueOf(10)));
    registerParam(this.knockback = new AttackParamEntry("knockback", Integer.valueOf(1)));
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, final EntityBossBase entityHost, final EntityLivingBase toAttackEntity) {
    JsonPrimitive damagePrimitive = (JsonPrimitive)this.attackDamage.getValue();
    final float damage = damagePrimitive.getAsFloat();
    JsonPrimitive knockbackPrimitive = (JsonPrimitive)this.knockback.getValue();
    final int knockback = knockbackPrimitive.getAsInt();
    if (entityHost instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBeepBoop) {
      entityHost.setPunchTimer(60);
      entityHost.field_70170_p.func_72960_a((Entity)entityHost, (byte)4);
      toAttackEntity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entityHost), damage);
      toAttackEntity.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 60, 255, true));
      (new Thread(new Runnable() {
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
          })).start();
    } else {
      toAttackEntity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entityHost), damage);
      toAttackEntity.func_70024_g((
          -MathHelper.func_76126_a(entityHost.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (
          
          MathHelper.func_76134_b(entityHost.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F));
      toAttackEntity.field_70181_x += 0.4000000059604645D;
      entityHost.field_70159_w *= 0.6D;
      entityHost.field_70179_y *= 0.6D;
      if (entityHost instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityArachna)
        toAttackEntity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 500, 1)); 
      if (entityHost instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityUlcan) {
        toAttackEntity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 100, 1));
        toAttackEntity.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 100, 1));
      } 
    } 
  }
  
  public boolean isRunning() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\PunchAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */