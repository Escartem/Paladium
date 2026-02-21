package fr.paladium.paladiumui.kit.sw;

import fr.paladium.paladiumui.kit.button.ButtonNode;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.sw.SwitchNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import lombok.NonNull;

public class SwitchNode extends SwitchNode {
  private static final TextInfo TEXT_INFO = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 0.0F, Color.WHITE).shadow(Color.BLACK.copyAlpha(0.4F)).shadow(1.0F, 1.0F);
  
  private TweenAnimator animator;
  
  protected SwitchNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static SwitchNode create(double x, double y, double width, double height) {
    return new SwitchNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    double stateWidth = getWidth() / getStateList().size();
    if (this.animator == null) {
      this.animator = TweenAnimator.create((float)(((Integer)getStateIndex().getOrDefault()).intValue() * stateWidth));
    } else {
      this.animator.sequence(500.0F, (float)(((Integer)getStateIndex().getOrDefault()).intValue() * stateWidth), (TweenEquation)TweenEquations.QUART_INOUT).start();
    } 
    RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(ColorConstant.GRAY)
      .border(ColorConstant.GRAY, 5.0D, false)
      .zindex(-1)
      .attach((Node)this);
    TextInfo textInfo = TEXT_INFO.copy().fontSize((int)(getHeight() * 0.4D));
    FlexNode.horizontal(0.0D, 0.0D, getHeight())
      .body(flex -> {
          for (String state : getStateList().getOrDefault())
            RectNode.create(0.0D, 0.0D, stateWidth, getHeight()).color(Color.TRANSPARENT).body(()).onClick(()).attach(flex); 
        }).attach((Node)this);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    this.animator.update();
    double stateWidth = getWidth() / getStateList().size();
    double x = getX() + this.animator.getValue();
    DrawUtils.SHAPE.drawRect(x, getY(), stateWidth, getHeight(), ButtonNode.DEFAULT_COLOR[0]);
    DrawUtils.SHAPE.drawRect(x, getY() - 5.0D, stateWidth, 5.0D, ButtonNode.DEFAULT_COLOR[1]);
    DrawUtils.SHAPE.drawRect(x, getY() + getHeight(), stateWidth, 5.0D, ButtonNode.DEFAULT_COLOR[2]);
    DrawUtils.SHAPE.drawRect(x - 5.0D, getY(), 5.0D, getHeight(), ButtonNode.DEFAULT_COLOR[3]);
    DrawUtils.SHAPE.drawRect(x + stateWidth, getY(), 5.0D, getHeight(), ButtonNode.DEFAULT_COLOR[3]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\sw\SwitchNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */