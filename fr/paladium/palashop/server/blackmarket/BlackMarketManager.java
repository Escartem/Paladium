package fr.paladium.palashop.server.blackmarket;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.cache.SidedActionCache;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerAction;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerActionContext;
import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.api.utils.CompletableCallback;
import fr.paladium.palashop.common.utils.time.UniversalTimeUtils;
import fr.paladium.palashop.server.blackmarket.dto.BlackMarketConfig;
import fr.paladium.palashop.server.blackmarket.dto.BlackMarketData;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import org.bson.Document;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlackMarketManager {
  public static final int ITEM_COUNT = 6;
  
  public static final Object LOCK = new Object();
  
  private static BlackMarketConfig config;
  
  public static void load() {
    System.out.println("[BlackMarketManager] Loading BlackMarket data...");
    synchronized (LOCK) {
      try {
        config = (BlackMarketConfig)PalaShopMod.getServer().getBlackMarketAPI().getConfig().execute().body();
      } catch (IOException e) {
        throw new RuntimeException(e);
      } 
    } 
    if (config != null) {
      System.out.println("[-] BlackMarket found");
      System.out.println("[-] ID: " + config.getId());
      System.out.println("[-] Active: " + config.isActive());
      System.out.println("[-] Expiration: " + UniversalTimeUtils.fromLong(config.getExpiration()));
    } else {
      System.out.println("[-] BlackMarket not found");
    } 
    System.out.println("[BlackMarketManager] BlackMarket data loaded");
  }
  
  @NonNull
  public static CompletableFuture<BlackMarketConfig> update() {
    final CompletableFuture<BlackMarketConfig> future = new CompletableFuture<>();
    PalaShopMod.getServer().getBlackMarketAPI().update().enqueue(new Callback<Void>() {
          public void onResponse(Call<Void> call, Response<Void> response) {
            if (!response.isSuccessful()) {
              future.completeExceptionally(new RuntimeException("Invalid response"));
              return;
            } 
            (new Thread(() -> {
                  synchronized (BlackMarketManager.LOCK) {
                    try {
                      BlackMarketManager.load();
                    } catch (Exception e) {
                      future.completeExceptionally(e);
                      return;
                    } 
                    future.complete(BlackMarketManager.config);
                  } 
                }"BlackMarketManager/Update")).start();
          }
          
          public void onFailure(Call<Void> call, Throwable t) {
            future.completeExceptionally(t);
          }
        });
    return future;
  }
  
  @ServerAction(cache = @SidedActionCache(client = "30s", server = "1m"))
  @NonNull
  public static CompletableFuture<Boolean> isActive() {
    return ServerActionHook.useServer(context -> CompletableFuture.completedFuture(Boolean.valueOf((config != null && config.isActive()))), new Object[0]);
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static CompletableFuture<BlackMarketData> getBlackMarket(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (config == null || !config.isActive() || config == null || !config.isActive())
      return CompletableFuture.completedFuture(null); 
    CompletableFuture<BlackMarketData> future = new CompletableFuture<>();
    PalaShopMod.getServer().getBlackMarketAPI().getBlackMarket(uuid).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @SideOnly(Side.SERVER)
  public static BlackMarketData getBlackMarket(@NonNull ShopUser shopUser) {
    if (shopUser == null)
      throw new NullPointerException("shopUser is marked non-null but is null"); 
    if (config == null || !config.isActive() || !shopUser.hasData("blackmarket"))
      return null; 
    Document document = shopUser.getData("blackmarket");
    if (document == null)
      return null; 
    return BlackMarketData.from(document);
  }
  
  @SideOnly(Side.SERVER)
  public static int getDiscount(@NonNull ShopUser shopUser, @NonNull String itemId) {
    if (shopUser == null)
      throw new NullPointerException("shopUser is marked non-null but is null"); 
    if (itemId == null)
      throw new NullPointerException("itemId is marked non-null but is null"); 
    BlackMarketData blackMarketData = getBlackMarket(shopUser);
    if (blackMarketData == null)
      return 0; 
    return ((Integer)blackMarketData.getItems().getOrDefault(itemId, Integer.valueOf(0))).intValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\blackmarket\BlackMarketManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */