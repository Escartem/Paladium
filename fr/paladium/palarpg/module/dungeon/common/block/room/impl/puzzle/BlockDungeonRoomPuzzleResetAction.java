package fr.paladium.palarpg.module.dungeon.common.block.room.impl.puzzle;

import fr.paladium.palarpg.module.dungeon.common.block.template.ABlockDungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.world.room.behavior.impl.PuzzleDungeonRoomBehavior;
import fr.paladium.palarpg.module.dungeon.server.config.room.DungeonRoomConfig;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDungeonRoomPuzzleResetAction extends ABlockDungeonRoom {
  public BlockDungeonRoomPuzzleResetAction() {
    super("dungeon_room_puzzle_reset_action", "puzzle/room_puzzle_reset_action");
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (world.field_72995_K)
      return; 
    if (world.func_72864_z(x, y, z) || world.func_72864_z(x, y + 1, z)) {
      TileEntity tileEntity = world.func_147438_o(x, y, z);
      if (!(tileEntity instanceof TileEntityDungeonRoom))
        return; 
      DungeonRoom room = ((TileEntityDungeonRoom)tileEntity).getRoom();
      if (room == null || room.isFinished() || !room.isActive() || room.getConfig().getType() != DungeonRoomConfig.DungeonRoomType.PUZZLE)
        return; 
      Optional<PuzzleDungeonRoomBehavior> optionalBehavior = room.getBehavior(PuzzleDungeonRoomBehavior.class);
      optionalBehavior.ifPresent(PuzzleDungeonRoomBehavior::reset);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\block\room\impl\puzzle\BlockDungeonRoomPuzzleResetAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */