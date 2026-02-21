package fr.paladium.palamod.modules.paladium.utils;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.PalaMod;

public class PacketAnnotationProcessor {
  public static int packetID = 0;
  
  public static void registerPacket(Class clazz) {
    if (clazz.isAnnotationPresent((Class)Packet.class)) {
      Class<?> c2 = null;
      for (Class<?> c : clazz.getClasses()) {
        if (c.isAnnotationPresent((Class)PacketHandler.class)) {
          c2 = c;
          break;
        } 
      } 
      if (c2 != null) {
        Packet packet = (Packet)clazz.getAnnotation(Packet.class);
        PalaMod.getNetHandler().registerMessage(c2, clazz, packetID, packet.side());
        packetID++;
      } 
    } 
  }
  
  public static void registerPacket(Class handler, Class packet, Side side) {
    PalaMod.getNetHandler().registerMessage(handler, packet, packetID, side);
    packetID++;
  }
  
  public static void registerPacket(Class handler, Class packet, Side side, int packedID) {
    PalaMod.getNetHandler().registerMessage(handler, packet, packedID, side);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\PacketAnnotationProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */