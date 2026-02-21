package fr.paladium.palarpg.module.dungeon.server.config;

public class DungeonGlobalConfig {
  private final DungeonGlobalRespawnConfig respawn;
  
  public String toString() {
    return "DungeonGlobalConfig(respawn=" + getRespawn() + ")";
  }
  
  public DungeonGlobalConfig(DungeonGlobalRespawnConfig respawn) {
    this.respawn = respawn;
  }
  
  public DungeonGlobalRespawnConfig getRespawn() {
    return this.respawn;
  }
  
  public class DungeonGlobalRespawnConfig {
    private final int usage;
    
    private final DungeonGlobalRespawnPriceConfig price;
    
    public String toString() {
      return "DungeonGlobalConfig.DungeonGlobalRespawnConfig(usage=" + getUsage() + ", price=" + getPrice() + ")";
    }
    
    public DungeonGlobalRespawnConfig(int usage, DungeonGlobalRespawnPriceConfig price) {
      this.usage = usage;
      this.price = price;
    }
    
    public int getUsage() {
      return this.usage;
    }
    
    public DungeonGlobalRespawnPriceConfig getPrice() {
      return this.price;
    }
    
    public class DungeonGlobalRespawnPriceConfig {
      private final long team;
      
      private final long personal;
      
      public String toString() {
        return "DungeonGlobalConfig.DungeonGlobalRespawnConfig.DungeonGlobalRespawnPriceConfig(team=" + getTeam() + ", personal=" + getPersonal() + ")";
      }
      
      public DungeonGlobalRespawnPriceConfig(long team, long personal) {
        this.team = team;
        this.personal = personal;
      }
      
      public long getTeam() {
        return this.team;
      }
      
      public long getPersonal() {
        return this.personal;
      }
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\DungeonGlobalConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */