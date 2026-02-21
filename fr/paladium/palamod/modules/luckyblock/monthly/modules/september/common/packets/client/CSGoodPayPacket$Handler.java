package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.GoodPayEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class Handler implements IMessageHandler<CSGoodPayPacket, IMessage> {
  public IMessage onMessage(CSGoodPayPacket message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT)
      return null; 
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    GoodPayEvent.INSTANCE.giveReward(player);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\client\CSGoodPayPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */