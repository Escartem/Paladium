package fr.paladium.palajobs.client.ui.lvltokens;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.abstracts.ANode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftBackNode;
import fr.paladium.lib.apollon.nodes.buttons.buttons.MinecraftCloseNode;
import fr.paladium.lib.apollon.nodes.label.MinecraftSubTitleNodeLabel;
import fr.paladium.lib.apollon.nodes.label.MinecraftTitleNodeLabel;
import fr.paladium.lib.apollon.ui.UI;
import fr.paladium.lib.apollon.utils.BackgroundHelper;
import fr.paladium.lib.aurelienribon.tweenengine.BaseTween;
import fr.paladium.lib.aurelienribon.tweenengine.Timeline;
import fr.paladium.lib.aurelienribon.tweenengine.Tween;
import fr.paladium.lib.aurelienribon.tweenengine.TweenCallback;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.client.ui.lvltokens.nodes.ButtonJobRewardChoice;
import fr.paladium.palajobs.client.ui.lvltokens.nodes.ButtonJobRewardHiddenReward;
import fr.paladium.palajobs.client.ui.lvltokens.nodes.ButtonJobRewardTokenSelector;
import fr.paladium.palajobs.client.ui.lvltokens.nodes.ButtonJobTokenSelector;
import fr.paladium.palajobs.client.ui.lvltokens.nodes.ButtonJobUseToken;
import fr.paladium.palajobs.core.packets.client.CSPacketChooseLvlTokenReward;
import fr.paladium.palajobs.core.packets.client.CSPacketUseLvlToken;
import fr.paladium.palajobs.core.tokens.LvlToken;
import fr.paladium.palajobs.core.tokens.LvlTokenRegistry;
import fr.paladium.palajobs.core.tokens.rewards.LvlTokenReward;
import fr.paladium.translate.common.texttotranslate.TTT;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.gui.GuiScreen;

public class UILvlToken extends UI {
  private List<LvlToken> lvlTokens;
  
  private JobType selectedJob;
  
  private String selectedReward = "";
  
  private long lastDiscovery = -1L;
  
  public UILvlToken(GuiScreen prev, List<LvlToken> lvlTokens) {
    super(prev, "palajobs:lvltokens");
    this.lvlTokens = lvlTokens;
  }
  
  public void func_73866_w_() {
    super.func_73866_w_();
    addNode((ANode)new MinecraftTitleNodeLabel(width(14.95F), height(14.07F), TTT.format("gui.jobs.title", new Object[0])));
    addNode((ANode)new MinecraftSubTitleNodeLabel(width(15.0F), height(23.0F), "JETONS DE Récompenses"));
    ButtonJobTokenSelector call2 = new ButtonJobTokenSelector(width(17.0F), height(28.0F), width(15.0F), height(8.0F), width(0.2D), "JETONS MINEUR", JobType.MINER, this.lvlTokens.stream().filter(j -> (j.getJobType() == JobType.MINER)).count());
    if (this.selectedJob == JobType.MINER)
      call2.selected = true; 
    call2.setCallback(c -> {
          if (this.selectedJob == JobType.MINER) {
            this.selectedJob = null;
          } else {
            this.selectedJob = JobType.MINER;
          } 
          func_73866_w_();
        });
    ButtonJobTokenSelector call1 = new ButtonJobTokenSelector(width(34.0F), height(28.0F), width(15.0F), height(8.0F), width(0.2D), "JETONS HUNTER", JobType.HUNTER, this.lvlTokens.stream().filter(j -> (j.getJobType() == JobType.HUNTER)).count());
    if (this.selectedJob == JobType.HUNTER)
      call1.selected = true; 
    call1.setCallback(c -> {
          if (this.selectedJob == JobType.HUNTER) {
            this.selectedJob = null;
          } else {
            this.selectedJob = JobType.HUNTER;
          } 
          func_73866_w_();
        });
    ButtonJobTokenSelector call4 = new ButtonJobTokenSelector(width(51.0F), height(28.0F), width(15.0F), height(8.0F), width(0.2D), "JETONS FARMER", JobType.FARMER, this.lvlTokens.stream().filter(j -> (j.getJobType() == JobType.FARMER)).count());
    if (this.selectedJob == JobType.FARMER)
      call4.selected = true; 
    call4.setCallback(c -> {
          if (this.selectedJob == JobType.FARMER) {
            this.selectedJob = null;
          } else {
            this.selectedJob = JobType.FARMER;
          } 
          func_73866_w_();
        });
    ButtonJobTokenSelector call3 = new ButtonJobTokenSelector(width(68.0F), height(28.0F), width(15.0F), height(8.0F), width(0.2D), "JETONS ALCHIMISTE", JobType.ALCHEMIST, this.lvlTokens.stream().filter(j -> (j.getJobType() == JobType.ALCHEMIST)).count());
    if (this.selectedJob == JobType.ALCHEMIST)
      call3.selected = true; 
    call3.setCallback(c -> {
          if (this.selectedJob == JobType.ALCHEMIST) {
            this.selectedJob = null;
          } else {
            this.selectedJob = JobType.ALCHEMIST;
          } 
          func_73866_w_();
        });
    addNode((ANode)call1);
    addNode((ANode)call2);
    addNode((ANode)call3);
    addNode((ANode)call4);
    boolean choiceBtn = true;
    boolean hasPendingReward = false;
    if (this.selectedJob != null) {
      hasPendingReward = (this.lvlTokens.stream().filter(lvl -> (lvl.getJobType() == this.selectedJob && lvl.getPendingRewards().size() > 0)).count() > 0L);
      if (hasPendingReward) {
        choiceBtn = false;
        List<ButtonJobRewardTokenSelector> rewardNodes = new ArrayList<>();
        Optional<LvlToken> minLvlToken = this.lvlTokens.stream().filter(lvlToken -> (lvlToken.getJobType() == this.selectedJob && lvlToken.getPendingRewards().size() > 0)).findFirst();
        if (minLvlToken.isPresent()) {
          LvlToken lvlToken = minLvlToken.get();
          int count = 0;
          for (String rewardUUID : lvlToken.getPendingRewards()) {
            int idx = LvlTokenRegistry.lvlTokenRewards.indexOf(new LvlTokenReward(rewardUUID));
            int offset = (lvlToken.getPendingRewards().size() == 2) ? 9 : 0;
            if (idx != -1) {
              LvlTokenReward reward = LvlTokenRegistry.lvlTokenRewards.get(idx);
              ButtonJobRewardTokenSelector node = new ButtonJobRewardTokenSelector(reward, width(offset + 23.0F + (count * 19)), height(40.0F), width(17.0F), width(17.0F), width(0.2F));
              if (reward.id.equalsIgnoreCase(this.selectedReward))
                node.selected = true; 
              node.setCallback(c -> {
                    if (this.selectedReward.equalsIgnoreCase(reward.id)) {
                      this.selectedReward = "";
                    } else {
                      this.selectedReward = reward.id;
                    } 
                    func_73866_w_();
                  });
              if (System.currentTimeMillis() - this.lastDiscovery < 500L)
                node.setScale(0.0F); 
              rewardNodes.add(node);
              addNode((ANode)node);
              count++;
            } 
          } 
          if (rewardNodes.size() > 0)
            (new Thread(() -> {
                  try {
                    Thread.sleep(100L);
                  } catch (Exception exception) {}
                  Timeline timeline = Timeline.createSequence().beginParallel();
                  for (ButtonJobRewardTokenSelector nodeReward : rewardNodes)
                    timeline.push(Tween.to(nodeReward, 3, 400.0F).target(1.0F)); 
                  ((Timeline)timeline.setCallback(new TweenCallback() {
                        public void onEvent(int arg0, BaseTween arg1) {}
                      })).end();
                  timeline.start(((ButtonJobRewardTokenSelector)rewardNodes.get(0)).getTweenManager());
                }"test")).start(); 
        } 
        ButtonJobRewardChoice confirmChoice = new ButtonJobRewardChoice(width(42.6F), height(78.0F), width(16.0F), height(5.0F));
        confirmChoice.setCallback(c -> {
              if (this.selectedJob != null) {
                LvlToken lvlToken = getNextLvlToken(this.selectedJob);
                if (lvlToken != null) {
                  PalaJobs.proxy.getNetwork().sendToServer((IMessage)new CSPacketChooseLvlTokenReward(lvlToken.uuid, this.selectedReward));
                  this.selectedReward = "";
                } else {
                  (new Notification(Notification.NotificationType.ERROR, "Vous n'avez aucun jeton en attente", "jobs")).send();
                } 
              } 
            });
        if (this.selectedReward.isEmpty())
          confirmChoice.setEnabled(false); 
        addNode((ANode)confirmChoice);
      } 
    } 
    if (!hasPendingReward) {
      ButtonJobRewardHiddenReward hiddenReward1 = new ButtonJobRewardHiddenReward(width(23.0F), height(40.0F), width(17.0F), width(17.0F));
      addNode((ANode)hiddenReward1);
      ButtonJobRewardHiddenReward hiddenReward2 = new ButtonJobRewardHiddenReward(width(42.0F), height(40.0F), width(17.0F), width(17.0F));
      addNode((ANode)hiddenReward2);
      ButtonJobRewardHiddenReward hiddenReward3 = new ButtonJobRewardHiddenReward(width(61.0F), height(40.0F), width(17.0F), width(17.0F));
      addNode((ANode)hiddenReward3);
      if (choiceBtn) {
        ButtonJobUseToken useTokenBtn = new ButtonJobUseToken(width(42.6F), height(78.0F), width(16.0F), height(5.0F), width(0.2F), "UTILISER", this.selectedJob, (this.selectedJob == null) ? 0L : this.lvlTokens.stream().filter(j -> (j.getJobType() == this.selectedJob)).count());
        useTokenBtn.setCallback(c -> {
              if (this.selectedJob != null) {
                LvlToken lvlToken = getNextLvlToken(this.selectedJob);
                if (lvlToken != null) {
                  (new Thread((), "test")).start();
                } else {
                  (new Notification(Notification.NotificationType.ERROR, "Vous n'avez aucun jeton en attente", "jobs")).send();
                } 
              } 
            });
        if (this.selectedJob == null)
          useTokenBtn.setEnabled(false); 
        addNode((ANode)useTokenBtn);
      } 
    } 
    addNode((ANode)new MinecraftBackNode(width(83.5F), height(12.5F), this.prev));
    addNode((ANode)new MinecraftCloseNode(width(85.92375F), height(12.0F)));
  }
  
  public LvlToken getNextLvlToken(JobType jobType) {
    List<LvlToken> lvlTokens = this.lvlTokens;
    Optional<LvlToken> minLvlToken = lvlTokens.stream().filter(lvlToken -> (lvlToken.getJobType() == jobType)).min((lvlToken1, lvlToken2) -> Integer.compare(lvlToken1.getLvl(), lvlToken2.getLvl()));
    if (minLvlToken.isPresent())
      return minLvlToken.get(); 
    return null;
  }
  
  public void preDraw(int mouseX, int mouseY, float ticks) {
    func_146276_q_();
    BackgroundHelper.createMinecraft(width(79.16F), height(79.16F));
  }
  
  public void setData(List<LvlToken> lvlTokens2) {
    this.lvlTokens = lvlTokens2;
    func_73866_w_();
  }
  
  public void setLastdiscovery(long currentTimeMillis) {
    this.lastDiscovery = System.currentTimeMillis();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\clien\\ui\lvltokens\UILvlToken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */