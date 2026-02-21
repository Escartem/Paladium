package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

@Packet(side = Side.CLIENT)
public class SCPacketInteraction implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  private int side;
  
  private CSPacketInteraction.InteractionType type;
  
  private boolean success;
  
  public SCPacketInteraction() {}
  
  public SCPacketInteraction(int x, int y, int z, int side, CSPacketInteraction.InteractionType type, boolean success) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.side = side;
    this.type = type;
    this.success = success;
  }
  
  public String toString() {
    return "SCPacketInteraction(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", side=" + this.side + ", type=" + this.type + ", success=" + this.success + ")";
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
    this.side = buf.readInt();
    this.type = CSPacketInteraction.InteractionType.values()[buf.readInt()];
    this.success = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
    buf.writeInt(this.side);
    buf.writeInt(this.type.ordinal());
    buf.writeBoolean(this.success);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCPacketInteraction, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketInteraction message, MessageContext ctx) {
      if (message.success)
        return null; 
      switch (message.type) {
        case BREAK:
          (Minecraft.func_71410_x()).field_71442_b.func_78767_c();
          break;
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\SCPacketInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */