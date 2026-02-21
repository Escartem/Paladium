package fr.paladium.server;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.common.CommonModule;

public class CommonServerProxy extends CommonModule {
  private static CommonServerProxy instance;
  
  public static CommonServerProxy getInstance() {
    return instance;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    instance = this;
    super.onPreInit(event);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
  }
  
  public void onServerStarting(FMLServerStartingEvent event) {
    super.onServerStarting(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\server\CommonServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */