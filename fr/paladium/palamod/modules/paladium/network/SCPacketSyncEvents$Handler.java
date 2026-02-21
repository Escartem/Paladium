package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.client.events.ClientEventHandler;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;

@PacketHandler
public class Handler implements IMessageHandler<SCPacketSyncEvents, IMessage> {
  public IMessage onMessage(SCPacketSyncEvents message, MessageContext ctx) {
    ClientEventHandler.events = SCPacketSyncEvents.access$000(message);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\SCPacketSyncEvents$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */