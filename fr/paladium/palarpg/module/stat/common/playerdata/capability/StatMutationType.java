package fr.paladium.palarpg.module.stat.common.playerdata.capability;

public enum StatMutationType {
  ADDITIVE("+"),
  MULTIPLICATIVE("x");
  
  public static final StatMutationType[] values;
  
  private final String display;
  
  static {
    values = values();
  }
  
  public String getDisplay() {
    return this.display;
  }
  
  StatMutationType(String display) {
    this.display = display;
  }
  
  public static StatMutationType fromOrdinal(int ordinal) {
    return values[ordinal];
  }
  
  public static StatMutationType fromString(String name) {
    for (StatMutationType type : values) {
      if (type.name().equalsIgnoreCase(name))
        return type; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\common\playerdata\capability\StatMutationType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */