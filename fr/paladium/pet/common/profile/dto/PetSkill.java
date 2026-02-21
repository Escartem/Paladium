package fr.paladium.pet.common.profile.dto;

public class PetSkill {
  private final String id;
  
  private final long lastChange;
  
  private final long nextUse;
  
  public PetSkill(String id, long lastChange, long nextUse) {
    this.id = id;
    this.lastChange = lastChange;
    this.nextUse = nextUse;
  }
  
  public String getId() {
    return this.id;
  }
  
  public long getLastChange() {
    return this.lastChange;
  }
  
  public long getNextUse() {
    return this.nextUse;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\profile\dto\PetSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */