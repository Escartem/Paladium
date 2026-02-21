package fr.paladium.palamod.modules.pillage.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class MessagePlaceBlock implements IMessage {
  int x;
  
  int y;
  
  int z;
  
  byte id;
  
  public MessagePlaceBlock() {}
  
  public MessagePlaceBlock(int x, int y, int z, byte id) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeByte(this.id);
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.id = buf.readByte();
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public static class ClientHandler implements IMessageHandler<MessagePlaceBlock, IMessage> {
    public IMessage onMessage(MessagePlaceBlock message, MessageContext ctx) {
      switch (message.id) {
        case 0:
          (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_147468_f(message.x, message.y, message.z);
          break;
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\network\packets\MessagePlaceBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */