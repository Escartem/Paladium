package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events.MessRenderEventHandler;
import io.netty.buffer.ByteBuf;
import java.util.concurrent.TimeUnit;

public class SCPlanePacket implements IMessage {
  public static final String SOUND_NAME = "mess";
  
  private static final long MESS_DURATION = TimeUnit.SECONDS.toMillis(20L);
  
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<SCPlanePacket, IMessage> {
    public IMessage onMessage(SCPlanePacket message, MessageContext ctx) {
      MessRenderEventHandler.INSTANCE.setMessExpirationMillis(System.currentTimeMillis() + SCPlanePacket.MESS_DURATION);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\packets\SCPlanePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */