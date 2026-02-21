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
    GuiUtils.drawRoundedRect(this.x, this.y, new Color(48 + (int)animationValue(207.0F), 217 + (int)animationValue(38.0F), 110 + (int)animationValue(145.0F)), this.width, this.height, width(2.0F));
    UIHalloweenTradeClose.this.drawFullyCenteredString("compris".toUpperCase(), this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(255 - (int)animationValue(207.0F), 255 - (int)animationValue(38.0F), 255 - (int)animationValue(145.0F)), Fonts.MONTSERRAT_BOLD.getFont(), 70);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\halloween\UIHalloweenTradeClose$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */