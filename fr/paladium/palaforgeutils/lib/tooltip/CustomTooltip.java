package fr.paladium.palaforgeutils.lib.tooltip;

import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import fr.paladium.zephyrui.lib.utils.list.IndexedLinkedList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.NonNull;
import net.minecraft.item.ItemStack;

public class CustomTooltip {
  private static final IndexedLinkedList<CustomTooltipData> TOOLTIP_LIST = new IndexedLinkedList();
  
  public static void register(@NonNull Predicate<ItemStack> predicate, @NonNull Supplier<CustomTooltipNode> node) {
    if (predicate == null)
      throw new NullPointerException("predicate is marked non-null but is null"); 
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    register(predicate, node, 0);
  }
  
  public static void register(@NonNull Predicate<ItemStack> predicate, @NonNull Supplier<CustomTooltipNode> node, int priority) {
    if (predicate == null)
      throw new NullPointerException("predicate is marked non-null but is null"); 
    if (node == null)
      throw new NullPointerException("node is marked non-null but is null"); 
    TOOLTIP_LIST.add(new CustomTooltipData(node, predicate, priority));
  }
  
  @NonNull
  protected static Optional<CustomTooltipData> getTooltipData(@NonNull ItemStack stack) {
    if (stack == null)
      throw new NullPointerException("stack is marked non-null but is null"); 
    for (CustomTooltipData data : TOOLTIP_LIST.ordered()) {
      if (data.getPredicate().test(stack))
        return Optional.of(data); 
    } 
    return Optional.empty();
  }
  
  protected static class CustomTooltipData implements IndexedElement {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\tooltip\CustomTooltip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */