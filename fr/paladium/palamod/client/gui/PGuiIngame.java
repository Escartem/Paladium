package fr.paladium.palamod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class PGuiIngame {
  private final Minecraft mc = Minecraft.func_71410_x();
  
  private final PGuiOverlayDebug overlayDebug = new PGuiOverlayDebug(this.mc);
  
  public void renderGameOverlay(RenderGameOverlayEvent.Pre event) {
    if (event.type == RenderGameOverlayEvent.ElementType.DEBUG) {
      ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      this.overlayDebug.renderDebugInfo(scaledresolution);
      event.setCanceled(true);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\client\gui\PGuiIngame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */