package fr.paladium.palatrade.lib.odin.modules;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public interface IProxy {
  void onPreInit(FMLPreInitializationEvent paramFMLPreInitializationEvent);
  
  void onInit(FMLInitializationEvent paramFMLInitializationEvent);
  
  void onPostInit(FMLPostInitializationEvent paramFMLPostInitializationEvent);
  
  void onServerStarting(FMLServerStartingEvent paramFMLServerStartingEvent);
  
  void onServerStarted(FMLServerStartedEvent paramFMLServerStartedEvent);
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\IProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */