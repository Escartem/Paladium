package fr.paladium.palatrade.lib.odin.modules.packet;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palatrade.lib.odin.modules.AOdinModule;
import fr.paladium.palatrade.lib.odin.modules.packet.internal.AbstractIMessage;
import fr.paladium.palatrade.lib.odin.modules.packet.internal.AbstractIMessageHandler;
import fr.paladium.palatrade.lib.odin.modules.packet.lib.ForgePacket;
import java.util.HashMap;
import java.util.Map;

public class OdinPacketModule extends AOdinModule {
  private static OdinPacketModule instance;
  
  private Map<Integer, Class<? extends ForgePacket>> packetById;
  
  private Map<Class<? extends ForgePacket>, Integer> packetByClass;
  
  public OdinPacketModule(String modId) {
    super(modId, "packet", "1.0.0");
    instance = this;
    this.packetById = new HashMap<>();
    this.packetByClass = new HashMap<>();
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {}
  
  public void onInit(FMLInitializationEvent event) {}
  
  public void onPostInit(FMLPostInitializationEvent event) {}
  
  public void onServerStarting(FMLServerStartingEvent event) {}
  
  public void onServerStarted(FMLServerStartedEvent event) {}
  
  public void registerPacket(Class<? extends ForgePacket> packet) {
    if (getNetwork() == null)
      initNetwork(getModId()); 
    int discriminator = this.packetById.size();
    if (this.packetById.isEmpty()) {
      addPacket(AbstractIMessageHandler.class, AbstractIMessage.class, Side.CLIENT);
      addPacket(AbstractIMessageHandler.class, AbstractIMessage.class, Side.SERVER);
    } 
    this.packetById.put(Integer.valueOf(discriminator), packet);
    this.packetByClass.put(packet, Integer.valueOf(discriminator));
  }
  
  public static OdinPacketModule getInstance() {
    return instance;
  }
  
  public ForgePacket getPacket(int id) {
    try {
      return ((Class<ForgePacket>)this.packetById.get(Integer.valueOf(id))).newInstance();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public int getPacketId(Class<? extends ForgePacket> clazz) {
    return ((Integer)this.packetByClass.get(clazz)).intValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\packet\OdinPacketModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */