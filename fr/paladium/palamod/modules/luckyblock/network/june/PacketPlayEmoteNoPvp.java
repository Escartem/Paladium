package fr.paladium.palamod.modules.luckyblock.network.june;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import io.netty.buffer.ByteBuf;

public class PacketPlayEmoteNoPvp implements IMessage {
  public String emoteName;
  
  public PacketPlayEmoteNoPvp() {}
  
  public PacketPlayEmoteNoPvp(String emoteName) {
    this.emoteName = emoteName;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.emoteName = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.emoteName);
  }
  
  public static class Handler implements IMessageHandler<PacketPlayEmoteNoPvp, IMessage> {
    public IMessage onMessage(PacketPlayEmoteNoPvp message, MessageContext ctx) {
      ClientProxy.playEmoteNoPvp(message.emoteName);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\june\PacketPlayEmoteNoPvp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */