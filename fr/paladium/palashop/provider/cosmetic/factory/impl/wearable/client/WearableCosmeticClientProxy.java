package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import fr.paladium.palakeybind.common.key.impl.IKeyBinding;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.CosmeticNavbarManager;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.cache.WearableCosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.dto.WearableCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.listener.WearableCosmeticKeybindListener;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.listener.WearableCosmeticRenderPlayerListener;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.loader.WearableCosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.client.render.ui.shop.WearableCosmeticShopRenderRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common.WearableCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.dto.WearableCosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;

public class WearableCosmeticClientProxy extends WearableCosmeticCommonProxy {
  private KeyBinding actionKey;
  
  public KeyBinding getActionKey() {
    return this.actionKey;
  }
  
  @ProviderListener
  public void onModPreInit(ModPreInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initLoader();
    initRender();
    initKeybind();
    initListener();
  }
  
  @ProviderListener
  public void onModInit(ModInitProviderEvent event) {
    super.onModInit(event);
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initNavbar();
  }
  
  private void initLoader() {
    WearableCosmeticClientLoader.getInstance().start();
  }
  
  private void initRender() {
    WearableCosmeticFactory.getInstance().registerRenderer("shop_render", (ShopElementRenderer)new WearableCosmeticShopRenderRenderer());
  }
  
  private void initKeybind() {
    this.actionKey = new KeyBinding("key.cosmetic.wearable.action", 21, "key.categories.cosmetic");
    if (this.actionKey instanceof IKeyBinding)
      ((IKeyBinding)this.actionKey).setSubCategory("wearable"); 
    ClientRegistry.registerKeyBinding(this.actionKey);
  }
  
  private void initListener() {
    addListener(new Class[] { WearableCosmeticKeybindListener.class });
    addListener(new Class[] { WearableCosmeticRenderPlayerListener.class });
  }
  
  private void initNavbar() {
    CosmeticNavbarManager.register(new CosmeticNavbarElement(WearableCosmeticFactory.ID + "_head", "tête", WearableCosmeticFactory.ID, Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/navbar/head.png")), cosmetic -> (cosmetic instanceof WearableCosmeticClient && ((WearableCosmeticClient)cosmetic).getType() == WearableCosmetic.WearableCosmeticType.HEAD)));
    CosmeticNavbarManager.register(new CosmeticNavbarElement(WearableCosmeticFactory.ID + "_body", "corps", WearableCosmeticFactory.ID, Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/navbar/body.png")), cosmetic -> (cosmetic instanceof WearableCosmeticClient && ((WearableCosmeticClient)cosmetic).getType() == WearableCosmetic.WearableCosmeticType.BODY)));
    CosmeticNavbarManager.register(new CosmeticNavbarElement(WearableCosmeticFactory.ID + "_suit", "costume", WearableCosmeticFactory.ID, Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/navbar/suit.png")), cosmetic -> (cosmetic instanceof WearableCosmeticClient && ((WearableCosmeticClient)cosmetic).getType() == WearableCosmetic.WearableCosmeticType.SUIT)));
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return WearableCosmeticClientCache.getInstance().getAll();
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return (Optional)Optional.of(WearableCosmeticClientCache.getInstance().get(id));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\client\WearableCosmeticClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */