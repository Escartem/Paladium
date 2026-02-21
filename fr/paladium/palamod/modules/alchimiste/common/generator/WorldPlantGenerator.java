package fr.paladium.palamod.modules.alchimiste.common.generator;

import cpw.mods.fml.common.IWorldGenerator;
import fr.paladium.palamod.api.BlocksRegister;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldPlantGenerator implements IWorldGenerator {
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    switch (world.field_73011_w.field_76574_g) {
      case 0:
        generateSurface(world, random, chunkX * 16, chunkZ * 16);
        break;
    } 
  }
  
  private void generateSurface(World world, Random random, int BlockX, int BlockZ) {
    int i;
    for (i = 0; i < 2; i++) {
      int posX = BlockX + random.nextInt(16);
      int posZ = BlockZ + random.nextInt(16);
      int posY = 50 + random.nextInt(25);
      if (random.nextInt(1000) == 1)
        (new WorldGenCustomFlower(BlocksRegister.FLOWER_HARPAGOPHYTUM)).func_76484_a(world, random, posX, posY, posZ); 
    } 
    for (i = 0; i < 2; i++) {
      int posX = BlockX + random.nextInt(16);
      int posZ = BlockZ + random.nextInt(16);
      int posY = 50 + random.nextInt(25);
      if (random.nextInt(1500) == 1)
        (new WorldGenCustomFlower(BlocksRegister.FLOWER_SAUGE)).func_76484_a(world, random, posX, posY, posZ); 
    } 
    for (i = 0; i < 2; i++) {
      int posX = BlockX + random.nextInt(16);
      int posZ = BlockZ + random.nextInt(16);
      int posY = 50 + random.nextInt(25);
      if (random.nextInt(100) == 1)
        (new WorldGenCustomFlower(BlocksRegister.FLOWER_CLATHRUSARCHERI)).func_76484_a(world, random, posX, posY, posZ); 
    } 
    for (i = 0; i < 2; i++) {
      int posX = BlockX + random.nextInt(16);
      int posZ = BlockZ + random.nextInt(16);
      int posY = 50 + random.nextInt(25);
      if (random.nextInt(200) == 1)
        (new WorldGenCustomFlower(BlocksRegister.FLOWER_ENDIUM)).func_76484_a(world, random, posX, posY, posZ); 
    } 
    for (i = 0; i < 2; i++) {
      int posX = BlockX + random.nextInt(16);
      int posZ = BlockZ + random.nextInt(16);
      int posY = 50 + random.nextInt(25);
      if (random.nextInt(10) == 1)
        (new WorldGenCustomFlower(BlocksRegister.FLOWER_PALADIUM)).func_76484_a(world, random, posX, posY, posZ); 
    } 
    for (i = 0; i < 2; i++) {
      int posX = BlockX + random.nextInt(16);
      int posZ = BlockZ + random.nextInt(16);
      int posY = 50 + random.nextInt(25);
      if (random.nextInt(500) == 1)
        (new WorldGenCustomFlower(BlocksRegister.FLOWER_MINERAL)).func_76484_a(world, random, posX, posY, posZ); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\generator\WorldPlantGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */