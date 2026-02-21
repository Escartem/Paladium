package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks.BlockEncryptedComputer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class Handler implements IMessageHandler<CSEncryptedComputerPacket, IMessage> {
  public IMessage onMessage(CSEncryptedComputerPacket message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT)
      return null; 
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    World world = player.field_70170_p;
    if (!world.func_72899_e(CSEncryptedComputerPacket.access$000(message), CSEncryptedComputerPacket.access$100(message), CSEncryptedComputerPacket.access$200(message)))
      return null; 
    Block block = world.func_147439_a(CSEncryptedComputerPacket.access$000(message), CSEncryptedComputerPacket.access$100(message), CSEncryptedComputerPacket.access$200(message));
    if (!(block instanceof BlockEncryptedComputer))
      return null; 
    BlockEncryptedComputer computer = (BlockEncryptedComputer)block;
    DoubleLocation playerLocation = new DoubleLocation(player.field_70165_t, player.field_70163_u, player.field_70161_v);
    DoubleLocation location = new DoubleLocation(CSEncryptedComputerPacket.access$000(message), CSEncryptedComputerPacket.access$100(message), CSEncryptedComputerPacket.access$200(message));
    if (playerLocation.distance(location) > 5.0D)
      return null; 
    computer.onHacked(world, CSEncryptedComputerPacket.access$000(message), CSEncryptedComputerPacket.access$100(message), CSEncryptedComputerPacket.access$200(message), (EntityPlayer)player, CSEncryptedComputerPacket.access$300(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\packets\CSEncryptedComputerPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */