package fr.paladium.palashop.client.ui.kit.transition;

import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.transition.Transition;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

public class PushInTransition extends Transition.In {
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
  }
  
  public void start() {
    Timeline timeline = getAnimator().sequence(1000.0F, 1.0F, (TweenEquation)TweenEquations.QUART_OUT).getTimeline();
    start(timeline);
  }
  
  public void pre(@NonNull UI ui, double mouseX, double mouseY) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    GL11.glPushMatrix();
    GL11.glTranslated(0.0D, (1080.0F * (1.0F - getAnimator().getValue())), 0.0D);
  }
  
  public void post(@NonNull UI ui, double mouseX, double mouseY) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\kit\transition\ShopSlideDownTransition$PushInTransition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */