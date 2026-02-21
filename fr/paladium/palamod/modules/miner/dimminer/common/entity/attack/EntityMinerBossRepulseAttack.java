package fr.paladium.palamod.modules.miner.dimminer.common.entity.attack;

import fr.paladium.palamod.modules.miner.dimminer.common.entity.EntityMinerBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class EntityMinerBossRepulseAttack implements IEntityMinerBossAttack {
  private boolean finished = true;
  
  public void start(EntityMinerBoss entity, EntityLivingBase entityLivingBase) {
    this.finished = false;
  }
  
  public void update(EntityMinerBoss entity, EntityLivingBase target) {
    target.field_70160_al = true;
    float str = 0.70000005F;
    target.field_70159_w += (target.field_70165_t - entity.field_70165_t > 0.0D) ? 0.7000000476837158D : -0.7000000476837158D;
    target.field_70181_x += 0.7000000476837158D;
    target.field_70179_y += (target.field_70161_v - entity.field_70161_v > 0.0D) ? 0.7000000476837158D : -0.7000000476837158D;
    if (target instanceof EntityPlayerMP) {
      EntityPlayerMP p = (EntityPlayerMP)target;
      p.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)target));
    } 
    this.finished = true;
  }
  
  public int getDuration() {
    return 0;
  }
  
  public int getChance(EntityMinerBoss entity) {
    return 100;
  }
  
  public boolean isAvailable(EntityMinerBoss entity) {
    return true;
  }
  
  public boolean isFinished(EntityMinerBoss entity) {
    return this.finished;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\entity\attack\EntityMinerBossRepulseAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */