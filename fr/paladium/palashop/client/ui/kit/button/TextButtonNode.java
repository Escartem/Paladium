package fr.paladium.palashop.client.ui.kit.button;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

public class TextButtonNode extends ButtonNode {
  private static final TextInfo TEXT_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 20.0F, Color.WHITE);
  
  private String text;
  
  private Resource resource;
  
  private double margin = 10.0D;
  
  private double resourceHeight;
  
  private double resourcePositionY;
  
  private boolean resourceColored;
  
  protected TextButtonNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static TextButtonNode create(double x, double y, double width, double height) {
    return new TextButtonNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    super.draw(mouseX, mouseY, ticks);
    Resource localResource = isLoading() ? ResourceConstant.ICON_LOADING : this.resource;
    String localText = isLoading() ? null : this.text;
    if (localResource != null)
      localResource.prepareBind(); 
    Color disabledContrastColor = (getDisabledColor().getRed() > 120 || getDisabledColor().getGreen() > 120 || getDisabledColor().getBlue() > 120) ? getDisabledColor().darker(0.5F) : getDisabledColor().brighter(0.8F);
    Color animatedColor = (isEnabled() || isLoading()) ? getHoveredColor().to(getColor(), hoverValue(1.0F)) : disabledContrastColor;
    TextInfo textInfo = TEXT_INFO.copy().color(animatedColor);
    if (localText == null) {
      if (localResource != null && localResource.getWidth() != 0 && localResource.getHeight() != 0) {
        if (this.resourceHeight == 0.0D)
          this.resourceHeight = textInfo.getHeight(); 
        double d1 = localResource.getWidth() * this.resourceHeight / localResource.getHeight();
        double d2 = getX() + (getWidth() - d1) / 2.0D;
        double d3 = getY() + this.resourcePositionY + getHeight() / 2.0D - this.resourceHeight / 2.0D;
        GL11.glPushMatrix();
        if (!this.resourceColored)
          animatedColor.bind(); 
        if (isLoading()) {
          GL11.glTranslated(0.0D, -this.resourcePositionY, 0.0D);
          GL11.glTranslated(d2 + d1 / 2.0D, d3 + this.resourceHeight / 2.0D, 0.0D);
          GL11.glRotated((System.currentTimeMillis() / 2L % 360L), 0.0D, 0.0D, 1.0D);
          GL11.glTranslated(-(d2 + d1 / 2.0D), -(d3 + this.resourceHeight / 2.0D), 0.0D);
        } 
        DrawUtils.RESOURCE.drawImage(d2, d3, d1, this.resourceHeight, localResource);
        Color.reset();
        GL11.glPopMatrix();
      } 
      return;
    } 
    Text text = Text.create(localText, textInfo);
    if (localResource == null || localResource.getWidth() == 0 || localResource.getHeight() == 0) {
      DrawUtils.TEXT.drawText(getX(), getY(), getWidth(), getHeight(), text.copy().align(Align.CENTER, Align.CENTER).overflow(TextOverflow.ELLIPSIS), TextMode.OVERFLOW);
      return;
    } 
    double textWidth = text.getWidth();
    double textHeight = text.getHeight();
    if (this.resourceHeight == 0.0D)
      this.resourceHeight = textHeight; 
    double resourceWidth = localResource.getWidth() * this.resourceHeight / localResource.getHeight();
    double totalWidth = textWidth + this.margin + resourceWidth;
    double textX = getX() + (getWidth() - totalWidth) / 2.0D;
    double textY = getY() + (getHeight() - textHeight) / 2.0D;
    double resourceX = textX + textWidth + this.margin;
    double resourceY = getY() + this.resourcePositionY + getHeight() / 2.0D - this.resourceHeight / 2.0D;
    DrawUtils.TEXT.drawText(textX, textY, text);
    if (!this.resourceColored)
      animatedColor.bind(); 
    DrawUtils.RESOURCE.drawImage(resourceX, resourceY, resourceWidth, this.resourceHeight, localResource);
    Color.reset();
  }
  
  @NonNull
  public TextButtonNode text(@NonNull String text) {
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    this.text = text;
    return this;
  }
  
  @NonNull
  public TextButtonNode resource(Resource resource) {
    this.resource = resource;
    return this;
  }
  
  @NonNull
  public TextButtonNode resource(Resource resource, double resourceHeight) {
    this.resource = resource;
    this.resourceHeight = resourceHeight;
    return this;
  }
  
  @NonNull
  public TextButtonNode resource(Resource resource, double resourceHeight, double resourcePositionY) {
    this.resource = resource;
    this.resourceHeight = resourceHeight;
    this.resourcePositionY = resourcePositionY;
    return this;
  }
  
  @NonNull
  public TextButtonNode resource(Resource resource, double resourceHeight, double resourcePositionY, boolean resourceColored) {
    this.resource = resource;
    this.resourceHeight = resourceHeight;
    this.resourcePositionY = resourcePositionY;
    this.resourceColored = resourceColored;
    return this;
  }
  
  @NonNull
  public TextButtonNode resourceHeight(double resourceHeight) {
    this.resourceHeight = resourceHeight;
    return this;
  }
  
  @NonNull
  public TextButtonNode resourcePositionY(double resourcePositionY) {
    this.resourcePositionY = resourcePositionY;
    return this;
  }
  
  @NonNull
  public TextButtonNode resourceColored(boolean resourceColored) {
    this.resourceColored = resourceColored;
    return this;
  }
  
  @NonNull
  public TextButtonNode margin(double margin) {
    this.margin = margin;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\button\TextButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */