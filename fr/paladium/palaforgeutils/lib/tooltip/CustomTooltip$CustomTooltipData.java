package fr.paladium.palaforgeutils.lib.tooltip;

import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.item.ItemStack;

public class CustomTooltipData implements IndexedElement {
  private final Supplier<CustomTooltipNode> node;
  
  private final Predicate<ItemStack> predicate;
  
  private final int priority;
  
  public CustomTooltipData(Supplier<CustomTooltipNode> node, Predicate<ItemStack> predicate, int priority) {
    this.node = node;
    this.predicate = predicate;
    this.priority = priority;
  }
  
  public Supplier<CustomTooltipNode> getNode() {
    return this.node;
  }
  
  public Predicate<ItemStack> getPredicate() {
    return this.predicate;
  }
  
  public int getPriority() {
    return this.priority;
  }
  
  public int getIndex() {
    return this.priority;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\tooltip\CustomTooltip$CustomTooltipData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */