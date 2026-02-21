package fr.paladium.palawarzoneevent.module.largage.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palawarzoneevent.module.largage.server.manager.LargageEventManager;

public class LargagePlayerLoggedInListener {
  @SubscribeEvent
  public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
    if (Server.is(new ServerType[] { ServerType.WARZONE }) && LargageEventManager.inst().isEventRunning())
      LargageEventManager.inst().sendLargageInfo(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\largage\server\listener\LargagePlayerLoggedInListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */