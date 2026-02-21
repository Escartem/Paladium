package fr.paladium.permissionbridge.server;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import fr.paladium.permissionbridge.common.CommonProxy;
import fr.paladium.permissionbridge.server.listener.PermissionBridgeServerListener;
import fr.paladium.permissionbridge.server.task.PermissionBridgeSyncTask;

public class ServerProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { PermissionBridgeServerListener.class });
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {
    super.onServerStarted(event);
    PermissionBridgeSyncTask.create();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */