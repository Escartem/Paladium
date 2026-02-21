package fr.paladium.palahologram.client;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palahologram.client.listener.WorldListener;
import fr.paladium.palahologram.common.CommonProxy;

public class ClientProxy extends CommonProxy {
  public static boolean TRANSLATE_LOADED = false;
  
  public static boolean PLACEHOLDER_LOADED = false;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { WorldListener.class });
    TRANSLATE_LOADED = Loader.isModLoaded("translate");
    PLACEHOLDER_LOADED = Loader.isModLoaded("palaplaceholder");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */