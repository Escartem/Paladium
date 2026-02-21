package fr.paladium.palaautomation.client.ui.node;

import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.tile.util.PipeItemData;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.font.impl.minecraft.MinecraftFont;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.List;
import lombok.NonNull;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class PipeSlotNode extends SlotNode {
  private final IPipeMachine machine;
  
  protected PipeSlotNode(double x, double y, double size, int slotIndex, @NonNull IInventory inventory, IPipeMachine pipeMachine) {
    super(x, y, size, slotIndex, inventory);
    if (inventory == null)
      throw new NullPointerException("inventory is marked non-null but is null"); 
    this.machine = pipeMachine;
  }
  
  @NonNull
  public static PipeSlotNode create(double x, double y, double size, int slotIndex, @NonNull IInventory inventory, @NonNull IPipeMachine pipeMachine) {
    if (inventory == null)
      throw new NullPointerException("inventory is marked non-null but is null"); 
    if (pipeMachine == null)
      throw new NullPointerException("pipeMachine is marked non-null but is null"); 
    return new PipeSlotNode(x, y, size, slotIndex, inventory, pipeMachine);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (getTexture() != null) {
      DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), getTexture());
    } else if (getColor() != null) {
      DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), getColor());
    } 
    if (getHoveredTexture() != null && isEnabled()) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, hoverValue(1.0F));
      DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), getHoveredTexture());
    } else if (getHoveredColor() != null && isEnabled()) {
      DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), getHoveredColor().copyAlpha(hoverValue((getHoveredColor()).a)));
    } 
    PipeItemData pipeItemData = (this.machine == null) ? null : this.machine.getItemData();
    ItemStack stack = (pipeItemData == null) ? null : pipeItemData.toSingleItemStack();
    if (stack != null) {
      DrawUtils.ITEM.drawItem(getX() + dw(2.0D) - getItemSize() / 2.0D, getY() + dh(2.0D) - getItemSize() / 2.0D, getItemSize(), stack);
      hoverLines(() -> DrawUtils.ITEM.getTooltip(stack));
    } else {
      clearHover();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.3F);
      if (getPlaceholderResource() != null)
        DrawUtils.RESOURCE.drawImage(getX() + dw(2.0D) - getItemSize() / 2.0D, getY() + dh(2.0D) - getItemSize() / 2.0D, getItemSize(), getItemSize(), getPlaceholderResource()); 
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    } 
    GL11.glTranslated(0.0D, 0.0D, 50.0D);
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), new Color(1.0F, 1.0F, 1.0F, hoverValue(0.2F)));
    if (pipeItemData != null) {
      Text text = Text.create("x " + pipeItemData.getCount(), TextInfo.create(MinecraftFont.MINECRAFT, 25.0F, Color.WHITE)).align(Align.CENTER, Align.CENTER);
      DrawUtils.TEXT.drawText(getX() + getWidth() / 2.0D, getY() + getHeight() - 10.0D, text);
    } 
    GL11.glTranslated(0.0D, 0.0D, -50.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\clien\\ui\node\PipeSlotNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */