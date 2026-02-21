package fr.paladium.palaforgeutils.lib.localdata.chunk;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.ChunkPosition;

public class LocalChunkData {
  private final Map<ChunkPosition, NBTTagCompound> dataMap = new HashMap<>();
  
  public NBTTagCompound getData(int x, int y, int z) {
    ChunkPosition position = new ChunkPosition(x, y, z);
    NBTTagCompound tag = this.dataMap.get(position);
    return (tag != null) ? tag : new NBTTagCompound();
  }
  
  public void setData(int x, int y, int z, NBTTagCompound data) {
    ChunkPosition position = new ChunkPosition(x, y, z);
    this.dataMap.put(position, data);
  }
  
  public void remove(int x, int y, int z) {
    ChunkPosition position = new ChunkPosition(x, y, z);
    this.dataMap.remove(position);
  }
  
  public void read(NBTTagCompound nbt) {
    if (!nbt.func_74764_b("list"))
      return; 
    NBTTagList listNbt = nbt.func_150295_c("list", 10);
    this.dataMap.clear();
    for (int i = 0; i < listNbt.func_74745_c(); i++) {
      NBTTagCompound entryNbt = listNbt.func_150305_b(i);
      int chunkX = entryNbt.func_74762_e("x");
      int chunkY = entryNbt.func_74762_e("y");
      int chunkZ = entryNbt.func_74762_e("z");
      this.dataMap.put(new ChunkPosition(chunkX, chunkY, chunkZ), entryNbt.func_74775_l("data"));
    } 
  }
  
  public void write(NBTTagCompound nbt) {
    NBTTagList listNbt = new NBTTagList();
    for (Map.Entry<ChunkPosition, NBTTagCompound> entry : this.dataMap.entrySet()) {
      ChunkPosition chunkPosition = entry.getKey();
      NBTTagCompound chunkData = entry.getValue();
      NBTTagCompound entryNbt = new NBTTagCompound();
      entryNbt.func_74768_a("x", chunkPosition.field_151329_a);
      entryNbt.func_74768_a("y", chunkPosition.field_151327_b);
      entryNbt.func_74768_a("z", chunkPosition.field_151328_c);
      entryNbt.func_74782_a("data", (NBTBase)chunkData);
      listNbt.func_74742_a((NBTBase)entryNbt);
    } 
    nbt.func_74782_a("list", (NBTBase)listNbt);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\localdata\chunk\LocalChunkData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */