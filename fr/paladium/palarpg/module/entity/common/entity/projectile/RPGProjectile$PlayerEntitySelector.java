package fr.paladium.palarpg.module.entity.common.entity.projectile;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

class PlayerEntitySelector implements IEntitySelector {
  public boolean func_82704_a(Entity entity) {
    if (!(entity instanceof EntityPlayer))
      return false; 
    EntityPlayer player = (EntityPlayer)entity;
    return (player.func_70089_S() && !player.field_71075_bZ.field_75102_a);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\projectile\RPGProjectile$PlayerEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */