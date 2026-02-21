package fr.paladium.palamod.modules.back2future.entities.ai;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.util.com.google.common.collect.AbstractIterator;

public class BlockPos extends Vec3i {
  public static final BlockPos ORIGIN = new BlockPos(0, 0, 0);
  
  private static final int NUM_X_BITS = 26;
  
  private static final int NUM_Z_BITS = 26;
  
  private static final int NUM_Y_BITS = 12;
  
  private static final int Y_SHIFT = 26;
  
  private static final int X_SHIFT = 38;
  
  private static final long X_MASK = 67108863L;
  
  private static final long Y_MASK = 4095L;
  
  private static final long Z_MASK = 67108863L;
  
  public BlockPos(int x, int y, int z) {
    super(x, y, z);
  }
  
  public BlockPos(double x, double y, double z) {
    super(x, y, z);
  }
  
  public BlockPos(Entity source) {
    this(source.field_70165_t, source.field_70163_u, source.field_70161_v);
  }
  
  public BlockPos(Vec3 source) {
    this(source.field_72450_a, source.field_72448_b, source.field_72449_c);
  }
  
  public BlockPos(Vec3i source) {
    this(source.getX(), source.getY(), source.getZ());
  }
  
  public BlockPos setY(double y) {
    return new BlockPos(getX(), y, getZ());
  }
  
  public BlockPos add(double x, double y, double z) {
    return new BlockPos(getX() + x, getY() + y, getZ() + z);
  }
  
  public BlockPos add(int x, int y, int z) {
    return new BlockPos(getX() + x, getY() + y, getZ() + z);
  }
  
  public BlockPos add(Vec3i vec) {
    return new BlockPos(getX() + vec.getX(), getY() + vec.getY(), getZ() + vec.getZ());
  }
  
  public BlockPos multiply(int factor) {
    return new BlockPos(getX() * factor, getY() * factor, getZ() * factor);
  }
  
  public BlockPos up() {
    return up(1);
  }
  
  public BlockPos subtract(Vec3i vec) {
    return new BlockPos(getX() - vec.getX(), getY() - vec.getY(), getZ() - vec.getZ());
  }
  
  public BlockPos up(int n) {
    return offset(EnumFacing.UP, n);
  }
  
  public BlockPos down() {
    return down(1);
  }
  
  public BlockPos down(int n) {
    return offset(EnumFacing.DOWN, n);
  }
  
  public BlockPos north() {
    return north(1);
  }
  
  public BlockPos north(int n) {
    return offset(EnumFacing.NORTH, n);
  }
  
  public BlockPos south() {
    return south(1);
  }
  
  public BlockPos south(int n) {
    return offset(EnumFacing.SOUTH, n);
  }
  
  public BlockPos west() {
    return west(1);
  }
  
  public BlockPos west(int n) {
    return offset(EnumFacing.WEST, n);
  }
  
  public BlockPos east() {
    return east(1);
  }
  
  public BlockPos east(int n) {
    return offset(EnumFacing.EAST, n);
  }
  
  public BlockPos offset(EnumFacing facing) {
    return offset(facing, 1);
  }
  
  public BlockPos offset(EnumFacing facing, int n) {
    return new BlockPos(getX() + facing.func_82601_c() * n, 
        getY() + facing.func_96559_d() * n, getZ() + facing.func_82599_e() * n);
  }
  
  public BlockPos crossProductBP(Vec3i vec) {
    return new BlockPos(getY() * vec.getZ() - getZ() * vec.getY(), 
        getZ() * vec.getX() - getX() * vec.getZ(), getX() * vec.getY() - getY() * vec.getX());
  }
  
  public long toLong() {
    return (getX() & 0x3FFFFFFL) << 38L | (getY() & 0xFFFL) << 26L | (getZ() & 0x3FFFFFFL) << 0L;
  }
  
  public static BlockPos fromLong(long serialized) {
    int j = (int)(serialized << 0L >> 38L);
    int k = (int)(serialized << 26L >> 52L);
    int l = (int)(serialized << 38L >> 38L);
    return new BlockPos(j, k, l);
  }
  
  public Vec3i crossProduct(Vec3i vec) {
    return crossProductBP(vec);
  }
  
  public static Iterable getAllInBox(BlockPos from, BlockPos to) {
    final BlockPos var2 = new BlockPos(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
    final BlockPos var3 = new BlockPos(Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));
    return new Iterable() {
        private static final String __OBFID = "CL_00002333";
        
        public Iterator iterator() {
          return (Iterator)new AbstractIterator() {
              private BlockPos lastReturned = null;
              
              private static final String __OBFID = "CL_00002332";
              
              protected BlockPos computeNext0() {
                if (this.lastReturned == null) {
                  this.lastReturned = var2;
                  return this.lastReturned;
                } 
                if (this.lastReturned.equals(var3))
                  return (BlockPos)endOfData(); 
                int var1 = this.lastReturned.getX();
                int var2x = this.lastReturned.getY();
                int var3x = this.lastReturned.getZ();
                if (var1 < var3.getX()) {
                  var1++;
                } else if (var2x < var3.getY()) {
                  var1 = var2.getX();
                  var2x++;
                } else if (var3x < var3.getZ()) {
                  var1 = var2.getX();
                  var2x = var2.getY();
                  var3x++;
                } 
                this.lastReturned = new BlockPos(var1, var2x, var3x);
                return this.lastReturned;
              }
              
              protected Object computeNext() {
                return computeNext0();
              }
            };
        }
      };
  }
  
  public static Iterable getAllInBoxMutable(BlockPos from, BlockPos to) {
    final BlockPos var2 = new BlockPos(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
    final BlockPos var3 = new BlockPos(Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));
    return new Iterable() {
        private static final String __OBFID = "CL_00002331";
        
        public Iterator iterator() {
          return (Iterator)new AbstractIterator() {
              private BlockPos.MutableBlockPos theBlockPos = null;
              
              private static final String __OBFID = "CL_00002330";
              
              protected BlockPos.MutableBlockPos computeNext0() {
                if (this.theBlockPos == null) {
                  this
                    .theBlockPos = new BlockPos.MutableBlockPos(var2.getX(), var2.getY(), var2.getZ(), null);
                  return this.theBlockPos;
                } 
                if (this.theBlockPos.equals(var3))
                  return (BlockPos.MutableBlockPos)endOfData(); 
                int var1 = this.theBlockPos.getX();
                int var2xx = this.theBlockPos.getY();
                int var3x = this.theBlockPos.getZ();
                if (var1 < var3.getX()) {
                  var1++;
                } else if (var2xx < var3.getY()) {
                  var1 = var2.getX();
                  var2xx++;
                } else if (var3x < var3.getZ()) {
                  var1 = var2.getX();
                  var2xx = var2.getY();
                  var3x++;
                } 
                this.theBlockPos.x = var1;
                this.theBlockPos.y = var2xx;
                this.theBlockPos.z = var3x;
                return this.theBlockPos;
              }
              
              protected Object computeNext() {
                return computeNext0();
              }
            };
        }
      };
  }
  
  public int distance(int x, int y, int z) {
    return (int)Math.sqrt(
        Math.pow((x - getX()), 2.0D) + Math.pow((y - getY()), 2.0D) + Math.pow((z - getZ()), 2.0D));
  }
  
  public int distance(BlockPos pos) {
    return (int)Math.sqrt(
        Math.pow((pos.getX() - getX()), 2.0D) + Math.pow((pos.getY() - getY()), 2.0D) + Math.pow((pos.getZ() - getZ()), 2.0D));
  }
  
  public boolean isEquals(int x, int y, int z) {
    return (getX() == x && getY() == y && getZ() == z);
  }
  
  public static final class MutableBlockPos extends BlockPos {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\ai\BlockPos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */