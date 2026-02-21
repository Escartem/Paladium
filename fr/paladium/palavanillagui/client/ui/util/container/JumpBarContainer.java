package fr.paladium.palavanillagui.client.ui.util.container;

import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import net.minecraft.client.Minecraft;

public class JumpBarContainer extends Node {
  private final Color background = new Color(50, 50, 50);
  
  private final Color background_bottom = new Color(26, 26, 26);
  
  private final Color orangeFiller = new Color(255, 148, 22);
  
  private final Color orangeFillerBottom = new Color(174, 76, 21);
  
  private final Color blueFiller = new Color(57, 101, 215);
  
  private final Color blueFillerBottom = new Color(25, 46, 102);
  
  protected JumpBarContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static JumpBarContainer create(double x, double y, double width, double height) {
    return new JumpBarContainer(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    float charge = (Minecraft.func_71410_x()).field_71439_g.func_110319_bJ();
    double orangeWidth = getWidth() * 0.85D;
    double blueWidth = getWidth() * 0.15D;
    double loadOrange = orangeWidth * ((charge <= 0.85D) ? (charge / 0.85D) : 1.0D);
    double blueCharge = ((charge - 0.85F) < 0.0D) ? 0.0D : (charge - 0.85F);
    double loadBlue = blueWidth * blueCharge / 0.15D;
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), 12.0D, this.background);
    DrawUtils.SHAPE.drawRect(getX(), getY() + 12.0D, getWidth(), 4.0D, this.background_bottom);
    DrawUtils.SHAPE.drawRect(getX(), getY(), loadOrange, 12.0D, this.orangeFiller);
    DrawUtils.SHAPE.drawRect(getX(), getY() + 12.0D, loadOrange, 4.0D, this.orangeFillerBottom);
    DrawUtils.SHAPE.drawRect(getX() + orangeWidth, getY(), loadBlue, 12.0D, this.blueFiller);
    DrawUtils.SHAPE.drawRect(getX() + orangeWidth, getY() + 12.0D, loadBlue, 4.0D, this.blueFillerBottom);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\container\JumpBarContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */