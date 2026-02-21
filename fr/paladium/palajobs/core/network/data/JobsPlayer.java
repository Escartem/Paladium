package fr.paladium.palajobs.core.network.data;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palajobs.PalaJobs;
import fr.paladium.palajobs.api.event.OnPlayerEarnXp;
import fr.paladium.palajobs.api.event.OnPlayerLevelUp;
import fr.paladium.palajobs.api.event.OnPlayerPreEarnXp;
import fr.paladium.palajobs.api.event.quest.PlayerFinishQuestEvent;
import fr.paladium.palajobs.api.event.quest.PlayerIncrementQuestEvent;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.quest.IQuestPlayerData;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.api.util.JobExpUtils;
import fr.paladium.palajobs.core.jobs.AbstractJob;
import fr.paladium.palajobs.core.jobs.Requirement;
import fr.paladium.palajobs.core.network.packet.server.SCPacketSyncExtendedJobsPlayerData;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import fr.paladium.palajobs.core.quest.QuestPlayerData;
import fr.paladium.palajobs.core.quest.QuestRegistry;
import fr.paladium.palajobs.core.tokens.LvlToken;
import fr.paladium.palajobs.core.utils.MultiplierUtils;
import fr.paladium.palajobs.core.utils.TimeUtil;
import fr.paladium.palajobs.server.managers.JobsManager;
import fr.paladium.palajobs.utils.JobsUtils;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.translate.common.texttotranslate.utils.TTTChat;
import java.time.ZonedDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.MinecraftForge;

public class JobsPlayer implements IExtendedEntityProperties, IJobsPlayer {
  public static final String PROP_NAME = "palajobs_JOBS";
  
  private Entity entity;
  
  private HashMap<Integer, Double> jobsStats;
  
  private HashMap<Integer, Long> jobsLastXP;
  
  private HashMap<Integer, Integer> jobsRequirements;
  
  private List<IQuestPlayerData> dailyQuests;
  
  private long refreshDailyQuestTime;
  
  private double xpMultiplier;
  
  private long doubleXpUntil;
  
  private double rankMultiplier;
  
  private JobType lastUpdatedJob;
  
  private List<LvlToken> lvlTokens;
  
  public Entity getEntity() {
    return this.entity;
  }
  
  public HashMap<Integer, Double> getJobsStats() {
    return this.jobsStats;
  }
  
  public HashMap<Integer, Long> getJobsLastXP() {
    return this.jobsLastXP;
  }
  
  public HashMap<Integer, Integer> getJobsRequirements() {
    return this.jobsRequirements;
  }
  
  public List<IQuestPlayerData> getDailyQuests() {
    return this.dailyQuests;
  }
  
  public long getRefreshDailyQuestTime() {
    return this.refreshDailyQuestTime;
  }
  
  public double getXpMultiplier() {
    return this.xpMultiplier;
  }
  
  public long getDoubleXpUntil() {
    return this.doubleXpUntil;
  }
  
  public double getRankMultiplier() {
    return this.rankMultiplier;
  }
  
  public JobType getLastUpdatedJob() {
    return this.lastUpdatedJob;
  }
  
  public List<LvlToken> getLvlTokens() {
    return this.lvlTokens;
  }
  
  private final Map<String, Map.Entry<JobType, Integer>> bossValue = new HashMap<>();
  
  public Map<String, Map.Entry<JobType, Integer>> getBossValue() {
    return this.bossValue;
  }
  
  public static void register() {
    MinecraftForge.EVENT_BUS.register(new ExtendedDataHandler());
  }
  
  public void init(Entity entity, World world) {
    this.jobsStats = new HashMap<>();
    this.jobsLastXP = new HashMap<>();
    this.jobsRequirements = new HashMap<>();
    this.dailyQuests = new ArrayList<>();
    this.lastUpdatedJob = JobType.MINER;
    this.entity = entity;
    this.lvlTokens = new ArrayList<>();
  }
  
  public void addXp(JobType type, ObjectiveType objectiveType, double xpStart, EntityPlayer p) {
    addXp(type, objectiveType, xpStart, p, MultiplierUtils.getMultiplierForPlayer(p, this).doubleValue());
  }
  
  public void addXp(JobType type, ObjectiveType objectiveType, double xpStart, EntityPlayer p, double mult) {
    int jobId = type.ordinal();
    double newXpValue = 0.0D;
    boolean levelUp = false;
    OnPlayerPreEarnXp onPlayerPreEarnXp = OnPlayerPreEarnXp.fireEvent(p, type, objectiveType, xpStart, mult);
    if (onPlayerPreEarnXp.isCanceled())
      return; 
    double xp = MultiplierUtils.getExperienceFromMultiplier(onPlayerPreEarnXp.xpearn, Double.valueOf(onPlayerPreEarnXp.multiplier));
    double oldXp = 0.0D;
    int oldLevel = 1;
    if (getJobsStats().containsKey(Integer.valueOf(jobId))) {
      oldXp = ((Double)getJobsStats().get(Integer.valueOf(jobId))).doubleValue();
      oldLevel = JobExpUtils.getLevel(oldXp);
      if (oldLevel == Integer.MAX_VALUE)
        return; 
      newXpValue = oldXp + xp;
      int newLevel = JobExpUtils.getLevel(newXpValue);
      if (oldLevel < newLevel) {
        for (int i = oldLevel + 1; i <= newLevel; i++) {
          if (!hasRequirement(type, i)) {
            newXpValue = JobExpUtils.getNeededXpForLvl(i) - 0.01D;
            newLevel = i - 1;
          } 
        } 
        double diff = newXpValue - oldXp;
        if (diff <= 0.0D)
          return; 
        xp = diff;
      } 
      OnPlayerEarnXp.fireEvent(p, type.getName(), xp);
      setExperience(type, newXpValue);
      if (oldLevel < newLevel) {
        for (int i = oldLevel + 1; i <= newLevel; i++) {
          OnPlayerLevelUp.fireEvent(p, type.getName(), i - 1, i);
          processTokenLvlCheck(i, type);
        } 
        levelUp = true;
      } 
    } else {
      newXpValue = xp;
      setExperience(type, newXpValue);
    } 
    if (levelUp && p instanceof EntityPlayerMP) {
      Optional<AbstractJob> job = JobsManager.getInstance().getJobByType(type);
      for (int i = oldLevel + 1; i <= JobExpUtils.getLevel(newXpValue); i++)
        ((AbstractJob)job.get()).onLevelUp(p, this, i); 
      S29PacketSoundEffect packet = new S29PacketSoundEffect("palajobs:palajobs.levelup", p.field_70165_t, p.field_70163_u, p.field_70161_v, 1.0F, 1.0F);
      ((EntityPlayerMP)p).field_71135_a.func_147359_a((Packet)packet);
      this.jobsRequirements.put(Integer.valueOf(jobId), Integer.valueOf(0));
    } 
    JobsUtils.sendJobNotification(p, type, JobExpUtils.getLevel(newXpValue), xp, mult);
    sync();
  }
  
  public boolean hasRequirement(JobType job, int level) {
    Optional<Requirement<?, ?>> optionalRequirement = getRequirement(job, level);
    if (!optionalRequirement.isPresent())
      return true; 
    if (!this.jobsRequirements.containsKey(Integer.valueOf(job.ordinal())))
      return false; 
    int completed = ((Integer)this.jobsRequirements.get(Integer.valueOf(job.ordinal()))).intValue();
    Requirement<?, ?> requirement = optionalRequirement.get();
    return (completed >= requirement.getValue());
  }
  
  public Optional<Requirement<?, ?>> getRequirement(JobType job, int level) {
    Optional<AbstractJob> optionalJob = JobsManager.getInstance().getJobByType(job);
    if (!optionalJob.isPresent())
      return Optional.empty(); 
    AbstractJob jobInstance = optionalJob.get();
    Requirement<?, ?> requirement = (Requirement<?, ?>)jobInstance.getRequirements().get(Integer.valueOf(level));
    if (requirement == null)
      return Optional.empty(); 
    return Optional.of(requirement);
  }
  
  public <T extends Requirement<?, ?>> Optional<T> getRequirement(JobType job, Class<T> clazz) {
    Optional<Requirement<?, ?>> optionalRequirement = getRequirement(job, getLevel(job) + 1);
    if (!optionalRequirement.isPresent())
      return Optional.empty(); 
    Requirement<?, ?> requirement = optionalRequirement.get();
    if (!clazz.isInstance(requirement))
      return Optional.empty(); 
    return Optional.of(clazz.cast(requirement));
  }
  
  public <T extends Requirement<?, ?>> List<Optional<T>> getRequirements(Class<T> clazz) {
    List<Optional<T>> requirements = new ArrayList<>();
    for (JobType jobType : JobType.values()) {
      Optional<T> requirement = getRequirement(jobType, clazz);
      if (requirement.isPresent())
        requirements.add(requirement); 
    } 
    return requirements;
  }
  
  public void incrementRequirementProgress(JobType job, int amount) {
    int jobId = job.ordinal();
    int oldProgress = ((Integer)this.jobsRequirements.getOrDefault(Integer.valueOf(jobId), Integer.valueOf(0))).intValue();
    int newProgress = oldProgress + amount;
    this.jobsRequirements.put(Integer.valueOf(jobId), Integer.valueOf(newProgress));
    sync();
  }
  
  private void processTokenLvlCheck(int lvl, JobType type) {
    int[] rewardedLvls = { 
        2, 4, 6, 8, 10, 11, 12, 13, 14, 15, 
        16, 17, 18, 19, 20 };
    boolean rewarded = Arrays.stream(rewardedLvls).anyMatch(l -> (l == lvl));
    if (rewarded)
      addLvlToken(lvl, type); 
  }
  
  public void incrementExperience(JobType type, double xp) {
    int jobId = type.ordinal();
    double oldXp = ((Double)getJobsStats().getOrDefault(Integer.valueOf(jobId), Double.valueOf(0.0D))).doubleValue();
    double total = Math.min(oldXp + xp, 9.223372036854776E18D);
    setExperience(type, total);
  }
  
  public void decrementExperience(JobType type, double xp) {
    int jobId = type.ordinal();
    double oldXp = ((Double)getJobsStats().getOrDefault(Integer.valueOf(jobId), Double.valueOf(0.0D))).doubleValue();
    double total = Math.max(oldXp - xp, 0.0D);
    setExperience(type, total);
  }
  
  public double getExperience(JobType type) {
    return ((Double)getJobsStats().getOrDefault(Integer.valueOf(type.ordinal()), Double.valueOf(0.0D))).doubleValue();
  }
  
  public void setExperience(JobType type, double xp) {
    int jobId = type.ordinal();
    getJobsStats().put(Integer.valueOf(jobId), Double.valueOf(xp));
    getJobsLastXP().put(Integer.valueOf(jobId), Long.valueOf(System.currentTimeMillis()));
    this.lastUpdatedJob = type;
    sync();
  }
  
  public int getLevel(JobType type) {
    return JobExpUtils.getLevel(getExperience(type));
  }
  
  public boolean setLevel(JobType type, int level) {
    setExperience(type, JobExpUtils.getNeededXpForLvl(level));
    return true;
  }
  
  public void resetExperience(JobType type) {
    setExperience(type, 0.0D);
  }
  
  public void reset() {
    this.jobsStats.forEach((k, v) -> (Double)this.jobsStats.put(k, Double.valueOf(0.0D)));
    this.jobsLastXP.forEach((k, v) -> (Long)this.jobsLastXP.put(k, Long.valueOf(Long.MAX_VALUE)));
    this.jobsRequirements.forEach((k, v) -> (Integer)this.jobsRequirements.put(k, Integer.valueOf(0)));
    sync();
  }
  
  public void checkDailyQuests() {
    ZonedDateTime now = TimeUtil.nowZoned();
    ZonedDateTime lastRefresh = TimeUtil.fromlong(this.refreshDailyQuestTime);
    if (this.dailyQuests.isEmpty() || now.getDayOfYear() != lastRefresh.getDayOfYear())
      refreshDailyQuests(); 
  }
  
  public void incrementQuestsStats(AbstractQuest quest, int nb, EntityPlayer p) {
    QuestPlayerData idx = new QuestPlayerData(quest.getId());
    if (this.dailyQuests.contains(idx)) {
      IQuestPlayerData questPlayerData = this.dailyQuests.get(this.dailyQuests.indexOf(idx));
      if (questPlayerData.isCompleted())
        return; 
      questPlayerData.progress(nb);
      MinecraftForge.EVENT_BUS.post((Event)new PlayerIncrementQuestEvent(p, nb, quest, questPlayerData));
      if (questPlayerData.getProgress() >= quest.getQuantity()) {
        MinecraftForge.EVENT_BUS.post((Event)new PlayerFinishQuestEvent(p, quest, questPlayerData));
        questPlayerData.setCompleted(true);
        questPlayerData.setProgress(quest.getQuantity());
        (new Notification(Notification.NotificationType.INFO, quest.getName() + " - " + TTT.format(p, "notification.jobs.quest", new Object[0]), "jobs")).send((EntityPlayerMP)this.entity);
        addXp(quest.getJob(), ObjectiveType.QUEST, quest.getEarnedExperience(), p);
        if (p instanceof EntityPlayerMP) {
          S29PacketSoundEffect packet = new S29PacketSoundEffect("palajobs:palajobs.quest.complete", p.field_70165_t, p.field_70163_u, p.field_70161_v, 1.0F, 1.0F);
          ((EntityPlayerMP)p).field_71135_a.func_147359_a((Packet)packet);
        } 
        checkMultiplier();
      } 
      sync();
    } 
  }
  
  public void setDoubleXpUntil(long expiration) {
    this.doubleXpUntil = expiration;
    sync();
  }
  
  public void setRankMultiplier(double value) {
    this.rankMultiplier = value;
    sync();
  }
  
  private void refreshDailyQuests() {
    if (!this.dailyQuests.isEmpty()) {
      long completedDailyQuests = this.dailyQuests.stream().filter(IQuestPlayerData::isCompleted).count();
      if (completedDailyQuests < 3L)
        if (this.xpMultiplier < 0.75D) {
          this.xpMultiplier = 0.0D;
        } else {
          this.xpMultiplier /= 2.0D;
        }  
    } 
    if (this.xpMultiplier >= 3.0D)
      this.xpMultiplier = 3.0D; 
    this.refreshDailyQuestTime = System.currentTimeMillis();
    this.dailyQuests = Lists.newArrayList();
    if (QuestRegistry.getInstance().getQuests().size() < 3)
      return; 
    while (this.dailyQuests.size() < 3) {
      AbstractQuest quest = pickupRandomUniqueQuest();
      if (containsQuest(quest.getId()))
        continue; 
      QuestPlayerData questPlayerData = new QuestPlayerData(quest.getId(), 0, false);
      this.dailyQuests.add(questPlayerData);
    } 
    sync();
  }
  
  private boolean containsQuest(String questId) {
    for (IQuestPlayerData quest : this.dailyQuests) {
      if (quest.getQuestId().equals(questId))
        return true; 
    } 
    return false;
  }
  
  private AbstractQuest pickupRandomUniqueQuest() {
    AbstractQuest quest;
    Random rdm = new Random();
    do {
      quest = QuestRegistry.getInstance().getQuests().get(rdm.nextInt(QuestRegistry.getInstance().getQuests().size()));
    } while (this.dailyQuests.contains(new QuestPlayerData(quest.getId())));
    return quest;
  }
  
  public void checkMultiplier() {
    long completedDailyQuests = this.dailyQuests.stream().filter(IQuestPlayerData::isCompleted).count();
    if (completedDailyQuests >= 3L) {
      if (this.xpMultiplier >= 3.0D) {
        this.xpMultiplier = 3.0D;
        return;
      } 
      if (this.xpMultiplier < 0.0D)
        this.xpMultiplier = 0.0D; 
      this.xpMultiplier++;
      TTTChat.sendMessage((EntityPlayer)this.entity, "message.jobs.multiplier", new Object[] { String.format("%.2f", new Object[] { Double.valueOf(this.xpMultiplier) }) });
    } 
  }
  
  public IQuestPlayerData getQuest(String id) {
    for (IQuestPlayerData quest : this.dailyQuests) {
      if (!quest.getQuestId().equals(id))
        continue; 
      return quest;
    } 
    return null;
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound nbt = new NBTTagCompound();
    NBTTagList nbtlist = new NBTTagList();
    for (Map.Entry<Integer, Double> entry : getJobsStats().entrySet()) {
      NBTTagCompound stat = new NBTTagCompound();
      stat.func_74768_a("idJob", ((Integer)entry.getKey()).intValue());
      stat.func_74780_a("xp", ((Double)entry.getValue()).doubleValue());
      nbtlist.func_74742_a((NBTBase)stat);
    } 
    nbt.func_74782_a("stats", (NBTBase)nbtlist);
    NBTTagList nbtlist2 = new NBTTagList();
    for (Map.Entry<Integer, Long> entry : getJobsLastXP().entrySet()) {
      NBTTagCompound stat = new NBTTagCompound();
      stat.func_74768_a("idJob", ((Integer)entry.getKey()).intValue());
      stat.func_74772_a("timestamp", ((Long)entry.getValue()).longValue());
      nbtlist2.func_74742_a((NBTBase)stat);
    } 
    nbt.func_74782_a("statsLastXp", (NBTBase)nbtlist2);
    NBTTagList nbtlist3 = new NBTTagList();
    for (Map.Entry<Integer, Integer> entry : getJobsRequirements().entrySet()) {
      NBTTagCompound stat = new NBTTagCompound();
      stat.func_74768_a("idJob", ((Integer)entry.getKey()).intValue());
      stat.func_74768_a("requirement", ((Integer)entry.getValue()).intValue());
      nbtlist3.func_74742_a((NBTBase)stat);
    } 
    nbt.func_74782_a("statsRequirements", (NBTBase)nbtlist3);
    NBTTagList questsNbtList = new NBTTagList();
    for (IQuestPlayerData quest : this.dailyQuests) {
      NBTTagCompound questNbt = new NBTTagCompound();
      questNbt.func_74778_a("idQuest", quest.getQuestId());
      questNbt.func_74768_a("progress", quest.getProgress());
      questNbt.func_74757_a("completed", quest.isCompleted());
      questsNbtList.func_74742_a((NBTBase)questNbt);
    } 
    nbt.func_74782_a("quests", (NBTBase)questsNbtList);
    nbt.func_74772_a("refreshDailyQuestTime", this.refreshDailyQuestTime);
    nbt.func_74780_a("xpMultiplier", this.xpMultiplier);
    nbt.func_74772_a("doubleXpUntil", this.doubleXpUntil);
    nbt.func_74780_a("rankMultiplier", this.rankMultiplier);
    nbt.func_74774_a("lastUpdatedJob", (byte)this.lastUpdatedJob.ordinal());
    NBTTagList nbtLvlTokenList = new NBTTagList();
    for (LvlToken lvlToken : this.lvlTokens) {
      NBTTagCompound nbtLvlToken = new NBTTagCompound();
      nbtLvlToken.func_74778_a("uuid", lvlToken.getUuid());
      nbtLvlToken.func_74768_a("lvl", lvlToken.getLvl());
      nbtLvlToken.func_74768_a("type", lvlToken.getJobType().ordinal());
      NBTTagList nbtLvlTokenRewardList = new NBTTagList();
      for (String rewardId : lvlToken.getPendingRewards())
        nbtLvlTokenRewardList.func_74742_a((NBTBase)new NBTTagString(rewardId)); 
      nbtLvlToken.func_74782_a("pendingRewards", (NBTBase)nbtLvlTokenRewardList);
      nbtLvlTokenList.func_74742_a((NBTBase)nbtLvlToken);
    } 
    nbt.func_74782_a("lvlTokens", (NBTBase)nbtLvlTokenList);
    NBTTagList bossNbtList = new NBTTagList();
    for (Map.Entry<String, Map.Entry<JobType, Integer>> entry : this.bossValue.entrySet()) {
      NBTTagCompound nbtBossValue = new NBTTagCompound();
      nbtBossValue.func_74778_a("uuid", entry.getKey());
      nbtBossValue.func_74768_a("type", ((JobType)((Map.Entry)entry.getValue()).getKey()).ordinal());
      nbtBossValue.func_74768_a("value", ((Integer)((Map.Entry)entry.getValue()).getValue()).intValue());
      bossNbtList.func_74742_a((NBTBase)nbtBossValue);
    } 
    nbt.func_74782_a("bossValue", (NBTBase)bossNbtList);
    compound.func_74782_a("jobs", (NBTBase)nbt);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (compound.func_74764_b("jobs")) {
      NBTTagCompound nbt = (NBTTagCompound)compound.func_74781_a("jobs");
      NBTTagList nbtlist = nbt.func_150295_c("stats", 10);
      for (int i = 0; i < nbtlist.func_74745_c(); i++) {
        NBTTagCompound stat = nbtlist.func_150305_b(i);
        int id = stat.func_74762_e("idJob");
        double count = stat.func_74769_h("xp");
        this.jobsStats.put(Integer.valueOf(id), Double.valueOf(count));
      } 
      NBTTagList nbtlist2 = nbt.func_150295_c("statsLastXp", 10);
      for (int j = 0; j < nbtlist2.func_74745_c(); j++) {
        NBTTagCompound stat2 = nbtlist2.func_150305_b(j);
        int id = stat2.func_74762_e("idJob");
        long timestamp = stat2.func_74763_f("timestamp");
        this.jobsLastXP.put(Integer.valueOf(id), Long.valueOf(timestamp));
      } 
      NBTTagList nbtlist3 = nbt.func_150295_c("statsRequirements", 10);
      for (int k = 0; k < nbtlist3.func_74745_c(); k++) {
        NBTTagCompound stat3 = nbtlist3.func_150305_b(k);
        int id = stat3.func_74762_e("idJob");
        int requirement = stat3.func_74762_e("requirement");
        this.jobsRequirements.put(Integer.valueOf(id), Integer.valueOf(requirement));
      } 
      this.dailyQuests.clear();
      NBTTagList questsNbtList = nbt.func_150295_c("quests", 10);
      if (questsNbtList.func_74745_c() <= 3)
        for (int n = 0; n < questsNbtList.func_74745_c(); n++) {
          NBTTagCompound questNbt = questsNbtList.func_150305_b(n);
          String id = questNbt.func_74779_i("idQuest");
          int progress = questNbt.func_74762_e("progress");
          boolean completed = questNbt.func_74767_n("completed");
          if (!QuestRegistry.getInstance().getQuestById(id).isPresent()) {
            this.dailyQuests.clear();
            break;
          } 
          QuestPlayerData questPlayerData = new QuestPlayerData(id, progress, completed);
          this.dailyQuests.add(questPlayerData);
        }  
      this.refreshDailyQuestTime = nbt.func_74763_f("refreshDailyQuestTime");
      this.xpMultiplier = nbt.func_74769_h("xpMultiplier");
      this.doubleXpUntil = nbt.func_74763_f("doubleXpUntil");
      this.rankMultiplier = nbt.func_74769_h("rankMultiplier");
      if (nbt.func_74764_b("lastUpdatedJob"))
        this.lastUpdatedJob = JobType.values()[nbt.func_74771_c("lastUpdatedJob")]; 
      NBTTagList nbtLvlTokenList = nbt.func_150295_c("lvlTokens", 10);
      this.lvlTokens.clear();
      for (int m = 0; m < nbtLvlTokenList.func_74745_c(); m++) {
        NBTTagCompound lvlTokenNbt = nbtLvlTokenList.func_150305_b(m);
        int lvl = lvlTokenNbt.func_74762_e("lvl");
        JobType jobType = JobType.values()[lvlTokenNbt.func_74762_e("type")];
        String uuid = lvlTokenNbt.func_74779_i("uuid");
        NBTTagList stringList = lvlTokenNbt.func_150295_c("pendingRewards", 8);
        List<String> pendingRewards = new ArrayList<>();
        for (int x = 0; x < stringList.func_74745_c(); x++)
          pendingRewards.add(stringList.func_150307_f(x)); 
        LvlToken lvlToken = new LvlToken(uuid, lvl, jobType, pendingRewards);
        this.lvlTokens.add(lvlToken);
      } 
      if (nbt.func_74764_b("bossValue")) {
        NBTTagList bossNbtList = nbt.func_150295_c("bossValue", 10);
        this.bossValue.clear();
        for (int n = 0; n < bossNbtList.func_74745_c(); n++) {
          NBTTagCompound nbtBossValue = bossNbtList.func_150305_b(n);
          String uuid = nbtBossValue.func_74779_i("uuid");
          JobType type = JobType.values()[nbtBossValue.func_74762_e("type")];
          int value = nbtBossValue.func_74762_e("value");
          this.bossValue.put(uuid, new AbstractMap.SimpleEntry<>(type, Integer.valueOf(value)));
        } 
      } 
    } 
  }
  
  public void sync() {
    if (this.entity instanceof EntityPlayerMP) {
      NBTTagCompound nbt = new NBTTagCompound();
      saveNBTData(nbt);
      PalaJobs.proxy.getNetwork().sendTo((IMessage)new SCPacketSyncExtendedJobsPlayerData(nbt), (EntityPlayerMP)this.entity);
    } 
  }
  
  public static JobsPlayer get(Entity p) {
    return (JobsPlayer)p.getExtendedProperties("palajobs_JOBS");
  }
  
  public void addLvlToken(int lvl, JobType job) {
    this.lvlTokens.add(new LvlToken(lvl, job));
    sync();
  }
  
  public boolean canCraft(ItemStack stack) {
    return (stack.func_77973_b() == Items.field_151134_bR) ? ((JobsManager.getInstance().canUseCraftEnchantment((EntityPlayer)this.entity, this, stack) == null)) : ((JobsManager.getInstance().canUseCraft((EntityPlayer)this.entity, this, stack) == null));
  }
  
  public boolean canUseItem(ItemStack stack) {
    return (JobsManager.getInstance().canUseUseItem((EntityPlayer)this.entity, this, stack) == null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\network\data\JobsPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */