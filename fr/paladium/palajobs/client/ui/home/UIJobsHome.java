package fr.paladium.palajobs.client.ui.home;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftHexaNode;
import fr.paladium.lib.apollon.nodes.flex.FlexDirection;
import fr.paladium.lib.apollon.nodes.flex.FlexNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.GuiUtils;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.client.ui.home.node.JobsQuestNode;
import fr.paladium.palajobs.client.ui.job.UIJob;
import fr.paladium.palajobs.client.ui.node.JobsProgressNode;
import fr.paladium.palajobs.client.ui.utils.JobUIData;
import fr.paladium.palajobs.client.ui.utils.advancement.JobAdvancement;
import fr.paladium.palajobs.client.ui.utils.advancement.QuestAdvancement;
import fr.paladium.palajobs.core.packets.client.CSPacketOpenBossGui;
import fr.paladium.palajobs.core.packets.client.CSPacketOpenLvlTokenGui;
import fr.paladium.translate.common.texttotranslate.TTT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class UIJobsHome extends UI {
  private static final ResourceLocation BOSS_ICON = new ResourceLocation("palajobs", "textures/gui/home/boss.png");
  
  private static final ResourceLocation TOKENS_ICON = new ResourceLocation("palajobs", "textures/gui/home/tokens.png");
  
  private static final ResourceLocation TOKEN_NOTIFICATION = new ResourceLocation("palajobs", "textures/gui/tokens/notification.png");
  
  private final JobUIData data;
  
  public UIJobsHome() {
    super(null, "palajobs:home");
    this.data = new JobUIData((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g);
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)new MinecraftTitleNodeLabel(width(14.95F), height(14.07F), TTT.format("gui.jobs.title", new Object[0])));
    TextNodeLabel label;
    addNode((ANode)(label = (new TextNodeLabel(width(13.0F), height(29.2F), TTT.format(JobType.MINER.getName(), new Object[0]), Fonts.PIXEL_NES.getFont(), 80, Color.WHITE)).setTextAlign(TextAlign.CENTER)));
    label.setWidth(width(16.5F));
    addNode((ANode)(label = (new TextNodeLabel(width(32.0F), height(29.2F), TTT.format(JobType.FARMER.getName(), new Object[0]), Fonts.PIXEL_NES.getFont(), 80, Color.WHITE)).setTextAlign(TextAlign.CENTER)));
    label.setWidth(width(16.5F));
    addNode((ANode)(label = (new TextNodeLabel(width(51.0F), height(29.2F), TTT.format(JobType.HUNTER.getName(), new Object[0]), Fonts.PIXEL_NES.getFont(), 80, Color.WHITE)).setTextAlign(TextAlign.CENTER)));
    label.setWidth(width(16.5F));
    addNode((ANode)(label = (new TextNodeLabel(width(70.0F), height(29.2F), TTT.format(JobType.ALCHEMIST.getName(), new Object[0]), Fonts.PIXEL_NES.getFont(), 80, Color.WHITE)).setTextAlign(TextAlign.CENTER)));
    label.setWidth(width(16.5F));
    addNode((new JobsProgressNode(width(15.0F), height(35.185F), width(12.5F), this.data.getMinerAdvancement()))
        .setShowLevel(true)
        .setCallback(node -> this.field_146297_k.func_147108_a((GuiScreen)new UIJob(this.data.getMinerAdvancement())))
        .addHover(JobType.MINER.getPrefix() + TTT.format(JobType.MINER.getName(), new Object[0]).toUpperCase())
        .addHover("")
        .addHover(getAdvancementString(this.data.getMinerAdvancement()))
        .setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
    addNode((new JobsProgressNode(width(34.0F), height(35.185F), width(12.5F), this.data.getFarmerAdvancement()))
        .setShowLevel(true)
        .setCallback(node -> this.field_146297_k.func_147108_a((GuiScreen)new UIJob(this.data.getFarmerAdvancement())))
        .addHover(JobType.FARMER.getPrefix() + TTT.format(JobType.FARMER.getName(), new Object[0]).toUpperCase())
        .addHover("")
        .addHover(getAdvancementString(this.data.getFarmerAdvancement()))
        .setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
    addNode((new JobsProgressNode(width(53.0F), height(35.185F), width(12.5F), this.data.getHunterAdvancement()))
        .setShowLevel(true)
        .setCallback(node -> this.field_146297_k.func_147108_a((GuiScreen)new UIJob(this.data.getHunterAdvancement())))
        .addHover(JobType.HUNTER.getPrefix() + TTT.format(JobType.HUNTER.getName(), new Object[0]).toUpperCase())
        .addHover("")
        .addHover(getAdvancementString(this.data.getHunterAdvancement()))
        .setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
    addNode((new JobsProgressNode(width(72.0F), height(35.185F), width(12.5F), this.data.getAlchemistAdvancement()))
        .setShowLevel(true)
        .setCallback(node -> this.field_146297_k.func_147108_a((GuiScreen)new UIJob(this.data.getAlchemistAdvancement())))
        .addHover(JobType.ALCHEMIST.getPrefix() + TTT.format(JobType.ALCHEMIST.getName(), new Object[0]).toUpperCase())
        .addHover("")
        .addHover(getAdvancementString(this.data.getAlchemistAdvancement()))
        .setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
    FlexNode questsNode = new FlexNode(width(13.33F), height(73.33F), 0.0D, height(13.4F), FlexDirection.ROW);
    questsNode.setMargin(width(1.05F));
    this.data.getQuests().stream().limit(3L).forEach(quest -> questsNode.addChild((ANode)new JobsQuestNode(0.0D, 0.0D, width(23.9583F), quest)));
    addNode((ANode)questsNode);
    addNode((new TextNodeLabel(width(62.5F), height(69.8F), formatDouble(this.data.getQuestMultiplier()) + "%", Fonts.MONTSERRAT_EXTRABOLD.getFont(), 40, Color.WHITE))
        .addHover(TTT.format("gui.jobs.home.quest.lore.1", new Object[0]))
        .addHover(TTT.format("gui.jobs.home.quest.lore.2", new Object[0]))
        .addHover(TTT.format("gui.jobs.home.quest.lore.3", new Object[0]))
        .addHover(TTT.format("gui.jobs.home.quest.lore.4", new Object[0]))
        .setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
    TextNodeLabel xpMultiplier = new TextNodeLabel(width(84.5F), height(69.8F), formatDouble(this.data.getMultiplier()) + "%", Fonts.MONTSERRAT_EXTRABOLD.getFont(), 40, Color.WHITE);
    xpMultiplier.setHoverFont(Fonts.MONTSERRAT_BOLD.getFont());
    xpMultiplier.addHover("§8[§a" + ((this.data.getDoubleXpUntil() > System.currentTimeMillis()) ? 100 : 0) + "%§8] §b" + TTT.format("gui.jobs.home.xp.lore.1", new Object[0]));
    xpMultiplier.addHover("");
    xpMultiplier.addHover("§8[§a" + formatDouble(this.data.getQuestMultiplier()) + "%§8] §b" + TTT.format("gui.jobs.home.xp.lore.2", new Object[0]));
    xpMultiplier.addHover("");
    xpMultiplier.addHover("§8[§a" + formatDouble(this.data.getRankMultiplier()) + "%§8] §b" + TTT.format("gui.jobs.home.xp.lore.3", new Object[0]));
    addNode((ANode)xpMultiplier);
    addNode((ANode)new TextNodeLabel(width(13.44F), height(70.0F), TTT.format("gui.jobs.home.quest", new Object[0]), Fonts.PIXEL_NES.getFont(), 60, Color.WHITE));
    addNode((ANode)(new TextNodeLabel(width(62.5F), height(69.8F), TTT.format("gui.jobs.home.bonus.quest", new Object[0]).toUpperCase(), Fonts.MONTSERRAT_MEDIUM.getFont(), 40, Color.WHITE)).setTextAlign(TextAlign.RIGHT));
    addNode((ANode)(new TextNodeLabel(width(84.5F), height(69.8F), TTT.format("gui.jobs.home.bonus.total", new Object[0]).toUpperCase(), Fonts.MONTSERRAT_MEDIUM.getFont(), 40, Color.WHITE)).setTextAlign(TextAlign.RIGHT));
    addNode((new MinecraftHexaNode(width(7.2F), height(43.843F), width(5.9375F), BOSS_ICON)).setBorder(true).setCallback(n -> PalaJobs.proxy.getNetwork().sendToServer((IMessage)new CSPacketOpenBossGui())));
    addNode((new MinecraftHexaNode(width(86.1F), height(43.843F), width(5.9375F), TOKENS_ICON)).setBorder(true).setCallback(n -> PalaJobs.proxy.getNetwork().sendToServer((IMessage)new CSPacketOpenLvlTokenGui())));
    addNode((ANode)new MinecraftCloseNode(width(85.92375F), height(12.0F)));
  }
  
  private String formatDouble(double value) {
    if (value % 1.0D != 0.0D)
      return String.format("%.1f", new Object[] { Double.valueOf(value) }); 
    return String.format("%.0f", new Object[] { Double.valueOf(value) });
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(79.16F), height(79.16F));
  }
  
  public void postDraw(int mouseX, int mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
    if (this.data.hasPendingToken)
      GuiUtils.drawImageTransparent(width(89.8F), height(44.8F), TOKEN_NOTIFICATION, width(1.5F), width(1.5F)); 
  }
  
  private String getAdvancementString(JobAdvancement advancement) {
    if (advancement.getLevel() == Integer.MAX_VALUE)
      return TTT.format("gui.jobs.home.levelmax", new Object[0]); 
    return "» " + (int)advancement.getExperience() + "/" + (int)advancement.getNextLevelExperience();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\home\UIJobsHome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */