package fr.paladium.palajobs.client.ui.lvltokens.nodes;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

public class ButtonJobRewardChoice extends AClickableNode {
  private Color TOP_COLOR = Color.decode("#FF847B");
  
  private Color LEFT_COLOR = Color.decode("#DD2B1D");
  
  private Color RIGHT_COLOR = Color.decode("#DD2B1D");
  
  private Color BOTTOM_COLOR = Color.decode("#92130C");
  
  private Color FILL_COLOR = Color.decode("#EF3926");
  
  private Color TOP_COLOR_DISABLE = Color.decode("#EFEFEF");
  
  private Color LEFT_COLOR_DISABLE = Color.decode("#B4B4B4");
  
  private Color RIGHT_COLOR_DISABLE = Color.decode("#B4B4B4");
  
  private Color BOTTOM_COLOR_DISABLE = Color.decode("#5B5B5B");
  
  private Color FILL_COLOR_DISABLE = Color.decode("#A7A7A7");
  
  private double stroke;
  
  public ButtonJobRewardChoice(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.stroke = width(2.0F);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.enabled ? this.FILL_COLOR.brighter(animationValue(0.3F)) : this.FILL_COLOR_DISABLE);
    GuiUtils.drawRect(this.x, this.y - this.stroke, this.x + this.width, this.y, this.enabled ? this.TOP_COLOR.brighter(animationValue(0.3F)) : this.TOP_COLOR_DISABLE);
    GuiUtils.drawRect(this.x, this.y + this.height, this.x + this.width, this.y + this.height + this.stroke, this.enabled ? this.BOTTOM_COLOR.brighter(animationValue(0.3F)) : this.BOTTOM_COLOR_DISABLE);
    GuiUtils.drawRect(this.x - this.stroke, this.y, this.x, this.y + this.height, this.enabled ? this.LEFT_COLOR.brighter(animationValue(0.3F)) : this.LEFT_COLOR_DISABLE);
    GuiUtils.drawRect(this.x + this.width, this.y, this.x + this.width + this.stroke, this.y + this.height, this.enabled ? this.RIGHT_COLOR.brighter(animationValue(0.3F)) : this.RIGHT_COLOR_DISABLE);
    drawContent(mc, mouseX, mouseY);
  }
  
  public void drawContent(Minecraft mc, int mouseX, int mouseY) {
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.PIXEL_NES.getFont(), 30);
    GuiUtils.drawCenteredStringWithCustomFont(mc, "CONFIRMER", this.x + this.width / 2.0D, this.y + this.height / 2.0D - (fontHeight / 2), Color.WHITE, Fonts.PIXEL_NES.getFont(), 30, true, Color.BLACK);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\lvltokens\nodes\ButtonJobRewardChoice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */