package fr.paladium.palamod.modules.luckyblock.network.luckypass;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import io.netty.buffer.ByteBuf;

public class PacketSetWaitingServer implements IMessage {
  private boolean waiting;
  
  public PacketSetWaitingServer() {}
  
  public PacketSetWaitingServer(boolean waiting) {
    this.waiting = waiting;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.waiting = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.waiting);
  }
  
  public static class Handler implements IMessageHandler<PacketSetWaitingServer, IMessage> {
    public IMessage onMessage(PacketSetWaitingServer message, MessageContext ctx) {
      ClientProxy.waitingServerResponse = message.waiting;
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\luckypass\PacketSetWaitingServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */