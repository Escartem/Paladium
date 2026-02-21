package fr.paladium.palashop.client;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palashop.common.CommonProxy;
import fr.paladium.palashop.common.provider.event.ProviderDispatcher;
import fr.paladium.palashop.common.provider.event.ProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModInitProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPostInitProviderEvent;
import fr.paladium.palashop.common.provider.event.impl.mod.ModPreInitProviderEvent;

public class ClientProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ModPreInitProviderEvent.post(event) });
  }
  
  public void onInit(FMLInitializationEvent event) {
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ModInitProviderEvent.pre(event) });
    super.onInit(event);
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ModInitProviderEvent.post(event) });
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ModPostInitProviderEvent.pre(event) });
    super.onPostInit(event);
    ProviderDispatcher.dispatchGlobal(new ProviderEvent[] { (ProviderEvent)ModPostInitProviderEvent.post(event) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */