package fr.paladium.pet.common.store.provider.client;

import fr.paladium.palashop.common.provider.dto.render.ShopElementRenderer;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.pet.common.store.provider.PetSkinShopProvider;
import fr.paladium.pet.common.store.provider.client.render.shop.PetSkinShopRenderRenderer;
import fr.paladium.pet.common.store.provider.common.PetSkinCommonProxy;

public class PetSkinClientProxy extends PetSkinCommonProxy {
  @ProviderListener
  public void onModInit(ModInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    PetSkinShopProvider.getInstance().registerRenderer("shop_render", (ShopElementRenderer)new PetSkinShopRenderRenderer());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\store\provider\client\PetSkinClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */