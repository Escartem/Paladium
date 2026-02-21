package fr.paladium.palajobs.client.ui.home.node;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.AClickableNode;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.nodes.progressbar.MinecraftProgressBarNode;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.lib.apollon.utils.text.TextOverflow;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.QuestType;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.palajobs.client.ui.node.RewardNode;
import fr.paladium.palajobs.client.ui.utils.advancement.QuestAdvancement;
import fr.paladium.palajobs.core.packets.client.CSPalajobsGiveItemQuest;
import fr.paladium.palajobs.core.pojo.rewards.Reward;
import fr.paladium.palajobs.core.utils.ItemStackSerializer;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class JobsQuestNode extends AClickableNode {
  private static final ResourceLocation UNCHECKED = new ResourceLocation("palajobs", "textures/gui/home/quest/unchecked.png");
  
  private static final ResourceLocation CHECKED = new ResourceLocation("palajobs", "textures/gui/home/quest/checked.png");
  
  private static final Map<JobType, ResourceLocation> TEXTURES = new HashMap<>();
  
  private final ResourceLocation texture;
  
  private QuestAdvancement quest;
  
  static {
    TEXTURES.put(JobType.ALCHEMIST, new ResourceLocation("palajobs", "textures/gui/icons/alchimiste_icon.png"));
    TEXTURES.put(JobType.FARMER, new ResourceLocation("palajobs", "textures/gui/icons/farmer_icon.png"));
    TEXTURES.put(JobType.HUNTER, new ResourceLocation("palajobs", "textures/gui/icons/hunter_icon.png"));
    TEXTURES.put(JobType.MINER, new ResourceLocation("palajobs", "textures/gui/icons/miner_icon.png"));
  }
  
  public JobsQuestNode(double x, double y, double width, QuestAdvancement quest) {
    super(x, y, width, width * 0.29718D);
    this.texture = TEXTURES.get(quest.getJob());
    setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
    this.quest = quest;
    MinecraftProgressBarNode bar;
    addChild((ANode)(bar = (new MinecraftProgressBarNode(width(25.0F), height(67.0F), (width(67.0F) - GuiUtils.getStringWidth(Minecraft.func_71410_x(), this.quest.getAdvancement() + "/" + this.quest.getObjective(), Fonts.PIXEL_NES.getFont(), 20)), new Color(94, 212, 42))).setMin(0.0F).setValue(quest.getAdvancement()).setMax(quest.getObjective())));
    bar.setHeight(height(8.0F));
    double drawY = y + height(50.0F) - (width(17.0F) * 1.15F / 2.0F);
    TextNodeLabel questTitle = new TextNodeLabel(x + width(25.0F), drawY, this.quest.getName().toUpperCase(), Fonts.MONTSERRAT_BOLD.getFont(), 20, Color.WHITE);
    questTitle.setWidth(width(60));
    questTitle.setTextOverflow(TextOverflow.ELLIPSIS);
    questTitle.setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
    addChild((ANode)questTitle);
    String xp = String.valueOf((int)this.quest.getEarnedExperience());
    TextNodeLabel questXp = new TextNodeLabel(x + width(95.5F), y + height(14.0F), xp, Fonts.PIXEL_NES.getFont(), -10, Color.WHITE);
    questXp.setTextAlign(TextAlign.RIGHT);
    addChild((ANode)questXp);
    RewardNode rewardNode = new RewardNode((float)(x + width(8)), (float)(y + height(30.5F)), width(10), width(10), (IReward)Reward.builder().type(RewardType.GIVE).rewardItem(ItemStackSerializer.read(new String(Base64.getDecoder().decode(quest.getIcon())))).logo(ItemStackSerializer.read(new String(Base64.getDecoder().decode(quest.getIcon())))).build());
    if (!quest.isCompleted())
      addChild((ANode)rewardNode); 
    TextNodeLabel questProgress = new TextNodeLabel(x + width(95.5F), y + height(65.0F), this.quest.getAdvancement() + "/" + this.quest.getObjective(), Fonts.PIXEL_NES.getFont(), 20, Color.WHITE);
    questProgress.setTextAlign(TextAlign.RIGHT);
    addChild((ANode)questProgress);
    if (quest.getQuestType() != null && quest.getQuestType().equals(QuestType.ITEM_GIVE)) {
      addHover(quest.isCompleted() ? "§aQuête terminée" : "§eCliquez ici pour donner l'item");
      setCallback(c -> {
            if (!quest.isCompleted())
              PalaJobs.proxy.getNetwork().sendToServer((IMessage)new CSPalajobsGiveItemQuest(quest.getId())); 
          });
    } 
  }
  
  public void draw(Minecraft mc, int mouseX, int mouseY) {
    super.draw(mc, mouseX, mouseY);
    GuiUtils.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(0.0F, 0.0F, 0.0F, 0.15F));
    GuiUtils.drawRect(this.x + width(81.0F), this.y + height(7.0F), this.x + width(97.0F), this.y + height(29.0F), new Color(50, 50, 50));
    GuiUtils.drawBorder(this.x + width(81.0F), this.y + height(7.0F), this.x + width(97.0F), this.y + height(29.0F), new Color(50, 50, 50), width(0.7F));
    double drawY = this.y + height(50.0F) - (width(17.0F) * 1.15F / 2.0F);
    double checkboxSize = width(17.0F);
    GuiUtils.drawImageTransparent(this.x + width(5.0F), drawY, this.quest.isCompleted() ? CHECKED : UNCHECKED, checkboxSize, checkboxSize * 1.149999976158142D);
    List<String> description = GuiUtils.getSplittedString(mc, this.quest.getDescription().toUpperCase(), Fonts.MONTSERRAT_MEDIUM.getFont(), 20, width(50.0F));
    for (int i = 0; i < description.size(); i++) {
      String line = description.get(i);
      boolean shouldBreak = (i >= 1);
      if (shouldBreak)
        line = line.substring(0, line.length() - 3) + "..."; 
      GuiUtils.drawStringWithCustomFont(mc, line, this.x + width(25.0F), drawY + height(20.0F) + (height(15.0F) * i), Color.WHITE, Fonts.MONTSERRAT_MEDIUM.getFont(), 20);
      if (shouldBreak) {
        getHovers().clear();
        addHover(this.quest.getName());
        addHover("");
        addHover("» " + this.quest.getDescription());
        if (this.quest.getQuestType() != null && this.quest.getQuestType().equals(QuestType.ITEM_GIVE)) {
          addHover("");
          addHover(this.quest.isCompleted() ? "§aQuête terminée" : "§eCliquez ici pour donner l'item");
        } 
        break;
      } 
    } 
    GuiUtils.drawImageTransparent(this.x + width(82.5F), this.y + height(11.5F), this.texture, width(4.0F), width(4.0F), false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\home\node\JobsQuestNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */