package fr.paladium.pet.common.network.data.additional.roll;

public class SkillRollData {
  private int slot;
  
  private String skillId;
  
  public void setSlot(int slot) {
    this.slot = slot;
  }
  
  public void setSkillId(String skillId) {
    this.skillId = skillId;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof SkillRollData))
      return false; 
    SkillRollData other = (SkillRollData)o;
    if (!other.canEqual(this))
      return false; 
    if (getSlot() != other.getSlot())
      return false; 
    Object this$skillId = getSkillId(), other$skillId = other.getSkillId();
    return !((this$skillId == null) ? (other$skillId != null) : !this$skillId.equals(other$skillId));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof SkillRollData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getSlot();
    Object $skillId = getSkillId();
    return result * 59 + (($skillId == null) ? 43 : $skillId.hashCode());
  }
  
  public String toString() {
    return "SkillRollData(slot=" + getSlot() + ", skillId=" + getSkillId() + ")";
  }
  
  public SkillRollData(int slot, String skillId) {
    this.slot = slot;
    this.skillId = skillId;
  }
  
  public int getSlot() {
    return this.slot;
  }
  
  public String getSkillId() {
    return this.skillId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\data\additional\roll\SkillRollData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */