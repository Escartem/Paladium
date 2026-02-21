package fr.paladium.palashop.server.shop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palaforgeutils.lib.console.ConsoleUtils;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.api.server.shop.IShopAPI;
import fr.paladium.palashop.api.server.shop.request.user.ShopBuyRequest;
import fr.paladium.palashop.api.server.shop.request.user.SubscriptionMutationRequest;
import fr.paladium.palashop.api.server.shop.response.user.ShopBuyResponse;
import fr.paladium.palashop.api.server.shop.response.user.SubscriptionMutationResponse;
import fr.paladium.palashop.api.utils.CompletableCallback;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.utils.server.ServerUtils;
import fr.paladium.palashop.server.shop.dto.BuyableObject;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.IBuyable;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.ShopItem;
import fr.paladium.palashop.server.shop.dto.item.Subscription;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import fr.paladium.palashop.server.shop.dto.page.ShopPage;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import fr.paladium.palashop.server.shop.event.ShopBuyProviderEvent;
import fr.paladium.palashop.server.shop.event.ShopItemApplyProviderEvent;
import fr.paladium.palashop.server.shop.event.ShopLoadProviderEvent;
import fr.paladium.palashop.server.shop.task.SubscriptionsTaskUpdater;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SideOnly(Side.SERVER)
public class ShopManager {
  public static final Object LOCK = new Object();
  
  private static ShopOffer monthlyOffer;
  
  private static ShopOffer currentOffer;
  
  private static Map<String, IShopItem> items;
  
  private static Map<String, ShopOffer> offers;
  
  private static Map<String, ShopPage> pages;
  
  public static void load() {
    long start = System.currentTimeMillis();
    System.out.println("[ShopManager] Loading shop data...");
    synchronized (LOCK) {
      ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ShopLoadProviderEvent.pre() });
      try {
        IShopAPI api = PalaShopMod.getServer().getShopAPI();
        monthlyOffer = (ShopOffer)api.getMonthlyOffer().execute().body();
        currentOffer = (ShopOffer)api.getCurrentOffer().execute().body();
        items = new HashMap<>();
        offers = new HashMap<>();
        pages = new HashMap<>();
        ((List)api.getItems().execute().body()).forEach(item -> (IShopItem)items.put(item.getUniqueId(), item));
        ((List)api.getOffers().execute().body()).forEach(offer -> (ShopOffer)offers.put(offer.getUniqueId(), offer));
        ((List)api.getPages().execute().body()).forEach(page -> (ShopPage)pages.put(page.getId(), page));
      } catch (IOException e) {
        throw new RuntimeException(e);
      } 
      ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ShopLoadProviderEvent.post() });
    } 
    if (monthlyOffer == null)
      throw new RuntimeException("Monthly offer is null"); 
    if (currentOffer == null)
      throw new RuntimeException("Current offer is null"); 
    if (items == null)
      throw new RuntimeException("Items are null"); 
    System.out.println("[ShopManager] Monthly offer: " + monthlyOffer.getId());
    System.out.println("[ShopManager] Current offer: " + currentOffer.getId());
    System.out.println("[ShopManager] Items: " + items.size());
    System.out.println("[ShopManager] Offers: " + offers.size());
    System.out.println("[ShopManager] Pages: " + pages.size());
    System.out.println("[ShopManager] Shop data loaded in " + (System.currentTimeMillis() - start) + "ms");
    System.out.println("[ShopManager] Starting subscriptions task updater...");
    if (!(new SubscriptionsTaskUpdater()).start())
      throw new RuntimeException("Failed to start subscriptions task updater"); 
  }
  
  @NonNull
  public static CompletableFuture<Void> update() {
    final CompletableFuture<Void> future = new CompletableFuture<>();
    PalaShopMod.getServer().getShopAPI().update().enqueue(new Callback<Void>() {
          public void onResponse(Call<Void> call, Response<Void> response) {
            if (!response.isSuccessful()) {
              future.completeExceptionally(new RuntimeException("Invalid response"));
              return;
            } 
            (new Thread(() -> {
                  synchronized (ShopManager.LOCK) {
                    try {
                      ShopManager.load();
                    } catch (Exception e) {
                      future.completeExceptionally(e);
                      return;
                    } 
                    future.complete(null);
                  } 
                }"ShopManager/Update")).start();
          }
          
          public void onFailure(Call<Void> call, Throwable t) {
            future.completeExceptionally(t);
          }
        });
    return future;
  }
  
  @NonNull
  public static ShopOffer getMonthlyOffer() {
    synchronized (LOCK) {
      return monthlyOffer;
    } 
  }
  
  @NonNull
  public static ShopOffer getCurrentOffer() {
    synchronized (LOCK) {
      return currentOffer;
    } 
  }
  
  @NonNull
  public static List<IShopItem> getItems() {
    synchronized (LOCK) {
      return (items == null) ? Collections.<IShopItem>emptyList() : new ArrayList<>(items.values());
    } 
  }
  
  @NonNull
  public static List<ShopOffer> getOffers() {
    synchronized (LOCK) {
      return (offers == null) ? Collections.<ShopOffer>emptyList() : new ArrayList<>(offers.values());
    } 
  }
  
  @NonNull
  public static Optional<IShopItem> getItem(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    synchronized (LOCK) {
      return (items == null) ? Optional.<IShopItem>empty() : Optional.<IShopItem>ofNullable(items.get(id));
    } 
  }
  
  @NonNull
  public static Optional<ShopOffer> getOffer(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    synchronized (LOCK) {
      return (offers == null) ? Optional.<ShopOffer>empty() : Optional.<ShopOffer>ofNullable(offers.get(id));
    } 
  }
  
  @NonNull
  public static List<ShopPage> getPages() {
    synchronized (LOCK) {
      return (pages == null) ? Collections.<ShopPage>emptyList() : new ArrayList<>(pages.values());
    } 
  }
  
  @NonNull
  public static Optional<ShopPage> getPage(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    synchronized (LOCK) {
      return (pages == null) ? Optional.<ShopPage>empty() : Optional.<ShopPage>ofNullable(pages.get(id));
    } 
  }
  
  public static class User {
    @NonNull
    public static CompletableFuture<ShopUser> getUser(@NonNull String uuid) {
      if (uuid == null)
        throw new NullPointerException("uuid is marked non-null but is null"); 
      IShopAPI api = PalaShopMod.getServer().getShopAPI();
      final CompletableFuture<ShopUser> future = new CompletableFuture<>();
      api.getUser(uuid).enqueue(new Callback<ShopUser>() {
            public void onResponse(Call<ShopUser> call, Response<ShopUser> response) {
              if (!response.isSuccessful() || response.body() == null) {
                future.completeExceptionally(new RuntimeException("Invalid response"));
                return;
              } 
              future.complete(response.body());
            }
            
            public void onFailure(Call<ShopUser> call, Throwable t) {
              future.completeExceptionally(t);
            }
          });
      return future;
    }
    
    @NonNull
    public static CompletableFuture<List<String>> getItems(@NonNull String uuid) {
      if (uuid == null)
        throw new NullPointerException("uuid is marked non-null but is null"); 
      IShopAPI api = PalaShopMod.getServer().getShopAPI();
      final CompletableFuture<List<String>> future = new CompletableFuture<>();
      api.getUserItems(uuid).enqueue(new Callback<List<String>>() {
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
              if (!response.isSuccessful() || response.body() == null) {
                future.completeExceptionally(new RuntimeException("Invalid response"));
                return;
              } 
              future.complete(response.body());
            }
            
            public void onFailure(Call<List<String>> call, Throwable t) {
              future.completeExceptionally(t);
            }
          });
      return future;
    }
    
    @NonNull
    public static CompletableFuture<List<String>> getDailyItems(@NonNull String uuid) {
      if (uuid == null)
        throw new NullPointerException("uuid is marked non-null but is null"); 
      CompletableFuture<List<String>> future = new CompletableFuture<>();
      PalaShopMod.getServer().getShopAPI().getUserDailyItems(uuid).enqueue(CompletableCallback.create(future));
      return future;
    }
    
    @NonNull
    public static CompletableFuture<Boolean> hasItem(@NonNull String uuid, @NonNull String item) {
      if (uuid == null)
        throw new NullPointerException("uuid is marked non-null but is null"); 
      if (item == null)
        throw new NullPointerException("item is marked non-null but is null"); 
      CompletableFuture<Boolean> future = new CompletableFuture<>();
      PalaShopMod.getServer().getShopAPI().hasUserItem(uuid, item).enqueue(CompletableCallback.create(future));
      return future;
    }
    
    @NonNull
    public static CompletableFuture<List<Subscription>> getSubscriptions(@NonNull String uuid) {
      if (uuid == null)
        throw new NullPointerException("uuid is marked non-null but is null"); 
      CompletableFuture<List<Subscription>> future = new CompletableFuture<>();
      PalaShopMod.getServer().getShopAPI().getUserSubscriptions(uuid).enqueue(CompletableCallback.create(future));
      return future;
    }
    
    @NonNull
    public static CompletableFuture<Map.Entry<ShopBuyResponse, BuyableObject<? extends IBuyable>>> buy(@NonNull final EntityPlayer player, @NonNull final String item, final boolean offer) {
      if (player == null)
        throw new NullPointerException("player is marked non-null but is null"); 
      if (item == null)
        throw new NullPointerException("item is marked non-null but is null"); 
      final String uuid = UUIDUtils.toString((Entity)player);
      final CompletableFuture<Map.Entry<ShopBuyResponse, BuyableObject<? extends IBuyable>>> future = new CompletableFuture<>();
      final IShopAPI api = PalaShopMod.getServer().getShopAPI();
      api.getUser(uuid).enqueue(new Callback<ShopUser>() {
            public void onResponse(Call<ShopUser> shopUserCall, Response<ShopUser> shopUserResponse) {
              if (!shopUserResponse.isSuccessful() || shopUserResponse.body() == null) {
                future.completeExceptionally(new RuntimeException("Invalid response"));
                return;
              } 
              ShopUser shopUser = (ShopUser)shopUserResponse.body();
              Optional<BuyableObject<? extends IBuyable>> optional = Optional.empty();
              if (offer) {
                Optional<ShopOffer> shopOffer = ShopManager.getOffer(item);
                if (shopOffer.isPresent())
                  optional = Optional.of(BuyableObject.of(shopUser, shopOffer.get())); 
              } else {
                Optional<IShopItem> shopItem = ShopManager.getItem(item);
                if (shopItem.isPresent())
                  optional = Optional.of(BuyableObject.of(shopUser, shopItem.get())); 
              } 
              if (!optional.isPresent()) {
                future.complete(new AbstractMap.SimpleEntry<>(new ShopBuyResponse(uuid, item, offer, 0L, ShopBuyResponse.Status.ITEM_NOT_FOUND, null, null), null));
                return;
              } 
              final BuyableObject<? extends IBuyable> buyable = optional.get();
              if (!buyable.getItem().isAvailable().booleanValue() || (buyable.getItem() instanceof IShopItem && !((IShopItem)buyable.getItem()).isBuyable().booleanValue())) {
                future.complete(new AbstractMap.SimpleEntry<>(new ShopBuyResponse(uuid, item, offer, 0L, ShopBuyResponse.Status.ITEM_NOT_AVAILABLE, null, null), buyable));
                return;
              } 
              List<IShopItem> items = new ArrayList<>();
              if (buyable.getItem() instanceof ShopOffer) {
                items.addAll(((ShopOffer)buyable.getItem()).getShopItems());
              } else {
                items.add((IShopItem)buyable.getItem());
              } 
              for (IShopItem shopItem : items) {
                if (shopItem.getProviderInstance() == null)
                  continue; 
                ShopBuyProviderEvent event = ShopBuyProviderEvent.pre(uuid, shopItem);
                ProviderDispatcher.dispatch(shopItem.getProviderInstance(), (ProviderEvent)event);
                if (event.isCanceled() || event.getResult().equals(shopItem)) {
                  future.complete(new AbstractMap.SimpleEntry<>(new ShopBuyResponse(uuid, item, offer, 0L, ShopBuyResponse.Status.ITEM_NOT_AVAILABLE, null, null), buyable));
                  return;
                } 
              } 
              ConditionalBuyableObject<? extends IBuyable> conditionalBuyable = ConditionalBuyableObject.from(shopUser, player, buyable);
              if ((buyable.isSubscription() && !conditionalBuyable.isBuyable() && conditionalBuyable.getErrorType() != ConditionalBuyableObject.ErrorType.OWNED) || (!buyable.isSubscription() && !conditionalBuyable.isBuyable())) {
                future.complete(new AbstractMap.SimpleEntry<>(new ShopBuyResponse(uuid, item, offer, 0L, ShopBuyResponse.Status.CONDITIONS_FAILED, null, conditionalBuyable.getError()), buyable));
                return;
              } 
              ShopBuyRequest request = new ShopBuyRequest(uuid, item, ServerUtils.getServerFullName(), offer);
              api.buyUserItem(request.getUuid(), request.getItem(), request).enqueue(new Callback<ShopBuyResponse>() {
                    public void onResponse(Call<ShopBuyResponse> call, Response<ShopBuyResponse> response) {
                      if (!response.isSuccessful() || response.body() == null) {
                        future.completeExceptionally(new RuntimeException("Invalid response"));
                        return;
                      } 
                      if (((ShopBuyResponse)response.body()).getStatus() == ShopBuyResponse.Status.SUCCESS && ((ShopBuyResponse)response.body()).getItems() != null && !((ShopBuyResponse)response.body()).getItems().isEmpty()) {
                        ((ShopBuyResponse)response.body()).getItems().forEach(shopItem -> ShopManager.User.apply(player, shopItem));
                        for (IShopItem shopItem : ((ShopBuyResponse)response.body()).getItems()) {
                          if (shopItem.getProviderInstance() == null)
                            continue; 
                          ProviderDispatcher.dispatch(shopItem.getProviderInstance(), (ProviderEvent)ShopBuyProviderEvent.post(uuid, shopItem));
                        } 
                      } 
                      future.complete(new AbstractMap.SimpleEntry<>(response.body(), buyable));
                    }
                    
                    public void onFailure(Call<ShopBuyResponse> call, Throwable t) {
                      future.completeExceptionally(t);
                    }
                  });
            }
            
            public void onFailure(Call<ShopUser> shopUserCall, Throwable shopUserThrowable) {
              future.completeExceptionally(shopUserThrowable);
            }
          });
      return future;
    }
    
    @NonNull
    public static CompletableFuture<SubscriptionMutationResponse> mutateSubscription(@NonNull EntityPlayer player, @NonNull String item, Subscription.Status status) {
      if (player == null)
        throw new NullPointerException("player is marked non-null but is null"); 
      if (item == null)
        throw new NullPointerException("item is marked non-null but is null"); 
      String uuid = UUIDUtils.toString((Entity)player);
      CompletableFuture<SubscriptionMutationResponse> future = new CompletableFuture<>();
      PalaShopMod.getServer().getShopAPI().mutateUserSubscription(uuid, item, new SubscriptionMutationRequest(uuid, item, status)).enqueue(CompletableCallback.create(future));
      return future;
    }
    
    public static void apply(@NonNull EntityPlayer player, @NonNull IShopItem shopItem) {
      if (player == null)
        throw new NullPointerException("player is marked non-null but is null"); 
      if (shopItem == null)
        throw new NullPointerException("shopItem is marked non-null but is null"); 
      String uuid = UUIDUtils.toString((Entity)player);
      if (shopItem.getProviderInstance() != null && ProviderDispatcher.dispatch(shopItem.getProviderInstance(), (ProviderEvent)ShopItemApplyProviderEvent.pre(player, shopItem)))
        return; 
      if (shopItem.getExecutions() != null && (shopItem.getExecutions()).length > 0 && shopItem.getExecutionType() != null)
        if (shopItem.getExecutionType() == ShopItem.ExecutionType.COMMAND) {
          for (String command : shopItem.getExecutions()) {
            command = command.replace("%username%", player.func_70005_c_()).replace("%player%", player.func_70005_c_()).replace("%uuid%", uuid).replace("%server%", ServerUtils.getServerName()).replace("%server_name%", ServerUtils.getServerName()).replace("%server_type%", ServerUtils.getServerType().toString()).replace("%date%", String.valueOf(System.currentTimeMillis()));
            if (command.contains("int(") && command.contains(")")) {
              int start = command.indexOf("int(") + 4;
              int end = command.indexOf(")", start);
              String value = command.substring(start, end);
              int quantity = Double.valueOf(value).intValue();
              command = command.replace("int(" + value + ")", String.valueOf(quantity));
            } 
            ConsoleUtils.executeCommand(command);
          } 
        } else if (shopItem.getExecutionType() == ShopItem.ExecutionType.ITEM) {
          for (String execution : shopItem.getExecutions()) {
            ItemStack item = ItemUtils.parse(execution);
            if (item != null) {
              while (item != null && item.field_77994_a > item.func_77976_d()) {
                ItemStack clone = item.func_77946_l();
                clone.field_77994_a = item.func_77976_d();
                item.field_77994_a -= item.func_77976_d();
                PalaGiveManager.inst().give(player, clone);
              } 
              if (item != null && item.field_77994_a > 0)
                PalaGiveManager.inst().give(player, item); 
            } 
          } 
        }  
      if (shopItem.getProviderInstance() != null)
        ProviderDispatcher.dispatch(shopItem.getProviderInstance(), (ProviderEvent)ShopItemApplyProviderEvent.post(player, shopItem)); 
      player.field_71069_bz.func_75142_b();
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\ShopManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */