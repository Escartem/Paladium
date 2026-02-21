package fr.paladium.palawither.common.wither.condition.impl;

class FactionCooldownWither {
  private final String wither;
  
  private final long date;
  
  public FactionCooldownWither(String wither, long date) {
    this.wither = wither;
    this.date = date;
  }
  
  public String getWither() {
    return this.wither;
  }
  
  public long getDate() {
    return this.date;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\condition\impl\FactionCooldownWitherCondition$FactionCooldownWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */