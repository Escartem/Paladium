package fr.paladium.paladiumui.kit.button.impl;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

public class ArrowButtonNode extends Node {
  private double rotation;
  
  public double getRotation() {
    return this.rotation;
  }
  
  protected ArrowButtonNode(double x, double y, double size, double rotation) {
    super(x, y, size, size);
    hoverDuration(100L);
    this.rotation = rotation;
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    Color color = isEnabled() ? Color.WHITE : new Color(171, 171, 171);
    double animationOffset = (this.rotation < 180.0D) ? dh(12.0D) : -dh(12.0D);
    double x = getX();
    double y = getY() + (isEnabled() ? hoverValue((float)animationOffset) : animationOffset);
    double width = dw(3.0D);
    double height = dh(3.0D);
    Color shadowColor = color.darker(0.4F);
    double shadowY = getY() + ((this.rotation < 180.0D) ? (height / 2.0D) : (-height / 2.0D));
    GL11.glPushMatrix();
    GL11.glTranslated(getX() + dw(2.0D), getY() + dh(2.0D), 0.0D);
    GL11.glRotated(this.rotation, 0.0D, 0.0D, 1.0D);
    GL11.glTranslated(-getX() - dw(2.0D), -getY() - dh(2.0D), 0.0D);
    DrawUtils.SHAPE.drawRect(x, shadowY, width, height * 3.0D, shadowColor);
    DrawUtils.SHAPE.drawRect(x + width, shadowY + height / 2.0D, width, height * 2.0D, shadowColor);
    DrawUtils.SHAPE.drawRect(x + width * 2.0D, shadowY + height, width, height, shadowColor);
    DrawUtils.SHAPE.drawRect(x, y, width, height * 3.0D, color);
    DrawUtils.SHAPE.drawRect(x + width, y + height / 2.0D, width, height * 2.0D, color);
    DrawUtils.SHAPE.drawRect(x + width * 2.0D, y + height, width, height, color);
    GL11.glPopMatrix();
  }
  
  @NonNull
  public final <T extends ArrowButtonNode> T rotation(double rotation) {
    this.rotation = rotation;
    return (T)this;
  }
  
  public static class Right {
    public static ArrowButtonNode create(double x, double y, double size) {
      return new ArrowButtonNode(x, y, size, 0.0D);
    }
  }
  
  public static class Left {
    public static ArrowButtonNode create(double x, double y, double size) {
      return new ArrowButtonNode(x, y, size, 180.0D);
    }
  }
  
  public static class Up {
    public static ArrowButtonNode create(double x, double y, double size) {
      return new ArrowButtonNode(x, y, size, 90.0D);
    }
  }
  
  public static class Down {
    public static ArrowButtonNode create(double x, double y, double size) {
      return new ArrowButtonNode(x, y, size, 270.0D);
    }
  }
  
  public static class Custom {
    public static ArrowButtonNode create(double x, double y, double size, double rotation) {
      return new ArrowButtonNode(x, y, size, rotation);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\button\impl\ArrowButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */