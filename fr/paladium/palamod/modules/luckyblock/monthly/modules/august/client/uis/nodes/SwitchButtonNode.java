package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.uis.nodes;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class SwitchButtonNode extends AClickableNode {
  private static final String TEXTURES_PATH = "palamod:textures/gui/LuckyBlock/august/buttons/";
  
  public void setResource(ResourceLocation resource) {
    this.resource = resource;
  }
  
  public static final ResourceLocation EMPTY = new ResourceLocation("palamod:textures/gui/LuckyBlock/august/buttons/empty.png");
  
  public static final ResourceLocation RIGHT = new ResourceLocation("palamod:textures/gui/LuckyBlock/august/buttons/right.png");
  
  public static final ResourceLocation LEFT = new ResourceLocation("palamod:textures/gui/LuckyBlock/august/buttons/left.png");
  
  private ResourceLocation resource;
  
  public ResourceLocation getResource() {
    return this.resource;
  }
  
  public SwitchButtonNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    this.resource = RIGHT;
  }
  
  public void onHover(int mouseX, int mouseY) {
    super.onHover(mouseX, mouseY);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawImageTransparent(this.x, this.y, this.resource, this.width, this.height, true);
  }
  
  public boolean switchType(int mouseX, int mouseY, int buttonId) {
    if (this.resource == LEFT && buttonId == 1)
      return false; 
    if (this.resource == RIGHT && buttonId == 0)
      return false; 
    if (this.resource == EMPTY) {
      this.resource = RIGHT;
    } else if (this.resource == RIGHT) {
      this.resource = LEFT;
    } else if (this.resource == LEFT) {
      this.resource = RIGHT;
    } 
    return true;
  }
  
  public boolean isClickedOnCorrectSide(int mouseX, int mouseY) {
    if (this.resource == EMPTY)
      return true; 
    if (this.resource == RIGHT)
      return (mouseX >= this.x + this.width / 2.0D); 
    return (mouseX <= this.x + this.width / 2.0D);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\clien\\uis\nodes\SwitchButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */