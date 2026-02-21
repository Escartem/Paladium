package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events.LanguageCheckEventHandler;

public class Handler implements IMessageHandler<SCTimedLanguagePacket, IMessage> {
  public IMessage onMessage(SCTimedLanguagePacket message, MessageContext ctx) {
    LanguageCheckEventHandler.INSTANCE.setLanguageExpirationMillis(System.currentTimeMillis() + SCTimedLanguagePacket.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\server\SCTimedLanguagePacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */