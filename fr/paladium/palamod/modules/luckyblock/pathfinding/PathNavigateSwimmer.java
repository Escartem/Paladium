package fr.paladium.palamod.modules.luckyblock.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PathNavigateSwimmer extends PathNavigatePala {
  public PathNavigateSwimmer(EntityLiving entityIn, World worldIn) {
    super(entityIn, worldIn);
  }
  
  protected PathFinderMoC getPathFinder() {
    this.nodeProcessor = new SwimNodeProcessor();
    return new PathFinderMoC(this.nodeProcessor);
  }
  
  protected boolean func_75485_k() {
    return func_75506_l();
  }
  
  protected Vec3 func_75502_i() {
    return Vec3.func_72443_a(this.field_75515_a.field_70165_t, this.field_75515_a.field_70163_u + this.field_75515_a.field_70131_O * 0.5D, this.field_75515_a.field_70161_v);
  }
  
  protected void func_75508_h() {
    Vec3 vec3 = func_75502_i();
    float f = this.field_75515_a.field_70130_N * this.field_75515_a.field_70130_N;
    byte b0 = 6;
    if (vec3.func_72436_e(this.field_75514_c.func_75881_a((Entity)this.field_75515_a, this.field_75514_c
          .func_75873_e())) < f)
      this.field_75514_c.func_75875_a(); 
    int i = Math.min(this.field_75514_c.func_75873_e() + b0, this.field_75514_c
        .func_75874_d() - 1);
    for (; i > this.field_75514_c
      .func_75873_e(); i--) {
      Vec3 vec31 = this.field_75514_c.func_75881_a((Entity)this.field_75515_a, i);
      if (vec31.func_72436_e(vec3) <= 36.0D && 
        func_75493_a(vec3, vec31, 0, 0, 0)) {
        this.field_75514_c.func_75872_c(i);
        break;
      } 
    } 
    checkForStuck(vec3);
  }
  
  protected void func_75487_m() {
    super.func_75487_m();
  }
  
  protected boolean func_75493_a(Vec3 posVec31, Vec3 posVec32, int sizeX, int sizeY, int sizeZ) {
    MovingObjectPosition movingobjectposition = this.field_75513_b.func_147447_a(posVec31, 
        Vec3.func_72443_a(posVec32.field_72450_a, posVec32.field_72448_b + this.field_75515_a.field_70131_O * 0.5D, posVec32.field_72449_c), false, true, false);
    return (movingobjectposition == null || movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.MISS);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\PathNavigateSwimmer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */