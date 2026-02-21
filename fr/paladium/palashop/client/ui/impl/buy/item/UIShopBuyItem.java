package fr.paladium.palashop.client.ui.impl.buy.item;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.api.server.shop.response.user.ShopBuyResponse;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.impl.buy.popup.UIShopBuyErrorPopup;
import fr.paladium.palashop.client.ui.impl.buy.popup.UIShopBuySuccessPopup;
import fr.paladium.palashop.client.ui.kit.button.TextButtonNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.palashop.client.ui.kit.image.GlowImageNode;
import fr.paladium.palashop.client.ui.kit.render.impl.ShopElementRenderRendererNode;
import fr.paladium.palashop.common.shop.network.item.BBPacketBuyItem;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
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
import org.apache.commons.lang3.StringUtils;

public class UIShopBuyItem extends UIShopPage implements UIShopPage.IBackableUI {
  private final UIShopPage back;
  
  private final ConditionalBuyableObject<IShopItem> buyable;
  
  public UIShopPage getBack() {
    return this.back;
  }
  
  public ConditionalBuyableObject<IShopItem> getBuyable() {
    return this.buyable;
  }
  
  public UIShopBuyItem(@NonNull UIShopPage back, @NonNull ConditionalBuyableObject<IShopItem> buyable) {
    super(back.getElement());
    if (back == null)
      throw new NullPointerException("back is marked non-null but is null"); 
    if (buyable == null)
      throw new NullPointerException("buyable is marked non-null but is null"); 
    this.back = back;
    this.buyable = buyable;
  }
  
  public void init() {
    super.init();
    TextNode.create(42.0D, 147.0D)
      .text(Text.create(getElement().getName().toUpperCase() + " > ", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 15.0F, ColorConstant.LIGHT_GRAY)).add(Text.create(((IShopItem)this.buyable.getObject().getItem()).getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 15.0F, ColorConstant.LIGHT_GRAY))))
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close((UI)this))
      .attach((UI)this);
    TextNode.create(42.0D, 177.0D)
      .text(Text.create(((IShopItem)this.buyable.getObject().getItem()).getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 54.0F, Color.WHITE)))
      .attach((UI)this);
    RectNode.create(42.0D, 252.0D, 1836.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    RectNode.create(42.0D, 306.0D, 716.0D, 716.0D)
      .color(ColorConstant.GRAY)
      .effect((NodeEffect)RoundedNodeEffect.create(20.0F))
      .body(rect -> {
          ShopElementRenderRendererNode.create(0.0D, 0.0D, rect.getWidth(), rect.getHeight()).object(this.buyable.getObject().getItem()).attach(rect);
          ImageNode.create(rect.aw(-22.0D), 22.0D).resource(ShopRarityConstant.getResource(((IShopItem)this.buyable.getObject().getItem()).getRarity())).width(107.0D).anchorX(Align.END).attach(rect);
        }).attach((UI)this);
    FlexNode.horizontal(840.0D, 306.0D, 56.0D)
      .margin(20.0D)
      .body(flex -> {
          Text text = Text.create(ShopRarityConstant.getName(((IShopItem)this.buyable.getObject().getItem()).getRarity()).toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BLACK, 22.0F, Color.WHITE).shadow(ShopRarityConstant.getColor(((IShopItem)this.buyable.getObject().getItem()).getRarity()).darker(0.5F)).shadow(0.0F, 1.0F)).align(Align.CENTER, Align.CENTER);
          RectNode.create(0.0D, 0.0D, text.getWidth() + 120.0D, flex.getHeight()).color(ShopRarityConstant.getColor(((IShopItem)this.buyable.getObject().getItem()).getRarity())).effect((NodeEffect)RoundedNodeEffect.create(3.0F)).body(()).attach(flex);
          if (this.buyable.getObject().hasDiscount())
            ImageNode.create(0.0D, 0.0D).resource(ResourceConstant.ICON_DISCOUNT).height(flex.getHeight()).attach(flex); 
        }).attach((UI)this);
    FlexNode.vertical(840.0D, 389.0D, 1038.0D)
      .margin(30.0D)
      .body(flex -> {
          TextNode.create(0.0D, 0.0D).text(Text.create(StringUtils.capitalize(((IShopItem)this.buyable.getObject().getItem()).getName()), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 50.0F, Color.WHITE))).mode(TextMode.SPLIT).width(flex.getWidth()).attach(flex);
          TextNode.create(0.0D, 0.0D).text(Text.create(StringUtils.capitalize(((IShopItem)this.buyable.getObject().getItem()).getDescription()), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 18.0F, ColorConstant.LIGHT_GRAY).lineHeight(5.0F))).mode(TextMode.SPLIT).width(700.0D).attach(flex);
        }).attach((UI)this);
    if (this.buyable.getObject().hasDiscount() && ((IShopItem)this.buyable.getObject().getItem()).getPrice().longValue() > 0L) {
      FlexNode.horizontal(1550.0D, 936.0D, 66.0D)
        .align(Align.CENTER)
        .margin(20.0D)
        .body(flex -> {
            ((TextNode)TextNode.create(0.0D, 0.0D).text(Text.create((this.buyable.getObject().getPrice().longValue() == 0L) ? "Gratuit" : String.format("%,d", new Object[] { this.buyable.getObject().getPrice() }).replace(",", ".").replace(" ", "."), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 48.0F, ColorConstant.PINK))).body(())).attach(flex);
            GlowImageNode.create(0.0D, 0.0D, 29.0D, 40.0D).glow(Color.decode("#72EB1B"), GlowImageNode.GlowProperty.create(0.7F, 1.3F)).resource(ResourceConstant.ITEM_PB).attach(flex);
            if (((IShopItem)this.buyable.getObject().getItem()).isSubscription())
              TextNode.create(0.0D, 0.0D).text(Text.create("/ " + this.buyable.getSubscriptionFormatted(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 30.0F, ColorConstant.LIGHT_GRAY))).attach(flex); 
          }).anchorX(Align.END)
        .visible(node -> ((IShopItem)this.buyable.getObject().getItem()).isBuyable().booleanValue())
        .attach((UI)this);
    } else {
      FlexNode.horizontal(1550.0D, 961.0D, 66.0D)
        .align(Align.CENTER)
        .margin(20.0D)
        .body(flex -> {
            TextNode.create(0.0D, 0.0D).text(Text.create((this.buyable.getObject().getPrice().longValue() == 0L) ? "Gratuit" : String.format("%,d", new Object[] { this.buyable.getObject().getPrice() }).replace(",", ".").replace(" ", "."), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 48.0F, Color.WHITE))).body(()).attach(flex);
            GlowImageNode.create(0.0D, 0.0D, 29.0D, 40.0D).glow(Color.decode("#72EB1B"), GlowImageNode.GlowProperty.create(0.7F, 1.3F)).resource(ResourceConstant.ITEM_PB).attach(flex);
            if (((IShopItem)this.buyable.getObject().getItem()).isSubscription())
              TextNode.create(0.0D, 0.0D).text(Text.create("/ " + this.buyable.getSubscriptionFormatted(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 30.0F, ColorConstant.LIGHT_GRAY))).attach(flex); 
          }).anchorX(Align.END)
        .visible(node -> ((IShopItem)this.buyable.getObject().getItem()).isBuyable().booleanValue())
        .attach((UI)this);
    } 
    FlexNode.vertical(1620.0D, 940.0D, 258.0D)
      .align(Align.CENTER)
      .anchorY(Align.END)
      .body(flex -> {
          if (this.buyable.isBuyable())
            return; 
          TextNode.create(0.0D, 0.0D).text(Text.create("Achat impossible : ", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 14.0F, ColorConstant.RED))).attach(flex);
          TextNode.create(0.0D, 0.0D).text(Text.create(this.buyable.getError(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 14.0F, ColorConstant.RED)).horizontalAlign(Align.CENTER)).mode(TextMode.SPLIT).width(flex.getWidth()).attach(flex);
        }).attach((UI)this);
    ((TextButtonNode)((TextButtonNode)TextButtonNode.create(1620.0D, 961.0D, 258.0D, 66.0D)
      .text((this.buyable.getObject().getPrice().longValue() == 0L) ? "Récupérer" : "Acheter")
      .resource(this.buyable.isBuyable() ? ((this.buyable.getObject().getPrice().longValue() == 0L) ? ResourceConstant.ICON_GIFT : ResourceConstant.ICON_BASKET) : ((this.buyable.getErrorType() == ConditionalBuyableObject.ErrorType.OWNED) ? ResourceConstant.ICON_BASKET_SUCCESS : ResourceConstant.ICON_BASKET_ERROR))
      .radius(6.0F)
      .onClick((node, mouseX, mouseY, clickType) -> {
          node.loading(true);
          (new Thread(())).start();
        })).enabled(node -> (!node.isLoading() && node.isMounted() && this.buyable.isBuyable())))
      .attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\buy\item\UIShopBuyItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */