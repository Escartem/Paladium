package fr.paladium.achievement.client.ui.nodes;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.core.utils.AchievementUtils;
import fr.paladium.achievement.server.managers.AchievementManager;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class UIHomeAchievementCategoryItemNode extends AClickableNode {
  private final ResourceLocation background = new ResourceLocation("achievement", "textures/gui/home/slot.png");
  
  public static final ResourceLocation gemIcon = new ResourceLocation("achievement", "textures/gui/buttons/gem.png");
  
  public static final ResourceLocation healIcon = new ResourceLocation("achievement", "textures/gui/buttons/heal.png");
  
  public static final ResourceLocation mountIcon = new ResourceLocation("achievement", "textures/gui/buttons/mount.png");
  
  public final AchievementCategory data;
  
  public boolean selected;
  
  public int totalQuests;
  
  public int totalCompletedQuests;
  
  public UIHomeAchievementCategoryItemNode(double x, double y, double width, double height, AchievementCategory data, boolean selected) {
    super(x, y, width, height);
    this.data = data;
    this.selected = selected;
    for (Achievement achievement : AchievementManager.getInstance().getAchievementsByCategory(data)) {
      if (achievement.getSubAchievements().size() > 0) {
        this.totalQuests++;
        int countSubCompleted = 0;
        for (Achievement achh : achievement.getSubAchievements()) {
          if (AchievementUtils.getAchievementStatFromEEP(achh.getId()) >= achh.getNbToValidate())
            countSubCompleted++; 
        } 
        if (countSubCompleted >= achievement.getSubAchievements().size())
          this.totalCompletedQuests++; 
        continue;
      } 
      this.totalQuests++;
      int stat = AchievementUtils.getAchievementStatFromEEP(achievement.getId());
      if (stat >= achievement.getNbToValidate())
        this.totalCompletedQuests++; 
    } 
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    if (this.selected) {
      GuiUtils.drawImageTransparent(this.x, this.y, this.background, this.width, this.height, false);
    } else {
      GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(0.0F, 0.0F, 0.0F, 0.2F + animationValue(0.2F)));
    } 
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_SEMIBOLD.getFont(), 15);
    double xOffset = width(25.5F);
    if (this.data == null)
      return; 
    GuiUtils.drawImageTransparent(this.x + xOffset - width(22.0F), this.y + height(50.0F) - height(38.0F), new ResourceLocation("achievement", "textures/gui/buttons/slot.png"), width(17.0F), width(17.0F));
    GuiUtils.drawStringWithCustomFont(mc, this.data.getName().toUpperCase(), this.x + xOffset, this.y + height(35.0F) - (fontHeight / 2), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 25);
    GuiUtils.drawStringWithCustomFont(mc, this.totalCompletedQuests + "/" + this.totalQuests, this.x + xOffset, this.y + height(63.0F) - (fontHeight / 2), Color.WHITE, Fonts.PIXEL_NES.getFont(), 5);
    GuiUtils.drawCenteredStringWithCustomFont(mc, ((this.totalCompletedQuests > 0) ? (100 * this.totalCompletedQuests / this.totalQuests) : 0) + "%", this.x + xOffset * 3.6D, this.y + height(15.0F) - (fontHeight / 2), Color.WHITE, Fonts.PIXEL_NES.getFont(), 10);
    float p = (this.totalQuests > 0) ? (this.totalCompletedQuests / this.totalQuests) : 0.0F;
    GuiUtils.drawRect(this.x + width(45.0F), this.y + height(62.944F), this.x + width(45.0F) + width(37.0F), this.y + height(62.944F) + height(10.166F), new Color(50, 50, 50));
    GuiUtils.drawRect(this.x + width(45.0F), this.y + height(65.944F) + height(4.166F), this.x + width(45.0F) + width(37.0F), this.y + height(62.944F) + height(10.166F) + height(0.7F), (new Color(50, 50, 50)).darker(0.2F));
    GuiUtils.drawRect(this.x + width(45.0F), this.y + height(62.944F), this.x + width(45.0F) + width(37.0F * p), this.y + height(62.944F) + height(10.166F), new Color(94, 212, 42));
    GuiUtils.drawRect(this.x + width(45.0F), this.y + height(65.944F) + height(4.166F), this.x + width(45.0F) + width(37.0F * p), this.y + height(62.944F) + height(10.166F) + height(0.7F), (new Color(94, 212, 42)).darker(0.2F));
    ItemStack is = AchievementManager.getItemStackFromString(this.data.getIcon());
    if (is != null)
      GuiUtils.renderScaledItemStackIntoGUI(is, this.x + width(5.5F), this.y + height(20.6F), width(0.8F)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\clien\\ui\nodes\UIHomeAchievementCategoryItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */