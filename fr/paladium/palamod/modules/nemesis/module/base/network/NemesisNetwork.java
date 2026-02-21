package fr.paladium.palamod.modules.nemesis.module.base.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;

public class NemesisNetwork {
  private static final Map<String, NemesisNetwork> NETWORK_MAP = new HashMap<>();
  
  private final String networkName;
  
  private final Map<Integer, Class<? extends NemesisPacket>> packetById;
  
  private final Map<Class<? extends NemesisPacket>, Integer> idByPacket;
  
  private int packetIdCounter;
  
  public NemesisNetwork(@NonNull String networkName) {
    if (networkName == null)
      throw new NullPointerException("networkName is marked non-null but is null"); 
    this.networkName = networkName;
    this.packetById = new HashMap<>();
    this.idByPacket = new HashMap<>();
    this.packetIdCounter = 0;
    NETWORK_MAP.put(networkName, this);
  }
  
  @NonNull
  public static Optional<NemesisNetwork> getNetwork(@NonNull String networkName) {
    if (networkName == null)
      throw new NullPointerException("networkName is marked non-null but is null"); 
    return Optional.ofNullable(NETWORK_MAP.get(networkName));
  }
  
  @NonNull
  public NemesisNetwork registerPacket(@NonNull Class<? extends NemesisPacket> packet) {
    if (packet == null)
      throw new NullPointerException("packet is marked non-null but is null"); 
    if (this.idByPacket.containsKey(packet))
      throw new IllegalArgumentException("Packet " + packet.getName() + " is already registered in network " + this.networkName); 
    this.packetById.put(Integer.valueOf(this.packetIdCounter), packet);
    this.idByPacket.put(packet, Integer.valueOf(this.packetIdCounter));
    this.packetIdCounter++;
    return this;
  }
  
  @NonNull
  public Optional<NemesisPacket> getPacket(int packetId) {
    Class<? extends NemesisPacket> packetClass = this.packetById.get(Integer.valueOf(packetId));
    if (packetClass == null)
      return Optional.empty(); 
    try {
      return Optional.of(packetClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
    } catch (Exception e) {
      e.printStackTrace();
      return Optional.empty();
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @NonNull
  public NemesisNetwork send(@NonNull NemesisPacket packet) {
    if (packet == null)
      throw new NullPointerException("packet is marked non-null but is null"); 
    Integer packetId = this.idByPacket.get(packet.getClass());
    if (packetId == null)
      throw new IllegalArgumentException("Packet " + packet.getClass().getName() + " is not registered in network " + this.networkName); 
    ByteBuf buffer = Unpooled.buffer();
    buffer.writeByte(packetId.intValue());
    packet.write(buffer);
    C17PacketCustomPayload customPayloadPacket = new C17PacketCustomPayload("Nemesis|" + this.networkName, buffer.copy());
    Minecraft.func_71410_x().func_147114_u().func_147297_a((Packet)customPayloadPacket);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\base\network\NemesisNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */