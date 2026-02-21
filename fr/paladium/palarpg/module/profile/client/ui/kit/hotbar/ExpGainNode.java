package fr.paladium.palarpg.module.profile.client.ui.kit.hotbar;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palarpg.module.profile.client.ui.overlay.UIOverlayRPGHotBar;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.TextElement;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class ExpGainNode extends Node {
  private final TweenAnimator opacityAnimator = TweenAnimator.create(0.0F);
  
  private final TweenAnimator translateAnimator = TweenAnimator.create(1.0F);
  
  private double exp;
  
  private ExpGainNode(double x, double y) {
    super(x, y);
  }
  
  public static ExpGainNode create(double x, double y) {
    return new ExpGainNode(x, y);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (ui instanceof UIOverlayRPGHotBar) {
      UIOverlayRPGHotBar hotbar = (UIOverlayRPGHotBar)ui;
      if (((Double)hotbar.getExpGainSignal().getOrDefault()).doubleValue() == 0.0D)
        return; 
      this.exp = ((Double)hotbar.getExpGainSignal().getOrDefault()).doubleValue();
    } 
    this.opacityAnimator.clear();
    Timeline opacityTimeline = Timeline.createSequence();
    opacityTimeline.push(Tween.to(this.opacityAnimator, 0, 1000.0F).ease((TweenEquation)TweenEquations.QUART_INOUT).target(1.0F));
    opacityTimeline.pushPause(3000.0F);
    opacityTimeline.push(Tween.to(this.opacityAnimator, 0, 500.0F).ease((TweenEquation)TweenEquations.QUART_INOUT).target(0.0F));
    this.opacityAnimator.setTimeline(opacityTimeline);
    this.opacityAnimator.start();
    this.translateAnimator.clear();
    this.translateAnimator.setValue(1.0F);
    this.translateAnimator.sequence(1000.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    this.opacityAnimator.update();
    this.translateAnimator.update();
    Text text = Text.create("Salle terminé ", TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.WHITE.copyAlpha(this.opacityAnimator.getValue()))).add(TextElement.create("+" + String.format("%.0f", new Object[] { Double.valueOf(this.exp) }), TextInfo.create((IFont)PaladiumFont.MINECRAFT, 12.0F, Color.decode("#00E397").copyAlpha(this.opacityAnimator.getValue())))).align(Align.END, Align.START);
    DrawUtils.TEXT.drawText(getX(), getY() + 10.0D * this.translateAnimator.getValue(), text);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\kit\hotbar\ExpGainNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */