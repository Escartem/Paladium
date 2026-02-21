package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server;

import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.ProviderLoadProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.SprayCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.api.ISprayCosmeticAPI;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.common.SprayCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.listener.SprayCosmeticExecuteServerListener;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.manager.SprayCosmeticManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class SprayCosmeticServerProxy extends SprayCosmeticCommonProxy {
  private ISprayCosmeticAPI api;
  
  public ISprayCosmeticAPI getApi() {
    return this.api;
  }
  
  @ProviderListener
  public void onModInit(ModInitProviderEvent event) {
    super.onModInit(event);
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initAPI();
    initManager();
    initListener();
    initBehavior();
  }
  
  private void initAPI() {
    this.api = (ISprayCosmeticAPI)PalaShopMod.getServer().createAPI("cosmetic/" + SprayCosmeticFactory.ID, ISprayCosmeticAPI.class);
  }
  
  private void initManager() {
    SprayCosmeticManager.load();
  }
  
  private void initListener() {
    addListener(new Class[] { SprayCosmeticExecuteServerListener.class });
  }
  
  private void initBehavior() {}
  
  @ProviderListener
  public void onProviderLoad(ProviderLoadProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST || this.api == null)
      return; 
    SprayCosmeticManager.load();
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return new ArrayList<>(SprayCosmeticManager.getCosmetics());
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return SprayCosmeticManager.getCosmetic(id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\server\SprayCosmeticServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */