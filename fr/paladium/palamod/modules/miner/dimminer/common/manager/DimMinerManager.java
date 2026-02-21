package fr.paladium.palamod.modules.miner.dimminer.common.manager;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class DimMinerManager {
  private static final int SECTION_SIZE = 5000;
  
  private static final Map<Integer, DimMinerSection> SECTION_CACHE = new ConcurrentHashMap<>();
  
  private static int currentIndex = 0;
  
  @NonNull
  public static DimMinerSection getSection(int index) {
    if (index < 0)
      throw new IllegalArgumentException("Index cannot be negative"); 
    if (SECTION_CACHE.containsKey(Integer.valueOf(index)))
      return SECTION_CACHE.get(Integer.valueOf(index)); 
    int x = 0;
    int z = 0;
    int direction = 0;
    int distance = 5000;
    int steps = 0;
    int changes = 0;
    for (int i = 1; i <= index; i++) {
      switch (direction) {
        case 0:
          x += 5000;
          break;
        case 1:
          z -= 5000;
          break;
        case 2:
          x -= 5000;
          break;
        case 3:
          z += 5000;
          break;
      } 
      steps++;
      if (steps == distance / 5000) {
        steps = 0;
        direction = (direction + 1) % 4;
        changes++;
        if (changes == 2) {
          changes = 0;
          distance += 5000;
        } 
      } 
    } 
    DimMinerSection section = new DimMinerSection(index, x, z);
    SECTION_CACHE.put(Integer.valueOf(index), section);
    return section;
  }
  
  @NonNull
  public static DimMinerSection generateNextSection() {
    return getSection(currentIndex++);
  }
  
  public static class DimMinerSection {
    private final int index;
    
    private final int centerX;
    
    private final int centerZ;
    
    private final int minX;
    
    private final int minZ;
    
    private final int maxX;
    
    private final int maxZ;
    
    private DoubleLocation center;
    
    public void setCenter(DoubleLocation center) {
      this.center = center;
    }
    
    public int getIndex() {
      return this.index;
    }
    
    public int getCenterX() {
      return this.centerX;
    }
    
    public int getCenterZ() {
      return this.centerZ;
    }
    
    public int getMinX() {
      return this.minX;
    }
    
    public int getMinZ() {
      return this.minZ;
    }
    
    public int getMaxX() {
      return this.maxX;
    }
    
    public int getMaxZ() {
      return this.maxZ;
    }
    
    public DoubleLocation getCenter() {
      return this.center;
    }
    
    public DimMinerSection(int index, int centerX, int centerZ) {
      this.index = index;
      this.centerX = centerX;
      this.centerZ = centerZ;
      this.minX = centerX - 2500;
      this.minZ = centerZ - 2500;
      this.maxX = centerX + 2500;
      this.maxZ = centerZ + 2500;
      this.center = new DoubleLocation(this.centerX, 256.0D, this.centerZ);
    }
    
    public boolean isInside(int x, int z) {
      return (x >= this.minX && x <= this.maxX && z >= this.minZ && z <= this.maxZ);
    }
    
    public boolean isInside(@NonNull Entity entity) {
      if (entity == null)
        throw new NullPointerException("entity is marked non-null but is null"); 
      return isInside(MathHelper.func_76128_c(entity.field_70165_t), MathHelper.func_76128_c(entity.field_70161_v));
    }
    
    public DoubleLocation findSafeTeleportLocation(World world) {
      int maxRadius = 20;
      int x = this.centerX;
      int z = this.centerZ;
      int y = world.func_72825_h(x, z);
      if (isValidGround(world, x, y, z) && isAreaClear(world, x, y, z))
        return new DoubleLocation(x, (y + 1), z); 
      for (int radius = 0; radius <= 20; radius++) {
        for (int dx = -radius; dx <= radius; dx++) {
          for (int dz = -radius; dz <= radius; dz++) {
            int checkX = x + dx;
            int checkZ = z + dz;
            int checkY = world.func_72825_h(checkX, checkZ);
            if (isValidGround(world, checkX, checkY, checkZ) && 
              isAreaClear(world, checkX, checkY, checkZ))
              return new DoubleLocation(checkX, (checkY + 1), checkZ); 
          } 
        } 
      } 
      return null;
    }
    
    private boolean isValidGround(World world, int x, int y, int z) {
      Block ground = world.func_147439_a(x, y - 1, z);
      return (ground.func_149662_c() && !ground.func_149688_o().func_76224_d());
    }
    
    private boolean isAreaClear(World world, int x, int y, int z) {
      for (int dx = -3; dx <= 3; dx++) {
        for (int dz = -3; dz <= 3; dz++) {
          for (int dy = 0; dy <= 5; dy++) {
            if (!world.func_147437_c(x + dx, y + dy, z + dz))
              return false; 
          } 
        } 
      } 
      return true;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\common\manager\DimMinerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */