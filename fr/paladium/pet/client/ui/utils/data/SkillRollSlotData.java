package fr.paladium.pet.client.ui.utils.data;

import fr.paladium.pet.server.skill.skill.Skill;
import net.minecraft.util.ResourceLocation;

public class SkillRollSlotData {
  private int slot;
  
  private String skillId;
  
  private boolean usageCooldown;
  
  private ResourceLocation logo;
  
  public void setSlot(int slot) {
    this.slot = slot;
  }
  
  public void setSkillId(String skillId) {
    this.skillId = skillId;
  }
  
  public void setUsageCooldown(boolean usageCooldown) {
    this.usageCooldown = usageCooldown;
  }
  
  public void setLogo(ResourceLocation logo) {
    this.logo = logo;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof SkillRollSlotData))
      return false; 
    SkillRollSlotData other = (SkillRollSlotData)o;
    if (!other.canEqual(this))
      return false; 
    if (getSlot() != other.getSlot())
      return false; 
    if (isUsageCooldown() != other.isUsageCooldown())
      return false; 
    Object this$skillId = getSkillId(), other$skillId = other.getSkillId();
    if ((this$skillId == null) ? (other$skillId != null) : !this$skillId.equals(other$skillId))
      return false; 
    Object this$logo = getLogo(), other$logo = other.getLogo();
    return !((this$logo == null) ? (other$logo != null) : !this$logo.equals(other$logo));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof SkillRollSlotData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getSlot();
    result = result * 59 + (isUsageCooldown() ? 79 : 97);
    Object $skillId = getSkillId();
    result = result * 59 + (($skillId == null) ? 43 : $skillId.hashCode());
    Object $logo = getLogo();
    return result * 59 + (($logo == null) ? 43 : $logo.hashCode());
  }
  
  public String toString() {
    return "SkillRollSlotData(slot=" + getSlot() + ", skillId=" + getSkillId() + ", usageCooldown=" + isUsageCooldown() + ", logo=" + getLogo() + ")";
  }
  
  public SkillRollSlotData(int slot, String skillId, boolean usageCooldown, ResourceLocation logo) {
    this.slot = slot;
    this.skillId = skillId;
    this.usageCooldown = usageCooldown;
    this.logo = logo;
  }
  
  public int getSlot() {
    return this.slot;
  }
  
  public String getSkillId() {
    return this.skillId;
  }
  
  public boolean isUsageCooldown() {
    return this.usageCooldown;
  }
  
  public ResourceLocation getLogo() {
    return this.logo;
  }
  
  public static SkillRollSlotData from(int slot, Skill skill, boolean usageCooldown) {
    return new SkillRollSlotData(slot, skill
        
        .getId(), usageCooldown, new ResourceLocation("palapet:textures/ui/skill/" + skill
          
          .getIcon() + ".png"));
  }
  
  public static SkillRollSlotData none(int slot) {
    return new SkillRollSlotData(slot, "", false, new ResourceLocation("palapet:textures/ui/home/unknown_logo.png"));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\clien\\u\\utils\data\SkillRollSlotData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */