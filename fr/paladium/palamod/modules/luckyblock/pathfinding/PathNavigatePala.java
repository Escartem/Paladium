package fr.paladium.palamod.modules.luckyblock.pathfinding;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;

public abstract class PathNavigatePala extends PathNavigate {
  protected EntityLiving field_75515_a;
  
  protected World field_75513_b;
  
  protected PathEntity field_75514_c;
  
  protected double field_75511_d;
  
  private final IAttributeInstance pathSearchRange;
  
  private int totalTicks;
  
  private int ticksAtLastPos;
  
  private Vec3 lastPosCheck = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
  
  private float field_179682_i = 1.0F;
  
  private final PathFinderMoC pathfinder;
  
  protected NodeProcessor nodeProcessor;
  
  protected Navigator navigation;
  
  private boolean NavigationState;
  
  public PathNavigatePala(EntityLiving entityIn, World worldIn) {
    super(entityIn, worldIn);
    this.field_75515_a = entityIn;
    this.field_75513_b = worldIn;
    this.pathSearchRange = entityIn.func_110148_a(SharedMonsterAttributes.field_111265_b);
    this.pathfinder = getPathFinder();
    this.navigation = new Navigator((EntityCreature)entityIn);
  }
  
  protected abstract PathFinderMoC getPathFinder();
  
  public void func_75489_a(double speedIn) {
    this.field_75511_d = speedIn;
  }
  
  public float func_111269_d() {
    return (float)this.pathSearchRange.func_111126_e();
  }
  
  public final PathEntity func_75488_a(double x, double y, double z) {
    return getPathToPos(new BlockPos(
          MathHelper.func_76128_c(x), (int)y, MathHelper.func_76128_c(z)));
  }
  
  public PathEntity getPathToPos(BlockPos pos) {
    if (!func_75485_k())
      return null; 
    float f = func_111269_d();
    this.field_75513_b.field_72984_F.func_76320_a("pathfind");
    BlockPos blockpos1 = new BlockPos((Entity)this.field_75515_a);
    int i = (int)(f + 8.0F);
    ChunkCache chunkcache = new ChunkCache(this.field_75513_b, blockpos1.add(-i, -i, -i).getX(), blockpos1.add(-i, -i, -i).getY(), blockpos1.add(-i, -i, -i).getZ(), blockpos1.add(i, i, i).getX(), blockpos1.add(i, i, i).getY(), blockpos1.add(i, i, i).getZ(), 0);
    PathEntity pathentity = this.pathfinder.createEntityPathTo((IBlockAccess)chunkcache, (Entity)this.field_75515_a, pos, f);
    this.field_75513_b.field_72984_F.func_76319_b();
    return pathentity;
  }
  
  public boolean func_75492_a(double x, double y, double z, double speedIn) {
    PathEntity pathentity = func_75488_a(MathHelper.func_76128_c(x), (int)y, 
        MathHelper.func_76128_c(z));
    return func_75484_a(pathentity, speedIn);
  }
  
  public void func_179678_a(float p_179678_1_) {
    this.field_179682_i = p_179678_1_;
  }
  
  public PathEntity func_75494_a(Entity entityIn) {
    if (!func_75485_k())
      return null; 
    float f = func_111269_d();
    this.field_75513_b.field_72984_F.func_76320_a("pathfind");
    BlockPos blockpos = (new BlockPos((Entity)this.field_75515_a)).up();
    int i = (int)(f + 16.0F);
    ChunkCache chunkcache = new ChunkCache(this.field_75513_b, blockpos.add(-i, -i, -i).getX(), blockpos.add(-i, -i, -i).getY(), blockpos.add(-i, -i, -i).getZ(), blockpos.add(i, i, i).getX(), blockpos.add(i, i, i).getY(), blockpos.add(i, i, i).getZ(), 0);
    PathEntity pathentity = this.pathfinder.createEntityPathTo((IBlockAccess)chunkcache, (Entity)this.field_75515_a, entityIn, f);
    this.field_75513_b.field_72984_F.func_76319_b();
    return pathentity;
  }
  
  public boolean func_75497_a(Entity entityIn, double speedIn) {
    PathEntity pathentityIn = func_75494_a(entityIn);
    return (pathentityIn != null) ? func_75484_a(pathentityIn, speedIn) : false;
  }
  
  public boolean func_75484_a(PathEntity pathentityIn, double speedIn) {
    if (pathentityIn == null) {
      this.field_75514_c = null;
      return false;
    } 
    if (!pathentityIn.func_75876_a(this.field_75514_c))
      this.field_75514_c = pathentityIn; 
    func_75487_m();
    if (this.field_75514_c.func_75874_d() == 0)
      return false; 
    this.field_75511_d = speedIn;
    Vec3 vec3 = func_75502_i();
    this.ticksAtLastPos = this.totalTicks;
    this.lastPosCheck = vec3;
    return true;
  }
  
  public PathEntity func_75505_d() {
    return this.field_75514_c;
  }
  
  public void func_75501_e() {
    UpdateNavigationstate();
    if (this.NavigationState)
      this.navigation.updateFlight(); 
    this.totalTicks++;
    if (!func_75500_f()) {
      if (func_75485_k()) {
        func_75508_h();
      } else if (this.field_75514_c != null && this.field_75514_c
        .func_75873_e() < this.field_75514_c.func_75874_d()) {
        Vec3 vec3 = func_75502_i();
        Vec3 vec31 = this.field_75514_c.func_75881_a((Entity)this.field_75515_a, this.field_75514_c
            .func_75873_e());
        if (vec3.field_72448_b > vec31.field_72448_b && !this.field_75515_a.field_70122_E && 
          MathHelper.func_76128_c(vec3.field_72450_a) == MathHelper.func_76128_c(vec31.field_72450_a) && 
          MathHelper.func_76128_c(vec3.field_72449_c) == MathHelper.func_76128_c(vec31.field_72449_c))
          this.field_75514_c.func_75872_c(this.field_75514_c.func_75873_e() + 1); 
      } 
      if (!func_75500_f()) {
        Vec3 vec3 = this.field_75514_c.func_75878_a((Entity)this.field_75515_a);
        if (vec3 != null)
          this.field_75515_a.func_70605_aq().func_75642_a(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c, this.field_75511_d); 
      } 
    } 
  }
  
  protected void func_75508_h() {
    Vec3 vec3 = func_75502_i();
    int i = this.field_75514_c.func_75874_d();
    int j = this.field_75514_c.func_75873_e();
    for (; j < this.field_75514_c
      .func_75874_d(); j++) {
      if ((this.field_75514_c.func_75877_a(j)).field_75837_b != (int)vec3.field_72448_b) {
        i = j;
        break;
      } 
    } 
    float f = this.field_75515_a.field_70130_N * this.field_75515_a.field_70130_N * this.field_179682_i;
    int k;
    for (k = this.field_75514_c.func_75873_e(); k < i; k++) {
      Vec3 vec31 = this.field_75514_c.func_75881_a((Entity)this.field_75515_a, k);
      if (vec3.func_72436_e(vec31) < f)
        this.field_75514_c.func_75872_c(k + 1); 
    } 
    k = MathHelper.func_76123_f(this.field_75515_a.field_70130_N);
    int j1 = (int)this.field_75515_a.field_70131_O + 1;
    int l = k;
    for (int i1 = i - 1; i1 >= this.field_75514_c.func_75873_e(); i1--) {
      if (func_75493_a(vec3, this.field_75514_c
          .func_75881_a((Entity)this.field_75515_a, i1), k, j1, l)) {
        this.field_75514_c.func_75872_c(i1);
        break;
      } 
    } 
    checkForStuck(vec3);
  }
  
  protected void checkForStuck(Vec3 positionVec3) {
    if (this.totalTicks - this.ticksAtLastPos > 100) {
      if (positionVec3.func_72436_e(this.lastPosCheck) < 2.25D)
        func_75499_g(); 
      this.ticksAtLastPos = this.totalTicks;
      this.lastPosCheck = positionVec3;
    } 
  }
  
  public boolean func_75500_f() {
    return (this.field_75514_c == null || this.field_75514_c.func_75879_b());
  }
  
  public void func_75499_g() {
    UpdateNavigationstate();
    if (this.NavigationState) {
      this.navigation.clearTargetPosition(1.0D);
    } else {
      this.field_75514_c = null;
    } 
  }
  
  protected abstract Vec3 func_75502_i();
  
  protected abstract boolean func_75485_k();
  
  protected boolean func_75506_l() {
    if (this.field_75515_a.func_70090_H() || this.field_75515_a.func_70058_J())
      return true; 
    double d0 = this.field_75515_a.field_70163_u + this.field_75515_a.func_70047_e();
    int i = MathHelper.func_76128_c(this.field_75515_a.field_70165_t);
    int j = MathHelper.func_76141_d(MathHelper.func_76128_c(d0));
    int k = MathHelper.func_76128_c(this.field_75515_a.field_70161_v);
    Block block = this.field_75513_b.func_147439_a(i, j, k);
    double filled = 0.0D;
    if (block instanceof IFluidBlock) {
      filled = ((IFluidBlock)block).getFilledPercentage(this.field_75513_b, i, j, k);
    } else {
      return false;
    } 
    if (filled < 0.0D) {
      filled *= -1.0D;
      return (d0 > j + 1.0D - filled);
    } 
    return (d0 < j + filled);
  }
  
  public NodeProcessor getNodeProcessor() {
    return this.nodeProcessor;
  }
  
  protected void func_75487_m() {}
  
  protected abstract boolean func_75493_a(Vec3 paramVec31, Vec3 paramVec32, int paramInt1, int paramInt2, int paramInt3);
  
  public void func_75504_d(boolean p_75504_1_) {
    super.func_75504_d(p_75504_1_);
  }
  
  public void func_75490_c(boolean p_75490_1_) {
    super.func_75490_c(p_75490_1_);
    this.pathfinder.setWoddenDoorAllowed(p_75490_1_);
  }
  
  public void func_75498_b(boolean p_75498_1_) {
    super.func_75498_b(p_75498_1_);
    this.pathfinder.setMovementBlockAllowed(p_75498_1_);
  }
  
  public void func_75491_a(boolean p_75491_1_) {
    super.func_75491_a(p_75491_1_);
    this.pathfinder.setPathingInWater(p_75491_1_);
  }
  
  public void func_75495_e(boolean p_75495_1_) {
    super.func_75495_e(p_75495_1_);
    this.pathfinder.setEntityDrown(p_75495_1_);
  }
  
  public boolean getCanEnterDoors() {
    return this.pathfinder.getWoddenDoorAllowed();
  }
  
  public boolean func_75507_c() {
    return this.pathfinder.getMovementBlockAllowed();
  }
  
  public boolean func_75486_a() {
    return this.pathfinder.getPathingInWater();
  }
  
  public boolean getCanSwim() {
    return this.pathfinder.getEntityDrown();
  }
  
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
  
  public boolean getNavigateState() {
    return false;
  }
  
  public void UpdateNavigationstate() {
    if (this.field_75515_a.func_70661_as() instanceof PathNavigateSwimmer || this.field_75515_a
      .func_70661_as() instanceof PathNavigateFlyer) {
      this.NavigationState = true;
    } else {
      this.NavigationState = false;
    } 
  }
  
  public Navigator getCustomNavigation() {
    return this.navigation;
  }
  
  public boolean atTargetPosition() {
    if (this.navigation.targetPosition != null)
      return (this.navigation.distanceToTargetPosition() < (this.field_75515_a.field_70130_N / 2.0F)); 
    return true;
  }
  
  public boolean isTargetPositionValid() {
    return this.navigation.isTargetPositionValid(this.navigation.targetPosition);
  }
  
  public boolean isTargetPositionValid(ChunkCoordinates targetPosition) {
    return this.navigation.isTargetPositionValid(targetPosition);
  }
  
  public boolean setTargetPosition(ChunkCoordinates targetPosition, double setSpeedMod) {
    if (this.navigation.isTargetPositionValid(targetPosition)) {
      this.navigation.targetPosition = targetPosition;
      this.navigation.speedModifier = setSpeedMod;
      return true;
    } 
    return false;
  }
  
  public boolean setTargetPosition(Entity targetEntity, double setSpeedMod) {
    return setTargetPosition(new ChunkCoordinates((int)targetEntity.field_70165_t, (int)targetEntity.field_70163_u, (int)targetEntity.field_70161_v), setSpeedMod);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\PathNavigatePala.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */