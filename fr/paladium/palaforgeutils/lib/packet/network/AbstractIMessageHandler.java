package fr.paladium.palaforgeutils.lib.packet.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import java.util.function.Consumer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AbstractIMessageHandler implements IMessageHandler<AbstractIMessage, IMessage> {
  public IMessage onMessage(AbstractIMessage message, MessageContext ctx) {
    ForgePacket packet = message.getPacket();
    if (packet == null)
      return null; 
    packet.setSide(ctx.side);
    if (ctx.side == Side.CLIENT) {
      packet.processClient();
    } else {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      packet.setEffectivePlayer(player);
      packet.processServer(player);
    } 
    Consumer<Object> callback = packet.pollCallback();
    if (callback != null)
      callback.accept(packet); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\network\AbstractIMessageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */