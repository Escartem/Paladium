package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.ClientProxy;

public class Handler implements IMessageHandler<PacketUseInversion, IMessage> {
  public IMessage onMessage(PacketUseInversion message, MessageContext ctx) {
    ClientProxy.customMovementInput.confused = message.inversion;
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketUseInversion$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */