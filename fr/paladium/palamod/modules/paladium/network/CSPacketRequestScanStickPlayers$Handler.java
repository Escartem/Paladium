package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.common.items.ItemStickModeration;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class Handler implements IMessageHandler<CSPacketRequestScanStickPlayers, PacketListPlayer> {
  @SideOnly(Side.SERVER)
  public PacketListPlayer onMessage(CSPacketRequestScanStickPlayers message, MessageContext ctx) {
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemStickModeration) {
      ItemStickModeration item = (ItemStickModeration)player.func_70694_bm().func_77973_b();
      List<String> playerNames = item.getResultPlayer(player.func_70694_bm(), player.field_70170_p, player, CSPacketRequestScanStickPlayers.access$000(message), CSPacketRequestScanStickPlayers.access$100(message));
      if (playerNames != null)
        PalaMod.getNetwork().sendTo(new PacketListPlayer(playerNames), player); 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\CSPacketRequestScanStickPlayers$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */