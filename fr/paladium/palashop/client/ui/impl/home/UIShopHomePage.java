package fr.paladium.palashop.client.ui.impl.home;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.palashop.client.ui.kit.item.ShopItemNode;
import fr.paladium.palashop.client.ui.kit.pricing.PricingNode;
import fr.paladium.palashop.client.ui.navbar.ShopNavbarManager;
import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.palashop.common.shop.network.home.BBPacketGetDaily;
import fr.paladium.palashop.common.shop.network.home.BBPacketGetOffers;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

public class UIShopHomePage extends UIShopPage {
  private final Signal<ConditionalBuyableObject<ShopOffer>> monthlyOfferSignal;
  
  private final Signal<ConditionalBuyableObject<ShopOffer>> currentOfferSignal;
  
  private final ListSignal<ConditionalBuyableObject<IShopItem>> dailyItemsSignal;
  
  public UIShopHomePage() {
    super((ShopNavbarElement)ShopNavbarManager.PAGE_HOME);
    this.monthlyOfferSignal = new Signal();
    this.currentOfferSignal = new Signal();
    this.dailyItemsSignal = new ListSignal();
  }
  
  public void init() {
    super.init();
    (new BBPacketGetOffers()).subscribe(result -> {
          this.monthlyOfferSignal.set(result.getMonthlyOffer());
          this.currentOfferSignal.set(result.getCurrentOffer());
        }).send();
    (new BBPacketGetDaily()).subscribe(result -> this.dailyItemsSignal.set(result.getItemList()))
      
      .send();
    TextNode.create(42.0D, 138.0D)
      .text(Text.create(getElement().getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 15.0F, ColorConstant.LIGHT_GRAY)))
      .attach((UI)this);
    ((TextNode)TextNode.create(42.0D, 159.0D)
      .onMount(node -> node.text(Text.create(((ShopOffer)((ConditionalBuyableObject)this.monthlyOfferSignal.getOrDefault()).getObject().getItem()).getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 40.0F, Color.WHITE)))))
      .skeleton(node -> RectNode.create(0.0D, 0.0D, 250.0D, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 40.0F, Color.WHITE).getHeight()).color(Color.LOADING))
      .wait((ISignal)this.monthlyOfferSignal)
      .attach((UI)this);
    RectNode.create(42.0D, 220.0D, 1136.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    RectNode.create(42.0D, 238.0D, 1136.0D, 431.0D)
      .color(ColorConstant.GRAY)
      .effect((NodeEffect)RoundedNodeEffect.create(22.0F))
      .attach((UI)this);
    ((ImageNode)((ImageNode)ImageNode.create(42.0D, 238.0D, 1136.0D, 431.0D)
      .effect((NodeEffect)RoundedNodeEffect.create(22.0F))
      .onInit(node -> {
          if (!node.isMounted())
            return; 
          ConditionalBuyableObject<ShopOffer> monthlyOffer = (ConditionalBuyableObject<ShopOffer>)this.monthlyOfferSignal.getOrDefault();
          ShopOffer offer = (ShopOffer)monthlyOffer.getObject().getItem();
          if (offer.getThumbnail() != null)
            node.resource(Resource.of(offer.getThumbnail())); 
          ImageNode.create(node.aw(-26.0D), 26.0D).resource(ShopRarityConstant.getResource(offer.getRarity())).width(84.0D).anchorX(Align.END).attach((Node)node);
          PricingNode.create(node.aw(-343.0D), node.ah(-170.0D), 295.0D, 122.0D).item(monthlyOffer).color(ColorConstant.LIGHT_DARK).radius(22.0F).attach((Node)node);
        })).skeleton(node -> RectNode.create(0.0D, 0.0D, node.getWidth(), node.getHeight()).color(Color.LOADING).effect(node.getEffect(RoundedNodeEffect.class))))
      .wait((ISignal)this.monthlyOfferSignal)
      .watch(this.monthlyOfferSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).attach((UI)this);
    ((TextNode)TextNode.create(1227.0D, 159.0D)
      .onMount(node -> node.text(Text.create(((ShopOffer)((ConditionalBuyableObject)this.currentOfferSignal.getOrDefault()).getObject().getItem()).getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 40.0F, Color.WHITE)))))
      .skeleton(node -> RectNode.create(0.0D, 0.0D, 250.0D, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 40.0F, Color.WHITE).getHeight()).color(Color.LOADING))
      .wait((ISignal)this.currentOfferSignal)
      .attach((UI)this);
    RectNode.create(1227.0D, 220.0D, 642.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    RectNode.create(1227.0D, 238.0D, 642.0D, 810.0D)
      .color(ColorConstant.GRAY)
      .effect((NodeEffect)RoundedNodeEffect.create(22.0F))
      .attach((UI)this);
    TextNode.create(42.0D, 702.0D)
      .text(Text.create("séléction du jour".toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 40.0F, Color.WHITE)))
      .attach((UI)this);
    RectNode.create(42.0D, 762.0D, 1134.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    ((ImageNode)((ImageNode)ImageNode.create(1227.0D, 238.0D, 642.0D, 810.0D)
      .effect((NodeEffect)RoundedNodeEffect.create(22.0F))
      .onInit(node -> {
          if (!node.isMounted())
            return; 
          ConditionalBuyableObject<ShopOffer> currentOffer = (ConditionalBuyableObject<ShopOffer>)this.currentOfferSignal.getOrDefault();
          ShopOffer offer = (ShopOffer)currentOffer.getObject().getItem();
          if (offer.getThumbnail() != null)
            node.resource(Resource.of(offer.getThumbnail())); 
          ImageNode.create(node.aw(-26.0D), 26.0D).resource(ShopRarityConstant.getResource(offer.getRarity())).width(84.0D).anchorX(Align.END).attach((Node)node);
          PricingNode.create(node.aw(-343.0D), node.ah(-170.0D), 295.0D, 122.0D).item(currentOffer).color(ColorConstant.LIGHT_DARK).radius(22.0F).attach((Node)node);
        })).skeleton(node -> RectNode.create(0.0D, 0.0D, node.getWidth(), node.getHeight()).color(Color.LOADING).effect(node.getEffect(RoundedNodeEffect.class))))
      .wait((ISignal)this.currentOfferSignal)
      .watch(this.currentOfferSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).attach((UI)this);
    long remaining = Duration.between(TimeUtils.nowZoned(), ZonedDateTime.of(TimeUtils.nowZoned().toLocalDate(), LocalTime.MIDNIGHT, ZoneId.of("Europe/Paris")).plusDays(1L)).getSeconds();
    if (remaining > 0L) {
      long hours = TimeUnit.SECONDS.toHours(remaining) % 24L;
      long minutes = TimeUnit.SECONDS.toMinutes(remaining) % 60L;
      long seconds = TimeUnit.SECONDS.toSeconds(remaining) % 60L;
      String time = String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds) });
      ((TextNode)TextNode.create(1178.0D, 724.0D)
        .text(Text.create(time, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 22.0F, ColorConstant.PRIMARY)))
        .onUpdate(node -> {
            long newRemaining = Duration.between(TimeUtils.nowZoned(), ZonedDateTime.of(TimeUtils.nowZoned().toLocalDate(), LocalTime.MIDNIGHT, ZoneId.of("Europe/Paris")).plusDays(1L)).getSeconds();
            long newHours = TimeUnit.SECONDS.toHours(newRemaining) % 24L;
            long newMinutes = TimeUnit.SECONDS.toMinutes(newRemaining) % 60L;
            long newSeconds = TimeUnit.SECONDS.toSeconds(newRemaining) % 60L;
            String newTime = String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(newHours), Long.valueOf(newMinutes), Long.valueOf(newSeconds) });
            node.getText().text(newTime);
          })).anchorX(Align.END)
        .attach((UI)this);
    } 
    FlexNode.horizontal(42.0D, 788.0D, 260.0D)
      .margin(24.0D)
      .onInit(flex -> {
          if (!flex.isMounted()) {
            for (int i = 0; i < 5; i++)
              ShopItemNode.create(0.0D, 0.0D, 208.0D, flex.getHeight()).attach(flex); 
            return;
          } 
          for (ConditionalBuyableObject<IShopItem> item : (Iterable<ConditionalBuyableObject<IShopItem>>)this.dailyItemsSignal.getOrDefault())
            ShopItemNode.create(0.0D, 0.0D, 208.0D, flex.getHeight()).shopItem(item).attach(flex); 
        }).watch((Signal)this.dailyItemsSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).wait((ISignal)this.dailyItemsSignal)
      .attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\home\UIShopHomePage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */