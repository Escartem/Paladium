package fr.paladium.palavanillagui.client.ui.util.node;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.palavanillagui.client.event.InventorySlotItemValidEvent;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slot.SlotNode;
import java.util.List;
import lombok.NonNull;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

public class InventorySlotNode extends SlotNode {
  private static final Color NOT_VALID_BACKGROUND_COLOR = Color.RED.copyAlpha(0.3F);
  
  protected InventorySlotNode(double x, double y, double size, int slotIndex, @NonNull IInventory inventory) {
    super(x, y, size, slotIndex, inventory);
    if (inventory == null)
      throw new NullPointerException("inventory is marked non-null but is null"); 
    itemSize((int)(size * 0.8D));
    texture(ResourceConstant.SLOT);
    hoverTexture(ResourceConstant.SLOT_HOVER);
  }
  
  public static InventorySlotNode create(double x, double y, double size, int slotIndex, @NonNull IInventory inventory) {
    if (inventory == null)
      throw new NullPointerException("inventory is marked non-null but is null"); 
    return new InventorySlotNode(x, y, size, slotIndex, inventory);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (getTexture() != null) {
      DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), getTexture());
    } else if (getColor() != null) {
      DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), getColor());
    } 
    if (getItemStack() != null) {
      InventorySlotItemValidEvent event = new InventorySlotItemValidEvent(getItemStack());
      MinecraftForge.EVENT_BUS.post((Event)event);
      if (event.isCanceled())
        DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), NOT_VALID_BACKGROUND_COLOR); 
    } 
    if (getHoveredTexture() != null && isEnabled()) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, hoverValue(1.0F));
      DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), getHoveredTexture());
    } else if (getHoveredColor() != null && isEnabled()) {
      DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), getHoveredColor().copyAlpha(hoverValue((getHoveredColor()).a)));
    } 
    ItemStack stack = getRenderItemStack();
    if (stack != null) {
      DrawUtils.ITEM.drawItem(getX() + dw(2.0D) - getItemSize() / 2.0D, getY() + dh(2.0D) - getItemSize() / 2.0D, getItemSize(), stack);
      hoverLines(() -> DrawUtils.ITEM.getTooltip(stack));
    } else {
      clearHover();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.3F);
      if (getPlaceholderResource() != null) {
        DrawUtils.RESOURCE.drawImage(getX() + dw(2.0D) - getItemSize() / 2.0D, getY() + dh(2.0D) - getItemSize() / 2.0D, getItemSize(), getItemSize(), getPlaceholderResource());
      } else if (getPlaceholderStack() != null) {
        DrawUtils.ITEM.drawItem(getX() + dw(2.0D) - getItemSize() / 2.0D, getY() + dh(2.0D) - getItemSize() / 2.0D, getItemSize(), getPlaceholderStack(), new Color(1.0F, 1.0F, 1.0F, 0.3F), true);
      } 
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    } 
    GL11.glTranslated(0.0D, 0.0D, 50.0D);
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), new Color(1.0F, 1.0F, 1.0F, hoverValue(0.2F)));
    GL11.glTranslated(0.0D, 0.0D, -50.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\node\InventorySlotNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */