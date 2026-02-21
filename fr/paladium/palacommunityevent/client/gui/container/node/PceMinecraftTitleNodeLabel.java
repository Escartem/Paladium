package fr.paladium.palacommunityevent.client.gui.container.node;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

public class PceMinecraftTitleNodeLabel extends ANode {
  private final String text;
  
  private int fontSize;
  
  private Color color;
  
  private Color shadowColor;
  
  public String getText() {
    return this.text;
  }
  
  public int getFontSize() {
    return this.fontSize;
  }
  
  public Color getColor() {
    return this.color;
  }
  
  public Color getShadowColor() {
    return this.shadowColor;
  }
  
  public PceMinecraftTitleNodeLabel(double x, double y, String text, String color, String colorShadow) {
    super(x, y, 0.0D, 0.0D);
    this.text = text;
    this.fontSize = 330;
    this.color = Color.decode("#" + color);
    this.shadowColor = Color.decode("#" + colorShadow);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawStringWithCustomFont(mc, this.text, this.x, this.y + this.ui.height(0.37F), this.shadowColor, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), this.fontSize);
    GuiUtils.drawStringWithCustomFont(mc, this.text, this.x, this.y, this.color, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), this.fontSize);
  }
  
  public PceMinecraftTitleNodeLabel setColor(Color color, Color shadowColor) {
    this.color = color;
    this.shadowColor = shadowColor;
    return this;
  }
  
  public PceMinecraftTitleNodeLabel setFontSize(int fontSize) {
    this.fontSize = fontSize;
    return this;
  }
  
  public boolean onClick(int mouseX, int mouseY, int mouseButton) {
    return false;
  }
  
  public void onRelease(int mouseX, int mouseY, int mouseButton) {}
  
  public void onKeyTyped(char c, int key) {}
  
  public void onHover(int mouseX, int mouseY) {}
  
  public void onHoverOut(int mouseX, int mouseY) {}
  
  public void fixedUpdate() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\container\node\PceMinecraftTitleNodeLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */