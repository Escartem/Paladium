package fr.paladium.palaclicker.client.ui.node.left;

import fr.paladium.palaclicker.client.ui.UIClicker;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ClickerFallingItemNode extends Node {
  private double rotation;
  
  protected ClickerFallingItemNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ClickerFallingItemNode create(double x, double y, double size) {
    return new ClickerFallingItemNode(x, y, size, size);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (((UIClicker)getUi()).getItemSignal().getOrDefault() == null)
      return; 
    GL11.glPushMatrix();
    GL11.glTranslated(getX() + dw(2.0D), getY() + dh(2.0D), 0.0D);
    GL11.glRotated(this.rotation, 0.0D, 0.0D, 1.0D);
    GL11.glTranslated(-(getX() + dw(2.0D)), -(getY() + dh(2.0D)), 0.0D);
    DrawUtils.ITEM.drawItem(getX(), getY(), getWidth(), (ItemStack)((UIClicker)getUi()).getItemSignal().getOrDefault());
    GL11.glPopMatrix();
  }
  
  public ClickerFallingItemNode rotation(double rotation) {
    this.rotation = rotation;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\clien\\ui\node\left\ClickerFallingItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */