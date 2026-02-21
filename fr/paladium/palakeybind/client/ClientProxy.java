package fr.paladium.palakeybind.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palakeybind.common.CommonProxy;
import fr.paladium.palakeybind.common.key.KeyTransformer;

public class ClientProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    KeyTransformer.init();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */