package fr.paladium.paladiumui.kit.slot;

import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import java.util.List;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class FakeSlotNode extends Node {
  private ItemStack item;
  
  private double itemSize;
  
  private boolean selected;
  
  public ItemStack getItem() {
    return this.item;
  }
  
  public double getItemSize() {
    return this.itemSize;
  }
  
  public boolean isSelected() {
    return this.selected;
  }
  
  protected FakeSlotNode(double x, double y, double size) {
    super(x, y, size, size);
    this.itemSize = (int)(size * 0.8D);
  }
  
  @NonNull
  public static FakeSlotNode create(double x, double y, double size) {
    return new FakeSlotNode(x, y, size);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), this.selected ? ResourceConstant.SLOT_SELECTED : ResourceConstant.SLOT);
    if (isEnabled() && !this.selected) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, hoverValue(1.0F));
      DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), ResourceConstant.SLOT_HOVER);
    } 
    if (this.item != null) {
      DrawUtils.ITEM.drawItem(getX() + dw(2.0D) - this.itemSize / 2.0D, getY() + dh(2.0D) - this.itemSize / 2.0D, this.itemSize, this.item);
      hoverLines(() -> DrawUtils.ITEM.getTooltip(this.item));
    } else {
      clearHover();
    } 
    GL11.glTranslated(0.0D, 0.0D, 50.0D);
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), new Color(1.0F, 1.0F, 1.0F, hoverValue(0.2F)));
    GL11.glTranslated(0.0D, 0.0D, -50.0D);
  }
  
  @NonNull
  public final <T extends FakeSlotNode> T item(Item item) {
    if (item == null) {
      this.item = null;
      return (T)this;
    } 
    this.item = new ItemStack(item);
    return (T)this;
  }
  
  @NonNull
  public final <T extends FakeSlotNode> T block(Block block) {
    if (block == null) {
      this.item = null;
      return (T)this;
    } 
    this.item = new ItemStack(block);
    return (T)this;
  }
  
  @NonNull
  public final <T extends FakeSlotNode> T item(ItemStack item) {
    this.item = item;
    return (T)this;
  }
  
  @NonNull
  public final <T extends FakeSlotNode> T itemSize(double itemSize) {
    this.itemSize = itemSize;
    return (T)this;
  }
  
  @NonNull
  public final <T extends FakeSlotNode> T selected(boolean selected) {
    this.selected = selected;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\slot\FakeSlotNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */