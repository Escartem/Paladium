package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.behavior.data.BoostMutator;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.HashMap;
import java.util.Map;

public class RPGFrigoriaBoost extends ARPGBaseAttack<RPGMobEntity> {
  private static final String ID = "FRIGORIA_BOOST";
  
  private long boostDuration;
  
  private final Map<String, BoostMutator> boostEffects = new HashMap<>();
  
  private RPGFrigoriaBoost(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.boostDuration = ((Double)getData("boostDuration", Double.valueOf(1000.0D))).longValue();
    Map<String, Map<String, Object>> boostEffectMap = (Map<String, Map<String, Object>>)getData("boostEffects", new HashMap<>());
    boostEffectMap.forEach((key, mapData) -> this.boostEffects.put(key, BoostMutator.of(mapData)));
  }
  
  public void onStart() {
    super.onStart();
    ((RPGMobEntity)getEntity()).boost(this.boostDuration, this.boostEffects);
  }
  
  public String getID() {
    return "FRIGORIA_BOOST";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGFrigoriaBoost(attack, entity);
  }
  
  public RPGFrigoriaBoost() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGFrigoriaBoost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */