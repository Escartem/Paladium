package fr.paladium.palamod.modules.trixium.gui;

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
    Color backgroundColor = new Color(48 + (int)animationValue(207.0F), 217 + (int)animationValue(38.0F), 110 + (int)animationValue(145.0F));
    Color textColor = new Color(255 - (int)animationValue(207.0F), 255 - (int)animationValue(38.0F), 255 - (int)animationValue(145.0F));
    GuiUtils.drawRoundedRect(this.x, this.y, backgroundColor, this.width, this.height, height(10.0F));
    UITrixium.this.drawFullyCenteredString("Ajouter du Trixium", this.x + this.width / 2.0D, this.y + this.height / 2.0D, textColor, Fonts.MONTSERRAT_BOLD.getFont(), 50);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\UITrixium$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */