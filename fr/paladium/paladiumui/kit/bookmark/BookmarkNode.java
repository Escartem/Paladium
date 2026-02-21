package fr.paladium.paladiumui.kit.bookmark;

import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class BookmarkNode extends Node {
  private static final TextInfo TEXT_INFO = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 48.0F, Color.WHITE).shadow(new Color(0.0F, 0.0F, 0.0F, 0.25F)).shadow(0.0F, 4.0F);
  
  private String iconString;
  
  private Resource icon;
  
  protected BookmarkNode(double x, double y) {
    super(x, y, 164.0D, 150.0D);
  }
  
  @NonNull
  public static BookmarkNode create(double x, double y) {
    return new BookmarkNode(x, y);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    DrawUtils.RESOURCE.drawImage(getX(), getY(), getWidth(), getHeight(), ResourceConstant.BOOKMARK);
    if (this.icon != null) {
      double iconFactor = 2.5D;
      double iconWidth = dw(2.5D);
      double iconHeight = this.icon.getHeight() * iconWidth / this.icon.getWidth();
      DrawUtils.RESOURCE.drawScaledImageWidth(getX() + dw(2.0D) - iconWidth / 2.0D, getY() + dh(2.7D) - iconHeight / 2.0D, dw(2.5D), this.icon);
    } else if (this.iconString != null) {
      TextInfo info = TEXT_INFO.copy().fontSize((int)dh(2.5D));
      DrawUtils.TEXT.drawText(getX() + dw(2.0D) - dw(20.0D), getY() + dh(2.7D) - info.dh(2.0D), Text.create(this.iconString, info, Align.CENTER));
    } 
  }
  
  @NonNull
  public <T extends BookmarkNode> T icon(@NonNull String icon) {
    if (icon == null)
      throw new NullPointerException("icon is marked non-null but is null"); 
    this.iconString = icon;
    return (T)this;
  }
  
  @NonNull
  public <T extends BookmarkNode> T icon(@NonNull Resource icon) {
    if (icon == null)
      throw new NullPointerException("icon is marked non-null but is null"); 
    this.icon = icon;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\bookmark\BookmarkNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */