package fr.paladium.palapass.client.ui.popup.quest.node;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.progress.ProgressNode;
import fr.paladium.palapass.client.ui.node.PalapassIconNode;
import fr.paladium.palapass.client.ui.popup.quest.UIPalapassQuestPopup;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.QuestProgressData;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.pojo.quest.QuestTier;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import net.minecraft.util.ResourceLocation;

public class PalapassQuestNode extends Node {
  private static final ResourceLocation ARROW_TEXTURE = new ResourceLocation("palapass", "textures/gui/icons/arrow.png");
  
  private Quest quest;
  
  private QuestProgressData progress;
  
  protected PalapassQuestNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public static PalapassQuestNode create(double x, double y, double width, double height) {
    return new PalapassQuestNode(x, y, width, height);
  }
  
  public void init(UI ui) {
    QuestTier questTier = null;
    int tierIndex = 0;
    if (!this.quest.getTiers().isEmpty()) {
      questTier = this.quest.getTiers().get(0);
      for (QuestTier t : this.quest.getTiers()) {
        tierIndex++;
        if (this.progress.getProgress() < t.getAmount()) {
          questTier = t;
          break;
        } 
      } 
      if (questTier.getAmount() == ((QuestTier)this.quest.getTiers().get(0)).getAmount() && 
        this.progress.getProgress() >= ((QuestTier)this.quest.getTiers().get(this.quest.getTiers().size() - 1)).getAmount())
        questTier = this.quest.getTiers().get(this.quest.getTiers().size() - 1); 
    } 
    ((RectNode)RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(this.progress.isCompleted() ? new Color(28, 37, 31) : new Color(0.0F, 0.0F, 0.0F, 0.15F))
      .onInit(node -> {
          boolean isSelected = ((UIPalapassQuestPopup)getUi()).isSelected(this.quest);
          node.border((this.progress.isCompleted() && !isSelected) ? new Color(94, 212, 42) : Color.WHITE, this.progress.isCompleted() ? 2.0D : (isSelected ? 2.0D : 0.0D), true);
        })).watch(((UIPalapassQuestPopup)getUi()).getSelectedQuest())
      .attach(this);
    ImageNode.create(-20.0D, dh(2.0D) - 14.0D)
      .resource(ARROW_TEXTURE)
      .linear(false)
      .size(29.0D, 29.0D)
      .onInit(node -> node.visible(()))
      .watch(((UIPalapassQuestPopup)getUi()).getSelectedQuest())
      .attach(this);
    PalapassIconNode.create(28.0D, dh(2.0D) - 35.0D, 70.0D)
      .icon(this.quest.getIcon())
      .attach(this);
    TextNode.create(140.0D, 24.0D)
      .text(Text.create(TTT.format(this.quest.getText(), new Object[0]).toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 15.0F, Color.WHITE)))
      .attach(this);
    String description = (questTier == null) ? TTT.format(this.quest.getDescription(), new Object[] { Integer.valueOf(this.quest.getQuantity()) }) : TTT.format(this.quest.getDescription(), new Object[] { Integer.valueOf(questTier.getAmount()) }).replace("{AMOUNT}", String.valueOf(questTier.getAmount()));
    if (description.length() > 70)
      description = description.substring(0, 70) + "..."; 
    TextNode.create(140.0D, 49.0D)
      .mode(TextMode.SPLIT)
      .text(Text.create(description.toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 15.0F, Color.WHITE)))
      .width(466.0D)
      .attach(this);
    if (questTier == null) {
      ProgressNode.create(140.0D, 99.0D, 436.0D, 9.0D)
        .color(new Color(57, 255, 101))
        .progress(0.0F, this.quest.getQuantity(), (this.progress.getProgress() > this.quest.getQuantity()) ? this.quest.getQuantity() : this.progress.getProgress())
        .padding(2.0D)
        .attach(this);
      String text = this.progress.isCompleted() ? PalapassTranslateEnum.QUEST_COMPLETED.textOrDefault("validé") : PalapassTranslateEnum.QUEST_PROGRESS.textOrDefault(this.progress.getProgress() + "/" + this.quest.getQuantity(), new Object[] { Integer.valueOf(this.progress.getProgress()), Integer.valueOf(this.quest.getQuantity()) });
      TextNode.create(580.0D, 93.0D)
        .text(Text.create(text, TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 10.0F, Color.WHITE)))
        .attach(this);
    } else {
      ProgressNode.create(140.0D, 99.0D, 436.0D, 9.0D)
        .color(new Color(57, 255, 101))
        .progress(0.0F, questTier.getAmount(), (this.progress.getProgress() > questTier.getAmount()) ? questTier.getAmount() : this.progress.getProgress())
        .padding(2.0D)
        .attach(this);
      String text = this.progress.isCompleted() ? PalapassTranslateEnum.QUEST_COMPLETED.textOrDefault("validé") : PalapassTranslateEnum.QUEST_PROGRESS.textOrDefault(this.progress.getProgress() + "/" + questTier.getAmount(), new Object[] { Integer.valueOf(this.progress.getProgress()), Integer.valueOf(questTier.getAmount()) });
      TextNode.create(580.0D, 93.0D)
        .text(Text.create(text, TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 10.0F, Color.WHITE)))
        .attach(this);
      TextNode.create(aw(-7.0D), 7.0D)
        .text(
          
          Text.create(("Tier " + ((tierIndex == 1) ? PalapassTranslateEnum.TIER_I
            .textOrDefault("I") : ((tierIndex == 2) ? PalapassTranslateEnum.TIER_II.textOrDefault("II") : PalapassTranslateEnum.TIER_III.textOrDefault("III")))).toUpperCase(), 
            TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, 13.0F, Color.WHITE), Align.END))
        
        .anchorX(Align.END)
        .attach(this);
    } 
  }
  
  public PalapassQuestNode data(Quest quest, QuestProgressData progress) {
    this.quest = quest;
    this.progress = progress;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\clien\\ui\popup\quest\node\PalapassQuestNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */