package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.ClientProxy;

public class Handler implements IMessageHandler<PacketClientFreeze, IMessage> {
  public IMessage onMessage(PacketClientFreeze message, MessageContext ctx) {
    if (ClientProxy.customMovementInput != null)
      ClientProxy.customMovementInput.freeze = PacketClientFreeze.access$000(message); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientFreeze$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */