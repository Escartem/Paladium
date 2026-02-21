package fr.paladium.palaforgeutils.lib.tooltip;

import fr.paladium.zephyrui.lib.ui.node.Node;
import lombok.NonNull;
import net.minecraft.item.ItemStack;

public abstract class CustomTooltipNode extends Node {
  private ItemStack item;
  
  public ItemStack getItem() {
    return this.item;
  }
  
  protected CustomTooltipNode() {
    super(0.0D, 0.0D);
  }
  
  protected CustomTooltipNode(double width, double height) {
    super(0.0D, 0.0D, width, height);
  }
  
  @NonNull
  public CustomTooltipNode item(@NonNull ItemStack item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    this.item = item;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\tooltip\CustomTooltipNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */