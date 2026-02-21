package fr.paladium.palarpg.module.dungeon.server.config.room;

public class DungeonRoomSizeConfig {
  private final int x;
  
  private final int z;
  
  public String toString() {
    return "DungeonRoomConfig.DungeonRoomSizeConfig(x=" + getX() + ", z=" + getZ() + ")";
  }
  
  public DungeonRoomSizeConfig(int x, int z) {
    this.x = x;
    this.z = z;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getZ() {
    return this.z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\room\DungeonRoomConfig$DungeonRoomSizeConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */