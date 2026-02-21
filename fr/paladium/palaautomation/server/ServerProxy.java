package fr.paladium.palaautomation.server;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaautomation.common.CommonProxy;
import fr.paladium.palaautomation.server.listener.PipePlaceListener;

public class ServerProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { PipePlaceListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */