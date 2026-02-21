package fr.paladium.palamod.modules.smeltery.ui.util;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;

public class TankNode extends LoadingNode {
  private final double stroke = 4.0D;
  
  private final Color loadColor = new Color(239, 57, 38);
  
  private final Color loadBorderColor = new Color(185, 28, 12);
  
  protected TankNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    getClass();
    double heightFilled = (getHeight() - 4.0D * 2.0D) * this.progress;
    getClass();
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), 4.0D, Color.WHITE);
    getClass();
    DrawUtils.SHAPE.drawRect(getX(), getY(), 4.0D, getHeight(), Color.WHITE);
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() - 4.0D, getWidth(), 4.0D, Color.WHITE);
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX() + getWidth() - 4.0D, getY(), 4.0D, getHeight(), Color.WHITE);
    getClass();
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX() + 4.0D, getY() + getHeight() - 4.0D - heightFilled, getWidth() - 4.0D * 2.0D, heightFilled, this.loadColor);
    if (this.progress <= 0.96D && this.progress >= 0.02D) {
      getClass();
      getClass();
      getClass();
      DrawUtils.SHAPE.drawRect(getX() + 4.0D, getY() + getHeight() - 4.0D - heightFilled, getWidth() - 4.0D * 2.0D, 5.0D, this.loadBorderColor);
    } 
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() / 2.0D * 0.25D - 4.0D / 2.0D, 15.0D, 4.0D, Color.WHITE);
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() / 2.0D * 0.5D - 4.0D / 2.0D, 25.0D, 4.0D, Color.WHITE);
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() / 2.0D * 0.75D - 4.0D / 2.0D, 15.0D, 4.0D, Color.WHITE);
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() / 2.0D * 1.25D - 4.0D / 2.0D, 15.0D, 4.0D, Color.WHITE);
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() / 2.0D * 1.5D - 4.0D / 2.0D, 25.0D, 4.0D, Color.WHITE);
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() / 2.0D * 1.75D - 4.0D / 2.0D, 15.0D, 4.0D, Color.WHITE);
    getClass();
    getClass();
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() / 2.0D - 4.0D / 2.0D, 35.0D, 4.0D, Color.WHITE);
  }
  
  public static TankNode create(double x, double y, double width, double height) {
    return new TankNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smelter\\u\\util\TankNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */