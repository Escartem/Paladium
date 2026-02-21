package fr.paladium.palashop.client.ui.impl.subscription.popup.warn;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.bookmark.BookmarkNode;
import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.palashop.client.ui.impl.subscription.UIShopSubscriptionsPage;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.server.shop.dto.item.Subscription;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.popup.UIDataPopup;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import lombok.NonNull;

@UIData(zlevel = 1000.0D)
@UIDataPopup(active = true)
public class UIShopSubscriptionExpiredPopup extends UI {
  private final Subscription subscription;
  
  public Subscription getSubscription() {
    return this.subscription;
  }
  
  public UIShopSubscriptionExpiredPopup(@NonNull Subscription subscription) {
    if (subscription == null)
      throw new NullPointerException("subscription is marked non-null but is null"); 
    this.subscription = subscription;
  }
  
  public void init() {
    BackgroundNode.create(870.0D, 870.0D)
      .body(background -> {
          IconButtonNode.create(background.aw(-50.0D), 21.0D, 21.0D).icon(ResourceConstant.CLOSE).onClick(()).attach(background);
          BookmarkNode.create(42.0D, 0.0D).icon("!").attach(background);
          ImageNode.create(background.dw(2.0D), 78.0D).resource(Resource.of(this.subscription.getItemThumbnail())).height(384.0D).anchorX(Align.CENTER).attach(background);
          TextNode.create(42.0D, 483.0D).text(Text.create("points boutique insuffisants", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 25.0F, ColorConstant.PRIMARY).shadow().shadow(0.0F, 2.0F))).attach(background);
          TextNode.create(42.0D, 543.0D).text(Text.create("votre abonnement a expiré", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 50.0F, Color.WHITE))).mode(TextMode.SPLIT).width(background.aw(-84.0D)).attach(background);
          TextNode.create(42.0D, 685.0D).text(Text.create("Votre abonnement est arrivé à expiration, tous les avantages liés à cet abonnement ont été retirés.".toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 20.0F, Color.WHITE))).mode(TextMode.SPLIT).width(background.aw(-84.0D)).attach(background);
          TextButtonNode.create(background.dw(2.0D), 785.0D).text("renouveler").anchorX(Align.CENTER).width(437.0D).onClick(()).attach(background);
        }).attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\subscription\popup\warn\UIShopSubscriptionExpiredPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */