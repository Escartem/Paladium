package fr.paladium.paladiumui.kit.button;

import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import lombok.NonNull;

public class IconButtonNode extends ButtonNode {
  private Resource icon;
  
  private double iconSize;
  
  public Resource getIcon() {
    return this.icon;
  }
  
  public double getIconSize() {
    return this.iconSize;
  }
  
  protected IconButtonNode(double x, double y, double size) {
    super(x, y);
    this.iconSize = size;
    padding(4.0D);
  }
  
  @NonNull
  public static IconButtonNode create(double x, double y, double size) {
    return new IconButtonNode(x, y, size);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    super.draw(mouseX, mouseY, ticks);
    if (getWidth() == 0.0D)
      width(this.iconSize + getHorizontalPadding() * 2.0D); 
    if (getHeight() == 0.0D)
      height(this.iconSize + getVerticalPadding() * 2.0D); 
    if (this.icon != null)
      DrawUtils.RESOURCE.drawImage(getX() + dw(2.0D) - this.iconSize / 2.0D, getY() + dh(2.0D) - this.iconSize / 2.0D, this.iconSize, this.iconSize, this.icon); 
  }
  
  @NonNull
  public <T extends IconButtonNode> T icon(@NonNull Resource icon) {
    if (icon == null)
      throw new NullPointerException("icon is marked non-null but is null"); 
    this.icon = icon;
    return (T)this;
  }
  
  @NonNull
  public <T extends IconButtonNode> T iconSize(double iconSize) {
    this.iconSize = iconSize;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\button\IconButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */