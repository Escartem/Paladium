package fr.paladium.palaclicker.client.ui.node.left;

import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;

public class ClickerFallingItemsSectionNode extends Node {
  private long lastUpdate;
  
  protected ClickerFallingItemsSectionNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    overflow(OverflowProperty.HIDDEN);
  }
  
  public static ClickerFallingItemsSectionNode create(double x, double y, double width, double height) {
    return new ClickerFallingItemsSectionNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (getChildren().isEmpty())
      for (int ox = 0; ox < 6; ox++) {
        for (int oy = 0; oy < 10; oy++) {
          double size = 60.0D + Math.random() * 50.0D;
          ClickerFallingItemNode.create(ox * dw(6.0D) - 20.0D + Math.random() * 40.0D, oy * dh(9.0D) - 40.0D + Math.random() * 80.0D, size)
            .rotation(Math.random() * 360.0D)
            .zindex((int)size)
            .attach(this);
        } 
      }  
    if (this.lastUpdate == 0L)
      this.lastUpdate = System.currentTimeMillis(); 
    long now = System.currentTimeMillis();
    long delta = now - this.lastUpdate;
    for (Node child : getChildren()) {
      child.y(child.getY() + delta * 0.1D * child.getHeight() / 100.0D);
      if (child.getY() > getHeight()) {
        child.y(-dh(7.0D));
        child.x(child.getDefaultX() - 20.0D + Math.random() * 40.0D);
        double size = 60.0D + Math.random() * 50.0D;
        child.size(size, size);
        child.zindex((int)size);
        ((ClickerFallingItemNode)child).rotation(Math.random() * 360.0D);
      } 
    } 
    this.lastUpdate = now;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\clien\\ui\node\left\ClickerFallingItemsSectionNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */