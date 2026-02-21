package fr.paladium.palarpg.module.entity.common.entity.attack;

import java.util.List;
import net.minecraft.entity.Entity;

public interface ISummonEntityAttack {
  default boolean killEntitiesOnDeath() {
    return false;
  }
  
  boolean hasEntitiesSpawned();
  
  List<Entity> getEntitiesSpawned();
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\ISummonEntityAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */