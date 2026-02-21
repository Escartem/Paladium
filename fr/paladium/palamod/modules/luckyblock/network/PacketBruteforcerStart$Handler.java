package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBruteforcer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class Handler implements IMessageHandler<PacketBruteforcerStart, IMessage> {
  public IMessage onMessage(PacketBruteforcerStart message, MessageContext ctx) {
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    if (!player.field_70170_p.func_72899_e(PacketBruteforcerStart.access$000(message), PacketBruteforcerStart.access$100(message), PacketBruteforcerStart.access$200(message)))
      return null; 
    TileEntity tile = player.field_70170_p.func_147438_o(PacketBruteforcerStart.access$000(message), PacketBruteforcerStart.access$100(message), PacketBruteforcerStart.access$200(message));
    if (tile == null || !(tile instanceof TileEntityBruteforcer))
      return null; 
    TileEntityBruteforcer bruteforcer = (TileEntityBruteforcer)tile;
    bruteforcer.start();
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketBruteforcerStart$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */