package fr.paladium.palavanillagui.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palavanillagui.common.CommonProxy;
import fr.paladium.palavanillagui.server.config.inventory.ShortcutConfig;
import fr.paladium.palavanillagui.server.listener.PVGuiServerAnvilActivatedListener;

public class ServerProxy extends CommonProxy {
  public static ShortcutConfig shortcutConfig;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { PVGuiServerAnvilActivatedListener.class });
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    shortcutConfig = (ShortcutConfig)ConfigurationManager.getInstance().register(ShortcutConfig.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */