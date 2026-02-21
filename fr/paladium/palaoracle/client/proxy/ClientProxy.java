package fr.paladium.palaoracle.client.proxy;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import fr.paladium.palaoracle.core.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {
  public static ClientProxy instance;
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    instance = this;
  }
  
  public static ClientProxy getInstance() {
    if (instance == null)
      instance = new ClientProxy(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaoracle\client\proxy\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */