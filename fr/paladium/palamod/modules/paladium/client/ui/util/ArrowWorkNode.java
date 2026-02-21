package fr.paladium.palamod.modules.paladium.client.ui.util;

import fr.paladium.palamod.modules.paladium.common.logics.TileCobbleBreaker;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;

public class ArrowWorkNode extends Node {
  private final Color backgroundColor = new Color(63, 63, 63);
  
  private TileCobbleBreaker tile;
  
  public ArrowWorkNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawRect(getX() + aw(-12.0D) / 2.0D, getY(), 12.0D, getHeight(), this.backgroundColor);
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() - 36.0D, getWidth(), 12.0D, this.backgroundColor);
    DrawUtils.SHAPE.drawRect(getX() + 12.0D, getY() + getHeight() - 24.0D, getWidth() - 24.0D, 12.0D, this.backgroundColor);
    double workHeight = getHeight() * this.tile.getWork() / this.tile.getUpgrade().getWork();
    getUi().mask(getX(), getY(), getWidth(), workHeight, () -> {
          DrawUtils.SHAPE.drawRect(getX() + aw(-12.0D) / 2.0D, getY(), 12.0D, getHeight(), Color.WHITE);
          DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() - 36.0D, getWidth(), 12.0D, Color.WHITE);
          DrawUtils.SHAPE.drawRect(getX() + 12.0D, getY() + getHeight() - 24.0D, getWidth() - 24.0D, 12.0D, Color.WHITE);
        });
  }
  
  public static ArrowWorkNode create(double x, double y, double width, double height) {
    return new ArrowWorkNode(x, y, width, height);
  }
  
  public <T extends ArrowWorkNode> T tileEntity(TileCobbleBreaker tile) {
    this.tile = tile;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\u\\util\ArrowWorkNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */