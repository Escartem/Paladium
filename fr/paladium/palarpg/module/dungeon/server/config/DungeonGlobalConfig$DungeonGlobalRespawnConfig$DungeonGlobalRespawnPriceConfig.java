package fr.paladium.palarpg.module.dungeon.server.config;

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


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\DungeonGlobalConfig$DungeonGlobalRespawnConfig$DungeonGlobalRespawnPriceConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */