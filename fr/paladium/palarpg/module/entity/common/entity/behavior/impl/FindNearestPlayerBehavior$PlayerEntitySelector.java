package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerEntitySelector implements IEntitySelector {
  public boolean func_82704_a(Entity entity) {
    if (!(entity instanceof EntityPlayer))
      return false; 
    EntityPlayer player = (EntityPlayer)entity;
    return (player.func_70089_S() && !player.field_71075_bZ.field_75102_a && FindNearestPlayerBehavior.this.getBehaviorOwner().func_70635_at().func_75522_a((Entity)player));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\FindNearestPlayerBehavior$PlayerEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */