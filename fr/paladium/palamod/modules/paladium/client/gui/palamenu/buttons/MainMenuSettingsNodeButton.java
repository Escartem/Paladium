package fr.paladium.palamod.modules.paladium.client.gui.palamenu.buttons;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class MainMenuSettingsNodeButton extends AClickableNode {
  private ResourceLocation icon = new ResourceLocation("palamod", "textures/gui/main/button/settings.png");
  
  private ResourceLocation chevron = new ResourceLocation("palamod", "textures/gui/main/button/down.png");
  
  public boolean active;
  
  public MainMenuSettingsNodeButton(double x, double y, double width) {
    super(x, y, width, (
        GuiUtils.getFontHeight(Minecraft.func_71410_x(), Fonts.MONTSERRAT_BOLD.getFont(), 50) * 2));
    setAnimationDuration(0.2F);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawImageTransparent(this.x, this.y, this.icon, height(60.0F), height(60.0F));
    GuiUtils.drawStringWithCustomFont(mc, "OPTIONS", this.x + width(20.0F), this.y, Color.white, Fonts.MONTSERRAT_BOLD
        .getFont(), 50);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\buttons\MainMenuSettingsNodeButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */