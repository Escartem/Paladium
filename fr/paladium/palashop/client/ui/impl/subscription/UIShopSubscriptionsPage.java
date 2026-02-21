package fr.paladium.palashop.client.ui.impl.subscription;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.api.server.pb.dto.PBData;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.impl.pb.UIShopPBPopup;
import fr.paladium.palashop.client.ui.impl.subscription.popup.mutate.UIShopSubscriptionCancelPopup;
import fr.paladium.palashop.client.ui.impl.subscription.popup.mutate.UIShopSubscriptionRenewPopup;
import fr.paladium.palashop.client.ui.kit.button.TextButtonNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.client.ui.kit.render.impl.ShopElementThumbnailRendererNode;
import fr.paladium.palashop.client.ui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palashop.client.ui.navbar.ShopNavbarManager;
import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.palashop.client.ui.store.UIShopStore;
import fr.paladium.palashop.common.shop.network.item.BBPacketGetItem;
import fr.paladium.palashop.common.shop.network.subscription.BBPacketGetSubscriptions;
import fr.paladium.palashop.common.utils.time.UniversalTimeUtils;
import fr.paladium.palashop.server.shop.dto.BuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.Subscription;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
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
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UIShopSubscriptionsPage extends UIShopPage {
  private final ListSignal<Subscription> subscriptionListSignal;
  
  private final MapSignal<String, BuyableObject<IShopItem>> itemMapSignal;
  
  public UIShopSubscriptionsPage() {
    super((ShopNavbarElement)ShopNavbarManager.TAB_SUBSCRIPTIONS);
    this.subscriptionListSignal = new ListSignal();
    this.itemMapSignal = new MapSignal();
  }
  
  public void init() {
    super.init();
    (new BBPacketGetSubscriptions()).subscribe(result -> {
          this.subscriptionListSignal.set(result.getSubscriptionList());
          Map<String, BuyableObject<IShopItem>> itemMap = new HashMap<>();
          AtomicInteger count = new AtomicInteger(0);
          for (Subscription subscription : result.getSubscriptionList())
            (new BBPacketGetItem(subscription.getItemId(), false)).subscribe(()).send(); 
        }).send();
    TextNode.create(42.0D, 138.0D)
      .text(Text.create(getElement().getName().toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 15.0F, ColorConstant.LIGHT_GRAY)))
      .attach((UI)this);
    TextNode.create(42.0D, 159.0D)
      .text(Text.create("GESTION DES ABONNEMENTS", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 40.0F, Color.WHITE)))
      .attach((UI)this);
    TextNode.create(1878.0D, 180.0D)
      .text(Text.create("Gérez vos abonnements", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 22.0F, ColorConstant.LIGHT_GRAY)))
      .anchorX(Align.END)
      .attach((UI)this);
    RectNode.create(42.0D, 220.0D, 1836.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    ContainerNode.create(42.0D, 253.0D, 1836.0D, 827.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.vertical(1850.0D, 0.0D, 787.0D))
      .onInit(container -> {
          if (!container.isMounted())
            return; 
          FlexNode.vertical(0.0D, 0.0D, container.getWidth()).margin(15.0D).body(()).attach(container);
        }).skeleton(container -> FlexNode.vertical(0.0D, 0.0D, container.getWidth()).margin(15.0D).body(()))
      
      .wait((ISignal)this.subscriptionListSignal)
      .watch((Signal)this.subscriptionListSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD }).attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\subscription\UIShopSubscriptionsPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */