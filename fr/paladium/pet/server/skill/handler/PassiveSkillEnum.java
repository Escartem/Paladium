package fr.paladium.pet.server.skill.handler;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.skill.skill.Skill;

public enum PassiveSkillEnum {
  SPEED_WALK("speed_walk"),
  AERIAL_EXPERT("aerial_expert"),
  STOMACH_ON_LEGS("stomach_on_legs"),
  AVIATOR("aviator"),
  HERBALIST("herbalist"),
  MAGNET("magnet"),
  PORTABLE_COFFIN("portable_coffin"),
  LIGHTWEIGHT("lightweight"),
  EXPERIENCE_ABSORBER("experience_absorber"),
  LEGENDARY_EXPERT("legendary_expert"),
  CROQUETTE_MAKER("croquette_maker"),
  GOOD_PICKAXE("good_pickaxe"),
  EXPERIENCED_PICKAXE("experienced_pickaxe"),
  GEOLOGY("geology"),
  MINING_FORTUNE("mining_fortune"),
  FERTILIZER("fertilizer"),
  CRUSHER_BOOSTER("crusher_booster"),
  VITAL_POINT("vital_point"),
  FISHERMAN("fisherman"),
  STRONG_KNOCKBACK("strong_knockback"),
  FLOWER_CHANCE("flower_chance"),
  MAGIC_EXTRACTOR("magic_extractor"),
  MAGIC_PORTAL("magic_portal"),
  BOOSTED_HARVEST("boosted_harvest"),
  PET_HAPPINESS("pet_happiness");
  
  private final String id;
  
  public String getId() {
    return this.id;
  }
  
  PassiveSkillEnum(String id) {
    this.id = id;
  }
  
  public PassiveResponse getResponse(PetPlayer pet) {
    return getResponse(pet, SkillConfig.get());
  }
  
  public PassiveResponse getResponse(PetPlayer pet, SkillConfig config) {
    if (!pet.has())
      return new PassiveResponse(false, null); 
    Skill skill = config.getPassive(this.id);
    if (skill == null)
      return new PassiveResponse(false, null); 
    SkillData data = pet.getSkill(this.id);
    if (data == null)
      return new PassiveResponse(false, skill); 
    if (pet.getLevel() < skill.getRequiredLevel())
      return new PassiveResponse(false, skill); 
    return new PassiveResponse(true, skill);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\PassiveSkillEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */