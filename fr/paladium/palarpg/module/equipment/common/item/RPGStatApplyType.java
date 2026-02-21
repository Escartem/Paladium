package fr.paladium.palarpg.module.equipment.common.item;

public enum RPGStatApplyType {
  WEAR, HELD, CONSUMABLE;
  
  private static RPGStatApplyType[] values;
  
  static {
    values = values();
  }
  
  public static RPGStatApplyType fromOrdinal(int ordinal) {
    return values[ordinal];
  }
  
  public static RPGStatApplyType fromString(String name) {
    for (RPGStatApplyType type : values) {
      if (type.name().equalsIgnoreCase(name))
        return type; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\RPGStatApplyType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */