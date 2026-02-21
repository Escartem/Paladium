package fr.paladium.palawarzoneevent.module.warzone.server.worlddata;

public enum WarzoneLeaderboardType {
  CAPTURE;
  
  public static final WarzoneLeaderboardType[] values;
  
  static {
    values = values();
  }
  
  public static WarzoneLeaderboardType getTypeByOrdinal(int ordinal) {
    for (int i = 0; i < values.length; i++) {
      if (i == ordinal)
        return values[i]; 
    } 
    return null;
  }
  
  public static WarzoneLeaderboardType getTypeByName(String name) {
    for (WarzoneLeaderboardType type : values) {
      if (type.name().equalsIgnoreCase(name))
        return type; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\server\worlddata\WarzoneLeaderboardType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */