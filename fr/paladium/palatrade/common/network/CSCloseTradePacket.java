package fr.paladium.palatrade.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palatrade.common.utils.Trade;
import fr.paladium.palatrade.lib.odin.modules.packet.lib.ForgePacket;
import fr.paladium.palatrade.server.manager.TradeManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSCloseTradePacket extends ForgePacket {
  public void write(ByteBuf buf) {}
  
  public void read(ByteBuf buf) {}
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Trade trade = TradeManager.getInstance().getTrade((EntityPlayer)player);
    if (trade == null)
      return; 
    (new Notification(Notification.NotificationType.INFO, "Vous avez annulé la demande d'échange avec " + trade.getTargetName(), "trade")).send(player);
    EntityPlayer target = TradeManager.getInstance().getTradePlayer((EntityPlayer)player);
    if (target instanceof EntityPlayerMP) {
      trade = TradeManager.getInstance().getTrade(target);
      if (trade != null && target.field_71070_bA instanceof fr.paladium.palatrade.common.container.ContainerTrade) {
        target.func_71053_j();
        (new Notification(Notification.NotificationType.INFO, "La demande d'échange a été annulé par " + player.func_70005_c_(), "trade")).send(player);
      } 
    } 
    TradeManager.getInstance().removeTrade((EntityPlayer)player);
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\common\network\CSCloseTradePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */