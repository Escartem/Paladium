package fr.paladium.palaforgeutils.lib.localdata.world;

import fr.paladium.palaforgeutils.lib.java.hash.LongHash;
import fr.paladium.palaforgeutils.lib.localdata.chunk.LocalChunkData;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class LocalWorldData {
  private static Map<World, LocalWorldData> cache = new HashMap<>();
  
  private final World world;
  
  private final Map<Long, LocalChunkData> chunkCache;
  
  private final Map<Long, LocalChunkData> unloadedChunkCache;
  
  public LocalWorldData(World world) {
    this.world = world;
    this.chunkCache = new ConcurrentHashMap<>();
    this.unloadedChunkCache = new ConcurrentHashMap<>();
  }
  
  public NBTTagCompound getBlockData(int x, int y, int z) {
    LocalChunkData chunkData = getChunkDataFromCoords(x, z);
    return chunkData.getData(x & 0xF, y, z & 0xF);
  }
  
  public void setBlockData(int x, int y, int z, NBTTagCompound data) {
    LocalChunkData chunkData = getChunkDataFromCoords(x, z);
    chunkData.setData(x & 0xF, y, z & 0xF, data);
  }
  
  public LocalChunkData getChunkDataFromCoords(int x, int z) {
    return getChunkData(x >> 4, z >> 4);
  }
  
  public LocalChunkData getChunkData(int chunkX, int chunkZ) {
    long hash = LongHash.toLong(chunkX, chunkZ);
    if (this.chunkCache.containsKey(Long.valueOf(hash)))
      return this.chunkCache.get(Long.valueOf(hash)); 
    if (this.unloadedChunkCache.containsKey(Long.valueOf(hash)))
      return this.unloadedChunkCache.get(Long.valueOf(hash)); 
    LocalChunkData chunkData = new LocalChunkData();
    this.chunkCache.put(Long.valueOf(hash), chunkData);
    return chunkData;
  }
  
  public LocalChunkData unload(Chunk chunk) {
    long hash = LongHash.toLong(chunk.field_76635_g, chunk.field_76647_h);
    if (!this.chunkCache.containsKey(Long.valueOf(hash)))
      return null; 
    LocalChunkData chunkData = this.chunkCache.remove(Long.valueOf(hash));
    if (chunkData != null)
      this.unloadedChunkCache.put(Long.valueOf(hash), chunkData); 
    return chunkData;
  }
  
  public void forceUnload(Chunk chunk) {
    long hash = LongHash.toLong(chunk.field_76635_g, chunk.field_76647_h);
    if (!this.unloadedChunkCache.containsKey(Long.valueOf(hash)))
      return; 
    this.unloadedChunkCache.remove(Long.valueOf(hash));
  }
  
  public void removeData(int x, int y, int z) {
    LocalChunkData chunkData = getChunkDataFromCoords(x, z);
    chunkData.remove(x & 0xF, y, z & 0xF);
  }
  
  public World getWorld() {
    return this.world;
  }
  
  public Map<Long, LocalChunkData> getUnloadedChunkCache() {
    return this.unloadedChunkCache;
  }
  
  public static LocalWorldData get(World world) {
    if (cache.containsKey(world))
      return cache.get(world); 
    LocalWorldData localWorldData = new LocalWorldData(world);
    cache.put(world, localWorldData);
    return localWorldData;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\localdata\world\LocalWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */