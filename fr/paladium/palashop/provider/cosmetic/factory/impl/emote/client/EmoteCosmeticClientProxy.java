package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client;

import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.CosmeticNavbarManager;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.cache.EmoteCosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.dto.EmoteCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.listener.EmoteCosmeticRenderPlayerListener;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.loader.EmoteCosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.render.ui.cosmetic.EmoteCosmeticCosmeticThumbnailRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.render.ui.shop.EmoteCosmeticShopRenderRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.render.ui.shop.EmoteCosmeticShopThumbnailRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.common.EmoteCosmeticCommonProxy;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class EmoteCosmeticClientProxy extends EmoteCosmeticCommonProxy {
  @ProviderListener
  public void onModPreInit(ModPreInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initLoader();
    initRender();
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
    EmoteCosmeticClientLoader.getInstance().start();
  }
  
  private void initRender() {
    EmoteCosmeticFactory.getInstance().registerRenderer("shop_thumbnail", (ShopElementRenderer)new EmoteCosmeticShopThumbnailRenderer());
    EmoteCosmeticFactory.getInstance().registerRenderer("shop_render", (ShopElementRenderer)new EmoteCosmeticShopRenderRenderer());
    EmoteCosmeticFactory.getInstance().registerRenderer("cosmetic_thumbnail", (ShopElementRenderer)new EmoteCosmeticCosmeticThumbnailRenderer());
  }
  
  private void initListener() {
    addListener(new Class[] { EmoteCosmeticRenderPlayerListener.class });
  }
  
  private void initNavbar() {
    CosmeticNavbarManager.register((new CosmeticNavbarElement(EmoteCosmeticFactory.ID, "emote", EmoteCosmeticFactory.ID, Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/navbar/emote.png")), EmoteCosmeticClient.class::isInstance)).index(2));
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return EmoteCosmeticClientCache.getInstance().getAll();
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return (Optional)Optional.of(EmoteCosmeticClientCache.getInstance().get(id));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\EmoteCosmeticClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */