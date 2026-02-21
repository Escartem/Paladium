package fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common;

import fr.paladium.palashop.common.provider.dto.impl.ShopProviderProxy;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.common.dto.behavior.CosmeticBehaviorManager;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.WearableCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common.behavior.WearableCosmeticKillAnimationBehavior;
import fr.paladium.palashop.provider.cosmetic.factory.impl.wearable.common.network.BBPacketAnimateWearableCosmetic;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public abstract class WearableCosmeticCommonProxy extends ShopProviderProxy {
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
    initNetwork(WearableCosmeticFactory.ID);
    addPacket(new Class[] { BBPacketAnimateWearableCosmetic.class });
  }
  
  private void initBehavior() {
    CosmeticBehaviorManager.registerBehavior(new Class[] { WearableCosmeticKillAnimationBehavior.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\wearable\common\WearableCosmeticCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */