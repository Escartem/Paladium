package fr.paladium.palavanillagui.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamixins.event.BlockAnvilActivatedEvent;
import fr.paladium.palavanillagui.PalaVanillaGuiMod;
import fr.paladium.palavanillagui.common.guihandler.GuiRegistry;

public class PVGuiServerAnvilActivatedListener {
  @SubscribeEvent
  public void onAnvilActivated(BlockAnvilActivatedEvent event) {
    if (!(event.getWorld()).field_72995_K)
      event.getPlayer().openGui(PalaVanillaGuiMod.getInstance(), GuiRegistry.GUI_ANVIL, event.getWorld(), event.getX(), event.getY(), event.getZ()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\server\listener\PVGuiServerAnvilActivatedListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */