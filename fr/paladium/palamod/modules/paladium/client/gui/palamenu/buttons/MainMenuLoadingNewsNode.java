package fr.paladium.palamod.modules.paladium.client.gui.palamenu.buttons;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

public class MainMenuLoadingNewsNode extends AClickableNode {
  public MainMenuLoadingNewsNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    int animation = 0;
    int duration = (int)(System.currentTimeMillis() % 2000L);
    if (duration < 1000) {
      animation = (int)(duration / 1000.0F * 10.0F);
    } else {
      animation = 10 - (int)((duration - 1000.0F) / 1000.0F * 10.0F);
    } 
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(150 + animation, 150 + animation, 150 + animation));
  }
  
  public boolean isHovered(int mouseX, int mouseY) {
    for (ANode child : getChildren()) {
      if (child.isHovered(mouseX, mouseY))
        return true; 
    } 
    return super.isHovered(mouseX, mouseY);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\buttons\MainMenuLoadingNewsNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */