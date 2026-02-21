package fr.paladium.palajobs.utils.forge.tiles;

import fr.paladium.palajobs.utils.forge.location.Location;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileUtils {
  public static TileEntity getTileEntity(Location location) {
    World world = location.getWorld();
    if (world == null)
      return null; 
    return getTileEntity(world, (int)location.getX(), (int)location.getY(), (int)location.getZ());
  }
  
  public static TileEntity getTileEntity(World world, int x, int y, int z) {
    if (world == null)
      return null; 
    return world.func_147438_o(x, y, z);
  }
  
  public static <T extends TileEntity> T getTileEntity(Class<T> clazz, World world, int x, int y, int z) {
    TileEntity tileEntity = getTileEntity(world, x, y, z);
    if (tileEntity == null)
      return null; 
    if (!clazz.isInstance(tileEntity))
      return null; 
    return (T)tileEntity;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajob\\utils\forge\tiles\TileUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */