package fr.paladium.palamod.modules.packetreducer.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class Handler implements IMessageHandler<PReducerPlayersPacket, IMessage> {
  public IMessage onMessage(PReducerPlayersPacket message, MessageContext ctx) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\packetreducer\common\packets\PReducerPlayersPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */