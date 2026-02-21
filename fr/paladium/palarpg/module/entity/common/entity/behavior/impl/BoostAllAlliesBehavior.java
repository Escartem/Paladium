package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.RPGElementType;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.data.BoostMutator;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public class BoostAllAlliesBehavior extends ABehavior {
  public static final String ID = "BOOST_ALL_ALLIES";
  
  private final RPGEntitySelector rpgEntitySelector = new RPGEntitySelector();
  
  private long boostDuration;
  
  private double range;
  
  private String animation;
  
  private long animationDuration;
  
  private RPGElementType elementType;
  
  public RPGEntitySelector getRpgEntitySelector() {
    return this.rpgEntitySelector;
  }
  
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
  
  public RPGElementType getElementType() {
    return this.elementType;
  }
  
  private final Map<String, BoostMutator> boostEffects = new HashMap<>();
  
  private List<RPGMobEntity> targetEntities;
  
  public Map<String, BoostMutator> getBoostEffects() {
    return this.boostEffects;
  }
  
  public List<RPGMobEntity> getTargetEntities() {
    return this.targetEntities;
  }
  
  public BoostAllAlliesBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    this.boostDuration = ((Double)getData("boostDuration", Double.valueOf(1000.0D))).longValue();
    this.range = ((Double)getData("range", Double.valueOf(100.0D))).doubleValue();
    this.animation = (String)getData("animation", "boost_aura");
    this.animationDuration = ((Double)getData("animationDuration", Double.valueOf(1000.0D))).longValue();
    this.elementType = (RPGElementType)getData("type", RPGElementType.class);
    Map<String, Map<String, Object>> boostEffectMap = (Map<String, Map<String, Object>>)getData("boostEffects", new HashMap<>());
    boostEffectMap.forEach((key, mapData) -> (BoostMutator)this.boostEffects.put(key, BoostMutator.of(mapData)));
  }
  
  public boolean canExecute() {
    if (getBehaviorOwner() == null || (getBehaviorOwner()).field_70170_p.field_72995_K || !super.canExecute() || getBehaviorOwner().getAnimated().isDeathAnimation())
      return false; 
    AxisAlignedBB checkAABB = (getBehaviorOwner()).field_70121_D.func_72314_b(this.range, 4.0D, this.range);
    List<RPGMobEntity> entitiesInRange = (getBehaviorOwner()).field_70170_p.func_82733_a(RPGMobEntity.class, checkAABB, this.rpgEntitySelector);
    if (entitiesInRange.isEmpty())
      return false; 
    this.targetEntities = entitiesInRange;
    return true;
  }
  
  public void execute() {
    if ((getBehaviorOwner()).field_70170_p.field_72995_K)
      return; 
    getBehaviorOwner().playAnimation(this.animation, this.animationDuration, true);
    this.targetEntities.forEach(rpgTarget -> rpgTarget.boost(this.boostDuration, this.boostEffects));
  }
  
  public String getID() {
    return "BOOST_ALL_ALLIES";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new BoostAllAlliesBehavior(behaviorData, entity);
  }
  
  public BoostAllAlliesBehavior() {}
  
  protected class RPGEntitySelector implements IEntitySelector {
    public boolean func_82704_a(Entity entity) {
      if (!(entity instanceof RPGMobEntity) || entity == BoostAllAlliesBehavior.this.getBehaviorOwner())
        return false; 
      RPGMobEntity rpgEntity = (RPGMobEntity)entity;
      RPGElementType elementType = BoostAllAlliesBehavior.this.getElementType();
      if (elementType == null)
        return !rpgEntity.isBoosted(); 
      return (rpgEntity.getRPGType() == elementType && !rpgEntity.isBoosted());
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\BoostAllAlliesBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */