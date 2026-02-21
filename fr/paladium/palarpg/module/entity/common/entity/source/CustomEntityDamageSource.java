package fr.paladium.palarpg.module.entity.common.entity.source;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class CustomEntityDamageSource extends EntityDamageSource {
  protected CustomEntityDamageSource(String p_i1567_1_, Entity p_i1567_2_) {
    super(p_i1567_1_, p_i1567_2_);
  }
  
  public static CustomEntityDamageSource causeCustomMobDamage(Entity entity) {
    return new CustomEntityDamageSource("mob", entity);
  }
  
  public boolean func_76350_n() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\source\CustomEntityDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */