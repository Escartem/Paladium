package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class DarkRenderEventHandler {
  public static DarkRenderEventHandler INSTANCE;
  
  private long darkExpirationMillis;
  
  public void setDarkExpirationMillis(long darkExpirationMillis) {
    this.darkExpirationMillis = darkExpirationMillis;
  }
  
  public long getDarkExpirationMillis() {
    return this.darkExpirationMillis;
  }
  
  public DarkRenderEventHandler() {
    INSTANCE = this;
    this.darkExpirationMillis = 0L;
  }
  
  @SubscribeEvent
  public void onRender(RenderGameOverlayEvent event) {
    Minecraft minecraft = Minecraft.func_71410_x();
    long now = System.currentTimeMillis();
    if (now > this.darkExpirationMillis)
      return; 
    ScaledResolution resolution = new ScaledResolution(minecraft, minecraft.field_71443_c, minecraft.field_71440_d);
    GuiUtils.drawRect(0.0D, 0.0D, resolution.func_78326_a(), resolution.func_78328_b(), Color.BLACK);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\events\DarkRenderEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */