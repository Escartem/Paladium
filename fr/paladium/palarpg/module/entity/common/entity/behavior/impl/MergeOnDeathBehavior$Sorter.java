package fr.paladium.palarpg.module.entity.common.entity.behavior.impl;

import java.util.Comparator;
import net.minecraft.entity.Entity;

public class Sorter implements Comparator<Entity> {
  public int compare(Entity e1, Entity e2) {
    double d0 = MergeOnDeathBehavior.this.getBehaviorOwner().func_70068_e(e1);
    double d1 = MergeOnDeathBehavior.this.getBehaviorOwner().func_70068_e(e2);
    return (d0 < d1) ? -1 : ((d0 > d1) ? 1 : 0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\behavior\impl\MergeOnDeathBehavior$Sorter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */