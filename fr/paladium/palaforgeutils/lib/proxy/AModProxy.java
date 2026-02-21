package fr.paladium.palaforgeutils.lib.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.common.MinecraftForge;

public abstract class AModProxy implements IModProxy {
  private SimpleNetworkWrapper network;
  
  private int packetDiscriminator;
  
  public void onPreInit(FMLPreInitializationEvent event) {}
  
  public void onInit(FMLInitializationEvent event) {}
  
  public void onPostInit(FMLPostInitializationEvent event) {}
  
  public void onServerStarting(FMLServerStartingEvent event) {}
  
  public void onServerStarted(FMLServerStartedEvent event) {}
  
  public void initNetwork(String channelName) {
    this.network = NetworkRegistry.INSTANCE.newSimpleChannel(channelName.toUpperCase());
  }
  
  public SimpleNetworkWrapper getNetwork() {
    return this.network;
  }
  
  public int getPacketDiscriminator() {
    return this.packetDiscriminator;
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
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\proxy\AModProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */