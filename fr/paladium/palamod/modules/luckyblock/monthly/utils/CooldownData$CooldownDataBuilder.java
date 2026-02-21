package fr.paladium.palamod.modules.luckyblock.monthly.utils;

public class CooldownDataBuilder {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\CooldownData$CooldownDataBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */