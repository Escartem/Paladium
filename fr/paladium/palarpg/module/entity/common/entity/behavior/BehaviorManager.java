package fr.paladium.palarpg.module.entity.common.entity.behavior;

import com.google.gson.JsonElement;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.AwakeSleepingAddsBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.BoostAllAlliesBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.BoostNearbyAllyBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.FindNearestAllyBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.FindNearestPlayerBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.FlyingBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.MergeOnDeathBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.MoveToTargetBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.PlayerAvoidBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.RunBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.SleepBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.SpawnAddOnStartAroundBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.SpawnAddOnStartBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.SplitOnDeathBehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.impl.WalkFasterInFlameBehavior;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import java.util.HashMap;
import java.util.Map;

public final class BehaviorManager {
  private static final Map<String, ABehavior> BEHAVIORS = new HashMap<>();
  
  public static void init() {
    register(new ABehavior[] { 
          (ABehavior)new FindNearestAllyBehavior(), (ABehavior)new FindNearestPlayerBehavior(), (ABehavior)new BoostNearbyAllyBehavior(), (ABehavior)new WalkFasterInFlameBehavior(), (ABehavior)new FlyingBehavior(), (ABehavior)new SplitOnDeathBehavior(), (ABehavior)new BoostAllAlliesBehavior(), (ABehavior)new RunBehavior(), (ABehavior)new MergeOnDeathBehavior(), (ABehavior)new AwakeSleepingAddsBehavior(), 
          (ABehavior)new SleepBehavior(), (ABehavior)new MoveToTargetBehavior(), (ABehavior)new SpawnAddOnStartBehavior(), (ABehavior)new SpawnAddOnStartAroundBehavior(), (ABehavior)new PlayerAvoidBehavior() });
  }
  
  public static void register(ABehavior... aBehaviors) {
    for (ABehavior behavior : aBehaviors) {
      if (BEHAVIORS.containsKey(behavior.getID()))
        throw new RuntimeException("Another behavior with the ID '" + behavior.getID() + "' is already registered"); 
      BEHAVIORS.put(behavior.getID(), behavior);
    } 
  }
  
  public static ABehavior getBehavior(String id, Map<String, JsonElement> behaviorData, RPGMobEntity entity) {
    ABehavior behavior = BEHAVIORS.get(id);
    if (behavior == null)
      throw new RuntimeException("The behavior with ID '" + id + "' doesn't exist"); 
    return behavior.copy(behaviorData, entity);
  }
  
  public static Map<String, ABehavior> getBehaviors() {
    return BEHAVIORS;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\BehaviorManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */