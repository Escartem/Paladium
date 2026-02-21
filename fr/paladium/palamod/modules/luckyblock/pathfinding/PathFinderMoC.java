package fr.paladium.palamod.modules.luckyblock.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.world.IBlockAccess;

public class PathFinderMoC extends PathFinder {
  private Path path = new Path();
  
  private PathPointEx[] pathOptions = new PathPointEx[32];
  
  private NodeProcessor nodeprocessor;
  
  public PathFinderMoC(NodeProcessor processor) {
    super(processor.blockaccess, processor.isWoddenDoorAllowed, processor.isMovementBlockAllowed, processor.isPathingInWater, processor.canEntityDrown);
    this.nodeprocessor = processor;
  }
  
  public PathEntity func_75856_a(Entity p_75856_1_, Entity p_75856_2_, float p_75856_3_) {
    return createEntityPathTo((IBlockAccess)p_75856_1_.field_70170_p, p_75856_1_, p_75856_2_, p_75856_3_);
  }
  
  public PathEntity func_75859_a(Entity p_75859_1_, int p_75859_2_, int p_75859_3_, int p_75859_4_, float p_75859_5_) {
    return createEntityPathTo((IBlockAccess)p_75859_1_.field_70170_p, p_75859_1_, (p_75859_2_ + 0.5F), (p_75859_3_ + 0.5F), (p_75859_4_ + 0.5F), p_75859_5_);
  }
  
  public PathEntity createEntityPathTo(IBlockAccess p_176188_1_, Entity p_176188_2_, Entity p_176188_3_, float p_176188_4_) {
    return createEntityPathTo(p_176188_1_, p_176188_2_, p_176188_3_.field_70165_t, p_176188_3_.field_70121_D.field_72338_b, p_176188_3_.field_70161_v, p_176188_4_);
  }
  
  public PathEntity createEntityPathTo(IBlockAccess p_180782_1_, Entity p_180782_2_, BlockPos p_180782_3_, float p_180782_4_) {
    return createEntityPathTo(p_180782_1_, p_180782_2_, (p_180782_3_
        .getX() + 0.5F), (p_180782_3_.getY() + 0.5F), (p_180782_3_
        .getZ() + 0.5F), p_180782_4_);
  }
  
  private PathEntity createEntityPathTo(IBlockAccess p_176189_1_, Entity p_176189_2_, double p_176189_3_, double p_176189_5_, double p_176189_7_, float p_176189_9_) {
    this.path.func_75848_a();
    this.nodeprocessor.init(p_176189_1_, p_176189_2_);
    PathPointEx pathpoint = this.nodeprocessor.getStart();
    PathPointEx pathpoint1 = this.nodeprocessor.getPathPointToCoords(p_176189_3_, p_176189_5_, p_176189_7_);
    PathEntity pathentity = addToPath(p_176189_2_, pathpoint, pathpoint1, p_176189_9_);
    this.nodeprocessor.postProcess();
    return pathentity;
  }
  
  private PathEntity addToPath(Entity mob, PathPointEx startPoint, PathPointEx endPoint, float maxDistance) {
    startPoint.field_75836_e = 0.0F;
    startPoint.field_75833_f = startPoint.func_75832_b(endPoint);
    startPoint.field_75834_g = startPoint.field_75833_f;
    this.path.func_75848_a();
    this.path.func_75849_a(startPoint);
    PathPointEx pathpoint = startPoint;
    while (!this.path.func_75845_e()) {
      PathPointEx pathpoint1 = (PathPointEx)this.path.func_75844_c();
      if (pathpoint1.equals(endPoint))
        return createEntityPath(startPoint, endPoint); 
      if (pathpoint1.func_75832_b(endPoint) < pathpoint.func_75832_b(endPoint))
        pathpoint = pathpoint1; 
      pathpoint1.visited = true;
      int i = this.nodeprocessor.findPathOptions(this.pathOptions, pathpoint1, endPoint, maxDistance);
      for (int j = 0; j < i; j++) {
        PathPointEx pathpoint2 = this.pathOptions[j];
        float f1 = pathpoint1.field_75836_e + pathpoint1.func_75832_b(pathpoint2);
        if (f1 < maxDistance * 2.0F && (
          !pathpoint2.func_75831_a() || f1 < pathpoint2.field_75836_e)) {
          pathpoint2.field_75841_h = pathpoint1;
          pathpoint2.field_75836_e = f1;
          pathpoint2.field_75833_f = pathpoint2.func_75832_b(endPoint);
          if (pathpoint2.func_75831_a()) {
            this.path.func_75850_a(pathpoint2, pathpoint2.field_75836_e + pathpoint2.field_75833_f);
          } else {
            pathpoint2.field_75834_g = pathpoint2.field_75836_e + pathpoint2.field_75833_f;
            this.path.func_75849_a(pathpoint2);
          } 
        } 
      } 
    } 
    if (pathpoint == startPoint)
      return null; 
    return createEntityPath(startPoint, pathpoint);
  }
  
  private PathEntity createEntityPath(PathPointEx start, PathPointEx end) {
    int i = 1;
    PathPointEx pathpoint2;
    for (pathpoint2 = end; pathpoint2.field_75841_h != null; pathpoint2 = pathpoint2.field_75841_h)
      i++; 
    PathPointEx[] apathpoint = new PathPointEx[i];
    pathpoint2 = end;
    i--;
    for (apathpoint[i] = end; pathpoint2.field_75841_h != null; apathpoint[i] = pathpoint2) {
      pathpoint2 = pathpoint2.field_75841_h;
      i--;
    } 
    return new PathEntity((PathPoint[])apathpoint);
  }
  
  public void setWoddenDoorAllowed(boolean flag) {
    this.nodeprocessor.isWoddenDoorAllowed = flag;
  }
  
  public boolean getWoddenDoorAllowed() {
    return this.nodeprocessor.isWoddenDoorAllowed;
  }
  
  public void setMovementBlockAllowed(boolean flag) {
    this.nodeprocessor.isMovementBlockAllowed = flag;
  }
  
  public boolean getMovementBlockAllowed() {
    return this.nodeprocessor.isMovementBlockAllowed;
  }
  
  public void setPathingInWater(boolean flag) {
    this.nodeprocessor.isPathingInWater = flag;
  }
  
  public boolean getPathingInWater() {
    return this.nodeprocessor.isPathingInWater;
  }
  
  public void setEntityDrown(boolean flag) {
    this.nodeprocessor.canEntityDrown = flag;
  }
  
  public boolean getEntityDrown() {
    return this.nodeprocessor.canEntityDrown;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\PathFinderMoC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */