package fr.paladium.palarpg.module.profile.client.ui.overlay;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.Timeline;
import fr.paladium.zephyrui.lib.animation.tweenengine.Tween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.UIDataOverlay;
import fr.paladium.zephyrui.lib.ui.core.data.overlay.render.UIDataOverlayRender;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

@UIData(background = false, anchorX = Align.END, anchorY = Align.START)
@UIDataOverlay(active = true, render = @UIDataOverlayRender(post = true, always = true))
public class UIOverlayRPGTip extends UI {
  private final String text;
  
  private final TweenAnimator appearAnimator;
  
  public UIOverlayRPGTip(@NonNull String text) {
    if (text == null)
      throw new NullPointerException("text is marked non-null but is null"); 
    this.text = text;
    this.appearAnimator = TweenAnimator.create(0.0F);
  }
  
  public void init() {
    Timeline sequence = Timeline.createSequence();
    sequence.push(Tween.to(this.appearAnimator, 0, 1000.0F).target(1.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
    sequence.pushPause(7500.0F);
    sequence.push(Tween.to(this.appearAnimator, 0, 1000.0F).target(0.0F).ease((TweenEquation)TweenEquations.QUART_INOUT));
    this.appearAnimator.setValue(0.0F);
    this.appearAnimator.setTimeline(sequence);
    this.appearAnimator.setCallback(tween -> ZUI.close(this))
      
      .start();
    ContainerNode.create(500.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> {
          ((ImageNode)ImageNode.create(0.0D, 0.0D, container.w(), container.h()).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/tip/background.png")).blocking().linear()).color(Color.WHITE.copyAlpha(0.0F)).onDraw(())).attach(container);
          TextNode.create(1760.0D, 46.0D).text(Text.create("astuce", TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 24.0F, Color.WHITE))).attach(container);
          TextNode.create(1520.0D, 115.0D).text(Text.create(this.text, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 18.0F, Color.WHITE)).horizontalAlign(Align.END)).mode(TextMode.SPLIT).width(350.0D).attach(container);
        }).onAnimate((node, animator, value) -> node.x((500.0F * (1.0F - value))))
      .animate(this.appearAnimator)
      .attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\clien\\ui\overlay\UIOverlayRPGTip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */