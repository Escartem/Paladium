package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.listener.BlackScreenRenderListener;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;

@PacketHandler
public class Handler implements IMessageHandler<SCBlackScreenPacket, IMessage> {
  public IMessage onMessage(SCBlackScreenPacket message, MessageContext ctx) {
    BlackScreenRenderListener.expirationMillis = System.currentTimeMillis() + SCBlackScreenPacket.access$000(message);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\network\packet\sc\SCBlackScreenPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */