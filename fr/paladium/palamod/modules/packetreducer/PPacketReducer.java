package fr.paladium.palamod.modules.packetreducer;

import com.allatori.annotations.DoNotRename;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.packetreducer.common.PPacketReducerProxyCommon;
import fr.paladium.palamod.modules.packetreducer.common.events.ClientEventsManager;
import fr.paladium.palamod.modules.packetreducer.common.packets.PReducerPlayersPacket;
import fr.paladium.palamod.modules.packetreducer.common.packets.PReducerSettingsPacket;
import fr.paladium.palamod.modules.packetreducer.managers.PacketReducerManager;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import net.minecraftforge.common.MinecraftForge;

@ObjectHolder("palamod")
@Pulse(id = "palamod-packet_redu", description = "Paladium Packet Reducer.", forced = true)
@DoNotRename
public class PPacketReducer {
  @Instance("palamod-packet_redu")
  public static PPacketReducer instance;
  
  @SidedProxy(clientSide = "fr.paladium.palamod.modules.packetreducer.client.PPacketReducerProxyClient", serverSide = "fr.paladium.palamod.modules.packetreducer.common.PPacketReducerProxyCommon")
  public static PPacketReducerProxyCommon proxy;
  
  public static SimpleNetworkWrapper networkWrapper;
  
  private PacketReducerManager packetReducerManager;
  
  public PacketReducerManager getPacketReducerManager() {
    return this.packetReducerManager;
  }
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) {
    System.out.println("Starting PacketReducer Preinit...");
    proxy.preInit();
    if (event.getSide() == Side.CLIENT) {
      ClientEventsManager clientEventsManager = new ClientEventsManager();
      FMLCommonHandler.instance().bus().register(clientEventsManager);
      MinecraftForge.EVENT_BUS.register(clientEventsManager);
    } 
    networkWrapper = new SimpleNetworkWrapper("palamod-preducer");
    networkWrapper.registerMessage(PReducerPlayersPacket.Handler.class, PReducerPlayersPacket.class, 0, Side.SERVER);
    networkWrapper.registerMessage(PReducerSettingsPacket.Handler.class, PReducerSettingsPacket.class, 1, Side.SERVER);
    System.out.println("PacketReducer Preinit done...");
  }
  
  @Handler
  public void init(FMLInitializationEvent event) {}
  
  @Handler
  public void postInit(FMLPostInitializationEvent event) {}
  
  @Handler
  public void serverStarting(FMLServerStartingEvent event) {
    proxy.preInit();
    try {
      this.packetReducerManager = new PacketReducerManager();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\packetreducer\PPacketReducer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */