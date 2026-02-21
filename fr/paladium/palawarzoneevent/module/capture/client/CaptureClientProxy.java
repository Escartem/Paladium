package fr.paladium.palawarzoneevent.module.capture.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palawarzoneevent.module.capture.client.listener.ClientEventListener;
import fr.paladium.palawarzoneevent.module.capture.common.CaptureCommonProxy;

public class CaptureClientProxy extends CaptureCommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { ClientEventListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\client\CaptureClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */