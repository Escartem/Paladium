package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.ClientProxy;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Handler implements IMessageHandler<PacketClientFrozen, IMessage> {
  public IMessage onMessage(PacketClientFrozen message, MessageContext ctx) {
    ClientProxy.frozen = PacketClientFrozen.access$000(message);
    ClientProxy.frozenStart = LocalDateTime.ofEpochSecond(
        LocalDateTime.now().toEpochSecond(ZoneOffset.ofTotalSeconds(0)), 0, 
        ZoneOffset.ofTotalSeconds(0));
    ClientProxy.frozenEnd = LocalDateTime.ofEpochSecond(LocalDateTime.now().plusSeconds((PacketClientFrozen.access$100(message) * 2))
        .toEpochSecond(ZoneOffset.ofTotalSeconds(0)), 0, ZoneOffset.ofTotalSeconds(0));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientFrozen$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */