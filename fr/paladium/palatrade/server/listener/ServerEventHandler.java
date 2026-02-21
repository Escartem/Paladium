package fr.paladium.palatrade.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palatrade.common.utils.Trade;
import fr.paladium.palatrade.server.manager.TradeManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class ServerEventHandler {
  @SubscribeEvent
  public void onLeave(PlayerEvent.PlayerLoggedOutEvent e) {
    EntityPlayer player = e.player;
    Trade trade = TradeManager.getInstance().getTrade(player);
    if (trade == null)
      return; 
    EntityPlayer target = TradeManager.getInstance().getTradePlayer(player);
    if (target instanceof EntityPlayerMP) {
      trade = TradeManager.getInstance().getTrade(target);
      if (trade != null && target.field_71070_bA instanceof fr.paladium.palatrade.common.container.ContainerTrade) {
        target.func_71053_j();
        (new Notification(Notification.NotificationType.INFO, "La demande d'échange a été annulé par " + player.func_70005_c_(), "trade")).send((EntityPlayerMP)target);
      } 
    } 
    TradeManager.getInstance().removeTrade(player);
  }
  
  @SubscribeEvent
  public void onPickup(EntityItemPickupEvent e) {
    EntityPlayer player = e.entityPlayer;
    if (!TradeManager.getInstance().isTrading(player))
      return; 
    e.setCanceled(true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\server\listener\ServerEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */