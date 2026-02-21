package fr.paladium.pet.common.store.provider.common;

import fr.paladium.palashop.common.provider.dto.impl.ShopProviderProxy;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;

public class PetSkinCommonProxy extends ShopProviderProxy {
  @ProviderListener
  public void onModPreInit(ModPreInitProviderEvent event) {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\store\provider\common\PetSkinCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */