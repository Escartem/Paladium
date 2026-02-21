package fr.paladium.palashop.client.ui.impl.subscription.popup.mutate;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.UIShopBase;
import fr.paladium.palashop.client.ui.kit.button.TextButtonNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.client.ui.kit.constant.SoundConstant;
import fr.paladium.palashop.client.ui.kit.transition.ShopSlideDownTransition;
import fr.paladium.palashop.common.shop.network.subscription.BBPacketRenewSubscription;
import fr.paladium.palashop.common.utils.time.UniversalTimeUtils;
import fr.paladium.palashop.server.shop.dto.BuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.Subscription;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.popup.UIDataPopup;
import fr.paladium.zephyrui.lib.ui.core.transition.Transition;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.function.Consumer;
import lombok.NonNull;

@UIDataPopup(active = true)
@UIData(backgroundColor = "#17181BF2", zlevel = 1000.0D)
public class UIShopSubscriptionRenewPopup extends UIShopBase {
  private final Subscription subscription;
  
  private final BuyableObject<IShopItem> shopItem;
  
  private final Consumer<Subscription> callback;
  
  public Subscription getSubscription() {
    return this.subscription;
  }
  
  public BuyableObject<IShopItem> getShopItem() {
    return this.shopItem;
  }
  
  public Consumer<Subscription> getCallback() {
    return this.callback;
  }
  
  public UIShopSubscriptionRenewPopup(@NonNull Subscription subscription, @NonNull BuyableObject<IShopItem> shopItem, Consumer<Subscription> callback) {
    if (subscription == null)
      throw new NullPointerException("subscription is marked non-null but is null"); 
    if (shopItem == null)
      throw new NullPointerException("shopItem is marked non-null but is null"); 
    this.subscription = subscription;
    this.shopItem = shopItem;
    this.callback = callback;
  }
  
  public void init() {
    setTransition((Transition)new ShopSlideDownTransition());
    ZonedDateTime nextRenewal = UniversalTimeUtils.fromLong(this.subscription.getNextRenewalDate()).withZoneSameInstant(ZoneId.systemDefault());
    ImageNode.create(887.0D, 249.0D, 147.0D, 157.0D)
      .resource(ResourceConstant.ICON_BASKET_SUCCESS)
      .color(ColorConstant.GREEN)
      .attach((UI)this);
    TextNode.create(960.0D, 449.0D)
      .text(Text.create("Renouveler l'abonnement ?", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 60.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    TextNode.create(960.0D, 544.0D)
      .text(Text.create((this.subscription.getStatus() == Subscription.Status.EXPIRED) ? "Votre abonnement sera activé dans les minutes qui suivent" : ("Vous ne serez pas débité jusqu'au prochain renouvellement le " + DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(nextRenewal.toLocalDate()).replace("/", "-")), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_REGULAR, 30.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    FlexNode.horizontal(960.0D, 696.0D, 66.0D)
      .margin(18.0D)
      .body(flex -> {
          TextButtonNode.create(0.0D, 0.0D, 216.0D, flex.getHeight()).text("Retour").radius(6.0F).color(ColorConstant.LIGHT_DARK).onClick(()).attach(flex);
          ((TextButtonNode)TextButtonNode.create(0.0D, 0.0D, 216.0D, flex.getHeight()).text("Renouveler").radius(6.0F).color(ColorConstant.PRIMARY).onClick(())).attach(flex);
        }).anchorX(Align.CENTER)
      .attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\subscription\popup\mutate\UIShopSubscriptionRenewPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */