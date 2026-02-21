package fr.paladium.palajobs.api.type;

public enum JobType {
  MINER("jobs.miner.name", "§c"),
  HUNTER("jobs.hunter.name", "§b"),
  FARMER("jobs.farmer.name", "§e"),
  ALCHEMIST("jobs.alchemist.name", "§d");
  
  private static final JobType[] values;
  
  private String name;
  
  private String prefix;
  
  static {
    values = values();
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getPrefix() {
    return this.prefix;
  }
  
  JobType(String name, String prefix) {
    this.name = name;
    this.prefix = prefix;
  }
  
  public static JobType getByName(String arg) {
    for (JobType type : values) {
      if (type.name.equalsIgnoreCase(arg))
        return type; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\type\JobType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */