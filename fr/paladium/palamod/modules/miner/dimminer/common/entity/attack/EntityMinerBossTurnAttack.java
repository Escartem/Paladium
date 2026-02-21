package fr.paladium.palamod.modules.miner.dimminer.common.entity.attack;

import fr.paladium.palamod.modules.miner.dimminer.common.entity.EntityMinerBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class EntityMinerBossTurnAttack implements IEntityMinerBossAttack {
  private boolean finished = true;
  
  private int tick = 0;
  
  public void start(EntityMinerBoss entity, EntityLivingBase entityLivingBase) {
    this.tick = 0;
    this.finished = false;
  }
  
  public void update(EntityMinerBoss entity, EntityLivingBase target) {
    entity.setRaised(true);
    if (this.tick >= 40)
      entity.setTurnAttack(true); 
    if (this.tick >= 45 && 
      entity.func_70032_d((Entity)target) < 3.5D && 
      target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entity), 2.14748365E9F)) {
      entity.setState(0);
      entity.setSubState(0);
      entity.func_70606_j(entity.func_110138_aP());
    } 
    if (this.tick >= getDuration()) {
      entity.setTurnAttack(false);
      entity.setRaised(false);
      this.finished = true;
    } 
    this.tick++;
  }
  
  public int getDuration() {
    return 60;
  }
  
  public int getChance(EntityMinerBoss entity) {
    int state = entity.getState();
    return (state == 0) ? 0 : ((state == 1) ? 0 : ((state == 2) ? 0 : ((state == 3) ? 0 : ((state == 4) ? 28 : ((state == 5) ? 33 : 43)))));
  }
  
  public boolean isAvailable(EntityMinerBoss entity) {
    int state = entity.getState();
    return (state > 3);
  }
  
  public boolean isFinished(EntityMinerBoss entity) {
    return this.finished;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\entity\attack\EntityMinerBossTurnAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */