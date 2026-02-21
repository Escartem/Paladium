package fr.paladium.palamod.modules.paladium.client.gui.palamenu.buttons;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;

public class InGameMenuNodeButton extends TexturedNodeButton {
  public InGameMenuNodeButton(double x, double y, double width, double height, String texture, final String text) {
    super(x, y, width, height, texture);
    addChild((ANode)new AClickableNode(width, 0.0D, width * 2.0D, height) {
          public void draw(Minecraft mc, int mouseX, int mouseY) {
            super.draw(mc, mouseX, mouseY);
            setWidth(animationValue((float)this.defaultWidth));
            GuiUtils.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), new Color(124, 16, 10));
            if (this.animationValue > 80.0F)
              GuiUtils.drawCenteredStringWithCustomFont(mc, text, getX() + getWidth() / 2.0D, getY() + height(40.0F), new Color(255, 255, 255, (int)((this.animationValue - 80.0F) / 20.0F * 255.0F)), Fonts.MONTSERRAT_BOLD.getFont(), 40); 
          }
          
          public boolean isHovered() {
            return this.parent.isHovered();
          }
          
          public boolean isHovered(int mouseX, int mouseY) {
            return this.parent.isHovered();
          }
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\buttons\InGameMenuNodeButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */