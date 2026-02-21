package fr.paladium.palaforgeutils.lib.command.annotated.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.command.annotated.parser.dto.TabCompleteEntry;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import scala.actors.threadpool.Arrays;

public class Handler implements IMessageHandler<CSPacketCommandTabCompleteCallback, IMessage> {
  @SideOnly(Side.SERVER)
  public IMessage onMessage(CSPacketCommandTabCompleteCallback message, MessageContext ctx) {
    CompletableFuture<List<String>> future = TabCompleteEntry.getAndRemoveCallback(CSPacketCommandTabCompleteCallback.access$000(message));
    if (future != null)
      future.complete(Arrays.asList((Object[])CSPacketCommandTabCompleteCallback.access$100(message))); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\packet\CSPacketCommandTabCompleteCallback$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */