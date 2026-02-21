package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

public class RPGEntitySelector implements IEntitySelector {
  public boolean func_82704_a(Entity entity) {
    if (!(entity instanceof RPGMobEntity) || entity == MergeOnDeathBehavior.this.getBehaviorOwner())
      return false; 
    RPGMobEntity owner = MergeOnDeathBehavior.this.getBehaviorOwner();
    RPGMobEntity rpgEntity = (RPGMobEntity)entity;
    if (!rpgEntity.hasBehavior("MERGE_ON_DEATH"))
      return false; 
    return (owner.getRPGType() == rpgEntity.getRPGType());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\MergeOnDeathBehavior$RPGEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */