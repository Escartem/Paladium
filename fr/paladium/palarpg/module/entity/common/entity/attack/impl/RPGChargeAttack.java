package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.DamageSource;

public class RPGChargeAttack extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "CHARGE_ATTACK";
  
  private boolean blockDirection;
  
  private boolean stopOnCollidePlayer;
  
  private double minDistanceToTarget;
  
  private double knockbackStrength;
  
  private double speed;
  
  private boolean executing = false;
  
  private boolean hasCollided = false;
  
  private float yaw;
  
  private double dirX;
  
  private double dirZ;
  
  public RPGChargeAttack(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.blockDirection = ((Boolean)getData("blockDirection", Boolean.valueOf(false))).booleanValue();
    this.stopOnCollidePlayer = ((Boolean)getData("stopOnCollidePlayer", Boolean.valueOf(false))).booleanValue();
    this.minDistanceToTarget = ((Double)getData("minDistanceToTarget", Double.valueOf(1.0D))).doubleValue();
    this.knockbackStrength = ((Double)getData("knockbackStrength", Double.valueOf(0.0D))).doubleValue();
    this.speed = ((Double)getData("speed", Double.valueOf(3.0D))).doubleValue() * 0.1D;
  }
  
  public boolean canPerform() {
    if (!super.canPerform() || ((RPGMobEntity)getEntity()).field_70170_p.field_72995_K || ((RPGMobEntity)getEntity()).func_70638_az() == null || !(((RPGMobEntity)getEntity()).func_70638_az() instanceof EntityPlayer) || ((RPGMobEntity)getEntity()).func_70032_d((Entity)((RPGMobEntity)getEntity()).func_70638_az()) < this.minDistanceToTarget)
      return false; 
    return true;
  }
  
  public void onStart() {
    super.onStart();
    this.executing = true;
    if (this.blockDirection) {
      double dx = (((RPGMobEntity)getEntity()).func_70638_az()).field_70165_t - ((RPGMobEntity)getEntity()).field_70165_t;
      double dz = (((RPGMobEntity)getEntity()).func_70638_az()).field_70161_v - ((RPGMobEntity)getEntity()).field_70161_v;
      this.yaw = (float)(Math.atan2(dz, dx) * 57.29577951308232D) - 90.0F;
      float yawRad = (float)Math.toRadians(this.yaw);
      this.dirX = -Math.sin(yawRad);
      this.dirZ = Math.cos(yawRad);
      ((RPGMobEntity)getEntity()).field_70177_z = this.yaw;
    } 
  }
  
  public void perform() {
    if (!this.hasCollided && this.executing) {
      if (this.executing && (((RPGMobEntity)getEntity()).getAnimated().getCurrentAnimation() == null || !((RPGMobEntity)getEntity()).getAnimated().getCurrentAnimation().getName().equals(getAttack().getAnimation())))
        playAnimation(null); 
      List<EntityPlayer> allPlayers = ((RPGMobEntity)getEntity()).field_70170_p.field_73010_i;
      for (EntityPlayer player : allPlayers) {
        if (((RPGMobEntity)getEntity()).field_70121_D.func_72326_a(player.field_70121_D)) {
          this.hasCollided = true;
          if (this.stopOnCollidePlayer) {
            this.executing = false;
            ((RPGMobEntity)getEntity()).func_70661_as().func_75499_g();
            damage((Entity)player);
            if (this.knockbackStrength > 0.0D) {
              double dx = player.field_70165_t - ((RPGMobEntity)getEntity()).field_70165_t;
              double dy = player.field_70163_u + player.func_70047_e() - ((RPGMobEntity)getEntity()).field_70163_u + ((RPGMobEntity)getEntity()).func_70047_e();
              double dz = player.field_70161_v - ((RPGMobEntity)getEntity()).field_70161_v;
              double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
              if (len > 1.0D) {
                double nx = dx / len;
                double ny = dy / len;
                double nz = dz / len;
                player.field_70160_al = true;
                player.field_70159_w += nx * this.knockbackStrength;
                player.field_70181_x += ny * this.knockbackStrength;
                player.field_70179_y += nz * this.knockbackStrength;
                ((EntityPlayerMP)player).field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)player));
              } 
            } 
          } 
        } 
      } 
      if (this.hasCollided) {
        reset();
        return;
      } 
      if (((RPGMobEntity)getEntity()).field_70123_F) {
        ((RPGMobEntity)getEntity()).func_70097_a(DamageSource.field_76368_d, getDamage());
        reset();
        return;
      } 
      if (!this.blockDirection) {
        float yawRad = (float)Math.toRadians(((RPGMobEntity)getEntity()).field_70177_z);
        this.dirX = -Math.sin(yawRad);
        this.dirZ = Math.cos(yawRad);
      } else {
        ((RPGMobEntity)getEntity()).field_70177_z = this.yaw;
      } 
      ((RPGMobEntity)getEntity()).func_70091_d(this.dirX * this.speed, 0.0D, this.dirZ * this.speed);
    } 
  }
  
  private void reset() {
    this.executing = false;
    this.hasCollided = false;
    ((RPGMobEntity)getEntity()).func_70661_as().func_75499_g();
    ((RPGMobEntity)getEntity()).func_70624_b(null);
  }
  
  public String getID() {
    return "CHARGE_ATTACK";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGChargeAttack(attack, entity);
  }
  
  public RPGChargeAttack() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGChargeAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */