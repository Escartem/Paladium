package fr.paladium.palamod.modules.luckyblock.gui.halloween;

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
    Color primaryColor = (UIHalloweenTrade.access$000(UIHalloweenTrade.this).getRemainingCosmetics() > 0) ? new Color(48, 217, 110) : new Color(84, 93, 87);
    if (UIHalloweenTrade.access$000(UIHalloweenTrade.this).getRemainingCosmetics() <= 0) {
      this.animationValue = 0.0F;
      getHovers().clear();
      addHover("Plus aucun cosmétique en vente.");
      setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
    } 
    GuiUtils.drawRoundedRect(this.x, this.y, new Color(primaryColor.getRed() + (int)animationValue(255.0F - primaryColor.getRed()), primaryColor.getGreen() + (int)animationValue(255.0F - primaryColor.getGreen()), primaryColor.getBlue() + (int)animationValue(255.0F - primaryColor.getBlue())), this.width, this.height, this.ui.width(0.3F));
    UIHalloweenTrade.this.drawFullyCenteredString("Acheter", this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(255 - (int)animationValue(255.0F - primaryColor.getRed()), 255 - (int)animationValue(255.0F - primaryColor.getGreen()), 255 - (int)animationValue(255.0F - primaryColor.getBlue())), Fonts.MONTSERRAT_BOLD.getFont(), 30);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\halloween\UIHalloweenTrade$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */