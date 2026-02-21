package fr.paladium.palamod.modules.paladium.client.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import fr.paladium.palamod.modules.paladium.client.manager.AncientHammerEffectClientCamManager;
import fr.paladium.palamod.modules.paladium.common.items.sword.ancient.data.ItemAncientHammerPlayerData;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class AncientHammerEffectListener {
  private boolean inCam;
  
  private Field distanceField;
  
  private Field debugCamYawField;
  
  private Field debugCamPitchField;
  
  private float yaw;
  
  private float pitch;
  
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onRenderNametag(RenderPlayerEvent.Specials.Pre event) {
    if (!(event.entity instanceof fr.paladium.palamod.modules.paladium.common.entities.ancient.FakeEntityPlayerMP))
      return; 
    event.renderCape = false;
  }
  
  @SubscribeEvent
  public void onJoin(EntityJoinWorldEvent event) {
    if (!this.inCam || event.entity != (Minecraft.func_71410_x()).field_71439_g)
      return; 
    stop();
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent event) {
    if (event.phase != TickEvent.Phase.START || (Minecraft.func_71410_x()).field_71439_g == null)
      return; 
    if (AncientHammerEffectClientCamManager.inst().getCam() != null) {
      if (!this.inCam) {
        this.yaw = (Minecraft.func_71410_x()).field_71439_g.field_70177_z - 180.0F;
        this.pitch = (Minecraft.func_71410_x()).field_71439_g.field_70125_A;
        setRotation(this.yaw, this.pitch);
        setDistance(10.0F);
      } 
      (Minecraft.func_71410_x()).field_71439_g.field_70159_w = 0.0D;
      (Minecraft.func_71410_x()).field_71439_g.field_70181_x = 0.0D;
      (Minecraft.func_71410_x()).field_71439_g.field_70179_y = 0.0D;
      (Minecraft.func_71410_x()).field_71439_g.field_70145_X = true;
      (Minecraft.func_71410_x()).field_71439_g.field_71075_bZ.field_75100_b = true;
      (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 1;
      (Minecraft.func_71410_x()).field_71474_y.field_74325_U = true;
      this.inCam = true;
      KeyBinding.func_74506_a();
    } else if (this.inCam) {
      stop();
    } 
  }
  
  @SubscribeEvent
  public void onFovUpdate(FOVUpdateEvent event) {
    if (this.inCam)
      event.newfov = 1.0F; 
  }
  
  @SubscribeEvent
  public void onRenderEntity(RenderLivingEvent.Pre event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    if ((event.entity == (Minecraft.func_71410_x()).field_71439_g && this.inCam) || ItemAncientHammerPlayerData.get((EntityPlayer)event.entity).isEffectCamActive() || ItemAncientHammerPlayerData.get((EntityPlayer)event.entity).isInvisibilityEffect()) {
      event.setCanceled(true);
      event.entity.func_70066_B();
    } 
  }
  
  @SubscribeEvent
  public void onRenderEntitySpecials(RenderLivingEvent.Specials event) {
    if (!(event.entity instanceof EntityPlayer))
      return; 
    if ((event.entity == (Minecraft.func_71410_x()).field_71439_g && this.inCam) || ItemAncientHammerPlayerData.get((EntityPlayer)event.entity).isEffectCamActive() || ItemAncientHammerPlayerData.get((EntityPlayer)event.entity).isInvisibilityEffect()) {
      event.setCanceled(true);
      event.entity.func_70066_B();
    } 
  }
  
  @SubscribeEvent
  public void onRenderPlayer(RenderPlayerEvent.Pre event) {
    if ((event.entityPlayer == (Minecraft.func_71410_x()).field_71439_g && this.inCam) || ItemAncientHammerPlayerData.get(event.entityPlayer).isEffectCamActive() || ItemAncientHammerPlayerData.get((EntityPlayer)event.entity).isInvisibilityEffect()) {
      event.setCanceled(true);
      event.entity.func_70066_B();
    } 
  }
  
  @SubscribeEvent
  public void onRenderPlayerSpecials(RenderPlayerEvent.Specials.Pre event) {
    if ((event.entityPlayer == (Minecraft.func_71410_x()).field_71439_g && this.inCam) || ItemAncientHammerPlayerData.get(event.entityPlayer).isEffectCamActive() || ItemAncientHammerPlayerData.get((EntityPlayer)event.entity).isInvisibilityEffect()) {
      event.setCanceled(true);
      event.entity.func_70066_B();
    } 
  }
  
  @SubscribeEvent
  public void onRenderPlayerArmor(RenderPlayerEvent.Pre event) {
    if ((event.entityPlayer == (Minecraft.func_71410_x()).field_71439_g && this.inCam) || ItemAncientHammerPlayerData.get(event.entityPlayer).isEffectCamActive() || ItemAncientHammerPlayerData.get((EntityPlayer)event.entity).isInvisibilityEffect()) {
      event.setCanceled(true);
      event.entity.func_70066_B();
    } 
  }
  
  @SubscribeEvent
  public void onRenderOverlay(RenderGameOverlayEvent.Pre event) {
    if (!this.inCam || event.type != RenderGameOverlayEvent.ElementType.ALL)
      return; 
    event.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onRenderWorld(RenderWorldLastEvent event) {
    if (!this.inCam)
      return; 
    setRotation(this.yaw, this.pitch);
  }
  
  @SubscribeEvent
  public void onMouseEvent(MouseEvent event) {
    if (!this.inCam)
      return; 
    event.setCanceled(true);
  }
  
  private void stop() {
    this.yaw = 0.0F;
    this.pitch = 0.0F;
    setDistance(4.0F);
    (Minecraft.func_71410_x()).field_71439_g.field_70145_X = false;
    (Minecraft.func_71410_x()).field_71439_g.field_71075_bZ.field_75100_b = false;
    (Minecraft.func_71410_x()).field_71474_y.field_74320_O = 0;
    (Minecraft.func_71410_x()).field_71474_y.field_74325_U = false;
    AncientHammerEffectClientCamManager.inst().setCam(null);
    this.inCam = false;
  }
  
  private void setDistance(float distance) {
    if (this.distanceField == null) {
      try {
        this.distanceField = ReflectionHelper.findField(EntityRenderer.class, new String[] { "thirdPersonDistance", "field_78490_B" });
        this.distanceField.setAccessible(true);
      } catch (Exception e) {
        e.printStackTrace();
      } 
      if (this.distanceField == null)
        throw new IllegalStateException("Unable to find distanceField field"); 
    } 
    EntityRenderer entityRenderer = (Minecraft.func_71410_x()).field_71460_t;
    try {
      this.distanceField.set(entityRenderer, Float.valueOf(distance));
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private void setRotation(float yaw, float pitch) {
    if (this.debugCamYawField == null || this.debugCamPitchField == null) {
      try {
        this.debugCamYawField = ReflectionHelper.findField(EntityRenderer.class, new String[] { "debugCamYaw", "field_78485_D" });
        this.debugCamPitchField = ReflectionHelper.findField(EntityRenderer.class, new String[] { "debugCamPitch", "field_78487_F" });
        this.debugCamYawField.setAccessible(true);
        this.debugCamPitchField.setAccessible(true);
      } catch (Exception e) {
        e.printStackTrace();
      } 
      if (this.debugCamYawField == null || this.debugCamPitchField == null)
        throw new IllegalStateException("Unable to find debugCamYaw or debugCamPitch fields"); 
    } 
    EntityRenderer entityRenderer = (Minecraft.func_71410_x()).field_71460_t;
    try {
      this.debugCamYawField.set(entityRenderer, Float.valueOf(yaw));
      this.debugCamPitchField.set(entityRenderer, Float.valueOf(pitch));
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\listener\AncientHammerEffectListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */