package fr.paladium.palajobs.client.ui.node.calltoaction;

import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class MinecraftIconCallToActionNode extends MinecraftCallToActionNode {
  private ResourceLocation icon;
  
  public MinecraftIconCallToActionNode(double x, double y, double width, double height, double stroke, ResourceLocation icon) {
    super(x, y, width, height, stroke);
    this.icon = icon;
  }
  
  public void drawContent(Minecraft mc, int mouseX, int mouseY) {
    if (!this.enabled)
      GuiUtils.greyLoad(true); 
    GuiUtils.drawImageTransparent(this.x + this.width / 4.0D, this.y + this.height / 4.0D, this.icon, this.width / 2.0D, this.height / 2.0D);
    if (!this.enabled)
      GuiUtils.greyUnload(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\node\calltoaction\MinecraftIconCallToActionNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */