package fr.paladium.palamod.client.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.scheduler.PaladiumScheduler;

@SideOnly(Side.CLIENT)
public class ClientHandler {
  @SubscribeEvent
  public void onClientTick(TickEvent.ClientTickEvent event) {
    if (event.phase == TickEvent.Phase.START)
      PaladiumScheduler.INSTANCE.tick(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\client\events\ClientHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */