package fr.paladium.palapass.common.network.data;

import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsDuration;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PalapassPlayer extends ExtendedEntityProperties {
  public static final String PROP_NAME = "palapass_PPass";
  
  private static final String TAG_NAME = "palapass";
  
  private int points;
  
  private int seasonMonth;
  
  private int seasonDay;
  
  public void setPoints(int points) {
    this.points = points;
  }
  
  public void setSeasonMonth(int seasonMonth) {
    this.seasonMonth = seasonMonth;
  }
  
  public void setSeasonDay(int seasonDay) {
    this.seasonDay = seasonDay;
  }
  
  public void setPremiumMonths(Set<Integer> premiumMonths) {
    this.premiumMonths = premiumMonths;
  }
  
  public void setQuestProgresses(List<QuestProgressData> questProgresses) {
    this.questProgresses = questProgresses;
  }
  
  public void setRewardsClaimed(List<String> rewardsClaimed) {
    this.rewardsClaimed = rewardsClaimed;
  }
  
  public int getPoints() {
    return this.points;
  }
  
  public int getSeasonMonth() {
    return this.seasonMonth;
  }
  
  public int getSeasonDay() {
    return this.seasonDay;
  }
  
  private Set<Integer> premiumMonths = new HashSet<>();
  
  public Set<Integer> getPremiumMonths() {
    return this.premiumMonths;
  }
  
  private List<QuestProgressData> questProgresses = new ArrayList<>();
  
  public List<QuestProgressData> getQuestProgresses() {
    return this.questProgresses;
  }
  
  private List<String> rewardsClaimed = new ArrayList<>();
  
  public List<String> getRewardsClaimed() {
    return this.rewardsClaimed;
  }
  
  public static PalapassPlayer get(EntityPlayer player) {
    return (PalapassPlayer)player.getExtendedProperties("palapass_PPass");
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74768_a("points", this.points);
    nbt.func_74768_a("seasonMonth", this.seasonMonth);
    nbt.func_74768_a("seasonDay", this.seasonDay);
    nbt.func_74768_a("premiumMonthsSize", this.premiumMonths.size());
    int i = 0;
    for (Iterator<Integer> iterator = this.premiumMonths.iterator(); iterator.hasNext(); ) {
      int premiumMonth = ((Integer)iterator.next()).intValue();
      nbt.func_74768_a("premiumMonth" + i, premiumMonth);
      i++;
    } 
    NBTTagList nbtQuestProgresses = new NBTTagList();
    for (QuestProgressData questProgress : this.questProgresses)
      nbtQuestProgresses.func_74742_a((NBTBase)questProgress.writeToNBT()); 
    nbt.func_74782_a("questProgresses", (NBTBase)nbtQuestProgresses);
    NBTTagList nbtRewardsClaimed = new NBTTagList();
    for (String r : this.rewardsClaimed) {
      NBTTagCompound value = new NBTTagCompound();
      value.func_74778_a("value", r);
      nbtRewardsClaimed.func_74742_a((NBTBase)value);
    } 
    nbt.func_74782_a("rewardsClaimed", (NBTBase)nbtRewardsClaimed);
    compound.func_74782_a("palapass", (NBTBase)nbt);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    NBTTagCompound nbt = compound.func_74775_l("palapass");
    this.points = nbt.func_74762_e("points");
    this.seasonMonth = nbt.func_74762_e("seasonMonth");
    this.seasonDay = nbt.func_74762_e("seasonDay");
    this.premiumMonths.clear();
    int premiumMonthsSize = nbt.func_74762_e("premiumMonthsSize");
    for (int i = 0; i < premiumMonthsSize; i++)
      this.premiumMonths.add(Integer.valueOf(nbt.func_74762_e("premiumMonth" + i))); 
    this.questProgresses.clear();
    if (nbt.func_150297_b("questProgresses", 9)) {
      NBTTagList nbtQuestProgresses = nbt.func_150295_c("questProgresses", 10);
      for (int j = 0; j < nbtQuestProgresses.func_74745_c(); j++) {
        NBTTagCompound questProgressCompound = nbtQuestProgresses.func_150305_b(j);
        QuestProgressData questProgress = new QuestProgressData();
        questProgress.readToNBT(questProgressCompound);
        this.questProgresses.add(questProgress);
      } 
    } 
    this.rewardsClaimed.clear();
    if (nbt.func_150297_b("rewardsClaimed", 9)) {
      NBTTagList nbtRewardsClaimed = nbt.func_150295_c("rewardsClaimed", 10);
      for (int j = 0; j < nbtRewardsClaimed.func_74745_c(); j++) {
        NBTTagCompound value = nbtRewardsClaimed.func_150305_b(j);
        String reward = value.func_74779_i("value");
        this.rewardsClaimed.add(reward);
      } 
    } 
  }
  
  public void refreshQuests() {
    int month = PalapassManager.getMonth();
    int day = PalapassManager.getDay();
    if (month != this.seasonMonth || this.questProgresses.isEmpty() || (month == 8 && this.questProgresses.stream().anyMatch(qp -> "2679e06c-775d-42d1-a6a3-b2beac46d2fe".equals(qp.getUuid())))) {
      refreshSeason();
    } else if (day != this.seasonDay) {
      refreshDay();
    } 
    this.seasonMonth = month;
    this.seasonDay = day;
    sync();
  }
  
  public boolean isPremium() {
    return this.premiumMonths.contains(Integer.valueOf(PalapassManager.getMonth()));
  }
  
  public void refreshDay() {
    this.questProgresses.removeIf(quest -> (quest.getDuration() == EnumQuestsDuration.DAILY));
    List<Quest> allQuests = PalapassManager.getInstance().dailyQuests();
    int iterations = 0;
    while (iterations < 10000 && this.questProgresses.stream().filter(q -> (q.getDuration() == EnumQuestsDuration.DAILY)).count() < 3L) {
      Quest randomQuest = allQuests.get((new Random()).nextInt(allQuests.size()));
      QuestProgressData questProgress = new QuestProgressData();
      questProgress.setUuid(randomQuest.getQuestUUID());
      questProgress.setCompleted(false);
      questProgress.setProgress(0);
      questProgress.setDuration(EnumQuestsDuration.DAILY);
      if (!this.questProgresses.contains(questProgress))
        this.questProgresses.add(questProgress); 
      iterations++;
    } 
    sync();
  }
  
  public void refreshSeason() {
    this.points = 0;
    this.questProgresses.clear();
    this.rewardsClaimed.clear();
    for (Quest quest : PalapassManager.getInstance().getSeasonQuests().get(Integer.valueOf(PalapassManager.getMonth()))) {
      QuestProgressData questProgress = new QuestProgressData();
      questProgress.setUuid(quest.getQuestUUID());
      questProgress.setCompleted(false);
      questProgress.setProgress(0);
      questProgress.setDuration(EnumQuestsDuration.SEASON);
      this.questProgresses.add(questProgress);
    } 
    refreshDay();
  }
  
  public List<Quest> getNonCompletedQuestsOfType(EnumQuestsType questType) {
    List<Quest> quests = new ArrayList<>();
    for (QuestProgressData questProgress : this.questProgresses) {
      Quest quest = PalapassManager.getInstance().getQuestFromUUID(questProgress.getUuid());
      if (questProgress == null || quest == null)
        continue; 
      if (!questProgress.isCompleted() && quest.getQuestType() == questType)
        quests.add(quest); 
    } 
    return quests;
  }
  
  public QuestProgressData getQuestProgress(Quest quest) {
    for (QuestProgressData questProgresses : this.questProgresses) {
      if (questProgresses.getUuid().equalsIgnoreCase(quest.getQuestUUID()))
        return questProgresses; 
    } 
    return null;
  }
  
  public void addPoints(int xp) {
    this.points += xp;
    sync();
  }
  
  public void addPremiumMonth() {
    addPremiumMonth(1);
  }
  
  public void addPremiumMonth(int count) {
    int currentMonth = PalapassManager.getMonth();
    for (int i = 0; i < count; i++) {
      int month = currentMonth + i;
      if (month > 12)
        month -= 12; 
      this.premiumMonths.add(Integer.valueOf(month));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\network\data\PalapassPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */