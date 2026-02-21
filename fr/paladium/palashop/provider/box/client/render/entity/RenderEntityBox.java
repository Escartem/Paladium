package fr.paladium.palashop.provider.box.client.render.entity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palashop.provider.box.client.overlay.UIShopBoxOverlay;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.model.impl.model.animation.LindwormAnimatable;
import software.bernie.geckolib3.model.impl.render.LindwormRenderer;

public class RenderEntityBox extends Render {
  private static final Map<Entity, UIShopBoxOverlay> UI_MAP = new ConcurrentHashMap<>();
  
  private static final Map<Entity, SoundRecord> SOUND_MAP = new ConcurrentHashMap<>();
  
  public RenderEntityBox() {
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float ticks) {
    if (!(entity instanceof EntityBox) || entity.field_70128_L)
      return; 
    EntityBox box = (EntityBox)entity;
    if (box.getBoxData() == null)
      return; 
    EntityLivingBase entityLivingBase = (Minecraft.func_71410_x()).field_71451_h;
    double vx = ((Entity)entityLivingBase).field_70169_q + (((Entity)entityLivingBase).field_70165_t - ((Entity)entityLivingBase).field_70169_q) * ticks;
    double vy = ((Entity)entityLivingBase).field_70167_r + (((Entity)entityLivingBase).field_70163_u - ((Entity)entityLivingBase).field_70167_r) * ticks + entityLivingBase.func_70047_e();
    double vz = ((Entity)entityLivingBase).field_70166_s + (((Entity)entityLivingBase).field_70161_v - ((Entity)entityLivingBase).field_70166_s) * ticks;
    double ex = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * ticks;
    double ey = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * ticks + ((box.getBoxData().getEntityHeight() + 1.0F + Math.max(box.getBoxData().getOverlaySize(), box.getBoxData().getEntityWidth()) * 0.5625F) / 2.0F);
    double ez = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * ticks;
    double dx = vx - ex;
    double dy = vy - ey;
    double dz = vz - ez;
    double distHoriz = Math.sqrt(dx * dx + dz * dz);
    double rotateY = -Math.atan2(dz, dx) * 180.0D / Math.PI - 90.0D;
    double rotateX = Math.atan2(dy, distHoriz) * 180.0D / Math.PI;
    SoundRecord idleSound = SOUND_MAP.get(entity);
    if (idleSound == null) {
      idleSound = SoundRecord.create(box.getBoxData().getResource().getSound().getIdle()).position((float)box.field_70165_t, (float)box.field_70163_u, (float)box.field_70161_v).attenuation(ISound.AttenuationType.LINEAR).volume(1.5F).build().play();
      idleSound.condition(record -> {
            EntityBox closest = null;
            double closestDist = Double.MAX_VALUE;
            for (Object e : viewer.field_70170_p.field_72996_f) {
              if (!(e instanceof EntityBox))
                continue; 
              EntityBox otherBox = (EntityBox)e;
              double dist = otherBox.func_70068_e(viewer);
              if (dist < closestDist) {
                closest = otherBox;
                closestDist = dist;
              } 
            } 
            return (closest == box);
          });
      SOUND_MAP.put(entity, idleSound);
    } 
    if (!idleSound.isPlaying() && !box.getClientState().isActive() && !box.getClientState().isWaiting()) {
      idleSound = SoundRecord.create(box.getBoxData().getResource().getSound().getIdle()).position((float)box.field_70165_t, (float)box.field_70163_u, (float)box.field_70161_v).attenuation(ISound.AttenuationType.LINEAR).volume(1.5F).build().play();
      idleSound.condition(record -> {
            EntityBox closest = null;
            double closestDist = Double.MAX_VALUE;
            for (Object e : viewer.field_70170_p.field_72996_f) {
              if (!(e instanceof EntityBox))
                continue; 
              EntityBox otherBox = (EntityBox)e;
              double dist = otherBox.func_70068_e(viewer);
              if (dist < closestDist) {
                closest = otherBox;
                closestDist = dist;
              } 
            } 
            return (closest == box);
          });
      SOUND_MAP.put(entity, idleSound);
    } else if (idleSound.isPlaying() && (box.getClientState().isActive() || box.getClientState().isWaiting())) {
      idleSound.stop();
    } 
    LindwormAnimatable animatable = (LindwormAnimatable)box.getBoxData().getResource().getLindwormModel().getOrCreateAnimatable((Entity)box);
    if (animatable != null && animatable.getCurrentAnimation() != null && "idle_common".equals(animatable.getCurrentAnimation().getName()) && !box.getClientState().isActive() && !box.getClientState().isWaiting())
      animatable.forceAnimation("idle", false); 
    GL11.glPushMatrix();
    GL11.glAlphaFunc(516, 0.0F);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    GL11.glEnable(2884);
    GL11.glTranslated(x, y, z);
    GL11.glRotated(rotateY, 0.0D, 1.0D, 0.0D);
    LindwormRenderer.renderModel(entity, box.getBoxData().getResource().getLindwormModel());
    GL11.glAlphaFunc(516, 0.1F);
    GL11.glPopMatrix();
    UIShopBoxOverlay ui = getUi(box);
    GL11.glPushMatrix();
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(3042);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
    GL11.glDisable(3553);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
    GL11.glDisable(2896);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    double overlayWidth = box.getBoxData().getOverlaySize();
    double overlayHeight = overlayWidth * 0.5625D;
    GL11.glTranslated(x, y, z);
    GL11.glTranslated(-overlayWidth / 2.0D, box.getBoxData().getEntityHeight() + 0.5D, 0.0D);
    GL11.glTranslated(overlayWidth / 2.0D, overlayHeight / 2.0D, 0.0D);
    GL11.glRotated(rotateY, 0.0D, 1.0D, 0.0D);
    GL11.glRotated(rotateX, 1.0D, 0.0D, 0.0D);
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glTranslated(-(overlayWidth / 2.0D), -(overlayHeight / 2.0D), 0.0D);
    GL11.glScaled(overlayWidth / 1920.0D, overlayHeight / 1080.0D, 1.0D);
    ui.load(1920.0D, 1080.0D, 4);
    GL11.glColorMask(false, false, false, false);
    GL11.glDepthMask(true);
    GL11.glEnable(2929);
    GL11.glPushMatrix();
    ui.setMask(true);
    ui.draw(ticks, 1920.0D, 1080.0D, Mouse.getX(), Mouse.getY());
    GL11.glPopMatrix();
    GL11.glColorMask(true, true, true, true);
    GL11.glAlphaFunc(516, 0.0F);
    GL11.glDepthMask(false);
    GL11.glTranslated(0.0D, 0.0D, -0.01D);
    GL11.glScaled(1.0D, 1.0D, 0.0D);
    GL11.glPushMatrix();
    ui.setMask(false);
    ui.draw(ticks, 1920.0D, 1080.0D, Mouse.getX(), Mouse.getY());
    GL11.glPopMatrix();
    GL11.glDepthMask(true);
    GL11.glAlphaFunc(516, 0.1F);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
    GL11.glEnable(3553);
    OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
  
  public ResourceLocation func_110775_a(Entity entity) {
    return null;
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    WorldClient worldClient = (Minecraft.func_71410_x()).field_71441_e;
    if (worldClient == null)
      return; 
    if (!UI_MAP.isEmpty()) {
      Iterator<UIShopBoxOverlay> uiIterator = UI_MAP.values().iterator();
      while (uiIterator.hasNext()) {
        UIShopBoxOverlay ui = uiIterator.next();
        Entity entity = worldClient.func_73045_a(ui.getBox().func_145782_y());
        if (!(entity instanceof EntityBox) || !entity.func_70089_S()) {
          ui.close();
          ui.properlyClose();
          uiIterator.remove();
          continue;
        } 
        ui.onUpdate();
      } 
    } 
    if (!SOUND_MAP.isEmpty())
      for (Iterator<Map.Entry<Entity, SoundRecord>> it = SOUND_MAP.entrySet().iterator(); it.hasNext(); ) {
        Map.Entry<Entity, SoundRecord> entry = it.next();
        Entity entity = entry.getKey();
        SoundRecord sound = entry.getValue();
        if (!(entity instanceof EntityBox) || !entity.func_70089_S()) {
          if (sound != null)
            sound.stop(); 
          it.remove();
        } 
      }  
  }
  
  @SubscribeEvent
  public void onJoin(EntityJoinWorldEvent event) {
    if (event.entity != (Minecraft.func_71410_x()).field_71439_g)
      return; 
    for (UI ui : UI_MAP.values()) {
      ui.close();
      ui.properlyClose();
    } 
    UI_MAP.clear();
    SOUND_MAP.values().forEach(SoundRecord::stop);
    SOUND_MAP.clear();
  }
  
  public static void stop(@NonNull EntityBox entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (!UI_MAP.containsKey(entity))
      return; 
    UIShopBoxOverlay ui = UI_MAP.get(entity);
    ui.close();
    ui.properlyClose();
    UI_MAP.remove(entity);
    ((SoundRecord)SOUND_MAP.remove(entity)).stop();
  }
  
  public static UIShopBoxOverlay getUi(@NonNull EntityBox entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    if (UI_MAP.containsKey(entity))
      return UI_MAP.get(entity); 
    UIShopBoxOverlay ui = new UIShopBoxOverlay(entity);
    UI_MAP.put(entity, ui);
    return ui;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\client\render\entity\RenderEntityBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */