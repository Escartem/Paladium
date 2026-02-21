package fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.server;

import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.ProviderLoadProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.CloakCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.api.ICloakCosmeticAPI;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.common.CloakCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.cloak.server.manager.CloakCosmeticManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class CloakCosmeticServerProxy extends CloakCosmeticCommonProxy {
  private ICloakCosmeticAPI api;
  
  public ICloakCosmeticAPI getApi() {
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
    this.api = (ICloakCosmeticAPI)PalaShopMod.getServer().createAPI("cosmetic/" + CloakCosmeticFactory.ID, ICloakCosmeticAPI.class);
  }
  
  private void initManager() {
    CloakCosmeticManager.load();
  }
  
  private void initBehavior() {}
  
  @ProviderListener
  public void onProviderLoad(ProviderLoadProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST || this.api == null)
      return; 
    CloakCosmeticManager.load();
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return new ArrayList<>(CloakCosmeticManager.getCosmetics());
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return CloakCosmeticManager.getCosmetic(id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\cloak\server\CloakCosmeticServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */