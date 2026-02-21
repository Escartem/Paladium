package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockRunningTreadmill;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class Handler implements IMessageHandler<CSSummerBodyPacket, IMessage> {
  public IMessage onMessage(CSSummerBodyPacket message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT)
      return null; 
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    World world = player.field_70170_p;
    if (!world.func_72899_e(CSSummerBodyPacket.access$000(message), CSSummerBodyPacket.access$100(message), CSSummerBodyPacket.access$200(message)))
      return null; 
    Block block = world.func_147439_a(CSSummerBodyPacket.access$000(message), CSSummerBodyPacket.access$100(message), CSSummerBodyPacket.access$200(message));
    if (!(block instanceof BlockRunningTreadmill))
      return null; 
    BlockRunningTreadmill treadmill = (BlockRunningTreadmill)block;
    DoubleLocation playerLocation = new DoubleLocation(player.field_70165_t, player.field_70163_u, player.field_70161_v);
    DoubleLocation location = new DoubleLocation(CSSummerBodyPacket.access$000(message), CSSummerBodyPacket.access$100(message), CSSummerBodyPacket.access$200(message));
    if (playerLocation.distance(location) > 5.0D)
      return null; 
    treadmill.handlePacket(player, location.getBlockX(), location.getBlockY(), location.getBlockZ(), CSSummerBodyPacket.access$300(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\packets\CSSummerBodyPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */