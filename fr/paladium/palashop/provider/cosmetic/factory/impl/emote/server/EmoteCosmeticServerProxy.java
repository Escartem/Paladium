package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server;

import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.ProviderLoadProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.api.IEmoteCosmeticAPI;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.common.EmoteCosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.listener.EmoteCosmeticExecuteServerListener;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.server.manager.EmoteCosmeticManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class EmoteCosmeticServerProxy extends EmoteCosmeticCommonProxy {
  private IEmoteCosmeticAPI api;
  
  public IEmoteCosmeticAPI getApi() {
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
    this.api = (IEmoteCosmeticAPI)PalaShopMod.getServer().createAPI("cosmetic/" + EmoteCosmeticFactory.ID, IEmoteCosmeticAPI.class);
  }
  
  private void initManager() {
    EmoteCosmeticManager.load();
  }
  
  private void initListener() {
    addListener(new Class[] { EmoteCosmeticExecuteServerListener.class });
  }
  
  private void initBehavior() {}
  
  @ProviderListener
  public void onProviderLoad(ProviderLoadProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST || this.api == null)
      return; 
    EmoteCosmeticManager.load();
  }
  
  @NonNull
  public List<? extends ICosmetic> getCosmetics() {
    return new ArrayList<>(EmoteCosmeticManager.getCosmetics());
  }
  
  @NonNull
  public Optional<? extends ICosmetic> getCosmetic(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return EmoteCosmeticManager.getCosmetic(id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\server\EmoteCosmeticServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */