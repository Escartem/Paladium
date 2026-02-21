package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.CosmeticNavbarManager;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.SprayCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.cache.SprayCosmeticClientCache;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.listener.SprayCosmeticExecuteClientListener;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.loader.SprayCosmeticClientLoader;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.client.render.entity.EntitySprayCosmeticRenderer;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.common.SprayCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.common.entity.EntitySprayCosmetic;
import fr.paladium.zephyrui.lib.resource.Resource;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;

public class SprayCosmeticClientProxy extends SprayCosmeticCommonProxy {
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
    SprayCosmeticClientLoader.getInstance().start();
  }
  
  private void initRender() {
    RenderingRegistry.registerEntityRenderingHandler(EntitySprayCosmetic.class, (Render)new EntitySprayCosmeticRenderer());
  }
  
  private void initListener() {
    addListener(new Class[] { SprayCosmeticExecuteClientListener.class });
  }
  
  private void initNavbar() {
    CosmeticNavbarManager.register((new CosmeticNavbarElement(SprayCosmeticFactory.ID, "spray", SprayCosmeticFactory.ID, Resource.of(new ResourceLocation("palashop", "textures/ui/cosmetic/navbar/spray.png")), cosmetic -> cosmetic.getFactory().getId().equals(SprayCosmeticFactory.ID))).index(3));
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return SprayCosmeticClientCache.getInstance().getAll();
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return (Optional)Optional.of(SprayCosmeticClientCache.getInstance().get(id));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\client\SprayCosmeticClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */