package fr.paladium.palarpg.module.dungeon.common.world;

public enum DungeonState {
  WAITING("hub.ogg", 1.0F),
  STARTING("hub.ogg", 1.0F),
  READY("hub.ogg", 1.0F),
  STARTED("room.ogg", 2.0F),
  FINISHED("room.ogg", 1.0F);
  
  DungeonState(String music, float volume) {
    this.music = music;
    this.volume = volume;
  }
  
  private final String music;
  
  private final float volume;
  
  public String getMusic() {
    return this.music;
  }
  
  public float getVolume() {
    return this.volume;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\DungeonWorld$DungeonState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */