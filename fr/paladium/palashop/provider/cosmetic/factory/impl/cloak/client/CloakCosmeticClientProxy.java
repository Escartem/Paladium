package fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client;

import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.CosmeticNavbarManager;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.CloakCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.cache.CloakCosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.listener.CloakCosmeticRenderPlayerListener;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.loader.CloakCosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.client.render.ui.shop.CloakCosmeticShopRenderRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.common.CloakCosmeticCommonProxy;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class CloakCosmeticClientProxy extends CloakCosmeticCommonProxy {
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
    CloakCosmeticClientLoader.getInstance().start();
  }
  
  private void initListener() {
    addListener(new Class[] { CloakCosmeticRenderPlayerListener.class });
  }
  
  private void initRender() {
    CloakCosmeticFactory.getInstance().registerRenderer("shop_render", (ShopElementRenderer)new CloakCosmeticShopRenderRenderer());
  }
  
  private void initNavbar() {
    CosmeticNavbarManager.register((new CosmeticNavbarElement(CloakCosmeticFactory.ID, "manteau", CloakCosmeticFactory.ID, Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/navbar/cloak.png")), cosmetic -> cosmetic.getFactory().getId().equals(CloakCosmeticFactory.ID))).index(1));
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return CloakCosmeticClientCache.getInstance().getAll();
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return (Optional)Optional.of(CloakCosmeticClientCache.getInstance().get(id));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\cloak\client\CloakCosmeticClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */