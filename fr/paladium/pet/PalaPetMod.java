package fr.paladium.pet;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.pet.common.PetCommonProxy;

@Mod(modid = "palapet", version = "1.0.0", acceptableRemoteVersions = "*")
public class PalaPetMod {
  @SidedProxy(clientSide = "fr.paladium.pet.client.PetClientProxy", serverSide = "fr.paladium.pet.server.PetServerProxy")
  public static PetCommonProxy proxy;
  
  @Instance("palapet")
  private static PalaPetMod instance;
  
  public static PalaPetMod getInstance() {
    return instance;
  }
  
  @EventHandler
  public void onPreInit(FMLPreInitializationEvent event) {
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
}


/* Location:              E:\Paladium\!\fr\paladium\pet\PalaPetMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */