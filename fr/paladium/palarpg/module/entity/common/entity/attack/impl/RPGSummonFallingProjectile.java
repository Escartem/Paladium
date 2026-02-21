package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.common.entity.projectile.RPGProjectile;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

public class RPGSummonFallingProjectile extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "SUMMON_FALLING_PROJECTILE";
  
  public RPGSummonFallingProjectile() {}
  
  private static final Random RANDOM = new Random();
  
  private int count;
  
  public RPGSummonFallingProjectile(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.count = ((Double)getData("count", Double.valueOf(1.0D))).intValue();
  }
  
  public boolean canPerform() {
    if (!super.canPerform() || ((RPGMobEntity)getEntity()).field_70170_p.field_72995_K || this.count <= 0 || getAttack().getProjectile() == null)
      return false; 
    return true;
  }
  
  public void onEnd() {
    super.onEnd();
    for (int i = 0; i < this.count; i++) {
      Vec3 pos = getRandomPosition();
      if (pos != null) {
        RPGProjectile projectile = getAttack().getProjectileEntity(((RPGMobEntity)getEntity()).field_70170_p, (EntityLivingBase)getEntity());
        if (projectile != null) {
          projectile.func_70012_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, 0.0F, 0.0F);
          ((RPGMobEntity)getEntity()).field_70170_p.func_72838_d((Entity)projectile);
        } 
      } 
    } 
  }
  
  public Vec3 getRandomPosition() {
    double angle = RANDOM.nextDouble() * 2.0D * Math.PI;
    double r = getAttack().getRange() * Math.sqrt(RANDOM.nextDouble());
    double x = ((RPGMobEntity)getEntity()).field_70165_t + r * Math.cos(angle);
    double z = ((RPGMobEntity)getEntity()).field_70161_v + r * Math.sin(angle);
    int y = ((RPGMobEntity)getEntity()).field_70170_p.func_72825_h((int)Math.floor(x), (int)Math.floor(z));
    while (!((RPGMobEntity)getEntity()).field_70170_p.func_147437_c((int)Math.floor(x), (int)Math.floor(y) - 1, (int)Math.floor(z))) {
      if (y <= ((RPGMobEntity)getEntity()).field_70163_u)
        return null; 
      y--;
    } 
    return Vec3.func_72443_a(x, y - 1.0D, z);
  }
  
  public String getID() {
    return "SUMMON_FALLING_PROJECTILE";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGSummonFallingProjectile(attack, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGSummonFallingProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */