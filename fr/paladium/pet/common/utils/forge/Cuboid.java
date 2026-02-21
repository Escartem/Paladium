package fr.paladium.pet.common.utils.forge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Cuboid implements Cloneable, Comparable<Cuboid>, Iterable<Location>, Serializable {
  private String worldName;
  
  private double minX;
  
  private double minY;
  
  private double minZ;
  
  private double maxX;
  
  private double maxY;
  
  private double maxZ;
  
  public void setWorldName(String worldName) {
    this.worldName = worldName;
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
  
  public String getWorldName() {
    return this.worldName;
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
  
  public Cuboid(Location minLocation, Location maxLocation) {
    this(minLocation.getX(), minLocation.getY(), minLocation.getZ(), maxLocation.getX(), maxLocation.getY(), maxLocation.getZ());
    if (!minLocation.isSameWorld(maxLocation))
      throw new IllegalArgumentException("Locations must be in the same world!"); 
    if (!"NONE".equals(minLocation.getWorldName())) {
      this.worldName = minLocation.getWorldName();
    } else {
      this.worldName = maxLocation.getWorldName();
    } 
  }
  
  public Cuboid(World world, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
    this(new Location(world, minX, minY, minZ), new Location(world, maxX, maxY, maxZ));
  }
  
  public Cuboid(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
    this.worldName = "NONE";
    this.minX = Math.min(minX, maxX);
    this.minY = Math.min(minY, maxY);
    this.minZ = Math.min(minZ, maxZ);
    this.maxX = Math.max(minX, maxX);
    this.maxY = Math.max(minY, maxY);
    this.maxZ = Math.max(minZ, maxZ);
  }
  
  public Location getCenter() {
    return new Location(this.worldName, (this.minX + this.maxX) / 2.0D, (this.minY + this.maxY) / 2.0D, (this.minZ + this.maxZ) / 2.0D);
  }
  
  public Location getMinLocation() {
    return new Location(this.worldName, this.minX, this.minY, this.minZ);
  }
  
  public Location getMaxLocation() {
    return new Location(this.worldName, this.maxX, this.maxY, this.maxZ);
  }
  
  public boolean contains(Location location) {
    if (!location.isSameWorld(getMinLocation()))
      return false; 
    return (location.getX() >= this.minX && location.getX() <= this.maxX && location
      .getY() >= this.minY && location.getY() <= this.maxY && location
      .getZ() >= this.minZ && location.getZ() <= this.maxZ);
  }
  
  public List<Location> getBorders() {
    List<Location> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (x == this.minX || x == this.maxX || y == this.minY || y == this.maxY || z == this.minZ || z == this.maxZ)
            locations.add(new Location(this.worldName, x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<Location> getTopBorders() {
    List<Location> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (y == this.maxY)
            locations.add(new Location(this.worldName, x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<Location> getBottomBorders() {
    List<Location> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (y == this.minY)
            locations.add(new Location(this.worldName, x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<Location> getNorthBorders() {
    List<Location> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (z == this.minZ)
            locations.add(new Location(this.worldName, x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<Location> getStage(int stage) {
    List<Location> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double z;
      for (z = this.minZ; z <= this.maxZ; z++) {
        double y;
        for (y = this.minY; y <= this.maxY; y++) {
          if (y == stage)
            locations.add(new Location(this.worldName, x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<Location> getSouthBorders() {
    List<Location> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (z == this.maxZ)
            locations.add(new Location(this.worldName, x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<Location> getEastBorders() {
    List<Location> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (x == this.maxX)
            locations.add(new Location(this.worldName, x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<Location> getWestBorders() {
    List<Location> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++) {
          if (x == this.minX)
            locations.add(new Location(this.worldName, x, y, z)); 
        } 
      } 
    } 
    return locations;
  }
  
  public List<Location> getLocations() {
    List<Location> locations = new ArrayList<>();
    for (double x = this.minX; x <= this.maxX; x++) {
      double y;
      for (y = this.minY; y <= this.maxY; y++) {
        double z;
        for (z = this.minZ; z <= this.maxZ; z++)
          locations.add(new Location(this.worldName, x, y, z)); 
      } 
    } 
    return locations;
  }
  
  public List<EntityPlayer> getPlayers() {
    World world = getWorld();
    List<EntityPlayer> players = new ArrayList<>();
    if (world == null)
      return players; 
    for (Object object : (getWorld()).field_73010_i) {
      if (object instanceof EntityPlayer) {
        EntityPlayer player = (EntityPlayer)object;
        Location location = new Location((Entity)player);
        if (contains(location))
          players.add(player); 
      } 
    } 
    return players;
  }
  
  public List<Entity> getEntities() {
    List<Entity> entities = new ArrayList<>();
    World world = getWorld();
    if (world == null)
      return entities; 
    for (Object object : world.field_72996_f) {
      Entity entity = (Entity)object;
      if (object instanceof Entity && contains(new Location(entity)))
        entities.add(entity); 
    } 
    return entities;
  }
  
  public World getWorld() {
    return WorldUtils.getWorld(this.worldName);
  }
  
  public double getVolume() {
    return (this.maxX - this.minX) * (this.maxY - this.minY) * (this.maxZ - this.minZ);
  }
  
  public Cuboid clone() {
    try {
      return (Cuboid)super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      throw new AssertionError();
    } 
  }
  
  public int compareTo(Cuboid cuboid) {
    return Double.compare(getVolume(), cuboid.getVolume());
  }
  
  public Iterator<Location> iterator() {
    return getLocations().iterator();
  }
  
  public void forEach(Consumer<? super Location> action) {
    super.forEach(action);
  }
  
  public Spliterator<Location> spliterator() {
    return super.spliterator();
  }
  
  public String toString() {
    return "Cuboid{worldName='" + this.worldName + '\'' + ", minX=" + this.minX + ", minY=" + this.minY + ", minZ=" + this.minZ + ", maxX=" + this.maxX + ", maxY=" + this.maxY + ", maxZ=" + this.maxZ + '}';
  }
  
  public boolean equals(Object obj) {
    if (obj == null)
      return false; 
    if (obj == this)
      return true; 
    if (!(obj instanceof Cuboid))
      return false; 
    Cuboid cuboid = (Cuboid)obj;
    return (cuboid.getMinLocation().equals(getMinLocation()) && cuboid.getMaxLocation().equals(getMaxLocation()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\commo\\utils\forge\Cuboid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */