package fr.paladium.palarpg.module.entity.server.loader.data.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.paladium.palarpg.module.entity.common.entity.projectile.RPGProjectile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RPGAttack {
  private final String attackId;
  
  private final String animation;
  
  private final float damage;
  
  private final float range;
  
  private final int weight;
  
  private final int attackDuration;
  
  private final long animationDuration;
  
  private final boolean disableMovement;
  
  private final boolean fire;
  
  private final int fireDuration;
  
  private final RPGAttackProjectile projectile;
  
  private final List<String> conditions;
  
  private final Map<String, JsonElement> data;
  
  public String toString() {
    return "RPGAttack(attackId=" + getAttackId() + ", animation=" + getAnimation() + ", damage=" + getDamage() + ", range=" + getRange() + ", weight=" + getWeight() + ", attackDuration=" + getAttackDuration() + ", animationDuration=" + getAnimationDuration() + ", disableMovement=" + isDisableMovement() + ", fire=" + isFire() + ", fireDuration=" + getFireDuration() + ", projectile=" + getProjectile() + ", conditions=" + getConditions() + ", data=" + getData() + ")";
  }
  
  public RPGAttack(String attackId, String animation, float damage, float range, int weight, int attackDuration, long animationDuration, boolean disableMovement, boolean fire, int fireDuration, RPGAttackProjectile projectile, List<String> conditions, Map<String, JsonElement> data) {
    this.attackId = attackId;
    this.animation = animation;
    this.damage = damage;
    this.range = range;
    this.weight = weight;
    this.attackDuration = attackDuration;
    this.animationDuration = animationDuration;
    this.disableMovement = disableMovement;
    this.fire = fire;
    this.fireDuration = fireDuration;
    this.projectile = projectile;
    this.conditions = conditions;
    this.data = data;
  }
  
  public String getAttackId() {
    return this.attackId;
  }
  
  public String getAnimation() {
    return this.animation;
  }
  
  public float getDamage() {
    return this.damage;
  }
  
  public float getRange() {
    return this.range;
  }
  
  public int getWeight() {
    return this.weight;
  }
  
  public int getAttackDuration() {
    return this.attackDuration;
  }
  
  public long getAnimationDuration() {
    return this.animationDuration;
  }
  
  public boolean isDisableMovement() {
    return this.disableMovement;
  }
  
  public boolean isFire() {
    return this.fire;
  }
  
  public int getFireDuration() {
    return this.fireDuration;
  }
  
  public RPGAttackProjectile getProjectile() {
    return this.projectile;
  }
  
  public List<String> getConditions() {
    return this.conditions;
  }
  
  public Map<String, JsonElement> getData() {
    return this.data;
  }
  
  public RPGAttack(String attackId, String model) {
    this.attackId = attackId;
    this.animation = "shot";
    this.damage = 0.0F;
    this.range = 0.0F;
    this.weight = 0;
    this.attackDuration = 100;
    this.animationDuration = 1000L;
    this.disableMovement = false;
    this.fire = false;
    this.fireDuration = 0;
    this.projectile = new RPGAttackProjectile(model);
    this.conditions = new ArrayList<>();
    this.data = new HashMap<>();
  }
  
  public RPGAttack(JsonObject jsonObject, JsonDeserializationContext context) {
    this.attackId = jsonObject.has("attackId") ? (String)context.deserialize(jsonObject.get("attackId"), String.class) : "";
    this.animation = jsonObject.has("animation") ? (String)context.deserialize(jsonObject.get("animation"), String.class) : "";
    this.damage = jsonObject.has("damage") ? ((Float)context.deserialize(jsonObject.get("damage"), float.class)).floatValue() : 0.0F;
    this.range = jsonObject.has("range") ? ((Float)context.deserialize(jsonObject.get("range"), float.class)).floatValue() : 0.0F;
    this.weight = jsonObject.has("weight") ? ((Integer)context.deserialize(jsonObject.get("weight"), int.class)).intValue() : 0;
    this.attackDuration = jsonObject.has("attackDuration") ? ((Integer)context.deserialize(jsonObject.get("attackDuration"), int.class)).intValue() : 100;
    this.animationDuration = jsonObject.has("animationDuration") ? ((Long)context.deserialize(jsonObject.get("animationDuration"), long.class)).longValue() : 1000L;
    this.disableMovement = jsonObject.has("disableMovement") ? ((Boolean)context.deserialize(jsonObject.get("disableMovement"), boolean.class)).booleanValue() : false;
    this.fire = jsonObject.has("fire") ? ((Boolean)context.deserialize(jsonObject.get("fire"), boolean.class)).booleanValue() : false;
    this.fireDuration = jsonObject.has("fireDuration") ? ((Integer)context.deserialize(jsonObject.get("fireDuration"), int.class)).intValue() : 0;
    this.projectile = jsonObject.has("projectile") ? new RPGAttackProjectile(jsonObject.getAsJsonObject("projectile"), context) : null;
    this.conditions = jsonObject.has("conditions") ? (List<String>)context.deserialize(jsonObject.get("conditions"), List.class) : new ArrayList<>();
    if (jsonObject.has("data")) {
      Map<String, JsonElement> datas = new HashMap<>();
      JsonObject dataObject = jsonObject.get("data").getAsJsonObject();
      dataObject.entrySet().forEach(entry -> datas.put(entry.getKey(), entry.getValue()));
      this.data = datas;
    } else {
      this.data = new HashMap<>();
    } 
  }
  
  public RPGProjectile getProjectileEntity(World world, EntityLivingBase thrower) {
    if (this.projectile == null)
      return null; 
    return this.projectile.getProjectileEntity(world, thrower, this);
  }
  
  public boolean doDamage() {
    return (this.damage > 0.0F);
  }
  
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
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\server\loader\dat\\util\RPGAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */