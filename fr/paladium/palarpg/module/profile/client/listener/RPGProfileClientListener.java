package fr.paladium.palarpg.module.profile.client.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import fr.paladium.palaforgeutils.lib.server.internal.event.ServerChangeEvent;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.module.profile.client.ui.overlay.UIOverlayRPGBossBar;
import fr.paladium.palarpg.module.profile.client.ui.overlay.UIOverlayRPGHotBar;
import fr.paladium.palarpg.module.profile.client.ui.overlay.UIOverlayRPGPlayerHUD;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;

public class RPGProfileClientListener {
  private UIOverlayRPGPlayerHUD overlay = null;
  
  @SubscribeEvent
  public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
    if (PalaRPGMod.proxy.isDungeonWorld() && (event.type == RenderGameOverlayEvent.ElementType.AIR || event.type == RenderGameOverlayEvent.ElementType.JUMPBAR || event.type == RenderGameOverlayEvent.ElementType.FOOD || event.type == RenderGameOverlayEvent.ElementType.ARMOR || event.type == RenderGameOverlayEvent.ElementType.EXPERIENCE || event.type == RenderGameOverlayEvent.ElementType.HEALTHMOUNT || event.type == RenderGameOverlayEvent.ElementType.HEALTH))
      event.setCanceled(true); 
  }
  
  @SubscribeEvent
  public void onPostServerTypeChange(ServerChangeEvent.Post event) {
    if (event.getServerType() == null || !PalaRPGMod.proxy.isDungeonWorld())
      closeOverlay(); 
    if (PalaRPGMod.proxy.isDungeonWorld())
      openOverlay(); 
  }
  
  @SubscribeEvent
  public void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
    closeOverlay();
  }
  
  @SubscribeEvent(priority = EventPriority.LOW)
  public void onRenderNametag(RenderLivingEvent.Specials.Pre event) {
    if (!(event.entity instanceof fr.paladium.palarpg.module.profile.client.util.FakeEntityPlayerMP))
      return; 
    event.setCanceled(true);
  }
  
  public void openOverlay() {
    if (this.overlay == null || !ZUI.isOpen(UIOverlayRPGPlayerHUD.class)) {
      this.overlay = new UIOverlayRPGPlayerHUD();
      ZUI.open((UI)this.overlay);
    } 
    if (!ZUI.isOpen(UIOverlayRPGBossBar.class))
      ZUI.open((UI)new UIOverlayRPGBossBar()); 
    if (!ZUI.isOpen(UIOverlayRPGHotBar.class))
      ZUI.open((UI)new UIOverlayRPGHotBar()); 
  }
  
  private void closeOverlay() {
    if (this.overlay != null) {
      ZUI.close((UI)this.overlay);
      if (ZUI.isOpen(UIOverlayRPGBossBar.class))
        ZUI.close(ZUI.getUI(UIOverlayRPGBossBar.class)); 
      if (ZUI.isOpen(UIOverlayRPGHotBar.class))
        ZUI.close(ZUI.getUI(UIOverlayRPGHotBar.class)); 
      this.overlay = null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\client\listener\RPGProfileClientListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */