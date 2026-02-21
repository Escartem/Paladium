package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.paladium.client.events.ClientEventHandler;
import fr.paladium.palamod.modules.paladium.utils.Packet;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;

@Packet(side = Side.CLIENT)
public class SCPacketSyncEvents implements IMessage {
  private Map<String, String> events;
  
  public SCPacketSyncEvents(Map<String, String> events) {
    this.events = events;
  }
  
  public SCPacketSyncEvents() {}
  
  public void toBytes(ByteBuf buffer) {
    buffer.writeInt(this.events.size());
    for (Map.Entry<String, String> run : this.events.entrySet()) {
      ByteBufUtils.writeUTF8String(buffer, run.getKey());
      ByteBufUtils.writeUTF8String(buffer, run.getValue());
    } 
  }
  
  public void fromBytes(ByteBuf buffer) {
    int size = buffer.readInt();
    this.events = new HashMap<>();
    for (int i = 0; i < size; i++) {
      String key = ByteBufUtils.readUTF8String(buffer);
      String value = ByteBufUtils.readUTF8String(buffer);
      this.events.put(key, value);
    } 
  }
  
  @PacketHandler
  public static class Handler implements IMessageHandler<SCPacketSyncEvents, IMessage> {
    public IMessage onMessage(SCPacketSyncEvents message, MessageContext ctx) {
      ClientEventHandler.events = message.events;
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\SCPacketSyncEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */