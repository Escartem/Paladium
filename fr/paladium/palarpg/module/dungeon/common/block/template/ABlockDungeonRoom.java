package fr.paladium.palarpg.module.dungeon.common.block.template;

import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonRoom;
import lombok.NonNull;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ABlockDungeonRoom extends ABlockDungeon implements ITileEntityProvider {
  protected ABlockDungeonRoom(@NonNull String name, @NonNull String texture) {
    super(name, texture);
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (texture == null)
      throw new NullPointerException("texture is marked non-null but is null"); 
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityDungeonRoom();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\block\template\ABlockDungeonRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */