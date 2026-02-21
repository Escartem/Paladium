package fr.paladium.palajobs.client.ui.node.calltoaction;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

public abstract class MinecraftCallToActionNode extends AClickableNode {
  private final Color TOP_COLOR = Color.decode("#FF847B");
  
  private final Color LEFT_COLOR = Color.decode("#DD2B1D");
  
  private final Color RIGHT_COLOR = Color.decode("#DD2B1D");
  
  private final Color BOTTOM_COLOR = Color.decode("#92130C");
  
  private final Color FILL_COLOR = Color.decode("#EF3926");
  
  private final Color TOP_COLOR_DISABLE = Color.decode("#EFEFEF");
  
  private final Color LEFT_COLOR_DISABLE = Color.decode("#B4B4B4");
  
  private final Color RIGHT_COLOR_DISABLE = Color.decode("#B4B4B4");
  
  private final Color BOTTOM_COLOR_DISABLE = Color.decode("#5B5B5B");
  
  private final Color FILL_COLOR_DISABLE = Color.decode("#A7A7A7");
  
  private final double stroke;
  
  public MinecraftCallToActionNode(double x, double y, double width, double height, double stroke) {
    super(x, y, width, height);
    this.stroke = stroke;
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
  
  public abstract void drawContent(Minecraft paramMinecraft, int paramInt1, int paramInt2);
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\node\calltoaction\MinecraftCallToActionNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */