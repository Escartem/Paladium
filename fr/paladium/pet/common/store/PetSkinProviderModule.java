package fr.paladium.pet.common.store;

import fr.paladium.palashop.common.provider.ProviderManager;
import fr.paladium.palashop.common.provider.dto.IShopProvider;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.ProviderLoadProviderEvent;
import fr.paladium.pet.common.store.provider.PetSkinShopProvider;

public class PetSkinProviderModule {
  private static PetSkinProviderModule instance;
  
  public PetSkinProviderModule() {
    instance = this;
  }
  
  @ProviderListener
  public void onProviderLoad(ProviderLoadProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.PRE)
      return; 
    try {
      ProviderManager.register((IShopProvider)new PetSkinShopProvider());
    } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
      throw new IllegalStateException("Failed to register PetSkinShopProvider", e);
    } 
  }
  
  public static PetSkinProviderModule getInstance() {
    if (instance == null)
      throw new IllegalStateException("PetSkinProviderModule is not initialized yet"); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\store\PetSkinProviderModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */