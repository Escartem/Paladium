package fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.server;

import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.ProviderLoadProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.KillMessageCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.api.IKillMessageCosmeticAPI;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.common.KillMessageCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.server.listener.KillMessageCosmeticServerListener;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killmessage.server.manager.KillMessageCosmeticManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class KillMessageCosmeticServerProxy extends KillMessageCosmeticCommonProxy {
  private IKillMessageCosmeticAPI api;
  
  public IKillMessageCosmeticAPI getApi() {
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
    this.api = (IKillMessageCosmeticAPI)PalaShopMod.getServer().createAPI("cosmetic/" + KillMessageCosmeticFactory.ID, IKillMessageCosmeticAPI.class);
  }
  
  private void initManager() {
    KillMessageCosmeticManager.load();
  }
  
  private void initListener() {
    addListener(new Class[] { KillMessageCosmeticServerListener.class });
  }
  
  private void initBehavior() {}
  
  @ProviderListener
  public void onProviderLoad(ProviderLoadProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST || this.api == null)
      return; 
    KillMessageCosmeticManager.load();
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return new ArrayList<>(KillMessageCosmeticManager.getCosmetics());
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return KillMessageCosmeticManager.getCosmetic(id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killmessage\server\KillMessageCosmeticServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */