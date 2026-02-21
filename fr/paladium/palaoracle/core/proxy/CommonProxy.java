package fr.paladium.palaoracle.core.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class CommonProxy {
  public SimpleNetworkWrapper network;
  
  public SimpleNetworkWrapper getNetwork() {
    return this.network;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {}
  
  public void onInit(FMLInitializationEvent event) {}
  
  public void onPostInit(FMLPostInitializationEvent event) {}
  
  public void onServerStarted(FMLServerStartedEvent event) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palaoracle\core\proxy\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */