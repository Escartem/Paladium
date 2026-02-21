package fr.paladium.palamod.modules.paladium.client.gui.palamenu.buttons;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class MainMenuNewsNode extends AClickableNode {
  private AtomicReference<ResourceLocation> texture;
  
  public MainMenuNewsNode(double x, double y, double width, double height, String textureUrl) {
    super(x, y, width, height);
    this.texture = downloadImage(textureUrl);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    if (this.texture.get() != null)
      GuiUtils.drawImageTransparent(this.x, this.y, animationValue(width(5.0F)), animationValue(height(5.0F)), 
          width(100.0F), height(100.0F), (width(100.0F) + animationValue(width(10.0F))), (
          height(100.0F) + animationValue(height(10.0F))), this.texture.get(), Color.WHITE, 1.0F); 
  }
  
  public boolean isHovered(int mouseX, int mouseY) {
    for (ANode child : getChildren()) {
      if (child.isHovered(mouseX, mouseY))
        return true; 
    } 
    return super.isHovered(mouseX, mouseY);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\buttons\MainMenuNewsNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */