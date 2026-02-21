package fr.paladium.palarpg.module.entity.server.loader.data.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import fr.paladium.palarpg.module.entity.common.entity.projectile.RPGProjectile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RPGAttackProjectile {
  private final String model;
  
  private final float force;
  
  private final float precision;
  
  private final double airFriction;
  
  private final double groundFriction;
  
  private final double gravity;
  
  private final boolean deathOnCollideEntity;
  
  private final boolean deathOnCollideBlock;
  
  private final boolean stickToBlock;
  
  private final int maxBounces;
  
  private final double bounceRestitution;
  
  private final long lifeSpan;
  
  private final int potionId;
  
  private final int potionAmplifier;
  
  private final int potionDuration;
  
  private final boolean explodeOnDeath;
  
  private final float explosionSize;
  
  public String toString() {
    return "RPGAttack.RPGAttackProjectile(model=" + getModel() + ", force=" + getForce() + ", precision=" + getPrecision() + ", airFriction=" + getAirFriction() + ", groundFriction=" + getGroundFriction() + ", gravity=" + getGravity() + ", deathOnCollideEntity=" + isDeathOnCollideEntity() + ", deathOnCollideBlock=" + isDeathOnCollideBlock() + ", stickToBlock=" + isStickToBlock() + ", maxBounces=" + getMaxBounces() + ", bounceRestitution=" + getBounceRestitution() + ", lifeSpan=" + getLifeSpan() + ", potionId=" + getPotionId() + ", potionAmplifier=" + getPotionAmplifier() + ", potionDuration=" + getPotionDuration() + ", explodeOnDeath=" + isExplodeOnDeath() + ", explosionSize=" + getExplosionSize() + ")";
  }
  
  public RPGAttackProjectile(String model, float force, float precision, double airFriction, double groundFriction, double gravity, boolean deathOnCollideEntity, boolean deathOnCollideBlock, boolean stickToBlock, int maxBounces, double bounceRestitution, long lifeSpan, int potionId, int potionAmplifier, int potionDuration, boolean explodeOnDeath, float explosionSize) {
    this.model = model;
    this.force = force;
    this.precision = precision;
    this.airFriction = airFriction;
    this.groundFriction = groundFriction;
    this.gravity = gravity;
    this.deathOnCollideEntity = deathOnCollideEntity;
    this.deathOnCollideBlock = deathOnCollideBlock;
    this.stickToBlock = stickToBlock;
    this.maxBounces = maxBounces;
    this.bounceRestitution = bounceRestitution;
    this.lifeSpan = lifeSpan;
    this.potionId = potionId;
    this.potionAmplifier = potionAmplifier;
    this.potionDuration = potionDuration;
    this.explodeOnDeath = explodeOnDeath;
    this.explosionSize = explosionSize;
  }
  
  public String getModel() {
    return this.model;
  }
  
  public float getForce() {
    return this.force;
  }
  
  public float getPrecision() {
    return this.precision;
  }
  
  public double getGravity() {
    return this.gravity;
  }
  
  public boolean isDeathOnCollideEntity() {
    return this.deathOnCollideEntity;
  }
  
  public boolean isDeathOnCollideBlock() {
    return this.deathOnCollideBlock;
  }
  
  public boolean isStickToBlock() {
    return this.stickToBlock;
  }
  
  public int getMaxBounces() {
    return this.maxBounces;
  }
  
  public double getBounceRestitution() {
    return this.bounceRestitution;
  }
  
  public long getLifeSpan() {
    return this.lifeSpan;
  }
  
  public int getPotionId() {
    return this.potionId;
  }
  
  public int getPotionAmplifier() {
    return this.potionAmplifier;
  }
  
  public int getPotionDuration() {
    return this.potionDuration;
  }
  
  public boolean isExplodeOnDeath() {
    return this.explodeOnDeath;
  }
  
  public float getExplosionSize() {
    return this.explosionSize;
  }
  
  public RPGAttackProjectile(String model) {
    this.model = model;
    this.force = 0.0F;
    this.precision = 0.0F;
    this.airFriction = 0.0D;
    this.groundFriction = 0.0D;
    this.gravity = 0.0D;
    this.deathOnCollideEntity = false;
    this.deathOnCollideBlock = false;
    this.stickToBlock = false;
    this.maxBounces = 25;
    this.bounceRestitution = 0.85D;
    this.lifeSpan = -1L;
    this.potionId = 0;
    this.potionAmplifier = 0;
    this.potionDuration = 0;
    this.explodeOnDeath = false;
    this.explosionSize = 2.0F;
  }
  
  public RPGAttackProjectile(JsonObject jsonObject, JsonDeserializationContext context) {
    this.model = jsonObject.has("model") ? (String)context.deserialize(jsonObject.get("model"), String.class) : "";
    this.force = jsonObject.has("force") ? ((Float)context.deserialize(jsonObject.get("force"), float.class)).floatValue() : 0.0F;
    this.precision = jsonObject.has("precision") ? ((Float)context.deserialize(jsonObject.get("precision"), float.class)).floatValue() : 0.0F;
    this.airFriction = jsonObject.has("airFriction") ? ((Double)context.deserialize(jsonObject.get("airFriction"), double.class)).doubleValue() : 1.0D;
    this.groundFriction = jsonObject.has("groundFriction") ? ((Double)context.deserialize(jsonObject.get("groundFriction"), double.class)).doubleValue() : 1.0D;
    this.gravity = jsonObject.has("gravity") ? ((Double)context.deserialize(jsonObject.get("gravity"), double.class)).doubleValue() : 0.05D;
    this.deathOnCollideEntity = jsonObject.has("deathOnCollideEntity") ? ((Boolean)context.deserialize(jsonObject.get("deathOnCollideEntity"), boolean.class)).booleanValue() : true;
    this.deathOnCollideBlock = jsonObject.has("deathOnCollideBlock") ? ((Boolean)context.deserialize(jsonObject.get("deathOnCollideBlock"), boolean.class)).booleanValue() : true;
    this.stickToBlock = jsonObject.has("stickToBlock") ? ((Boolean)context.deserialize(jsonObject.get("stickToBlock"), boolean.class)).booleanValue() : false;
    this.maxBounces = jsonObject.has("maxBounces") ? ((Integer)context.deserialize(jsonObject.get("maxBounces"), int.class)).intValue() : 0;
    this.bounceRestitution = jsonObject.has("bounceRestitution") ? ((Double)context.deserialize(jsonObject.get("bounceRestitution"), double.class)).doubleValue() : 0.85D;
    this.lifeSpan = jsonObject.has("lifeSpan") ? ((Long)context.deserialize(jsonObject.get("lifeSpan"), long.class)).longValue() : -1L;
    this.potionId = jsonObject.has("potionId") ? ((Integer)context.deserialize(jsonObject.get("potionId"), int.class)).intValue() : 0;
    this.potionAmplifier = jsonObject.has("potionAmplifier") ? ((Integer)context.deserialize(jsonObject.get("potionAmplifier"), int.class)).intValue() : 0;
    this.potionDuration = jsonObject.has("potionDuration") ? ((Integer)context.deserialize(jsonObject.get("potionDuration"), int.class)).intValue() : 0;
    this.explodeOnDeath = jsonObject.has("explodeOnDeath") ? ((Boolean)context.deserialize(jsonObject.get("explodeOnDeath"), boolean.class)).booleanValue() : false;
    this.explosionSize = jsonObject.has("explosionSize") ? ((Float)context.deserialize(jsonObject.get("explosionSize"), float.class)).floatValue() : 2.0F;
  }
  
  public RPGProjectile getProjectileEntity(World world, EntityLivingBase thrower, RPGAttack attack) {
    return new RPGProjectile(world, thrower, attack);
  }
  
  public boolean canBounce() {
    return (this.maxBounces > 0);
  }
  
  public double getAirFriction() {
    return 1.0D - this.airFriction;
  }
  
  public double getGroundFriction() {
    return 1.0D - this.groundFriction;
  }
  
  public boolean hasPotionEffect() {
    return (this.potionId > 0 && this.potionDuration > 0);
  }
  
  public PotionEffect getPotionEffect() {
    return new PotionEffect(this.potionId, this.potionDuration, this.potionAmplifier);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\server\loader\dat\\util\RPGAttack$RPGAttackProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */