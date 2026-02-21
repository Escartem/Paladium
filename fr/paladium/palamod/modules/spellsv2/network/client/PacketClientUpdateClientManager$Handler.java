package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;

public class Handler implements IMessageHandler<PacketClientUpdateClientManager, IMessage> {
  public IMessage onMessage(PacketClientUpdateClientManager message, MessageContext ctx) {
    if (message.points >= 0.0D)
      ClientManager.setPoints(message.points); 
    if (message.level >= 0)
      ClientManager.setLevel(message.level); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientUpdateClientManager$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */