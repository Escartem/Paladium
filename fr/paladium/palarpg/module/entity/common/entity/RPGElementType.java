package fr.paladium.palarpg.module.entity.common.entity;

public enum RPGElementType {
  NEUTRAL, TALIKUS, MANELIOS, VITALYS, NIMBRIA, FRIGORIA;
  
  private static RPGElementType[] values;
  
  static {
    values = values();
  }
  
  public static RPGElementType fromString(String name) {
    for (RPGElementType type : values) {
      if (type.name().equalsIgnoreCase(name))
        return type; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\RPGElementType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */