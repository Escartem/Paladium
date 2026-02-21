package fr.paladium.palamod.modules.egghunt.common.helios.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.egghunt.common.helios.ModuleEggHunt;

public class Handler implements IMessageHandler<SCPacketEggHunt, IMessage> {
  public IMessage onMessage(SCPacketEggHunt message, MessageContext ctx) {
    ModuleEggHunt.getInstance().setActive(SCPacketEggHunt.access$000(message));
    ModuleEggHunt.getInstance().setCanXp(SCPacketEggHunt.access$100(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\helios\network\SCPacketEggHunt$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */