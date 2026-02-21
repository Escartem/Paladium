package fr.paladium.palarpg.module.dungeon.common.world.room.behavior;

import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.EntityDungeonRoomBehavior;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.MerchantDungeonRoomBehavior;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.PuzzleDungeonRoomBehavior;
import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import fr.paladium.palaschematicmod.common.pojo.data.BlockPos;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.block.Block;

public abstract class DungeonRoomBehavior {
  public DungeonRoomBehavior(DungeonRoom room) {
    this.room = room;
  }
  
  private static final Map<DungeonRoomConfig.DungeonRoomType, Class<? extends DungeonRoomBehavior>> DEFAULT_BEHAVIORS = new HashMap<>();
  
  private static final Map<String, Class<? extends DungeonRoomBehavior>> CUSTOM_BEHAVIORS = new HashMap<>();
  
  private final DungeonRoom room;
  
  static {
    DEFAULT_BEHAVIORS.put(DungeonRoomConfig.DungeonRoomType.CLASSIC, EntityDungeonRoomBehavior.class);
    DEFAULT_BEHAVIORS.put(DungeonRoomConfig.DungeonRoomType.MINIBOSS, EntityDungeonRoomBehavior.class);
    DEFAULT_BEHAVIORS.put(DungeonRoomConfig.DungeonRoomType.BOSS, EntityDungeonRoomBehavior.class);
    DEFAULT_BEHAVIORS.put(DungeonRoomConfig.DungeonRoomType.PUZZLE, PuzzleDungeonRoomBehavior.class);
    DEFAULT_BEHAVIORS.put(DungeonRoomConfig.DungeonRoomType.MERCHANT, MerchantDungeonRoomBehavior.class);
  }
  
  public DungeonRoom getRoom() {
    return this.room;
  }
  
  public static void registerBehavior(@NonNull String id, @NonNull Class<? extends DungeonRoomBehavior> behavior) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (behavior == null)
      throw new NullPointerException("behavior is marked non-null but is null"); 
    CUSTOM_BEHAVIORS.put(id, behavior);
  }
  
  @NonNull
  public static Optional<Class<? extends DungeonRoomBehavior>> getBehavior(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return Optional.ofNullable(CUSTOM_BEHAVIORS.get(id));
  }
  
  @NonNull
  public static Optional<Class<? extends DungeonRoomBehavior>> getBehavior(@NonNull DungeonRoomConfig.DungeonRoomType type) {
    if (type == null)
      throw new NullPointerException("type is marked non-null but is null"); 
    return Optional.ofNullable(DEFAULT_BEHAVIORS.get(type));
  }
  
  public abstract void onPaste(@NonNull Block paramBlock, @NonNull BlockPos paramBlockPos);
  
  public abstract void onLoad();
  
  public abstract void onReset();
  
  public abstract void onRemove();
  
  public abstract void onJoin();
  
  public abstract void onLeave();
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\world\room\behavior\DungeonRoomBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */