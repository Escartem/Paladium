package fr.paladium.paladiumui.kit.button;

import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import lombok.NonNull;

public class HexaButtonNode extends Node {
  private boolean border;
  
  private Resource icon;
  
  protected HexaButtonNode(double x, double y) {
    super(x, y);
    border(false);
  }
  
  @NonNull
  public static HexaButtonNode create(double x, double y) {
    return new HexaButtonNode(x, y);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    Resource resource = this.border ? ResourceConstant.HEXA : ResourceConstant.HEXA_BORDER;
    DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), resource);
    if (this.icon != null) {
      double iconFactor = this.border ? 2.297872340425532D : 2.0D;
      double iconWidth = dw(iconFactor);
      double iconHeight = this.icon.getHeight() * iconWidth / this.icon.getWidth();
      DrawUtils.RESOURCE.drawScaledImageWidth(getX() + dw(2.0D) - iconWidth / 2.0D, getY() + dh(2.0D) - iconHeight / 2.0D, dw(iconFactor), this.icon);
    } 
  }
  
  @NonNull
  public <T extends HexaButtonNode> T border(boolean border) {
    this.border = border;
    size(border ? 54.0D : 47.0D, border ? 62.0D : 54.0D);
    return (T)this;
  }
  
  @NonNull
  public <T extends HexaButtonNode> T icon(@NonNull Resource icon) {
    if (icon == null)
      throw new NullPointerException("icon is marked non-null but is null"); 
    this.icon = icon;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\button\HexaButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */