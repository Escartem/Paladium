package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server;

import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.ProviderLoadProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.api.IWearableCosmeticAPI;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common.WearableCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.server.manager.WearableCosmeticManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class WearableCosmeticServerProxy extends WearableCosmeticCommonProxy {
  private IWearableCosmeticAPI api;
  
  public IWearableCosmeticAPI getApi() {
    return this.api;
  }
  
  @ProviderListener
  public void onModInit(ModInitProviderEvent event) {
    super.onModInit(event);
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initAPI();
    initManager();
    initBehavior();
  }
  
  private void initAPI() {
    this.api = (IWearableCosmeticAPI)PalaShopMod.getServer().createAPI("cosmetic/" + WearableCosmeticFactory.ID, IWearableCosmeticAPI.class);
  }
  
  private void initManager() {
    WearableCosmeticManager.load();
  }
  
  private void initBehavior() {}
  
  @ProviderListener
  public void onProviderLoad(ProviderLoadProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST || this.api == null)
      return; 
    WearableCosmeticManager.load();
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return new ArrayList<>(WearableCosmeticManager.getCosmetics());
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return WearableCosmeticManager.getCosmetic(id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\server\WearableCosmeticServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */