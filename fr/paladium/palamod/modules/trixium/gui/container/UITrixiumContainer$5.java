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
  
  public void draw(Minecraft arg0, int arg1, int arg2) {
    GuiUtils.drawCenteredStringWithCustomFont(UITrixiumContainer.this.field_146297_k, "déposer votre trixium".toUpperCase(), this.width / 2.0D, height(24.44F), Color.WHITE, Fonts.MONTSERRAT_EXTRABOLD.getFont(), 120);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\container\UITrixiumContainer$5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */