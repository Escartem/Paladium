package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.ClientProxy;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Handler implements IMessageHandler<PacketClientSpellTiming, IMessage> {
  public IMessage onMessage(PacketClientSpellTiming message, MessageContext ctx) {
    ClientProxy.spellStarting.put(Spells.values()[message.spell], 
        LocalDateTime.ofEpochSecond(
          LocalDateTime.now().toEpochSecond(ZoneOffset.ofTotalSeconds(0)), 0, 
          ZoneOffset.ofTotalSeconds(0)));
    ClientProxy.spellEnding.put(Spells.values()[message.spell], 
        
        LocalDateTime.ofEpochSecond(LocalDateTime.now().toEpochSecond(ZoneOffset.ofTotalSeconds(0)), 0, 
          ZoneOffset.ofTotalSeconds(0))
        .plusSeconds(message.delay));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientSpellTiming$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */