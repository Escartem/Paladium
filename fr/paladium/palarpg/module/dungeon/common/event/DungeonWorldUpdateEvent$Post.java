package fr.paladium.palarpg.module.dungeon.common.event;

import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import lombok.NonNull;

public class Post extends DungeonWorldUpdateEvent {
  public Post(@NonNull DungeonWorld world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\event\DungeonWorldUpdateEvent$Post.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */