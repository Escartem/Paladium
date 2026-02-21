package fr.paladium.palawarzoneevent.module.capture.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palaforgeutils.lib.server.internal.event.ServerChangeEvent;
import fr.paladium.palawarzoneevent.module.capture.client.ui.UIOverlayCapture;
import fr.paladium.palawarzoneevent.module.capture.common.manager.CaptureEventManager;
import fr.paladium.palawarzoneevent.module.capture.common.util.CapturePoint;
import fr.paladium.palawarzoneevent.module.capture.common.util.CaptureRadiusShape;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public class ClientEventListener {
  private static final Map<String, List<DoubleLocation>> CAPTURE_BLOCK_CACHE = new HashMap<>();
  
  private UIOverlayCapture overlay;
  
  private final double offset = 0.001D;
  
  private final Color lowestColor = Color.BLUE.copyAlpha(0.2F);
  
  private final Color highestColor = Color.GREEN.copyAlpha(0.2F);
  
  private final Color interuptedColor = Color.RED.copyAlpha(0.2F);
  
  private long lastCaptureCacheUpdate = 0L;
  
  @SubscribeEvent
  public void onPostServerTypeChange(ServerChangeEvent.Post event) {
    if (event.getServerType() == null || !event.getServerType().is(new ServerType[] { ServerType.WARZONE })) {
      CAPTURE_BLOCK_CACHE.clear();
      closeOverlay();
    } 
    if (Server.is(new ServerType[] { ServerType.WARZONE }))
      openOverlay(); 
  }
  
  public void openOverlay() {
    if (this.overlay == null || !ZUI.isOpen(UIOverlayCapture.class)) {
      this.overlay = new UIOverlayCapture();
      ZUI.open((UI)this.overlay);
    } 
  }
  
  private void closeOverlay() {
    if (this.overlay != null) {
      ZUI.close((UI)this.overlay);
      this.overlay = null;
    } 
  }
  
  @SubscribeEvent
  public void onWorldRenderLast(RenderWorldLastEvent event) {
    if (!Server.is(new ServerType[] { ServerType.WARZONE }) || CaptureEventManager.inst().getPoints().isEmpty())
      return; 
    GL11.glPushMatrix();
    GL11.glPushAttrib(24576);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glDisable(2884);
    GL11.glDisable(3553);
    GL11.glDisable(2896);
    GL11.glTranslated(-RenderManager.field_78725_b, -RenderManager.field_78726_c, -RenderManager.field_78723_d);
    CaptureEventManager.inst().getPoints().forEach(capturePoint -> {
          if (!capturePoint.isEnabled())
            return; 
          if (!CAPTURE_BLOCK_CACHE.containsKey(capturePoint.getName()) || System.currentTimeMillis() - this.lastCaptureCacheUpdate > 60000L) {
            List<DoubleLocation> blocksList = loadCaptureCache(capturePoint);
            if (blocksList.isEmpty())
              return; 
            CAPTURE_BLOCK_CACHE.put(capturePoint.getName(), blocksList);
            this.lastCaptureCacheUpdate = System.currentTimeMillis();
          } 
          DoubleLocation loc = capturePoint.getLocation();
          if (loc.distance((Entity)(Minecraft.func_71410_x()).field_71439_g) > (Math.min((Minecraft.func_71410_x()).field_71474_y.field_151451_c, 4) << 4))
            return; 
          GL11.glTranslated(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
          Color overlayColor = capturePoint.isInterrupted() ? this.interuptedColor : this.lowestColor.to(this.highestColor, (float)(capturePoint.getCapturingProgress() / 100.0D));
          ((List)CAPTURE_BLOCK_CACHE.get(capturePoint.getName())).forEach(());
        });
    GL11.glPopAttrib();
    GL11.glPopMatrix();
  }
  
  private List<DoubleLocation> loadCaptureCache(CapturePoint capPoint) {
    World world = (Minecraft.func_71410_x()).field_71439_g.field_70170_p;
    DoubleLocation loc = capPoint.getLocation();
    int radius = capPoint.getRadius();
    List<DoubleLocation> blocksCache = new ArrayList<>();
    for (int i = -radius; i <= radius; i++) {
      for (int j = -radius; j <= radius; j++) {
        if (capPoint.getRadiusType() == CaptureRadiusShape.SQUARE || i * i + j * j <= radius * radius) {
          int blockX = loc.getBlockX() + i;
          int blockZ = loc.getBlockZ() + j;
          for (int k = -3; k < 2; k++) {
            int blockY = loc.getBlockY() + k;
            Block block = world.func_147439_a(blockX, blockY, blockZ);
            Block upperBlock = world.func_147439_a(blockX, blockY + 1, blockZ);
            if (block.func_149688_o().func_76220_a() && block.func_149688_o().func_76218_k() && block.func_149645_b() == 0 && !upperBlock.func_149688_o().func_76218_k() && !upperBlock.func_149688_o().func_76220_a())
              blocksCache.add(new DoubleLocation(blockX, blockY, blockZ)); 
          } 
        } 
      } 
    } 
    return blocksCache;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\capture\client\listener\ClientEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */