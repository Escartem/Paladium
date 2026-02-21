package fr.paladium.palaforgeutils.lib.localdata.storage;

import fr.paladium.palaforgeutils.lib.localdata.chunk.LocalChunkData;
import fr.paladium.palaforgeutils.lib.localdata.world.LocalWorldData;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.chunk.Chunk;

public class LocalDataStorageHandler {
  private static final String NBT_KEY = "localData";
  
  public static void save(Chunk chunk, NBTTagCompound nbt) {
    LocalWorldData worldData = LocalWorldData.get(chunk.field_76637_e);
    LocalChunkData chunkData = worldData.getChunkData(chunk.field_76635_g, chunk.field_76647_h);
    worldData.forceUnload(chunk);
    NBTTagCompound dataNbt = new NBTTagCompound();
    chunkData.write(dataNbt);
    nbt.func_74782_a("localData", (NBTBase)dataNbt);
  }
  
  public static void load(Chunk chunk, NBTTagCompound nbt) {
    LocalWorldData worldData = LocalWorldData.get(chunk.field_76637_e);
    LocalChunkData chunkData = worldData.getChunkData(chunk.field_76635_g, chunk.field_76647_h);
    if (!nbt.func_74764_b("localData"))
      return; 
    chunkData.read(nbt.func_74775_l("localData"));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\localdata\storage\LocalDataStorageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */