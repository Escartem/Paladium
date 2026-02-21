package fr.paladium.pet.client.ui.utils.data;

import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.common.network.data.additional.roll.SkillRollData;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.common.pet.PetAdditionalData;
import fr.paladium.pet.common.store.provider.PetSkinShopProvider;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.AssignmentConfig;
import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.skill.SkillManager;
import fr.paladium.pet.server.skill.skill.Skill;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class HomeData {
  private final int level;
  
  private final int happiness;
  
  private final int maxHappiness;
  
  private int experience;
  
  private int maxExperience;
  
  private boolean visible;
  
  private final double happinessMultiplier;
  
  private final List<AssignmentClientData> assignments;
  
  private final List<SlotClientData> slots;
  
  private final List<SlotClientData> selectableSlots;
  
  private final List<SkinData> skins;
  
  private final List<SkillRollSlotData> skillRollSlots;
  
  private final SkinData currentSkin;
  
  private final boolean bypass;
  
  public int getLevel() {
    return this.level;
  }
  
  public int getHappiness() {
    return this.happiness;
  }
  
  public int getMaxHappiness() {
    return this.maxHappiness;
  }
  
  public int getExperience() {
    return this.experience;
  }
  
  public int getMaxExperience() {
    return this.maxExperience;
  }
  
  public boolean isVisible() {
    return this.visible;
  }
  
  public double getHappinessMultiplier() {
    return this.happinessMultiplier;
  }
  
  public List<AssignmentClientData> getAssignments() {
    return this.assignments;
  }
  
  public List<SlotClientData> getSlots() {
    return this.slots;
  }
  
  public List<SlotClientData> getSelectableSlots() {
    return this.selectableSlots;
  }
  
  public List<SkinData> getSkins() {
    return this.skins;
  }
  
  public List<SkillRollSlotData> getSkillRollSlots() {
    return this.skillRollSlots;
  }
  
  public SkinData getCurrentSkin() {
    return this.currentSkin;
  }
  
  public boolean isBypass() {
    return this.bypass;
  }
  
  public HomeData(EntityPlayerMP player, PetPlayer pet) {
    this.level = pet.getLevel();
    this.maxHappiness = pet.getMaxHappiness();
    this.visible = pet.isVisible();
    this.assignments = initAssignments((EntityPlayer)player, pet);
    this.slots = initSlots(player, pet);
    this.selectableSlots = initSelectableSlots(player, pet);
    this.skillRollSlots = initSkillRollSlots(pet);
    this.happiness = Math.min(pet.getHappiness(), this.maxHappiness);
    this.happinessMultiplier = pet.getSkillMultiplier();
    this.currentSkin = initCurrentSkin(player.field_70170_p, pet);
    this.skins = initSkins((EntityPlayer)player, pet);
    this.bypass = SkillManager.getInstance().isBypassed((EntityPlayer)player);
    double currentExperience = pet.getExperience();
    double beforeLevelExperience = pet.getRequiredExperience(this.level - 1);
    double nextLevelExperience = pet.getRequiredExperience(this.level);
    this.experience = (int)Math.floor(currentExperience - beforeLevelExperience);
    this.maxExperience = (int)Math.floor(nextLevelExperience - beforeLevelExperience);
    if (this.level == 100) {
      this.experience = 100;
      this.maxExperience = 100;
    } 
  }
  
  public SkillRollSlotData findSkillRollData(int slot) {
    for (SkillRollSlotData data : this.skillRollSlots) {
      if (data.getSlot() == slot)
        return data; 
    } 
    return SkillRollSlotData.none(slot);
  }
  
  private List<SkillRollSlotData> initSkillRollSlots(PetPlayer pet) {
    SkillConfig config = SkillConfig.get();
    long now = System.currentTimeMillis();
    List<SkillRollData> rolls = pet.getSkillRollData().getRolls(pet);
    List<SkillRollSlotData> skillRollSlots = new ArrayList<>();
    for (SkillRollData roll : rolls) {
      int slot = roll.getSlot();
      Optional<Skill> result = config.findSkillById(roll.getSkillId());
      if (!result.isPresent())
        continue; 
      Skill skill = result.get();
      SkillData skillData = pet.getSkill(slot);
      boolean usageCooldown = (skillData != null && skill.isOnCooldown(skill.getCooldown(now, skillData.getNextUseMillis())));
      skillRollSlots.add(SkillRollSlotData.from(slot, skill, usageCooldown));
    } 
    return skillRollSlots;
  }
  
  private SkinData initCurrentSkin(World world, PetPlayer pet) {
    PetAdditionalData skin = PetCommonProxy.getInstance().findPet(pet.getCurrentSkin());
    if (skin == null)
      return SkinData.defaultData(); 
    return new SkinData(skin.getName());
  }
  
  private List<SkinData> initSkins(EntityPlayer player, PetPlayer pet) {
    List<SkinData> skins = new ArrayList<>();
    for (String skin : PetSkinShopProvider.getInstance().getSkins(player, pet)) {
      PetAdditionalData result = PetCommonProxy.getInstance().findPet(skin);
      if (result == null)
        continue; 
      skins.add(new SkinData(result.getName()));
    } 
    return skins;
  }
  
  private List<SlotClientData> initSelectableSlots(EntityPlayerMP player, PetPlayer pet) {
    List<SlotClientData> slots = new ArrayList<>();
    SkillConfig config = SkillConfig.get();
    List<Skill> skills = new ArrayList<>();
    List<Skill> actives = new ArrayList<>(config.getActiveSkills());
    List<Skill> passives = new ArrayList<>(config.getPassiveSkills());
    actives.sort(Comparator.comparing(skill -> skill.getName((EntityPlayer)player)));
    passives.sort(Comparator.comparing(skill -> skill.getName((EntityPlayer)player)));
    skills.addAll(actives);
    skills.addAll(passives);
    for (Skill skill : skills) {
      if (!skill.hasRequiredLevel(this.level))
        continue; 
      if (pet.isSkillSelected(skill))
        continue; 
      slots.add(SlotClientData.from(-1, skill, (EntityPlayer)player, pet, 0L));
    } 
    return slots;
  }
  
  private List<SlotClientData> initSlots(EntityPlayerMP player, PetPlayer pet) {
    int index = 0;
    long now = System.currentTimeMillis();
    List<SlotClientData> slots = new ArrayList<>();
    SkillConfig config = SkillConfig.get();
    for (SkillData data : pet.getSkillData().getSkills().values()) {
      if (data.getSkillId().equals("no_skill")) {
        slots.add(SlotClientData.none((EntityPlayer)player, index));
      } else {
        Optional<Skill> result = config.findSkillById(data.getSkillId());
        if (!result.isPresent()) {
          slots.add(SlotClientData.none((EntityPlayer)player, index));
        } else {
          long nextChangeMillis = data.getChangeCooldown(player, now);
          slots.add(SlotClientData.from(index, result.get(), (EntityPlayer)player, pet, nextChangeMillis));
        } 
      } 
      index++;
    } 
    while (index < 20) {
      slots.add(SlotClientData.locked((EntityPlayer)player, index, pet.getRequiredLevelForSlot(index)));
      index++;
    } 
    return slots;
  }
  
  private List<AssignmentClientData> initAssignments(EntityPlayer player, PetPlayer pet) {
    List<AssignmentClientData> assignments = new ArrayList<>();
    AssignmentConfig config = AssignmentConfig.get();
    for (AssignmentData data : pet.getAssignmentData().getAssignments().values()) {
      Optional<Assignment> result = config.findAssignmentById(data.getAssignmentId());
      if (!result.isPresent())
        continue; 
      assignments.add(AssignmentClientData.from(player, result.get(), data));
    } 
    return assignments;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\u\\utils\data\HomeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */