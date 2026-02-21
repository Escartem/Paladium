package fr.paladium.palamod.modules.back2future.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;

class AIEvilAttack extends EntityAIAttackOnCollide {
  public AIEvilAttack(EntityRabbit p_i45867_1_) {
    super((EntityCreature)p_i45867_1_, EntityLivingBase.class, 1.4D, true);
  }
  
  protected double func_179512_a(EntityLivingBase attackTarget) {
    return (4.0F + attackTarget.field_70130_N);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityRabbit$AIEvilAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */