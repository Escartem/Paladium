package fr.paladium.palarpg.module.dungeon.client.ui.transition;

import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import lombok.NonNull;
import net.minecraft.client.settings.KeyBinding;

@UIData(background = false, zlevel = 500.0D)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true, always = true, gui = true, zindex = 500))
public class UIDungeonTransitionOverlay extends UI {
  private final TweenAnimator animator;
  
  private final boolean wait;
  
  private boolean shouldClose;
  
  private boolean closing;
  
  public UIDungeonTransitionOverlay() {
    this(Boolean.valueOf(false));
  }
  
  public UIDungeonTransitionOverlay(@NonNull Boolean wait) {
    if (wait == null)
      throw new NullPointerException("wait is marked non-null but is null"); 
    this.animator = TweenAnimator.create(0.0F);
    this.wait = wait.booleanValue();
    KeyBinding.func_74506_a();
  }
  
  public void init() {
    if (this.wait) {
      this.animator.sequence(500.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
      return;
    } 
    Timeline timeline = Timeline.createSequence();
    timeline.push(Tween.to(this.animator, 0, 500.0F).target(1.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
    timeline.pushPause(300.0F);
    timeline.push(Tween.to(this.animator, 0, 500.0F).target(0.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
    this.animator.setTimeline(timeline);
    this.animator.setCallback(tween -> ZUI.close(this, true)).start();
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    this.animator.update();
    DrawUtils.SHAPE.drawRect(getRelativeX(0.0D), getRelativeY(0.0D), getViewportWidth(), getViewportHeight(), BackgroundNode.BACKGROUND_COLOR.copyAlpha(this.animator.getValue()));
  }
  
  public void update() {
    if (this.shouldClose && !this.closing && this.animator.getValue() == 1.0F) {
      this.closing = true;
      this.animator.sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(tween -> ZUI.close(this, true)).start();
    } 
  }
  
  public boolean close() {
    if (this.wait)
      this.shouldClose = true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\transition\UIDungeonTransitionOverlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */