package fr.paladium.palajobs.core.tokens;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.tokens.rewards.EnumLvlTokenRewardCategory;
import fr.paladium.palajobs.core.tokens.rewards.EnumLvlTokenRewardRarity;
import fr.paladium.palajobs.core.tokens.rewards.LvlTokenReward;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class LvlTokenRegistry {
  public static List<LvlTokenReward> lvlTokenRewards = new ArrayList<>();
  
  public static void register(LvlTokenReward lvlTokenReward) {
    if (lvlTokenReward.category == EnumLvlTokenRewardCategory.ITEM && lvlTokenReward.itemStack == null)
      lvlTokenReward.itemStack = new ItemStack(Items.field_151048_u); 
    lvlTokenRewards.add(lvlTokenReward);
  }
  
  public static List<LvlTokenReward> generateRewards(int level, JobType job) {
    List<LvlTokenReward> rewards = new ArrayList<>();
    EnumLvlTokenRewardRarity rarity = generateRarity(level);
    List<LvlTokenReward> filteredRewards = filterRewardsByJob(level, rarity, job);
    List<LvlTokenReward> uniqueRewards = drawUniqueRewards(filteredRewards, 3);
    rewards.addAll(uniqueRewards);
    return rewards;
  }
  
  private static EnumLvlTokenRewardRarity generateRarity(int lvl) {
    List<EnumLvlTokenRewardRarity> rarities = new ArrayList<>();
    Collections.addAll(rarities, EnumLvlTokenRewardRarity.values());
    if (lvlTokenRewards.stream().filter(c -> (c.lvl == lvl && c.rarity == EnumLvlTokenRewardRarity.COMMON)).count() < 3L)
      rarities.remove(rarities.indexOf(EnumLvlTokenRewardRarity.COMMON)); 
    if (lvlTokenRewards.stream().filter(c -> (c.lvl == lvl && c.rarity == EnumLvlTokenRewardRarity.RARE)).count() < 3L)
      rarities.remove(rarities.indexOf(EnumLvlTokenRewardRarity.RARE)); 
    int totalPercent = 0;
    List<EnumLvlTokenRewardRarity> listToDraw = new ArrayList<>();
    for (EnumLvlTokenRewardRarity r : rarities) {
      totalPercent += r.percent;
      for (int i = 0; i < r.percent; i++)
        listToDraw.add(r); 
    } 
    Random random = new Random();
    int rand = random.nextInt(totalPercent);
    return listToDraw.get(rand);
  }
  
  private static List<LvlTokenReward> filterRewardsByJob(int level, EnumLvlTokenRewardRarity rarity, JobType job) {
    List<LvlTokenReward> eligibleRewards = new ArrayList<>();
    for (LvlTokenReward reward : lvlTokenRewards) {
      if (reward.lvl == level && reward.rarity == rarity && reward.job.equals(job))
        eligibleRewards.add(reward); 
    } 
    Collections.shuffle(eligibleRewards);
    return eligibleRewards;
  }
  
  private static List<LvlTokenReward> drawUniqueRewards(List<LvlTokenReward> rewards, int numToDraw) {
    List<LvlTokenReward> uniqueRewards = new ArrayList<>();
    Random random = new Random();
    int i = 0;
    while (uniqueRewards.size() < numToDraw && !rewards.isEmpty() && i < 50) {
      int randomIndex = random.nextInt(rewards.size());
      LvlTokenReward drawnReward = rewards.get(randomIndex);
      if (!containsReward(uniqueRewards, drawnReward))
        uniqueRewards.add(drawnReward); 
      i++;
    } 
    return uniqueRewards;
  }
  
  private static boolean containsReward(List<LvlTokenReward> rewards, LvlTokenReward reward) {
    for (LvlTokenReward r : rewards) {
      if (r.id.equals(reward.id) || r.category == reward.category)
        return true; 
    } 
    return false;
  }
  
  public static void registerForJob(JobType job, LvlTokenReward reward) {
    reward.id = "job" + job.ordinal() + "-" + reward.id;
    register(reward);
  }
  
  public static void register() {
    for (JobType rewardedJob : JobType.values()) {
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-money-335", "335$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 335, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-money-350", "350$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 350, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-money-360", "360$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 360, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-money-375", "375$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 375, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-money-390", "390$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 390, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-money-400", "400$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 400, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-money-415", "415$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 415, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-xpjobs-650", "650 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 650, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-xpjobs-800", "800 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 800, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-xpjobs-1000", "1000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 1000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-xpjobs-1200", "1200 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 1200, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-xpjobs-1400", "1400 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 1400, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-xpjobs-1600", "1600 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 1600, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(2, "2-epic-xpjobs-2000", "2000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 2000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-money-565", "565", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 565, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-money-595", "595$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 595, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-money-610", "610$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 610, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-money-632", "632$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 632, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-money-650", "650$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 650, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-money-670", "670$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 670, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-money-700", "700$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 700, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-xpjobs-1500", "1500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 1500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-xpjobs-2000", "2000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 2000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-xpjobs-2500", "2500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 2500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-xpjobs-2600", "2600 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 2600, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-xpjobs-3250", "3250 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 3250, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-xpjobs-4000", "4000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 4000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(4, "4-epic-xpjobs-5000", "5000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 5000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-money-845", "845$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 845, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-money-885", "885$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 885, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-money-910", "910$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 910, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-money-940", "940$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 940, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-money-970", "970$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 970, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-money-1000", "1000$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 1000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-money-1035", "1035$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 1035, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-xpjobs-2500", "2500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 2500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-xpjobs-3250", "3250 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 3250, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-xpjobs-4000", "4000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 4000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-xpjobs-4225", "4225 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 4225, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-xpjobs-5200", "5200 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 5200, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-xpjobs-6500", "6500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 6500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(6, "6-epic-xpjobs-8000", "8000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 8000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-money-950", "950$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 950, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-money-1000", "1000$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-money-1030", "1030$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1030, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-money-1059", "1059$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 1059, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-money-1090", "1090$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 1090, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-money-1120", "1120$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 1120, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-money-1165", "1165$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 1165, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-xpjobs-3750", "3750 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 3750, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-xpjobs-4500", "4500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 4500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-xpjobs-5500", "5500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 5500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-xpjobs-5850", "5850 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 5850, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-xpjobs-7150", "7150 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 7150, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-xpjobs-9000", "9000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 9000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(8, "8-epic-xpjobs-11000", "11000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 11000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-money-1125", "1125$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1125, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-money-1175", "1175$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1175, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-money-1210", "1210$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1210, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-money-1248", "1248$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 1248, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-money-1285", "1285$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 1285, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-money-1320", "1320$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 1320, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-money-1370", "1370$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 1370, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-xpjobs-4500", "4500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 4500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-xpjobs-5500", "5500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 5500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-xpjobs-6500", "6500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 6500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-xpjobs-7150", "7150 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 7150, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-xpjobs-8450", "8450 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 8450, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-xpjobs-11000", "11000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 11000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(10, "10-epic-xpjobs-13000", "13000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 13000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-money-1410", "1410$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1410, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-money-1470", "1470$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1470, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-money-1520", "1520$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1520, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-money-1567", "1567$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 1567, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-money-1615", "1615$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 1615, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-money-1660", "1660$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 1660, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-money-1725", "1725$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 1725, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-xpjobs-6000", "6000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 6000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-xpjobs-7000", "7000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 7000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-xpjobs-8250", "8250 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 8250, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-xpjobs-9100", "9100 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 9100, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-xpjobs-10725", "10725 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 10725, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-xpjobs-14000", "14000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 14000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(11, "11-epic-xpjobs-16500", "16500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 16500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-money-1690", "1690$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1690, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-money-1765", "1765$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1765, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-money-1820", "1820$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1820, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-money-1878", "1878$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 1878, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-money-1935", "1935$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 1935, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-money-1990", "1990$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 1990, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-money-2065", "2065$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 2065, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-xpjobs-7000", "7000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 7000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-xpjobs-8250", "8250 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 8250, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-xpjobs-10000", "10000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 10000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-xpjobs-10725", "10725 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 10725, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-xpjobs-13000", "13000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 13000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-xpjobs-16500", "16500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 16500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(12, "12-epic-xpjobs-20000", "20000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 20000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-money-1965", "1965$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 1965, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-money-2050", "2050$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 2050, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-money-2120", "2120$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 2120, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-money-2183", "2183$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 2183, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-money-2250", "2250$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 2250, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-money-2315", "2315$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 2315, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-money-2400", "2400$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 2400, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-xpjobs-8000", "8000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 8000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-xpjobs-9500", "9500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 9500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-xpjobs-11500", "11500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 11500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-xpjobs-12350", "12350 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 12350, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-xpjobs-14950", "14950 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 14950, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-xpjobs-19000", "19000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 19000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(13, "13-epic-xpjobs-23000", "23000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 23000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-money-2240", "2240$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 2240, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-money-2340", "2340$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 2340, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-money-2415", "2415$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 2415, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-money-2491", "2491$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 2491, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-money-2565", "2565$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 2565, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-money-2640", "2640$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 2640, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-money-2740", "2740$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 2740, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-xpjobs-9000", "9000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 9000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-xpjobs-11000", "11000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 11000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-xpjobs-13250", "13250 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 13250, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-xpjobs-14300", "14300 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 14300, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-xpjobs-17225", "17225 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 17225, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-xpjobs-22000", "22000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 22000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(14, "14-epic-xpjobs-26500", "26500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 26500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-money-2530", "2530$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 2530, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-money-2645", "2645$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 2645, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-money-2730", "2730$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 2730, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-money-2812", "2812$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 2812, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-money-2900", "2900$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 2900, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-money-2980", "2980$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 2980, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-money-3100", "3100$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 3100, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-xpjobs-10500", "10500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 10500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-xpjobs-12500", "12500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 12500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-xpjobs-15000", "15000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 15000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-xpjobs-16250", "16250 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 16250, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-xpjobs-19500", "19500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 19500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-xpjobs-25000", "25000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 25000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(15, "15-epic-xpjobs-30000", "30000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 30000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-money-2790", "2790$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 2790, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-money-2915", "2915$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 2915, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-money-3010", "3010$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3010, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-money-3102", "3102$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 3102, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-money-3195", "3195$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 3195, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-money-3290", "3290$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 3290, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-money-3410", "3410$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 3410, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-xpjobs-11500", "11500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 11500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-xpjobs-13750", "13750 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 13750, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-xpjobs-16500", "16500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 16500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-xpjobs-17875", "17875 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 17875, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-xpjobs-21450", "21450 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 21450, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-xpjobs-27500", "27500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 27500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(16, "16-epic-xpjobs-33000", "33000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 33000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-money-3040", "3040$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3040, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-money-3175", "3175$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3175, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-money-3275", "3275$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3275, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-money-3376", "3376$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 3376, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-money-3480", "3480$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 3480, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-money-3580", "3580$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 3580, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-money-3710", "3710$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 3710, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-xpjobs-12500", "12500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 12500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-xpjobs-15000", "15000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 15000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-xpjobs-18000", "18000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 18000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-xpjobs-19500", "19500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 19500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-xpjobs-23400", "23400 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 23400, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-xpjobs-30000", "30000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 30000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(17, "17-epic-xpjobs-30000", "30000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 30000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-money-3200", "3200$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3200, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-money-3350", "3350$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3350, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-money-3455", "3455$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3455, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-money-3562", "3562$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 3562, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-money-3670", "3670$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 3670, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-money-3775", "3775$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 3775, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-money-3920", "3920$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 3920, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-xpjobs-13500", "13500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 13500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-xpjobs-16000", "16000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 16000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-xpjobs-19000", "19000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 19000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-xpjobs-20800", "20800 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 20800, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-xpjobs-24700", "24700 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 24700, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-xpjobs-32000", "32000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 32000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(18, "18-epic-xpjobs-38000", "38000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 38000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-money-3375", "3375$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3375, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-money-3525", "3525$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3525, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-money-3640", "3640$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3640, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-money-3750", "3750$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 3750, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-money-3865", "3865$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 3865, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-money-3975", "3975$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 3975, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-money-4125", "4125$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 4125, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-xpjobs-14000", "14000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 14000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-xpjobs-17000", "17000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 17000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-xpjobs-20000", "20000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 20000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-xpjobs-22100", "22100 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 22100, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-xpjobs-26000", "26000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 26000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-xpjobs-34000", "34000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 34000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(19, "19-epic-xpjobs-40000", "40000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 40000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-money-3590", "3590$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3590, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-money-3750", "3750$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3750, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-money-3870", "3870$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.COMMON, null, 3870, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-money-3991", "3991$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 3991, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-money-4110", "4110$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.RARE, null, 4110, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-money-4230", "4230$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 4230, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-money-4390", "4390$", EnumLvlTokenRewardCategory.MONEY, EnumLvlTokenRewardRarity.EPIC, null, 4390, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-xpjobs-14500", "14500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 14500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-xpjobs-17500", "17500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 17500, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-xpjobs-21250", "21250 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.COMMON, null, 21250, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-xpjobs-22750", "22750 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 22750, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-xpjobs-27625", "27625 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.RARE, null, 27625, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-xpjobs-35000", "35000 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 35000, rewardedJob));
      registerForJob(rewardedJob, new LvlTokenReward(20, "20-epic-xpjobs-42500", "42500 Job XP", EnumLvlTokenRewardCategory.XP_JOBS, EnumLvlTokenRewardRarity.EPIC, null, 42500, rewardedJob));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\tokens\LvlTokenRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */