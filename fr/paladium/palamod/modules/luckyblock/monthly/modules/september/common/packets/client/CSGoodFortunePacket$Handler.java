package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents.GoodFortuneEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class Handler implements IMessageHandler<CSGoodFortunePacket, IMessage> {
  public IMessage onMessage(CSGoodFortunePacket message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT)
      return null; 
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    World world = player.field_70170_p;
    GoodFortuneEvent.INSTANCE.giveReward(player);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\client\CSGoodFortunePacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */