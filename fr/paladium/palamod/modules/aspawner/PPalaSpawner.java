package fr.paladium.palamod.modules.aspawner;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import fr.paladium.palamod.modules.aspawner.common.PPalaSpawnerCommonProxy;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;

@ObjectHolder("palamod")
@Pulse(id = "palamod-palaspawner", description = "Paladium spawner feature", forced = true)
@DoNotRename
public class PPalaSpawner {
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.aspawner.client.PPalaSpawnerClientProxy", serverSide = "fr.paladium.palamod.modules.aspawner.server.PPalaSpawnerServerProxy")
  public static PPalaSpawnerCommonProxy proxy;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    proxy.onPreInit(event);
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    proxy.onInit(event);
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {
    try {
      proxy.onPostInit(event);
    } catch (Throwable $ex) {
      throw $ex;
    } 
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    proxy.onServerStarting(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\aspawner\PPalaSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */