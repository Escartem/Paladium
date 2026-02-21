package fr.paladium.palamod.modules.hunter.world;

import cpw.mods.fml.common.IWorldGenerator;
import fr.paladium.palamod.api.BlocksRegister;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class BambooWorldGenerator implements IWorldGenerator {
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    generateBamboo(random, chunkX * 16, chunkZ * 16, world);
  }
  
  private void generateBamboo(Random random, int xChunk, int zChunk, World world) {
    BiomeGenBase biome = world.func_72807_a(xChunk, zChunk);
    if (biome.field_76791_y.toLowerCase().contains("jungle"))
      for (int x = xChunk; x < xChunk + 16; x++) {
        for (int z = zChunk; z < zChunk + 16; z++) {
          int y = world.func_72976_f(x, z) - 1;
          Block b = world.func_147439_a(x, y, z);
          if ((b == Blocks.field_150354_m || b == Blocks.field_150349_c) && random
            .nextInt((b == Blocks.field_150354_m) ? 15 : 35) == 0)
            for (int i = 0; i < random.nextInt(9) + 3 && 
              world.func_147439_a(x, y + i + 1, z) == Blocks.field_150350_a; i++)
              world.func_147449_b(x, y + i + 1, z, BlocksRegister.BAMBOO);  
        } 
      }  
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\world\BambooWorldGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */