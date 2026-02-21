package fr.paladium.permissionbridge.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import net.minecraft.entity.player.EntityPlayerMP;

public class PermissionBridgeServerListener {
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    FMLServerScheduler.getInstance().add(new Runnable[] { () -> {
            EntityPlayerMP player = (EntityPlayerMP)event.player;
            PermissionManager.inst().sync(player);
          } });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\permissionbridge\server\listener\PermissionBridgeServerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */