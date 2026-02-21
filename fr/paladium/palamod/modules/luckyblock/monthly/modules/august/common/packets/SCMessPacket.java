package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events.MessRenderEventHandler;
import io.netty.buffer.ByteBuf;
import java.util.concurrent.TimeUnit;

public class SCMessPacket implements IMessage {
  private static final String SOUND_NAME = "mess";
  
  private static final long MESS_DURATION = TimeUnit.SECONDS.toMillis(3L);
  
  private long duration;
  
  public SCMessPacket() {
    this.duration = MESS_DURATION;
  }
  
  public SCMessPacket(long duration) {
    this.duration = duration;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.duration = buf.readLong();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeLong(this.duration);
  }
  
  public static class Handler implements IMessageHandler<SCMessPacket, IMessage> {
    public IMessage onMessage(SCMessPacket message, MessageContext ctx) {
      MessRenderEventHandler.INSTANCE.setMessExpirationMillis(System.currentTimeMillis() + message.duration);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\packets\SCMessPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */