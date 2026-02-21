package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events.HydrationRenderEventHandler;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.HydrationData;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;

public class SCHydrationPacket implements IMessage {
  public static final int ADD_ACTION_ID = 0;
  
  public static final int REMOVE_ACTION_ID = 1;
  
  private HydrationData data;
  
  public SCHydrationPacket() {
    this.data = new HydrationData();
  }
  
  public SCHydrationPacket(HydrationData data) {
    this.data = data;
  }
  
  public void fromBytes(ByteBuf buf) {
    this
      
      .data = new HydrationData(buf.readLong(), buf.readLong(), FastUUID.parseUUID(ByteBufUtils.readUTF8String(buf)), buf.readInt());
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeLong(this.data.getCreationMillis());
    buf.writeLong(this.data.getLastHitMillis());
    ByteBufUtils.writeUTF8String(buf, FastUUID.toString(this.data.getPlayerUniqueId()));
    buf.writeInt(this.data.getCurrentHydration());
  }
  
  public static class Handler implements IMessageHandler<SCHydrationPacket, IMessage> {
    public IMessage onMessage(SCHydrationPacket message, MessageContext ctx) {
      HydrationRenderEventHandler.INSTANCE.setData(message.data);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\packets\SCHydrationPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */