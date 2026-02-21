package fr.paladium.palawither.common.wither.base.property.impl;

import fr.paladium.palawither.common.wither.base.EntityWitherBase;
import fr.paladium.palawither.common.wither.base.property.WitherProperty;
import lombok.NonNull;
import net.minecraft.entity.Entity;

@WitherProperty("explosion")
public class WitherExplosionProperty {
  public static final String ID = "explosion";
  
  private final int power;
  
  private final boolean fire;
  
  private final boolean damage;
  
  public WitherExplosionProperty(int power, boolean fire, boolean damage) {
    this.power = power;
    this.fire = fire;
    this.damage = damage;
  }
  
  public int getPower() {
    return this.power;
  }
  
  public boolean isFire() {
    return this.fire;
  }
  
  public boolean isDamage() {
    return this.damage;
  }
  
  public <T extends EntityWitherBase> void explode(@NonNull T wither) {
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    ((EntityWitherBase)wither).field_70170_p.func_72885_a((Entity)wither, ((EntityWitherBase)wither).field_70165_t, ((EntityWitherBase)wither).field_70163_u + wither.func_70047_e(), ((EntityWitherBase)wither).field_70161_v, this.power, this.fire, (((EntityWitherBase)wither).field_70170_p.func_82736_K().func_82766_b("mobGriefing") && this.damage));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\base\property\impl\WitherExplosionProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */