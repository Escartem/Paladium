package fr.paladium.palamod.modules.paladium.utils;

import fr.paladium.palamod.modules.paladium.common.events.ChunkHandler;
import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public class CheckTileEntityRunnable implements Runnable {
  private int id;
  
  private int y;
  
  private ChunkPosition chunkPosition;
  
  public CheckTileEntityRunnable(int id, int y, ChunkPosition chunkPosition) {
    this.id = id;
    this.y = y;
    this.chunkPosition = chunkPosition;
  }
  
  public void run() {
    if (Minecraft.func_71410_x() == null) {
      System.err.println("[UnclaimFinder][Error] Minecraft not exist");
      ChunkHandler.getChunkMap().remove(this.chunkPosition);
      return;
    } 
    if ((Minecraft.func_71410_x()).field_71439_g == null) {
      System.err.println("[UnclaimFinder][Error] Player not exist");
      ChunkHandler.getChunkMap().remove(this.chunkPosition);
      return;
    } 
    if ((Minecraft.func_71410_x()).field_71439_g.field_70170_p == null) {
      System.err.println("[UnclaimFinder][Error] World not exist");
      ChunkHandler.getChunkMap().remove(this.chunkPosition);
      return;
    } 
    int chunkX = this.chunkPosition.field_151329_a * 16;
    int chunkZ = this.chunkPosition.field_151328_c * 16;
    try {
      World world = (Minecraft.func_71410_x()).field_71439_g.field_70170_p;
      if (!world.func_72863_F().func_73149_a(chunkX, chunkZ)) {
        ChunkHandler.getChunkMap().remove(this.chunkPosition);
        System.err.println("[UnclaimFinder][Error] Chunk not exist");
        return;
      } 
      for (int ox = chunkX; ox < chunkX + 16; ox++) {
        for (int oz = chunkZ; oz < chunkZ + 16; oz++) {
          for (int oy = this.y; oy < this.y + 64; oy++) {
            Block block = world.func_147439_a(ox, oy, oz);
            if (block != null)
              if (block != Blocks.field_150350_a) {
                int meta = world.func_72805_g(ox, oy, oz);
                if (block.hasTileEntity(meta))
                  TileEntity tileEntity = world.func_147438_o(ox, oy, oz); 
              }  
          } 
        } 
      } 
      Boolean[] check = (Boolean[])ChunkHandler.getChunkMap().get(this.chunkPosition);
      check[this.id] = Boolean.valueOf(true);
      ChunkHandler.getChunkMap().put(this.chunkPosition, check);
      if (Arrays.<Boolean>stream(check).allMatch(b -> b.booleanValue()))
        ChunkHandler.getChunkMap().remove(this.chunkPosition); 
    } catch (Exception e) {
      System.err.println("[UnclaimFinder][Error] Chunk loading");
      ChunkHandler.getChunkMap().remove(this.chunkPosition);
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\CheckTileEntityRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */