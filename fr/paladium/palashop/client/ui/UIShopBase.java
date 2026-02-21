package fr.paladium.palashop.client.ui;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.SoundConstant;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.mouse.NodeMousePressedCallback;
import fr.paladium.zephyrui.lib.ui.node.callback.registry.NodeCallbackRegistry;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector4f;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import org.lwjgl.opengl.GL11;

public abstract class UIShopBase extends UI {
  private static int clickCallbackId = -1;
  
  private static SoundRecord music;
  
  private Node hoveredNode;
  
  private boolean hovering;
  
  private boolean hoveringNode;
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    if ((getMusicSound() != null && music == null) || !music.isPlaying())
      music = SoundRecord.create(PalaShopMod.getInstance(), getMusicSound()).volume(0.1F).loop().build().play(); 
    if (clickCallbackId == -1)
      clickCallbackId = NodeCallbackRegistry.getId(NodeMousePressedCallback.class); 
    Node tmpHoveredNode = null;
    for (Node node : getNodeList().recursive().reversed()) {
      if (!node.isHovered(mouseX, mouseY) || !node.hasCallback(clickCallbackId))
        continue; 
      tmpHoveredNode = node;
    } 
    if (tmpHoveredNode != null && tmpHoveredNode != this.hoveredNode && (this.hoveredNode == null || !this.hoveredNode.getChildren().recursive().contains((IndexedElement)tmpHoveredNode))) {
      SoundConstant.BUTTON_HOVER.copy().play();
      this.hoveredNode = tmpHoveredNode;
    } else if (tmpHoveredNode == null && this.hoveredNode != null) {
      this.hoveredNode = null;
    } 
  }
  
  public void drawHover(List<String> lines, double x, double y) {
    if (this.hovering) {
      this.hoveringNode = true;
      return;
    } 
    if (lines.isEmpty())
      return; 
    this.hovering = true;
    this.hoveringNode = false;
    Node hoveredNode = null;
    for (Node node : getNodeList().recursive().reversed()) {
      if (node.isHovered(x, y, false) && node.renderHover(getMouseX(), getMouseY(), 0.0F) && this.hoveringNode) {
        hoveredNode = node;
        break;
      } 
    } 
    float opacity = 1.0F;
    if (hoveredNode != null) {
      x = hoveredNode.getAbsoluteX() + hoveredNode.dw(2.0D) - 25.0D;
      y = hoveredNode.getAbsoluteY() + hoveredNode.getHeight() + 30.0D;
      opacity = hoveredNode.hoverValue(1.0F);
    } 
    float radius = 7.0F;
    float borderStroke = 2.0F;
    float borderRadius = 9.0F;
    double paddingX = 40.0D;
    double paddingY = 15.0D;
    boolean bottom = true;
    boolean left = true;
    double width = 0.0D;
    double height = 0.0D;
    List<Text> textList = new ArrayList<>();
    for (String line : lines) {
      Text text = Text.create(line, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 20.0F, Color.WHITE.copyAlpha(opacity)));
      textList.add(text);
      width = Math.max(width, text.getWidth());
      height += text.getHeight();
    } 
    width += 80.0D;
    height += 30.0D;
    if (x + width > 1900.0D) {
      x -= width - 50.0D;
      left = false;
    } 
    if (y + height > 1060.0D) {
      y -= 30.0D + height + 20.0D + 10.0D;
      if (hoveredNode != null)
        y -= hoveredNode.getHeight(); 
      bottom = false;
      left = !left;
    } 
    Color color = ColorConstant.LIGHT_DARK.copyAlpha(opacity);
    Color startColor = Color.decode("#383B40").copyAlpha(opacity);
    Color endColor = Color.decode("#141517").copyAlpha(opacity);
    Color gradient = Color.gradient(startColor, endColor, new Vector4f(0.0F, 0.0F, 0.0F, 1.0F));
    double finalX = x;
    double finalY = y;
    double finalWidth = width;
    double finalHeight = height;
    GL11.glPushMatrix();
    if (!bottom) {
      GL11.glTranslated(finalX + finalWidth / 2.0D, finalY + finalHeight / 2.0D, 0.0D);
      GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
      GL11.glTranslated(-(finalX + finalWidth / 2.0D), -(finalY + finalHeight / 2.0D), 0.0D);
    } 
    gradient.bind(() -> DrawUtils.SHAPE.drawRect(finalX - 2.0D, finalY + 9.0D, finalWidth + 4.0D, finalHeight - 18.0D, Color.WHITE), new Vector4f((float)finalX - 2.0F, (float)finalY + 9.0F, (float)finalWidth + 4.0F, (float)finalHeight - 18.0F));
    startMask(finalX - 2.0D, finalY - 2.0D, finalWidth + 4.0D, 11.0D);
    DrawUtils.SHAPE.drawRoundedRect(finalX - 2.0D, finalY - 2.0D, finalWidth + 4.0D, finalHeight + 4.0D, startColor, 9.0F);
    stopMask();
    startMask(finalX - 2.0D, finalY + finalHeight - 9.0D, finalWidth + 4.0D, 11.0D);
    DrawUtils.SHAPE.drawRoundedRect(finalX - 2.0D, finalY - 2.0D, finalWidth + 4.0D, finalHeight + 4.0D, endColor, 9.0F);
    stopMask();
    DrawUtils.SHAPE.drawRoundedRect(finalX, finalY, finalWidth, finalHeight, color, 7.0F);
    double arrowX = left ? (finalX + 15.0D) : (finalX + finalWidth - 35.0D);
    double arrowY = finalY - 10.0D;
    startMask(arrowX - 2.0D - 5.0D, arrowY - 2.0D - 5.0D, 34.0D, 17.0D);
    GL11.glPushMatrix();
    GL11.glTranslated(arrowX + 10.0D, arrowY + 10.0D, 0.0D);
    GL11.glRotated(45.0D, 0.0D, 0.0D, 1.0D);
    GL11.glTranslated(-(arrowX + 10.0D), -(arrowY + 10.0D), 0.0D);
    DrawUtils.SHAPE.drawRoundedRect(arrowX - 2.0D, arrowY - 2.0D, 24.0D, 24.0D, startColor, 7.0F);
    DrawUtils.SHAPE.drawRoundedRect(arrowX, arrowY, 20.0D, 20.0D, color, 5.0F);
    GL11.glPopMatrix();
    stopMask();
    GL11.glPopMatrix();
    double offsetY = 0.0D;
    for (int i = 0; i < textList.size(); i++) {
      Text text = textList.get(i);
      DrawUtils.TEXT.drawText(finalX + 40.0D, finalY + 15.0D + offsetY, text);
      offsetY += text.getHeight();
    } 
    this.hovering = false;
  }
  
  public void mousePressed(double mouseX, double mouseY, @NonNull ClickType clickType, @NonNull InternalContext context) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (!context.isCancelled())
      return; 
    SoundConstant.BUTTON_CLICK.copy().play();
  }
  
  public boolean close() {
    FMLClientScheduler.getInstance().add(new Runnable[] { () -> {
            if (!ZUI.isOpen(UIShopBase.class)) {
              music = null;
              Minecraft.func_71410_x().func_147118_V().func_147690_c();
            } 
          } });
    return super.close();
  }
  
  protected String getMusicSound() {
    return "sounds/global/music.ogg";
  }
  
  public static ISound getMusic() {
    return (ISound)music;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\UIShopBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */