package fr.paladium.palaclicker.client.ui.node.right;

import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopItem;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClickerShopBuildingNode extends Node {
  public static final Color BORDER_COLOR = new Color(51, 8, 5);
  
  public static final Color[] COLORS = new Color[] { new Color(239, 57, 38), new Color(249, 110, 95), new Color(186, 35, 19), new Color(170, 39, 25), new Color(146, 19, 12) };
  
  private ClickerShopItem shopItem;
  
  protected ClickerShopBuildingNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static ClickerShopBuildingNode create(double x, double y, double width, double height) {
    return new ClickerShopBuildingNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    clearChildren();
    ItemNode.create(dw(2.0D) - dw(4.0D), dh(2.0D) - dw(4.0D) - dw(32.0D), dw(2.0D))
      .item(this.shopItem.getItemStack())
      .attach(this);
    hoverLines(() -> this.shopItem.getItemStack().func_82840_a((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, true));
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    double offset = dw(16.0D);
    double bigOffset = dw(11.7D);
    Color[] colors = COLORS;
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), colors[0]);
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), offset, colors[1]);
    DrawUtils.SHAPE.drawRect(getX(), getY(), offset, getHeight(), colors[2]);
    DrawUtils.SHAPE.drawRect(getX() + aw(-offset), getY(), offset, getHeight(), colors[2]);
    DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-bigOffset - offset), getWidth(), offset, colors[3]);
    DrawUtils.SHAPE.drawRect(getX(), getY() + ah(-bigOffset), getWidth(), bigOffset, colors[4]);
  }
  
  public ClickerShopBuildingNode data(ClickerShopItem shopItem) {
    this.shopItem = shopItem;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\clien\\ui\node\right\ClickerShopBuildingNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */