package fr.paladium.paladiumui.kit.toggle;

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
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.toggle.ToggleNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class ToggleNode extends ToggleNode<Object, Object> {
  private static final TextInfo TEXT_INFO = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 17.0F).color(Color.WHITE).shadow(new Color(0.0F, 0.0F, 0.0F, 0.4F)).shadow(1.0F, 1.0F);
  
  private Color toggleBackgroundColor = ColorConstant.GRAY;
  
  private Color backBackgroundColor = ColorConstant.GRAY;
  
  private double borderSize = 5.0D;
  
  private TweenAnimator animator;
  
  protected ToggleNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    onChange((node, toggle) -> this.animator.sequence(500.0F, node.isToggle() ? 1.0F : 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).start());
    this.borderSize = width / 17.8D;
  }
  
  @NonNull
  public static ToggleNode create(double x, double y, double width) {
    return new ToggleNode(x, y, width, width * 0.584D);
  }
  
  public void init(UI ui) {
    this.animator = TweenAnimator.create(isToggle() ? 1.0F : 0.0F);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    this.animator.update();
    double toggleSize = aw(-this.borderSize) / 2.0D;
    Color backgroundColor = this.backBackgroundColor.to(this.toggleBackgroundColor, this.animator.getValue());
    DrawUtils.SHAPE.drawRect(getX(), getY(), getWidth(), getHeight(), backgroundColor);
    DrawUtils.SHAPE.drawRect(getX() - this.borderSize, getY() + this.borderSize, this.borderSize, getHeight() - this.borderSize * 2.0D, backgroundColor);
    DrawUtils.SHAPE.drawRect(getX() + getWidth(), getY() + this.borderSize, this.borderSize, getHeight() - this.borderSize * 2.0D, backgroundColor);
    double lineStroke = getHeight() * 0.0769D;
    DrawUtils.SHAPE.drawRect(getX() + toggleSize / 2.0D - lineStroke / 2.0D, getY() + dh(2.0D) - dh(5.0D), lineStroke, dh(2.5D), Color.WHITE);
    double squareSize = dh(3.25D);
    double squareX = getX() + toggleSize + this.borderSize + toggleSize / 2.0D - squareSize / 2.0D;
    double squareY = getY() + dh(2.0D) - squareSize / 2.0D;
    DrawUtils.SHAPE.drawBorder(squareX, squareY, squareX + squareSize, squareY + squareSize, Color.WHITE, getHeight() * 0.05769D);
    float brightness = hoverValue(0.3F);
    Color[] color = new Color[ButtonNode.DISBALED_COLOR.length];
    for (int i = 0; i < color.length; i++)
      color[i] = ButtonNode.DISBALED_COLOR[i].to(ButtonNode.DEFAULT_COLOR[i], this.animator.getValue()); 
    double toggleX = getX() + (toggleSize + this.borderSize) * this.animator.getValue();
    double toggleY = getY() + this.borderSize;
    DrawUtils.SHAPE.drawRect(toggleX, toggleY, toggleSize, toggleSize, color[0]);
    DrawUtils.SHAPE.drawRect(toggleX, toggleY - this.borderSize, toggleSize, this.borderSize, color[1].brighter(brightness));
    DrawUtils.SHAPE.drawRect(toggleX, toggleY + toggleSize, toggleSize, this.borderSize, color[2].brighter(brightness));
    DrawUtils.SHAPE.drawRect(toggleX - this.borderSize, toggleY, this.borderSize, toggleSize, color[3].brighter(brightness));
    DrawUtils.SHAPE.drawRect(toggleX + toggleSize, toggleY, this.borderSize, toggleSize, color[3].brighter(brightness));
    if (getState() != null && getState().getToggle() != null && getState().getBack() != null) {
      Object object = (this.animator.getValue() > 0.5F) ? getState().getToggle() : getState().getBack();
      if (object != null)
        if (object instanceof String) {
          TextInfo info = TEXT_INFO.copy().fontSize((int)dh(3.0D));
          DrawUtils.TEXT.drawText(toggleX + toggleSize / 2.0D, toggleY + toggleSize / 2.0D - info.dh(2.0D), Text.create(object, info, Align.CENTER));
        } else if (object instanceof ResourceLocation) {
          double size = getHeight() * 0.5D;
          DrawUtils.RESOURCE.drawImage(toggleX + toggleSize / 2.0D - size / 2.0D, toggleY + toggleSize / 2.0D - size / 2.0D, size, size, Resource.of((ResourceLocation)object).nearest());
        } else if (object instanceof Resource) {
          double size = getHeight() * 0.5D;
          DrawUtils.RESOURCE.drawImage(toggleX + toggleSize / 2.0D - size / 2.0D, toggleY + toggleSize / 2.0D - size / 2.0D, size, size, (Resource)object);
        }  
    } 
  }
  
  @NonNull
  public <T extends ToggleNode> T backgroundColor(@NonNull Color toggle, @NonNull Color back) {
    if (toggle == null)
      throw new NullPointerException("toggle is marked non-null but is null"); 
    if (back == null)
      throw new NullPointerException("back is marked non-null but is null"); 
    this.toggleBackgroundColor = toggle;
    this.backBackgroundColor = back;
    return (T)this;
  }
  
  @NonNull
  public <T extends ToggleNode> T borderSize(double size) {
    this.borderSize = size;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\toggle\ToggleNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */