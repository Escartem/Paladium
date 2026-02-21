package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.TextNodeLabel;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.apollon.utils.Color;
import fr.paladium.lib.apollon.utils.Fonts;
import fr.paladium.lib.apollon.utils.text.TextAlign;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.client.ui.utils.JobUIData;
import fr.paladium.palajobs.client.ui.utils.advancement.JobAdvancement;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.nodes.JobsTransferorProgressNode;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class JobTransferorHomeUI extends UI {
  private static final int SELECTED_LIMIT = 2;
  
  private final JobUIData data;
  
  public JobTransferorHomeUI() {
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
    addNode((new JobsTransferorProgressNode(width(15.0F), height(35.185F), width(12.5F), this.data.getMinerAdvancement()))
        .setShowLevel(true)
        .setCallback(node -> {
            JobsTransferorProgressNode progressNode = (JobsTransferorProgressNode)node;
            progressNode.handleSelect();
          }).addHover(JobType.MINER.getPrefix() + TTT.format(JobType.MINER.getName(), new Object[0]).toUpperCase())
        .addHover("")
        .addHover(getAdvancementString(this.data.getMinerAdvancement()))
        .setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
    addNode((new JobsTransferorProgressNode(width(34.0F), height(35.185F), width(12.5F), this.data.getFarmerAdvancement()))
        .setShowLevel(true)
        .setCallback(node -> {
            JobsTransferorProgressNode progressNode = (JobsTransferorProgressNode)node;
            progressNode.handleSelect();
          }).addHover(JobType.FARMER.getPrefix() + TTT.format(JobType.FARMER.getName(), new Object[0]).toUpperCase())
        .addHover("")
        .addHover(getAdvancementString(this.data.getFarmerAdvancement()))
        .setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
    addNode((new JobsTransferorProgressNode(width(53.0F), height(35.185F), width(12.5F), this.data.getHunterAdvancement()))
        .setShowLevel(true)
        .setCallback(node -> {
            JobsTransferorProgressNode progressNode = (JobsTransferorProgressNode)node;
            progressNode.handleSelect();
          }).addHover(JobType.HUNTER.getPrefix() + TTT.format(JobType.HUNTER.getName(), new Object[0]).toUpperCase())
        .addHover("")
        .addHover(getAdvancementString(this.data.getHunterAdvancement()))
        .setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
    addNode((new JobsTransferorProgressNode(width(72.0F), height(35.185F), width(12.5F), this.data.getAlchemistAdvancement()))
        .setShowLevel(true)
        .setCallback(node -> {
            JobsTransferorProgressNode progressNode = (JobsTransferorProgressNode)node;
            progressNode.handleSelect();
          }).addHover(JobType.ALCHEMIST.getPrefix() + TTT.format(JobType.ALCHEMIST.getName(), new Object[0]).toUpperCase())
        .addHover("")
        .addHover(getAdvancementString(this.data.getAlchemistAdvancement()))
        .setHoverFont(Fonts.MONTSERRAT_BOLD.getFont()));
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
    List<JobsTransferorProgressNode> selectedNodes = getSelectedNodes();
    if (selectedNodes.size() == 2)
      this.field_146297_k.func_147108_a((GuiScreen)new JobTransferorUI(selectedNodes, this.data)); 
  }
  
  public List<JobsTransferorProgressNode> getSelectedNodes() {
    int count = 0;
    List<JobsTransferorProgressNode> selectedNodes = new ArrayList<>();
    for (ANode node : getNodes()) {
      if (!(node instanceof JobsTransferorProgressNode))
        continue; 
      JobsTransferorProgressNode progressNode = (JobsTransferorProgressNode)node;
      if (progressNode.isSelected()) {
        count++;
        selectedNodes.add(progressNode);
        if (count >= 2)
          break; 
      } 
    } 
    selectedNodes.sort(Comparator.comparingLong(JobsTransferorProgressNode::getSelectedMillis));
    return selectedNodes;
  }
  
  private String getAdvancementString(JobAdvancement advancement) {
    if (advancement.getLevel() == Integer.MAX_VALUE)
      return TTT.format("gui.jobs.home.levelmax", new Object[0]); 
    return "» " + (int)advancement.getExperience() + "/" + (int)advancement.getNextLevelExperience();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\clien\\uis\JobTransferorHomeUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */