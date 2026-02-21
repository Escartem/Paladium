package fr.paladium.palavanillagui.client;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palavanillagui.client.config.inventory.ConfigInventoryShortcut;
import fr.paladium.palavanillagui.client.listener.ClientListener;
import fr.paladium.palavanillagui.common.CommonProxy;
import java.io.File;

public class ClientProxy extends CommonProxy {
  public static ConfigInventoryShortcut configInventoryShortcut;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    try {
      configInventoryShortcut = (ConfigInventoryShortcut)JsonConfigLoader.loadConfig(new File(event.getModConfigurationDirectory(), "shortcuts.json"), ConfigInventoryShortcut.class);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    if (configInventoryShortcut == null || configInventoryShortcut.getShortcuts() == null)
      configInventoryShortcut = new ConfigInventoryShortcut(); 
    addListener(new Class[] { ClientListener.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */