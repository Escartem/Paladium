package fr.paladium.palamod.modules.hunter.networks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.hunter.proxies.ClientProxy;

public class Handler implements IMessageHandler<PacketRituel, IMessage> {
  public IMessage onMessage(PacketRituel message, MessageContext ctx) {
    ClientProxy.rituel = message.count;
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\networks\PacketRituel$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */