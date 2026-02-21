package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.common.entity.projectile.RPGProjectile;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class RPGSpreadProjectileAroundAttack extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "SPREAD_PROJECTILE_AROUND";
  
  private int web_count;
  
  public RPGSpreadProjectileAroundAttack() {}
  
  public RPGSpreadProjectileAroundAttack(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.web_count = ((Integer)getData("web_count", Integer.valueOf(8))).intValue();
  }
  
  public boolean canPerform() {
    return (super.canPerform() && !((RPGMobEntity)getEntity()).field_70170_p.field_72995_K);
  }
  
  public void perform() {
    super.perform();
    if (getExecutingTick() == 22 && getAttack().getProjectile() != null) {
      Random rand = ((RPGMobEntity)getEntity()).func_70681_au();
      double radius = getAttack().getRange();
      double y = ((RPGMobEntity)getEntity()).field_70163_u + 4.0D;
      for (int i = 0; i < this.web_count; i++) {
        double angle = rand.nextDouble() * 2.0D * Math.PI;
        double r = radius * Math.sqrt(rand.nextDouble());
        double x = ((RPGMobEntity)getEntity()).field_70165_t + r * Math.cos(angle);
        double z = ((RPGMobEntity)getEntity()).field_70161_v + r * Math.sin(angle);
        RPGProjectile rpgProj = getAttack().getProjectileEntity(((RPGMobEntity)getEntity()).field_70170_p, (EntityLivingBase)getEntity());
        if (rpgProj == null)
          break; 
        rpgProj.field_70159_w = 0.0D;
        rpgProj.field_70179_y = 0.0D;
        rpgProj.func_70107_b(x, y, z);
        ((RPGMobEntity)getEntity()).field_70170_p.func_72838_d((Entity)rpgProj);
      } 
    } 
  }
  
  public String getID() {
    return "SPREAD_PROJECTILE_AROUND";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGSpreadProjectileAroundAttack(attack, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGSpreadProjectileAroundAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */