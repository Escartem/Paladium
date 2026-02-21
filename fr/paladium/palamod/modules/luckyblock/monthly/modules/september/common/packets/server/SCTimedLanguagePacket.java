package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events.LanguageCheckEventHandler;
import io.netty.buffer.ByteBuf;
import java.util.concurrent.TimeUnit;

public class SCTimedLanguagePacket implements IMessage {
  public static final long EVENT_DURATION = TimeUnit.SECONDS.toMillis(30L);
  
  private long duration;
  
  public SCTimedLanguagePacket(long duration) {
    this.duration = duration;
  }
  
  public SCTimedLanguagePacket() {
    this.duration = 0L;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.duration = buf.readLong();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeLong(this.duration);
  }
  
  public static class Handler implements IMessageHandler<SCTimedLanguagePacket, IMessage> {
    public IMessage onMessage(SCTimedLanguagePacket message, MessageContext ctx) {
      LanguageCheckEventHandler.INSTANCE.setLanguageExpirationMillis(System.currentTimeMillis() + message.duration);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\server\SCTimedLanguagePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */