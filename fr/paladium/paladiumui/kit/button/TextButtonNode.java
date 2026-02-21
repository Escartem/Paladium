package fr.paladium.paladiumui.kit.button;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class TextButtonNode extends ButtonNode {
  private static final TextInfo TEXT_INFO = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 19.0F, Color.WHITE).shadow(new Color(0.0F, 0.0F, 0.0F, 0.4F)).shadow(1.0F, 1.0F);
  
  private String text;
  
  private Resource icon;
  
  private TextInfo textInfo;
  
  public String getText() {
    return this.text;
  }
  
  public Resource getIcon() {
    return this.icon;
  }
  
  private double iconMargin = 10.0D;
  
  public double getIconMargin() {
    return this.iconMargin;
  }
  
  private double iconSize = 29.0D;
  
  public double getIconSize() {
    return this.iconSize;
  }
  
  private IconPosition iconPosition = IconPosition.RIGHT;
  
  public IconPosition getIconPosition() {
    return this.iconPosition;
  }
  
  protected TextButtonNode(double x, double y) {
    super(x, y);
    this.textInfo = TEXT_INFO;
  }
  
  @NonNull
  public static TextButtonNode create(double x, double y) {
    return new TextButtonNode(x, y);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    super.draw(mouseX, mouseY, ticks);
    if (this.text == null)
      return; 
    if (getWidth() == 0.0D) {
      width(this.textInfo.getWidth(this.text) + getHorizontalPadding() * 2.0D);
      if (this.icon != null)
        width(getWidth() + this.iconSize + this.iconMargin * 2.0D); 
    } 
    if (getHeight() == 0.0D) {
      height(this.textInfo.getHeight() + getVerticalPadding() * 2.0D);
      if (this.icon != null) {
        double iconHeight = this.iconSize + getVerticalPadding() * 2.0D;
        if (iconHeight > getHeight())
          height(iconHeight); 
      } 
    } 
    if (this.icon != null) {
      double textWidth = this.textInfo.getWidth(this.text);
      double totalWidth = textWidth + this.iconSize + this.iconMargin;
      double textX = getX() + dw(2.0D) - totalWidth / 2.0D;
      if (this.iconPosition == IconPosition.RIGHT) {
        DrawUtils.TEXT.drawText(textX, getY() + dh(2.0D) - this.textInfo.dh(2.0D), Text.create(this.text, this.textInfo));
        DrawUtils.RESOURCE.drawImage(textX + textWidth + this.iconMargin, getY() + dh(2.0D) - this.iconSize / 2.0D, this.iconSize, this.iconSize, this.icon);
      } else {
        DrawUtils.RESOURCE.drawImage(textX, getY() + dh(2.0D) - this.iconSize / 2.0D, this.iconSize, this.iconSize, this.icon);
        DrawUtils.TEXT.drawText(textX + this.iconSize + this.iconMargin, getY() + dh(2.0D) - this.textInfo.dh(2.0D), Text.create(this.text, this.textInfo));
      } 
    } else {
      DrawUtils.TEXT.drawText(getX() + dw(2.0D), getY() + dh(2.0D) - this.textInfo.dh(2.0D), Text.create(this.text, this.textInfo, Align.CENTER));
    } 
  }
  
  @NonNull
  public static TextInfo getTextInfo() {
    return TEXT_INFO;
  }
  
  @NonNull
  public <T extends TextButtonNode> T text(@NonNull String text) {
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    this.text = text;
    width(0.0D);
    height(0.0D);
    return (T)this;
  }
  
  @NonNull
  public <T extends TextButtonNode> T icon(@NonNull Resource icon) {
    if (icon == null)
      throw new NullPointerException("icon is marked non-null but is null"); 
    this.icon = icon;
    width(0.0D);
    height(0.0D);
    return (T)this;
  }
  
  @NonNull
  public <T extends TextButtonNode> T textInfo(@NonNull TextInfo textInfo) {
    if (textInfo == null)
      throw new NullPointerException("textInfo is marked non-null but is null"); 
    this.textInfo = textInfo;
    return (T)this;
  }
  
  @NonNull
  public <T extends TextButtonNode> T iconMargin(double iconMargin) {
    this.iconMargin = iconMargin;
    return (T)this;
  }
  
  @NonNull
  public <T extends TextButtonNode> T iconSize(double iconSize) {
    this.iconSize = iconSize;
    return (T)this;
  }
  
  @NonNull
  public <T extends TextButtonNode> T iconPosition(@NonNull IconPosition iconPosition) {
    if (iconPosition == null)
      throw new NullPointerException("iconPosition is marked non-null but is null"); 
    this.iconPosition = iconPosition;
    return (T)this;
  }
  
  public enum IconPosition {
    LEFT, RIGHT;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\button\TextButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */