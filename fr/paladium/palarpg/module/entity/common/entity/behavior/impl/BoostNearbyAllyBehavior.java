package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.data.BoostMutator;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;

public class BoostNearbyAllyBehavior extends ABehavior {
  public static final String ID = "BOOST_NEARBY_ALLY";
  
  private long boostDuration;
  
  private double range;
  
  private String animation;
  
  private long animationDuration;
  
  public long getBoostDuration() {
    return this.boostDuration;
  }
  
  public double getRange() {
    return this.range;
  }
  
  public String getAnimation() {
    return this.animation;
  }
  
  public long getAnimationDuration() {
    return this.animationDuration;
  }
  
  private long lastBoostId = -2147483648L;
  
  public long getLastBoostId() {
    return this.lastBoostId;
  }
  
  private final Map<String, BoostMutator> boostEffects = new HashMap<>();
  
  public Map<String, BoostMutator> getBoostEffects() {
    return this.boostEffects;
  }
  
  protected BoostNearbyAllyBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    this.boostDuration = ((Double)getData("boostDuration", Double.valueOf(1000.0D))).longValue();
    this.range = ((Double)getData("range", Double.valueOf(2.0D))).doubleValue();
    this.animation = (String)getData("animation", "boost_aura");
    this.animationDuration = ((Double)getData("animationDuration", Double.valueOf(1000.0D))).longValue();
    Map<String, Map<String, Object>> boostEffectMap = (Map<String, Map<String, Object>>)getData("boostEffects", new HashMap<>());
    boostEffectMap.forEach((key, mapData) -> this.boostEffects.put(key, BoostMutator.of(mapData)));
  }
  
  public boolean canExecute() {
    if (!super.canExecute() || getBehaviorOwner() == null || getBehaviorOwner().func_70638_az() == null || (getBehaviorOwner()).field_70170_p.field_72995_K || this.lastBoostId == getBehaviorOwner().func_70638_az().func_145782_y() || getBehaviorOwner().getAnimated().isDeathAnimation())
      return false; 
    double distanceToTarget = getBehaviorOwner().func_70032_d((Entity)getBehaviorOwner().func_70638_az());
    return (distanceToTarget <= this.range);
  }
  
  public void execute() {
    if ((getBehaviorOwner()).field_70170_p.field_72995_K)
      return; 
    this.lastBoostId = getBehaviorOwner().func_70638_az().func_145782_y();
    getBehaviorOwner().playAnimation(this.animation, this.animationDuration, true).setCallback(e -> {
          RPGMobEntity rpgEntity = getBehaviorOwner();
          if (rpgEntity.func_70638_az() == null || !(rpgEntity.func_70638_az() instanceof RPGMobEntity))
            return; 
          RPGMobEntity rpgTarget = (RPGMobEntity)rpgEntity.func_70638_az();
          rpgTarget.boost(this.boostDuration, this.boostEffects);
          rpgEntity.func_70624_b(null);
          this.lastBoostId = -2147483648L;
        });
  }
  
  public String getID() {
    return "BOOST_NEARBY_ALLY";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new BoostNearbyAllyBehavior(behaviorData, entity);
  }
  
  public BoostNearbyAllyBehavior() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\BoostNearbyAllyBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */