package fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.common;

import cpw.mods.fml.common.registry.EntityRegistry;
import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.common.provider.dto.impl.ShopProviderProxy;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.KillAnimationCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.killanimation.common.entity.EntityKillAnimationCosmetic;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public abstract class KillAnimationCosmeticCommonProxy extends ShopProviderProxy {
  @NonNull
  public abstract <T extends fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic> List<T> getCosmetics();
  
  @NonNull
  public abstract <T extends fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic> Optional<T> getCosmetic(@NonNull String paramString);
  
  @ProviderListener
  public void onModInit(ModInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initEntity();
    initNetwork();
    initBehavior();
  }
  
  private void initEntity() {
    EntityRegistry.registerModEntity(EntityKillAnimationCosmetic.class, "EntityKillCosmetic", 0, PalaShopMod.getInstance(), 100, 1, true);
  }
  
  private void initNetwork() {
    initNetwork(KillAnimationCosmeticFactory.ID);
  }
  
  private void initBehavior() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\killanimation\common\KillAnimationCosmeticCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */