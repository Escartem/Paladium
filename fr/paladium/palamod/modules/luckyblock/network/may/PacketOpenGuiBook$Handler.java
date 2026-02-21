package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;

public class Handler implements IMessageHandler<PacketOpenGuiBook, IMessage> {
  public IMessage onMessage(PacketOpenGuiBook message, MessageContext ctx) {
    ClientProxy.openBook(PacketOpenGuiBook.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\PacketOpenGuiBook$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */