package fr.paladium.achievement.client.profile.gui;

import fr.paladium.achievement.client.profile.gui.node.ProfileAchievementCategoryNode;
import fr.paladium.achievement.client.profile.gui.node.ProfileAchievementNode;
import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.core.profile.dto.PlayerAchievement;
import fr.paladium.achievement.core.profile.dto.PlayerAchievementData;
import fr.paladium.achievement.server.managers.AchievementManager;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.progress.ProgressNode;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.profile.client.gui.UIProfile;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UIProfileAchievements extends UIProfile<PlayerAchievementData> {
  private final Map<String, PlayerAchievement> achievements;
  
  private final Signal<AchievementCategory> selectedCategorySignal;
  
  public Map<String, PlayerAchievement> getAchievements() {
    return this.achievements;
  }
  
  public Signal<AchievementCategory> getSelectedCategorySignal() {
    return this.selectedCategorySignal;
  }
  
  public UIProfileAchievements(AModule<?> module) {
    super(module);
    this.achievements = new HashMap<>();
    this.selectedCategorySignal = new Signal(AchievementCategory.values()[0]);
  }
  
  public void onInit() {
    RectNode.create(236.0D, 165.0D, 1448.0D, 785.0D)
      .color(Color.BLACK.copyAlpha(0.2F))
      .attach((UI)this);
    TextNode.create(271.0D, 275.0D)
      .text(Text.create("progression globale", PaladiumText.TITLE))
      .attach((UI)this);
    TextNode.create(271.0D, 434.0D)
      .text(Text.create("catégories", PaladiumText.TITLE))
      .attach((UI)this);
    TextNode.create(651.0D, 434.0D)
      .text(Text.create("achievements", PaladiumText.TITLE))
      .attach((UI)this);
  }
  
  public void onPostInit() {
    this.achievements.clear();
    ((PlayerAchievementData)getProfileData().getOrDefault()).getAchievements().forEach(achievement -> (PlayerAchievement)this.achievements.put(achievement.getId(), achievement));
    int count = 0;
    int total = 0;
    for (AchievementCategory category : AchievementCategory.values()) {
      List<Achievement> achivementsByCategory = AchievementManager.getInstance().getAchievementsByCategory(category);
      int countCategory = 0;
      for (Achievement achievement : achivementsByCategory) {
        if (this.achievements.containsKey(achievement.getId())) {
          int ac = ((PlayerAchievement)this.achievements.get(achievement.getId())).getProgress();
          int at = achievement.getNbToValidate();
          if (!achievement.getSubAchievements().isEmpty()) {
            ac = 0;
            at = achievement.getSubAchievements().size();
            for (Achievement subAchievement : achievement.getSubAchievements()) {
              if (!this.achievements.containsKey(subAchievement.getId()))
                continue; 
              PlayerAchievement subProgress = this.achievements.get(subAchievement.getId());
              if (!subProgress.isCompleted())
                continue; 
              ac++;
            } 
          } 
          if (ac >= at)
            countCategory++; 
        } 
      } 
      count += countCategory;
      total += achivementsByCategory.size();
    } 
    ProgressNode.create(271.0D, 327.0D, 1377.0D, 62.0D)
      .color(ColorConstant.GREEN)
      .padding(7.0D)
      .progress(0.0F, total, count)
      .attach((UI)this);
    TextNode.create(1649.0D, 275.0D)
      .text(Text.create(count + "/" + total, TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 24.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach((UI)this);
    ContainerNode.create(271.0D, 475.0D, 322.0D, 474.0D)
      .overflow(OverflowProperty.SCROLL)
      .body(container -> FlexNode.vertical(1.0D, 2.0D, container.aw(-2.0D)).margin(7.0D).body(()).watch(this.selectedCategorySignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(container)).attach((UI)this);
    ContainerNode.create(650.0D, 475.0D, 964.0D, 474.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.create(980.0D, 20.0D, 434.0D))
      .body(container -> FlexNode.vertical(1.0D, 2.0D, container.aw(-3.0D)).margin(20.0D).body(()).watch(this.selectedCategorySignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.BODY }).attach(container)).attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\client\profile\gui\UIProfileAchievements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */