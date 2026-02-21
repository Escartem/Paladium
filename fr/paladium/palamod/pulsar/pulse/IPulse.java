package fr.paladium.palamod.pulsar.pulse;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Deprecated
public interface IPulse {
  void preInit(FMLPreInitializationEvent paramFMLPreInitializationEvent);
  
  void init(FMLInitializationEvent paramFMLInitializationEvent);
  
  void postInit(FMLPostInitializationEvent paramFMLPostInitializationEvent);
  
  void serverStarting(FMLServerStartingEvent paramFMLServerStartingEvent);
  
  void serverStarted(FMLServerStartedEvent paramFMLServerStartedEvent);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\pulsar\pulse\IPulse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */