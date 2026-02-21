package fr.paladium.palamod.modules.miner.dimminer.client.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.miner.dimminer.client.overlay.UIDimMinerTransitionOverlay;
import fr.paladium.palamod.modules.miner.dimminer.common.data.DimMinerPlayer;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.world.World;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class ClientDimMinerEventHandler {
  private boolean wasInMinerDimension;
  
  private boolean active;
  
  @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
  public void onRender(RenderGameOverlayEvent.Pre e) {
    if (!PMiner.proxy.isMinerDimension()) {
      this.wasInMinerDimension = false;
      return;
    } 
    if (!this.wasInMinerDimension) {
      ZUI.open((UI)new UIDimMinerTransitionOverlay());
      this.wasInMinerDimension = true;
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onClientTick(TickEvent.ClientTickEvent e) {
    WorldClient world = (Minecraft.func_71410_x()).field_71441_e;
    if (world == null) {
      this.wasInMinerDimension = false;
      return;
    } 
    if (e.phase != TickEvent.Phase.END)
      return; 
    if (PMiner.proxy.isMinerDimension() && !ForgeEnv.isIDE()) {
      world.field_73011_w.setSkyRenderer(new IRenderHandler() {
            public void render(float partialTicks, WorldClient world, Minecraft mc) {}
          });
      world.func_72912_H().func_76068_b(15000L);
      world.func_72912_H().func_76084_b(false);
      world.func_72912_H().func_76080_g(0);
      float light = world.func_72801_o((int)(Minecraft.func_71410_x()).field_71439_g.field_70165_t, (int)(Minecraft.func_71410_x()).field_71439_g.field_70163_u, (int)(Minecraft.func_71410_x()).field_71439_g.field_70161_v);
      if (light > 0.5F) {
        DimMinerPlayer data = DimMinerPlayer.get();
        if (world.field_73012_v.nextInt(50) == 0)
          world.func_72980_b((Minecraft.func_71410_x()).field_71439_g.field_70165_t, (Minecraft.func_71410_x()).field_71439_g.field_70163_u, (Minecraft.func_71410_x()).field_71439_g.field_70161_v, "mob.bat.idle", 0.5F, world.field_73012_v.nextFloat(), false); 
        if (world.field_73012_v.nextInt(Math.max(1, (int)(data.getPoints() / 2L))) == 0) {
          double x = (Minecraft.func_71410_x()).field_71439_g.field_70165_t + world.field_73012_v.nextInt(30) - 15.0D;
          double z = (Minecraft.func_71410_x()).field_71439_g.field_70161_v + world.field_73012_v.nextInt(30) - 15.0D;
          world.func_72942_c((Entity)new EntityLightningBolt((World)world, x, world.func_72976_f((int)x, (int)z), z));
          world.func_72980_b(x, world.func_72976_f((int)x, (int)z), z, "ambient.weather.thunder", 100.0F, 1.0F, false);
        } 
      } 
      this.active = true;
    } else if (this.active) {
      world.field_73011_w.setSkyRenderer(null);
      this.active = false;
    } 
  }
  
  @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
  public void onFovUpdate(FOVUpdateEvent e) {
    if (PMiner.proxy.isMinerDimension() && !ForgeEnv.isIDE()) {
      DimMinerPlayer data = DimMinerPlayer.get();
      if (data.getPoints() < 500L) {
        Random random = e.entity.field_70170_p.field_73012_v;
        float mult = (1.0F - (float)data.getPoints() / 500.0F) * 2.0F;
        e.entity.field_71109_bG = random.nextFloat() * mult / 20.0F;
        e.entity.field_70726_aT = random.nextFloat() * mult;
      } 
    } 
  }
  
  @SubscribeEvent
  public void onRenderFog(EntityViewRenderEvent.RenderFogEvent e) {
    if (!PMiner.proxy.isMinerDimension() || ForgeEnv.isIDE())
      return; 
    Minecraft mc = Minecraft.func_71410_x();
    WorldClient worldClient = mc.field_71441_e;
    DimMinerPlayer data = DimMinerPlayer.get();
    for (int l = 0; l < 15; l++) {
      int i1 = (int)mc.field_71439_g.field_70165_t + ((World)worldClient).field_73012_v.nextInt(16) - ((World)worldClient).field_73012_v.nextInt(16);
      int j1 = (int)mc.field_71439_g.field_70163_u + 10 + ((World)worldClient).field_73012_v.nextInt(16) - ((World)worldClient).field_73012_v.nextInt(16);
      int k1 = (int)mc.field_71439_g.field_70161_v + ((World)worldClient).field_73012_v.nextInt(16) - ((World)worldClient).field_73012_v.nextInt(16);
      worldClient.func_72869_a("depthsuspend", (i1 + ((World)worldClient).field_73012_v.nextFloat()), (j1 + ((World)worldClient).field_73012_v.nextFloat()), (k1 + ((World)worldClient).field_73012_v.nextFloat()), 0.0D, 0.0D, 0.0D);
    } 
    float f1 = 5.0F;
    int j = 12 + (int)((float)(1000L - data.getPoints()) / 150.0F);
    if (j < 20)
      f1 = 5.0F + (e.farPlaneDistance - 5.0F) * (1.0F - j / 20.0F); 
    GL11.glFogi(2917, 9729);
    if (e.fogMode < 0) {
      GL11.glFogf(2915, 0.0F);
      GL11.glFogf(2916, f1 * 0.8F);
    } else {
      GL11.glFogf(2915, f1 * 0.25F);
      GL11.glFogf(2916, f1);
    } 
    if ((GLContext.getCapabilities()).GL_NV_fog_distance)
      GL11.glFogi(34138, 34139); 
  }
  
  @SubscribeEvent
  public void onFogColor(EntityViewRenderEvent.FogColors e) {
    if (!PMiner.proxy.isMinerDimension() || ForgeEnv.isIDE())
      return; 
    e.red = 0.0F;
    e.blue = 0.0F;
    e.green = 0.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\client\listener\ClientDimMinerEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */