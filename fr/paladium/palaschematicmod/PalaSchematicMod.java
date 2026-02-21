package fr.paladium.palaschematicmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palaschematicmod.common.CommonProxy;
import java.io.File;

@Mod(modid = "palaschematic", version = "1.0.0", acceptableRemoteVersions = "*")
public class PalaSchematicMod {
  @Instance("palaschematic")
  private static PalaSchematicMod instance;
  
  public static File configFolder;
  
  @SidedProxy(clientSide = "fr.paladium.palaschematicmod.client.ClientProxy", serverSide = "fr.paladium.palaschematicmod.server.ServerProxy")
  public static CommonProxy proxy;
  
  @EventHandler
  public void onPreInit(FMLPreInitializationEvent event) {
    configFolder = event.getModConfigurationDirectory();
    proxy.onPreInit(event);
  }
  
  @EventHandler
  public void onInit(FMLInitializationEvent event) {
    proxy.onInit(event);
  }
  
  @EventHandler
  public void onPostInit(FMLPostInitializationEvent event) {
    proxy.onPostInit(event);
  }
  
  @EventHandler
  public void onServerStarting(FMLServerStartingEvent event) {
    proxy.onServerStarting(event);
  }
  
  @EventHandler
  public void onServerStarted(FMLServerStartedEvent event) {
    proxy.onServerStarted(event);
  }
  
  public static PalaSchematicMod getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\PalaSchematicMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */