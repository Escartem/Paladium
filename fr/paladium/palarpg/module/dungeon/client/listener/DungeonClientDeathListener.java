package fr.paladium.palarpg.module.dungeon.client.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.module.dungeon.client.ui.death.UIDungeonDeathOverlay;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

public class DungeonClientDeathListener {
  public static int respawnTicks = 0;
  
  public static ItemStack respawnItem = null;
  
  public static boolean isDead;
  
  @SubscribeEvent
  public void onRenderRespawnAnimation(RenderGameOverlayEvent.Pre event) {
    if (event.type != RenderGameOverlayEvent.ElementType.ALL)
      return; 
    Minecraft mc = Minecraft.func_71410_x();
    if (respawnTicks <= 0 || mc.field_71439_g == null)
      return; 
    ScaledResolution scaledResolution = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
    int screenWidth = scaledResolution.func_78326_a();
    int screenHeight = scaledResolution.func_78328_b();
    GL11.glPushMatrix();
    GL11.glPushAttrib(1048575);
    int elapsedTicks = 40 - respawnTicks;
    float progress = (elapsedTicks + event.partialTicks) / 40.0F;
    float progressSq = progress * progress;
    float progressCubed = progress * progressSq;
    float animationCurve = 10.25F * progressCubed * progressSq - 24.95F * progressSq * progressSq + 25.5F * progressCubed - 13.8F * progressSq + 4.0F * progress;
    float animationAngle = animationCurve * 3.1415927F;
    GL11.glEnable(3008);
    GL11.glPushMatrix();
    GL11.glPushAttrib(8256);
    GL11.glEnable(2929);
    GL11.glDisable(2884);
    GL11.glPushMatrix();
    float iconScale = screenHeight * 0.11F + screenHeight * 0.38F * MathHelper.func_76126_a(animationAngle);
    GL11.glTranslated((screenWidth / 2.0F - iconScale / 2.0F), (screenHeight / 2.0F - iconScale / 2.0F), -50.0D);
    GL11.glScalef(iconScale, -iconScale, iconScale);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glRotatef(900.0F * Math.abs(MathHelper.func_76126_a(animationAngle)), 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(6.0F * MathHelper.func_76134_b(progress * 8.0F), 1.0F, 0.0F, 0.0F);
    GL11.glRotatef(6.0F * MathHelper.func_76134_b(progress * 8.0F), 0.0F, 0.0F, 1.0F);
    Minecraft.func_71410_x().func_110434_K().func_110577_a(TextureMap.field_110576_c);
    GL11.glEnable(32826);
    RenderHelper.func_74520_c();
    DrawUtils.ITEM.drawItem(0.0D, 0.0D, 1.0D, respawnItem, Color.WHITE.copyAlpha(MathHelper.func_76126_a(animationAngle)), false);
    RenderHelper.func_74518_a();
    GL11.glDisable(32826);
    GL11.glPopMatrix();
    GL11.glPopAttrib();
    GL11.glPopMatrix();
    GL11.glEnable(2884);
    GL11.glDisable(2929);
    GL11.glPopAttrib();
    GL11.glPopMatrix();
  }
  
  @SubscribeEvent
  public void onTickRespawnAnimation(TickEvent.ClientTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    if (respawnTicks > 0) {
      respawnTicks--;
    } else if (respawnItem != null) {
      respawnItem = null;
    } 
  }
  
  @SubscribeEvent
  public void onTickDeath(TickEvent.ClientTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    Minecraft mc = Minecraft.func_71410_x();
    if (mc.field_71439_g == null || !PalaRPGMod.proxy.isDungeonWorld()) {
      respawn();
      return;
    } 
    EntityClientPlayerMP entityClientPlayerMP = mc.field_71439_g;
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (!optionalDungeonWorld.isPresent()) {
      respawn();
      return;
    } 
    Optional<DungeonPlayer> optionalDungeonPlayer = ((DungeonWorld)optionalDungeonWorld.get()).getPlayer(UUIDUtils.toString((Entity)entityClientPlayerMP));
    if (!optionalDungeonPlayer.isPresent()) {
      respawn();
      return;
    } 
    boolean isAlive = ((DungeonPlayer)optionalDungeonPlayer.get()).isAlive();
    if (isAlive && isDead) {
      respawn();
    } else if (!isAlive && !isDead) {
      kill();
    } 
  }
  
  private void respawn() {
    if (!isDead)
      return; 
    UI ui = ZUI.getUI(UIDungeonDeathOverlay.class);
    if (ui != null)
      ZUI.close(ui); 
    isDead = false;
  }
  
  private void kill() {
    if (isDead)
      return; 
    ZUI.open((UI)new UIDungeonDeathOverlay());
    isDead = true;
  }
  
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onRenderDeathOverlay(RenderGameOverlayEvent.Pre event) {
    if (!isDead)
      return; 
    if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
      event.setCanceled(true); 
  }
  
  @SubscribeEvent
  public void onRenderDeathHand(RenderHandEvent event) {
    if (!isDead)
      return; 
    event.setCanceled(true);
  }
  
  @SubscribeEvent
  public void onRenderDeathEntity(RenderLivingEvent.Pre event) {
    if (!(event.entity instanceof EntityPlayer) || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player == (Minecraft.func_71410_x()).field_71439_g)
      return; 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (!optionalDungeonWorld.isPresent())
      return; 
    Optional<DungeonPlayer> optionalDungeonPlayer = ((DungeonWorld)optionalDungeonWorld.get()).getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent())
      return; 
    if (!((DungeonPlayer)optionalDungeonPlayer.get()).isAlive()) {
      event.setCanceled(true);
      event.entity.func_70066_B();
    } 
  }
  
  @SubscribeEvent
  public void onRenderDeathEntitySpecials(RenderLivingEvent.Specials event) {
    if (!(event.entity instanceof EntityPlayer) || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player == (Minecraft.func_71410_x()).field_71439_g)
      return; 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (!optionalDungeonWorld.isPresent())
      return; 
    Optional<DungeonPlayer> optionalDungeonPlayer = ((DungeonWorld)optionalDungeonWorld.get()).getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent())
      return; 
    if (!((DungeonPlayer)optionalDungeonPlayer.get()).isAlive()) {
      event.setCanceled(true);
      event.entity.func_70066_B();
    } 
  }
  
  @SubscribeEvent
  public void onRenderDeathPlayer(RenderPlayerEvent.Pre event) {
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player == (Minecraft.func_71410_x()).field_71439_g || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (!optionalDungeonWorld.isPresent())
      return; 
    Optional<DungeonPlayer> optionalDungeonPlayer = ((DungeonWorld)optionalDungeonWorld.get()).getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent())
      return; 
    if (!((DungeonPlayer)optionalDungeonPlayer.get()).isAlive()) {
      event.setCanceled(true);
      event.entity.func_70066_B();
    } 
  }
  
  @SubscribeEvent
  public void onRenderDeathPlayerSpecials(RenderPlayerEvent.Specials.Pre event) {
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player == (Minecraft.func_71410_x()).field_71439_g || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (!optionalDungeonWorld.isPresent())
      return; 
    Optional<DungeonPlayer> optionalDungeonPlayer = ((DungeonWorld)optionalDungeonWorld.get()).getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent())
      return; 
    if (!((DungeonPlayer)optionalDungeonPlayer.get()).isAlive()) {
      event.setCanceled(true);
      event.entity.func_70066_B();
    } 
  }
  
  @SubscribeEvent
  public void onRenderDeathPlayerArmor(RenderPlayerEvent.Pre event) {
    EntityPlayer player = (EntityPlayer)event.entity;
    if (player == (Minecraft.func_71410_x()).field_71439_g || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.getClient();
    if (!optionalDungeonWorld.isPresent())
      return; 
    Optional<DungeonPlayer> optionalDungeonPlayer = ((DungeonWorld)optionalDungeonWorld.get()).getPlayer(UUIDUtils.toString((Entity)player));
    if (!optionalDungeonPlayer.isPresent())
      return; 
    if (!((DungeonPlayer)optionalDungeonPlayer.get()).isAlive()) {
      event.setCanceled(true);
      event.entity.func_70066_B();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\client\listener\DungeonClientDeathListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */