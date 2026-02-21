package fr.paladium.palacommunityevent.client.gui.container;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.progressbar.MinecraftProgressBarNode;
import fr.paladium.lib.apollon.utils.Color;

class null extends MinecraftProgressBarNode {
  null(double x0, double x1, double x2, double x3, Color x4) {
    super(x0, x1, x2, x3, x4);
  }
  
  public boolean isHovered(int mouseX, int mouseY) {
    if (UICommunityEvent.this.getNodes().stream().filter(n -> !(n instanceof MinecraftProgressBarNode)).anyMatch(ANode::isHovered))
      return false; 
    return super.isHovered(mouseX, mouseY);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\container\UICommunityEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */