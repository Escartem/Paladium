package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;

public class Handler implements IMessageHandler<PacketSendChatMessage, IMessage> {
  public IMessage onMessage(PacketSendChatMessage message, MessageContext ctx) {
    (Minecraft.func_71410_x()).field_71439_g.func_71165_d(message.message);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketSendChatMessage$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */