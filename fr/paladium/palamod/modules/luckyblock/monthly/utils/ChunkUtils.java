package fr.paladium.palamod.modules.luckyblock.monthly.utils;

import fr.paladium.palajobs.utils.forge.location.Cuboid;
import fr.paladium.palajobs.utils.forge.location.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ChunkUtils {
  public static List<Chunk> getChunks(World world, Cuboid cuboid) {
    List<Chunk> chunks = new ArrayList<>();
    for (Location location : cuboid.getLocations()) {
      Chunk chunk = world.func_72938_d(location.getBlockX(), location.getBlockZ());
      ChunkCoordIntPair pair = chunk.func_76632_l();
      if (chunks.contains(chunk))
        continue; 
      chunks.add(chunk);
    } 
    return chunks;
  }
  
  public static List<EntityPlayerMP> getPlayers(Cuboid cuboid) {
    World world = cuboid.getWorld();
    if (world == null)
      return new ArrayList<>(); 
    return (List<EntityPlayerMP>)MonthlyUtils.getOnlinePlayers()
      .stream()
      .filter(player -> contains(cuboid, player.field_70165_t, player.field_70163_u, player.field_70161_v))
      .collect(Collectors.toList());
  }
  
  public static boolean contains(Cuboid cuboid, double x, double y, double z) {
    int blockX = (int)Math.floor(x);
    int blockY = (int)Math.floor(y);
    int blockZ = (int)Math.floor(z);
    return (blockX >= cuboid.getMinX() && blockX <= cuboid.getMaxX() && blockY >= cuboid.getMinY() && blockY <= cuboid.getMaxY() && blockZ >= cuboid.getMinZ() && blockZ <= cuboid.getMaxZ());
  }
  
  public static List<EntityLivingBase> getLivingEntities(World world, Cuboid cuboid) {
    List<Chunk> chunks = getChunks(world, cuboid);
    List<EntityLivingBase> entities = new ArrayList<>();
    List<Location> locations = cuboid.getLocations();
    for (Chunk chunk : chunks) {
      for (int i = 0; i < chunk.field_76645_j.length; i++) {
        Object object = chunk.field_76645_j[i];
        if (object != null)
          if (object instanceof ArrayList) {
            ArrayList list = (ArrayList)object;
            for (Object entity : list) {
              if (entity == null)
                continue; 
              if (entity instanceof EntityLivingBase) {
                EntityLivingBase living = (EntityLivingBase)entity;
                if (entities.contains(living))
                  continue; 
                for (Location location : locations) {
                  int locationX = location.getBlockX();
                  int locationY = location.getBlockY();
                  int locationZ = location.getBlockZ();
                  int entityX = (int)Math.floor(living.field_70165_t);
                  int entityY = (int)Math.floor(living.field_70163_u);
                  int entityZ = (int)Math.floor(living.field_70161_v);
                  if (locationX == entityX && locationY == entityY && locationZ == entityZ)
                    entities.add((EntityLivingBase)entity); 
                } 
              } 
            } 
          }  
      } 
    } 
    return entities;
  }
  
  public static boolean locationContains(Location location, EntityLivingBase entity) {
    return (location.getBlockX() == Math.floor(entity.field_70165_t) && location
      .getBlockY() == Math.floor(entity.field_70163_u) && location
      .getBlockZ() == Math.floor(entity.field_70161_v));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\ChunkUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */