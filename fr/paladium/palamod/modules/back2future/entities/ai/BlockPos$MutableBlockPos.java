package fr.paladium.palamod.modules.back2future.entities.ai;

public final class MutableBlockPos extends BlockPos {
  public int x;
  
  public int y;
  
  public int z;
  
  private static final String __OBFID = "CL_00002329";
  
  private MutableBlockPos(int x_, int y_, int z_) {
    super(0, 0, 0);
    this.x = x_;
    this.y = y_;
    this.z = z_;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public Vec3i crossProduct(Vec3i vec) {
    return crossProductBP(vec);
  }
  
  MutableBlockPos(int p_i46025_1_, int p_i46025_2_, int p_i46025_3_, Object p_i46025_4_) {
    this(p_i46025_1_, p_i46025_2_, p_i46025_3_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\ai\BlockPos$MutableBlockPos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */