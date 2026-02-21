package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events.HydrationRenderEventHandler;

public class Handler implements IMessageHandler<SCHydrationPacket, IMessage> {
  public IMessage onMessage(SCHydrationPacket message, MessageContext ctx) {
    HydrationRenderEventHandler.INSTANCE.setData(SCHydrationPacket.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\packets\SCHydrationPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */