package fr.paladium.palarpg.module.dungeon.common.world.generator;

public final class DungeonRoomGeneration {
  private final long min;
  
  private final long max;
  
  private final long weight;
  
  public DungeonRoomGeneration(long min, long max, long weight) {
    this.min = min;
    this.max = max;
    this.weight = weight;
  }
  
  public long getMin() {
    return this.min;
  }
  
  public long getMax() {
    return this.max;
  }
  
  public long getWeight() {
    return this.weight;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\generator\DungeonGenerator$DungeonRoomGeneration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */