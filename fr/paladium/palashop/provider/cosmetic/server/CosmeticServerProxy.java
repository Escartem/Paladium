package fr.paladium.palashop.provider.cosmetic.server;

import fr.paladium.palaforgeutils.lib.command.annotated.registry.CommandRegistry;
import fr.paladium.palashop.PalaShopMod;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.ProviderListener;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.api.ICosmeticAPI;
import fr.paladium.palashop.provider.cosmetic.common.CosmeticCommonProxy;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.server.command.CosmeticCommand;
import fr.paladium.palashop.provider.cosmetic.server.dto.shop.CosmeticShopItem;
import fr.paladium.palashop.provider.cosmetic.server.listener.CosmeticPlayerListener;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.event.ShopLoadProviderEvent;
import java.util.Optional;

public class CosmeticServerProxy extends CosmeticCommonProxy {
  private ICosmeticAPI api;
  
  public ICosmeticAPI getApi() {
    return this.api;
  }
  
  @ProviderListener
  public void onModInit(ModInitProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    initAPI();
    initCommand();
    initListener();
  }
  
  @ProviderListener
  public void onShopLoad(ShopLoadProviderEvent event) {
    if (event.getPhase() != ProviderEvent.Phase.POST)
      return; 
    for (IShopItem shopItem : ShopManager.getItems()) {
      if (!(shopItem instanceof CosmeticShopItem))
        continue; 
      CosmeticShopItem cosmeticShopItem = (CosmeticShopItem)shopItem;
      Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(cosmeticShopItem.getCosmeticFactory());
      if (!optionalFactory.isPresent())
        continue; 
      CosmeticFactory factory = optionalFactory.get();
      Optional<? extends ICosmetic> optionalCosmetic = factory.getCosmetic(cosmeticShopItem.getCosmeticId());
      if (!optionalCosmetic.isPresent())
        continue; 
      cosmeticShopItem.setMappedName(((ICosmetic)optionalCosmetic.get()).getName());
    } 
  }
  
  private void initAPI() {
    this.api = (ICosmeticAPI)PalaShopMod.getServer().createAPI("cosmetic", ICosmeticAPI.class);
  }
  
  private void initCommand() {
    CommandRegistry.register(new Class[] { CosmeticCommand.class });
  }
  
  private void initListener() {
    addListener(new Class[] { CosmeticPlayerListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\server\CosmeticServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */