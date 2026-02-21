package fr.paladium.palajobs.client.ui.job;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftBackNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftHelpNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftTextCallToActionNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftSubTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.nodes.progressbar.MinecraftProgressBarNode;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.ui.UIPopup;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.palajobs.client.ui.home.UIJobsHome;
import fr.paladium.palajobs.client.ui.node.JobsProgressNode;
import fr.paladium.palajobs.client.ui.node.RewardNode;
import fr.paladium.palajobs.client.ui.objective.UIJobsObjectivesPopup;
import fr.paladium.palajobs.client.ui.reward.UIJobsRewards;
import fr.paladium.palajobs.client.ui.utils.advancement.JobAdvancement;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class UIJob extends UI {
  private static final ResourceLocation XP_ICON = new ResourceLocation("palajobs", "textures/gui/job/xp.png");
  
  private static final ResourceLocation REWARD_ICON = new ResourceLocation("palajobs", "textures/gui/job/reward.png");
  
  private final JobAdvancement data;
  
  public UIJob(JobAdvancement data) {
    super((GuiScreen)new UIJobsHome(), "palajobs:job");
    this.data = data;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)new MinecraftTitleNodeLabel(width(14.95F), height(14.07F), TTT.format("gui.jobs.title", new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(14.95F), height(27.0F), TTT.format(this.data.getJob().getType().getName(), new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(55.0F), height(24.2F), TTT.format("gui.jobs.job.level", new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(55.0F), height(48.0F), TTT.format("gui.jobs.job.reward", new Object[0])));
    addNode((ANode)(new TextNodeLabel(width(79.2F), height(28.5F), String.format("%s/%s", new Object[] { (new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.FRANCE))).format((int)this.data.getExperience()).replace(" ", "."), (new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.FRANCE))).format((int)this.data.getNextLevelExperience()).replace(" ", ".") }), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 0, Color.WHITE)).setTextAlign(TextAlign.RIGHT));
    addNode((ANode)(new MinecraftProgressBarNode(width(52.7F), height(32.0F), width(26.5F), height(3.0F), new Color(94, 212, 42))).setMin(0.0F).setValue((float)this.data.getExperience()).setMax((float)this.data.getNextLevelExperience()));
    if (this.data.getRequirement() != null) {
      addNode((ANode)new MinecraftSubTitleNodeLabel(width(52.7F), height(38.24F), TTT.format("pré-requis", new Object[0])));
      addNode((new MinecraftHelpNode(width(78.314995F), height(38.5F), width(0.885F), height(1.574F))).addHover(TTT.format("Obligatoire pour passer le niveau.", new Object[0])).setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
      TextNodeLabel descriptionLabel = new TextNodeLabel(width(52.7F), height(41.1F), this.data.getRequirement().getDescription(), Fonts.MONTSERRAT_SEMIBOLD.getFont(), 0, Color.WHITE);
      descriptionLabel.setWidth(width(25.0F));
      addNode((ANode)descriptionLabel);
      addNode((ANode)(new TextNodeLabel(width(79.2F), height(40.5F), String.format("%s/%s", new Object[] { (new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.FRANCE))).format(this.data.getRequirementProgress()).replace(" ", "."), (new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.FRANCE))).format(this.data.getRequirement().getValue()).replace(" ", ".") }), Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), 0, Color.WHITE)).setTextAlign(TextAlign.RIGHT));
      addNode((ANode)(new MinecraftProgressBarNode(width(52.7F), height(43.5F), width(26.5F), height(1.38F), new Color(94, 212, 42))).setMin(0.0F).setValue(this.data.getRequirementProgress()).setMax(this.data.getRequirement().getValue()));
    } 
    addNode((ANode)new JobsProgressNode(width(15.0F), height(31.0F), width(30.0F), this.data));
    addNode((ANode)new TextNodeLabel(width(54.5F), height(55.0F), TTT.format("gui.jobs.job.reward", new Object[0]).toUpperCase(), Fonts.MONTSERRAT_BOLD.getFont(), 30, Color.WHITE));
    addNode((ANode)new TextNodeLabel(width(54.5F), height(63.0F), TTT.format("gui.jobs.job.craft", new Object[0]).toUpperCase(), Fonts.MONTSERRAT_BOLD.getFont(), 30, Color.WHITE));
    addNode((ANode)new TextNodeLabel(width(54.5F), height(71.0F), TTT.format("gui.jobs.job.usage", new Object[0]).toUpperCase(), Fonts.MONTSERRAT_BOLD.getFont(), 30, Color.WHITE));
    List<IReward> rewards = (List<IReward>)this.data.getRewards().getOrDefault(Integer.valueOf(this.data.getLevel() + 1), new ArrayList());
    AtomicInteger index = new AtomicInteger(0);
    rewards.stream().filter(r -> (r.getType() == RewardType.GIVE || r.getType() == RewardType.MONEY_GIVE)).limit(6L).forEach(reward -> {
          addNode((ANode)new RewardNode(width(76.5F) - width(2.0F) * index.get(), height(54.0F), width(2.0F), width(2.0F), reward));
          index.getAndIncrement();
        });
    index.set(0);
    rewards.stream().filter(r -> (r.getType() == RewardType.CRAFT)).limit(6L).forEach(reward -> {
          addNode((ANode)new RewardNode(width(76.5F) - width(2.0F) * index.get(), height(62.0F), width(2.0F), width(2.0F), reward));
          index.getAndIncrement();
        });
    index.set(0);
    rewards.stream().filter(r -> (r.getType() == RewardType.USAGE)).limit(6L).forEach(reward -> {
          addNode((ANode)new RewardNode(width(76.5F) - width(2.0F) * index.get(), height(70.0F), width(2.0F), width(2.0F), reward));
          index.getAndIncrement();
        });
    addNode((new MinecraftTextCallToActionNode(width(53.0F), height(78.05F), width(25.9F), TTT.format("gui.jobs.job.rewards", new Object[0]).toUpperCase())).setCallback(node -> this.field_146297_k.func_147108_a((GuiScreen)new UIJobsRewards(this.data))));
    addNode((new MinecraftHelpNode(width(77.8F), height(23.65F)))
        .setCallback(node -> openPopup((UIPopup)new UIJobsObjectivesPopup((GuiScreen)this, this.data)))
        .addHover(TTT.format("gui.jobs.job.help", new Object[0]))
        .setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
    addNode((ANode)new MinecraftBackNode(width(83.0F), height(12.5F), this.prev));
    addNode((ANode)new MinecraftCloseNode(width(85.92375F), height(12.0F)));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(79.16F), height(79.16F));
    GuiUtils.drawImageTransparent(width(52.7F), height(23.24F), XP_ICON, width(2.0F), width(2.0F));
    GuiUtils.drawImageTransparent(width(52.7F), height(47.0F), REWARD_ICON, width(2.0F), width(2.0F));
    GuiUtils.drawRect(width(52.7F), height(52.0F), width(79.2F), height(59.5F), new Color(0.0F, 0.0F, 0.0F, 0.15F));
    GuiUtils.drawRect(width(52.7F), height(60.0F), width(79.2F), height(67.5F), new Color(0.0F, 0.0F, 0.0F, 0.15F));
    GuiUtils.drawRect(width(52.7F), height(68.0F), width(79.2F), height(75.5F), new Color(0.0F, 0.0F, 0.0F, 0.15F));
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, "LVL.", width(52.7F), height(28.79F), Color.WHITE, Fonts.MINECRAFT_DUNGEONS_REGULAR.getFont(), -20);
    GuiUtils.drawStringWithCustomFont(this.field_146297_k, String.valueOf(this.data.getLevel()), width(54.3F), height(27.85F), Color.WHITE, Fonts.MONTSERRAT_BLACK.getFont(), 150);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\job\UIJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */