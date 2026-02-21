package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.common.items.ItemStickModeration;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketRequestScanStickPlayers implements IMessage {
  private int itemId;
  
  private int radius;
  
  public CSPacketRequestScanStickPlayers() {}
  
  public CSPacketRequestScanStickPlayers(int itemId, int radius) {
    this.itemId = itemId;
    this.radius = radius;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.itemId = buf.readInt();
    this.radius = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.itemId);
    buf.writeInt(this.radius);
  }
  
  public static class Handler implements IMessageHandler<CSPacketRequestScanStickPlayers, PacketListPlayer> {
    @SideOnly(Side.SERVER)
    public PacketListPlayer onMessage(CSPacketRequestScanStickPlayers message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemStickModeration) {
        ItemStickModeration item = (ItemStickModeration)player.func_70694_bm().func_77973_b();
        List<String> playerNames = item.getResultPlayer(player.func_70694_bm(), player.field_70170_p, player, message.itemId, message.radius);
        if (playerNames != null)
          PalaMod.getNetwork().sendTo(new PacketListPlayer(playerNames), player); 
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\CSPacketRequestScanStickPlayers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */