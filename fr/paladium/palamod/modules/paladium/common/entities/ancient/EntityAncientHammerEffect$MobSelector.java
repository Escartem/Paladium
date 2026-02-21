package fr.paladium.palamod.modules.paladium.common.entities.ancient;

import fr.paladium.palawither.common.utils.WitherUtils;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

class MobSelector implements IEntitySelector {
  private MobSelector() {}
  
  public boolean func_82704_a(Entity entity) {
    if (!(entity instanceof net.minecraft.entity.monster.EntityMob) && !(entity instanceof net.minecraft.entity.passive.EntityAnimal))
      return false; 
    EntityLivingBase mob = (EntityLivingBase)entity;
    if (mob.field_70128_L || mob.func_85032_ar() || mob instanceof fr.paladium.palajobs.core.entity.boss.AEntityJobBoss || mob instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase || WitherUtils.isWither((Entity)mob))
      return false; 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\entities\ancient\EntityAncientHammerEffect$MobSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */