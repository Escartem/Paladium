package fr.paladium.pet.common.network.data;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.helios.utils.TimeUtil;
import fr.paladium.palaforgeutils.lib.extended.ExtendedEntityProperties;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.pet.PetLogger;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.event.experience.PetEarnExperienceEvent;
import fr.paladium.pet.common.event.experience.PetExperienceSource;
import fr.paladium.pet.common.event.global.PetStatChangeEvent;
import fr.paladium.pet.common.event.global.UpdateStatType;
import fr.paladium.pet.common.event.happiness.PetEarnHappinessEvent;
import fr.paladium.pet.common.event.level.PetLevelUpEvent;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentNBTData;
import fr.paladium.pet.common.network.data.additional.roll.SkillRollData;
import fr.paladium.pet.common.network.data.additional.roll.SkillRollNBTData;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.common.network.data.additional.skill.SkillNBTData;
import fr.paladium.pet.common.store.provider.PetSkinShopProvider;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.AssignmentConfig;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import fr.paladium.pet.server.config.global.GlobalConfig;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import fr.paladium.pet.server.skill.skill.Skill;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import joptsimple.internal.Strings;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.StringUtils;

public class PetPlayer extends ExtendedEntityProperties {
  public static final String PROP_NAME = "palapet_PetPlayer";
  
  public static final String TAG_NAME = "PetPlayer";
  
  public static final int MAX_SLOTS = 20;
  
  public static final int MAX_LEVEL = 100;
  
  public static final int MAX_ASSIGNMENTS_TRY = 1000;
  
  private static final String TAG_LAST_CONNECTION_INCREMENT = "lastConnectionIncrement";
  
  private static final String TAG_EXPERIENCE = "experience";
  
  private static final String TAG_ASSIGNMENT_RESET_MILLIS = "assignmentResetMillis";
  
  private static final String TAG_HAPPINESS = "happiness";
  
  private static final String TAG_UNLOCKED_SKIN = "unlockedSkin";
  
  private static final String TAG_CURRENT_SKIN = "currentSkin";
  
  private static final String TAG_LAST_SKILL_USAGE = "lastSkillUsage";
  
  private static final String TAG_VISIBLE = "visible";
  
  private static final String TAG_SKILL_USES = "skillUses";
  
  private static final String TAG_SKILL_USES_ID = "skillUsesId";
  
  private static final String TAG_SKILL_USES_MILLIS = "skillUsesMillis";
  
  private static double[] experienceTable;
  
  private long lastConnectionIncrement;
  
  private double experience;
  
  private long assignmentResetMillis;
  
  private int happiness;
  
  private long lastSkillUsage;
  
  private String unlockedSkin;
  
  private String currentSkin;
  
  private boolean visible;
  
  private AssignmentNBTData assignmentData;
  
  private SkillNBTData skillData;
  
  private SkillRollNBTData skillRollData;
  
  private final HashMap<String, Long> skillUsesMap;
  
  public void setLastConnectionIncrement(long lastConnectionIncrement) {
    this.lastConnectionIncrement = lastConnectionIncrement;
  }
  
  public void setExperience(double experience) {
    this.experience = experience;
  }
  
  public void setAssignmentResetMillis(long assignmentResetMillis) {
    this.assignmentResetMillis = assignmentResetMillis;
  }
  
  public void setHappiness(int happiness) {
    this.happiness = happiness;
  }
  
  public void setLastSkillUsage(long lastSkillUsage) {
    this.lastSkillUsage = lastSkillUsage;
  }
  
  public void setUnlockedSkin(String unlockedSkin) {
    this.unlockedSkin = unlockedSkin;
  }
  
  public void setCurrentSkin(String currentSkin) {
    this.currentSkin = currentSkin;
  }
  
  public void setVisible(boolean visible) {
    this.visible = visible;
  }
  
  public void setAssignmentData(AssignmentNBTData assignmentData) {
    this.assignmentData = assignmentData;
  }
  
  public void setSkillData(SkillNBTData skillData) {
    this.skillData = skillData;
  }
  
  public void setSkillRollData(SkillRollNBTData skillRollData) {
    this.skillRollData = skillRollData;
  }
  
  public long getLastConnectionIncrement() {
    return this.lastConnectionIncrement;
  }
  
  public double getExperience() {
    return this.experience;
  }
  
  public long getAssignmentResetMillis() {
    return this.assignmentResetMillis;
  }
  
  public long getLastSkillUsage() {
    return this.lastSkillUsage;
  }
  
  public String getUnlockedSkin() {
    return this.unlockedSkin;
  }
  
  public String getCurrentSkin() {
    return this.currentSkin;
  }
  
  public boolean isVisible() {
    return this.visible;
  }
  
  public HashMap<String, Long> getSkillUsesMap() {
    return this.skillUsesMap;
  }
  
  public PetPlayer() {
    long now = TimeUtils.now();
    this.lastConnectionIncrement = now;
    this.experience = 0.0D;
    this.happiness = 0;
    this.assignmentResetMillis = now;
    this.visible = true;
    this.currentSkin = null;
    this.unlockedSkin = null;
    this.skillUsesMap = new HashMap<>();
  }
  
  public static PetPlayer get(EntityPlayer player) {
    return (PetPlayer)player.getExtendedProperties("palapet_PetPlayer");
  }
  
  public void saveNBTData(NBTTagCompound compound) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74772_a("lastConnectionIncrement", this.lastConnectionIncrement);
    nbt.func_74780_a("experience", this.experience);
    nbt.func_74772_a("assignmentResetMillis", this.assignmentResetMillis);
    nbt.func_74772_a("lastSkillUsage", this.lastSkillUsage);
    nbt.func_74768_a("happiness", this.happiness);
    nbt.func_74757_a("visible", this.visible);
    NBTTagList skillUses = new NBTTagList();
    this.skillUsesMap.forEach((skillId, millis) -> {
          NBTTagCompound skillUse = new NBTTagCompound();
          skillUse.func_74778_a("skillUsesId", skillId);
          skillUse.func_74772_a("skillUsesMillis", millis.longValue());
          skillUses.func_74742_a((NBTBase)skillUse);
        });
    nbt.func_74782_a("skillUses", (NBTBase)skillUses);
    getAssignmentData().write(nbt);
    getSkillData().write(this, nbt);
    getSkillRollData().write(nbt);
    if (this.currentSkin != null)
      nbt.func_74778_a("currentSkin", this.currentSkin); 
    if (this.unlockedSkin != null)
      nbt.func_74778_a("unlockedSkin", this.unlockedSkin); 
    compound.func_74782_a("PetPlayer", (NBTBase)nbt);
  }
  
  public void loadNBTData(NBTTagCompound compound) {
    if (!compound.func_74764_b("PetPlayer"))
      return; 
    NBTTagCompound nbt = compound.func_74775_l("PetPlayer");
    this.lastConnectionIncrement = nbt.func_74763_f("lastConnectionIncrement");
    this.experience = nbt.func_74769_h("experience");
    this.assignmentResetMillis = nbt.func_74763_f("assignmentResetMillis");
    this.lastSkillUsage = nbt.func_74763_f("lastSkillUsage");
    this.happiness = nbt.func_74762_e("happiness");
    this.visible = nbt.func_74767_n("visible");
    NBTTagList skillUses = nbt.func_150295_c("skillUses", 10);
    for (int i = 0; i < skillUses.func_74745_c(); i++) {
      NBTTagCompound skillUse = skillUses.func_150305_b(i);
      String skillId = skillUse.func_74779_i("skillUsesId");
      long millis = skillUse.func_74763_f("skillUsesMillis");
      this.skillUsesMap.put(skillId, Long.valueOf(millis));
    } 
    getAssignmentData().read(nbt);
    getSkillData().read(this, nbt);
    getSkillRollData().read(nbt);
    if (nbt.func_74764_b("currentSkin"))
      this.currentSkin = nbt.func_74779_i("currentSkin"); 
    if (nbt.func_74764_b("unlockedSkin"))
      this.unlockedSkin = nbt.func_74779_i("unlockedSkin"); 
  }
  
  public void putNextSkillUsage(String skillId, long millis) {
    this.skillUsesMap.put(skillId, Long.valueOf(millis));
  }
  
  public long getNextSkillUsage(Skill skill) {
    long now = System.currentTimeMillis();
    this.skillUsesMap.entrySet().removeIf(entry -> (((Long)entry.getValue()).longValue() < now));
    return ((Long)this.skillUsesMap.getOrDefault(skill.getId(), Long.valueOf(0L))).longValue();
  }
  
  public void tryResetAssignment(EntityPlayerMP player, long now) {
    ZonedDateTime nowDate = TimeUtil.nowZoned();
    ZonedDateTime lastResetDate = TimeUtil.fromLong(this.assignmentResetMillis);
    boolean isSameDay = (nowDate.getDayOfYear() == lastResetDate.getDayOfYear());
    if (getAssignmentData().getAssignments().isEmpty() || !isSameDay)
      pickupAssignments(player, now); 
  }
  
  public void reduceHappinessFromAssignments(EntityPlayerMP player) {
    Random random = new Random();
    HashMap<String, AssignmentData> assignments = getAssignmentData().getAssignments();
    int happinessLoss = GlobalConfig.get().getHappinessLoss();
    PassiveResponse response = PassiveSkillEnum.PET_HAPPINESS.getResponse(this);
    double value = response.getPersonalValue(this);
    List<AssignmentData> uncompletedAssignments = (List<AssignmentData>)assignments.values().stream().filter(data -> !data.isCompleted()).collect(Collectors.toList());
    if (uncompletedAssignments.isEmpty())
      return; 
    if (response.has(value)) {
      int amount = (int)Math.floor(value);
      for (int j = 0; j < amount && 
        !uncompletedAssignments.isEmpty(); j++) {
        AssignmentData randomAssignment = uncompletedAssignments.get(random.nextInt(uncompletedAssignments.size()));
        Optional<Assignment> result = AssignmentConfig.get().findAssignmentById(randomAssignment.getAssignmentId());
        if (result.isPresent()) {
          uncompletedAssignments.remove(randomAssignment);
          randomAssignment.complete(player, this, result.get());
        } 
      } 
    } 
    for (int i = 0; i < uncompletedAssignments.size(); i++)
      this.happiness -= happinessLoss; 
    if (this.happiness < 0)
      this.happiness = 0; 
  }
  
  public void pickupAssignments(EntityPlayerMP player, long now) {
    HashMap<String, AssignmentData> assignments = getAssignmentData().getAssignments();
    reduceHappinessFromAssignments(player);
    getAssignmentData().getAssignments().clear();
    this.assignmentResetMillis = now;
    Random random = new Random(now);
    AssignmentConfig config = AssignmentConfig.get();
    int maxAssignments = config.getMaxAssignmentsPerDay();
    int i = assignments.size();
    while (i < maxAssignments) {
      Assignment assignment = config.getRandomAssignment(random, getLevel());
      if (assignment == null)
        continue; 
      if (!isAssignmentInList(assignment.getType(), config))
        assignments.put(assignment.getId(), new AssignmentData(assignment.getId())); 
      i = assignments.size();
    } 
  }
  
  private boolean isAssignmentInList(AssignmentType type, AssignmentConfig config) {
    HashMap<String, AssignmentData> assignments = getAssignmentData().getAssignments();
    for (Map.Entry<String, AssignmentData> entry : assignments.entrySet()) {
      AssignmentData data = entry.getValue();
      Optional<Assignment> result = config.findAssignmentById(data.getAssignmentId());
      if (!result.isPresent())
        continue; 
      Assignment assignment = result.get();
      if (assignment.getType() == type)
        return true; 
    } 
    return false;
  }
  
  public Optional<Assignment> findAssignmentById(String id) {
    return AssignmentConfig.get().findAssignmentById(id);
  }
  
  public int setHappiness(EntityPlayer player, int happiness) {
    PetStatChangeEvent.call(player, UpdateStatType.HAPPINESS);
    if (happiness <= 0) {
      this.happiness = 0;
      return 0;
    } 
    int maxHappiness = getMaxHappiness();
    if (happiness > getMaxHappiness()) {
      this.happiness = maxHappiness;
      return maxHappiness;
    } 
    this.happiness = happiness;
    return happiness;
  }
  
  public void earnHappiness(EntityPlayerMP player, int amount) {
    if (amount <= 0) {
      PetLogger.error("Tried to earn negative happiness: " + amount);
      return;
    } 
    int maxHappiness = getMaxHappiness();
    if (this.happiness >= maxHappiness)
      return; 
    PetEarnHappinessEvent event = new PetEarnHappinessEvent((EntityPlayer)player, amount);
    if (MinecraftForge.EVENT_BUS.post((Event)event))
      return; 
    this.happiness += event.getAmount();
    if (this.happiness > maxHappiness)
      this.happiness = maxHappiness; 
  }
  
  public void earnExperience(EntityPlayer player, PetExperienceSource source, double amount) {
    if (amount <= 0.0D) {
      PetLogger.error("Tried to earn negative experience: " + amount + " from source: " + source.name());
      return;
    } 
    amount *= GlobalConfig.get().getExperienceMultiplier();
    PetEarnExperienceEvent event = new PetEarnExperienceEvent(player, source, amount);
    if (MinecraftForge.EVENT_BUS.post((Event)event))
      return; 
    int currentLevel = getLevel();
    int oldSlots = getSlots(currentLevel);
    this.experience += event.getAmount();
    int newLevel = getLevel();
    int newSlots = getSlots(newLevel);
    if (!hasLevelUp(currentLevel, newLevel))
      return; 
    MinecraftForge.EVENT_BUS.post((Event)new PetLevelUpEvent(player, source, currentLevel, newLevel, oldSlots, newSlots));
    getSkillData().refresh(this, player);
  }
  
  private boolean hasLevelUp(int currentLevel, int newLevel) {
    return (newLevel > currentLevel);
  }
  
  public double getMaxExperience() {
    return getRequiredExperience(getMaxLevel());
  }
  
  public double getRequiredExperience(int level) {
    if (level <= 0)
      return 0.0D; 
    if (experienceTable == null) {
      experienceTable = new double[101];
      Arrays.fill(experienceTable, -1.0D);
    } 
    if (experienceTable[level] >= 0.0D)
      return experienceTable[level]; 
    experienceTable[level] = (level * level - 1) * 90.0D / level + 300.0D + getRequiredExperience(level - 1);
    return (level * level - 1) * 90.0D / level + 300.0D + getRequiredExperience(level - 1);
  }
  
  public int getLevel() {
    int level = 0;
    while (getRequiredExperience(level) <= this.experience) {
      level++;
      if (level >= 100)
        return 100; 
    } 
    return level;
  }
  
  public int getMaxLevel() {
    return 100;
  }
  
  public double getSkillValue(Skill skill) {
    return getSkillValue(skill, null);
  }
  
  public double getSkillValue(Skill skill, EntityPlayerMP player) {
    if (skill == null)
      return 0.0D; 
    double defaultValue = skill.getValue() * getSkillMultiplier();
    return defaultValue;
  }
  
  public double getSkillMultiplier() {
    return GlobalConfig.get().getHappinessMultiplier(getHappiness());
  }
  
  public int getMaxHappiness() {
    return GlobalConfig.get().getMaxHappiness(getLevel());
  }
  
  public double getExperienceByJobLevelUp(int level) {
    if (level <= 0)
      return 0.0D; 
    return (level * 5) / 2.0D * 50.0D;
  }
  
  public int getSlots(int level) {
    if (level < 5)
      return 0; 
    int slots = level / 5;
    return Math.min(slots, 20);
  }
  
  public int getRequiredLevelForSlot(int index) {
    return (index + 1) * 5;
  }
  
  public boolean slotExist(int level, int slot) {
    return (slot >= 0 && slot <= getSlots(level));
  }
  
  public void onPlayerLoggedIn(EntityPlayerMP player) {
    GlobalConfig config = GlobalConfig.get();
    ZonedDateTime nowDate = TimeUtil.nowZoned();
    ZonedDateTime lastResetDate = TimeUtil.fromLong(this.lastConnectionIncrement);
    boolean isSameDay = (nowDate.getDayOfYear() == lastResetDate.getDayOfYear());
    if (isSameDay)
      return; 
    earnExperience((EntityPlayer)player, PetExperienceSource.CONNECTION, config.getExperiencePerConnection());
    this.lastConnectionIncrement = TimeUtils.now();
  }
  
  public SkillData getSkill(int slot) {
    Map<Integer, SkillData> skills = getSkillData().getSkills();
    if (skills == null)
      return null; 
    return skills.get(Integer.valueOf(slot));
  }
  
  public SkillRollData getSkillFromRoll(int slot) {
    return getSkillRollData().get(this, slot);
  }
  
  public SkillData getSkill(String id) {
    HashMap<Integer, SkillData> skills = getSkillData().getSkills();
    if (skills == null)
      return null; 
    for (Map.Entry<Integer, SkillData> entry : skills.entrySet()) {
      SkillData data = entry.getValue();
      if (data.getSkillId().equals(id))
        return data; 
    } 
    return null;
  }
  
  public boolean isSkillSelected(String skillName) {
    if (skillName == null)
      return false; 
    return (getSkill(skillName) != null);
  }
  
  public boolean isSkillSelected(Skill skill) {
    if (skill == null)
      return false; 
    return isSkillSelected(skill.getId());
  }
  
  public int getHappiness() {
    int level = getLevel();
    int maxHappiness = getMaxHappiness();
    int maxLevel = getMaxLevel();
    if (level >= maxLevel)
      return maxHappiness; 
    return Math.min(this.happiness, maxHappiness);
  }
  
  public boolean has() {
    return !StringUtils.isEmpty(this.unlockedSkin);
  }
  
  public void changePetName(EntityPlayerMP player, String name) {
    int maxLen = GlobalConfig.get().getMaxPetNameLength();
    if (Strings.isNullOrEmpty(name) || name.length() > maxLen) {
      PetTranslateEnum.MESSAGE_PET_NAME_TOO_LONG.message((ICommandSender)player, new Object[] { Integer.valueOf(maxLen) });
      return;
    } 
    PetTranslateEnum.MESSAGE_PET_NAME_CHANGED.message((ICommandSender)player, new Object[] { name });
    sync();
  }
  
  public void changePetSkin(EntityPlayerMP player, String skinId) {
    if (Strings.isNullOrEmpty(skinId) || !hasSkin((EntityPlayer)player, skinId)) {
      PetTranslateEnum.MESSAGE_PET_SKIN_CHANGED.message((ICommandSender)player, new Object[] { skinId });
      return;
    } 
    this.currentSkin = skinId;
    PetTranslateEnum.MESSAGE_PET_SKIN_CHANGED.message((ICommandSender)player, new Object[] { PetSkinShopProvider.getInstance().getTranslatedSkinName((EntityPlayer)player, skinId) });
    sync();
  }
  
  public boolean hasSkin(EntityPlayer player, String skinId) {
    return PetSkinShopProvider.getInstance().getSkins(player, this).contains(skinId);
  }
  
  public void unlock(EntityPlayerMP player, String id) {
    if (Strings.isNullOrEmpty(id))
      return; 
    this.currentSkin = id;
    this.unlockedSkin = id;
    sync();
  }
  
  public AssignmentNBTData getAssignmentData() {
    if (this.assignmentData == null)
      this.assignmentData = new AssignmentNBTData(); 
    return this.assignmentData;
  }
  
  public SkillNBTData getSkillData() {
    if (this.skillData == null)
      this.skillData = new SkillNBTData(); 
    return this.skillData;
  }
  
  public SkillRollNBTData getSkillRollData() {
    if (this.skillRollData == null)
      this.skillRollData = new SkillRollNBTData(); 
    return this.skillRollData;
  }
  
  public void addExperience(EntityPlayer player, double value) {
    setExperience(player, this.experience + value);
  }
  
  public void setExperience(EntityPlayer player, double value) {
    if (value <= 0.0D)
      value = 0.0D; 
    double maxExperience = getMaxExperience();
    value = Math.min(value, maxExperience);
    int oldLevel = getLevel();
    int oldSLots = getSlots(oldLevel);
    this.experience = value;
    int newLevel = getLevel();
    int newSlots = getSlots(newLevel);
    if (hasLevelUp(oldLevel, newLevel)) {
      getSkillData().refresh(this, player);
      MinecraftForge.EVENT_BUS.post((Event)new PetLevelUpEvent(player, PetExperienceSource.ADMIN, oldLevel, newLevel, oldSLots, newSlots));
    } 
  }
  
  public void setLevel(EntityPlayer player, int value) {
    double experience = getRequiredExperience(value - 1);
    int oldLevel = getLevel();
    int oldSlots = getSlots(oldLevel);
    this.experience = experience;
    int newLevel = getLevel();
    int newSlots = getSlots(newLevel);
    getSkillData().refresh(this, player);
    MinecraftForge.EVENT_BUS.post((Event)new PetLevelUpEvent(player, PetExperienceSource.ADMIN, oldLevel, newLevel, oldSlots, newSlots));
  }
  
  public boolean changeVisibility() {
    this.visible = !this.visible;
    sync();
    return this.visible;
  }
  
  public void reset() {
    this.experience = 0.0D;
    this.happiness = 0;
    this.assignmentResetMillis = System.currentTimeMillis();
    this.currentSkin = null;
    this.unlockedSkin = null;
    getSkillData().getSkills().clear();
    getSkillRollData().getRolls(this).clear();
    sync();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\data\PetPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */