package fr.paladium.palamod.modules.luckyblock.ai;

import fr.paladium.palamod.modules.luckyblock.entity.EntityMobPaladium;
import fr.paladium.palamod.modules.luckyblock.pathfinding.NodeProcessor;
import fr.paladium.palamod.modules.luckyblock.pathfinding.PathNavigatePala;
import fr.paladium.palamod.modules.luckyblock.utils.AITool;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class EntityAIMoverHelperPala extends EntityMoveHelper {
  EntityCreature theCreature;
  
  protected double field_75646_b;
  
  protected double field_75647_c;
  
  protected double field_75644_d;
  
  protected double field_75645_e;
  
  protected float moveForward;
  
  protected float moveStrafe;
  
  protected Action action;
  
  public boolean func_75640_a() {
    return (this.action == Action.MOVE_TO);
  }
  
  public double func_75638_b() {
    return this.field_75645_e;
  }
  
  public void func_75642_a(double x, double y, double z, double speedIn) {
    this.field_75646_b = x;
    this.field_75647_c = y;
    this.field_75644_d = z;
    this.field_75645_e = speedIn;
    this.action = Action.MOVE_TO;
  }
  
  public void strafe(float forward, float strafe) {
    this.action = Action.STRAFE;
    this.moveForward = forward;
    this.moveStrafe = strafe;
    this.field_75645_e = 0.25D;
  }
  
  public void read(EntityAIMoverHelperPala that) {
    this.action = that.action;
    this.field_75646_b = that.field_75646_b;
    this.field_75647_c = that.field_75647_c;
    this.field_75644_d = that.field_75644_d;
    this.field_75645_e = Math.max(that.field_75645_e, 1.0D);
    this.moveForward = that.moveForward;
    this.moveStrafe = that.moveStrafe;
  }
  
  public void onUpdateMoveOnGround() {
    if (this.action == Action.STRAFE) {
      float f = (float)this.theCreature.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e();
      float f2 = (float)this.field_75645_e * f;
      float f3 = this.moveForward;
      float f4 = this.moveStrafe;
      float f5 = MathHelper.func_76129_c(f3 * f3 + f4 * f4);
      if (f5 < 1.0F)
        f5 = 1.0F; 
      f5 = f2 / f5;
      f3 *= f5;
      f4 *= f5;
      float f6 = MathHelper.func_76126_a(this.theCreature.field_70177_z * 0.017453292F);
      float f7 = MathHelper.func_76134_b(this.theCreature.field_70177_z * 0.017453292F);
      float f8 = f3 * f7 - f4 * f6;
      float f9 = f4 * f7 + f3 * f6;
      PathNavigatePala pathnavigate = (PathNavigatePala)this.theCreature.func_70661_as();
      if (pathnavigate != null) {
        NodeProcessor nodeprocessor = pathnavigate.getNodeProcessor();
        if (nodeprocessor != null && nodeprocessor
          .getPathNodeType((IBlockAccess)this.theCreature.field_70170_p, 
            MathHelper.func_76128_c(this.theCreature.field_70165_t + f8), 
            MathHelper.func_76128_c(this.theCreature.field_70163_u), 
            MathHelper.func_76128_c(this.theCreature.field_70161_v + f9)) != 0) {
          this.moveForward = 1.0F;
          this.moveStrafe = 0.0F;
          f2 = f;
        } 
      } 
      this.theCreature.func_70659_e(f2);
      this.theCreature.func_70657_f(this.moveForward);
      this.theCreature.field_70702_br = this.moveStrafe;
      this.action = Action.WAIT;
    } else if (this.action == Action.MOVE_TO) {
      this.action = Action.WAIT;
      double d0 = this.field_75646_b - this.theCreature.field_70165_t;
      double d2 = this.field_75644_d - this.theCreature.field_70161_v;
      double d3 = this.field_75647_c - this.theCreature.field_70163_u;
      double d4 = d0 * d0 + d3 * d3 + d2 * d2;
      if (d4 < 2.500000277905201E-7D) {
        this.theCreature.func_70657_f(0.0F);
        return;
      } 
      float f10 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
      this.theCreature.field_70177_z = func_75639_a(this.theCreature.field_70177_z, f10, 20.0F);
      this.theCreature.func_70659_e(
          (float)(this.field_75645_e * this.theCreature.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e()));
      if (d3 > this.theCreature.field_70138_W && d0 * d0 + d2 * d2 < 
        Math.max(1.0F, this.theCreature.field_70130_N))
        this.theCreature.func_70683_ar().func_75660_a(); 
    } else {
      this.theCreature.func_70657_f(0.0F);
    } 
  }
  
  protected float func_75639_a(float p_75639_1_, float p_75639_2_, float p_75639_3_) {
    float f = MathHelper.func_76142_g(p_75639_2_ - p_75639_1_);
    if (f > p_75639_3_)
      f = p_75639_3_; 
    if (f < -p_75639_3_)
      f = -p_75639_3_; 
    float f2 = p_75639_1_ + f;
    if (f2 < 0.0F) {
      f2 += 360.0F;
    } else if (f2 > 360.0F) {
      f2 -= 360.0F;
    } 
    return f2;
  }
  
  public double getX() {
    return this.field_75646_b;
  }
  
  public double getY() {
    return this.field_75647_c;
  }
  
  public double getZ() {
    return this.field_75644_d;
  }
  
  public EntityAIMoverHelperPala(EntityLiving entityliving) {
    super(entityliving);
    this.action = Action.WAIT;
    this.theCreature = (EntityCreature)entityliving;
    this.field_75646_b = entityliving.field_70165_t;
    this.field_75647_c = entityliving.field_70163_u;
    this.field_75644_d = entityliving.field_70161_v;
  }
  
  public void func_75641_c() {
    boolean isFlyer = ((EntityMobPaladium)this.theCreature).isFlyer();
    boolean isSwimmer = this.theCreature.func_70090_H();
    float fLimitAngle = 90.0F;
    if (!isFlyer && !isSwimmer) {
      onUpdateMoveOnGround();
      return;
    } 
    if (isFlyer && this.theCreature.field_70153_n == null)
      flyingMovementUpdate(); 
    if (isSwimmer) {
      swimmerMovementUpdate();
      fLimitAngle = 30.0F;
    } 
    if (this.action == Action.MOVE_TO && 
      !this.theCreature.func_70661_as().func_75500_f()) {
      double d0 = this.field_75646_b - this.theCreature.field_70165_t;
      double d2 = this.field_75647_c - this.theCreature.field_70163_u;
      double d3 = this.field_75644_d - this.theCreature.field_70161_v;
      double d4 = d0 * d0 + d2 * d2 + d3 * d3;
      d4 = MathHelper.func_76133_a(d4);
      if (d4 < 0.5D) {
        this.theCreature.func_70657_f(0.0F);
        this.theCreature.func_70661_as().func_75499_g();
        return;
      } 
      d2 /= d4;
      float f = (float)(Math.atan2(d3, d0) * 180.0D / Math.PI) - 90.0F;
      this.theCreature.field_70177_z = func_75639_a(this.theCreature.field_70177_z, f, fLimitAngle);
      this.theCreature.field_70761_aq = this.theCreature.field_70177_z;
      float f2 = (float)(this.field_75645_e * this.theCreature.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
      this.theCreature.func_70659_e(this.theCreature
          .func_70689_ay() + (f2 - this.theCreature.func_70689_ay()) * 0.125F);
      double d5 = Math.sin((this.theCreature.field_70173_aa + this.theCreature.func_145782_y()) * 0.75D) * 0.01D;
      double d6 = Math.cos(this.theCreature.field_70177_z * Math.PI / 180.0D);
      double d7 = Math.sin(this.theCreature.field_70177_z * Math.PI / 180.0D);
      this.theCreature.field_70159_w += d5 * d6;
      this.theCreature.field_70179_y += d5 * d7;
      this.theCreature.field_70181_x += d5 * (d7 + d6) * 0.25D;
      this.theCreature.field_70181_x += this.theCreature.func_70689_ay() * d2 * 1.5D;
    } 
  }
  
  private void flyingMovementUpdate() {
    if (((EntityMobPaladium)this.theCreature).getIsFlying()) {
      int distY = AITool.distanceToFloor((Entity)this.theCreature);
      if (distY <= ((EntityMobPaladium)this.theCreature).minFlyingHeight() && (this.theCreature.field_70123_F || this.theCreature.field_70170_p.field_73012_v
        
        .nextInt(100) == 0)) {
        EntityCreature theCreature = this.theCreature;
        theCreature.field_70181_x += 0.02D;
      } 
      if (distY > ((EntityMobPaladium)this.theCreature).maxFlyingHeight() || this.theCreature.field_70170_p.field_73012_v
        .nextInt(150) == 0) {
        EntityCreature theCreature2 = this.theCreature;
        theCreature2.field_70181_x -= 0.02D;
      } 
    } else if (this.theCreature.field_70181_x < 0.0D) {
      EntityCreature theCreature3 = this.theCreature;
      theCreature3.field_70181_x *= 0.6D;
    } 
  }
  
  private void swimmerMovementUpdate() {
    if (this.theCreature.field_70153_n != null)
      return; 
    double distToSurface = AITool.waterSurfaceAtGivenEntity((Entity)this.theCreature) - this.theCreature.field_70163_u;
    if (distToSurface > ((EntityMobPaladium)this.theCreature).getDivingDepth()) {
      if (this.theCreature.field_70181_x < 0.0D)
        this.theCreature.field_70181_x = 0.0D; 
      EntityCreature theCreature = this.theCreature;
      theCreature.field_70181_x += 0.001D;
      EntityCreature theCreature2 = this.theCreature;
      theCreature2.field_70181_x += distToSurface * 0.01D;
    } 
    if (!this.theCreature.func_70661_as().func_75500_f() && this.theCreature.field_70123_F)
      ((EntityMobPaladium)this.theCreature).forceEntityJump(); 
    if (this.theCreature.func_70638_az() != null && 
      (this.theCreature.func_70638_az()).field_70163_u < this.field_75647_c - 0.5D && this.theCreature
      .func_70032_d((Entity)this.theCreature.func_70638_az()) < 10.0F && 
      this.theCreature.field_70181_x < -0.1D)
      this.theCreature.field_70181_x = -0.1D; 
  }
  
  public enum Action {
    WAIT, MOVE_TO, STRAFE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\ai\EntityAIMoverHelperPala.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */