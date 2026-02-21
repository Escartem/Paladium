package fr.paladium.palawither.common.wither.base.property;

import fr.paladium.palawither.common.wither.base.projectile.EntityWitherProjectile;
import fr.paladium.palawither.common.wither.base.property.impl.WitherArmoredProperty;
import fr.paladium.palawither.common.wither.base.property.impl.WitherBreakBlockProperty;
import fr.paladium.palawither.common.wither.base.property.impl.WitherExplosionProperty;
import fr.paladium.palawither.common.wither.base.property.impl.WitherProjectileProperty;
import java.util.function.Function;
import lombok.NonNull;
import net.minecraft.command.IEntitySelector;
import net.minecraft.util.DamageSource;

public class WitherProperties {
  @NonNull
  public static WitherExplosionProperty explosion(int power, boolean fire, boolean damage) {
    return new WitherExplosionProperty(power, fire, damage);
  }
  
  @NonNull
  public static WitherProjectileProperty projectile(@NonNull Class<? extends EntityWitherProjectile> projectileClass) {
    if (projectileClass == null)
      throw new NullPointerException("projectileClass is marked non-null but is null"); 
    return new WitherProjectileProperty(projectileClass);
  }
  
  @NonNull
  public static WitherProjectileProperty projectile(@NonNull Class<? extends EntityWitherProjectile> projectileClass, @NonNull IEntitySelector entitySelector) {
    if (projectileClass == null)
      throw new NullPointerException("projectileClass is marked non-null but is null"); 
    if (entitySelector == null)
      throw new NullPointerException("entitySelector is marked non-null but is null"); 
    return new WitherProjectileProperty(projectileClass, entitySelector);
  }
  
  @NonNull
  public static WitherArmoredProperty armored() {
    return new WitherArmoredProperty();
  }
  
  @NonNull
  public static WitherBreakBlockProperty breakBlock(int delay) {
    return new WitherBreakBlockProperty(source -> Integer.valueOf(delay));
  }
  
  @NonNull
  public static WitherBreakBlockProperty breakBlock(@NonNull Function<DamageSource, Integer> delayFunction) {
    if (delayFunction == null)
      throw new NullPointerException("delayFunction is marked non-null but is null"); 
    return new WitherBreakBlockProperty(delayFunction);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\base\property\WitherProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */