package fr.paladium.palamod.modules.nemesis.module.base;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palamod.modules.nemesis.module.base.network.NemesisNetwork;
import lombok.NonNull;
import net.minecraftforge.common.MinecraftForge;

public abstract class NemesisModule implements INemesisModule {
  private final String name;
  
  private final NemesisNetwork network;
  
  public String getName() {
    return this.name;
  }
  
  public NemesisNetwork getNetwork() {
    return this.network;
  }
  
  public NemesisModule(String name) {
    this.name = name;
    this.network = new NemesisNetwork(name);
  }
  
  @NonNull
  public NemesisModule addListener(@NonNull Class<?> clazz) {
    if (clazz == null)
      throw new NullPointerException("clazz is marked non-null but is null"); 
    try {
      Object listener = clazz.newInstance();
      MinecraftForge.EVENT_BUS.register(listener);
      FMLCommonHandler.instance().bus().register(listener);
    } catch (InstantiationException|IllegalAccessException e) {
      throw new RuntimeException("Failed to register listener: " + clazz.getName(), e);
    } 
    return this;
  }
  
  public void preInit(FMLPreInitializationEvent event) {}
  
  public void init(FMLInitializationEvent event) {}
  
  public void postInit(FMLPostInitializationEvent event) {}
  
  public void serverStarting(FMLServerStartingEvent event) {}
  
  public void serverStarted(FMLServerStartedEvent event) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\base\NemesisModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */