package fr.paladium.palamod.modules.miner.dimminer.common.entity.attack;

import fr.paladium.palamod.modules.miner.dimminer.common.entity.EntityMinerBoss;
import net.minecraft.entity.EntityLivingBase;

public interface IEntityMinerBossAttack {
  void start(EntityMinerBoss paramEntityMinerBoss, EntityLivingBase paramEntityLivingBase);
  
  void update(EntityMinerBoss paramEntityMinerBoss, EntityLivingBase paramEntityLivingBase);
  
  int getDuration();
  
  int getChance(EntityMinerBoss paramEntityMinerBoss);
  
  boolean isAvailable(EntityMinerBoss paramEntityMinerBoss);
  
  boolean isFinished(EntityMinerBoss paramEntityMinerBoss);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\entity\attack\IEntityMinerBossAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */