package fr.paladium.pet.server.skill.handler;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.skill.Skill;

public class PassiveResponse {
  public static final double NONE_VALUE = 0.0D;
  
  private final boolean has;
  
  private final Skill skill;
  
  public PassiveResponse(boolean has, Skill skill) {
    this.has = has;
    this.skill = skill;
  }
  
  public boolean isHas() {
    return this.has;
  }
  
  public Skill getSkill() {
    return this.skill;
  }
  
  public boolean has(double value) {
    if (this.skill != null && this.has && this.skill.getValue() <= 0.0D)
      return true; 
    if (!this.has || value <= 0.0D)
      return false; 
    return true;
  }
  
  public double getPersonalValue(PetPlayer pet) {
    if (this.skill == null)
      return 0.0D; 
    if (!pet.isSkillSelected(this.skill))
      return 0.0D; 
    return this.skill.getPersonalValue(pet);
  }
  
  public double getValueAsPercent(double value) {
    if (value <= 0.0D)
      return 0.0D; 
    return value / 100.0D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\PassiveResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */