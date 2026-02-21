package fr.paladium.permissionbridge.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.permissionbridge.client.listener.PermissionBridgeClientListener;
import fr.paladium.permissionbridge.common.CommonProxy;

public class ClientProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { PermissionBridgeClientListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */