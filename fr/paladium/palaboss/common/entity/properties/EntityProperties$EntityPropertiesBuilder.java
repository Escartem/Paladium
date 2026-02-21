package fr.paladium.palaboss.common.entity.properties;

public class EntityPropertiesBuilder {
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
      speed$value = EntityProperties.access$000(); 
    double health$value = this.health$value;
    if (!this.health$set)
      health$value = EntityProperties.access$100(); 
    double followRange$value = this.followRange$value;
    if (!this.followRange$set)
      followRange$value = EntityProperties.access$200(); 
    float knockback$value = this.knockback$value;
    if (!this.knockback$set)
      knockback$value = EntityProperties.access$300(); 
    float explosion$value = this.explosion$value;
    if (!this.explosion$set)
      explosion$value = EntityProperties.access$400(); 
    float fire$value = this.fire$value;
    if (!this.fire$set)
      fire$value = EntityProperties.access$500(); 
    int attackDelay$value = this.attackDelay$value;
    if (!this.attackDelay$set)
      attackDelay$value = EntityProperties.access$600(); 
    return new EntityProperties(speed$value, health$value, followRange$value, knockback$value, explosion$value, fire$value, attackDelay$value);
  }
  
  public String toString() {
    return "EntityProperties.EntityPropertiesBuilder(speed$value=" + this.speed$value + ", health$value=" + this.health$value + ", followRange$value=" + this.followRange$value + ", knockback$value=" + this.knockback$value + ", explosion$value=" + this.explosion$value + ", fire$value=" + this.fire$value + ", attackDelay$value=" + this.attackDelay$value + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\properties\EntityProperties$EntityPropertiesBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */