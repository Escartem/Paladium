package fr.paladium.palarpg.module.stat.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palarpg.module.stat.client.renderer.RPGDamageRenderer;
import fr.paladium.zephyrui.lib.opengl.GLHelper;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class RPGStatsWorldRenderListener {
  @SubscribeEvent
  public void onWorldRenderLast(RenderWorldLastEvent event) {
    GLHelper.pushAttrib(new Integer[0]);
    GLHelper.pushMatrix();
    GLHelper.depthMask(false);
    RPGDamageRenderer.render(event.partialTicks);
    GLHelper.popMatrix();
    GLHelper.popAttrib();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\client\listener\RPGStatsWorldRenderListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */