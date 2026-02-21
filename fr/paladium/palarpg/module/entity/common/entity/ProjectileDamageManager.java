package fr.paladium.palarpg.module.entity.common.entity;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;

public class ProjectileDamageManager {
  private static Cache<Integer, ProjectileDamage> PROJECTILE_CACHE = CacheBuilder.newBuilder().expireAfterWrite(1L, TimeUnit.MINUTES).build();
  
  public static void registerProjectileDamage(Entity projectileEntity, Entity caster, float damage) {
    registerProjectileDamage(projectileEntity.func_145782_y(), caster, damage);
  }
  
  public static void registerProjectileDamage(int projectileId, Entity caster, float damage) {
    PROJECTILE_CACHE.put(Integer.valueOf(projectileId), new ProjectileDamage(damage, caster));
  }
  
  public static void removeProjectileDamage(Entity projectileEntity) {
    removeProjectileDamage(projectileEntity.func_145782_y());
  }
  
  public static void removeProjectileDamage(int projectileId) {
    PROJECTILE_CACHE.invalidate(Integer.valueOf(projectileId));
  }
  
  public static ProjectileDamage getProjectileDamage(Entity projectileEntity) {
    return (ProjectileDamage)PROJECTILE_CACHE.getIfPresent(Integer.valueOf(projectileEntity.func_145782_y()));
  }
  
  public static ProjectileDamage getProjectileDamage(int projectileId) {
    return (ProjectileDamage)PROJECTILE_CACHE.getIfPresent(Integer.valueOf(projectileId));
  }
  
  public static class ProjectileDamage {
    private final float damage;
    
    private final Entity Entity;
    
    public ProjectileDamage(float damage, Entity entity) {
      this.damage = damage;
      this.Entity = entity;
    }
    
    public float getDamage() {
      return this.damage;
    }
    
    public Entity getEntity() {
      return this.Entity;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\ProjectileDamageManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */