package fr.paladium.palaboss.common.entity.properties;

import net.minecraft.nbt.NBTTagCompound;

public class EntityProperties {
  public void setSpeed(double speed) {
    this.speed = speed;
  }
  
  public void setHealth(double health) {
    this.health = health;
  }
  
  public void setFollowRange(double followRange) {
    this.followRange = followRange;
  }
  
  public void setKnockback(float knockback) {
    this.knockback = knockback;
  }
  
  public void setExplosion(float explosion) {
    this.explosion = explosion;
  }
  
  public void setFire(float fire) {
    this.fire = fire;
  }
  
  public void setAttackDelay(int attackDelay) {
    this.attackDelay = attackDelay;
  }
  
  private static double $default$speed() {
    return 2.0D;
  }
  
  private static double $default$health() {
    return 20.0D;
  }
  
  private static double $default$followRange() {
    return 16.0D;
  }
  
  private static float $default$knockback() {
    return 0.2F;
  }
  
  private static float $default$explosion() {
    return 0.5F;
  }
  
  private static float $default$fire() {
    return 0.5F;
  }
  
  private static int $default$attackDelay() {
    return 30;
  }
  
  public static EntityPropertiesBuilder builder() {
    return new EntityPropertiesBuilder();
  }
  
  public static class EntityPropertiesBuilder {
    private boolean speed$set;
    
    private double speed$value;
    
    private boolean health$set;
    
    private double health$value;
    
    private boolean followRange$set;
    
    private double followRange$value;
    
    private boolean knockback$set;
    
    private float knockback$value;
    
    private boolean explosion$set;
    
    private float explosion$value;
    
    private boolean fire$set;
    
    private float fire$value;
    
    private boolean attackDelay$set;
    
    private int attackDelay$value;
    
    public EntityPropertiesBuilder speed(double speed) {
      this.speed$value = speed;
      this.speed$set = true;
      return this;
    }
    
    public EntityPropertiesBuilder health(double health) {
      this.health$value = health;
      this.health$set = true;
      return this;
    }
    
    public EntityPropertiesBuilder followRange(double followRange) {
      this.followRange$value = followRange;
      this.followRange$set = true;
      return this;
    }
    
    public EntityPropertiesBuilder knockback(float knockback) {
      this.knockback$value = knockback;
      this.knockback$set = true;
      return this;
    }
    
    public EntityPropertiesBuilder explosion(float explosion) {
      this.explosion$value = explosion;
      this.explosion$set = true;
      return this;
    }
    
    public EntityPropertiesBuilder fire(float fire) {
      this.fire$value = fire;
      this.fire$set = true;
      return this;
    }
    
    public EntityPropertiesBuilder attackDelay(int attackDelay) {
      this.attackDelay$value = attackDelay;
      this.attackDelay$set = true;
      return this;
    }
    
    public EntityProperties build() {
      double speed$value = this.speed$value;
      if (!this.speed$set)
        speed$value = EntityProperties.$default$speed(); 
      double health$value = this.health$value;
      if (!this.health$set)
        health$value = EntityProperties.$default$health(); 
      double followRange$value = this.followRange$value;
      if (!this.followRange$set)
        followRange$value = EntityProperties.$default$followRange(); 
      float knockback$value = this.knockback$value;
      if (!this.knockback$set)
        knockback$value = EntityProperties.$default$knockback(); 
      float explosion$value = this.explosion$value;
      if (!this.explosion$set)
        explosion$value = EntityProperties.$default$explosion(); 
      float fire$value = this.fire$value;
      if (!this.fire$set)
        fire$value = EntityProperties.$default$fire(); 
      int attackDelay$value = this.attackDelay$value;
      if (!this.attackDelay$set)
        attackDelay$value = EntityProperties.$default$attackDelay(); 
      return new EntityProperties(speed$value, health$value, followRange$value, knockback$value, explosion$value, fire$value, attackDelay$value);
    }
    
    public String toString() {
      return "EntityProperties.EntityPropertiesBuilder(speed$value=" + this.speed$value + ", health$value=" + this.health$value + ", followRange$value=" + this.followRange$value + ", knockback$value=" + this.knockback$value + ", explosion$value=" + this.explosion$value + ", fire$value=" + this.fire$value + ", attackDelay$value=" + this.attackDelay$value + ")";
    }
  }
  
  public EntityProperties() {
    this.speed = $default$speed();
    this.health = $default$health();
    this.followRange = $default$followRange();
    this.knockback = $default$knockback();
    this.explosion = $default$explosion();
    this.fire = $default$fire();
    this.attackDelay = $default$attackDelay();
  }
  
  public EntityProperties(double speed, double health, double followRange, float knockback, float explosion, float fire, int attackDelay) {
    this.speed = speed;
    this.health = health;
    this.followRange = followRange;
    this.knockback = knockback;
    this.explosion = explosion;
    this.fire = fire;
    this.attackDelay = attackDelay;
  }
  
  public static final EntityProperties DEFAULT = builder().build();
  
  private double speed;
  
  private double health;
  
  private double followRange;
  
  private float knockback;
  
  private float explosion;
  
  private float fire;
  
  private int attackDelay;
  
  public double getSpeed() {
    return this.speed;
  }
  
  public double getHealth() {
    return this.health;
  }
  
  public double getFollowRange() {
    return this.followRange;
  }
  
  public float getKnockback() {
    return this.knockback;
  }
  
  public float getExplosion() {
    return this.explosion;
  }
  
  public float getFire() {
    return this.fire;
  }
  
  public int getAttackDelay() {
    return this.attackDelay;
  }
  
  public void writeNBT(NBTTagCompound nbt) {
    nbt.func_74780_a("speed", this.speed);
    nbt.func_74780_a("health", this.health);
    nbt.func_74780_a("followRange", this.followRange);
    nbt.func_74776_a("knockback", this.knockback);
    nbt.func_74776_a("explosion", this.explosion);
    nbt.func_74776_a("fire", this.fire);
    nbt.func_74768_a("attackDelay", this.attackDelay);
  }
  
  public void readNBT(NBTTagCompound nbt) {
    this.speed = nbt.func_74769_h("speed");
    this.health = nbt.func_74769_h("health");
    this.followRange = nbt.func_74769_h("followRange");
    this.knockback = nbt.func_74760_g("knockback");
    this.explosion = nbt.func_74760_g("explosion");
    this.fire = nbt.func_74760_g("fire");
    this.attackDelay = nbt.func_74762_e("attackDelay");
  }
  
  public static EntityProperties fromNBT(NBTTagCompound nbt) {
    EntityProperties result = new EntityProperties();
    result.readNBT(nbt);
    return result;
  }
  
  public EntityProperties copy() {
    NBTTagCompound nbt = new NBTTagCompound();
    writeNBT(nbt);
    return fromNBT(nbt);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\properties\EntityProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */