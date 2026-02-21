package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.common.entity.projectile.RPGProjectile;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class RPGShootMultipleProjectileAttack extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "SHOOT_MULTIPLE_PROJECTILE";
  
  private int count;
  
  public RPGShootMultipleProjectileAttack() {}
  
  public RPGShootMultipleProjectileAttack(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.count = ((Integer)getData("count", Integer.valueOf(5))).intValue();
  }
  
  public boolean canPerform() {
    if (!super.canPerform() || ((RPGMobEntity)getEntity()).func_70638_az() == null)
      return false; 
    if (getAttack().getProjectile() == null)
      throw new RuntimeException("Attack 'SHOOT_MULTIPLE_PROJECTILE' for entity '" + ((RPGMobEntity)getEntity()).getRpgData().getId() + "' doesn't have a projectile section"); 
    return true;
  }
  
  public void onEnd() {
    super.onEnd();
    for (int i = 0; i < this.count; i++) {
      RPGProjectile proj = getAttack().getProjectileEntity(((RPGMobEntity)getEntity()).field_70170_p, (EntityLivingBase)getEntity());
      if (proj == null)
        return; 
      throwProjectile((Entity)proj, (Entity)getEntity(), (Entity)((RPGMobEntity)getEntity()).func_70638_az(), getAttack().getProjectile().getForce(), getAttack().getProjectile().getPrecision());
    } 
  }
  
  public String getID() {
    return "SHOOT_MULTIPLE_PROJECTILE";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGShootMultipleProjectileAttack(attack, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGShootMultipleProjectileAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */