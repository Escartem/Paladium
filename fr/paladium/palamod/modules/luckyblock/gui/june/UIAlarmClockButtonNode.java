package fr.paladium.palamod.modules.luckyblock.gui.june;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class UIAlarmClockButtonNode extends AClickableNode {
  private static ResourceLocation BUTTON_UP = new ResourceLocation("palamod", "textures/gui/LuckyBlock/june/alarmclock_up.png");
  
  private static ResourceLocation BUTTON_DOWN = new ResourceLocation("palamod", "textures/gui/LuckyBlock/june/alarmclock_down.png");
  
  public static float BUTTON_WIDTH = 8.072916F;
  
  public static float BUTTON_HEIGHT = 4.8148146F;
  
  private boolean up;
  
  public UIAlarmClockButtonNode(boolean isUp, double x, double y, double width, double height) {
    super(x, y, width, height);
    this.up = isUp;
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawImageTransparent(this.x, this.y, this.up ? BUTTON_UP : BUTTON_DOWN, this.width, this.height, false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\june\UIAlarmClockButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */