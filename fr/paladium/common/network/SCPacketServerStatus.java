package fr.paladium.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.common.CommonModule;
import fr.paladium.common.utils.ServerState;
import fr.paladium.common.utils.ServerStatus;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;

public class SCPacketServerStatus implements IMessage {
  private Map<String, ServerStatus> servers;
  
  public SCPacketServerStatus() {}
  
  public SCPacketServerStatus(Map<String, ServerStatus> servers) {
    this.servers = servers;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.servers = new HashMap<>();
    int size = buf.readInt();
    for (int i = 0; i < size; i++) {
      String server = ByteBufUtils.readUTF8String(buf);
      ServerStatus status = new ServerStatus(ServerState.values()[buf.readInt()], ByteBufUtils.readUTF8String(buf), buf.readInt(), buf.readInt());
      this.servers.put(server, status);
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.servers.size());
    for (Map.Entry<String, ServerStatus> entry : this.servers.entrySet()) {
      ByteBufUtils.writeUTF8String(buf, entry.getKey());
      ServerStatus status = entry.getValue();
      buf.writeInt(status.getState().ordinal());
      ByteBufUtils.writeUTF8String(buf, (status.getRestartReason() == null) ? "" : status.getRestartReason());
      buf.writeInt(status.getOnlinePlayers());
      buf.writeInt(status.getMaxPlayers());
    } 
  }
  
  public static class Handler implements IMessageHandler<SCPacketServerStatus, IMessage> {
    public IMessage onMessage(SCPacketServerStatus message, MessageContext ctx) {
      CommonModule.getInstance().getConfig().setServers(message.servers);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\network\SCPacketServerStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */