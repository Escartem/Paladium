package fr.paladium.palapass.common.manager;

import fr.paladium.palaforgeutils.lib.bukkit.BukkitUtils;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palapass.common.achievement.PalapassReachPointsAchievement;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.network.packet.client.SCPacketPalapassOpenGui;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsDuration;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import fr.paladium.palapass.common.pojo.reward.RewardLevel;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Tuple;

public class PalapassManager {
  private static PalapassManager instance;
  
  public void setFirstMonth(int firstMonth) {
    this.firstMonth = firstMonth;
  }
  
  private final Map<String, Quest> dailyQuestsByUuid = new HashMap<>();
  
  public Map<String, Quest> getDailyQuestsByUuid() {
    return this.dailyQuestsByUuid;
  }
  
  private final Map<String, Quest> seasonQuestsByUuid = new HashMap<>();
  
  public Map<String, Quest> getSeasonQuestsByUuid() {
    return this.seasonQuestsByUuid;
  }
  
  private final Map<EnumQuestsType, List<Quest>> dailyQuests = new HashMap<>();
  
  public Map<EnumQuestsType, List<Quest>> getDailyQuests() {
    return this.dailyQuests;
  }
  
  private final Map<Integer, List<Quest>> seasonQuests = new HashMap<>();
  
  public Map<Integer, List<Quest>> getSeasonQuests() {
    return this.seasonQuests;
  }
  
  private final Map<Integer, List<RewardLevel>> rewards = new HashMap<>();
  
  private int firstMonth;
  
  public Map<Integer, List<RewardLevel>> getRewards() {
    return this.rewards;
  }
  
  public int getFirstMonth() {
    return this.firstMonth;
  }
  
  public PalapassManager() {
    instance = this;
  }
  
  public static void openPalapass(EntityPlayerMP player) {
    if (!BukkitUtils.hasPermission((Entity)player, "palapass.player"))
      return; 
    PalapassPlayer playerData = PalapassPlayer.get((EntityPlayer)player);
    playerData.refreshQuests();
    Set<Quest> dailyQuests = new HashSet<>();
    for (Quest quest : getInstance().dailyQuests()) {
      if (quest.getQuestDuration() != EnumQuestsDuration.DAILY || playerData.getQuestProgress(quest) == null)
        continue; 
      dailyQuests.add(quest);
    } 
    PalapassReachPointsAchievement.performCheck((EntityPlayer)player, playerData.getPoints());
    (new SCPacketPalapassOpenGui(
        getInstance().getRewards().get(Integer.valueOf(getMonth())), new ArrayList<>(dailyQuests), 
        
        getInstance().seasonQuests()))
      .send(player);
  }
  
  public Quest getQuestFromUUID(String uuid) {
    Quest dailyQuest = this.dailyQuestsByUuid.get(uuid);
    if (dailyQuest == null)
      return this.seasonQuestsByUuid.get(uuid); 
    return dailyQuest;
  }
  
  public static int getMonth() {
    return ((Integer)getDate().func_76340_b()).intValue();
  }
  
  public static int getDay() {
    return ((Integer)getDate().func_76341_a()).intValue();
  }
  
  public static Tuple getDate() {
    ZonedDateTime now = TimeUtils.nowZoned();
    int day = now.getDayOfMonth();
    int month = now.getMonthValue();
    return new Tuple(Integer.valueOf(day), Integer.valueOf(month));
  }
  
  public List<Quest> seasonQuests() {
    return this.seasonQuests.get(Integer.valueOf(getMonth()));
  }
  
  public List<Quest> dailyQuests() {
    return (List<Quest>)this.dailyQuests.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
  }
  
  public boolean isFirstMonth() {
    return (this.firstMonth == -1 || getMonth() == this.firstMonth);
  }
  
  public static PalapassManager getInstance() {
    if (instance == null)
      instance = new PalapassManager(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\manager\PalapassManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */