package fr.paladium.palarpg.module.dungeon.common.block.room;

import fr.paladium.palarpg.module.dungeon.common.block.template.ABlockDungeonRoom;
import fr.paladium.palarpg.module.dungeon.common.block.tileentity.TileEntityDungeonRoom;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDungeonRoomSpawn extends ABlockDungeonRoom {
  public BlockDungeonRoomSpawn() {
    super("dungeon_room_spawn", "room_spawn");
  }
  
  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityDungeonRoom))
      return; 
    TileEntityDungeonRoom tile = (TileEntityDungeonRoom)tileEntity;
    int rotation = MathHelper.func_76128_c((entity.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    ForgeDirection direction = ForgeDirection.NORTH;
    switch (rotation) {
      case 0:
        direction = ForgeDirection.NORTH;
        break;
      case 1:
        direction = ForgeDirection.EAST;
        break;
      case 2:
        direction = ForgeDirection.SOUTH;
        break;
      case 3:
        direction = ForgeDirection.WEST;
        break;
    } 
    tile.setDirection(direction);
    tile.func_70296_d();
    world.func_147471_g(x, y, z);
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!player.func_70093_af() || !player.field_71075_bZ.field_75098_d)
      return false; 
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityDungeonRoom))
      return false; 
    TileEntityDungeonRoom tile = (TileEntityDungeonRoom)tileEntity;
    tile.incrementDirection();
    tile.func_70296_d();
    world.func_147471_g(x, y, z);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\block\room\BlockDungeonRoomSpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */