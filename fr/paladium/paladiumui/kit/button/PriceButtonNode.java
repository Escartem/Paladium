package fr.paladium.paladiumui.kit.button;

import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class PriceButtonNode extends ButtonNode {
  private static final TextInfo PRICE_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 30.0F, Color.WHITE);
  
  private static final TextInfo TITLE_INFO = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 19.0F, Color.WHITE).shadow(new Color(0.0F, 0.0F, 0.0F, 0.4F)).shadow(1.0F, 1.0F);
  
  private static final TextInfo DESCRIPTION_INFO = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 10.0F, Color.WHITE);
  
  private long price;
  
  private String title;
  
  private String description;
  
  private TextInfo priceInfo;
  
  private TextInfo titleInfo;
  
  private TextInfo descriptionInfo;
  
  private double margin;
  
  public long getPrice() {
    return this.price;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public TextInfo getPriceInfo() {
    return this.priceInfo;
  }
  
  public TextInfo getTitleInfo() {
    return this.titleInfo;
  }
  
  public TextInfo getDescriptionInfo() {
    return this.descriptionInfo;
  }
  
  public double getMargin() {
    return this.margin;
  }
  
  protected PriceButtonNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.priceInfo = PRICE_INFO;
    this.titleInfo = TITLE_INFO;
    this.descriptionInfo = DESCRIPTION_INFO;
    this.margin = 30.0D;
  }
  
  @NonNull
  public static PriceButtonNode create(double x, double y, double width, double height) {
    return new PriceButtonNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.title == null || this.title.isEmpty())
      return; 
    Resource pbResource = ResourceConstant.PB.blocking();
    Text priceText = Text.create(Long.valueOf(this.price), this.priceInfo);
    pbResource.prepareBind();
    double pbWidth = pbResource.getWidth() * priceText.getHeight() / pbResource.getHeight();
    double pbHeight = priceText.getHeight();
    FlexNode.horizontal(aw(-this.margin), 0.0D, h())
      .margin(8.0D)
      .align(Align.CENTER)
      .body(flex -> {
          TextNode.create(0.0D, 0.0D).text(priceText).attach(flex);
          ImageNode.create(0.0D, 0.0D).resource(pbResource).size(pbWidth, pbHeight).attach(flex);
        }).anchorX(Align.END)
      .attach(this);
    RectNode.create(aw(-this.margin) - priceText.getWidth() - 8.0D - pbWidth - this.margin, dh(4.0D), 2.0D, dh(2.0D))
      .color(Color.BLACK.copyAlpha(0.4F))
      .attach(this);
    FlexNode.vertical(this.margin, dh(2.0D), aw(-this.margin) - priceText.getWidth() - 8.0D - pbWidth - this.margin - this.margin - 10.0D)
      .body(flex -> {
          TextNode.create(0.0D, 0.0D).text(Text.create(this.title.toUpperCase(), this.titleInfo).overflow(TextOverflow.ELLIPSIS)).mode(TextMode.OVERFLOW).width(flex.w()).attach(flex);
          if (this.description != null && !this.description.isEmpty())
            TextNode.create(0.0D, 0.0D).text(Text.create(this.description.toUpperCase(), this.descriptionInfo).overflow(TextOverflow.ELLIPSIS)).mode(TextMode.OVERFLOW).width(flex.w()).attach(flex); 
        }).anchorY(Align.CENTER)
      .attach(this);
  }
  
  @NonNull
  public static TextInfo getDefaultPriceInfo() {
    return PRICE_INFO;
  }
  
  @NonNull
  public static TextInfo getDefaultTitleInfo() {
    return TITLE_INFO;
  }
  
  @NonNull
  public static TextInfo getDefaultDescriptionInfo() {
    return DESCRIPTION_INFO;
  }
  
  @NonNull
  public <T extends PriceButtonNode> T price(long price) {
    this.price = price;
    return (T)this;
  }
  
  @NonNull
  public <T extends PriceButtonNode> T title(@NonNull String title) {
    if (title == null)
      throw new NullPointerException("title is marked non-null but is null"); 
    this.title = title;
    return (T)this;
  }
  
  @NonNull
  public <T extends PriceButtonNode> T description(@NonNull String description) {
    if (description == null)
      throw new NullPointerException("description is marked non-null but is null"); 
    this.description = description;
    return (T)this;
  }
  
  @NonNull
  public <T extends PriceButtonNode> T priceInfo(@NonNull TextInfo priceInfo) {
    if (priceInfo == null)
      throw new NullPointerException("priceInfo is marked non-null but is null"); 
    this.priceInfo = priceInfo;
    return (T)this;
  }
  
  @NonNull
  public <T extends PriceButtonNode> T titleInfo(@NonNull TextInfo titleInfo) {
    if (titleInfo == null)
      throw new NullPointerException("titleInfo is marked non-null but is null"); 
    this.titleInfo = titleInfo;
    return (T)this;
  }
  
  @NonNull
  public <T extends PriceButtonNode> T descriptionInfo(@NonNull TextInfo descriptionInfo) {
    if (descriptionInfo == null)
      throw new NullPointerException("descriptionInfo is marked non-null but is null"); 
    this.descriptionInfo = descriptionInfo;
    return (T)this;
  }
  
  @NonNull
  public <T extends PriceButtonNode> T margin(double margin) {
    this.margin = margin;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\button\PriceButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */