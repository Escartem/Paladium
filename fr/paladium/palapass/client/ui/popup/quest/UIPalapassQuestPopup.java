package fr.paladium.palapass.client.ui.popup.quest;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.bookmark.BookmarkNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.button.impl.BackButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.paladiumui.kit.progress.ProgressNode;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palapass.client.ui.popup.quest.node.PalapassQuestNode;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.network.data.QuestProgressData;
import fr.paladium.palapass.common.network.packet.server.CSPacketPalapassGiveItem;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsDuration;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.pojo.quest.QuestTier;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.animation.tweenengine.BaseTween;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquation;
import fr.paladium.zephyrui.lib.animation.tweenengine.TweenEquations;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.popup.UIDataPopup;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.MaskNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

@UIData(zlevel = 100.0D, background = false)
@UIDataPopup(active = true, transition = UIDataPopup.PopupTransition.NONE)
public class UIPalapassQuestPopup extends UI {
  private final PalapassPlayer playerData;
  
  private final List<Quest> dailyQuestList;
  
  private final List<Quest> seasonQuestList;
  
  private Signal<Quest> selectedQuest;
  
  private boolean closing;
  
  private TweenAnimator bodyAnimator;
  
  private TweenAnimator panelAnimator;
  
  private Node body;
  
  private Node panel;
  
  public UIPalapassQuestPopup(PalapassPlayer playerData, List<Quest> dailyQuestList, List<Quest> seasonQuestList) {
    this.playerData = playerData;
    this.dailyQuestList = dailyQuestList;
    this.seasonQuestList = seasonQuestList;
  }
  
  public PalapassPlayer getPlayerData() {
    return this.playerData;
  }
  
  public List<Quest> getDailyQuestList() {
    return this.dailyQuestList;
  }
  
  public List<Quest> getSeasonQuestList() {
    return this.seasonQuestList;
  }
  
  public Signal<Quest> getSelectedQuest() {
    return this.selectedQuest;
  }
  
  public boolean isClosing() {
    return this.closing;
  }
  
  public TweenAnimator getBodyAnimator() {
    return this.bodyAnimator;
  }
  
  public TweenAnimator getPanelAnimator() {
    return this.panelAnimator;
  }
  
  public Node getBody() {
    return this.body;
  }
  
  public Node getPanel() {
    return this.panel;
  }
  
  public void init() {
    if (this.selectedQuest == null)
      this.selectedQuest = new Signal(); 
    if (this.bodyAnimator == null)
      this.bodyAnimator = TweenAnimator.create().sequence(700.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start(); 
    if (this.panelAnimator == null)
      this.panelAnimator = TweenAnimator.create(); 
    this
      
      .panel = ContainerNode.create(-1920.0D, 0.0D, 1920.0D, 1080.0D).effect((NodeEffect)MaskNodeEffect.create(0.0D, 0.0D, 1920.0D, 1080.0D)).onInit(panel -> {
          Quest quest = (Quest)this.selectedQuest.getOrDefault();
          this.panel.clearChildren();
          if (this.panelAnimator.getValue() == 0.0F && quest != null) {
            this.panelAnimator.sequence(700.0F, 1.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
          } else if (this.panelAnimator.getValue() != 0.0F && quest == null) {
            this.panelAnimator.sequence(700.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
            return;
          } 
          if (quest == null)
            return; 
          QuestProgressData progress = this.playerData.getQuestProgress(quest);
          if (progress == null) {
            Minecraft.func_71410_x().func_147108_a(null);
            (new Notification(Notification.NotificationType.ERROR, PalapassTranslateEnum.ERROR_LOADING_PROGRESS_CONTENT.textOrDefault("Impossible de charger la progression de vos quêtes"), "palapass")).send();
            return;
          } 
          QuestTier questTier = null;
          if (!quest.getTiers().isEmpty()) {
            questTier = quest.getTiers().get(0);
            for (QuestTier t : quest.getTiers()) {
              if (progress.getProgress() < t.getAmount()) {
                questTier = t;
                break;
              } 
            } 
            if (questTier.getAmount() == ((QuestTier)quest.getTiers().get(0)).getAmount() && progress.getProgress() >= ((QuestTier)quest.getTiers().get(quest.getTiers().size() - 1)).getAmount())
              questTier = quest.getTiers().get(quest.getTiers().size() - 1); 
          } 
          BackgroundNode.create().attach(this.panel);
          TextNode.create(853.0D, 71.0D).text(Text.create((quest.getQuestDuration() == EnumQuestsDuration.DAILY) ? PalapassTranslateEnum.QUEST_DURATION_DAILY.textOrDefault("quête journalière") : PalapassTranslateEnum.QUEST_DURATION_SEASONAL.textOrDefault("quête saisonnière"), PaladiumText.TITLE.copy().fontSize(32.0F))).attach(this.panel);
          BackButtonNode.create(1823.0D, 68.0D).onClick(()).attach(panel);
          BookmarkNode.create(853.0D, 153.0D).icon("?").size(45.0D, 41.0D).attach(panel);
          ((TextNode)TextNode.create(920.0D, 143.0D).mode(TextMode.OVERFLOW).text(Text.create(TTT.format(quest.getText(), new Object[0]), PaladiumText.TITLE.copy().fontSize(40.0F), TextOverflow.DOT)).width(928.0D).onInit(())).attach(panel);
          String description = (questTier == null) ? TTT.format(quest.getDescription(), new Object[] { Integer.valueOf(quest.getQuantity()) }) : TTT.format(quest.getDescription(), new Object[] { Integer.valueOf(questTier.getAmount()) }).replace("{AMOUNT}", String.valueOf(questTier.getAmount()));
          TextNode.create(853.0D, 216.0D).mode(TextMode.SPLIT).text(Text.create(description, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 20.0F, Color.WHITE))).width(995.0D).attach(panel);
          TextNode.create(853.0D, 382.0D).text(Text.create(PalapassTranslateEnum.PROGRESSION.textOrDefault("progression"), PaladiumText.TITLE.copy().fontSize(40.0F))).attach(panel);
          String progression = progress.getProgress() + "/" + quest.getQuantity();
          float progressValue = (progress.getProgress() / quest.getQuantity());
          if (!quest.getTiers().isEmpty() && ((QuestTier)quest.getTiers().get(1)).getAmount() > 0) {
            progression = progress.getProgress() + "/" + questTier.getAmount();
            progressValue = (progress.getProgress() / questTier.getAmount());
          } 
          if (progress.isCompleted()) {
            progression = PalapassTranslateEnum.VALIDATE.textOrDefault("validé");
            progressValue = 1.0F;
          } 
          TextNode.create(1848.0D, 386.0D).text(Text.create(progression, TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 20.0F, Color.WHITE), Align.END)).anchorX(Align.END).attach(panel);
          ProgressNode.create(853.0D, 465.0D, 995.0D, 42.0D).color(new Color(94, 212, 42)).padding(4.0D).progress(progressValue).attach(panel);
          TextNode.create(853.0D, 559.0D).text(Text.create(PalapassTranslateEnum.GAIN.textOrDefault("gain"), PaladiumText.TITLE.copy().fontSize(40.0F))).attach(panel);
          if (quest.getTiers().isEmpty()) {
            FlexNode.horizontal(1848.0D, 563.0D, 47.0D).margin(20.0D).body(()).anchorX(Align.END).attach(panel);
          } else {
            for (int i = 0; i < quest.getTiers().size(); i++) {
              QuestTier tier = quest.getTiers().get(i);
              int iFinal = i;
              FlexNode.horizontal(1848.0D, (563 + i * 100), 47.0D).margin(10.0D).body(()).anchorX(Align.END).attach(panel);
            } 
          } 
          if (quest.getQuestType() == EnumQuestsType.ITEM_GIVE && !progress.isCompleted())
            FlexNode.horizontal(1350.0D, 980.0D, 42.0D).align(Align.CENTER).body(()).attach(panel); 
        }).watch(this.selectedQuest).attach(this);
    this
      
      .body = ContainerNode.create(-831.0D, 0.0D, 1920.0D, 1080.0D).effect((NodeEffect)MaskNodeEffect.create(0.0D, 0.0D, 1920.0D, 1080.0D)).body(body -> {
          BackgroundNode.create(0.0D, 35.0D, 761.0D, 1010.0D).attach(body);
          CloseButtonNode.create(715.0D, 63.0D).onClick(()).attach(body);
          TextNode.create(27.0D, 44.0D).text(Text.create(PalapassTranslateEnum.PALAPASS.textOrDefault("palapass"), PaladiumText.HEADER)).attach(body);
          TextNode.create(29.0D, 188.0D).text(Text.create(PalapassTranslateEnum.QUEST_DURATION_DAILY.textOrDefault("quête journalière"), PaladiumText.TITLE.copy().fontSize(32.0F))).attach(body);
          ContainerNode.create(29.0D, 253.0D, 678.0D, 388.0D).body(()).attach(body);
          TextNode.create(29.0D, 674.0D).text(Text.create(PalapassTranslateEnum.QUEST_DURATION_SEASONAL.textOrDefault("quête saisonnière"), PaladiumText.TITLE.copy().fontSize(32.0F))).attach(body);
          ContainerNode.create(0.0D, 728.0D, 707.0D, 290.0D).overflow(OverflowProperty.SCROLL).scrollbar((ScrollbarNode)ScrollbarNode.create(719.0D, 0.0D, 290.0D)).body(()).attach(body);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (this.bodyAnimator == null)
      return; 
    this.bodyAnimator.update();
    this.body.x(this.body.getDefaultX() + (831.0F * this.bodyAnimator.getValue()));
    this.panelAnimator.update();
    this.panel.x(this.panel.getDefaultX() + (1920.0F * this.panelAnimator.getValue()));
  }
  
  public void drawBackground(double mouseX, double mouseY, float ticks) {
    if (this.bodyAnimator == null || this.bodyAnimator.getValue() == 0.0F)
      return; 
    Color color = getData().getBackgroundColor();
    color.a = this.bodyAnimator.getValue() * 0.8F;
    DrawUtils.SHAPE.drawRect(0.0D, 0.0D, getWidth(), getHeight(), color);
  }
  
  public boolean close() {
    if (!this.closing) {
      this.closing = true;
      this.panelAnimator.sequence(700.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).start();
      this.bodyAnimator.sequence(700.0F, 0.0F, (TweenEquation)TweenEquations.QUART_INOUT).setCallback(tween -> ZUI.close(this)).start();
      return false;
    } 
    return true;
  }
  
  public boolean isSelected(Quest quest) {
    return (this.selectedQuest.getOrDefault() == quest);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\clien\\ui\popup\quest\UIPalapassQuestPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */