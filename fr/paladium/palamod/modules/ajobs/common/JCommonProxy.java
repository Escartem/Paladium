package fr.paladium.palamod.modules.ajobs.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palamod.modules.ajobs.common.manager.JobsManager;

public class JCommonProxy {
  private JobsManager jobsManager = new JobsManager();
  
  public void preInit(FMLPreInitializationEvent event) {}
  
  public void init(FMLInitializationEvent event) {}
  
  public void postInit(FMLPostInitializationEvent event) {}
  
  public void serverStarting(FMLServerStartingEvent event) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\JCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */