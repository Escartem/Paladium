package fr.paladium.palamod.modules.miner.networks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.miner.proxy.ClientProxy;
import io.netty.buffer.ByteBuf;

public class SCPacketSetInMinage implements IMessage {
  public boolean inMinage;
  
  public SCPacketSetInMinage() {}
  
  public SCPacketSetInMinage(boolean inMinage) {
    this.inMinage = inMinage;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.inMinage = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.inMinage);
  }
  
  public static class Handler implements IMessageHandler<SCPacketSetInMinage, IMessage> {
    public IMessage onMessage(SCPacketSetInMinage message, MessageContext ctx) {
      ClientProxy.inMinage = message.inMinage;
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\networks\SCPacketSetInMinage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */