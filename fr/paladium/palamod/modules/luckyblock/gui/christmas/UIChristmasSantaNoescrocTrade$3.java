package fr.paladium.palamod.modules.luckyblock.gui.christmas;

import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

class null extends ClickableNodeChristmasSantaNoescrocTrade {
  null(UIChristmasSantaNoescrocTrade associatedUI, int step, int itemID, double x, double y, double width, double height) {
    super(associatedUI, step, itemID, x, y, width, height);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRoundedRect(this.x, this.y, new Color(162 + (int)animationValue(93.0F), 162 + (int)animationValue(93.0F), 162 + (int)animationValue(93.0F)), this.width, this.height, width(2.0F));
    UIChristmasSantaNoescrocTrade.this.drawFullyCenteredString("non".toUpperCase(), this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(255 - (int)animationValue(93.0F), 255 - (int)animationValue(93.0F), 255 - (int)animationValue(93.0F)), Fonts.MONTSERRAT_BOLD.getFont(), 70);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\gui\christmas\UIChristmasSantaNoescrocTrade$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */