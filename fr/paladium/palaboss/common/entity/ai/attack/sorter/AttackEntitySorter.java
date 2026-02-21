package fr.paladium.palaboss.common.entity.ai.attack.sorter;

import java.util.Comparator;
import java.util.function.BiFunction;
import net.minecraft.entity.Entity;

public class AttackEntitySorter implements Comparator<Entity> {
  private final BiFunction<Entity, Entity, Integer> sorter;
  
  public AttackEntitySorter(BiFunction<Entity, Entity, Integer> sorter) {
    this.sorter = sorter;
  }
  
  public int compare(Entity o1, Entity o2) {
    return ((Integer)this.sorter.apply(o1, o2)).intValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\ai\attack\sorter\AttackEntitySorter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */