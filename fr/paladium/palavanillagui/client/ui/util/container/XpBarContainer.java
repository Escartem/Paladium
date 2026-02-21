package fr.paladium.palavanillagui.client.ui.util.container;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.align.Align;
import net.minecraft.client.Minecraft;

public class XpBarContainer extends Node {
  private static final Color LIGHT_GREEN = new Color(57, 255, 101);
  
  private static final Color DARK_GREEN = new Color(0, 91, 20);
  
  private static final Color DARK_GRAY = new Color(26, 26, 26);
  
  private static final Color GRAY = new Color(50, 50, 50);
  
  private final TextInfo levelTextInfo = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 19.0F).color(LIGHT_GREEN).shadow(DARK_GREEN);
  
  protected XpBarContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    double xpPercent = (Minecraft.func_71410_x()).field_71439_g.field_71106_cc;
    int xplevel = (Minecraft.func_71410_x()).field_71439_g.field_71068_ca;
    String str = "LV." + xplevel;
    double strWidth = this.levelTextInfo.getWidth(str) + 10.0D;
    double firstBarPercent = (xpPercent <= 0.5D) ? (xpPercent * 2.0D) : 1.0D;
    double secondBarPercent = (xpPercent > 0.5D) ? ((xpPercent - 0.5D) * 2.0D) : 0.0D;
    double barWidth = getWidth() / 2.0D - strWidth / 2.0D;
    DrawUtils.SHAPE.drawRect(getX(), getY(), barWidth, getHeight() - 4.0D, GRAY);
    DrawUtils.SHAPE.drawRect(getX(), getY(), barWidth * firstBarPercent, getHeight() - 4.0D, LIGHT_GREEN);
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() - 4.0D, barWidth, 4.0D, DARK_GRAY);
    DrawUtils.SHAPE.drawRect(getX(), getY() + getHeight() - 4.0D, barWidth * firstBarPercent, 4.0D, DARK_GREEN);
    DrawUtils.TEXT.drawText(dw(2.0D), getY() - 14.0D, str, this.levelTextInfo, Align.CENTER, Align.START);
    DrawUtils.SHAPE.drawRect(getX() + barWidth + strWidth + 1.0D, getY(), barWidth - 1.0D, getHeight() - 4.0D, GRAY);
    DrawUtils.SHAPE.drawRect(getX() + barWidth + strWidth + 1.0D, getY(), (barWidth - 1.0D) * secondBarPercent, getHeight() - 4.0D, LIGHT_GREEN);
    DrawUtils.SHAPE.drawRect(getX() + barWidth + strWidth + 1.0D, getY() + getHeight() - 4.0D, barWidth - 1.0D, 4.0D, DARK_GRAY);
    DrawUtils.SHAPE.drawRect(getX() + barWidth + strWidth + 1.0D, getY() + getHeight() - 4.0D, (barWidth - 1.0D) * secondBarPercent, 4.0D, DARK_GREEN);
  }
  
  public static XpBarContainer create(double x, double y, double width, double height) {
    return new XpBarContainer(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\container\XpBarContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */