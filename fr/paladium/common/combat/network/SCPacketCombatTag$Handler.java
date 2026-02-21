package fr.paladium.common.combat.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.common.CommonModule;

public class Handler implements IMessageHandler<SCPacketCombatTag, IMessage> {
  public IMessage onMessage(SCPacketCombatTag message, MessageContext ctx) {
    CommonModule.getInstance().getCombatTag().setTimeOffset(System.currentTimeMillis() - SCPacketCombatTag.access$000(message));
    CommonModule.getInstance().getCombatTag().setEnd(SCPacketCombatTag.access$100(message));
    CommonModule.getInstance().getCombatTag().setOpponents(SCPacketCombatTag.access$200(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\combat\network\SCPacketCombatTag$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */