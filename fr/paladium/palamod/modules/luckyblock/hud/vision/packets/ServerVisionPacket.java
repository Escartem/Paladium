package fr.paladium.palamod.modules.luckyblock.hud.vision.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class ServerVisionPacket implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<ServerVisionPacket, IMessage> {
    public IMessage onMessage(ServerVisionPacket message, MessageContext ctx) {
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\hud\vision\packets\ServerVisionPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */