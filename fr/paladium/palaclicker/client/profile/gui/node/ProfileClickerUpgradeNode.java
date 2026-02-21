package fr.paladium.palaclicker.client.profile.gui.node;

import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgrade;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;

public class ProfileClickerUpgradeNode extends Node {
  public static final Color BORDER_COLOR = new Color(51, 8, 5);
  
  public static final Color[] COLORS = new Color[] { new Color(239, 57, 38), new Color(249, 110, 95), new Color(186, 35, 19), new Color(170, 39, 25), new Color(146, 19, 12) };
  
  private static final Color[] DISABLED_COLORS = new Color[] { new Color(67, 67, 80), new Color(117, 117, 135), new Color(59, 59, 71), new Color(42, 42, 51), new Color(34, 34, 39) };
  
  private static final Color[] BUYED_COLORS = new Color[] { new Color(38, 94, 239), new Color(95, 157, 249), new Color(19, 86, 186), new Color(25, 74, 170), new Color(12, 50, 146) };
  
  private ClickerUpgrade upgrade;
  
  protected ProfileClickerUpgradeNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ProfileClickerUpgradeNode create(double x, double y, double width, double height) {
    return new ProfileClickerUpgradeNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    clearChildren();
    if (this.upgrade.getImage() != null && !this.upgrade.getImage().isEmpty()) {
      ImageNode.create(dw(2.0D) - dw(4.0D), dh(2.0D) - dw(4.0D) - dw(32.0D))
        .resource(this.upgrade.getImage())
        .linear(false)
        .width(dw(2.0D))
        .aspectRatio(1.0D)
        .attach(this);
    } else if (this.upgrade.getItem() != null && !this.upgrade.getItem().isEmpty()) {
      ItemNode.create(dw(2.0D) - dw(4.0D), dh(2.0D) - dw(4.0D) - dw(32.0D), dw(2.0D))
        .item(this.upgrade.getItemStack())
        .stackSize(false)
        .attach(this);
    } 
    clearHover();
    hoverLines(() -> "§5§l" + this.upgrade.getName());
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    double offset = dw(16.0D);
    double bigOffset = dw(11.7D);
    Color[] colors = isEnabled() ? BUYED_COLORS : DISABLED_COLORS;
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), colors[0]);
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), offset, colors[1]);
    DrawUtils.SHAPE.drawRect(getX(), getY(), offset, getHeight(), colors[2]);
    DrawUtils.SHAPE.drawRect(getX() + aw(-offset), getY(), offset, getHeight(), colors[2]);
    DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-bigOffset - offset), getWidth(), offset, colors[3]);
    DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-bigOffset), getWidth(), bigOffset, colors[4]);
  }
  
  public ProfileClickerUpgradeNode data(ClickerUpgrade upgrade) {
    this.upgrade = upgrade;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\client\profile\gui\node\ProfileClickerUpgradeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */