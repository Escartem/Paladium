package fr.paladium.palamod.modules.trixium.gui;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

class null extends AClickableNode {
  null(double x0, double x1, double x2, double x3) {
    super(x0, x1, x2, x3);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRoundedRect(this.x, this.y, Color.WHITE, this.width, this.height, height(10.0F));
    UITrixium.this.drawFullyCenteredString("Classement", this.x + this.width / 2.0D, this.y + this.height / 2.0D, new Color(99, 99, 99), Fonts.MONTSERRAT_BOLD.getFont(), 50);
    double crownX = this.x + width(71.5F);
    double crownY = this.y + height(12.0F);
    double crownSize = width(6.0F);
    GL11.glPushMatrix();
    GL11.glTranslated(crownX, crownY + crownSize, 0.0D);
    GL11.glRotated(15.0D + animationValue(10.0F), 0.0D, 0.0D, 1.0D);
    GL11.glTranslated(-crownX, -(crownY + crownSize), 0.0D);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, animationValue(1.0F));
    GuiUtils.drawImageTransparent(crownX, crownY, UITrixium.access$000(UITrixium.this), crownSize, crownSize, false);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\UITrixium$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */