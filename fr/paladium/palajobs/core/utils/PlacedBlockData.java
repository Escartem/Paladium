package fr.paladium.palajobs.core.utils;

import fr.paladium.palaforgeutils.lib.localdata.world.LocalWorldData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PlacedBlockData {
  public static void setPlaced(World world, int x, int y, int z) {
    LocalWorldData localWorld = LocalWorldData.get(world);
    NBTTagCompound blockData = localWorld.getBlockData(x, y, z);
    blockData.func_74757_a("placed", true);
    localWorld.setBlockData(x, y, z, blockData);
  }
  
  public static void removePlaced(World world, int x, int y, int z) {
    LocalWorldData localWorld = LocalWorldData.get(world);
    NBTTagCompound blockData = localWorld.getBlockData(x, y, z);
    blockData.func_82580_o("placed");
    localWorld.setBlockData(x, y, z, blockData);
  }
  
  public static boolean isPlaced(World world, int x, int y, int z) {
    LocalWorldData localWorld = LocalWorldData.get(world);
    NBTTagCompound blockData = localWorld.getBlockData(x, y, z);
    return blockData.func_74764_b("placed") ? blockData.func_74767_n("placed") : false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\cor\\utils\PlacedBlockData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */