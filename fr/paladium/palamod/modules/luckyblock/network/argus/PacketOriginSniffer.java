package fr.paladium.palamod.modules.luckyblock.network.argus;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketOriginSniffer implements IMessage {
  private int size;
  
  private byte[] data;
  
  public PacketOriginSniffer() {}
  
  public PacketOriginSniffer(int size, byte[] data) {
    this.size = size;
    this.data = data;
  }
  
  public void fromBytes(ByteBuf buf) {
    int size = buf.readInt();
    if (size >= 83886080)
      return; 
    this.data = new byte[size];
    buf.readBytes(this.data);
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.size);
    buf.writeBytes(this.data);
  }
  
  public static class Handler implements IMessageHandler<PacketOriginSniffer, IMessage> {
    public IMessage onMessage(PacketOriginSniffer message, MessageContext ctx) {
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\argus\PacketOriginSniffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */