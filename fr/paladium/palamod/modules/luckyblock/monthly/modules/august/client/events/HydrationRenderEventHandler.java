package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.HydrationData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class HydrationRenderEventHandler {
  private static final int PROGRESS_BAR_WIDTH = 80;
  
  private static final int PROGRESS_BAR_HEIGHT = 5;
  
  public static HydrationRenderEventHandler INSTANCE;
  
  private HydrationData data;
  
  public void setData(HydrationData data) {
    this.data = data;
  }
  
  public HydrationData getData() {
    return this.data;
  }
  
  public HydrationRenderEventHandler() {
    INSTANCE = this;
    this.data = null;
  }
  
  @SubscribeEvent
  public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
    Minecraft mc = Minecraft.func_71410_x();
    if (event.type != RenderGameOverlayEvent.ElementType.FOOD)
      return; 
    if (this.data == null || this.data.isExpired())
      return; 
    int screenWidth = event.resolution.func_78326_a();
    int screenHeight = event.resolution.func_78328_b();
    int progressBarX = (screenWidth - 80) / 2 + 50;
    int progressBarY = screenHeight - 45;
    float progress = this.data.getCurrentHydration() / 100.0F;
    GL11.glPushMatrix();
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    Gui.func_73734_a(progressBarX, progressBarY, progressBarX + 80, progressBarY + 5, -2147483648);
    Gui.func_73734_a(progressBarX + (int)(80.0F * (1.0F - progress)), progressBarY, progressBarX + 80, progressBarY + 5, -16746562);
    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\events\HydrationRenderEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */