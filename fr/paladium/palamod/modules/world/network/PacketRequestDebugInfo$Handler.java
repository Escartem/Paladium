package fr.paladium.palamod.modules.world.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class Handler implements IMessageHandler<PacketRequestDebugInfo, PacketDebugInfo> {
  public PacketDebugInfo onMessage(PacketRequestDebugInfo message, MessageContext ctx) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\network\PacketRequestDebugInfo$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */