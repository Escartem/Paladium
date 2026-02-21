package fr.paladium.palajobs.client.ui.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

public class MinecraftProgressBarNode extends AClickableNode {
  private Color foregroundColor;
  
  private Color foregroundColorDark;
  
  private Color backgroundColor;
  
  private Color backgroundColorDark;
  
  private float percentage;
  
  public MinecraftProgressBarNode(double x, double y, double width, double height, Color color) {
    super(x, y, width, height);
    this.foregroundColor = color;
    this.foregroundColorDark = this.foregroundColor.darker(0.5F);
    this.backgroundColor = new Color(50, 50, 50);
    this.backgroundColorDark = this.backgroundColor.darker(0.5F);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + height(85.0F), this.backgroundColor);
    GuiUtils.drawRect(this.x, this.y + height(85.0F), this.x + this.width, this.y + this.height, this.backgroundColorDark);
    GuiUtils.drawRect(this.x, this.y, this.x + this.width * this.percentage, this.y + height(85.0F), this.foregroundColor);
    GuiUtils.drawRect(this.x, this.y + height(85.0F), this.x + this.width * this.percentage, this.y + this.height, this.foregroundColorDark);
  }
  
  public MinecraftProgressBarNode setPercentage(float percentage) {
    this.percentage = percentage;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\node\MinecraftProgressBarNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */