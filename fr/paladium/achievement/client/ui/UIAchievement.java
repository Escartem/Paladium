package fr.paladium.achievement.client.ui;

import fr.paladium.achievement.client.ui.nodes.AchievementCloseNode;
import fr.paladium.achievement.client.ui.nodes.UIHomeAchievementCategoryItemNode;
import fr.paladium.achievement.client.ui.nodes.UIHomeAchievementDetailItemNode;
import fr.paladium.achievement.core.AchievementRegistry;
import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.core.utils.AchievementUtils;
import fr.paladium.achievement.server.managers.AchievementManager;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.TexturedNodeButton;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.lib.apollon.nodes.flex.FlexDirection;
import fr.paladium.lib.apollon.nodes.flex.FlexNode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import java.util.Iterator;
import net.minecraft.util.ResourceLocation;

public class UIAchievement extends UI {
  private AchievementCategory selectedMountData;
  
  public boolean unlockedOnly = false;
  
  public int totalCompletedQuests;
  
  public int totalQuests;
  
  private FlexNode flexNode;
  
  public UIAchievement() {
    super(null, "achievement:home");
    for (Achievement ach : AchievementRegistry.getInstance().getAchievements()) {
      if (ach.getParent() != null)
        continue; 
      this.totalQuests++;
      if (ach instanceof fr.paladium.achievement.core.pojo.types.MultiAchievement) {
        int countCompleted = 0;
        for (Achievement achh : ach.getSubAchievements()) {
          if (AchievementUtils.getAchievementStatFromEEP(achh.getId()) >= achh.getNbToValidate())
            countCompleted++; 
        } 
        if (countCompleted >= ach.getSubAchievements().size())
          this.totalCompletedQuests++; 
        continue;
      } 
      if (AchievementUtils.getAchievementStatFromEEP(ach.getId()) >= ach.getNbToValidate())
        this.totalCompletedQuests++; 
    } 
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    ScrollArea scrollArea = new ScrollArea(width(21.1458F), height(38.85F), height(50.166F), width(0.83F), height(45.185F));
    ScrollableArea area = new ScrollableArea(width(3.28125F), height(38.72F), width(20.53125F), height(89.037F), scrollArea);
    area.setScrollSpeed(50);
    area.setScrollTexture("achievement:textures/gui/home/scroll");
    area.setScrollHoverTexture("achievement:textures/gui/home/scroll_hover");
    addScrollableArea(area);
    ScrollArea scrollAreaAchievements = new ScrollArea(width(94.1458F), height(38.85F), height(50.166F), width(0.83F), height(15.0F));
    ScrollableArea areaAchievements = new ScrollableArea(width(23.28125F), height(38.72F), width(93.53125F), height(89.037F), scrollAreaAchievements);
    areaAchievements.setScrollSpeed(50);
    areaAchievements.setScrollTexture("achievement:textures/gui/home/scroll");
    areaAchievements.setScrollHoverTexture("achievement:textures/gui/home/scroll_hover");
    addScrollableArea(areaAchievements);
    int count = 0;
    this.flexNode = new FlexNode(areaAchievements.getMinX(), areaAchievements.getMinY() + height(0.1F), areaAchievements.getMaxX() - areaAchievements.getMinX(), height(10.592F), FlexDirection.COLUMN);
    this.flexNode.setMargin(height(1.0F));
    addNode((ANode)this.flexNode);
    this.flexNode.setArea(areaAchievements);
    for (int i = 0; i < (AchievementCategory.values()).length; i++) {
      AchievementCategory item = AchievementCategory.values()[i];
      if (item instanceof AchievementCategory) {
        addNode((new UIHomeAchievementCategoryItemNode(area.getMinX(), area.getMinY() + height(0.1F) + (height(7.992F) * count), area.getMaxX() - area.getMinX(), height(7.592F), item, (this.selectedMountData == null) ? false : this.selectedMountData.equals(item)))
            .setCallback(node -> {
                this.selectedMountData = item;
                areaAchievements.setScroll(0.0D);
                getNodes().stream().filter(UIHomeAchievementCategoryItemNode.class::isInstance).map(UIHomeAchievementCategoryItemNode.class::cast).forEach(());
                Iterator<?> ii = this.flexNode.getChildren().iterator();
                while (ii.hasNext()) {
                  ANode nodeTemp = (ANode)ii.next();
                  if (nodeTemp instanceof UIHomeAchievementDetailItemNode)
                    ii.remove(); 
                } 
                int count2 = 0;
                for (Achievement achievement : AchievementManager.getInstance().getAchievementsByCategory(item)) {
                  this.flexNode.addChild((new UIHomeAchievementDetailItemNode(0.0D, 0.0D, areaAchievements.getMaxX() - areaAchievements.getMinX(), height(10.592F), achievement, count2)).setCallback(()));
                  count2++;
                } 
              }).setArea(area));
        count++;
      } 
    } 
    TexturedNodeButton unlockedOnly = new TexturedNodeButton(width(83.5F), height(35.0F), width(10.0F), height(3.2F));
    unlockedOnly.setTexture("achievement:textures/gui/home/unlocked_checked");
    unlockedOnly.setCallback(c -> {
          this.unlockedOnly = !this.unlockedOnly;
          if (this.unlockedOnly) {
            unlockedOnly.setTexture("achievement:textures/gui/home/unlocked_unchecked");
          } else {
            unlockedOnly.setTexture("achievement:textures/gui/home/unlocked_checked");
          } 
        });
    addNode((ANode)unlockedOnly);
    addNode((ANode)new AchievementCloseNode(width(94.218F), height(5.74F), width(2.34F), height(4.16F)));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawImageTransparent(0.0D, 0.0D, getBackground(), this.field_146294_l, this.field_146295_m, false);
    GuiUtils.drawImageTransparent(width(5.0F), height(20.0F), 0.0D, 0.0D, width(90.0F), height(10.0F), width(90.0F), height(10.0F), new ResourceLocation("achievement", "textures/gui/home/progressbar_foreground.png"), new Color(255, 255, 255), 0.2F);
    float x = width(5.0F);
    float y = -height(44.0F);
    float p = (this.totalQuests > 0) ? (this.totalCompletedQuests / this.totalQuests) : 0.0F;
    GuiUtils.drawRect((x + width(0.5F)), (y + height(64.944F)), (x + width(45.0F) + width(44.5F)), (y + height(64.944F) + height(7.166F)), new Color(50, 50, 50));
    GuiUtils.drawRect((x + width(0.5F)), (y + height(67.944F) + height(4.166F)), (x + width(45.0F) + width(44.5F)), (y + height(64.944F) + height(7.166F) + height(0.7F)), (new Color(50, 50, 50)).darker(0.2F));
    GuiUtils.drawRect((x + width(0.5F)), (y + height(64.944F)), (x + width(45.0F) * p), (y + height(64.944F) + height(7.166F)), new Color(94, 212, 42));
    GuiUtils.drawRect((x + width(0.5F)), (y + height(67.944F) + height(4.166F)), (x + width(45.0F) * p), (y + height(64.944F) + height(7.166F) + height(0.7F)), (new Color(94, 212, 42)).darker(0.2F));
    GuiUtils.drawRect(width(94.1458F), height(38.85F), width(94.9757999833107D), height(89.01600036621093D), new Color(65, 55, 54));
    if (this.totalQuests > 0)
      GuiUtils.drawCenteredStringWithCustomFont(this.field_146297_k, (100 * this.totalCompletedQuests / this.totalQuests) + "%", (x + width(45.0F)), (y + height(66.944F)), Color.WHITE, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 75); 
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, "Progression globale".toUpperCase(), (x + width(0.2F)), (y + height(61.244F)), Color.WHITE, Fonts.MINECRAFT_BOLD.getFont(), 150);
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, "CatEgories".toUpperCase(), (x + width(0.2F)), (y + height(79.244F)), Color.WHITE, Fonts.MINECRAFT_BOLD.getFont(), 150);
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, "Achievements".toUpperCase(), (x + width(18.5F)), (y + height(79.244F)), Color.WHITE, Fonts.MINECRAFT_BOLD.getFont(), 150);
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
    if (this.flexNode != null && this.flexNode.getArea() != null && this.flexNode.getArea().isCanScroll())
      GuiUtils.drawGradientRect((int)width(23.3F), (int)height(80.0F), (int)width(95.0F), (int)height(89.1D), (new Color(0, 0, 0, 0)).getRGB(), (new Color(23, 23, 28, 255)).getRGB(), 0.0F); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\clien\\ui\UIAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */