package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import fr.paladium.palarpg.module.entity.common.entity.RPGElementType;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

public class RPGEntitySelector implements IEntitySelector {
  public boolean func_82704_a(Entity entity) {
    if (!(entity instanceof RPGMobEntity) || entity == FindNearestAllyBehavior.this.getBehaviorOwner())
      return false; 
    RPGMobEntity rpgEntity = (RPGMobEntity)entity;
    if (rpgEntity.getBehaviorsName().stream().filter(bName -> bName.startsWith("BOOST")).findFirst().isPresent())
      return false; 
    RPGElementType elementType = FindNearestAllyBehavior.this.getElementType();
    if (elementType == null)
      return (rpgEntity.isBoosted() == FindNearestAllyBehavior.this.isBoosted()); 
    return (rpgEntity.getRPGType() == elementType && rpgEntity.isBoosted() == FindNearestAllyBehavior.this.isBoosted());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\FindNearestAllyBehavior$RPGEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */