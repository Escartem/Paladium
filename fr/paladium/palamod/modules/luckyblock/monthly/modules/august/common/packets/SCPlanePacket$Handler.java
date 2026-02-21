package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events.MessRenderEventHandler;

public class Handler implements IMessageHandler<SCPlanePacket, IMessage> {
  public IMessage onMessage(SCPlanePacket message, MessageContext ctx) {
    MessRenderEventHandler.INSTANCE.setMessExpirationMillis(System.currentTimeMillis() + SCPlanePacket.access$000());
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\packets\SCPlanePacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */