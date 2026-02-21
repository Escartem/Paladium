package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.sc;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.listener.BlackScreenRenderListener;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;

public class SCBlackScreenPacket implements IMessage {
  private long durationMillis;
  
  public SCBlackScreenPacket() {}
  
  public SCBlackScreenPacket(long durationMillis) {
    this.durationMillis = durationMillis;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.durationMillis = buf.readLong();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeLong(this.durationMillis);
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCBlackScreenPacket, IMessage> {
    public IMessage onMessage(SCBlackScreenPacket message, MessageContext ctx) {
      BlackScreenRenderListener.expirationMillis = System.currentTimeMillis() + message.durationMillis;
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\network\packet\sc\SCBlackScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */