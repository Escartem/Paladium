package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketClientUpdateVelocity implements IMessage {
  public double x;
  
  public double y;
  
  public double z;
  
  public PacketClientUpdateVelocity() {}
  
  public PacketClientUpdateVelocity(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readDouble();
    this.y = buf.readDouble();
    this.z = buf.readDouble();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeDouble(this.x);
    buf.writeDouble(this.y);
    buf.writeDouble(this.z);
  }
  
  public static class Handler implements IMessageHandler<PacketClientUpdateVelocity, IMessage> {
    public IMessage onMessage(PacketClientUpdateVelocity message, MessageContext ctx) {
      (Minecraft.func_71410_x()).field_71439_g.func_70016_h(message.x, message.y, message.z);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientUpdateVelocity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */