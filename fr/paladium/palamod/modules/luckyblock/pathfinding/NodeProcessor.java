package fr.paladium.palamod.modules.luckyblock.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public abstract class NodeProcessor {
  protected IBlockAccess blockaccess;
  
  protected Entity entity;
  
  protected IntHashMap pointMap = new IntHashMap();
  
  protected int entitySizeX;
  
  protected int entitySizeY;
  
  protected int entitySizeZ;
  
  protected boolean isWoddenDoorAllowed = true;
  
  protected boolean isMovementBlockAllowed = false;
  
  protected boolean isPathingInWater = false;
  
  protected boolean canEntityDrown = true;
  
  public void init(IBlockAccess sourceIn, Entity mob) {
    this.blockaccess = sourceIn;
    this.pointMap.func_76046_c();
    this.entity = mob;
    this.entitySizeX = MathHelper.func_76141_d(mob.field_70130_N + 1.0F);
    this.entitySizeY = MathHelper.func_76141_d(mob.field_70131_O + 1.0F);
    this.entitySizeZ = MathHelper.func_76141_d(mob.field_70130_N + 1.0F);
  }
  
  public void postProcess() {
    this.blockaccess = null;
    this.entity = null;
  }
  
  protected PathPointEx openPoint(int x, int y, int z) {
    int l = PathPointEx.func_75830_a(x, y, z);
    PathPointEx pathpoint = (PathPointEx)this.pointMap.func_76041_a(l);
    if (pathpoint == null) {
      pathpoint = new PathPointEx(x, y, z);
      this.pointMap.func_76038_a(l, pathpoint);
    } 
    return pathpoint;
  }
  
  public abstract PathPointEx getStart();
  
  public abstract PathPointEx getPathPointToCoords(double paramDouble1, double paramDouble2, double paramDouble3);
  
  public abstract int findPathOptions(PathPointEx[] paramArrayOfPathPointEx, PathPointEx paramPathPointEx1, PathPointEx paramPathPointEx2, float paramFloat);
  
  public void setEnterDoors(boolean canEnterDoorsIn) {
    this.isWoddenDoorAllowed = canEnterDoorsIn;
  }
  
  public void setBreakDoors(boolean canBreakDoorsIn) {
    this.isMovementBlockAllowed = canBreakDoorsIn;
  }
  
  public void setCanSwim(boolean canSwimIn) {
    this.canEntityDrown = canSwimIn;
  }
  
  public boolean getCanEnterDoors() {
    return this.isWoddenDoorAllowed;
  }
  
  public boolean getCanBreakDoors() {
    return this.isMovementBlockAllowed;
  }
  
  public boolean getCanSwim() {
    return this.canEntityDrown;
  }
  
  public abstract int getPathNodeType(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, EntityLiving paramEntityLiving, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean1, boolean paramBoolean2);
  
  public abstract int getPathNodeType(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\NodeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */