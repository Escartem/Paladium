package fr.paladium.palavoicechat.client.ui.node;

import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.ui.node.Node;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class MicrophoneTestingNode extends Node {
  private static final Color BACKGROUND_COLOR = Color.decode("#282828");
  
  private static final Color FOREGROUND_COLOR = Color.decode("#1F1F1F");
  
  protected MicrophoneTestingNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static MicrophoneTestingNode create(double x, double y, double width, double height) {
    return new MicrophoneTestingNode(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    double amplitude = IoNettyVoIPClient.getInstance().isTestingMic() ? IoNettyVoIPClient.getInstance().getTalkCache().getAmplitude((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g) : 0.0D;
    double filledWidth = w() * amplitude;
    int i = 0;
    while (true) {
      double barX;
      Color barColor;
      if (i < 43) {
        barX = getX() + i * 5.0D;
        double barEndX = barX + 3.0D;
        barColor = BACKGROUND_COLOR;
        if (barX < getX() + filledWidth) {
          if (barEndX > getX() + filledWidth) {
            double greenWidth = getX() + filledWidth - barX;
            DrawUtils.SHAPE.drawRect(barX, getY(), greenWidth, 13.0D, Color.GREEN);
            DrawUtils.SHAPE.drawRect(barX, getY() + 13.0D, greenWidth, 1.0D, Color.GREEN.darker());
            DrawUtils.SHAPE.drawRect(barX + greenWidth, getY(), 3.0D - greenWidth, 13.0D, BACKGROUND_COLOR);
            DrawUtils.SHAPE.drawRect(barX, getY() + 13.0D, 3.0D, 1.0D, FOREGROUND_COLOR);
          } else {
            barColor = Color.GREEN;
            DrawUtils.SHAPE.drawRect(barX, getY(), 3.0D, 13.0D, barColor);
            DrawUtils.SHAPE.drawRect(barX, getY() + 13.0D, 3.0D, 1.0D, FOREGROUND_COLOR);
          } 
          continue;
        } 
      } else {
        break;
      } 
      DrawUtils.SHAPE.drawRect(barX, getY(), 3.0D, 13.0D, barColor);
      DrawUtils.SHAPE.drawRect(barX, getY() + 13.0D, 3.0D, 1.0D, FOREGROUND_COLOR);
      i++;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\clien\\ui\node\MicrophoneTestingNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */