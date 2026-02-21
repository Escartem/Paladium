package fr.paladium.palamod.modules.miner.dimminer.common.entity.attack;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.modules.miner.dimminer.common.entity.EntityMinerBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class EntityMinerBossLeftShockWaveAttack implements IEntityMinerBossAttack {
  private boolean finished = true;
  
  private int tick = 0;
  
  public void start(EntityMinerBoss entity, EntityLivingBase entityLivingBase) {
    this.tick = 0;
    this.finished = false;
    entity.setStrikeGroundAnimProgress(0.0F);
    entity.setStrikeGroundAnimMaxDuration(5.0F);
  }
  
  public void update(EntityMinerBoss entity, EntityLivingBase target) {
    entity.setLeftArmRaised(true);
    if (this.tick >= 45) {
      entity.setStrikingGround(true);
      entity.setStrikeGroundAnimProgress(entity.getStrikeGroundAnimProgress() + 1.0F);
    } 
    if (this.tick == 45)
      earthquake((EntityLivingBase)entity, 10); 
    if (this.tick >= getDuration()) {
      if (entity.func_70032_d((Entity)target) < 5.0F && 
        target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entity), 2.14748365E9F)) {
        entity.setState(0);
        entity.setSubState(0);
        entity.func_70606_j(entity.func_110138_aP());
      } 
      entity.setLeftArmRaised(false);
      entity.setStrikingGround(false);
      this.finished = true;
    } 
    this.tick++;
  }
  
  public void earthquake(EntityLivingBase entity, int count) {
    for (float i = 0.5F; i < 3.0F; i++) {
      earthquake(entity, i, 230.0F, count);
      earthquake(entity, i, 270.0F, count);
      earthquake(entity, i, 315.0F, count);
      earthquake(entity, i, 360.0F, count);
      earthquake(entity, i, 45.0F, count);
      earthquake(entity, i, 90.0F, count);
      earthquake(entity, i, 135.0F, count);
      earthquake(entity, i, 180.0F, count);
    } 
  }
  
  public void earthquake(EntityLivingBase entity, float i, float rotationYaw, int count) {
    float damage = 2.14748365E9F;
    double x = (count - 6) * Math.cos(Math.toRadians((i * 6.0F + rotationYaw + 90.0F))) + entity.field_70165_t;
    double z = (count - 6) * Math.sin(Math.toRadians((i * 6.0F + rotationYaw + 90.0F))) + entity.field_70161_v;
    BlockPos pos = new BlockPos(x, entity.field_70163_u, z);
    EntityCustomFallingBlock falling = new EntityCustomFallingBlock(entity.field_70170_p, (Entity)entity, x, entity.field_70163_u - 1.0D, z, 0.20000000298023224D, i * 6.0F + rotationYaw + 90.0F, pos, 2147483647);
    if (!entity.field_70170_p.field_72995_K)
      entity.field_70170_p.func_72838_d((Entity)falling); 
  }
  
  public int getDuration() {
    return 50;
  }
  
  public int getChance(EntityMinerBoss entity) {
    int state = entity.getState();
    return (state == 0) ? 0 : ((state == 1) ? 0 : ((state == 2) ? 0 : ((state == 3) ? 40 : ((state == 4) ? 32 : ((state == 5) ? 35 : 38)))));
  }
  
  public boolean isAvailable(EntityMinerBoss entity) {
    int state = entity.getState();
    return (state > 2);
  }
  
  public boolean isFinished(EntityMinerBoss entity) {
    return this.finished;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\entity\attack\EntityMinerBossLeftShockWaveAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */