package fr.paladium.palavoicechat.client.ui.node;

import fr.paladium.palavoicechat.PalaVoiceChatMod;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.palavoicechat.utils.Utils;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slider.SliderCursorNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.slider.SliderNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import java.util.function.Supplier;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class MicrophoneActivationNode extends SliderNode<Double> {
  private static final Color BACKGROUND = Color.decode("#171717");
  
  private static final Color BACKGROUND_COLOR = Color.decode("#282828");
  
  private static final Color FOREGROUND_COLOR = Color.decode("#1F1F1F");
  
  private static final Color GREEN_FILLER = Color.decode("#00E357");
  
  private static final Color GREEN_FILLER_LOWER = Color.decode("#008232");
  
  private static final Color ORANGE_FILLER = Color.decode("#FF6B01");
  
  private static final Color ORANGE_FILLER_LOWER = Color.decode("#7E280D");
  
  private Supplier<Boolean> hoverable = () -> Boolean.valueOf(true);
  
  protected MicrophoneActivationNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    cursor(new MicrophoneActivationCursorNode(4.0D, 22.0D));
  }
  
  public static MicrophoneActivationNode create(double x, double y, double width, double height) {
    return new MicrophoneActivationNode(x, y, width, height);
  }
  
  public void drawSlider(double mouseX, double mouseY, float ticks) {
    DrawUtils.SHAPE.drawBorder(getX(), getY() - 4.0D, getX() + w(), getY() - 4.0D + h() + 8.0D, BACKGROUND, 6.0D);
    DrawUtils.SHAPE.drawRect(getX(), getY() - 4.0D, w(), h() + 8.0D, BACKGROUND);
    double amplitude = IoNettyVoIPClient.getInstance().getTalkCache().getAmplitude((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
    double activation = Utils.dbToPerc(PalaVoiceChatMod.getClientProxy().getClientConfig().getVoiceActivationThreshold());
    double filledWidth = w() * amplitude;
    double activationWidth = w() * activation;
    int i = 0;
    while (true) {
      double barX;
      Color barColor, barLowerColor;
      if (i < 43) {
        barX = getX() + i * 5.0D;
        double barEndX = barX + 3.0D;
        barColor = BACKGROUND_COLOR;
        barLowerColor = FOREGROUND_COLOR;
        if (barX < getX() + activationWidth) {
          if (barEndX > getX() + activationWidth) {
            double orangeWidth = getX() + activationWidth - barX;
            DrawUtils.SHAPE.drawRect(barX, getY(), orangeWidth, 13.0D, ORANGE_FILLER);
            DrawUtils.SHAPE.drawRect(barX, getY() + 13.0D, orangeWidth, 1.0D, ORANGE_FILLER_LOWER);
            DrawUtils.SHAPE.drawRect(barX + orangeWidth, getY(), 3.0D - orangeWidth, 13.0D, BACKGROUND_COLOR);
            DrawUtils.SHAPE.drawRect(barX, getY() + 13.0D, 3.0D, 1.0D, FOREGROUND_COLOR);
            continue;
          } 
          barColor = ORANGE_FILLER;
          barLowerColor = ORANGE_FILLER_LOWER;
        } 
        if (barX < getX() + filledWidth) {
          if (barEndX > getX() + filledWidth) {
            double greenWidth = getX() + filledWidth - barX;
            DrawUtils.SHAPE.drawRect(barX, getY(), greenWidth, 13.0D, GREEN_FILLER);
            DrawUtils.SHAPE.drawRect(barX, getY() + 13.0D, greenWidth, 1.0D, GREEN_FILLER_LOWER);
            DrawUtils.SHAPE.drawRect(barX + greenWidth, getY(), 3.0D - greenWidth, 13.0D, BACKGROUND_COLOR);
            DrawUtils.SHAPE.drawRect(barX, getY() + 13.0D, 3.0D, 1.0D, FOREGROUND_COLOR);
          } else {
            barColor = GREEN_FILLER;
            barLowerColor = GREEN_FILLER_LOWER;
            DrawUtils.SHAPE.drawRect(barX, getY(), 3.0D, 13.0D, barColor);
            DrawUtils.SHAPE.drawRect(barX, getY() + 13.0D, 3.0D, 1.0D, barLowerColor);
          } 
          continue;
        } 
      } else {
        break;
      } 
      DrawUtils.SHAPE.drawRect(barX, getY(), 3.0D, 13.0D, barColor);
      DrawUtils.SHAPE.drawRect(barX, getY() + 13.0D, 3.0D, 1.0D, barLowerColor);
      i++;
    } 
  }
  
  public MicrophoneActivationNode hoverable(Supplier<Boolean> hoverable) {
    this.hoverable = hoverable;
    return this;
  }
  
  public boolean isHovered(double mouseX, double mouseY, boolean checkEnabled) {
    if (!((Boolean)this.hoverable.get()).booleanValue())
      return false; 
    return super.isHovered(mouseX, mouseY, checkEnabled);
  }
  
  public void mousePressed(double mouseX, double mouseY, @NonNull ClickType clickType, @NonNull InternalContext context) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (!((Boolean)this.hoverable.get()).booleanValue())
      return; 
    super.mousePressed(mouseX, mouseY, clickType, context);
  }
  
  private final class MicrophoneActivationCursorNode extends SliderCursorNode {
    protected MicrophoneActivationCursorNode(double width, double height) {
      super(width, height);
    }
    
    public void drawCursor(double mouseX, double mouseY, float ticks) {
      DrawUtils.SHAPE.drawRect(getX() - dw(2.0D), getY() - 4.0D, getWidth(), getHeight(), Color.WHITE);
      DrawUtils.SHAPE.drawRect(getX() - dw(2.0D), getY() - 4.0D + getHeight(), getWidth(), 1.0D, Color.WHITE.darker());
    }
    
    public boolean isHovered(double mouseX, double mouseY, boolean checkEnabled) {
      if (!((Boolean)MicrophoneActivationNode.this.hoverable.get()).booleanValue())
        return false; 
      return super.isHovered(mouseX, mouseY, checkEnabled);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\node\MicrophoneActivationNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */