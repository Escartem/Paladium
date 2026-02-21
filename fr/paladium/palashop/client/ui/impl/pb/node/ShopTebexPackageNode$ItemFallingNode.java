package fr.paladium.palashop.client.ui.impl.pb.node;

import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import org.lwjgl.opengl.GL11;

class ItemFallingNode extends Node {
  private double rotation;
  
  protected ItemFallingNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ItemFallingNode create(double x, double y, double size) {
    return new ItemFallingNode(x, y, size, size);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(getX() + dw(2.0D), getY() + dh(2.0D), 0.0D);
    GL11.glRotated(this.rotation, 0.0D, 0.0D, 1.0D);
    GL11.glTranslated(-(getX() + dw(2.0D)), -(getY() + dh(2.0D)), 0.0D);
    DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), ResourceConstant.ITEM_PB);
    GL11.glPopMatrix();
  }
  
  public ItemFallingNode rotation(double rotation) {
    this.rotation = rotation;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\pb\node\ShopTebexPackageNode$ItemFallingNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */