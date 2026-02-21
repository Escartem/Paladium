package fr.paladium.palashop.server.shop.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.client.ui.impl.subscription.popup.warn.UIShopSubscriptionExpiredPopup;
import fr.paladium.palashop.client.ui.impl.subscription.popup.warn.UIShopSubscriptionReminderPopup;
import fr.paladium.palashop.common.shop.data.ShopPlayer;
import fr.paladium.palashop.server.pb.PBManager;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.BuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.Subscription;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class SubscriptionsPopupListener {
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    ShopPlayer shopPlayer = ShopPlayer.get((Entity)player);
    if (shopPlayer == null || (shopPlayer.getNotifyExpiredSubscriptions().isEmpty() && shopPlayer.getNotifyReminderSubscriptions().isEmpty()))
      return; 
    String uuid = UUIDUtils.toString((Entity)player);
    for (String subscriptionId : shopPlayer.getNotifyExpiredSubscriptions()) {
      ShopManager.User.getSubscriptions(uuid)
        .thenAccept(subscriptionList -> {
            if (subscriptionList == null || subscriptionList.isEmpty())
              return; 
            Subscription subscription = subscriptionList.stream().filter(()).findFirst().orElse(null);
            if (subscription == null || subscription.getStatus() != Subscription.Status.EXPIRED)
              return; 
            FMLServerScheduler.getInstance().add(new Runnable[] { () });
          }).exceptionally(e -> null);
    } 
    shopPlayer.getNotifyExpiredSubscriptions().clear();
    for (Iterator<String> iterator = shopPlayer.getNotifyReminderSubscriptions().iterator(); iterator.hasNext(); ) {
      String subscriptionId = iterator.next();
      ShopManager.User.getSubscriptions(uuid)
        .thenAccept(subscriptionList -> {
            if (subscriptionList == null || subscriptionList.isEmpty())
              return; 
            Subscription subscription = subscriptionList.stream().filter(()).findFirst().orElse(null);
            if (subscription == null || (subscription.getStatus() != Subscription.Status.ACTIVE && subscription.getStatus() != Subscription.Status.PENDING))
              return; 
            Optional<IShopItem> shopItem = ShopManager.getItem(subscription.getItemId());
            if (!shopItem.isPresent())
              return; 
            ShopManager.User.getUser(uuid).thenAccept(()).exceptionally(());
          }).exceptionally(e -> null);
    } 
    shopPlayer.getNotifyReminderSubscriptions().clear();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\listener\SubscriptionsPopupListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */