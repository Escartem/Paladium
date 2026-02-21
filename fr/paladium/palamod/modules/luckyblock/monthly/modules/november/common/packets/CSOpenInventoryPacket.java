package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.luckyevents.ExplosiveInventoryEvent;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSOpenInventoryPacket implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<CSOpenInventoryPacket, IMessage> {
    public IMessage onMessage(CSOpenInventoryPacket message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      ExplosiveInventoryEvent event = ExplosiveInventoryEvent.getInstance();
      if (event == null)
        return null; 
      event.throwDynamite(player, System.currentTimeMillis());
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\packets\CSOpenInventoryPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */