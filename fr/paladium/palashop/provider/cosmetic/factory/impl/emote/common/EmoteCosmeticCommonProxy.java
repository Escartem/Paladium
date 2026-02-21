package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.common;

import fr.paladium.palashop.common.provider.dto.impl.ShopProviderProxy;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.EmoteCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.emote.common.network.SCPacketEmoteCosmeticExecute;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public abstract class EmoteCosmeticCommonProxy extends ShopProviderProxy {
  @NonNull
  public abstract <T extends fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic> List<T> getCosmetics();
  
  @NonNull
  public abstract <T extends fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic> Optional<T> getCosmetic(@NonNull String paramString);
  
  @ProviderListener
  public void onModInit(ModInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initNetwork();
    initBehavior();
  }
  
  private void initNetwork() {
    initNetwork(EmoteCosmeticFactory.ID);
    addPacket(new Class[] { SCPacketEmoteCosmeticExecute.class });
  }
  
  private void initBehavior() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\common\EmoteCosmeticCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */