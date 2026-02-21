package fr.paladium.palamod.modules.miner.dimminer.common.entity.attack;

import fr.paladium.palamod.modules.miner.dimminer.common.entity.EntityMinerBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class EntityMinerBossPunchAttack implements IEntityMinerBossAttack {
  private boolean finished = true;
  
  private int tick = 0;
  
  public void start(EntityMinerBoss entity, EntityLivingBase entityLivingBase) {
    this.tick = 0;
    this.finished = false;
    entity.setPunchAnimProgress(0.0F);
  }
  
  public void update(EntityMinerBoss entity, EntityLivingBase target) {
    entity.setRightArmRaised(true);
    if (this.tick >= 40) {
      entity.setRaised(false);
      entity.setRightArmRaised(false);
      entity.setPunching(true);
      entity.setPunchAnimProgress(entity.getPunchAnimProgress() + 1.0F);
      entity.setPunchAnimMaxDuration(10.0F);
    } 
    if (this.tick == 45 && 
      entity.func_70685_l((Entity)target) && entity.func_70032_d((Entity)target) < 3.5D && 
      target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entity), 2.14748365E9F)) {
      entity.setState(0);
      entity.setSubState(0);
      entity.func_70606_j(entity.func_110138_aP());
    } 
    if (this.tick >= getDuration()) {
      entity.setRaised(false);
      entity.setRightArmRaised(false);
      entity.setPunchAnimProgress(10.0F);
      entity.setPunchAnimMaxDuration(10.0F);
      entity.setPunching(false);
      this.finished = true;
    } 
    this.tick++;
  }
  
  public int getDuration() {
    return 50;
  }
  
  public int getChance(EntityMinerBoss entity) {
    int state = entity.getState();
    return (state == 0) ? 75 : ((state == 1) ? 80 : ((state == 2) ? 85 : ((state == 3) ? 35 : ((state == 4) ? 20 : ((state == 5) ? 17 : 18)))));
  }
  
  public boolean isAvailable(EntityMinerBoss entity) {
    return true;
  }
  
  public boolean isFinished(EntityMinerBoss entity) {
    return this.finished;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\entity\attack\EntityMinerBossPunchAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */