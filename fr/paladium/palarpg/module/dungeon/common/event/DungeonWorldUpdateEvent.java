package fr.paladium.palarpg.module.dungeon.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import lombok.NonNull;

public class DungeonWorldUpdateEvent extends Event {
  private final DungeonWorld world;
  
  public DungeonWorldUpdateEvent(DungeonWorld world) {
    this.world = world;
  }
  
  public DungeonWorld getWorld() {
    return this.world;
  }
  
  public static class Pre extends DungeonWorldUpdateEvent {
    public Pre(@NonNull DungeonWorld world) {
      super(world);
      if (world == null)
        throw new NullPointerException("world is marked non-null but is null"); 
    }
  }
  
  public static class Post extends DungeonWorldUpdateEvent {
    public Post(@NonNull DungeonWorld world) {
      super(world);
      if (world == null)
        throw new NullPointerException("world is marked non-null but is null"); 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\event\DungeonWorldUpdateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */