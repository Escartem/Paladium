package fr.paladium.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.common.CommonModule;

public class Handler implements IMessageHandler<SCPacketServerStatus, IMessage> {
  public IMessage onMessage(SCPacketServerStatus message, MessageContext ctx) {
    CommonModule.getInstance().getConfig().setServers(SCPacketServerStatus.access$000(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\network\SCPacketServerStatus$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */