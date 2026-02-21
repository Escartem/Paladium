package fr.paladium.palajobs.client.ui.reward;

import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftBackNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.buttons.utils.MinecraftScrollableArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.lib.apollon.nodes.field.MinecraftTextFieldNode;
import fr.paladium.lib.apollon.nodes.flex.FlexDirection;
import fr.paladium.lib.apollon.nodes.flex.FlexNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftSubTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.client.ui.job.UIJob;
import fr.paladium.palajobs.client.ui.reward.node.JobRewardLevelNode;
import fr.paladium.palajobs.client.ui.utils.advancement.JobAdvancement;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

public class UIJobsRewards extends UI {
  private final JobAdvancement data;
  
  private MinecraftTextFieldNode search;
  
  private String lastSearch;
  
  private ScrollableArea area;
  
  private FlexNode flex;
  
  private final List<JobRewardLevelNode> rewards;
  
  public UIJobsRewards(JobAdvancement data) {
    super((GuiScreen)new UIJob(data), "palajobs:rewards");
    this.data = data;
    this.rewards = new LinkedList<>();
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)new MinecraftTitleNodeLabel(width(14.95F), height(14.07F), TTT.format("gui.jobs.title", new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(13.54F), height(30.0F), TTT.format("gui.jobs.rewards.level", new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(24.635F), height(30.0F), TTT.format("gui.jobs.rewards.reward", new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(40.3125F), height(30.0F), TTT.format("gui.jobs.rewards.craft", new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(64.6875F), height(30.0F), TTT.format("gui.jobs.rewards.usage", new Object[0])));
    addNode((ANode)(this.search = (new MinecraftTextFieldNode(width(71.77F), height(20.0F), width(12.96875F))).setPlaceholder(TTT.format("gui.jobs.rewards.search.placeholder", new Object[0]))));
    this.area = (ScrollableArea)MinecraftScrollableArea.builder().bounds(width(13.54F), height(34.35F), width(85.9375F), height(82.7F)).scrollbar(width(87.0F), height(34.35F), height(48.0F));
    addScrollableArea(this.area);
    this.flex = new FlexNode(width(13.54F), height(34.35F), width(72.3975F), height(48.35F), FlexDirection.COLUMN);
    this.flex.setMargin(height(0.5F));
    this.flex.setArea(this.area);
    addNode((ANode)this.flex);
    this.rewards.clear();
    for (int i = 0; i < JobExpUtils.getMaxLevel(); i++) {
      List<IReward> rewards = (List<IReward>)this.data.getRewards().getOrDefault(Integer.valueOf(i + 1), new ArrayList());
      this.rewards.add(new JobRewardLevelNode(0.0D, 0.0D, width(72.3975F), height(7.5925F), (this.data.getLevel() < i + 1), i + 1, rewards));
    } 
    this.rewards.forEach(this.flex::addChild);
    addNode((ANode)new MinecraftBackNode(width(83.0F), height(12.5F), this.prev));
    addNode((ANode)new MinecraftCloseNode(width(85.92375F), height(12.0F)));
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(79.16F), height(79.16F));
  }
  
  public void fixedUpdate() {
    if (this.lastSearch == null)
      this.lastSearch = this.search.getText(); 
    if (this.lastSearch != this.search.getText()) {
      this.flex.getChildren().clear();
      if (!this.search.getText().isEmpty()) {
        this.rewards.stream().filter(node -> node.match(this.search.getText())).forEach(this.flex::addChild);
      } else {
        this.rewards.forEach(this.flex::addChild);
      } 
      this.area.setScroll(0.0D);
      this.flex.setScroll(0.0D);
      this.flex.setY(this.flex.getDefaultY());
    } 
    this.lastSearch = this.search.getText();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\reward\UIJobsRewards.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */