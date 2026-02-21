package fr.paladium.palamod.modules.luckyblock.network.easter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import io.netty.buffer.ByteBuf;

public class PacketRainbowParticle implements IMessage {
  public int x;
  
  public int y;
  
  public int z;
  
  public PacketRainbowParticle() {}
  
  public PacketRainbowParticle(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  public static class Handler implements IMessageHandler<PacketRainbowParticle, IMessage> {
    public IMessage onMessage(PacketRainbowParticle message, MessageContext ctx) {
      ClientProxy.spawnRainbowParticle(message);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\easter\PacketRainbowParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */