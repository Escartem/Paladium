package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityLuckyBlock;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;

public class Handler implements IMessageHandler<PacketUseLuckyBlock, IMessage> {
  public IMessage onMessage(PacketUseLuckyBlock message, MessageContext ctx) {
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    if (message.cancel) {
      WorldServer world = (WorldServer)player.field_70170_p;
      if (!world.func_72899_e(message.x, message.y, message.z) || !(world.func_147438_o(message.x, message.y, message.z) instanceof TileEntityLuckyBlock))
        return null; 
      TileEntityLuckyBlock te = (TileEntityLuckyBlock)world.func_147438_o(message.x, message.y, message.z);
      if (te == null)
        return null; 
      te.setOpen(false);
      return null;
    } 
    PacketUseLuckyBlock.perform(player, message.x, message.y, message.z);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketUseLuckyBlock$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */