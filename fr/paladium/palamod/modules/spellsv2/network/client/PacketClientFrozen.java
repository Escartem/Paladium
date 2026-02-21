package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.ClientProxy;
import io.netty.buffer.ByteBuf;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class PacketClientFrozen implements IMessage {
  private boolean frozen;
  
  private int tier;
  
  public PacketClientFrozen() {}
  
  public PacketClientFrozen(boolean frozen, int tier) {
    this.frozen = frozen;
    this.tier = tier;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.frozen = buf.readBoolean();
    this.tier = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.frozen);
    buf.writeInt(this.tier);
  }
  
  public static class Handler implements IMessageHandler<PacketClientFrozen, IMessage> {
    public IMessage onMessage(PacketClientFrozen message, MessageContext ctx) {
      ClientProxy.frozen = message.frozen;
      ClientProxy.frozenStart = LocalDateTime.ofEpochSecond(
          LocalDateTime.now().toEpochSecond(ZoneOffset.ofTotalSeconds(0)), 0, 
          ZoneOffset.ofTotalSeconds(0));
      ClientProxy.frozenEnd = LocalDateTime.ofEpochSecond(LocalDateTime.now().plusSeconds((message.tier * 2))
          .toEpochSecond(ZoneOffset.ofTotalSeconds(0)), 0, ZoneOffset.ofTotalSeconds(0));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientFrozen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */