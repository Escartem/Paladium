package fr.paladium.palamod.modules.trixium.gui.container;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
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
    GuiUtils.drawRoundedRect(this.x, this.y, Color.WHITE, this.width, this.height, height(10.0F));
    UITrixiumContainer.this.drawFullyCenteredString("Classement", this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(99, 99, 99), Fonts.MONTSERRAT_BOLD.getFont(), 50);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\container\UITrixiumContainer$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */