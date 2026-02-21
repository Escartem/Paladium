package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import fr.paladium.palarpg.module.entity.common.entity.RPGElementType;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

public class RPGEntitySelector implements IEntitySelector {
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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\BoostAllAlliesBehavior$RPGEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */