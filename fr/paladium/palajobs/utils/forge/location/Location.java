package fr.paladium.palajobs.utils.forge.location;

import java.io.Serializable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class Location implements Cloneable, Serializable {
  public static final String NO_WORLD_NAME = "NONE";
  
  private String worldName;
  
  private double x;
  
  private double y;
  
  private double z;
  
  private float yaw;
  
  private float pitch;
  
  public void setWorldName(String worldName) {
    this.worldName = worldName;
  }
  
  public void setX(double x) {
    this.x = x;
  }
  
  public void setY(double y) {
    this.y = y;
  }
  
  public void setZ(double z) {
    this.z = z;
  }
  
  public void setYaw(float yaw) {
    this.yaw = yaw;
  }
  
  public void setPitch(float pitch) {
    this.pitch = pitch;
  }
  
  public String getWorldName() {
    return this.worldName;
  }
  
  public double getX() {
    return this.x;
  }
  
  public double getY() {
    return this.y;
  }
  
  public double getZ() {
    return this.z;
  }
  
  public float getYaw() {
    return this.yaw;
  }
  
  public float getPitch() {
    return this.pitch;
  }
  
  public Location(Entity entity) {
    this(entity.field_70170_p
        .func_72912_H().func_76065_j(), entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70177_z, entity.field_70125_A);
  }
  
  public Location(World world, double x, double y, double z) {
    this(world.func_72912_H().func_76065_j(), x, y, z, 0.0F, 0.0F);
  }
  
  public Location(World world, double x, double y, double z, float yaw, float pitch) {
    this(world.func_72912_H().func_76065_j(), x, y, z, yaw, pitch);
  }
  
  @Deprecated
  public Location(double x, double y, double z) {
    this("NONE", x, y, z, 0.0F, 0.0F);
  }
  
  @Deprecated
  public Location(String worldName, double x, double y, double z, float yaw, float pitch) {
    this.worldName = worldName;
    this.x = x;
    this.y = y;
    this.z = z;
    this.yaw = yaw;
    this.pitch = pitch;
  }
  
  @Deprecated
  public Location(String worldName, double x, double y, double z) {
    this(worldName, x, y, z, 0.0F, 0.0F);
  }
  
  public double getDistance(Location location) {
    return Math.sqrt(Math.pow(location.getX() - this.x, 2.0D) + Math.pow(location.getY() - this.y, 2.0D) + Math.pow(location.getZ() - this.z, 2.0D));
  }
  
  public Double getDistance2D(Location location) {
    return Double.valueOf(Math.sqrt(Math.pow(location.getX() - this.x, 2.0D) + Math.pow(location.getZ() - this.z, 2.0D)));
  }
  
  public Location add(double x, double y, double z) {
    this.x += x;
    this.y += y;
    this.z += z;
    return this;
  }
  
  public boolean contains(Location location) {
    int locX = (int)getX();
    int locY = (int)getY();
    int locZ = (int)getZ();
    int targetX = (int)location.getX();
    int targetY = (int)location.getY();
    int targetZ = (int)location.getZ();
    return (locX == targetX && locY == targetY && locZ == targetZ);
  }
  
  public boolean contains2D(Location location) {
    int locX = (int)getX();
    int locZ = (int)getZ();
    int targetX = (int)location.getX();
    int targetZ = (int)location.getZ();
    return (locX == targetX && locZ == targetZ);
  }
  
  public int getBlockX() {
    return (int)Math.floor(this.x);
  }
  
  public int getBlockY() {
    return (int)Math.floor(this.y);
  }
  
  public int getBlockZ() {
    return (int)Math.floor(this.z);
  }
  
  public Block getBlock() {
    return getWorld().func_147439_a(getBlockX(), getBlockY(), getBlockZ());
  }
  
  public Block getBlock(World world) {
    return world.func_147439_a(getBlockX(), getBlockY(), getBlockZ());
  }
  
  public boolean isSameWorld(Location location) {
    if (getWorldName().equals("NONE") || location.getWorldName().equals("NONE"))
      return true; 
    return this.worldName.equals(location.getWorldName());
  }
  
  public World getWorld() {
    return WorldUtils.getWorld(this.worldName);
  }
  
  public Location clone(double x, double y, double z) {
    Location clone = clone();
    clone.add(x, y, z);
    return clone;
  }
  
  public Location clone() {
    try {
      Location clone = (Location)super.clone();
      return clone;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      throw new AssertionError();
    } 
  }
  
  public boolean equals(Object obj) {
    if (obj == null)
      return false; 
    if (obj == this)
      return true; 
    if (!(obj instanceof Location))
      return false; 
    Location location = (Location)obj;
    return (contains(location) && isSameWorld(location));
  }
  
  public String toString() {
    return "Location [worldName=" + this.worldName + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajob\\utils\forge\location\Location.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */