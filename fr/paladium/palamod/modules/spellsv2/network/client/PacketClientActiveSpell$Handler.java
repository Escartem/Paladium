package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;

public class Handler implements IMessageHandler<PacketClientActiveSpell, IMessage> {
  public IMessage onMessage(PacketClientActiveSpell message, MessageContext ctx) {
    if (message.active) {
      ClientManager.addActiveSpell(message.spell, message.uuid);
    } else {
      ClientManager.removeActiveSpell(message.spell, message.uuid);
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientActiveSpell$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */