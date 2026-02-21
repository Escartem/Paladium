package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events.DarkRenderEventHandler;

public class Handler implements IMessageHandler<SCDarkScreenPacket, IMessage> {
  public IMessage onMessage(SCDarkScreenPacket message, MessageContext ctx) {
    DarkRenderEventHandler.INSTANCE.setDarkExpirationMillis(System.currentTimeMillis() + SCDarkScreenPacket.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\server\SCDarkScreenPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */