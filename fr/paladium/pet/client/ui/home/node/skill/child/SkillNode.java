package fr.paladium.pet.client.ui.home.node.skill.child;

import fr.paladium.lib.apollon.fontV2.FontObj;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextOverflow;
import fr.paladium.pet.client.ui.utils.data.SlotClientData;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;

public class SkillNode extends AClickableNode {
  private static final Color HOVER_COLOR = new Color(0, 0, 0, 30);
  
  private final SlotClientData data;
  
  private final String activeSkill;
  
  private final String passiveSkill;
  
  private final String active;
  
  private final String passive;
  
  public SkillNode(SlotClientData data, double x, double y, double width, double height) {
    super(x, y, width, height);
    this.data = data;
    this.activeSkill = PetTranslateEnum.GUI_NODE_SKILL_ACTIVE_SKILL.text();
    this.passiveSkill = PetTranslateEnum.GUI_NODE_SKILL_PASSIVE_SKILL.text();
    this.active = PetTranslateEnum.GUI_NODE_SKILL_ACTIVE.text();
    this.passive = PetTranslateEnum.GUI_NODE_SKILL_PASSIVE.text();
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.data
        
        .getColorInfo().getBackgroundColor());
    GuiUtils.drawBorder(this.x, this.y, this.x + this.width, this.y + this.height, this.data
        
        .getColorInfo().getBorderColor(), 1.0D);
    double nameX = this.x + width(15.0F);
    double nameY = this.y + (height(50.0F) / 2.0F);
    double logoSize = width(7.0F);
    double logoX = nameX - width(11.0F);
    double logoY = nameY - height(2.0F);
    GuiUtils.drawImageTransparent(logoX, logoY, this.data.getLogo(), logoSize, logoSize, false);
    String enumName = this.data.getColorInfo().toString();
    if (enumName.contains(this.active) || enumName.contains(this.passive)) {
      String text = enumName.contains(this.active) ? this.activeSkill : this.passiveSkill;
      FontObj fontObj = Fonts.MONTSERRAT_BOLD.getFont();
      int j = 1;
      double textWidth = GuiUtils.getStringWidth(mc, text, fontObj, j);
      double labelY = this.y + height(13.0F);
      double labelX = this.x + width(100.0F) - textWidth - width(1.5F);
      GuiUtils.drawStringWithCustomFont(mc, text, labelX, labelY, this.data.getColorInfo().getBorderColor(), fontObj, j);
      double labelWidth = GuiUtils.getStringWidth(mc, text, fontObj, j);
      double labelHeight = GuiUtils.getFontHeight(mc, fontObj, j);
      double cooldownX = labelX + labelWidth / 2.0D;
      double cooldownY = labelY + labelHeight + height(2.0F);
      if (this.data.getColorInfo().isCooldown()) {
        long remaining = this.data.getNextChangeMillis() - System.currentTimeMillis();
        if (remaining < 0L)
          remaining = 0L; 
        String cooldownText = String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(TimeUnit.MILLISECONDS.toHours(remaining) % 24L), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(remaining) % 60L), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(remaining) % 60L) });
        fontObj = Fonts.MONTSERRAT_REGULAR.getFont();
        j = 1;
        textWidth = GuiUtils.getStringWidth(mc, cooldownText, fontObj, j);
        labelY = this.y + height(13.0F);
        labelX = this.x + width(100.0F) - textWidth - width(1.8F);
        GuiUtils.drawStringWithCustomFont(mc, cooldownText, labelX, cooldownY, this.data.getColorInfo().getBorderColor(), fontObj, j);
      } 
    } 
    FontObj font = Fonts.MONTSERRAT_EXTRABOLD.getFont();
    int fontSize = 50;
    GuiUtils.drawStringWithCustomFont(mc, this.data
        .getName().toUpperCase(), nameX, nameY, Color.WHITE, font, fontSize);
    double descriptionX = nameX;
    double descriptionY = nameY + GuiUtils.getFontHeight(mc, font, fontSize) + height(13.0F);
    font = Fonts.MONTSERRAT_MEDIUM.getFont();
    fontSize = 15;
    List<String> lines = GuiUtils.getSplittedString(mc, this.data.getDescription(), font, fontSize, width(80.0F));
    int lineHeight = GuiUtils.getFontHeight(mc, font, fontSize + 10);
    int lineCount = Math.min(lines.size(), 3);
    for (int i = 0; i < lineCount; i++) {
      String text = lines.get(i);
      if (i == lineCount - 1 && lineCount != lines.size())
        text = text + TextOverflow.ELLIPSIS.getOverflow(); 
      GuiUtils.drawStringWithCustomFont(mc, text, descriptionX, descriptionY, Color.WHITE, font, fontSize);
      descriptionY += lineHeight;
    } 
    if (isHovered() && this.data.getColorInfo() != SlotClientData.SkillColorInfo.LOCKED)
      GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, HOVER_COLOR); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\ui\home\node\skill\child\SkillNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */