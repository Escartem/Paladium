package fr.paladium.palamod.modules.packetreducer.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class Handler implements IMessageHandler<PReducerSettingsPacket, IMessage> {
  public IMessage onMessage(PReducerSettingsPacket message, MessageContext ctx) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\packetreducer\common\packets\PReducerSettingsPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */