package fr.paladium.palatrade.utils.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palatrade.lib.odin.modules.IOdinModule;
import fr.paladium.palatrade.lib.odin.modules.IProxy;
import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.common.MinecraftForge;

public abstract class AProxy implements IProxy {
  private List<IOdinModule> modules;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    loadModules();
    if (this.modules != null)
      this.modules.forEach(module -> module.onPreInit(event)); 
  }
  
  public void onInit(FMLInitializationEvent event) {
    if (this.modules != null)
      this.modules.forEach(module -> module.onInit(event)); 
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    if (this.modules != null)
      this.modules.forEach(module -> module.onPostInit(event)); 
  }
  
  public void onServerStarting(FMLServerStartingEvent event) {
    if (this.modules != null)
      this.modules.forEach(module -> module.onServerStarting(event)); 
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {
    if (this.modules != null)
      this.modules.forEach(module -> module.onServerStarted(event)); 
  }
  
  protected void addListener(Class<?>... listeners) {
    for (Class<?> listener : listeners) {
      try {
        Object instanciedListener = listener.newInstance();
        MinecraftForge.EVENT_BUS.register(instanciedListener);
        FMLCommonHandler.instance().bus().register(instanciedListener);
      } catch (InstantiationException|IllegalAccessException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public void loadModule(String modId, Class<? extends IOdinModule> module) {
    if (this.modules == null)
      this.modules = new ArrayList<>(); 
    try {
      IOdinModule instanciedModule = module.getConstructor(new Class[] { String.class }).newInstance(new Object[] { modId });
      this.modules.add(instanciedModule);
      System.out.println("[Odin] Loading module : " + instanciedModule.getName() + " [" + instanciedModule.getVersion() + "]");
    } catch (Exception e) {
      System.out.println("[Odin] Unable to load module : " + module.getName());
      e.printStackTrace();
    } 
  }
  
  public abstract void loadModules();
}


/* Location:              E:\Paladium\!\fr\paladium\palatrad\\utils\proxy\AProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */