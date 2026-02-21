package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;

@Packet(side = Side.SERVER)
public class CSPacketInteraction implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  private int side;
  
  private InteractionType type;
  
  public CSPacketInteraction() {}
  
  public CSPacketInteraction(int x, int y, int z, int side, InteractionType type) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.side = side;
    this.type = type;
  }
  
  public String toString() {
    return "CSPacketInteraction(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", side=" + this.side + ", type=" + this.type + ")";
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
    this.side = buf.readInt();
    this.type = InteractionType.values()[buf.readInt()];
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
    buf.writeInt(this.side);
    buf.writeInt(this.type.ordinal());
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<CSPacketInteraction, IMessage> {
    public IMessage onMessage(CSPacketInteraction message, MessageContext ctx) {
      return null;
    }
  }
  
  public enum InteractionType {
    BREAK, PLACE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\CSPacketInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */