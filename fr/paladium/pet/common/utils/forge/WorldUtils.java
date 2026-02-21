package fr.paladium.pet.common.utils.forge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class WorldUtils {
  @SideOnly(Side.SERVER)
  public static World[] getWorlds() {
    return (World[])(MinecraftServer.func_71276_C()).field_71305_c;
  }
  
  public static World getWorld(String worldName) {
    World[] worlds = getWorlds();
    if (worlds == null)
      return null; 
    for (World world : worlds) {
      if (world.func_72912_H().func_76065_j().equals(worldName))
        return world; 
    } 
    return null;
  }
  
  public static World getWorld(int dimension) {
    for (World world : getWorlds()) {
      if (world.field_73011_w.field_76574_g == dimension)
        return world; 
    } 
    return null;
  }
  
  public static Entity getEntityById(int id, World world) {
    return world.func_73045_a(id);
  }
  
  public static World getWorld(Location location) {
    return getWorld(location.getWorldName());
  }
  
  @SideOnly(Side.CLIENT)
  public static Entity getEntityById(int id) {
    return getEntityById(id, (World)(Minecraft.func_71410_x()).field_71441_e);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\commo\\utils\forge\WorldUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */