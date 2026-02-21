package fr.paladium.palawarzoneevent.module.largage.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palaforgeutils.lib.server.internal.event.ServerChangeEvent;
import fr.paladium.palawarzoneevent.module.largage.client.ui.UIOverlayLargageScoreboard;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;

public class ClientEventListener {
  private UIOverlayLargageScoreboard overlay;
  
  @SubscribeEvent
  public void onPostServerTypeChange(ServerChangeEvent.Post event) {
    if (event.getServerType() == null || !event.getServerType().is(new ServerType[] { ServerType.WARZONE }))
      closeOverlay(); 
    if (Server.is(new ServerType[] { ServerType.WARZONE }))
      openOverlay(); 
  }
  
  public void openOverlay() {
    if (this.overlay == null || !ZUI.isOpen(UIOverlayLargageScoreboard.class)) {
      this.overlay = new UIOverlayLargageScoreboard();
      ZUI.open((UI)this.overlay);
    } 
  }
  
  private void closeOverlay() {
    if (this.overlay != null) {
      ZUI.close((UI)this.overlay);
      this.overlay = null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\client\listener\ClientEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */