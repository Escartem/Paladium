package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events.MessRenderEventHandler;

public class Handler implements IMessageHandler<SCMessPacket, IMessage> {
  public IMessage onMessage(SCMessPacket message, MessageContext ctx) {
    MessRenderEventHandler.INSTANCE.setMessExpirationMillis(System.currentTimeMillis() + SCMessPacket.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\packets\SCMessPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */