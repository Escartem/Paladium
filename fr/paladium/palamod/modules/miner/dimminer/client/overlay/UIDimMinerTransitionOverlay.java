package fr.paladium.palamod.modules.miner.dimminer.client.overlay;

import fr.paladium.helios.module.title.ModuleTitle;
import fr.paladium.helios.module.title.utils.MinecraftTitle;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.interaction.UIDataOverlayInteraction;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@UIData(background = false)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true, always = true), interaction = @UIDataOverlayInteraction(cancelClick = true, cancelKeyboard = true, cancelScroll = true))
public class UIDimMinerTransitionOverlay extends UI {
  private final Minecraft mc;
  
  private final TweenAnimator animator;
  
  public Minecraft getMc() {
    return this.mc;
  }
  
  public TweenAnimator getAnimator() {
    return this.animator;
  }
  
  private final Map<Float, Runnable> callbackMap = new HashMap<>();
  
  private boolean started;
  
  public Map<Float, Runnable> getCallbackMap() {
    return this.callbackMap;
  }
  
  public boolean isStarted() {
    return this.started;
  }
  
  public UIDimMinerTransitionOverlay() {
    this.mc = Minecraft.func_71410_x();
    this.animator = TweenAnimator.create(0.0F);
    Timeline timeline = Timeline.createSequence();
    timeline.push(Tween.to(this.animator, 0, 1000.0F).target(0.0F).ease((TweenEquation)TweenEquations.LINEAR));
    timeline.push(Tween.to(this.animator, 0, 6000.0F).target(1.0F).ease((TweenEquation)TweenEquations.SINE_IN));
    timeline.push(Tween.to(this.animator, 0, 1000.0F).target(2.0F).ease((TweenEquation)TweenEquations.QUAD_INOUT));
    addCallback(1.1F, () -> ModuleTitle.getInstance().sendTitle(new MinecraftTitle("§5Dimension Mineur", "§dBienvenue dans la dimension du métier de mineur !", 500L, 3000L, 500L)));
    this.animator.setTimeline(timeline);
    this.animator.setCallback(tween -> ZUI.close(this));
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    double logoWidth = (622.0F * (1.0F + getStep(0.0F, 1.0F) * 0.5F) * (1.0F + getStep(1.0F, 2.0F) * 9.0F));
    double logoHeight = (152.0F * (1.0F + getStep(0.0F, 1.0F) * 0.5F) * (1.0F + getStep(1.0F, 2.0F) * 9.0F));
    if (this.animator.getValue() >= 1.0F) {
      GL11.glClear(1024);
      GL11.glEnable(2960);
      GL11.glStencilFunc(519, 1, 255);
      GL11.glStencilOp(7680, 7680, 7681);
      GL11.glColorMask(false, false, false, false);
      DrawUtils.RESOURCE.drawImage(960.0D - logoWidth / 2.0D, 540.0D - logoHeight / 2.0D, logoWidth, logoHeight, Resource.of(new ResourceLocation("palamod", "textures/gui/miner/transition/logo.png")));
      GL11.glColorMask(true, true, true, true);
      GL11.glStencilFunc(517, 1, 255);
      GL11.glStencilOp(7680, 7680, 7680);
    } 
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, 1920.0D, 1080.0D, Color.BLACK);
    if (this.animator.getValue() >= 1.0F) {
      GL11.glDisable(2960);
      GL11.glClear(1024);
    } 
    GL11.glColor4f(1.0F, 1.0F, 1.0F, getStep(0.0F, 0.5F) - getStep(1.0F, 1.5F));
    DrawUtils.RESOURCE.drawImage(960.0D - logoWidth / 2.0D, 540.0D - logoHeight / 2.0D, logoWidth, logoHeight, Resource.of(new ResourceLocation("palamod", "textures/gui/miner/transition/logo.png")));
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    if (!this.started) {
      this.started = true;
      this.animator.start();
    } 
    float oldValue = this.animator.getValue();
    this.animator.update();
    float newValue = this.animator.getValue();
    for (Iterator<Float> iterator = this.callbackMap.keySet().iterator(); iterator.hasNext(); ) {
      float step = ((Float)iterator.next()).floatValue();
      if (oldValue < step && newValue >= step)
        ((Runnable)this.callbackMap.get(Float.valueOf(step))).run(); 
    } 
  }
  
  public float getStep(float min, float max) {
    float value = this.animator.getValue();
    if (value < min)
      return 0.0F; 
    if (value > max)
      return 1.0F; 
    return (value - min) / (max - min);
  }
  
  public void addCallback(float step, Runnable callback) {
    this.callbackMap.put(Float.valueOf(step), callback);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\dimminer\client\overlay\UIDimMinerTransitionOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */