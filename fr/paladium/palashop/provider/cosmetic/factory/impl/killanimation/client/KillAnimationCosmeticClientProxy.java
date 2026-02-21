package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.CosmeticNavbarManager;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.KillAnimationCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.cache.KillAnimationCosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.dto.KillAnimationCosmeticClient;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.loader.KillAnimationCosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.render.entity.EntityKillAnimationCosmeticRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.render.ui.cosmetic.KillAnimationCosmeticCosmeticRenderRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.client.render.ui.shop.KillAnimationCosmeticShopRenderRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.common.KillAnimationCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.common.entity.EntityKillAnimationCosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;

public class KillAnimationCosmeticClientProxy extends KillAnimationCosmeticCommonProxy {
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
    KillAnimationCosmeticClientLoader.getInstance().start();
  }
  
  private void initRender() {
    KillAnimationCosmeticFactory.getInstance().registerRenderer("shop_render", (ShopElementRenderer)new KillAnimationCosmeticShopRenderRenderer());
    KillAnimationCosmeticFactory.getInstance().registerRenderer("cosmetic_render", (ShopElementRenderer)new KillAnimationCosmeticCosmeticRenderRenderer());
    RenderingRegistry.registerEntityRenderingHandler(EntityKillAnimationCosmetic.class, (Render)new EntityKillAnimationCosmeticRenderer());
  }
  
  private void initListener() {}
  
  private void initNavbar() {
    CosmeticNavbarManager.register((new CosmeticNavbarElement(KillAnimationCosmeticFactory.ID, "animation de kill", KillAnimationCosmeticFactory.ID, Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/navbar/kill_animation.png")), KillAnimationCosmeticClient.class::isInstance)).index(4));
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return KillAnimationCosmeticClientCache.getInstance().getAll();
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return (Optional)Optional.of(KillAnimationCosmeticClientCache.getInstance().get(id));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\client\KillAnimationCosmeticClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */