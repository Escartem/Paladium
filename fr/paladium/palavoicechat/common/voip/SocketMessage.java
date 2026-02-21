package fr.paladium.palavoicechat.common.voip;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.client.voip.audio.AudioChannel;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.palavoicechat.common.network.BBAuthenticatePacket;
import fr.paladium.palavoicechat.common.voip.packet.Packet;
import fr.paladium.palavoicechat.common.voip.packet.impl.AuthenticateAckPacket;
import fr.paladium.palavoicechat.common.voip.packet.impl.AuthenticatePacket;
import fr.paladium.palavoicechat.common.voip.packet.impl.KeepAlivePacket;
import fr.paladium.palavoicechat.common.voip.packet.impl.MicrophonePacket;
import fr.paladium.palavoicechat.common.voip.packet.impl.PlayerConnectionPacket;
import fr.paladium.palavoicechat.common.voip.packet.impl.PlayerDisconnectionPacket;
import fr.paladium.palavoicechat.common.voip.packet.impl.SendSecretPacket;
import fr.paladium.palavoicechat.common.voip.packet.impl.SoundPacket;
import fr.paladium.palavoicechat.common.voip.packet.impl.UpdatePositionPacket;
import fr.paladium.palavoicechat.server.ServerProxy;
import fr.paladium.palavoicechat.server.manager.KeepAliveManager;
import fr.paladium.palavoicechat.utils.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;

public class SocketMessage {
  private final Packet<?> packet;
  
  private UUID playerUUID;
  
  private UUID secret;
  
  private InetAddress clientAddress;
  
  private int clientPort;
  
  public Packet<?> getPacket() {
    return this.packet;
  }
  
  public UUID getPlayerUUID() {
    return this.playerUUID;
  }
  
  public UUID getSecret() {
    return this.secret;
  }
  
  public InetAddress getClientAddress() {
    return this.clientAddress;
  }
  
  public int getClientPort() {
    return this.clientPort;
  }
  
  private static final Map<Byte, Class<? extends Packet<?>>> packetRegistry = new HashMap<>();
  
  static {
    packetRegistry.put(Byte.valueOf((byte)0), AuthenticatePacket.class);
    packetRegistry.put(Byte.valueOf((byte)1), AuthenticateAckPacket.class);
    packetRegistry.put(Byte.valueOf((byte)2), MicrophonePacket.class);
    packetRegistry.put(Byte.valueOf((byte)3), SoundPacket.class);
    packetRegistry.put(Byte.valueOf((byte)4), KeepAlivePacket.class);
    packetRegistry.put(Byte.valueOf((byte)5), UpdatePositionPacket.class);
    packetRegistry.put(Byte.valueOf((byte)6), SendSecretPacket.class);
    packetRegistry.put(Byte.valueOf((byte)7), PlayerConnectionPacket.class);
    packetRegistry.put(Byte.valueOf((byte)8), PlayerDisconnectionPacket.class);
  }
  
  public SocketMessage(Packet<?> packet, UUID playerUUID, UUID secret, InetAddress clientAddress, int clientPort) {
    this.packet = packet;
    this.playerUUID = playerUUID;
    this.secret = secret;
    this.clientAddress = clientAddress;
    this.clientPort = clientPort;
  }
  
  public SocketMessage(Packet<?> packet) {
    this.packet = packet;
  }
  
  public SocketMessage(Packet<?> packet, UUID secret) {
    this(packet);
    this.secret = secret;
  }
  
  public static SocketMessage readPacket(ChannelHandlerContext ctx, DatagramPacket packet, ByteBuf buf) throws Exception {
    byte packetType = buf.readByte();
    Class<? extends Packet<?>> packetClass = packetRegistry.get(Byte.valueOf(packetType));
    if (packetClass == null)
      throw new InstantiationException("Could not find packet with ID " + packetType); 
    Packet<? extends Packet<?>> p = (Packet<? extends Packet<?>>)packetClass.newInstance();
    p = p.fromBytes(buf);
    return new SocketMessage(p);
  }
  
  @SideOnly(Side.CLIENT)
  public void handle() throws InterruptedException {
    IoNettyVoIPClient client = IoNettyVoIPClient.getInstance();
    if (client == null)
      return; 
    if (getPacket() instanceof AuthenticateAckPacket && 
      !client.isAuthenticated()) {
      client.setAuthenticated(true);
      client.setAuthenticating(false);
      client.startMicThread();
      (new Notification(Notification.NotificationType.SUCCESS, "Connexion au voice chat réussi", "VOICE")).send();
    } 
    if (getPacket() instanceof SoundPacket) {
      if (!PalaVoiceChatMod.getClientProxy().getClientConfig().isVoiceChatEnabled())
        return; 
      SoundPacket packet = (SoundPacket)getPacket();
      AudioChannel sendTo = (AudioChannel)client.getAudioChannels().get(packet.getSender());
      if (sendTo == null) {
        AudioChannel ch = new AudioChannel(packet.getSender());
        ch.addToQueue(packet);
        ch.start();
        client.getAudioChannels().put(packet.getSender(), ch);
      } else {
        sendTo.addToQueue(packet);
      } 
      client.getAudioChannels().values().stream().filter(AudioChannel::canKill).forEach(AudioChannel::closeAndKill);
      client.getAudioChannels().entrySet().removeIf(entry -> ((AudioChannel)entry.getValue()).isClosed());
    } 
  }
  
  @SideOnly(Side.SERVER)
  public void handleServer() throws InterruptedException {
    if (getPacket() instanceof SendSecretPacket) {
      SendSecretPacket secretPacket = (SendSecretPacket)getPacket();
      EntityPlayerMP player = PlayerUtils.getPlayer(secretPacket.getPlayerUUID());
      if (player == null)
        return; 
      (new BBAuthenticatePacket(ServerProxy.serverConfig.getVoipServerIp(), ServerProxy.serverConfig.getVoipServerPort(), secretPacket.getSecret())).send(player);
    } 
    if (getPacket() instanceof KeepAlivePacket)
      KeepAliveManager.getInstance().onKeepAliveResponse(); 
  }
  
  private static byte getPacketType(Packet<? extends Packet<?>> packet) {
    for (Map.Entry<Byte, Class<? extends Packet<?>>> entry : packetRegistry.entrySet()) {
      if (packet.getClass().equals(entry.getValue()))
        return ((Byte)entry.getKey()).byteValue(); 
    } 
    return -1;
  }
  
  @SideOnly(Side.CLIENT)
  public static ByteBuf write(Packet<?> packet, UUID secret) {
    ByteBuf buffer = Unpooled.buffer();
    byte type = getPacketType((Packet)packet);
    buffer.resetWriterIndex();
    if (type < 0)
      throw new IllegalArgumentException("Packet type not found"); 
    buffer.writeByte(type);
    ByteBufUtils.writeUUID(buffer, (Minecraft.func_71410_x()).field_71439_g.func_110124_au());
    buffer.writeBoolean((secret != null));
    if (secret != null)
      ByteBufUtils.writeUUID(buffer, secret); 
    packet.toBytes(buffer);
    return buffer;
  }
  
  @SideOnly(Side.SERVER)
  public static ByteBuf writeServer(Packet<?> packet) {
    ByteBuf buffer = Unpooled.buffer();
    buffer.resetWriterIndex();
    byte type = getPacketType((Packet)packet);
    if (type < 0)
      throw new IllegalArgumentException("Packet type not found"); 
    buffer.writeByte(type);
    ByteBufUtils.writeUUID(buffer, UUIDUtils.from(ServerProxy.serverConfig.getSecret()));
    packet.toBytes(buffer);
    return buffer;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\voip\SocketMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */