package fr.paladium.palamod.modules.luckyblock.network.luckypass;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;

public class Handler implements IMessageHandler<PacketSetWaitingServer, IMessage> {
  public IMessage onMessage(PacketSetWaitingServer message, MessageContext ctx) {
    ClientProxy.waitingServerResponse = PacketSetWaitingServer.access$000(message);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\luckypass\PacketSetWaitingServer$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */