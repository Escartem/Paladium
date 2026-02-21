package fr.paladium.palashop.provider.cosmetic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.api.utils.CompletableCallback;
import fr.paladium.palashop.common.provider.dto.ShopProvider;
import fr.paladium.palashop.common.provider.dto.impl.AShopProvider;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.ProviderLoadProviderEvent;
import fr.paladium.palashop.provider.cosmetic.api.request.CosmeticMutationRequest;
import fr.paladium.palashop.provider.cosmetic.client.CosmeticClientProxy;
import fr.paladium.palashop.provider.cosmetic.common.CosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.CloakCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.KillAnimationCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.KillMessageCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.SprayCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.server.CosmeticServerProxy;
import fr.paladium.palashop.provider.cosmetic.server.dto.cosmetic.ShopCosmeticData;
import fr.paladium.palashop.provider.cosmetic.server.dto.shop.CosmeticShopItem;
import fr.paladium.palashop.server.shop.event.ShopItemApplyProviderEvent;
import fr.paladium.zephyrui.internal.mod.utils.command.uuid.UUIDUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import net.minecraft.entity.Entity;

@ShopProvider(id = "cosmetic", item = CosmeticShopItem.class)
public class CosmeticProvider extends AShopProvider<CosmeticShopItem> {
  private static CosmeticProvider instance;
  
  private static CosmeticCommonProxy proxy;
  
  private final Map<String, CosmeticFactory> factories;
  
  public CosmeticProvider() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    instance = this;
    proxy = (CosmeticCommonProxy)createProxy("fr.paladium.palashop.provider.cosmetic.client.CosmeticClientProxy", "fr.paladium.palashop.provider.cosmetic.server.CosmeticServerProxy");
    ProviderDispatcher.register(proxy);
    this.factories = new HashMap<>();
    registerFactory((CosmeticFactory)new CloakCosmeticFactory(this));
    registerFactory((CosmeticFactory)new EmoteCosmeticFactory(this));
    registerFactory((CosmeticFactory)new KillAnimationCosmeticFactory(this));
    registerFactory((CosmeticFactory)new KillMessageCosmeticFactory(this));
    registerFactory((CosmeticFactory)new SprayCosmeticFactory(this));
    registerFactory((CosmeticFactory)new WearableCosmeticFactory(this));
  }
  
  @ProviderListener
  public void onEvent(ProviderEvent event) {
    if (event.isGlobal() && event instanceof ProviderLoadProviderEvent) {
      System.out.println((event.getPhase() == ProviderEvent.Phase.PRE) ? "[/] Loading cosmetics" : "[+] Cosmetics loaded");
      return;
    } 
    dispatch(event);
  }
  
  @ProviderListener
  public void onShopApply(ShopItemApplyProviderEvent<CosmeticShopItem> event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    addCosmetic(UUIDUtils.toString((Entity)((ShopItemApplyProviderEvent.ShopBuyProviderEventData)event.getResult()).getPlayer()), ((CosmeticShopItem)((ShopItemApplyProviderEvent.ShopBuyProviderEventData)event.getResult()).getShopItem()).getCosmeticId(), ((CosmeticShopItem)((ShopItemApplyProviderEvent.ShopBuyProviderEventData)event.getResult()).getShopItem()).getCosmeticFactory());
  }
  
  public void load() {
    load(ProviderLoadProviderEvent.post());
  }
  
  private void load(@NonNull ProviderLoadProviderEvent event) {
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    System.out.println("[/] Loading cosmetics");
    dispatch((ProviderEvent)event);
    System.out.println("[+] Cosmetics loaded");
  }
  
  public void dispatch(@NonNull ProviderEvent event) {
    if (event == null)
      throw new NullPointerException("event is marked non-null but is null"); 
    if (event.isGlobal())
      return; 
    ProviderDispatcher.dispatch(proxy, event);
    this.factories.values().forEach(factory -> ProviderDispatcher.dispatch(factory, event));
  }
  
  public Optional<CosmeticFactory> getFactory(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return Optional.ofNullable(this.factories.get(id));
  }
  
  public void registerFactory(@NonNull CosmeticFactory factory) {
    if (factory == null)
      throw new NullPointerException("factory is marked non-null but is null"); 
    this.factories.put(factory.getId(), factory);
    ProviderDispatcher.register(factory);
  }
  
  public <S> ShopElementRenderer<S> getRenderer(@NonNull String type, @NonNull S object) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    if (object == null)
      throw new NullPointerException("object is marked non-null but is null"); 
    for (CosmeticFactory factory : this.factories.values()) {
      ShopElementRenderer<S> renderer = factory.getRenderer(type, object);
      if (renderer != null && renderer.isValid(object))
        return renderer; 
    } 
    return super.getRenderer(type, object);
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public CompletableFuture<Void> update() {
    CompletableFuture<Void> future = new CompletableFuture<>();
    getServer().getApi().update().enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public CompletableFuture<Set<ShopCosmeticData>> getCosmetics(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    CompletableFuture<Set<ShopCosmeticData>> future = new CompletableFuture<>();
    getServer().getApi().getCosmetics(uuid).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public CompletableFuture<Boolean> hasCosmetic(@NonNull String uuid, @NonNull String cosmetic) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    CompletableFuture<Boolean> future = new CompletableFuture<>();
    getServer().getApi().hasCosmetic(uuid, cosmetic).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public CompletableFuture<Set<ShopCosmeticData>> getCosmetics(@NonNull String uuid, @NonNull String factory) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (factory == null)
      throw new NullPointerException("factory is marked non-null but is null"); 
    CompletableFuture<Set<ShopCosmeticData>> future = new CompletableFuture<>();
    getServer().getApi().getCosmetics(uuid, factory).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public CompletableFuture<ShopCosmeticData> addCosmetic(@NonNull String uuid, @NonNull ICosmetic cosmetic) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (cosmetic == null)
      throw new NullPointerException("cosmetic is marked non-null but is null"); 
    return addCosmetic(uuid, cosmetic.getId(), cosmetic.getFactory().getId());
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public CompletableFuture<ShopCosmeticData> addCosmetic(@NonNull String uuid, @NonNull String cosmeticId, @NonNull String cosmeticFactory) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (cosmeticId == null)
      throw new NullPointerException("cosmeticId is marked non-null but is null"); 
    if (cosmeticFactory == null)
      throw new NullPointerException("cosmeticFactory is marked non-null but is null"); 
    CompletableFuture<ShopCosmeticData> future = new CompletableFuture<>();
    getServer().getApi().addCosmetic(uuid, new CosmeticMutationRequest(uuid, cosmeticId, cosmeticFactory)).enqueue(CompletableCallback.create(future));
    return future;
  }
  
  @NonNull
  public static CosmeticCommonProxy getProxy() {
    return proxy;
  }
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public static CosmeticClientProxy getClient() {
    return (CosmeticClientProxy)proxy;
  }
  
  @SideOnly(Side.SERVER)
  @NonNull
  public static CosmeticServerProxy getServer() {
    return (CosmeticServerProxy)proxy;
  }
  
  @NonNull
  public static CosmeticProvider getInstance() {
    if (instance == null)
      throw new IllegalStateException("CosmeticProvider is not initialized yet!"); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\CosmeticProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */