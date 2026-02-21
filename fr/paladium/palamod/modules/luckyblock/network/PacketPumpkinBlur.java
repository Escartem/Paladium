package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import io.netty.buffer.ByteBuf;

public class PacketPumpkinBlur implements IMessage {
  public boolean state;
  
  public PacketPumpkinBlur() {}
  
  public PacketPumpkinBlur(boolean state) {
    this.state = state;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.state = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.state);
  }
  
  public static class Handler implements IMessageHandler<PacketPumpkinBlur, IMessage> {
    public IMessage onMessage(PacketPumpkinBlur message, MessageContext ctx) {
      ClientProxy.pumpkinBlur = message.state;
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketPumpkinBlur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */