package fr.paladium.palamod.modules.miner.networks;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.miner.proxy.ClientProxy;

public class Handler implements IMessageHandler<SCPacketSetInMinage, IMessage> {
  public IMessage onMessage(SCPacketSetInMinage message, MessageContext ctx) {
    ClientProxy.inMinage = message.inMinage;
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\networks\SCPacketSetInMinage$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */