package fr.paladium.pet.server.skill.handler.impl.active.data;

import net.minecraft.entity.EntityLivingBase;

public class RevealingSwordData {
  private long durationMillis;
  
  private long expirationMillis;
  
  private String targetName;
  
  public void setDurationMillis(long durationMillis) {
    this.durationMillis = durationMillis;
  }
  
  public void setExpirationMillis(long expirationMillis) {
    this.expirationMillis = expirationMillis;
  }
  
  public void setTargetName(String targetName) {
    this.targetName = targetName;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof RevealingSwordData))
      return false; 
    RevealingSwordData other = (RevealingSwordData)o;
    if (!other.canEqual(this))
      return false; 
    if (getDurationMillis() != other.getDurationMillis())
      return false; 
    if (getExpirationMillis() != other.getExpirationMillis())
      return false; 
    Object this$targetName = getTargetName(), other$targetName = other.getTargetName();
    return !((this$targetName == null) ? (other$targetName != null) : !this$targetName.equals(other$targetName));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof RevealingSwordData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $durationMillis = getDurationMillis();
    result = result * 59 + (int)($durationMillis >>> 32L ^ $durationMillis);
    long $expirationMillis = getExpirationMillis();
    result = result * 59 + (int)($expirationMillis >>> 32L ^ $expirationMillis);
    Object $targetName = getTargetName();
    return result * 59 + (($targetName == null) ? 43 : $targetName.hashCode());
  }
  
  public String toString() {
    return "RevealingSwordData(durationMillis=" + getDurationMillis() + ", expirationMillis=" + getExpirationMillis() + ", targetName=" + getTargetName() + ")";
  }
  
  public long getDurationMillis() {
    return this.durationMillis;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public String getTargetName() {
    return this.targetName;
  }
  
  public RevealingSwordData(long durationMillis) {
    this.durationMillis = durationMillis;
  }
  
  public void update(EntityLivingBase living) {
    this.targetName = living.func_70005_c_();
    this.expirationMillis = System.currentTimeMillis() + this.durationMillis;
  }
  
  public boolean isExpired() {
    return (System.currentTimeMillis() >= this.expirationMillis);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\data\RevealingSwordData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */