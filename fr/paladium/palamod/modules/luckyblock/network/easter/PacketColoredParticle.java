package fr.paladium.palamod.modules.luckyblock.network.easter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import io.netty.buffer.ByteBuf;

public class PacketColoredParticle implements IMessage {
  public double x;
  
  public double y;
  
  public double z;
  
  public float r;
  
  public float g;
  
  public float b;
  
  public float scale;
  
  public PacketColoredParticle() {}
  
  public PacketColoredParticle(double x, double y, double z, float r, float g, float b, float scale) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.r = r;
    this.g = g;
    this.b = b;
    this.scale = scale;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readDouble();
    this.y = buf.readDouble();
    this.z = buf.readDouble();
    this.r = buf.readFloat();
    this.g = buf.readFloat();
    this.b = buf.readFloat();
    this.scale = buf.readFloat();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeDouble(this.x);
    buf.writeDouble(this.y);
    buf.writeDouble(this.z);
    buf.writeFloat(this.r);
    buf.writeFloat(this.g);
    buf.writeFloat(this.b);
    buf.writeFloat(this.scale);
  }
  
  public static class Handler implements IMessageHandler<PacketColoredParticle, IMessage> {
    public IMessage onMessage(PacketColoredParticle message, MessageContext ctx) {
      ClientProxy.spawnColoredParticle(message);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\easter\PacketColoredParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */