package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.Map;
import software.bernie.geckolib3.entity.animation.AnimationType;

public class RunBehavior extends ABehavior {
  public static final String ID = "RUN";
  
  private String animation;
  
  private double speed;
  
  private boolean executed = false;
  
  public RunBehavior(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    super(behaviorData);
    setBehaviorOwner(entity);
    this.animation = (String)getData("animation", "run");
    this.speed = ((Double)getData("speed", Double.valueOf(2.0D))).doubleValue();
  }
  
  public boolean canExecute() {
    return (!this.executed && super.canExecute());
  }
  
  public void execute() {
    if (!this.executed) {
      this.executed = true;
      getBehaviorOwner().setDefaultAnimation(AnimationType.WALK, new String[] { this.animation });
      getBehaviorOwner().getProperties().setSpeed(this.speed);
      getBehaviorOwner().resetSpeed();
    } 
  }
  
  public String getID() {
    return "RUN";
  }
  
  public ABehavior copy(Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    return new RunBehavior(behaviorData, entity);
  }
  
  public RunBehavior() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\RunBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */