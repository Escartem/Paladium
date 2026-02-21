package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events.DarkRenderEventHandler;
import io.netty.buffer.ByteBuf;
import java.util.concurrent.TimeUnit;

public class SCDarkScreenPacket implements IMessage {
  public static final long FIRST_DARK_DURATION = TimeUnit.SECONDS.toMillis(1L);
  
  public static final long SECOND_DARK_DURATION = TimeUnit.SECONDS.toMillis(3L);
  
  private long duration;
  
  public SCDarkScreenPacket(long duration) {
    this.duration = duration;
  }
  
  public SCDarkScreenPacket() {
    this.duration = 0L;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.duration = buf.readLong();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeLong(this.duration);
  }
  
  public static class Handler implements IMessageHandler<SCDarkScreenPacket, IMessage> {
    public IMessage onMessage(SCDarkScreenPacket message, MessageContext ctx) {
      DarkRenderEventHandler.INSTANCE.setDarkExpirationMillis(System.currentTimeMillis() + message.duration);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\server\SCDarkScreenPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */