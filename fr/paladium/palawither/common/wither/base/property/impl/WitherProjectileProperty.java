package fr.paladium.palawither.common.wither.base.property.impl;

import fr.paladium.palawither.common.wither.base.EntityWitherBase;
import fr.paladium.palawither.common.wither.base.IWither;
import fr.paladium.palawither.common.wither.base.projectile.EntityWitherProjectile;
import fr.paladium.palawither.common.wither.base.property.WitherProperty;
import java.lang.reflect.Constructor;
import lombok.NonNull;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

@WitherProperty("projectile")
public class WitherProjectileProperty {
  public static final String ID = "projectile";
  
  private final Constructor<? extends EntityWitherProjectile> constructor;
  
  private final IEntitySelector entitySelector;
  
  public Constructor<? extends EntityWitherProjectile> getConstructor() {
    return this.constructor;
  }
  
  public IEntitySelector getEntitySelector() {
    return this.entitySelector;
  }
  
  public WitherProjectileProperty(@NonNull Class<? extends EntityWitherProjectile> projectileClass) {
    this(projectileClass, IWither.DEFAULT_ENTITY_SELECTOR);
    if (projectileClass == null)
      throw new NullPointerException("projectileClass is marked non-null but is null"); 
  }
  
  public WitherProjectileProperty(@NonNull Class<? extends EntityWitherProjectile> projectileClass, @NonNull IEntitySelector entitySelector) {
    if (projectileClass == null)
      throw new NullPointerException("projectileClass is marked non-null but is null"); 
    if (entitySelector == null)
      throw new NullPointerException("entitySelector is marked non-null but is null"); 
    try {
      this.constructor = projectileClass.getConstructor(new Class[] { World.class, EntityLivingBase.class, double.class, double.class, double.class });
      this.entitySelector = entitySelector;
    } catch (NoSuchMethodException e) {
      throw new IllegalArgumentException("The projectile class must have a constructor with parameters: World, EntityLivingBase, double, double, double", e);
    } 
  }
  
  public void invoke(@NonNull EntityWitherBase wither, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) throws Exception {
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    EntityWitherProjectile projectile = this.constructor.newInstance(new Object[] { wither.field_70170_p, wither, Double.valueOf(motionX), Double.valueOf(motionY), Double.valueOf(motionZ) });
    if (projectile == null)
      return; 
    projectile.field_70165_t = posX;
    projectile.field_70163_u = posY;
    projectile.field_70161_v = posZ;
    wither.field_70170_p.func_72838_d((Entity)projectile);
    wither.field_70170_p.func_72889_a(null, 1014, (int)posX, (int)posY, (int)posZ, 0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\base\property\impl\WitherProjectileProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */