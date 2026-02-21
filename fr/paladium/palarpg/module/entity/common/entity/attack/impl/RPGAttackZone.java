package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palaboss.common.entity.ai.attack.selector.AttackEntitySelector;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class RPGAttackZone extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "ATTACK_ZONE";
  
  private IEntitySelector selector;
  
  private double minDist;
  
  public RPGAttackZone() {}
  
  public RPGAttackZone(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.minDist = ((Double)getData("minDist", Double.valueOf(0.0D))).doubleValue();
    this.selector = (IEntitySelector)new AttackEntitySelector(ent -> {
          if (!ent.func_70089_S())
            return Boolean.valueOf(false); 
          double distance = ((RPGMobEntity)getEntity()).func_70032_d(ent);
          return Boolean.valueOf((distance >= this.minDist && distance <= getAttack().getRange()));
        });
  }
  
  public boolean canPerform() {
    List<EntityPlayer> players = getPlayersAround();
    return (super.canPerform() && !players.isEmpty());
  }
  
  public void onEnd() {
    super.onEnd();
    for (EntityPlayer player : getPlayersAround())
      damage((Entity)player); 
  }
  
  public String getID() {
    return "ATTACK_ZONE";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGAttackZone(attack, entity);
  }
  
  private List<EntityPlayer> getPlayersAround() {
    return getEntitiesAround(EntityPlayer.class, getAttack().getRange(), this.selector);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGAttackZone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */