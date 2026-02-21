package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityBossProjectiles;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityGear;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ThrowProjectileAttack extends DistancedAttack {
  public static final String NAME = "throwProjectile";
  
  private transient AttackParamEntry projectileType;
  
  private transient AttackParamEntry damage;
  
  private transient JsonObject jsonObject;
  
  private Class projectileTypeClass;
  
  public ThrowProjectileAttack(JsonObject jsonObject) {
    super("throwProjectile");
    this.jsonObject = jsonObject;
    registerParam(this
        .projectileType = new AttackParamEntry("projectileTypeClass", EntityGear.class.getName()));
    registerParam(this.damage = new AttackParamEntry("attackDamage", Double.valueOf(10.0D)));
  }
  
  public boolean isRunning() {
    return false;
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    try {
      if (this.projectileTypeClass == null) {
        JsonPrimitive projectilePrimitive = (JsonPrimitive)this.projectileType.getValue();
        this.projectileTypeClass = Class.forName(projectilePrimitive.getAsString());
      } 
      JsonPrimitive damagePrimitive = (JsonPrimitive)this.damage.getValue();
      float damage = damagePrimitive.getAsFloat();
      Entity projectile = null;
      projectile = this.projectileTypeClass.getConstructor(new Class[] { World.class, EntityLivingBase.class, EntityLivingBase.class, Float.class, Float.class }).newInstance(new Object[] { world, entityHost, toAttackEntity, Float.valueOf(1.6F), 
            Float.valueOf((14 - world.field_73013_u.func_151525_a() * 4)) });
      if (projectile instanceof EntityBossProjectiles)
        ((EntityBossProjectiles)projectile).setDamage(damage); 
      world.func_72838_d(projectile);
      toAttackEntity.func_70097_a(DamageSource.field_76376_m, damage);
    } catch (InstantiationException|IllegalAccessException|NoSuchMethodException|ClassNotFoundException|java.lang.reflect.InvocationTargetException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\ThrowProjectileAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */