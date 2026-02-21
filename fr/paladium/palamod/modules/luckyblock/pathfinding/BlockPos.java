package fr.paladium.palamod.modules.luckyblock.pathfinding;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

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
  
  public static Iterable<?> getAllInBox(BlockPos from, BlockPos to) {
    final BlockPos blockpos2 = new BlockPos(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
    final BlockPos blockpos3 = new BlockPos(Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));
    return new Iterable() {
        public Iterator<Object> iterator() {
          return (Iterator<Object>)new AbstractIterator<Object>() {
              private BlockPos lastReturned = null;
              
              protected BlockPos computeNext0() {
                if (this.lastReturned == null) {
                  this.lastReturned = blockpos2;
                  return this.lastReturned;
                } 
                if (this.lastReturned.equals(blockpos3))
                  return (BlockPos)endOfData(); 
                int i = this.lastReturned.getX();
                int j = this.lastReturned.getY();
                int k = this.lastReturned.getZ();
                if (i < blockpos3.getX()) {
                  i++;
                } else if (j < blockpos3.getY()) {
                  i = blockpos2.getX();
                  j++;
                } else if (k < blockpos3.getZ()) {
                  i = blockpos2.getX();
                  j = blockpos2.getY();
                  k++;
                } 
                this.lastReturned = new BlockPos(i, j, k);
                return this.lastReturned;
              }
              
              protected Object computeNext() {
                return computeNext0();
              }
            };
        }
      };
  }
  
  public Vec3i crossProduct(Vec3i vec) {
    return crossProductBP(vec);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\pathfinding\BlockPos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */