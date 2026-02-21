package fr.paladium.palaboss.common.entity.ai.task;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

class null implements IEntitySelector {
  public boolean func_82704_a(Entity entity) {
    return (entity instanceof EntityPlayer && !((EntityPlayer)entity).field_71075_bZ.field_75102_a);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\ai\task\EntityAINearestPlayerTarget$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */