package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import software.bernie.geckolib3.entity.animation.AnimationType;

public class WalkFasterInFlameBehavior extends ABehavior {
  public static final String ID = "WALK_FASTER_IN_FLAMES";
  
  private static final UUID FLAMESPEED_MODIFIER = UUIDUtils.from("0383d831-e5cd-4e7c-b5bd-94f936230ac2");
  
  private boolean isFlameSpeedEnabled = false;
  
  private String defaultWalkingAnimation;
  
  private double additionalSpeed;
  
  private String animation;
  
  protected WalkFasterInFlameBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    this.defaultWalkingAnimation = entity.getAnimated().hasDefaultAnimation(AnimationType.WALK) ? entity.getAnimated().getDefaultAnimation(AnimationType.WALK) : "";
    this.additionalSpeed = ((Double)getData("additionalSpeed", Double.valueOf(0.1D))).doubleValue();
    this.animation = (String)getData("animation", "walk_fast");
  }
  
  public boolean canExecute() {
    if (!super.canExecute() || getBehaviorOwner() == null || (getBehaviorOwner()).field_70121_D == null || this.defaultWalkingAnimation == null || this.defaultWalkingAnimation.isEmpty())
      return false; 
    boolean isInFlame = (getBehaviorOwner()).field_70170_p.func_147470_e((getBehaviorOwner()).field_70121_D.func_72331_e(0.001D, 0.001D, 0.001D));
    if (!isInFlame && this.isFlameSpeedEnabled) {
      this.isFlameSpeedEnabled = false;
      disableFlameSpeed();
    } 
    return isInFlame;
  }
  
  public void execute() {
    if (!this.isFlameSpeedEnabled) {
      this.isFlameSpeedEnabled = true;
      enableFlameSpeed();
    } 
  }
  
  public String getID() {
    return "WALK_FASTER_IN_FLAMES";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new WalkFasterInFlameBehavior(behaviorData, entity);
  }
  
  private final void enableFlameSpeed() {
    getBehaviorOwner().setDefaultAnimation(AnimationType.WALK, new String[] { this.animation });
    if (!(getBehaviorOwner()).field_70170_p.field_72995_K && getBehaviorOwner().func_110148_a(SharedMonsterAttributes.field_111263_d).func_111127_a(FLAMESPEED_MODIFIER) == null) {
      AttributeModifier flameSpeedModifier = new AttributeModifier(FLAMESPEED_MODIFIER, "flamespeed", this.additionalSpeed, 0);
      getBehaviorOwner().func_110148_a(SharedMonsterAttributes.field_111263_d).func_111121_a(flameSpeedModifier);
    } 
  }
  
  private final void disableFlameSpeed() {
    getBehaviorOwner().setDefaultAnimation(AnimationType.WALK, new String[] { this.defaultWalkingAnimation });
    if (!(getBehaviorOwner()).field_70170_p.field_72995_K) {
      AttributeModifier speedAttribute = getBehaviorOwner().func_110148_a(SharedMonsterAttributes.field_111263_d).func_111127_a(FLAMESPEED_MODIFIER);
      if (speedAttribute != null)
        getBehaviorOwner().func_110148_a(SharedMonsterAttributes.field_111263_d).func_111124_b(speedAttribute); 
    } 
  }
  
  public WalkFasterInFlameBehavior() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\WalkFasterInFlameBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */