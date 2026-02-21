package fr.paladium.palaspawner;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palaspawner.common.SpawnerCommonProxy;

@Mod(modid = "palaspawner", version = "1.0.0", acceptableRemoteVersions = "*")
public class SpawnerMod {
  @SidedProxy(clientSide = "fr.paladium.palaspawner.client.SpawnerClientProxy", serverSide = "fr.paladium.palaspawner.server.SpawnerServerProxy")
  public static SpawnerCommonProxy proxy;
  
  @Instance("palaspawner")
  private static SpawnerMod instance;
  
  public static SpawnerMod getInstance() {
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


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\SpawnerMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */