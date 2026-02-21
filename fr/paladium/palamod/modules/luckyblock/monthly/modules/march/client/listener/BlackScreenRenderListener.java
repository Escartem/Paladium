package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class BlackScreenRenderListener {
  public static long expirationMillis;
  
  @SubscribeEvent
  public void onRender(RenderGameOverlayEvent event) {
    if (isExpired(System.currentTimeMillis()))
      return; 
    Minecraft minecraft = Minecraft.func_71410_x();
    ScaledResolution resolution = new ScaledResolution(minecraft, minecraft.field_71443_c, minecraft.field_71440_d);
    GuiUtils.drawRect(0.0D, 0.0D, resolution.func_78326_a(), resolution.func_78328_b(), Color.BLACK);
  }
  
  private boolean isExpired(long now) {
    return (now >= expirationMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\listener\BlackScreenRenderListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */