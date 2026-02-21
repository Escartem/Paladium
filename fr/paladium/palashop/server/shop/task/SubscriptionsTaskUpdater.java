package fr.paladium.palashop.server.shop.task;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.task.ATask;
import fr.paladium.palaforgeutils.lib.task.Schedule;
import fr.paladium.palashop.api.server.shop.response.user.ShopBuyResponse;
import fr.paladium.palashop.api.server.shop.response.user.SubscriptionMutationResponse;
import fr.paladium.palashop.common.shop.data.ShopPlayer;
import fr.paladium.palashop.common.utils.time.UniversalTimeUtils;
import fr.paladium.palashop.server.pb.PBManager;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.BuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.Subscription;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import fr.paladium.zephyrui.internal.mod.utils.command.uuid.UUIDUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

@Schedule(every = "1m")
public class SubscriptionsTaskUpdater extends ATask {
  public SubscriptionsTaskUpdater() {
    super("PalaShop/SubscriptionsTaskUpdater");
  }
  
  public void run() {
    for (Iterator iterator = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b.iterator(); iterator.hasNext(); ) {
      Object entity = iterator.next();
      if (!(entity instanceof EntityPlayerMP))
        continue; 
      EntityPlayerMP player = (EntityPlayerMP)entity;
      ShopPlayer shopPlayer = ShopPlayer.get((Entity)player);
      if (shopPlayer == null)
        continue; 
      String uuid = UUIDUtils.toString((Entity)player);
      ShopManager.User.getUser(uuid)
        .thenAccept(user -> ShopManager.User.getSubscriptions(uuid).thenAccept(()).exceptionally(()))
        
        .exceptionally(e -> {
            System.out.println("Error while updating subscriptions for player " + player.func_70005_c_());
            e.printStackTrace();
            return null;
          });
    } 
  }
  
  private void mutateSubscription(EntityPlayerMP player, Subscription subscription, Subscription.Status status) {
    mutateSubscription(player, subscription, status, null);
  }
  
  private void mutateSubscription(EntityPlayerMP player, Subscription subscription, Subscription.Status status, Consumer<SubscriptionMutationResponse> callback) {
    ShopManager.User.mutateSubscription((EntityPlayer)player, subscription.getItemId(), status)
      .thenAccept(mutation -> {
          if (!mutation.isSuccessful())
            return; 
          if (callback != null)
            callback.accept(mutation); 
        }).exceptionally(e -> {
          System.out.println("Error while updating subscriptions for player " + player.func_70005_c_());
          e.printStackTrace();
          return null;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\task\SubscriptionsTaskUpdater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */