package fr.paladium.palaforgeutils.lib.location;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.teleport.TeleportUtils;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.bukkit.util.NumberConversions;

public class DoubleLocation {
  private double x;
  
  private double y;
  
  private double z;
  
  private float yaw;
  
  private float pitch;
  
  public DoubleLocation(double x, double y, double z, float yaw, float pitch) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.yaw = yaw;
    this.pitch = pitch;
  }
  
  public DoubleLocation(double x, double y, double z) {
    this(x, y, z, 0.0F, 0.0F);
  }
  
  public DoubleLocation(@NonNull Entity entity) {
    this(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70177_z, entity.field_70125_A);
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
  }
  
  @SideOnly(Side.SERVER)
  public void teleportServer(@NonNull EntityPlayer entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    TeleportUtils.teleport(entity, this.x, this.y, this.z, this.yaw, this.pitch);
  }
  
  @SideOnly(Side.CLIENT)
  public void moveClient(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    entity.func_70012_b(this.x, this.y, this.z, this.yaw, this.pitch);
  }
  
  public double distance(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return entity.func_70011_f(this.x, this.y, this.z);
  }
  
  public double distance(double x, double y, double z) {
    double d3 = this.x - x;
    double d4 = this.y - y;
    double d5 = this.z - z;
    return MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
  }
  
  public double distance(@NonNull DoubleLocation location) {
    if (location == null)
      throw new NullPointerException("location is marked non-null but is null"); 
    return distance(location.getX(), location.getY(), location.getZ());
  }
  
  public DoubleLocation add(double x, double y, double z) {
    this.x += x;
    this.y += y;
    this.z += z;
    return this;
  }
  
  public DoubleLocation mult(double x, double y, double z) {
    this.x *= x;
    this.y *= y;
    this.z *= z;
    return this;
  }
  
  public DoubleLocation lookAt(double x, double y, double z) {
    Vec3 entityVector3d = Vec3.func_72443_a(this.x, this.y, this.z);
    Vec3 targetVector3d = Vec3.func_72443_a(x, y, z);
    Vec3 directionalVector3d = entityVector3d.func_72444_a(targetVector3d);
    double PI_DOUBLE = 6.283185307179586D;
    double ox = directionalVector3d.field_72450_a;
    double oz = directionalVector3d.field_72449_c;
    if (ox == 0.0D && oz == 0.0D) {
      this.pitch = (directionalVector3d.field_72448_b > 0.0D) ? -90.0F : 90.0F;
      return this;
    } 
    double theta = Math.atan2(-ox, oz);
    this.yaw = (float)Math.toDegrees((theta + 6.283185307179586D) % 6.283185307179586D);
    double ox2 = NumberConversions.square(ox);
    double oz2 = NumberConversions.square(oz);
    double oxz = Math.sqrt(ox2 + oz2);
    this.pitch = (float)Math.toDegrees(Math.atan(-directionalVector3d.field_72448_b / oxz));
    return this;
  }
  
  public DoubleLocation lookAt(@NonNull Entity entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return lookAt(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
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
  
  public int getBlockX() {
    return MathHelper.func_76128_c(this.x);
  }
  
  public int getBlockY() {
    return MathHelper.func_76128_c(this.y);
  }
  
  public int getBlockZ() {
    return MathHelper.func_76128_c(this.z);
  }
  
  public BlockLocation getBlockLocation(World world) {
    return new BlockLocation(world, getBlockX(), getBlockY(), getBlockZ());
  }
  
  public DoubleLocation clone() {
    return new DoubleLocation(this.x, this.y, this.z, this.yaw, this.pitch);
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + Float.floatToIntBits(this.pitch);
    long temp = Double.doubleToLongBits(this.x);
    result = 31 * result + (int)(temp ^ temp >>> 32L);
    temp = Double.doubleToLongBits(this.y);
    result = 31 * result + (int)(temp ^ temp >>> 32L);
    result = 31 * result + Float.floatToIntBits(this.yaw);
    temp = Double.doubleToLongBits(this.z);
    result = 31 * result + (int)(temp ^ temp >>> 32L);
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    DoubleLocation other = (DoubleLocation)obj;
    if (Float.floatToIntBits(this.pitch) != Float.floatToIntBits(other.pitch) || Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x) || Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y) || Float.floatToIntBits(this.yaw) != Float.floatToIntBits(other.yaw))
      return false; 
    if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z))
      return false; 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\location\DoubleLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */