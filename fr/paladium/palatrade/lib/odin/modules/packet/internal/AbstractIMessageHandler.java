package fr.paladium.palatrade.lib.odin.modules.packet.internal;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class AbstractIMessageHandler implements IMessageHandler<AbstractIMessage, IMessage> {
  public IMessage onMessage(AbstractIMessage message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT) {
      message.getPacket().processClient();
    } else {
      message.getPacket().processServer((ctx.getServerHandler()).field_147369_b);
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\packet\internal\AbstractIMessageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */