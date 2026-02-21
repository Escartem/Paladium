package fr.paladium.palatrade.lib.odin.modules;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

public abstract class AOdinModule implements IOdinModule {
  private final String modId;
  
  private final String name;
  
  private final String version;
  
  private SimpleNetworkWrapper network;
  
  private int packetDiscriminator;
  
  public AOdinModule(String modId, String name, String version) {
    this.modId = modId;
    this.name = name;
    this.version = version;
  }
  
  protected <Q extends cpw.mods.fml.common.network.simpleimpl.IMessage, R extends cpw.mods.fml.common.network.simpleimpl.IMessage> void addPacket(Class<? extends IMessageHandler<Q, R>> messageHandler, Class<Q> requestMessageType, Side side) {
    if (this.network == null)
      initNetwork(getModId() + getName()); 
    this.network.registerMessage(messageHandler, requestMessageType, this.packetDiscriminator, side);
    this.packetDiscriminator++;
  }
  
  protected void initNetwork(String channelName) {
    this.network = NetworkRegistry.INSTANCE.newSimpleChannel(channelName.toUpperCase());
  }
  
  protected int getPacketDiscriminator() {
    return this.packetDiscriminator;
  }
  
  protected void addListener(Class<?> listener) {
    try {
      Object instanciedListener = listener.newInstance();
      MinecraftForge.EVENT_BUS.register(instanciedListener);
      FMLCommonHandler.instance().bus().register(instanciedListener);
    } catch (InstantiationException|IllegalAccessException e) {
      e.printStackTrace();
    } 
  }
  
  public SimpleNetworkWrapper getNetwork() {
    return this.network;
  }
  
  public String getModId() {
    return this.modId;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getVersion() {
    return this.version;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\AOdinModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */