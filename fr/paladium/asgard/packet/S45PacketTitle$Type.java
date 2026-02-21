package fr.paladium.asgard.packet;

public enum Type {
  TITLE, SUBTITLE, ACTIONBAR, TIMES, CLEAR, RESET;
  
  public static Type byName(String name) {
    for (Type s45packettitle$type : values()) {
      if (s45packettitle$type.name().equalsIgnoreCase(name))
        return s45packettitle$type; 
    } 
    return TITLE;
  }
  
  public static String[] getNames() {
    String[] astring = new String[(values()).length];
    int i = 0;
    for (Type s45packettitle$type : values())
      astring[i++] = s45packettitle$type.name().toLowerCase(); 
    return astring;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\packet\S45PacketTitle$Type.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */