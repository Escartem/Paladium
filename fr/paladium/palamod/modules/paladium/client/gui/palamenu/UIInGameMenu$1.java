package fr.paladium.palamod.modules.paladium.client.gui.palamenu;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.AnimatedResourceLocation;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

class null extends AClickableNode {
  null(double x0, double x1, double x2, double x3) {
    super(x0, x1, x2, x3);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    if (isHovered(mouseX, mouseY)) {
      GuiUtils.drawImageTransparent(this.x, this.y, UIInGameMenu.access$000(), this.width, this.height, false);
    } else {
      AnimatedResourceLocation.from(UIInGameMenu.access$100()).bind(mc.field_71439_g.field_70173_aa);
      GuiUtils.drawRectWithNoBinding(this.x, this.y, this.width, this.height);
    } 
    GuiUtils.drawCenteredStringWithCustomFont(mc, "Boutique", this.x + width(50.0F), this.y + height(40.0F), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 30);
    GuiUtils.drawImageTransparent(this.x + width(94.0F), this.y - width(2.0F), UIInGameMenu.access$200(), width(25.0F), (width(25.0F) / 3.0F), false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\UIInGameMenu$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */