package fr.paladium.palamod.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palamod.scheduler.PaladiumScheduler;

public class ServerHandler {
  @SubscribeEvent
  public void on(TickEvent.ServerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    PaladiumScheduler.INSTANCE.tick();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\event\ServerHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */