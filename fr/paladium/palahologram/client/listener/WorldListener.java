package fr.paladium.palahologram.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palahologram.client.renderer.HologramRenderer;
import fr.paladium.palahologram.common.worlddata.HologramWorldData;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class WorldListener {
  @SubscribeEvent
  public void onWorldRender(RenderWorldLastEvent event) {
    HologramWorldData hologramData = HologramWorldData.get();
    hologramData.getHologramData().values().forEach(HologramRenderer::renderHologram);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palahologram\client\listener\WorldListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */