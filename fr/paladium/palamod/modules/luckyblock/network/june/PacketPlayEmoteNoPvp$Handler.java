package fr.paladium.palamod.modules.luckyblock.network.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;

public class Handler implements IMessageHandler<PacketPlayEmoteNoPvp, IMessage> {
  public IMessage onMessage(PacketPlayEmoteNoPvp message, MessageContext ctx) {
    ClientProxy.playEmoteNoPvp(message.emoteName);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\june\PacketPlayEmoteNoPvp$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */