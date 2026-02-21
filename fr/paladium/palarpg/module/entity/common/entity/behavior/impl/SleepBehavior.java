package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.Map;
import net.minecraft.entity.SharedMonsterAttributes;

public class SleepBehavior extends ABehavior {
  public static final String ID = "SLEEP";
  
  private boolean sleeping;
  
  public SleepBehavior() {}
  
  public boolean isSleeping() {
    return this.sleeping;
  }
  
  public SleepBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    this.sleeping = true;
  }
  
  public boolean canExecute() {
    return (super.canExecute() && this.sleeping);
  }
  
  public void execute() {
    if (getBehaviorOwner().func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e() != 0.0D)
      getBehaviorOwner().clearSpeed(); 
    if (getBehaviorOwner().func_70638_az() != null) {
      getBehaviorOwner().func_70624_b(null);
      getBehaviorOwner().func_70661_as().func_75499_g();
    } 
    if (getBehaviorOwner().func_110143_aJ() < getBehaviorOwner().func_110138_aP())
      getBehaviorOwner().func_70606_j(getBehaviorOwner().func_110138_aP()); 
    (getBehaviorOwner()).field_70724_aR = getBehaviorOwner().getProperties().getAttackDelay();
  }
  
  public void awake() {
    this.sleeping = false;
    getBehaviorOwner().resetSpeed();
  }
  
  public String getID() {
    return "SLEEP";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new SleepBehavior(behaviorData, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\SleepBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */