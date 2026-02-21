package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySafeChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class Handler implements IMessageHandler<PacketOpenSafeChest, IMessage> {
  public IMessage onMessage(PacketOpenSafeChest message, MessageContext ctx) {
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    if (!player.field_70170_p.func_72899_e(PacketOpenSafeChest.access$000(message), PacketOpenSafeChest.access$100(message), PacketOpenSafeChest.access$200(message)))
      return null; 
    TileEntity tile = player.field_70170_p.func_147438_o(PacketOpenSafeChest.access$000(message), PacketOpenSafeChest.access$100(message), PacketOpenSafeChest.access$200(message));
    if (tile == null || !(tile instanceof TileEntitySafeChest))
      return null; 
    TileEntitySafeChest chest = (TileEntitySafeChest)tile;
    if (!chest.func_70300_a((EntityPlayer)player))
      return null; 
    if (chest.getCode() == null || chest.getCode().isEmpty()) {
      chest.code = PacketOpenSafeChest.access$300(message);
    } else if (!chest.getCode().equals(PacketOpenSafeChest.access$300(message))) {
      return null;
    } 
    player.field_71135_a.func_147359_a(tile.func_145844_m());
    player.openGui(PalaMod.instance, PGuiRegistry.GUI_SAFE_CHEST, tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketOpenSafeChest$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */