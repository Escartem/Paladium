package fr.paladium.pet.common.store.provider.server;

import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.pet.common.store.provider.common.PetSkinCommonProxy;
import fr.paladium.pet.common.store.provider.server.listener.PetSkinPlayerServerListener;

public class PetSkinServerProxy extends PetSkinCommonProxy {
  @ProviderListener
  public void onModInit(ModInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    addListener(new Class[] { PetSkinPlayerServerListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\store\provider\server\PetSkinServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */