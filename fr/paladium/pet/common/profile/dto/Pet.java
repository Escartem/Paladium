package fr.paladium.pet.common.profile.dto;

import fr.paladium.pet.server.config.global.GlobalConfig;
import java.util.List;

public class Pet {
  private final String currentSkin;
  
  private final int happiness;
  
  private final double experience;
  
  private final List<PetSkill> skills;
  
  private int maxHappiness;
  
  public Pet(String currentSkin, int happiness, double experience, List<PetSkill> skills, int maxHappiness) {
    this.currentSkin = currentSkin;
    this.happiness = happiness;
    this.experience = experience;
    this.skills = skills;
    this.maxHappiness = maxHappiness;
  }
  
  public String getCurrentSkin() {
    return this.currentSkin;
  }
  
  public int getHappiness() {
    return this.happiness;
  }
  
  public double getExperience() {
    return this.experience;
  }
  
  public List<PetSkill> getSkills() {
    return this.skills;
  }
  
  public int getMaxHappiness() {
    return this.maxHappiness;
  }
  
  public Pet build() {
    this.maxHappiness = GlobalConfig.get().getMaxHappiness(getLevel());
    return this;
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
  
  public double getRequiredExperience(int level) {
    if (level <= 0)
      return 0.0D; 
    return (level * level - 1) * 90.0D / level + 300.0D + getRequiredExperience(level - 1);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\profile\dto\Pet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */