package fr.paladium.palajobs.client.ui.lvltokens.nodes;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ButtonJobUseToken extends AClickableNode {
  private String text;
  
  private JobType job;
  
  private long tokenCount;
  
  public boolean selected = false;
  
  private static final ResourceLocation ALCHEMIST_ICON = new ResourceLocation("palajobs", "textures/gui/tokens/alchemist.png");
  
  private static final ResourceLocation MINER_ICON = new ResourceLocation("palajobs", "textures/gui/tokens/miner.png");
  
  private static final ResourceLocation HUNTER_ICON = new ResourceLocation("palajobs", "textures/gui/tokens/hunter.png");
  
  private static final ResourceLocation FARMER_ICON = new ResourceLocation("palajobs", "textures/gui/tokens/farmer.png");
  
  private static final ResourceLocation TOKEN = new ResourceLocation("palajobs", "textures/gui/tokens/token.png");
  
  private Color TOP_COLOR = Color.decode("#FF847B");
  
  private Color LEFT_COLOR = Color.decode("#DD2B1D");
  
  private Color RIGHT_COLOR = Color.decode("#DD2B1D");
  
  private Color BOTTOM_COLOR = Color.decode("#92130C");
  
  private Color FILL_COLOR = Color.decode("#EF3926");
  
  private Color TOP_COLOR_DISABLE = Color.decode("#EFEFEF");
  
  private Color LEFT_COLOR_DISABLE = Color.decode("#B4B4B4");
  
  private Color RIGHT_COLOR_DISABLE = Color.decode("#B4B4B4");
  
  private Color BOTTOM_COLOR_DISABLE = Color.decode("#5B5B5B");
  
  private Color FILL_COLOR_DISABLE = Color.decode("#A7A7A7");
  
  private double stroke;
  
  public ButtonJobUseToken(double x, double y, double width, double height, double stroke, String text, JobType job, long tokenCount) {
    super(x, y, width, height);
    this.stroke = stroke;
    this.text = text;
    this.tokenCount = tokenCount;
    this.job = job;
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.enabled ? this.FILL_COLOR.brighter(animationValue(0.3F)) : this.FILL_COLOR_DISABLE);
    GuiUtils.drawRect(this.x, this.y - this.stroke, this.x + this.width, this.y, this.enabled ? this.TOP_COLOR.brighter(animationValue(0.3F)) : this.TOP_COLOR_DISABLE);
    GuiUtils.drawRect(this.x, this.y + this.height, this.x + this.width, this.y + this.height + this.stroke, this.enabled ? this.BOTTOM_COLOR.brighter(animationValue(0.3F)) : this.BOTTOM_COLOR_DISABLE);
    GuiUtils.drawRect(this.x - this.stroke, this.y, this.x, this.y + this.height, this.enabled ? this.LEFT_COLOR.brighter(animationValue(0.3F)) : this.LEFT_COLOR_DISABLE);
    GuiUtils.drawRect(this.x + this.width, this.y, this.x + this.width + this.stroke, this.y + this.height, this.enabled ? this.RIGHT_COLOR.brighter(animationValue(0.3F)) : this.RIGHT_COLOR_DISABLE);
    drawContent(mc, mouseX, mouseY);
  }
  
  public void drawContent(Minecraft mc, int mouseX, int mouseY) {
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.PIXEL_NES.getFont(), 30);
    GuiUtils.drawStringWithCustomFont(mc, this.text, this.x + this.width / 10.0D, this.y + this.height / 2.5D - (fontHeight / 2), Color.WHITE, Fonts.PIXEL_NES.getFont(), 20, true, Color.BLACK);
    GuiUtils.drawStringWithCustomFont(mc, (this.job == null) ? "CLIQUEZ SUR UN JETON" : ("UN JETON DE CHOIX " + TTT.format(this.job.getName(), new Object[0]).toUpperCase()), this.x + this.width / 9.5D, this.y + this.height / 1.2999999523162842D - (fontHeight / 2), Color.WHITE, Fonts.MONTSERRAT_SEMIBOLD.getFont(), -10, false, Color.BLACK);
    ResourceLocation image = ALCHEMIST_ICON;
    if (this.job == JobType.FARMER) {
      image = FARMER_ICON;
    } else if (this.job == JobType.HUNTER) {
      image = HUNTER_ICON;
    } else if (this.job == JobType.MINER) {
      image = MINER_ICON;
    } else if (this.job == JobType.ALCHEMIST) {
      image = ALCHEMIST_ICON;
    } else {
      image = TOKEN;
    } 
    GuiUtils.drawImageTransparent(this.x + width(84), this.y + height(16), image, width(12), width(12));
  }
  
  public void setText(String text) {
    this.text = text;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\lvltokens\nodes\ButtonJobUseToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */