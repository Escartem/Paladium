package fr.paladium.palamod.modules.paladynamique;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import fr.paladium.bigbrother.lib.BigBrotherAPI;
import fr.paladium.bigbrother.lib.metrics.MetricCounter;
import fr.paladium.palamod.metrics.bigbrother.BigBrotherImpl;
import fr.paladium.palamod.modules.paladynamique.listener.CraftListener;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;

@ObjectHolder("palamod")
@Pulse(id = "palamod-paladynamique", description = "Pala dynamic generation", forced = true)
public class PPalaDynamique {
  @Instance("palamod-paladynamique")
  public static PPalaDynamique instance;
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    instance = this;
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    FMLCommonHandler.instance().bus().register(new CraftListener());
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {}
  
  @Handler
  public void serverStarting(FMLServerStartingEvent e) {}
  
  public void addGenerated(String reason, double amount) {
    if (BigBrotherImpl.instance == null)
      return; 
    BigBrotherAPI api = BigBrotherImpl.instance.getApi();
    if (api == null)
      return; 
    MetricCounter counter = (new MetricCounter()).setName("paladium_generated").register(api);
    counter.newInstance(reason, amount);
  }
  
  public void addDestroyed(String reason, double amount) {
    if (BigBrotherImpl.instance == null)
      return; 
    BigBrotherAPI api = BigBrotherImpl.instance.getApi();
    if (api == null)
      return; 
    MetricCounter counter = (new MetricCounter()).setName("paladium_destroyed").register(api);
    if (counter.getRegisterer() == null || counter.getRegisterer().getService() == null || !counter.getRegisterer().getService().isConnected())
      return; 
    counter.newInstance(reason, amount);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladynamique\PPalaDynamique.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */