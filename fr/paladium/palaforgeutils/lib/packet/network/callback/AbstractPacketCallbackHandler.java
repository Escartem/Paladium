package fr.paladium.palaforgeutils.lib.packet.network.callback;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import java.util.function.Consumer;

public class AbstractPacketCallbackHandler implements IMessageHandler<AbstractPacketCallback, IMessage> {
  public IMessage onMessage(AbstractPacketCallback message, MessageContext ctx) {
    if (message.getCallbackUUID() == null)
      return null; 
    Consumer<Object> callback = ForgePacket.pollCallback(message.getCallbackUUID());
    if (callback != null)
      callback.accept(message.getData()); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\network\callback\AbstractPacketCallbackHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */