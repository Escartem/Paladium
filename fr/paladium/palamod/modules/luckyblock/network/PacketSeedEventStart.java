package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.events.ClientSeedEvent;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketSeedEventStart implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  public PacketSeedEventStart() {}
  
  public PacketSeedEventStart(int x, int y, int z) {
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
  
  public static class Handler implements IMessageHandler<PacketSeedEventStart, IMessage> {
    public IMessage onMessage(PacketSeedEventStart message, MessageContext ctx) {
      if (ctx.side == Side.CLIENT)
        ClientSeedEvent.event = new ClientSeedEvent((Minecraft.func_71410_x()).field_71439_g.field_70170_p, message.x, message.y, message.z); 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketSeedEventStart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */