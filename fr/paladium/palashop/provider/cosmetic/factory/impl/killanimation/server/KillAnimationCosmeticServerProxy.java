package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server;

import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.ProviderLoadProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.KillAnimationCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.api.IKillAnimationCosmeticAPI;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.common.KillAnimationCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.listener.KillAnimationCosmeticServerListener;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.server.manager.KillAnimationCosmeticManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class KillAnimationCosmeticServerProxy extends KillAnimationCosmeticCommonProxy {
  private IKillAnimationCosmeticAPI api;
  
  public IKillAnimationCosmeticAPI getApi() {
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
    this.api = (IKillAnimationCosmeticAPI)PalaShopMod.getServer().createAPI("cosmetic/" + KillAnimationCosmeticFactory.ID, IKillAnimationCosmeticAPI.class);
  }
  
  private void initManager() {
    KillAnimationCosmeticManager.load();
  }
  
  private void initListener() {
    addListener(new Class[] { KillAnimationCosmeticServerListener.class });
  }
  
  private void initBehavior() {}
  
  @ProviderListener
  public void onProviderLoad(ProviderLoadProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST || this.api == null)
      return; 
    KillAnimationCosmeticManager.load();
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return new ArrayList<>(KillAnimationCosmeticManager.getCosmetics());
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return KillAnimationCosmeticManager.getCosmetic(id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\server\KillAnimationCosmeticServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */