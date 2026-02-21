package fr.paladium.palajobs.client.ui.node.calltoaction;

import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

public class MinecraftTextCallToActionNode extends MinecraftCallToActionNode {
  private String text;
  
  public MinecraftTextCallToActionNode(double x, double y, double width, double height, double stroke, String text) {
    super(x, y, width, height, stroke);
    this.text = text;
  }
  
  public void drawContent(Minecraft mc, int mouseX, int mouseY) {
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.PIXEL_NES.getFont(), 30);
    GuiUtils.drawCenteredStringWithCustomFont(mc, this.text, this.x + this.width / 2.0D, this.y + this.height / 2.0D - (fontHeight / 2), Color.WHITE, Fonts.PIXEL_NES.getFont(), 30, true, Color.BLACK);
  }
  
  public void setText(String text) {
    this.text = text;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\node\calltoaction\MinecraftTextCallToActionNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */