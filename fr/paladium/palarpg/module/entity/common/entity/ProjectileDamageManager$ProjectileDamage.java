package fr.paladium.palarpg.module.entity.common.entity;

import net.minecraft.entity.Entity;

public class ProjectileDamage {
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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\ProjectileDamageManager$ProjectileDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */