package fr.paladium.palamod.modules.luckyblock.network.easter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;

public class Handler implements IMessageHandler<PacketColoredParticle, IMessage> {
  public IMessage onMessage(PacketColoredParticle message, MessageContext ctx) {
    ClientProxy.spawnColoredParticle(message);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\easter\PacketColoredParticle$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */