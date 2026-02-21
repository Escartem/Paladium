package fr.paladium.palaforgeutils.lib.projectile;

import java.util.function.Supplier;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3d;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ProjectileHandler implements IProjectile {
  private Entity projectileEntity;
  
  private World world;
  
  private Supplier<Boolean> preCallback;
  
  private Runnable postCallback;
  
  public Entity getProjectileEntity() {
    return this.projectileEntity;
  }
  
  public World getWorld() {
    return this.world;
  }
  
  public Supplier<Boolean> getPreCallback() {
    return this.preCallback;
  }
  
  public Runnable getPostCallback() {
    return this.postCallback;
  }
  
  public ProjectileHandler(Class<? extends Entity> projectileClazz, World world) {
    try {
      this.projectileEntity = projectileClazz.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
    } catch (Exception e) {
      this.projectileEntity = null;
      e.printStackTrace();
    } 
    this.world = world;
  }
  
  public ProjectileHandler(Entity projectileEntity, World world) {
    this.projectileEntity = projectileEntity;
    this.world = world;
  }
  
  public void throwProjectile(Vector3d from, Vector3d motion, Vector2f rotation, double force, double precision) {
    if (!onPreThrow())
      return; 
    if (this.projectileEntity == null) {
      System.out.println("[ProjectileHandler] Projectile entity is null.");
      return;
    } 
    if (this.projectileEntity instanceof IProjectile && 
      !((IProjectile)this.projectileEntity).onPreThrow())
      return; 
    motion.x *= Math.max(0.0D, force);
    motion.z *= Math.max(0.0D, force);
    motion.x += (this.world.field_73012_v.nextDouble() - 0.5D) * Math.min(1.0D, Math.max(0.0D, 1.0D - precision));
    motion.y += (this.world.field_73012_v.nextDouble() - 0.5D) * Math.min(1.0D, Math.max(0.0D, 1.0D - precision));
    motion.z += (this.world.field_73012_v.nextDouble() - 0.5D) * Math.min(1.0D, Math.max(0.0D, 1.0D - precision));
    this.projectileEntity.func_70080_a(from.x, from.y, from.z, rotation.x, rotation.y);
    this.projectileEntity.func_70024_g(motion.x, motion.y, motion.z);
    this.world.func_72838_d(this.projectileEntity);
    onPostThrow();
    if (this.projectileEntity instanceof IProjectile)
      ((IProjectile)this.projectileEntity).onPostThrow(); 
  }
  
  public void throwProjectile(Entity caster, Entity target, double force, double precision) {
    double projectileY = caster.field_70163_u + caster.func_70047_e() - 0.10000000149011612D;
    double d0 = target.field_70165_t - caster.field_70165_t;
    double d1 = target.field_70121_D.field_72338_b + (target.field_70131_O / 3.0F) - projectileY;
    double d2 = target.field_70161_v - caster.field_70161_v;
    double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
    if (d3 < 1.0E-7D)
      return; 
    float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
    float f3 = (float)-(Math.atan2(d1, d3) * 180.0D / Math.PI);
    double d4 = d0 / d3;
    double d5 = d2 / d3;
    double projectileX = caster.field_70165_t + d4;
    double projectileZ = caster.field_70161_v + d5;
    double motionX = (-MathHelper.func_76126_a(f2 / 180.0F * 3.1415927F) * MathHelper.func_76134_b(f3 / 180.0F * 3.1415927F));
    double motionZ = (MathHelper.func_76134_b(f2 / 180.0F * 3.1415927F) * MathHelper.func_76134_b(f3 / 180.0F * 3.1415927F));
    double motionY = -MathHelper.func_76126_a(f3 / 180.0F * 3.1415927F);
    caster.field_70177_z = f2;
    caster.field_70125_A = f3;
    float offset = caster.field_70130_N / 2.0F + 0.2F;
    double xOffset = -Math.sin(Math.toRadians(f2)) * Math.cos(Math.toRadians(f3)) * offset;
    double zOffset = Math.cos(Math.toRadians(f2)) * Math.cos(Math.toRadians(f3)) * offset;
    throwProjectile(new Vector3d(projectileX + xOffset, projectileY, projectileZ + zOffset), new Vector3d(motionX, motionY, motionZ), new Vector2f(f2, f3), force, precision);
  }
  
  public ProjectileHandler setProjectileClazz(Class<? extends Entity> projectileClazz) {
    try {
      this.projectileEntity = projectileClazz.getConstructor(new Class[] { World.class }).newInstance(new Object[] { this.world });
    } catch (Exception e) {
      this.projectileEntity = null;
      e.printStackTrace();
    } 
    return this;
  }
  
  public ProjectileHandler setProjectileEntity(Entity projectileEntity) {
    this.projectileEntity = projectileEntity;
    return this;
  }
  
  public ProjectileHandler setWorld(World world) {
    this.world = world;
    return this;
  }
  
  @NonNull
  public boolean onPreThrow() {
    return (this.preCallback == null) ? true : ((Boolean)this.preCallback.get()).booleanValue();
  }
  
  public void onPostThrow() {
    if (this.postCallback != null)
      this.postCallback.run(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\projectile\ProjectileHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */