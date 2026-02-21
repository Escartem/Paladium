package fr.paladium.pet.common.network.data.additional.skill;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.config.skill.SkillConfig;
import fr.paladium.pet.server.skill.skill.Skill;
import net.minecraft.entity.player.EntityPlayerMP;

public class SkillData {
  private String skillId;
  
  private long lastChangeMillis;
  
  private long nextUseMillis;
  
  public void setSkillId(String skillId) {
    this.skillId = skillId;
  }
  
  public void setLastChangeMillis(long lastChangeMillis) {
    this.lastChangeMillis = lastChangeMillis;
  }
  
  public void setNextUseMillis(long nextUseMillis) {
    this.nextUseMillis = nextUseMillis;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof SkillData))
      return false; 
    SkillData other = (SkillData)o;
    if (!other.canEqual(this))
      return false; 
    if (getLastChangeMillis() != other.getLastChangeMillis())
      return false; 
    if (getNextUseMillis() != other.getNextUseMillis())
      return false; 
    Object this$skillId = getSkillId(), other$skillId = other.getSkillId();
    return !((this$skillId == null) ? (other$skillId != null) : !this$skillId.equals(other$skillId));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof SkillData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $lastChangeMillis = getLastChangeMillis();
    result = result * 59 + (int)($lastChangeMillis >>> 32L ^ $lastChangeMillis);
    long $nextUseMillis = getNextUseMillis();
    result = result * 59 + (int)($nextUseMillis >>> 32L ^ $nextUseMillis);
    Object $skillId = getSkillId();
    return result * 59 + (($skillId == null) ? 43 : $skillId.hashCode());
  }
  
  public String toString() {
    return "SkillData(skillId=" + getSkillId() + ", lastChangeMillis=" + getLastChangeMillis() + ", nextUseMillis=" + getNextUseMillis() + ")";
  }
  
  public SkillData(String skillId, long lastChangeMillis, long nextUseMillis) {
    this.skillId = skillId;
    this.lastChangeMillis = lastChangeMillis;
    this.nextUseMillis = nextUseMillis;
  }
  
  public String getSkillId() {
    return this.skillId;
  }
  
  public long getLastChangeMillis() {
    return this.lastChangeMillis;
  }
  
  public long getNextUseMillis() {
    return this.nextUseMillis;
  }
  
  public SkillData(String id) {
    this.skillId = id;
    this.nextUseMillis = 0L;
    this.lastChangeMillis = 0L;
  }
  
  public long getChangeCooldown(EntityPlayerMP player, long now) {
    long cooldown = SkillConfig.get().getChangeSkillCooldown() - now - this.lastChangeMillis;
    if (cooldown <= 0L)
      return 0L; 
    return cooldown;
  }
  
  public boolean canChangeSlot(long cooldown) {
    return (cooldown <= 0L);
  }
  
  public void changeSlot(Skill skill, long nextUseMillis) {
    this.skillId = skill.getId();
    this.nextUseMillis = nextUseMillis;
    this.lastChangeMillis = System.currentTimeMillis();
    this.nextUseMillis = nextUseMillis;
  }
  
  public void changeSlotBypass(Skill skill) {
    this.skillId = skill.getId();
    this.nextUseMillis = 0L;
    this.lastChangeMillis = 0L;
  }
  
  public void use(PetPlayer petPlayer, long usageMillis) {
    this.nextUseMillis = usageMillis;
    petPlayer.putNextSkillUsage(this.skillId, usageMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\data\additional\skill\SkillData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */