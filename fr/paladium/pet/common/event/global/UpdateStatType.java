package fr.paladium.pet.common.event.global;

public enum UpdateStatType {
  EXPERIENCE, LEVEL, HAPPINESS, SKILL_USED, SKILL_CHANGED, ASSIGNMENT_FINISHED, ASSIGNMENT_CHANGED;
  
  public boolean needUpdate() {
    return (this == LEVEL || this == HAPPINESS || this == SKILL_CHANGED || this == ASSIGNMENT_FINISHED || this == SKILL_USED);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\event\global\UpdateStatType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */