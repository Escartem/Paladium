package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import io.netty.buffer.ByteBuf;

public class SCGoldenCagePacket implements IMessage {
  public SCGoldenCagePacket() {}
  
  public SCGoldenCagePacket(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public static DoubleLocation LOCATION = new DoubleLocation(0.0D, 0.0D, 0.0D);
  
  private int x;
  
  private int y;
  
  private int z;
  
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
  
  public static class Handler implements IMessageHandler<SCGoldenCagePacket, IMessage> {
    public IMessage onMessage(SCGoldenCagePacket message, MessageContext ctx) {
      if (ctx.side != Side.CLIENT)
        return null; 
      SCGoldenCagePacket.LOCATION = new DoubleLocation(message.x, message.y, message.z);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\SCGoldenCagePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */