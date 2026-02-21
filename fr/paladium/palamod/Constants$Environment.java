package fr.paladium.palamod;

public enum Environment {
  RELEASE, SERVER, DEV;
  
  public static Environment getEnum(String value) {
    for (Environment v : values()) {
      if (v.name().equals(value))
        return v; 
    } 
    return DEV;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\Constants$Environment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */