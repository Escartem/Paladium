package fr.paladium.palaforgeutils.lib.server.internal.network;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.internal.event.ServerChangeEvent;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.MinecraftForge;

public class SCPacketServerInformations implements IMessage {
  private String hostName;
  
  private String serverType;
  
  private String serverName;
  
  public SCPacketServerInformations() {}
  
  public SCPacketServerInformations(String hostName, String serverType, String serverName) {
    this.hostName = hostName;
    this.serverType = serverType;
    this.serverName = serverName;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.hostName = PacketSerialUtils.readString(buf);
    this.serverType = PacketSerialUtils.readString(buf);
    this.serverName = PacketSerialUtils.readString(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    PacketSerialUtils.writeString(buf, (this.hostName == null) ? "" : this.hostName);
    PacketSerialUtils.writeString(buf, (this.serverType == null) ? "" : this.serverType);
    PacketSerialUtils.writeString(buf, (this.serverName == null) ? "" : this.serverName);
  }
  
  public static class Handler implements IMessageHandler<SCPacketServerInformations, IMessage> {
    public IMessage onMessage(SCPacketServerInformations message, MessageContext ctx) {
      MinecraftForge.EVENT_BUS.post((Event)new ServerChangeEvent.Pre(Side.CLIENT, Server.getServerType()));
      Server.set(message.hostName, message.serverType, message.serverName);
      MinecraftForge.EVENT_BUS.post((Event)new ServerChangeEvent.Post(Side.CLIENT, Server.getServerType()));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\server\internal\network\SCPacketServerInformations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */