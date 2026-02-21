package fr.paladium.palaforgeutils.lib.command.annotated.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.dto.TabCompleteEntry;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import scala.actors.threadpool.Arrays;

public class CSPacketCommandTabCompleteCallback implements IMessage {
  private String uuid;
  
  private String[] options;
  
  public CSPacketCommandTabCompleteCallback() {}
  
  public CSPacketCommandTabCompleteCallback(String uuid, String[] options) {
    this.uuid = uuid;
    this.options = options;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.uuid = PacketSerialUtils.readString(buf);
    this.options = new String[PacketSerialUtils.readInt(buf)];
    for (int i = 0; i < this.options.length; i++)
      this.options[i] = PacketSerialUtils.readString(buf); 
  }
  
  public void toBytes(ByteBuf buf) {
    PacketSerialUtils.writeString(buf, this.uuid);
    PacketSerialUtils.writeInt(buf, this.options.length);
    for (String arg : this.options)
      PacketSerialUtils.writeString(buf, arg); 
  }
  
  public static class Handler implements IMessageHandler<CSPacketCommandTabCompleteCallback, IMessage> {
    @SideOnly(Side.SERVER)
    public IMessage onMessage(CSPacketCommandTabCompleteCallback message, MessageContext ctx) {
      CompletableFuture<List<String>> future = TabCompleteEntry.getAndRemoveCallback(message.uuid);
      if (future != null)
        future.complete(Arrays.asList((Object[])message.options)); 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\packet\CSPacketCommandTabCompleteCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */