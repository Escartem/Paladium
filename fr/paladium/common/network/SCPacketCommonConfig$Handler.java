package fr.paladium.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.common.CommonModule;

public class Handler implements IMessageHandler<SCPacketCommonConfig, IMessage> {
  public IMessage onMessage(SCPacketCommonConfig message, MessageContext ctx) {
    CommonModule.getInstance().getConfig().setServerType(SCPacketCommonConfig.access$000(message));
    CommonModule.getInstance().getConfig().setServerName(SCPacketCommonConfig.access$100(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\network\SCPacketCommonConfig$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */