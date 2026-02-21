package fr.paladium.palavanillagui.client.ui.util.node;

import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.node.Node;
import net.minecraft.util.ResourceLocation;

public class HeartNode extends Node {
  private static final Resource HEART_EMPTY = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/heart_empty.png")).nearest();
  
  private static final Resource HEART_DAMAGED_OVERLAY = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/hurt_damaged_overlay.png")).nearest();
  
  private static final Resource HEART_FULL = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/heart_full.png")).nearest();
  
  private static final Resource HEART_POISON = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/heart_poison.png")).nearest();
  
  private static final Resource HEART_WITHER = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/heart_wither.png")).nearest();
  
  private static final Resource HEART_ABSO = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/heart_absorption.png")).nearest();
  
  private static final Resource MOUNTHEART_FULL = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/hotbar/heart_mount.png")).nearest();
  
  private boolean half = false;
  
  private boolean full = false;
  
  private boolean abso = false;
  
  private boolean poison = false;
  
  private boolean wither = false;
  
  private boolean mount = false;
  
  private final TweenAnimator healAnimation;
  
  private final TweenAnimator hurtAnimation;
  
  protected HeartNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.healAnimation = TweenAnimator.create(0.0F);
    this.hurtAnimation = TweenAnimator.create(0.0F);
    this.hurtAnimation.sequence(1.0F, 1.0F, (TweenEquation)TweenEquations.LINEAR);
    this.hurtAnimation.getTimeline().pushPause(200.0F);
    this.hurtAnimation.push(1.0F, 0.0F, (TweenEquation)TweenEquations.LINEAR);
    this.hurtAnimation.getTimeline().pushPause(100.0F);
    this.hurtAnimation.push(1.0F, 1.0F, (TweenEquation)TweenEquations.LINEAR);
    this.hurtAnimation.getTimeline().pushPause(100.0F);
    this.hurtAnimation.push(1.0F, 0.0F, (TweenEquation)TweenEquations.LINEAR);
  }
  
  public static HeartNode create(double x, double y, double width, double height) {
    return new HeartNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    this.healAnimation.update();
    this.hurtAnimation.update();
    DrawUtils.RESOURCE.drawImage(getX(), getY() - this.healAnimation.getValue(), getWidth(), getHeight(), HEART_EMPTY);
    Resource heart_filler = HEART_FULL;
    if (this.mount) {
      heart_filler = MOUNTHEART_FULL;
    } else if (this.poison) {
      heart_filler = HEART_POISON;
    } else if (this.wither) {
      heart_filler = HEART_WITHER;
    } else if (this.abso) {
      heart_filler = HEART_ABSO;
    } 
    if (this.full) {
      DrawUtils.RESOURCE.drawImage(getX(), getY() - this.healAnimation.getValue(), getWidth(), getHeight(), heart_filler);
    } else if (this.half) {
      Double[] scissorbox = { Double.valueOf(0.0D), Double.valueOf(0.0D), Double.valueOf(dw(2.0D)), Double.valueOf(getHeight()) };
      if (this.mount)
        scissorbox[0] = Double.valueOf(dw(2.0D)); 
      DrawUtils.RESOURCE.drawImage(getX() + (this.mount ? dw(2.0D) : 0.0D), getY() - this.healAnimation.getValue(), getWidth(), getHeight(), heart_filler.copy().textureCoords(scissorbox[0].doubleValue(), scissorbox[1].doubleValue(), scissorbox[2].doubleValue(), scissorbox[3].doubleValue()));
    } 
    if (this.hurtAnimation.getTimeline().isStarted() && this.hurtAnimation.getValue() == 1.0F)
      DrawUtils.RESOURCE.drawImage(getX() - 2.0D, getY() - this.healAnimation.getValue() - 1.0D, getWidth() + 4.0D, getHeight() + 4.0D, HEART_DAMAGED_OVERLAY); 
  }
  
  public <T extends HeartNode> T healAnimOffset(float offset) {
    this.healAnimation.setTimeline(Timeline.createSequence());
    this.healAnimation.getTimeline().delay(offset);
    this.healAnimation.push(50.0F, 7.0F, (TweenEquation)TweenEquations.SINE_OUT);
    this.healAnimation.push(50.0F, 0.0F, (TweenEquation)TweenEquations.SINE_IN);
    this.healAnimation.start();
    return (T)this;
  }
  
  public <T extends HeartNode> T hurtAnimation() {
    this.hurtAnimation.start();
    return (T)this;
  }
  
  public <T extends HeartNode> T half(boolean half) {
    this.half = half;
    return (T)this;
  }
  
  public <T extends HeartNode> T full(boolean full) {
    this.full = full;
    return (T)this;
  }
  
  public <T extends HeartNode> T abso(boolean abso) {
    this.abso = abso;
    return (T)this;
  }
  
  public <T extends HeartNode> T poison(boolean poison) {
    this.poison = poison;
    return (T)this;
  }
  
  public <T extends HeartNode> T wither(boolean wither) {
    this.wither = wither;
    return (T)this;
  }
  
  public <T extends HeartNode> T mount(boolean mount) {
    this.mount = mount;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\node\HeartNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */