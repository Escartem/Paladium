package fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.node;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palamod.modules.paladium.client.gui.palamenu.mainmenu.UIMainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class MainMenuSelectionNode extends AClickableNode {
  private String text;
  
  private final ResourceLocation texture;
  
  private final int fontSize;
  
  private final boolean selected;
  
  public MainMenuSelectionNode(double x, double y, double width, double height, String text, ResourceLocation texture, int fontSize, boolean selected) {
    super(x, y, width, height);
    this.text = text;
    this.texture = texture;
    this.fontSize = fontSize;
    this.selected = selected;
    setAnimationDuration(0.1F);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    Color color = !this.enabled ? new Color(110, 110, 110) : (this.selected ? new Color(239, 57, 38) : new Color(255 - (int)animationValue(16.0F), 255 - (int)animationValue(198.0F), 255 - (int)animationValue(217.0F)));
    Color shadowColor = !this.enabled ? new Color(73, 73, 73) : (this.selected ? new Color(185, 28, 12) : new Color(159 - (int)animationValue(-26.0F), 159 - (int)animationValue(131.0F), 159 - (int)animationValue(147.0F)));
    GuiUtils.drawStringWithCustomFont(mc, this.text.toUpperCase(), this.x + (this.enabled ? animationValue(width(2.0F)) : 0.0F), this.y + height(5.0F), shadowColor, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), this.fontSize);
    GuiUtils.drawStringWithCustomFont(mc, this.text.toUpperCase(), this.x + (this.enabled ? animationValue(width(2.0F)) : 0.0F), this.y, color, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), this.fontSize);
  }
  
  public void onHover(int mouseX, int mouseY) {
    super.onHover(mouseX, mouseY);
    if (!this.enabled)
      return; 
    ((UIMainMenu)this.ui).setFocused(this.texture, (float)(this.y + height(30.0F)));
  }
  
  public void setText(String text) {
    this.text = text;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\mainmenu\node\MainMenuSelectionNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */