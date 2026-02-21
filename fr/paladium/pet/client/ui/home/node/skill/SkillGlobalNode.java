package fr.paladium.pet.client.ui.home.node.skill;

import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.ui.UIPopup;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.pet.client.ui.home.UIPetHome;
import fr.paladium.pet.client.ui.home.popup.UISkillRollPopup;
import fr.paladium.pet.client.ui.utils.data.HomeData;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class SkillGlobalNode extends ANode {
  private static final ResourceLocation LOGO = new ResourceLocation("palapet", "textures/ui/home/skill_logo.png");
  
  private final HomeData data;
  
  private final String title;
  
  private double buttonPosX;
  
  private final double backgroundY;
  
  private final UIPetHome parentUI;
  
  public SkillGlobalNode(UIPetHome parentUI, double x, double y, double width, double height, double backgroundY) {
    super(x, y, width, height);
    this.data = parentUI.getData();
    this.title = PetTranslateEnum.GUI_NODE_SKILL_TITLE.text();
    this.backgroundY = backgroundY;
    this.parentUI = parentUI;
  }
  
  public void onInit() {
    super.onInit();
    ANode button = (new MinecraftTextCallToActionNode(width(78.5F), height(0.2F), width(21.0F), PetTranslateEnum.GUI_NODE_SKILL_BUTTON_TITLE.text())).setCallback(callback -> this.ui.openPopup((UIPopup)new UISkillRollPopup(this.parentUI)));
    addChild(button);
    this.buttonPosX = (width(78.5F) + width(21.0F));
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRect(this.x, this.backgroundY, this.x + this.width, this.backgroundY + this.height, UIPetHome.BACKGROUND_COLOR);
    double logoX = this.ui.width(1.979F);
    double logoY = this.ui.width(1.979F);
    GuiUtils.drawImageTransparent(this.x, this.y, LOGO, logoX, logoY);
    double textX = this.x + logoX + width(1.094F);
    double textY = this.y + this.ui.height(0.75F);
    int fontSize = 140;
    FontObj font = Fonts.MONTSERRAT_EXTRABOLD.getFont();
    GuiUtils.drawStringWithCustomFont(mc, this.title, textX, textY, Color.WHITE, font, fontSize);
    double barX = textX + GuiUtils.getStringWidth(mc, this.title, font, fontSize) + width(1.0F);
    double barY = textY + (GuiUtils.getFontHeight(mc, font, fontSize) / 2.0F);
    double barHeight = height(0.4F);
    GuiUtils.drawRect(barX, barY, this.buttonPosX - width(7.5F), barY + barHeight, Color.WHITE);
  }
  
  public boolean onClick(int i, int i1, int i2) {
    return false;
  }
  
  public void onRelease(int i, int i1, int i2) {}
  
  public void onKeyTyped(char c, int i) {}
  
  public void onHover(int i, int i1) {}
  
  public void onHoverOut(int i, int i1) {}
  
  public void fixedUpdate() {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\node\skill\SkillGlobalNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */