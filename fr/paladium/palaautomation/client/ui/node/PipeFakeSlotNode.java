package fr.paladium.palaautomation.client.ui.node;

import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.tile.util.PipeItemData;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.font.impl.minecraft.MinecraftFont;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.align.Align;
import java.util.List;
import lombok.NonNull;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class PipeFakeSlotNode extends Node {
  private final IPipeMachine pipeMachine;
  
  private final Resource texture;
  
  private final Resource hoverTexture;
  
  private double itemSize;
  
  protected PipeFakeSlotNode(double x, double y, double size, IPipeMachine pipeMachine) {
    super(x, y, size, size);
    this.pipeMachine = pipeMachine;
    this.itemSize = size * 0.8D;
    this.texture = ResourceConstant.SLOT;
    this.hoverTexture = ResourceConstant.SLOT_HOVER;
  }
  
  @NonNull
  public static PipeFakeSlotNode create(double x, double y, double size, @NonNull IPipeMachine pipeMachine) {
    if (pipeMachine == null)
      throw new NullPointerException("pipeMachine is marked non-null but is null"); 
    return new PipeFakeSlotNode(x, y, size, pipeMachine);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.texture != null)
      DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), this.texture); 
    if (this.hoverTexture != null && isEnabled()) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, hoverValue(1.0F));
      DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), this.hoverTexture);
    } 
    PipeItemData pipeItemData = this.pipeMachine.getItemData();
    ItemStack stack = (pipeItemData == null) ? null : pipeItemData.toSingleItemStack();
    if (stack != null) {
      DrawUtils.ITEM.drawItem(getX() + dw(2.0D) - this.itemSize / 2.0D, getY() + dh(2.0D) - this.itemSize / 2.0D, this.itemSize, stack);
      hoverLines(() -> DrawUtils.ITEM.getTooltip(stack));
    } else {
      clearHover();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.3F);
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
  
  public PipeFakeSlotNode itemSize(float size) {
    this.itemSize = size;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\clien\\ui\node\PipeFakeSlotNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */