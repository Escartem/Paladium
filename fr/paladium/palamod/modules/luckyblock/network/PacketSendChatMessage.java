package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketSendChatMessage implements IMessage {
  public String message;
  
  public PacketSendChatMessage() {}
  
  public PacketSendChatMessage(String message) {
    this.message = message;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.message = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.message);
  }
  
  public static class Handler implements IMessageHandler<PacketSendChatMessage, IMessage> {
    public IMessage onMessage(PacketSendChatMessage message, MessageContext ctx) {
      (Minecraft.func_71410_x()).field_71439_g.func_71165_d(message.message);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketSendChatMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */