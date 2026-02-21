package fr.paladium.palavoicechat.client.ui.node;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import java.util.function.Supplier;

public class DisplayNode extends Node {
  private final Color color = Color.decode("#282828");
  
  private final Color shadowColor = Color.decode("#1F1F1F");
  
  private double border = 0.0D;
  
  private Supplier<Boolean> selected = () -> Boolean.valueOf(true);
  
  private Supplier<Boolean> hoverable = () -> Boolean.valueOf(true);
  
  protected DisplayNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static DisplayNode create(double x, double y, double width, double height) {
    return new DisplayNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    boolean selected = ((Boolean)this.selected.get()).booleanValue();
    Color bgColor = this.shadowColor.brighter(hoverValue(0.2F));
    DrawUtils.SHAPE.drawRect(getX(), getY() + this.border + 4.0D, this.border, h() - this.border * 2.0D, bgColor);
    DrawUtils.SHAPE.drawRect(getX() + this.border, getY() + 4.0D, w() - this.border * 2.0D, h(), bgColor);
    DrawUtils.SHAPE.drawRect(getX() + w() - this.border, getY() + this.border + 4.0D, this.border, h() - this.border * 2.0D, bgColor);
    Color fgColor = selected ? this.color.brighter(hoverValue(0.2F)) : this.shadowColor.brighter(hoverValue(0.2F));
    DrawUtils.SHAPE.drawRect(getX(), getY() + this.border, this.border, h() - this.border * 2.0D, fgColor);
    DrawUtils.SHAPE.drawRect(getX() + this.border, getY(), w() - this.border * 2.0D, h(), fgColor);
    DrawUtils.SHAPE.drawRect(getX() + w() - this.border, getY() + this.border, this.border, h() - this.border * 2.0D, fgColor);
  }
  
  public DisplayNode border(double border) {
    this.border = border;
    return this;
  }
  
  public DisplayNode selected(Supplier<Boolean> selected) {
    this.selected = selected;
    return this;
  }
  
  public DisplayNode hoverable(Supplier<Boolean> hoverable) {
    this.hoverable = hoverable;
    return this;
  }
  
  public boolean isHovered(double mouseX, double mouseY, boolean checkEnabled) {
    if (!((Boolean)this.hoverable.get()).booleanValue())
      return false; 
    return super.isHovered(mouseX, mouseY, checkEnabled);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\node\DisplayNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */