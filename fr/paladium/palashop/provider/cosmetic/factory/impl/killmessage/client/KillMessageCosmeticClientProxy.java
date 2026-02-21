package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client;

import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.CosmeticNavbarManager;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.KillMessageCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.cache.KillMessageCosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.dto.KillMessageCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.render.ui.cosmetic.KillMessageCosmeticCosmeticRenderRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.render.ui.cosmetic.KillMessageCosmeticCosmeticThumbnailRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.render.ui.shop.KillMessageCosmeticShopRenderRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.client.render.ui.shop.KillMessageCosmeticShopThumbnailRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.common.KillMessageCosmeticCommonProxy;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class KillMessageCosmeticClientProxy extends KillMessageCosmeticCommonProxy {
  @ProviderListener
  public void onModPreInit(ModPreInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
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
  
  private void initRender() {
    KillMessageCosmeticFactory.getInstance().registerRenderer("shop_thumbnail", (ShopElementRenderer)new KillMessageCosmeticShopThumbnailRenderer());
    KillMessageCosmeticFactory.getInstance().registerRenderer("shop_render", (ShopElementRenderer)new KillMessageCosmeticShopRenderRenderer());
    KillMessageCosmeticFactory.getInstance().registerRenderer("cosmetic_thumbnail", (ShopElementRenderer)new KillMessageCosmeticCosmeticThumbnailRenderer());
    KillMessageCosmeticFactory.getInstance().registerRenderer("cosmetic_render", (ShopElementRenderer)new KillMessageCosmeticCosmeticRenderRenderer());
  }
  
  private void initListener() {}
  
  private void initNavbar() {
    CosmeticNavbarManager.register((new CosmeticNavbarElement(KillMessageCosmeticFactory.ID, "message de kill", KillMessageCosmeticFactory.ID, Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/navbar/kill_message.png")), KillMessageCosmeticClient.class::isInstance)).index(5));
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return KillMessageCosmeticClientCache.getInstance().getAll();
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return (Optional)Optional.of(KillMessageCosmeticClientCache.getInstance().get(id));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\client\KillMessageCosmeticClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */