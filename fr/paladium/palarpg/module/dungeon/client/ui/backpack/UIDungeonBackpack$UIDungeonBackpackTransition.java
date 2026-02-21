package fr.paladium.palarpg.module.dungeon.client.ui.backpack;

import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.transition.Transition;
import lombok.NonNull;
import org.lwjgl.opengl.GL11;

class UIDungeonBackpackTransition extends Transition {
  public UIDungeonBackpackTransition() {
    super(new UIDungeonBackpackInTransition(null), new UIDungeonBackpackOutTransition(null));
  }
  
  private static class UIDungeonBackpackInTransition extends Transition.In {
    private UIDungeonBackpackInTransition() {}
    
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
      GL11.glTranslated(0.0D, (1080.0F * (1.0F - getAnimator().getValue())), 0.0D);
    }
    
    public void post(@NonNull UI ui, double mouseX, double mouseY) {
      if (ui == null)
        throw new NullPointerException("ui is marked non-null but is null"); 
      GL11.glPopMatrix();
    }
  }
  
  private static class UIDungeonBackpackOutTransition extends Transition.Out {
    private UIDungeonBackpackOutTransition() {}
    
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
      GL11.glTranslated(0.0D, (1080.0F * (1.0F - getAnimator().getValue())), 0.0D);
    }
    
    public void post(@NonNull UI ui, double mouseX, double mouseY) {
      if (ui == null)
        throw new NullPointerException("ui is marked non-null but is null"); 
      GL11.glPopMatrix();
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\backpack\UIDungeonBackpack$UIDungeonBackpackTransition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */