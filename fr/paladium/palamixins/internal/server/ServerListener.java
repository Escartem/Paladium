package fr.paladium.palamixins.internal.server;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamixins.event.SlotCanTakeEvent;

public class ServerListener {
  @SubscribeEvent
  public void onCraftItem(SlotCanTakeEvent event) {
    System.out.println(event.getSlot() + " " + event.getPlayer().func_70005_c_() + " " + event.getItemStack());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\internal\server\ServerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */