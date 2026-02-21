package fr.paladium.palashop.client.ui.impl.buy.offer;

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
import fr.paladium.palashop.client.ui.kit.item.ShopItemNode;
import fr.paladium.palashop.client.ui.kit.render.impl.ShopElementRenderRendererNode;
import fr.paladium.palashop.client.ui.navbar.element.impl.ShopNavbarPage;
import fr.paladium.palashop.common.shop.network.item.BBPacketBuyItem;
import fr.paladium.palashop.common.shop.network.item.BBPacketGetItem;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.callback.NodeCallbackMethod;
import fr.paladium.zephyrui.lib.ui.node.callback.impl.state.NodeInitCallback;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

public class UIShopBuyDefaultOffer extends UIShopPage implements UIShopPage.IBackableUI {
  private final UIShopPage back;
  
  private final Signal<ConditionalBuyableObject<ShopOffer>> buyableSignal;
  
  private final IntegerSignal displayItemSignal;
  
  public UIShopPage getBack() {
    return this.back;
  }
  
  public Signal<ConditionalBuyableObject<ShopOffer>> getBuyableSignal() {
    return this.buyableSignal;
  }
  
  public IntegerSignal getDisplayItemSignal() {
    return this.displayItemSignal;
  }
  
  public UIShopBuyDefaultOffer(@NonNull UIShopPage back, @NonNull ConditionalBuyableObject<ShopOffer> buyable) {
    super(back.getElement());
    if (back == null)
      throw new NullPointerException("back is marked non-null but is null"); 
    if (buyable == null)
      throw new NullPointerException("buyable is marked non-null but is null"); 
    this.back = back;
    this.buyableSignal = new Signal(buyable);
    this.displayItemSignal = new IntegerSignal(0);
  }
  
  public void init() {
    super.init();
    schedule(() -> {
          double mx = getMouseX();
          double my = getMouseY();
          if (mx >= 42.0D && mx <= 758.0D && my >= 306.0D && my <= 1022.0D)
            return; 
          int next = ((Integer)this.displayItemSignal.getOrDefault()).intValue() + 1;
          if (next >= ((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem()).getConditionalShopItems().size()) {
            this.displayItemSignal.set(Integer.valueOf(0));
          } else {
            this.displayItemSignal.set(Integer.valueOf(next));
          } 
        }3000L, 3000L);
    (new BBPacketGetItem((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem())).subscribe(result -> this.buyableSignal.set(result.getOffer()))
      
      .send();
    TextNode.create(42.0D, 147.0D)
      .text(Text.create(((ShopNavbarPage)getElement()).getName().toUpperCase() + " > ", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 15.0F, ColorConstant.LIGHT_GRAY)).add(Text.create(((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem()).getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 15.0F, ColorConstant.LIGHT_GRAY))))
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close((UI)this))
      .attach((UI)this);
    TextNode.create(42.0D, 177.0D)
      .text(Text.create(((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem()).getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 54.0F, Color.WHITE)))
      .attach((UI)this);
    RectNode.create(42.0D, 252.0D, 1836.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    RectNode.create(42.0D, 306.0D, 716.0D, 716.0D)
      .color(ColorConstant.GRAY)
      .effect((NodeEffect)RoundedNodeEffect.create(20.0F))
      .body(rect -> {
          ((ShopElementRenderRendererNode)ShopElementRenderRendererNode.create(0.0D, 0.0D, rect.getWidth(), rect.getHeight()).onInit(new NodeInitCallback<ShopElementRenderRendererNode>() {
                public void apply(@NonNull ShopElementRenderRendererNode node) {
                  if (node == null)
                    throw new NullPointerException("node is marked non-null but is null"); 
                }
                
                @NodeCallbackMethod(NodeCallbackMethod.Type.PRE)
                public void pre(@NonNull ShopElementRenderRendererNode node, @NonNull InternalContext context) {
                  if (node == null)
                    throw new NullPointerException("node is marked non-null but is null"); 
                  if (context == null)
                    throw new NullPointerException("context is marked non-null but is null"); 
                  node.object(((ConditionalBuyableObject)((ShopOffer)((ConditionalBuyableObject)UIShopBuyDefaultOffer.this.buyableSignal.getOrDefault()).getObject().getItem()).getConditionalShopItems().get(((Integer)UIShopBuyDefaultOffer.this.displayItemSignal.getOrDefault()).intValue())).getObject().getItem());
                }
                
                @NodeCallbackMethod(NodeCallbackMethod.Type.POST)
                public void post(@NonNull ShopElementRenderRendererNode node, @NonNull InternalContext context) {
                  if (node == null)
                    throw new NullPointerException("node is marked non-null but is null"); 
                  if (context == null)
                    throw new NullPointerException("context is marked non-null but is null"); 
                }
              })).watch((Signal)this.displayItemSignal).attach(rect);
          ((ImageNode)ImageNode.create(rect.aw(-22.0D), 22.0D).onInit(())).width(107.0D).anchorX(Align.END).watch((Signal)this.displayItemSignal).attach(rect);
        }).attach((UI)this);
    FlexNode.horizontal(840.0D, 306.0D, 56.0D)
      .margin(20.0D)
      .body(flex -> {
          Text text = Text.create(ShopRarityConstant.getName(((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem()).getRarity()).toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BLACK, 22.0F, Color.WHITE).shadow(ShopRarityConstant.getColor(((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem()).getRarity()).darker(0.5F)).shadow(0.0F, 1.0F)).align(Align.CENTER, Align.CENTER);
          RectNode.create(0.0D, 0.0D, text.getWidth() + 120.0D, flex.getHeight()).color(ShopRarityConstant.getColor(((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem()).getRarity())).effect((NodeEffect)RoundedNodeEffect.create(3.0F)).body(()).attach(flex);
          if (((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().hasDiscount())
            ImageNode.create(0.0D, 0.0D).resource(ResourceConstant.ICON_DISCOUNT).height(flex.getHeight()).attach(flex); 
        }).attach((UI)this);
    TextNode.create(840.0D, 389.0D)
      .text(Text.create(StringUtils.capitalize(((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem()).getName()), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 50.0F, Color.WHITE)))
      .attach((UI)this);
    TextNode.create(840.0D, 479.0D)
      .text(Text.create(StringUtils.capitalize(((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem()).getDescription()), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 18.0F, ColorConstant.LIGHT_GRAY).lineHeight(5.0F)))
      .mode(TextMode.SPLIT)
      .width(700.0D)
      .attach((UI)this);
    if (((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem()).getConditionalShopItems() != null && !((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem()).getConditionalShopItems().isEmpty()) {
      TextNode.create(840.0D, 565.0D)
        .text(Text.create("cette offre contient :", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 20.0F, Color.WHITE)))
        .attach((UI)this);
      FlexNode.horizontal(840.0D, 607.0D, 244.0D)
        .margin(17.0D)
        .body(flex -> {
            for (ConditionalBuyableObject<IShopItem> item : (Iterable<ConditionalBuyableObject<IShopItem>>)((ShopOffer)((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getItem()).getConditionalShopItems())
              ShopItemNode.create(0.0D, 0.0D, 195.0D, flex.getHeight()).shopItem(item).attach(flex); 
          }).watch(this.buyableSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach((UI)this);
    } 
    if (((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().hasDiscount() && ((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getPrice().longValue() > 0L) {
      FlexNode.horizontal(1550.0D, 936.0D, 66.0D)
        .align(Align.CENTER)
        .margin(20.0D)
        .body(flex -> {
            ((TextNode)TextNode.create(0.0D, 0.0D).text(Text.create((((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getPrice().longValue() == 0L) ? "Gratuit" : String.format("%,d", new Object[] { ((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getPrice() }).replace(",", ".").replace(" ", "."), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 48.0F, ColorConstant.PINK))).body(())).attach(flex);
            GlowImageNode.create(0.0D, 0.0D, 29.0D, 40.0D).glow(Color.decode("#72EB1B"), GlowImageNode.GlowProperty.create(0.7F, 1.3F)).resource(ResourceConstant.ITEM_PB).attach(flex);
          }).anchorX(Align.END)
        .watch(this.buyableSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).visible(node -> (((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getErrorType() != ConditionalBuyableObject.ErrorType.OWNED))
        .attach((UI)this);
    } else {
      FlexNode.horizontal(1550.0D, 961.0D, 66.0D)
        .align(Align.CENTER)
        .margin(20.0D)
        .body(flex -> {
            TextNode.create(0.0D, 0.0D).text(Text.create((((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getPrice().longValue() == 0L) ? "Gratuit" : String.format("%,d", new Object[] { ((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getPrice() }).replace(",", ".").replace(" ", "."), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 48.0F, Color.WHITE))).body(()).attach(flex);
            GlowImageNode.create(0.0D, 0.0D, 29.0D, 40.0D).glow(Color.decode("#72EB1B"), GlowImageNode.GlowProperty.create(0.7F, 1.3F)).resource(ResourceConstant.ITEM_PB).attach(flex);
          }).anchorX(Align.END)
        .watch(this.buyableSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).visible(node -> (((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getErrorType() != ConditionalBuyableObject.ErrorType.OWNED))
        .attach((UI)this);
    } 
    FlexNode.vertical(1620.0D, 940.0D, 258.0D)
      .align(Align.CENTER)
      .anchorY(Align.END)
      .body(flex -> {
          if (((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).isBuyable())
            return; 
          TextNode.create(0.0D, 0.0D).text(Text.create("Achat impossible : ", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 14.0F, ColorConstant.RED))).attach(flex);
          TextNode.create(0.0D, 0.0D).text(Text.create(((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getError(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 14.0F, ColorConstant.RED)).horizontalAlign(Align.CENTER)).mode(TextMode.SPLIT).width(flex.getWidth()).attach(flex);
        }).watch(this.buyableSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach((UI)this);
    ((TextButtonNode)((TextButtonNode)((TextButtonNode)TextButtonNode.create(1620.0D, 961.0D, 258.0D, 66.0D)
      .onInit(node -> {
          node.text((((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getPrice().longValue() == 0L) ? "Réupérer" : "Acheter");
          node.resource(((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).isBuyable() ? ((((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getObject().getPrice().longValue() == 0L) ? ResourceConstant.ICON_GIFT : ResourceConstant.ICON_BASKET) : ((((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).getErrorType() == ConditionalBuyableObject.ErrorType.OWNED) ? ResourceConstant.ICON_BASKET_SUCCESS : ResourceConstant.ICON_BASKET_ERROR));
        })).radius(6.0F)
      .onClick((node, mouseX, mouseY, clickType) -> {
          node.loading(true);
          (new Thread(())).start();
        })).enabled(node -> (!node.isLoading() && node.isMounted() && ((ConditionalBuyableObject)this.buyableSignal.getOrDefault()).isBuyable())))
      .watch(this.buyableSignal)
      .attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\buy\offer\UIShopBuyDefaultOffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */