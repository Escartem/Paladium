package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SummerSalesEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class Handler implements IMessageHandler<CSSummerSalePacket, IMessage> {
  public IMessage onMessage(CSSummerSalePacket message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT)
      return null; 
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    World world = player.field_70170_p;
    SummerSalesEvent.INSTANCE.giveReward(player);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\packets\CSSummerSalePacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */