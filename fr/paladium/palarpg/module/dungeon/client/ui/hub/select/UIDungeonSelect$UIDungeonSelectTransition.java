package fr.paladium.palarpg.module.dungeon.client.ui.hub.select;

import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.transition.Transition;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

class UIDungeonSelectTransition extends Transition {
  public UIDungeonSelectTransition() {
    super(new UIDungeonSelectInTransition(null), new UIDungeonSelectOutTransition(null));
  }
  
  private static class UIDungeonSelectInTransition extends Transition.In {
    private UIDungeonSelectInTransition() {}
    
    public void init(@NonNull UI ui) {
      if (ui == null)
        throw new NullPointerException("ui is marked non-null but is null"); 
    }
    
    public void start() {
      start(getAnimator().sequence(500.0F, 1.0F, (TweenEquation)TweenEquations.QUART_OUT).getTimeline());
    }
    
    public void pre(@NonNull UI ui, double mouseX, double mouseY) {
      if (ui == null)
        throw new NullPointerException("ui is marked non-null but is null"); 
      GL11.glPushMatrix();
      GL11.glTranslated((-1920.0F * (1.0F - getAnimator().getValue())), 0.0D, 0.0D);
    }
    
    public void post(@NonNull UI ui, double mouseX, double mouseY) {
      if (ui == null)
        throw new NullPointerException("ui is marked non-null but is null"); 
      GL11.glPopMatrix();
    }
  }
  
  private static class UIDungeonSelectOutTransition extends Transition.Out {
    private UIDungeonSelectOutTransition() {}
    
    public void init(@NonNull UI ui) {
      if (ui == null)
        throw new NullPointerException("ui is marked non-null but is null"); 
    }
    
    public void start() {
      start(getAnimator().sequence(500.0F, 0.0F, (TweenEquation)TweenEquations.QUART_IN).getTimeline());
    }
    
    public void pre(@NonNull UI ui, double mouseX, double mouseY) {
      if (ui == null)
        throw new NullPointerException("ui is marked non-null but is null"); 
      GL11.glPushMatrix();
      GL11.glTranslated((-1920.0F * (1.0F - getAnimator().getValue())), 0.0D, 0.0D);
    }
    
    public void post(@NonNull UI ui, double mouseX, double mouseY) {
      if (ui == null)
        throw new NullPointerException("ui is marked non-null but is null"); 
      GL11.glPopMatrix();
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\hub\select\UIDungeonSelect$UIDungeonSelectTransition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */