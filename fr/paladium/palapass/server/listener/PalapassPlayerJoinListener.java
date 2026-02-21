package fr.paladium.palapass.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palapass.common.network.data.PalapassPlayer;

public class PalapassPlayerJoinListener {
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent e) {
    PalapassPlayer.get(e.player).refreshQuests();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\listener\PalapassPlayerJoinListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */