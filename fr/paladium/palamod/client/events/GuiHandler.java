package fr.paladium.palamod.client.events;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.client.gui.RenderHandler;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@SideOnly(Side.CLIENT)
public class GuiHandler {
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onGui(GuiOpenEvent event) {
    RenderHandler.guiInstance(event);
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onOverlay(RenderGameOverlayEvent.Pre event) {
    RenderHandler.guiIngameInstance(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\client\events\GuiHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */