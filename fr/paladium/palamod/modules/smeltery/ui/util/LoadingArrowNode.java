package fr.paladium.palamod.modules.smeltery.ui.util;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;

public class LoadingArrowNode extends LoadingNode {
  private double thickness;
  
  private ArrowPointing direction = ArrowPointing.RIGHT;
  
  protected LoadingArrowNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.thickness == 0.0D)
      return; 
    drawArrow(this.backgroundColor);
    if (this.progress > 0.0D) {
      double heightProgressDown;
      double heightProgressUP;
      switch (this.direction) {
        case LEFT:
          getUi().mask(getX() + getWidth() * (1.0D - this.progress), getY(), this.progress * getWidth(), getHeight(), () -> drawArrow(this.fillColor));
          break;
        case RIGHT:
          getUi().mask(getX(), getY(), getWidth() * this.progress, getHeight(), () -> drawArrow(this.fillColor));
          break;
        case DOWN:
          heightProgressDown = getHeight() * this.progress;
          getUi().mask(getX(), getY(), getWidth(), heightProgressDown, () -> drawArrow(this.fillColor));
          break;
        case UP:
          heightProgressUP = getHeight() * this.progress;
          getUi().mask(getX(), getY() + getHeight() - heightProgressUP, getWidth(), heightProgressUP, () -> drawArrow(this.fillColor));
          break;
      } 
    } 
  }
  
  private void drawArrow(Color color) {
    switch (this.direction) {
      case LEFT:
        DrawUtils.SHAPE.drawRect(getX(), getY() + dh(2.0D) - this.thickness / 2.0D, getWidth(), this.thickness, color);
        DrawUtils.SHAPE.drawRect(getX() + this.thickness, getY() + (getHeight() - this.thickness * 3.0D) / 2.0D, this.thickness, this.thickness * 3.0D, color);
        DrawUtils.SHAPE.drawRect(getX() + this.thickness * 2.0D, getY() + (getHeight() - this.thickness * 5.0D) / 2.0D, this.thickness, this.thickness * 5.0D, color);
        break;
      case RIGHT:
        DrawUtils.SHAPE.drawRect(getX(), getY() + dh(2.0D) - this.thickness / 2.0D, getWidth(), this.thickness, color);
        DrawUtils.SHAPE.drawRect(getX() + getWidth() - this.thickness * 2.0D, getY() + (getHeight() - this.thickness * 3.0D) / 2.0D, this.thickness, this.thickness * 3.0D, color);
        DrawUtils.SHAPE.drawRect(getX() + getWidth() - this.thickness * 3.0D, getY() + (getHeight() - this.thickness * 5.0D) / 2.0D, this.thickness, this.thickness * 5.0D, color);
        break;
      case UP:
        DrawUtils.SHAPE.drawRect(getX() + dw(2.0D) - this.thickness / 2.0D, getY(), this.thickness, getHeight(), color);
        DrawUtils.SHAPE.drawRect(getX() + (getWidth() - this.thickness * 3.0D) / 2.0D, getY() + this.thickness, this.thickness * 3.0D, this.thickness, color);
        DrawUtils.SHAPE.drawRect(getX() + (getWidth() - this.thickness * 5.0D) / 2.0D, getY() + this.thickness * 2.0D, this.thickness * 5.0D, this.thickness, color);
        break;
      case DOWN:
        DrawUtils.SHAPE.drawRect(getX() + dw(2.0D) - this.thickness / 2.0D, getY(), this.thickness, getHeight(), color);
        DrawUtils.SHAPE.drawRect(getX() + (getWidth() - this.thickness * 3.0D) / 2.0D, getY() + getHeight() - this.thickness * 2.0D, this.thickness * 3.0D, this.thickness, color);
        DrawUtils.SHAPE.drawRect(getX() + (getWidth() - this.thickness * 5.0D) / 2.0D, getY() + getHeight() - this.thickness * 3.0D, this.thickness * 5.0D, this.thickness, color);
        break;
    } 
  }
  
  public static <T extends LoadingArrowNode> T create(double x, double y, double width, double height) {
    return (T)new LoadingArrowNode(x, y, width, height);
  }
  
  public <T extends LoadingArrowNode> T thickness(double thickness) {
    this.thickness = thickness;
    return (T)this;
  }
  
  public <T extends LoadingArrowNode> T point(ArrowPointing direction) {
    this.direction = direction;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smelter\\u\\util\LoadingArrowNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */