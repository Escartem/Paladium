package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.common.entity.projectile.RPGProjectile;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class RPGAttackProjectile extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "ATTACK_PROJECTILE";
  
  private RPGAttack.RPGAttackProjectile projectile;
  
  public RPGAttackProjectile() {}
  
  public RPGAttackProjectile(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.projectile = attack.getProjectile();
  }
  
  public boolean canPerform() {
    if (!super.canPerform() || ((RPGMobEntity)getEntity()).func_70638_az() == null || !((RPGMobEntity)getEntity()).func_70638_az().func_70089_S() || !((RPGMobEntity)getEntity()).func_70635_at().func_75522_a((Entity)((RPGMobEntity)getEntity()).func_70638_az()))
      return false; 
    if (this.projectile == null)
      throw new RuntimeException("Attack 'ATTACK_PROJECTILE' for entity '" + ((RPGMobEntity)getEntity()).getRpgData().getId() + "' doesn't have a projectile section"); 
    return true;
  }
  
  public void perform() {
    super.perform();
    EntityLivingBase target = ((RPGMobEntity)getEntity()).func_70638_az();
    if (target == null)
      return; 
    double d0 = target.field_70165_t - ((RPGMobEntity)getEntity()).field_70165_t;
    double d2 = target.field_70161_v - ((RPGMobEntity)getEntity()).field_70161_v;
    float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
    ((RPGMobEntity)getEntity()).field_70126_B = ((RPGMobEntity)getEntity()).field_70758_at = ((RPGMobEntity)getEntity()).field_70759_as = ((RPGMobEntity)getEntity()).field_70177_z = f2 % 360.0F;
  }
  
  public void onEnd() {
    super.onEnd();
    RPGProjectile rPGProjectile = this.projectile.getProjectileEntity(((RPGMobEntity)getEntity()).field_70170_p, (EntityLivingBase)getEntity(), getAttack());
    if (rPGProjectile == null)
      return; 
    throwProjectile((Entity)rPGProjectile, (Entity)getEntity(), (Entity)((RPGMobEntity)getEntity()).func_70638_az(), this.projectile.getForce(), this.projectile.getPrecision());
  }
  
  public String getID() {
    return "ATTACK_PROJECTILE";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGAttackProjectile(attack, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGAttackProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */