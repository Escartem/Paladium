package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import net.minecraft.entity.Entity;

public class RPGAttackMelee extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "ATTACK_MELEE";
  
  public RPGAttackMelee() {}
  
  public RPGAttackMelee(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
  }
  
  public boolean canPerform() {
    if (!super.canPerform() || ((RPGMobEntity)getEntity()).func_70638_az() == null || ((RPGMobEntity)getEntity()).func_70032_d((Entity)((RPGMobEntity)getEntity()).func_70638_az()) > getAttack().getRange())
      return false; 
    return true;
  }
  
  public void onEnd() {
    super.onEnd();
    if (((RPGMobEntity)getEntity()).func_70638_az() != null && ((RPGMobEntity)getEntity()).func_70032_d((Entity)((RPGMobEntity)getEntity()).func_70638_az()) <= getAttack().getRange())
      damage((Entity)((RPGMobEntity)getEntity()).func_70638_az()); 
  }
  
  public String getID() {
    return "ATTACK_MELEE";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGAttackMelee(attack, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGAttackMelee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */