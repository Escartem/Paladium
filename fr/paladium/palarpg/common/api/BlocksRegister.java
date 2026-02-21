package fr.paladium.palarpg.common.api;

import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palarpg.module.dungeon.common.block.BlockDungeonChest;
import fr.paladium.palarpg.module.dungeon.common.block.BlockDungeonHub;
import fr.paladium.palarpg.module.dungeon.common.block.room.BlockDungeonRoomChest;
import fr.paladium.palarpg.module.dungeon.common.block.room.BlockDungeonRoomSpawn;
import fr.paladium.palarpg.module.dungeon.common.block.room.impl.entity.BlockDungeonRoomEntity;
import fr.paladium.palarpg.module.dungeon.common.block.room.impl.puzzle.BlockDungeonRoomPuzzlePlayerTrigger;
import fr.paladium.palarpg.module.dungeon.common.block.room.impl.puzzle.BlockDungeonRoomPuzzleRedstoneTrigger;
import fr.paladium.palarpg.module.dungeon.common.block.room.impl.puzzle.BlockDungeonRoomPuzzleResetAction;
import fr.paladium.palarpg.module.dungeon.common.block.room.power.BlockDungeonRoomActivePower;
import fr.paladium.palarpg.module.dungeon.common.block.room.power.BlockDungeonRoomJoinPower;
import fr.paladium.palarpg.module.dungeon.common.block.room.trigger.BlockDungeonRoomFinishTrigger;
import fr.paladium.palarpg.module.dungeon.common.block.room.trigger.BlockDungeonRoomStartTrigger;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.block.Block;

public class BlocksRegister {
  public static List<Block> DUNGEON_BLOCKS = new ArrayList<>();
  
  public static Block DUNGEON_HUB;
  
  public static Block DUNGEON_CHEST;
  
  public static Block DUNGEON_ROOM_CHEST;
  
  public static Block DUNGEON_ROOM_SPAWN;
  
  public static Block DUNGEON_ROOM_JOIN_POWER;
  
  public static Block DUNGEON_ROOM_ACTIVE_POWER;
  
  public static Block DUNGEON_ROOM_START_TRIGGER;
  
  public static Block DUNGEON_ROOM_FINISH_TRIGGER;
  
  public static Block DUNGEON_ROOM_ENTITY;
  
  public static Block DUNGEON_ROOM_PUZZLE_RESET_ACTION;
  
  public static Block DUNGEON_ROOM_PUZZLE_PLAYER_TRIGGER;
  
  public static Block DUNGEON_ROOM_PUZZLE_REDSTONE_TRIGGER;
  
  public static void register() {
    DUNGEON_BLOCKS.add(register(DUNGEON_HUB = (Block)new BlockDungeonHub()));
    DUNGEON_BLOCKS.add(register(DUNGEON_CHEST = (Block)new BlockDungeonChest()));
    DUNGEON_BLOCKS.add(register(DUNGEON_ROOM_CHEST = (Block)new BlockDungeonRoomChest()));
    DUNGEON_BLOCKS.add(register(DUNGEON_ROOM_SPAWN = (Block)new BlockDungeonRoomSpawn()));
    DUNGEON_BLOCKS.add(register(DUNGEON_ROOM_JOIN_POWER = (Block)new BlockDungeonRoomJoinPower()));
    DUNGEON_BLOCKS.add(register(DUNGEON_ROOM_ACTIVE_POWER = (Block)new BlockDungeonRoomActivePower()));
    DUNGEON_BLOCKS.add(register(DUNGEON_ROOM_START_TRIGGER = (Block)new BlockDungeonRoomStartTrigger()));
    DUNGEON_BLOCKS.add(register(DUNGEON_ROOM_FINISH_TRIGGER = (Block)new BlockDungeonRoomFinishTrigger()));
    DUNGEON_BLOCKS.add(register(DUNGEON_ROOM_ENTITY = (Block)new BlockDungeonRoomEntity()));
    DUNGEON_BLOCKS.add(register(DUNGEON_ROOM_PUZZLE_RESET_ACTION = (Block)new BlockDungeonRoomPuzzleResetAction()));
    DUNGEON_BLOCKS.add(register(DUNGEON_ROOM_PUZZLE_PLAYER_TRIGGER = (Block)new BlockDungeonRoomPuzzlePlayerTrigger()));
    DUNGEON_BLOCKS.add(register(DUNGEON_ROOM_PUZZLE_REDSTONE_TRIGGER = (Block)new BlockDungeonRoomPuzzleRedstoneTrigger()));
  }
  
  @NonNull
  private static Block register(@NonNull Block block) {
    if (block == null)
      throw new NullPointerException("block is marked non-null but is null"); 
    RegistryUtils.block(new Block[] { block });
    return block;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\common\api\BlocksRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */