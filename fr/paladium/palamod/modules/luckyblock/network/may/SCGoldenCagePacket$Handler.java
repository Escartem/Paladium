package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;

public class Handler implements IMessageHandler<SCGoldenCagePacket, IMessage> {
  public IMessage onMessage(SCGoldenCagePacket message, MessageContext ctx) {
    if (ctx.side != Side.CLIENT)
      return null; 
    SCGoldenCagePacket.LOCATION = new DoubleLocation(SCGoldenCagePacket.access$000(message), SCGoldenCagePacket.access$100(message), SCGoldenCagePacket.access$200(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\SCGoldenCagePacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */