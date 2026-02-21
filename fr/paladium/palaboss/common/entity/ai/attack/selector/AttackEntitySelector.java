package fr.paladium.palaboss.common.entity.ai.attack.selector;

import java.util.function.Function;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

public class AttackEntitySelector implements IEntitySelector {
  private final Function<Entity, Boolean> selector;
  
  public AttackEntitySelector(Function<Entity, Boolean> selector) {
    this.selector = selector;
  }
  
  public boolean func_82704_a(Entity entity) {
    return ((Boolean)this.selector.apply(entity)).booleanValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\ai\attack\selector\AttackEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */