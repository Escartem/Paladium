package fr.paladium.palawither.common.entity.projectile;

import fr.paladium.palawither.common.wither.base.projectile.EntityWitherProjectile;
import lombok.NonNull;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityBabyWitherProjectile extends EntityWitherProjectile {
  public EntityBabyWitherProjectile(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
  }
  
  public EntityBabyWitherProjectile(@NonNull World world, @NonNull EntityLivingBase shootingEntity, double motionX, double motionY, double motionZ) {
    super(world, shootingEntity, motionX, motionY, motionZ);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (shootingEntity == null)
      throw new NullPointerException("shootingEntity is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\entity\projectile\EntityBabyWitherProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */