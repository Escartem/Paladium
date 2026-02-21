package fr.paladium.palashop.provider.cosmetic.common.dto.behavior;

import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NonNull;

public class CosmeticBehaviorManager {
  private static final Map<String, List<CosmeticBehavior>> BEHAVIOR_MAP = new HashMap<>();
  
  public static void registerBehavior(@NonNull Class<? extends CosmeticBehavior<?>>... behaviorClasses) {
    if (behaviorClasses == null)
      throw new NullPointerException("behaviorClasses is marked non-null but is null"); 
    for (Class<? extends CosmeticBehavior<?>> clazz : behaviorClasses) {
      try {
        registerBehavior(clazz.newInstance());
      } catch (Exception e) {
        throw new IllegalArgumentException("error while registering behavior " + clazz.getSimpleName(), e);
      } 
    } 
  }
  
  public static void registerBehavior(@NonNull CosmeticBehavior<?>... behaviors) {
    if (behaviors == null)
      throw new NullPointerException("behaviors is marked non-null but is null"); 
    for (CosmeticBehavior<?> behavior : behaviors)
      registerBehavior(behavior); 
  }
  
  private static void registerBehavior(@NonNull CosmeticBehavior<?> behavior) {
    if (behavior == null)
      throw new NullPointerException("behavior is marked non-null but is null"); 
    if (behavior.getFactories().isEmpty())
      throw new IllegalArgumentException("factories cannot be empty for behavior " + behavior.getClass().getSimpleName()); 
    for (Iterator<String> iterator = behavior.getFactories().iterator(); iterator.hasNext(); ) {
      String factory = iterator.next();
      BEHAVIOR_MAP.computeIfAbsent(factory, key -> new ArrayList());
      if (((List)BEHAVIOR_MAP.get(factory)).stream().anyMatch(b -> b.getId().equals(behavior.getId())))
        throw new IllegalArgumentException("behavior " + behavior.getId() + " is already registered for factory " + factory); 
      ((List<CosmeticBehavior<?>>)BEHAVIOR_MAP.get(factory)).add(behavior);
    } 
  }
  
  @NonNull
  public static List<CosmeticBehavior> getBehaviors(@NonNull ICosmetic cosmetic) {
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    if (cosmetic.getBehaviors() == null || cosmetic.getBehaviors().isEmpty())
      return new ArrayList<>(); 
    List<CosmeticBehavior> behaviors = BEHAVIOR_MAP.getOrDefault(cosmetic.getFactory().getId(), new ArrayList<>());
    return (List<CosmeticBehavior>)behaviors.stream().filter(behavior -> behavior.isApplicable(cosmetic)).collect(Collectors.toList());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\dto\behavior\CosmeticBehaviorManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */