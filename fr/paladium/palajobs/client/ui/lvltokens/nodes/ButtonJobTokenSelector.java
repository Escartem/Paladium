package fr.paladium.palajobs.client.ui.lvltokens.nodes;

import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.palajobs.api.type.JobType;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ButtonJobTokenSelector extends AClickableNode {
  private String text;
  
  private JobType job;
  
  private long tokenCount;
  
  public boolean selected = false;
  
  private static final ResourceLocation ALCHEMIST_ICON = new ResourceLocation("palajobs", "textures/gui/tokens/alchemist.png");
  
  private static final ResourceLocation MINER_ICON = new ResourceLocation("palajobs", "textures/gui/tokens/miner.png");
  
  private static final ResourceLocation HUNTER_ICON = new ResourceLocation("palajobs", "textures/gui/tokens/hunter.png");
  
  private static final ResourceLocation FARMER_ICON = new ResourceLocation("palajobs", "textures/gui/tokens/farmer.png");
  
  private double stroke;
  
  public ButtonJobTokenSelector(double x, double y, double width, double height, double stroke, String text, JobType job, long tokenCount) {
    super(x, y, width, height);
    this.stroke = stroke;
    this.text = text;
    this.tokenCount = tokenCount;
    this.job = job;
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, Color.decode("#1a1a1f").darker(0.3F));
    if (this.selected) {
      GuiUtils.drawRect(this.x, this.y - this.stroke, this.x + this.width, this.y, Color.white);
      GuiUtils.drawRect(this.x, this.y + this.height, this.x + this.width, this.y + this.height + this.stroke, Color.white);
      GuiUtils.drawRect(this.x - this.stroke, this.y, this.x, this.y + this.height, Color.white);
      GuiUtils.drawRect(this.x + this.width, this.y, this.x + this.width + this.stroke * 1.2000000476837158D, this.y + this.height, Color.white);
    } 
    drawContent(mc, mouseX, mouseY);
  }
  
  public void drawContent(Minecraft mc, int mouseX, int mouseY) {
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.PIXEL_NES.getFont(), 30);
    GuiUtils.drawStringWithCustomFont(mc, this.text, this.x + this.width / 3.3D, this.y + this.height / 2.5D - (fontHeight / 2), Color.WHITE, Fonts.MONTSERRAT_SEMIBOLD.getFont(), 15, true, Color.BLACK);
    GuiUtils.drawStringWithCustomFont(mc, "X" + this.tokenCount, this.x + this.width / 3.3D, this.y + this.height / 1.6D - (fontHeight / 2), Color.WHITE, Fonts.PIXEL_NES.getFont(), 80, true, Color.BLACK);
    ResourceLocation image = ALCHEMIST_ICON;
    if (this.job == JobType.FARMER) {
      image = FARMER_ICON;
    } else if (this.job == JobType.HUNTER) {
      image = HUNTER_ICON;
    } else if (this.job == JobType.MINER) {
      image = MINER_ICON;
    } else if (this.job == JobType.ALCHEMIST) {
      image = ALCHEMIST_ICON;
    } 
    GuiUtils.drawImageTransparent(this.x + width(6), this.y + height(14), image, width(19.2D), width(22.32D));
  }
  
  public void setText(String text) {
    this.text = text;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\lvltokens\nodes\ButtonJobTokenSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */