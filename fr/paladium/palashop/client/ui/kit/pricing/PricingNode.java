package fr.paladium.palashop.client.ui.kit.pricing;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.impl.buy.item.UIShopBuyItem;
import fr.paladium.palashop.client.ui.impl.buy.offer.UIShopBuyDefaultOffer;
import fr.paladium.palashop.client.ui.impl.buy.offer.UIShopBuyHorizontalOffer;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.IBuyable;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import lombok.NonNull;

public class PricingNode extends Node {
  private Color color = ColorConstant.DARK;
  
  public Color getColor() {
    return this.color;
  }
  
  private float radius = 0.0F;
  
  private ConditionalBuyableObject<? extends IBuyable> item;
  
  public float getRadius() {
    return this.radius;
  }
  
  public ConditionalBuyableObject<? extends IBuyable> getItem() {
    return this.item;
  }
  
  protected PricingNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    onClick((node, mouseX, mouseY, clickType) -> {
          if (this.item == null)
            return; 
          if (this.item.getObject().getItem() instanceof IShopItem) {
            ZUI.open((UI)new UIShopBuyItem((UIShopPage)getUi(), this.item), true);
          } else if (this.item.getObject().getItem() instanceof ShopOffer) {
            ShopOffer offer = (ShopOffer)this.item.getObject().getItem();
            if (ShopOffer.ShopOfferType.HORIZONTAL.equals(offer.getType())) {
              ZUI.open((UI)new UIShopBuyHorizontalOffer((UIShopPage)getUi(), this.item), true);
            } else if (ShopOffer.ShopOfferType.DEFAULT.equals(offer.getType())) {
              ZUI.open((UI)new UIShopBuyDefaultOffer((UIShopPage)getUi(), this.item), true);
            } 
          } 
        });
  }
  
  @NonNull
  public static PricingNode create(double x, double y, double width, double height) {
    return new PricingNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    if (!isMounted() || this.item == null) {
      RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
        .color(this.color)
        .effect((NodeEffect)RoundedNodeEffect.create(this.radius))
        .body(rect -> {
            Text nameText = Text.create("item name", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, (float)getHeight() * 0.17F, ColorConstant.LIGHT_GRAY)).overflow(TextOverflow.ELLIPSIS);
            Text priceText = Text.create("10.000", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, (float)getHeight() * 0.26F, Color.WHITE));
            double margin = getHeight() * 0.04D;
            double totalHeight = nameText.getHeight() + margin + priceText.getHeight();
            double x = 19.0D;
            double y = (getHeight() - totalHeight) / 2.0D;
            RectNode.create(19.0D, y, nameText.getWidth(), nameText.getHeight()).color(Color.LOADING).attach(rect);
            y += nameText.getHeight() + margin;
            RectNode.create(19.0D, y, priceText.getWidth() + getWidth() * 0.03D + 22.0D, priceText.getHeight()).color(Color.LOADING).attach(rect);
            ImageNode.create(aw(-19.0D), y + priceText.getHeight() * 0.15D).resource(ResourceConstant.ICON_BASKET).color(Color.LOADING).height(priceText.getHeight() * 0.7D).anchorX(Align.END).attach(rect);
          }).attach(this);
      return;
    } 
    ((RectNode)RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(this.color)
      .effect((NodeEffect)RoundedNodeEffect.create(this.radius))
      .onDraw((node, mouseX, mouseY, ticks) -> node.border(Color.TRANSPARENT.to(Color.WHITE, hoverValue(1.0F)), 2.0D)))
      .attach(this);
    Text nameText = Text.create(this.item.getObject().getItem().getName(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, (float)getHeight() * 0.17F, ColorConstant.LIGHT_GRAY)).overflow(TextOverflow.ELLIPSIS);
    Text priceText = Text.create((this.item.getObject().getPrice().longValue() == 0L) ? "Gratuit" : String.format("%,d", new Object[] { this.item.getObject().getPrice() }).replace(",", ".").replace(" ", "."), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, (float)getHeight() * 0.26F, (this.item.getObject().getItem() instanceof IShopItem && this.item.getObject().hasDiscount()) ? ColorConstant.PINK : Color.WHITE));
    double margin = getHeight() * 0.04D;
    double totalHeight = nameText.getHeight() + margin + priceText.getHeight();
    double x = 19.0D;
    double y = (getHeight() - totalHeight) / 2.0D;
    TextNode.create(19.0D, y)
      .text(nameText)
      .mode(TextMode.OVERFLOW)
      .width(aw(-38.0D))
      .attach(this);
    y += nameText.getHeight() + margin;
    if (this.item.getObject().getItem() instanceof ShopOffer && this.item.getErrorType() == ConditionalBuyableObject.ErrorType.OWNED) {
      FlexNode.horizontal(19.0D, y, priceText.getHeight())
        .align(Align.CENTER)
        .margin(getWidth() * 0.03D)
        .body(flex -> TextNode.create(0.0D, 0.0D).text(priceText.copy().text("Acheté")).attach(flex))
        
        .attach(this);
    } else if (this.item.getObject().getItem() instanceof IShopItem && !((IShopItem)this.item.getObject().getItem()).isBuyable().booleanValue()) {
      FlexNode.horizontal(19.0D, y, priceText.getHeight())
        .align(Align.CENTER)
        .margin(getWidth() * 0.03D)
        .body(flex -> TextNode.create(0.0D, 0.0D).text(priceText.copy().text(ShopRarityConstant.getName(this.item.getObject().getItem().getRarity()).toUpperCase())).attach(flex))
        
        .attach(this);
    } else {
      FlexNode.horizontal(19.0D, y, priceText.getHeight())
        .align(Align.CENTER)
        .margin(getWidth() * 0.03D)
        .body(flex -> {
            TextNode.create(0.0D, 0.0D).text(priceText).attach(flex);
            ImageNode.create(0.0D, 0.0D).resource(ResourceConstant.ITEM_PB).height(priceText.getHeight() * 0.7D).attach(flex);
            if (this.item.getObject().getItem().isSubscription())
              TextNode.create(0.0D, 0.0D).text(Text.create("/" + this.item.getSubscriptionFormatted(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, (float)getHeight() * 0.15F, ColorConstant.LIGHT_GRAY))).attach(flex); 
          }).attach(this);
    } 
    ImageNode.create(aw(-19.0D), y + priceText.getHeight() * 0.15D)
      .resource((this.item.isBuyable() || this.item.getErrorType() == ConditionalBuyableObject.ErrorType.BUYABLE) ? ResourceConstant.ICON_BASKET : ((this.item.getErrorType() == ConditionalBuyableObject.ErrorType.OWNED) ? ResourceConstant.ICON_BASKET_SUCCESS : ResourceConstant.ICON_BASKET_ERROR))
      .color((this.item.isBuyable() || this.item.getErrorType() == ConditionalBuyableObject.ErrorType.BUYABLE) ? ColorConstant.ORANGE : ((this.item.getErrorType() == ConditionalBuyableObject.ErrorType.OWNED) ? ColorConstant.GREEN : ColorConstant.RED))
      .height(priceText.getHeight() * 0.7D)
      .anchorX(Align.END)
      .attach(this);
    if (nameText.getWidth() > aw(-38.0D))
      hover(() -> this.item.getObject().getItem().getName()); 
  }
  
  @NonNull
  public final <T extends PricingNode> T color(@NonNull Color color) {
    if (color == null)
      throw new NullPointerException("color is marked non-null but is null"); 
    this.color = color;
    return (T)this;
  }
  
  @NonNull
  public final <T extends PricingNode> T radius(float radius) {
    this.radius = radius;
    return (T)this;
  }
  
  @NonNull
  public final <T extends PricingNode> T item(@NonNull ConditionalBuyableObject<? extends IBuyable> item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    this.item = item;
    return (T)this;
  }
  
  public boolean isHovered(double mouseX, double mouseY, boolean checkEnabled) {
    if (getParent() != null && getParent().isHovered(mouseX, mouseY, checkEnabled))
      return true; 
    return super.isHovered(mouseX, mouseY, checkEnabled);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\pricing\PricingNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */