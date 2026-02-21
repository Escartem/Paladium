package fr.paladium.palamod.modules.luckyblock.monthly.utils;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaschematicmod.common.pojo.data.BlockData;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import fr.paladium.palaschematicmod.common.pojo.schematic.Schematic;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class DoubleCuboid implements Cloneable, Comparable<DoubleCuboid>, Serializable {
  private World world;
  
  private double minX;
  
  private double minY;
  
  private double minZ;
  
  private double maxX;
  
  private double maxY;
  
  private double maxZ;
  
  public void setWorld(World world) {
    this.world = world;
  }
  
  public void setMinX(double minX) {
    this.minX = minX;
  }
  
  public void setMinY(double minY) {
    this.minY = minY;
  }
  
  public void setMinZ(double minZ) {
    this.minZ = minZ;
  }
  
  public void setMaxX(double maxX) {
    this.maxX = maxX;
  }
  
  public void setMaxY(double maxY) {
    this.maxY = maxY;
  }
  
  public void setMaxZ(double maxZ) {
    this.maxZ = maxZ;
  }
  
  public World getWorld() {
    return this.world;
  }
  
  public double getMinX() {
    return this.minX;
  }
  
  public double getMinY() {
    return this.minY;
  }
  
  public double getMinZ() {
    return this.minZ;
  }
  
  public double getMaxX() {
    return this.maxX;
  }
  
  public double getMaxY() {
    return this.maxY;
  }
  
  public double getMaxZ() {
    return this.maxZ;
  }
  
  public DoubleCuboid(World world, double x, double y, double z, double radius) {
    this(world, x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
  }
  
  public DoubleCuboid(World world, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
    this(world, new DoubleLocation(minX, minY, minZ), new DoubleLocation(maxX, maxY, maxZ));
  }
  
  public DoubleCuboid(@NonNull World world, @NonNull DoubleLocation minLocation, @NonNull DoubleLocation maxLocation) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (minLocation == null)
      throw new NullPointerException("minLocation is marked non-null but is null"); 
    if (maxLocation == null)
      throw new NullPointerException("maxLocation is marked non-null but is null"); 
    this.world = world;
    this.minX = Math.min(minLocation.getX(), maxLocation.getX());
    this.minY = Math.min(minLocation.getY(), maxLocation.getY());
    this.minZ = Math.min(minLocation.getZ(), maxLocation.getZ());
    this.maxX = Math.max(minLocation.getX(), maxLocation.getX());
    this.maxY = Math.max(minLocation.getY(), maxLocation.getY());
    this.maxZ = Math.max(minLocation.getZ(), maxLocation.getZ());
  }
  
  public DoubleLocation getCenter() {
    return new DoubleLocation((this.minX + this.maxX) / 2.0D, (this.minY + this.maxY) / 2.0D, (this.minZ + this.maxZ) / 2.0D);
  }
  
  public DoubleLocation getMinLocation() {
    return new DoubleLocation(this.minX, this.minY, this.minZ);
  }
  
  public DoubleLocation getMaxLocation() {
    return new DoubleLocation(this.maxX, this.maxY, this.maxZ);
  }
  
  public boolean contains(DoubleLocation location) {
    return (location.getX() >= this.minX && location.getX() <= this.maxX && location
      .getY() >= this.minY && location.getY() <= this.maxY && location
      .getZ() >= this.minZ && location.getZ() <= this.maxZ);
  }
  
  public boolean contains(double x, double y, double z) {
    return (x >= this.minX && x <= this.maxX && y >= this.minY && y <= this.maxY && z >= this.minZ && z <= this.maxZ);
  }
  
  public List<DoubleLocation> getLocations() {
    List<DoubleLocation> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++)
          locations.add(new DoubleLocation(x, y, z)); 
      } 
    } 
    return locations;
  }
  
  public List<DoubleLocation> getTopLocations() {
    List<DoubleLocation> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double z;
      for (z = this.minZ; z <= this.maxZ; z++)
        locations.add(new DoubleLocation(x, this.maxY, z)); 
    } 
    return locations;
  }
  
  public List<DoubleLocation> getBorders() {
    List<DoubleLocation> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (x == this.minX || x == this.maxX || y == this.minY || y == this.maxY || z == this.minZ || z == this.maxZ)
            locations.add(new DoubleLocation(x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<DoubleLocation> getTopBorders() {
    List<DoubleLocation> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (y == this.maxY)
            locations.add(new DoubleLocation(x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<DoubleLocation> getBottomBorders() {
    List<DoubleLocation> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (y == this.minY)
            locations.add(new DoubleLocation(x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<DoubleLocation> getNorthBorders() {
    List<DoubleLocation> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (z == this.minZ)
            locations.add(new DoubleLocation(x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<DoubleLocation> getSouthBorders() {
    List<DoubleLocation> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (z == this.maxZ)
            locations.add(new DoubleLocation(x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<DoubleLocation> getEastBorders() {
    List<DoubleLocation> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (x == this.maxX)
            locations.add(new DoubleLocation(x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<DoubleLocation> getWestBorders() {
    List<DoubleLocation> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (x == this.minX)
            locations.add(new DoubleLocation(x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public <T> List<T> getEntities(Class<T> clazz) {
    List<T> entities = new ArrayList<>();
    for (Object o : this.world.field_72996_f) {
      Entity entity = (Entity)o;
      if (contains(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v) && entity.getClass().isAssignableFrom(clazz))
        entities.add((T)entity); 
    } 
    return entities;
  }
  
  public List<Entity> getEntities() {
    List<Entity> entities = new ArrayList<>();
    for (Object o : this.world.field_72996_f) {
      Entity entity = (Entity)o;
      if (contains(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v))
        entities.add(entity); 
    } 
    return entities;
  }
  
  public List<DoubleLocation> getBlocks(Block block) {
    List<DoubleLocation> blocks = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (this.world.func_147439_a((int)x, (int)y, (int)z) == block)
            blocks.add(new DoubleLocation(x, y, z)); 
        } 
      } 
    } 
    return blocks;
  }
  
  public double getRandomX() {
    return this.minX + (this.maxX - this.minX) * Math.random();
  }
  
  public double getRandomY() {
    return this.minY + (this.maxY - this.minY) * Math.random();
  }
  
  public double getRandomZ() {
    return this.minZ + (this.maxZ - this.minZ) * Math.random();
  }
  
  public double getVolume() {
    return (this.maxX - this.minX) * (this.maxY - this.minY) * (this.maxZ - this.minZ);
  }
  
  public DoubleLocation getRandomLocation() {
    return new DoubleLocation(getRandomX(), getRandomY(), getRandomZ());
  }
  
  public DoubleLocation getMinLocation(double x, double y, double z) {
    return new DoubleLocation(Math.min(this.minX, x), Math.min(this.minY, y), Math.min(this.minZ, z));
  }
  
  public DoubleLocation getMaxLocation(double x, double y, double z) {
    return new DoubleLocation(Math.max(this.maxX, x), Math.max(this.maxY, y), Math.max(this.maxZ, z));
  }
  
  public DoubleCuboid clone() {
    try {
      return (DoubleCuboid)super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      throw new AssertionError();
    } 
  }
  
  public int compareTo(DoubleCuboid cuboid) {
    return Double.compare(getVolume(), cuboid.getVolume());
  }
  
  public boolean equals(Object obj) {
    if (obj == null)
      return false; 
    if (obj == this)
      return true; 
    if (!(obj instanceof DoubleCuboid))
      return false; 
    DoubleCuboid cuboid = (DoubleCuboid)obj;
    return (cuboid.getMinLocation().equals(getMinLocation()) && cuboid.getMaxLocation().equals(getMaxLocation()));
  }
  
  public static DoubleCuboid of(World world, DoubleLocation origin, Schematic schematic) {
    Bounds bounds = new Bounds(origin.getBlockX(), origin.getBlockY(), origin.getBlockZ());
    for (BlockData data : schematic.getBlocks()) {
      BlockPos pos = data.getPos();
      int blockX = pos.getX();
      int blockY = pos.getY();
      int blockZ = pos.getZ();
      bounds.updateBounds(blockX, blockY, blockZ);
    } 
    return bounds.toCuboid(world);
  }
  
  private static class Bounds {
    int minX;
    
    int minY;
    
    int minZ;
    
    int maxX;
    
    int maxY;
    
    int maxZ;
    
    public int getMinX() {
      return this.minX;
    }
    
    public int getMinY() {
      return this.minY;
    }
    
    public int getMinZ() {
      return this.minZ;
    }
    
    public int getMaxX() {
      return this.maxX;
    }
    
    public int getMaxY() {
      return this.maxY;
    }
    
    public int getMaxZ() {
      return this.maxZ;
    }
    
    public Bounds(int x, int y, int z) {
      this.minX = this.maxX = x;
      this.minY = this.maxY = y;
      this.minZ = this.maxZ = z;
    }
    
    public void updateBounds(int x, int y, int z) {
      this.minX = Math.min(this.minX, x);
      this.minY = Math.min(this.minY, y);
      this.minZ = Math.min(this.minZ, z);
      this.maxX = Math.max(this.maxX, x);
      this.maxY = Math.max(this.maxY, y);
      this.maxZ = Math.max(this.maxZ, z);
    }
    
    public DoubleCuboid toCuboid(World world) {
      return new DoubleCuboid(world, this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\DoubleCuboid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */