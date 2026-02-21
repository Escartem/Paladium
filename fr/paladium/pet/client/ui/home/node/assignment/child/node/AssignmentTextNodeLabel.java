package fr.paladium.pet.client.ui.home.node.assignment.child.node;

import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.client.ui.home.node.assignment.child.AssignmentNode;
import net.minecraft.client.Minecraft;

public class AssignmentTextNodeLabel extends TextNodeLabel {
  private AssignmentNode parentNode;
  
  public AssignmentTextNodeLabel(double x, double y, String text, FontObj font, int fontSize, Color color, AssignmentNode parentNode) {
    super(x, y, text, font, fontSize, color);
    this.parentNode = parentNode;
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    double parentX = this.parentNode.x;
    double parentY = this.parentNode.y;
    double parentWidth = this.parentNode.width;
    double parentHeight = this.parentNode.height;
    if (this.parentNode.getData().isFinish()) {
      GuiUtils.drawRect(parentX, parentY, parentX + parentWidth, parentY + parentHeight, new Color(AssignmentNode.GREEN_FINISH_COLOR.r, AssignmentNode.GREEN_FINISH_COLOR.g, AssignmentNode.GREEN_FINISH_COLOR.b, 0.3F));
      double logoWidth = this.parentNode.width(18.888F);
      double logoHeight = this.parentNode.width(18.888F);
      GuiUtils.drawImageTransparent(parentX + this.parentNode.width(40.0F), parentY + this.parentNode.height(30.0F), AssignmentNode.UNION_LOGO, logoWidth, logoHeight);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\node\assignment\child\node\AssignmentTextNodeLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */