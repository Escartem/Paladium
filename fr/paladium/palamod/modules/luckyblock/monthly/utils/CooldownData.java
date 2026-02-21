package fr.paladium.palamod.modules.luckyblock.monthly.utils;

public class CooldownData {
  private String name;
  
  private long duration;
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setDuration(long duration) {
    this.duration = duration;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof CooldownData))
      return false; 
    CooldownData other = (CooldownData)o;
    if (!other.canEqual(this))
      return false; 
    if (getDuration() != other.getDuration())
      return false; 
    Object this$name = getName(), other$name = other.getName();
    return !((this$name == null) ? (other$name != null) : !this$name.equals(other$name));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof CooldownData;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $duration = getDuration();
    result = result * 59 + (int)($duration >>> 32L ^ $duration);
    Object $name = getName();
    return result * 59 + (($name == null) ? 43 : $name.hashCode());
  }
  
  public String toString() {
    return "CooldownData(name=" + getName() + ", duration=" + getDuration() + ")";
  }
  
  CooldownData(String name, long duration) {
    this.name = name;
    this.duration = duration;
  }
  
  public static CooldownDataBuilder builder() {
    return new CooldownDataBuilder();
  }
  
  public static class CooldownDataBuilder {
    private String name;
    
    private long duration;
    
    public CooldownDataBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public CooldownDataBuilder duration(long duration) {
      this.duration = duration;
      return this;
    }
    
    public CooldownData build() {
      return new CooldownData(this.name, this.duration);
    }
    
    public String toString() {
      return "CooldownData.CooldownDataBuilder(name=" + this.name + ", duration=" + this.duration + ")";
    }
  }
  
  public String getName() {
    return this.name;
  }
  
  public long getDuration() {
    return this.duration;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\CooldownData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */