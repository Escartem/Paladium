package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityClipboard;
import net.minecraft.tileentity.TileEntity;

public class Handler implements IMessageHandler<PacketClipboard, IMessage> {
  public IMessage onMessage(PacketClipboard message, MessageContext ctx) {
    if (!(ctx.getServerHandler()).field_147369_b.field_70170_p.func_72899_e(PacketClipboard.access$000(message), PacketClipboard.access$100(message), PacketClipboard.access$200(message)))
      return null; 
    TileEntity tile = (ctx.getServerHandler()).field_147369_b.field_70170_p.func_147438_o(PacketClipboard.access$000(message), 
        PacketClipboard.access$100(message), PacketClipboard.access$200(message));
    if (tile != null && tile instanceof TileEntityClipboard) {
      TileEntityClipboard clipboard = (TileEntityClipboard)tile;
      clipboard.updateClipboardFromPlayerSelection(PacketClipboard.access$300(message));
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketClipboard$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */