package fr.paladium.palaboss.common.entity.ai.task;

import java.util.Comparator;
import net.minecraft.entity.Entity;

public class Sorter implements Comparator<Entity> {
  private final Entity theEntity;
  
  public Sorter(Entity entity) {
    this.theEntity = entity;
  }
  
  public int compare(Entity e1, Entity e2) {
    double d0 = this.theEntity.func_70068_e(e1);
    double d1 = this.theEntity.func_70068_e(e2);
    return (d0 < d1) ? -1 : ((d0 > d1) ? 1 : 0);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\ai\task\EntityAINearestPlayerTarget$Sorter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */