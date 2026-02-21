package fr.paladium.palajobs.core.jobs;

import fr.paladium.palajobs.api.blacklist.IBlackListedItem;
import fr.paladium.palajobs.api.reward.IReward;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.RewardType;
import fr.paladium.palajobs.core.achievements.PalaJobsReachLevelAchievement;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.objectives.AbstractObjective;
import fr.paladium.palajobs.core.pojo.objectives.ObjectiveValue;
import fr.paladium.palajobs.core.registry.JobRegistry;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palajobs.utils.JobsUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public abstract class AbstractJob implements IAbstractJob {
  private final JobType type;
  
  private final List<AbstractObjective<?>> objectives;
  
  private final Map<Integer, List<IReward>> rewards;
  
  private final Map<Integer, Requirement<?, ?>> requirements;
  
  public JobType getType() {
    return this.type;
  }
  
  public List<AbstractObjective<?>> getObjectives() {
    return this.objectives;
  }
  
  public Map<Integer, List<IReward>> getRewards() {
    return this.rewards;
  }
  
  public Map<Integer, Requirement<?, ?>> getRequirements() {
    return this.requirements;
  }
  
  public AbstractJob(JobType type) {
    this.type = type;
    this.objectives = new ArrayList<>();
    this.rewards = new HashMap<>();
    this.requirements = new HashMap<>();
  }
  
  public void register() {
    registerObjectives();
    registerRewards();
    registerBlacklistedCrafts();
    registerBlacklistedUsages();
    registerLvlTokensRewards();
    registerRequirements();
    JobsManager manager = JobsManager.getInstance();
    importRewardsFromBlackListedItems(manager.getBlackListedCrafts(), RewardType.CRAFT);
    importRewardsFromBlackListedItems(manager.getBlackListedUsages(), RewardType.USAGE);
    JobRegistry.register(this);
  }
  
  public AbstractJob addReward(Integer level, IReward... from) {
    List<IReward> list = this.rewards.get(level);
    List<IReward> fromList = new ArrayList<>();
    for (IReward reward : from) {
      reward.setLevel(level.intValue());
      fromList.add(reward);
    } 
    if (list == null || list.isEmpty()) {
      this.rewards.put(level, fromList);
    } else {
      list.addAll(fromList);
    } 
    return this;
  }
  
  public void importRewardsFromBlackListedItems(List<IBlackListedItem> blackListedItems, RewardType type) {
    for (IBlackListedItem blackListedItem : blackListedItems) {
      int level = blackListedItem.getRequiredLevel();
      IReward reward = blackListedItem.buildReward(type);
      addReward(Integer.valueOf(level), new IReward[] { reward });
    } 
  }
  
  public ObjectiveValue buildValue(double gived, int requiredLevel) {
    return ObjectiveValue.builder().givedExperience(gived).requiredLevel(requiredLevel).build();
  }
  
  public AbstractJob addObjective(AbstractObjective<?> objective) {
    this.objectives.add(objective);
    return this;
  }
  
  public AbstractJob addObjectives(AbstractObjective<?>... objectives) {
    this.objectives.addAll(Arrays.asList(objectives));
    return this;
  }
  
  public AbstractJob addBlacklistedCraft(int level, ItemStack itemStack) {
    JobsManager.getInstance().registerBlackListedCraft(new BlackListedItem(level, getType(), itemStack));
    return this;
  }
  
  public AbstractJob addBlacklistedUsage(int level, ItemStack itemStack) {
    JobsManager.getInstance().registerBlackListedUsage(new BlackListedItem(level, getType(), itemStack));
    return this;
  }
  
  public AbstractJob addBlacklistedUsage(int level, ItemStack itemStack, List<String> hovers) {
    JobsManager.getInstance().registerBlackListedUsage(new BlackListedItem(level, getType(), itemStack, hovers));
    return this;
  }
  
  public AbstractJob addRequirement(Requirement<?, ?> requirement) {
    this.requirements.put(Integer.valueOf(requirement.getLevel()), requirement);
    return this;
  }
  
  public void onLevelUp(EntityPlayer player, JobsPlayer data, int level) {
    PalaJobsReachLevelAchievement.performCheck(player, this.type, level);
    JobsUtils.sendJobTitle(data, player, this.type);
    if (this.rewards.containsKey(Integer.valueOf(level)))
      ((List)this.rewards.get(Integer.valueOf(level))).forEach(r -> r.giveReward(player)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\jobs\AbstractJob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */