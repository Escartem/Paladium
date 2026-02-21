package fr.paladium.palamod.modules.paladium.client.ui.util;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;

public class ThreeToOneBarNode extends Node {
  private double thickness;
  
  private double regroupHeight;
  
  private final Color backgroundColor = new Color(64, 64, 78);
  
  protected ThreeToOneBarNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.thickness == 0.0D || this.regroupHeight == 0.0D)
      return; 
    DrawUtils.SHAPE.drawRect(getX(), getY(), this.thickness, getHeight() * this.regroupHeight, this.backgroundColor);
    DrawUtils.SHAPE.drawRect(getX() + getWidth() / 2.0D - this.thickness / 2.0D, getY(), this.thickness, getHeight(), this.backgroundColor);
    DrawUtils.SHAPE.drawRect(getX() + getWidth() - this.thickness, getY(), this.thickness, getHeight() * this.regroupHeight, this.backgroundColor);
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() * this.regroupHeight - this.thickness / 2.0D, getWidth(), this.thickness, this.backgroundColor);
  }
  
  public static ThreeToOneBarNode create(double x, double y, double width, double height) {
    return new ThreeToOneBarNode(x, y, width, height);
  }
  
  public <T extends ThreeToOneBarNode> T regroupHeight(double regroupHeight) {
    this.regroupHeight = regroupHeight;
    return (T)this;
  }
  
  public <T extends ThreeToOneBarNode> T thickness(double thickness) {
    this.thickness = thickness;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\u\\util\ThreeToOneBarNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */