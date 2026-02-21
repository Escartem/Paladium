package fr.paladium.palamod.modules.nemesis;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palamod.modules.nemesis.module.base.INemesisModule;
import fr.paladium.palamod.modules.nemesis.module.base.network.NemesisNetworkListener;
import fr.paladium.palamod.modules.nemesis.module.impl.autoclick.NemesisAutoclickModule;
import fr.paladium.palamod.modules.nemesis.module.impl.dump.NemesisDumpModule;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.common.MinecraftForge;

@Pulse(id = "palamod", description = "Nemesis submodule manager", forced = true)
@DoNotRename
public class NemesisPulse {
  private static final List<INemesisModule> SUBMODULES = new ArrayList<>();
  
  static {
    register((INemesisModule)new NemesisDumpModule());
    register((INemesisModule)new NemesisAutoclickModule());
  }
  
  public static void register(INemesisModule module) {
    SUBMODULES.add(module);
  }
  
  public static INemesisModule getSubModule(String name) {
    for (INemesisModule m : SUBMODULES) {
      if (m.getName().equalsIgnoreCase(name))
        return m; 
    } 
    return null;
  }
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    for (INemesisModule m : SUBMODULES)
      m.preInit(event); 
    NemesisNetworkListener networkListener = new NemesisNetworkListener();
    MinecraftForge.EVENT_BUS.register(networkListener);
    FMLCommonHandler.instance().bus().register(networkListener);
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {
    for (INemesisModule m : SUBMODULES)
      m.init(event); 
  }
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {
    for (INemesisModule m : SUBMODULES)
      m.postInit(event); 
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    for (INemesisModule m : SUBMODULES)
      m.serverStarting(event); 
  }
  
  @Handler
  public void serverStarted(FMLServerStartedEvent event) {
    for (INemesisModule m : SUBMODULES)
      m.serverStarted(event); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\NemesisPulse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */