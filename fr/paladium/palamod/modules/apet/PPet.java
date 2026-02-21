package fr.paladium.palamod.modules.apet;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import fr.paladium.palamod.modules.apet.common.PetCommonProxy;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;

@ObjectHolder("palamod")
@Pulse(id = "palamod-apet", description = "Paladium Pets feature", forced = true)
@DoNotRename
public class PPet {
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.apet.client.PetClientProxy", serverSide = "fr.paladium.palamod.modules.apet.server.PetServerProxy")
  public static PetCommonProxy proxy;
  
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\PPet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */