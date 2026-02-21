package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.network.packet.cs;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.ShootingStarEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.server.dialog.ShootingStarDialogManager;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSShootingStarHandlePacket implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  @PacketHandler
  public static class Handler implements IMessageHandler<CSShootingStarHandlePacket, IMessage> {
    public IMessage onMessage(CSShootingStarHandlePacket message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      ShootingStarEvent instance = ShootingStarEvent.getInstance();
      if (!instance.hasWished(player.func_110124_au()))
        return null; 
      ShootingStarDialogManager.getInstance().sendDialog(player, null);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\network\packet\cs\CSShootingStarHandlePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */