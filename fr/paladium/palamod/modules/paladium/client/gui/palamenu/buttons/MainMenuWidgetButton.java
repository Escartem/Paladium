package fr.paladium.palamod.modules.paladium.client.gui.palamenu.buttons;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import javax.vecmath.Vector2d;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class MainMenuWidgetButton extends AClickableNode {
  private String text;
  
  private ResourceLocation icon;
  
  private ResourceLocation left = new ResourceLocation("palamod", "textures/gui/main/button/left.png");
  
  private ResourceLocation right = new ResourceLocation("palamod", "textures/gui/main/button/right.png");
  
  public MainMenuWidgetButton(double x, double y, double width, double height, String text, String icon) {
    super(x, y, width, height);
    this.text = text;
    this.icon = new ResourceLocation(icon + ".png");
    setAnimationDuration(0.1F);
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawShape(new Color(239, 57, 38), new Vector2d[] { new Vector2d(this.x, this.y + this.height), new Vector2d(this.x + 
            width(100.0F) - this.ui.width(2.0F), this.y + this.height), new Vector2d(this.x + this.width, this.y), new Vector2d(this.x + this.ui
            .width(2.0F), this.y) });
    GuiUtils.drawCenteredStringWithCustomFont(mc, this.text, this.x + this.width / 2.0D, this.y + 
        height(50.0F) - (GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_BLACK.getFont(), 130) / 2), Color.white, Fonts.MONTSERRAT_BLACK
        .getFont(), 130);
    GuiUtils.drawImageTransparent(this.x + this.ui.width(1.0F) - animationValue(width(6.0F)), this.y - 
        height(10.0F) - animationValue(height(20.0F)), this.left, this.ui.width(5.0F), this.ui.width(1.42857F), false);
    GuiUtils.drawImageTransparent(this.x + this.width - this.ui.width(6.0F) + animationValue(width(6.0F)), this.y + this.height - 
        height(30.0F) + animationValue(height(15.0F)), this.right, this.ui.width(5.0F), this.ui
        .width(1.42857F), false);
    GuiUtils.drawShape(new Color(255, 255, 255, 100), new Vector2d[] { new Vector2d(this.x + this.width + 
            animationValue(width(5.0F)), this.y + this.height), new Vector2d(this.x + this.width + this.ui
            .width(3.5F) + animationValue(width(5.0F)), this.y + this.height), new Vector2d(this.x + this.width + this.ui
            .width(3.5F) + this.ui.width(2.0F) + animationValue(width(5.0F)), this.y), new Vector2d(this.x + this.width + this.ui
            .width(2.0F) + animationValue(width(5.0F)), this.y) });
    GuiUtils.drawImageTransparent(this.x + this.width + this.ui.width(1.6F) + animationValue(width(5.0F)), this.y + 
        height(33.0F), this.icon, this.ui.width(2.0F), height(46.0F));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamenu\buttons\MainMenuWidgetButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */