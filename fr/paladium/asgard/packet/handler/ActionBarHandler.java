package fr.paladium.asgard.packet.handler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.asgard.AsgardClient;
import fr.paladium.asgard.packet.PacketChatWithType;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S02PacketChat;

public class ActionBarHandler implements IMessageHandler<PacketChatWithType, IMessage> {
  @SideOnly(Side.CLIENT)
  public static void handleChat(INetHandlerPlayClient pClient, PacketChatWithType pPacket) {
    if (pPacket.getType() != 2) {
      pClient.func_147251_a(new S02PacketChat(pPacket.getChat()));
    } else {
      AsgardClient.getInstance().setRecordPlaying(pPacket.getChat(), false);
    } 
  }
  
  public IMessage onMessage(PacketChatWithType message, MessageContext ctx) {
    handleChat((INetHandlerPlayClient)ctx.getClientHandler(), message);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\packet\handler\ActionBarHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */