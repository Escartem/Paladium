package fr.paladium.palamod.modules.trixium.gui.container;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

class null extends AClickableNode {
  null(double x0, double x1, double x2, double x3) {
    super(x0, x1, x2, x3);
  }
  
  public void draw(Minecraft arg0, int arg1, int arg2) {
    GuiUtils.drawRoundedRect(width(25.2F), height(14.9F), new Color(8, 8, 104, 240), width(49.58F), height(78.05F), width(0.7F));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\container\UITrixiumContainer$4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */