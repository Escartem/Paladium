package fr.paladium.achievement.client.ui.nodes;

import fr.paladium.achievement.client.ui.UIAchievement;
import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.utils.AchievementUtils;
import fr.paladium.achievement.server.managers.AchievementManager;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class UIHomeAchievementDetailItemNode extends AClickableNode {
  public static final ResourceLocation gemIcon = new ResourceLocation("achievement", "textures/gui/buttons/gem.png");
  
  public static final ResourceLocation healIcon = new ResourceLocation("achievement", "textures/gui/buttons/heal.png");
  
  public static final ResourceLocation mountIcon = new ResourceLocation("achievement", "textures/gui/buttons/mount.png");
  
  public final Achievement data;
  
  public int id;
  
  public UIHomeAchievementDetailItemNode(double x, double y, double width, double height, Achievement data, int id) {
    super(x, y, width, height);
    this.data = data;
    this.id = id;
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    int fontHeight = GuiUtils.getFontHeight(mc, Fonts.MONTSERRAT_SEMIBOLD.getFont(), 15);
    double xOffset = width(9.5F);
    boolean completed = (AchievementUtils.getAchievementStatFromEEP(this.data.getId()) >= this.data.getNbToValidate());
    if (this.data instanceof fr.paladium.achievement.core.pojo.types.MultiAchievement) {
      int countSubCompleted = 0;
      for (Achievement ach : this.data.getSubAchievements()) {
        if (AchievementUtils.getAchievementStatFromEEP(ach.getId()) >= ach.getNbToValidate())
          countSubCompleted++; 
      } 
      completed = (countSubCompleted >= this.data.getSubAchievements().size());
    } 
    double hidedHeight = 0.0D;
    if (this.ui instanceof UIAchievement) {
      UIAchievement uiAchvmt = (UIAchievement)this.ui;
      hidedHeight = -uiAchvmt.height(1.0F);
      if (uiAchvmt.unlockedOnly) {
        if (completed)
          this.height = hidedHeight; 
      } else if (this.height == hidedHeight) {
        this.height = this.defaultHeight;
      } 
    } 
    if (this.height == hidedHeight)
      return; 
    Color bgColor = completed ? new Color(0.1F, 0.5F, 0.15F, 0.2F + animationValue(0.2F)) : new Color(0.0F, 0.0F, 0.0F, 0.2F + animationValue(0.2F));
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, bgColor);
    if (this.data == null)
      return; 
    float multi = 1.0F;
    if (this.height != this.defaultHeight) {
      GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, bgColor);
      GuiUtils.drawImageTransparent(this.x + width(97.5F), this.y + height(21.0F), new ResourceLocation("achievement", "textures/gui/home/arrow_top.png"), width(1.0F), width(1.0F));
      multi = 0.5F;
      if (this.data.getSubAchievements().size() > 9)
        multi = 0.33333334F; 
      if (this.data instanceof fr.paladium.achievement.core.pojo.types.MultiAchievement) {
        int countSub = 0;
        int xIndex = 0;
        for (Achievement sub : this.data.getSubAchievements()) {
          float height = 58.0F;
          if (this.data.getSubAchievements().size() > 9)
            height -= 13.0F; 
          GuiUtils.drawStringWithCustomFont(mc, sub.getName() + ": " + sub.getDescription(), this.x + xOffset - width(5) + width(xIndex * 35), this.y + height(height) - (fontHeight / 2) + height(countSub * 15), Color.WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 30);
          GuiUtils.drawImageTransparent(this.x + width(1.5F) + width(xIndex * 35), this.y + height(height - 5.0F) + height(countSub * 15), new ResourceLocation("achievement", "textures/gui/home/sub_" + ((AchievementUtils.getAchievementStatFromEEP(sub.getId()) >= sub.getNbToValidate()) ? "" : "un") + "checked.png"), width(2.0F), width(2.2F), true);
          countSub++;
          if (countSub >= ((this.data.getSubAchievements().size() > 9) ? 4 : 3)) {
            xIndex++;
            countSub = 0;
          } 
        } 
      } else {
        float p = (this.data.getNbToValidate() > 0) ? (AchievementUtils.getAchievementStatFromEEP(this.data.getId()) / this.data.getNbToValidate()) : 0.0F;
        GuiUtils.drawRect(this.x + width(0.9F), this.y + height(62.944F), this.x + width(0.9F) + width(37.0F), this.y + height(62.944F) + height(28.166F), new Color(50, 50, 50));
        GuiUtils.drawRect(this.x + width(0.9F), this.y + height(73.944F) + height(14.166F), this.x + width(0.9F) + width(37.0F), this.y + height(62.944F) + height(28.166F) + height(0.7F), (new Color(50, 50, 50)).darker(0.2F));
        GuiUtils.drawRect(this.x + width(0.9F), this.y + height(62.944F), this.x + width(0.9F) + width(37.0F * p), this.y + height(62.944F) + height(28.166F), new Color(94, 212, 42));
        GuiUtils.drawRect(this.x + width(0.9F), this.y + height(73.944F) + height(14.166F), this.x + width(0.9F) + width(37.0F * p), this.y + height(62.944F) + height(28.166F) + height(0.7F), (new Color(94, 212, 42)).darker(0.2F));
        GuiUtils.drawStringWithCustomFont(mc, AchievementUtils.getAchievementStatFromEEP(this.data.getId()) + "/" + this.data.getNbToValidate(), this.x + width(39.0F), this.y + height(65.944F), Color.WHITE, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 80);
      } 
    } else {
      GuiUtils.drawImageTransparent(this.x + width(97.5F), this.y + height(42.0F), new ResourceLocation("achievement", "textures/gui/home/arrow_down.png"), width(1.0F), width(1.0F));
      multi = 1.0F;
    } 
    if (!(this.data instanceof fr.paladium.achievement.core.pojo.types.MultiAchievement)) {
      GuiUtils.drawCenteredStringWithCustomFont(mc, ((AchievementUtils.getAchievementStatFromEEP(this.data.getId()) >= this.data.getNbToValidate()) ? 1 : 0) + "/" + '\001', this.x + xOffset + width(85), this.y + height(40.0F * multi) - (fontHeight / 2), Color.WHITE, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 25);
    } else {
      int countSubCompleted = 0;
      for (Achievement ach : this.data.getSubAchievements()) {
        if (AchievementUtils.getAchievementStatFromEEP(ach.getId()) >= ach.getNbToValidate())
          countSubCompleted++; 
      } 
      if (this.height == this.defaultHeight) {
        float p = (this.data.getSubAchievements().size() > 0) ? (countSubCompleted / this.data.getSubAchievements().size()) : 0.0F;
        GuiUtils.drawRect(this.x + width(80), this.y + height(40.944F), this.x + width(45.0F) + width(44.5F), this.y + height(40.944F) + height(9.166F), new Color(50, 50, 50));
        GuiUtils.drawRect(this.x + width(80), this.y + height(43.944F) + height(4.166F), this.x + width(45.0F) + width(44.5F), this.y + height(48.944F) + height(9.166F) + height(0.7F), (new Color(50, 50, 50)).darker(0.2F));
        GuiUtils.drawRect(this.x + width(80), this.y + height(40.944F), this.x + width(45.0F) + width(44.5F * p), this.y + height(40.944F) + height(15.166F), new Color(94, 212, 42));
        GuiUtils.drawRect(this.x + width(80), this.y + height(43.944F) + height(9.166F), this.x + width(45.0F) + width(44.5F * p), this.y + height(48.944F) + height(9.166F) + height(0.7F), (new Color(94, 212, 42)).darker(0.2F));
      } 
      GuiUtils.drawCenteredStringWithCustomFont(mc, countSubCompleted + "/" + this.data.getSubAchievements().size(), this.x + xOffset + width(85), this.y + height(40.0F * multi) - (fontHeight / 2), Color.WHITE, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 25);
    } 
    GuiUtils.drawStringWithCustomFont(mc, this.data.getName().toUpperCase(), this.x + xOffset, this.y + height(38.0F * multi) - (fontHeight / 2), Color.WHITE, Fonts.MONTSERRAT_BOLD.getFont(), 30);
    GuiUtils.drawStringWithCustomFont(mc, this.data.getDescription(), this.x + xOffset, this.y + height(58.0F * multi) - (fontHeight / 2), Color.WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 30);
    GuiUtils.drawImageTransparent(this.x + width(1), this.y + width(0.7F), new ResourceLocation("achievement", "textures/gui/buttons/slot.png"), width(6.5F), width(6.5F));
    if (completed) {
      GuiUtils.drawImageTransparent(this.x + width(2), this.y + width(1.8F), new ResourceLocation("achievement", "textures/gui/home/completed.png"), width(4.5F), width(4.5F), false);
    } else {
      ItemStack is = AchievementManager.getItemStackFromString((this.data.getIcon() != null) ? this.data.getIcon() : "");
      if (is != null)
        GuiUtils.renderScaledItemStackIntoGUI(is, this.x + width(1.5F), this.y + height(15.0F * multi), width(0.35F)); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\clien\\ui\nodes\UIHomeAchievementDetailItemNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */