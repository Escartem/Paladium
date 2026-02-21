package fr.paladium.palamod.modules.luckyblock.pathfinding;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;

public class Navigator {
  EntityCreature host;
  
  public ChunkCoordinates targetPosition;
  
  public double flyingSpeed = 1.0D;
  
  public boolean faceMovement = true;
  
  public double speedModifier = 1.0D;
  
  private double randomStrafeAngle;
  
  public Navigator setSpeed(double setSpeed) {
    this.flyingSpeed = setSpeed;
    return this;
  }
  
  public Navigator setFacing(boolean facing) {
    this.faceMovement = facing;
    return this;
  }
  
  public boolean setTargetPosition(ChunkCoordinates targetPosition, double setSpeedMod) {
    if (isTargetPositionValid(targetPosition)) {
      this.targetPosition = targetPosition;
      this.speedModifier = setSpeedMod;
      return true;
    } 
    return false;
  }
  
  public boolean setTargetPosition(Entity targetEntity, double setSpeedMod) {
    return setTargetPosition(new ChunkCoordinates((int)targetEntity.field_70165_t, (int)targetEntity.field_70163_u, (int)targetEntity.field_70161_v), setSpeedMod);
  }
  
  public boolean clearTargetPosition(double setSpeedMod) {
    return setTargetPosition((ChunkCoordinates)null, setSpeedMod);
  }
  
  public boolean isTargetPositionValid() {
    return isTargetPositionValid(this.targetPosition);
  }
  
  public boolean isTargetPositionValid(ChunkCoordinates targetPosition) {
    if (targetPosition == null)
      return true; 
    if (this.host.func_70661_as() instanceof PathNavigateSwimmer && 
      isSwimmable(targetPosition.field_71574_a, targetPosition.field_71572_b, targetPosition.field_71573_c))
      return true; 
    if (!this.host.field_70170_p.func_147437_c(targetPosition.field_71574_a, targetPosition.field_71572_b, targetPosition.field_71573_c) && !this.host.field_70145_X)
      return false; 
    if (targetPosition.field_71572_b < 3)
      return false; 
    return true;
  }
  
  public double distanceToTargetPosition() {
    return this.host.func_70011_f(this.targetPosition.field_71574_a, this.targetPosition.field_71572_b, this.targetPosition.field_71573_c);
  }
  
  public boolean atTargetPosition() {
    if (this.targetPosition != null)
      return (distanceToTargetPosition() < (this.host.field_70130_N / 2.0F)); 
    return true;
  }
  
  public Navigator(EntityCreature setHost) {
    this.randomStrafeAngle = 0.0D;
    this.host = setHost;
  }
  
  public void updateFlight() {
    if (this.targetPosition == null)
      return; 
    if (this.randomStrafeAngle <= 0.0D && this.host.func_70681_au().nextDouble() <= 0.25D)
      this.randomStrafeAngle = this.host.func_70681_au().nextBoolean() ? 90.0D : -90.0D; 
    if (this.randomStrafeAngle > 0.0D)
      this.randomStrafeAngle -= 0.5D; 
    double[] coords = getFacingPosition(this.targetPosition.field_71574_a + 0.5D, this.targetPosition.field_71572_b, this.targetPosition.field_71573_c + 0.5D, 1.0D, this.randomStrafeAngle);
    double dirX = coords[0] - this.host.field_70165_t;
    double dirY = this.targetPosition.field_71572_b + 0.1D - this.host.field_70163_u;
    double dirZ = coords[2] - this.host.field_70161_v;
    double speed = this.host.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e() * 2.0D;
    this.host.field_70159_w += (
      Math.signum(dirX) * speed - this.host.field_70159_w) * 0.10000000149011612D * 0.3D * this.speedModifier;
    this.host.field_70181_x += (
      Math.signum(dirY) * speed - this.host.field_70181_x) * 0.10000000149011612D * 0.3D * this.speedModifier;
    this.host.field_70179_y += (
      Math.signum(dirZ) * speed - this.host.field_70179_y) * 0.10000000149011612D * 0.3D * this.speedModifier;
    float fullAngle = (float)(Math.atan2(this.host.field_70179_y, this.host.field_70159_w) * 180.0D / Math.PI) - 90.0F;
    float angle = MathHelper.func_76142_g(fullAngle - this.host.field_70177_z);
    this.host.field_70701_bs = 0.5F;
    if (this.faceMovement && this.host.func_70638_az() != null && (this.host.field_70159_w > 0.02500000037252903D || this.host.field_70179_y > 0.02500000037252903D))
      this.host.field_70177_z += angle; 
  }
  
  public void flightMovement(float moveStrafe, float moveForward) {
    if (this.host.func_70090_H() && !(this.host.func_70661_as() instanceof PathNavigateSwimmer)) {
      this.host.func_70060_a(moveStrafe, moveForward, 0.02F);
      this.host.func_70091_d(this.host.field_70159_w, this.host.field_70181_x, this.host.field_70179_y);
      this.host.field_70159_w *= 0.800000011920929D;
      this.host.field_70181_x *= 0.800000011920929D;
      this.host.field_70179_y *= 0.800000011920929D;
    } else if (this.host.func_70058_J() && 
      !(this.host.func_70661_as() instanceof PathNavigateSwimmer)) {
      this.host.func_70060_a(moveStrafe, moveForward, 0.02F);
      this.host.func_70091_d(this.host.field_70159_w, this.host.field_70181_x, this.host.field_70179_y);
      this.host.field_70159_w *= 0.5D;
      this.host.field_70181_x *= 0.5D;
      this.host.field_70179_y *= 0.5D;
    } else {
      float motion = 0.91F;
      if (this.host.field_70122_E) {
        motion = 0.54600006F;
        Block block = this.host.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.host.field_70165_t), 
            MathHelper.func_76128_c(this.host.field_70121_D.field_72338_b) - 1, 
            MathHelper.func_76128_c(this.host.field_70161_v));
        if (block != null)
          motion = block.field_149765_K * 0.91F; 
      } 
      float flyingMotion = 0.16277136F / motion * motion * motion;
      this.host.func_70060_a(moveStrafe, moveForward, this.host.field_70122_E ? (0.1F * flyingMotion) : (float)(0.019999999552965164D * this.speedModifier));
      motion = 0.91F;
      if (this.host.field_70122_E) {
        motion = 0.54600006F;
        Block block = this.host.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.host.field_70165_t), 
            MathHelper.func_76128_c(this.host.field_70121_D.field_72338_b) - 1, 
            MathHelper.func_76128_c(this.host.field_70161_v));
        if (block != null)
          motion = block.field_149765_K * 0.91F; 
      } 
      if (this.host != null && this.host.field_70121_D != null)
        this.host.func_70091_d(this.host.field_70159_w, this.host.field_70181_x, this.host.field_70179_y); 
      this.host.field_70159_w *= motion;
      this.host.field_70181_x *= motion;
      this.host.field_70179_y *= motion;
    } 
    this.host.field_70722_aY = this.host.field_70721_aZ;
    double deltaX = this.host.field_70165_t - this.host.field_70169_q;
    double deltaZ = this.host.field_70161_v - this.host.field_70166_s;
    float var7 = MathHelper.func_76133_a(deltaX * deltaX + deltaZ * deltaZ) * 4.0F;
    if (var7 > 1.0F)
      var7 = 1.0F; 
    this.host.field_70721_aZ += (var7 - this.host.field_70721_aZ) * 0.4F;
    this.host.field_70754_ba += this.host.field_70721_aZ;
  }
  
  protected void adjustRotationToWaypoint() {
    double distX = this.targetPosition.field_71574_a - this.host.field_70165_t;
    double distZ = this.targetPosition.field_71573_c - this.host.field_70161_v;
    float fullAngle = (float)(Math.atan2(distZ, distX) * 180.0D / Math.PI);
    float angle = MathHelper.func_76142_g(fullAngle - this.host.field_70177_z);
    if (angle > 30.0F)
      angle = 30.0F; 
    if (angle < -30.0F)
      angle = -30.0F; 
    this.host.field_70761_aq = this.host.field_70177_z += angle;
  }
  
  public void adjustRotationToTarget(ChunkCoordinates target) {
    double distX = target.field_71574_a - this.host.field_70165_t;
    double distZ = target.field_71573_c - this.host.field_70161_v;
    float fullAngle = (float)(Math.atan2(distZ, distX) * 180.0D / Math.PI) - 90.0F;
    float angle = MathHelper.func_76142_g(fullAngle - this.host.field_70177_z);
    this.host.field_70177_z += angle;
  }
  
  public double[] getFacingPosition(double distance) {
    return getFacingPosition((Entity)this.host, distance, 0.0D);
  }
  
  public double[] getFacingPosition(Entity entity, double distance, double angleOffset) {
    return getFacingPosition(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, distance, 
        Math.toRadians(entity.field_70177_z) + angleOffset);
  }
  
  public double[] getFacingPosition(double x, double y, double z, double distance, double angle) {
    double xAmount = -Math.sin(angle);
    double zAmount = Math.cos(angle);
    double[] coords = new double[3];
    coords[0] = x + distance * xAmount;
    coords[1] = y;
    coords[2] = z + distance * zAmount;
    return coords;
  }
  
  public boolean isSwimmable(int x, int y, int z) {
    Block block = this.host.field_70170_p.func_147439_a(x, y, z);
    if (block == null)
      return false; 
    if (Material.field_151586_h.equals(block.func_149688_o()))
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\PathNavigatePala$Navigator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */