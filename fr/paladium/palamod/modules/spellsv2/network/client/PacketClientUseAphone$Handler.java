package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;

public class Handler implements IMessageHandler<PacketClientUseAphone, IMessage> {
  public IMessage onMessage(PacketClientUseAphone message, MessageContext ctx) {
    if (message.active) {
      ClientManager.addMuted(message.x, message.y, message.z, message.tier);
    } else {
      ClientManager.removeMuted(message.x, message.y, message.z, message.tier);
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientUseAphone$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */