package fr.paladium.palarpg.module.entity.common.entity.attack;

import com.google.gson.JsonElement;
import fr.paladium.palaboss.common.entity.ai.attack.ABaseAttack;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.entity.common.condition.IMolangParserHolder;
import fr.paladium.palarpg.module.entity.common.condition.RPGConditionParser;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.common.entity.source.CustomEntityDamageSource;
import fr.paladium.palarpg.module.entity.server.loader.RPGEntityLoader;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

public abstract class ARPGBaseAttack<T extends RPGMobEntity> extends ABaseAttack<T> {
  private RPGAttack attack;
  
  public String toString() {
    return "ARPGBaseAttack(attack=" + getAttack() + ", executingTick=" + getExecutingTick() + ")";
  }
  
  public RPGAttack getAttack() {
    return this.attack;
  }
  
  private int executingTick = 0;
  
  public int getExecutingTick() {
    return this.executingTick;
  }
  
  public ARPGBaseAttack() {
    super(null, 0, 0, "", 0L, 0.0F);
  }
  
  public ARPGBaseAttack(RPGAttack attack) {
    super(null, attack.getWeight(), attack.getAttackDuration(), attack.getAnimation(), attack.getAnimationDuration(), attack.getDamage());
    this.attack = attack;
  }
  
  public void onStart() {
    super.onStart();
    this.executingTick = 0;
    if (this.attack.isDisableMovement())
      ((RPGMobEntity)getEntity()).clearSpeed(); 
  }
  
  public boolean canPerform() {
    return (getEntity() != null && RPGConditionParser.evaluates((IMolangParserHolder)getEntity(), this.attack.getConditions()));
  }
  
  public void perform() {
    this.executingTick++;
  }
  
  public void onEnd() {
    this.executingTick = 0;
    if (this.attack.isDisableMovement())
      ((RPGMobEntity)getEntity()).resetSpeed(); 
  }
  
  public void damage(Entity target) {
    if (target != null && target.func_70089_S()) {
      if (target instanceof EntityPlayer) {
        EntityPlayer player = (EntityPlayer)target;
        if (player.field_71075_bZ.field_75102_a)
          return; 
      } 
      target.func_70097_a((DamageSource)CustomEntityDamageSource.causeCustomMobDamage((Entity)getEntity()), getDamage());
    } 
  }
  
  public <N> N getData(String dataID, Class<N> clazz) {
    JsonElement elem = (JsonElement)this.attack.getData().get(dataID);
    if (elem == null || elem.isJsonNull())
      return null; 
    if (RPGEntityData.class.isAssignableFrom(clazz) && DungeonWorld.get(((RPGMobEntity)getEntity()).field_70170_p).isPresent())
      return (N)((DungeonWorld)DungeonWorld.get(((RPGMobEntity)getEntity()).field_70170_p).get()).getGson().fromJson(elem, clazz); 
    return (N)RPGEntityLoader.GSON.fromJson(elem, clazz);
  }
  
  public <N> N getData(String dataID, N defaultValue) {
    JsonElement elem = (JsonElement)this.attack.getData().get(dataID);
    if (elem == null || elem.isJsonNull())
      return defaultValue; 
    return (N)RPGEntityLoader.GSON.fromJson(elem, (defaultValue == null) ? Object.class : defaultValue.getClass());
  }
  
  public void throwProjectile(Entity projectile, Entity caster, Entity target, float force, float precision) {
    if (target == null)
      return; 
    if (target instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)target;
      if (!player.func_70089_S() || player.field_71075_bZ.field_75102_a)
        return; 
    } 
    double projectileY = caster.field_70163_u + caster.func_70047_e() - 0.25D;
    double d0 = target.field_70165_t - caster.field_70165_t;
    double d1 = target.field_70121_D.field_72338_b + target.func_70047_e() - projectileY;
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
    Vector3d from = new Vector3d(projectileX, projectileY, projectileZ);
    Vector3d motion = new Vector3d(motionX, motionY, motionZ);
    Vector2f rotation = new Vector2f(f2, f3);
    caster.field_70177_z = f2;
    caster.field_70125_A = f3;
    motion.x *= Math.max(0.0F, force);
    motion.y *= Math.max(0.0F, force);
    motion.z *= Math.max(0.0F, force);
    motion.x += (((RPGMobEntity)getEntity()).field_70170_p.field_73012_v.nextDouble() - 0.5D) * Math.min(1.0F, Math.max(0.0F, 1.0F - precision));
    motion.y += (((RPGMobEntity)getEntity()).field_70170_p.field_73012_v.nextDouble() - 0.5D) * Math.min(1.0F, Math.max(0.0F, 1.0F - precision));
    motion.z += (((RPGMobEntity)getEntity()).field_70170_p.field_73012_v.nextDouble() - 0.5D) * Math.min(1.0F, Math.max(0.0F, 1.0F - precision));
    projectile.func_70080_a(from.x, from.y, from.z, rotation.x, rotation.y);
    projectile.func_70024_g(motion.x, motion.y, motion.z);
    ((RPGMobEntity)getEntity()).field_70170_p.func_72838_d(projectile);
  }
  
  public abstract String getID();
  
  public abstract ARPGBaseAttack<T> copy(RPGAttack paramRPGAttack, RPGMobEntity paramRPGMobEntity);
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\ARPGBaseAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */