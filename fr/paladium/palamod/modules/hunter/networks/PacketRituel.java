package fr.paladium.palamod.modules.hunter.networks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.hunter.proxies.ClientProxy;
import io.netty.buffer.ByteBuf;

public class PacketRituel implements IMessage {
  public int count;
  
  public PacketRituel() {}
  
  public PacketRituel(int count) {
    this.count = count;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.count = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.count);
  }
  
  public static class Handler implements IMessageHandler<PacketRituel, IMessage> {
    public IMessage onMessage(PacketRituel message, MessageContext ctx) {
      ClientProxy.rituel = message.count;
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\networks\PacketRituel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */