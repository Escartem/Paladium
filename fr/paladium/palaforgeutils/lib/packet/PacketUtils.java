package fr.paladium.palaforgeutils.lib.packet;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.packet.network.AbstractIMessage;
import fr.paladium.palaforgeutils.lib.packet.network.AbstractIMessageHandler;
import fr.paladium.palaforgeutils.lib.packet.network.callback.AbstractPacketCallback;
import fr.paladium.palaforgeutils.lib.packet.network.callback.AbstractPacketCallbackHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacketUtils {
  private static List<SimpleNetworkWrapper> cachedNetworks = new ArrayList<>();
  
  private static Map<String, Class<? extends ForgePacket>> packetByName = new HashMap<>();
  
  private static Map<Class<? extends ForgePacket>, String> packetByClass = new HashMap<>();
  
  private static Map<String, SimpleNetworkWrapper> networkByName = new HashMap<>();
  
  public static SimpleNetworkWrapper createNetwork(String name) {
    return NetworkRegistry.INSTANCE.newSimpleChannel(name.toUpperCase());
  }
  
  public static void registerPacket(SimpleNetworkWrapper network, Class<? extends ForgePacket> packet) {
    if (!cachedNetworks.contains(network)) {
      addPacket(network, (Class)AbstractIMessageHandler.class, AbstractIMessage.class, Side.CLIENT, 0);
      addPacket(network, (Class)AbstractIMessageHandler.class, AbstractIMessage.class, Side.SERVER, 0);
      addPacket(network, (Class)AbstractPacketCallbackHandler.class, AbstractPacketCallback.class, Side.CLIENT, 1);
      addPacket(network, (Class)AbstractPacketCallbackHandler.class, AbstractPacketCallback.class, Side.SERVER, 1);
      cachedNetworks.add(network);
    } 
    String name = packet.getName();
    packetByName.put(name, packet);
    packetByClass.put(packet, name);
    networkByName.put(name, network);
  }
  
  private static <Q extends cpw.mods.fml.common.network.simpleimpl.IMessage, R extends cpw.mods.fml.common.network.simpleimpl.IMessage> void addPacket(SimpleNetworkWrapper network, Class<? extends IMessageHandler<Q, R>> messageHandler, Class<Q> requestMessageType, Side side, int discriminator) {
    network.registerMessage(messageHandler, requestMessageType, discriminator, side);
  }
  
  public static ForgePacket getPacket(String name) {
    try {
      Class<? extends ForgePacket> clazz = packetByName.get(name);
      if (clazz == null) {
        System.err.println("[PalaForgeUtils][Network] Packet not found by name '" + name + "'");
        return null;
      } 
      ForgePacket packet = clazz.newInstance();
      packet.setNetwork(networkByName.get(name));
      return packet;
    } catch (Exception e) {
      System.err.println("[PalaForgeUtils][Network] Error while creating packet by name '" + name + "'");
      e.printStackTrace();
      return null;
    } 
  }
  
  public static SimpleNetworkWrapper getNetwork(String name) {
    return networkByName.get(name);
  }
  
  public static String getPacketName(Class<? extends ForgePacket> clazz) {
    return packetByClass.get(clazz);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\PacketUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */