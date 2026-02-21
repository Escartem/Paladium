package fr.paladium.palamod.modules.back2future.world;

import cpw.mods.fml.common.IWorldGenerator;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.blocks.ChorusFlower;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.util.ForgeDirection;

public class B2FWorldGenerator implements IWorldGenerator {
  private final List<WorldGenMinable> generators = new LinkedList<>();
  
  public B2FWorldGenerator() {
    this.generators.add(new WorldGenMinable(ModBlocks.stone, 1, Back2Future.maxStonesPerCluster, Blocks.field_150348_b));
    this.generators.add(new WorldGenMinable(ModBlocks.stone, 3, Back2Future.maxStonesPerCluster, Blocks.field_150348_b));
    this.generators.add(new WorldGenMinable(ModBlocks.stone, 5, Back2Future.maxStonesPerCluster, Blocks.field_150348_b));
  }
  
  public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    if (Back2Future.enableStones && Back2Future.maxStonesPerCluster > 0 && world.field_73011_w.field_76574_g != -1 && world.field_73011_w.field_76574_g != 1)
      for (Iterator<WorldGenMinable> iterator = this.generators.iterator(); iterator.hasNext(); ) {
        WorldGenMinable generator = iterator.next();
        for (int i = 0; i < 10; i++) {
          int x = chunkX * 16 + rand.nextInt(16);
          int y = rand.nextInt(80);
          int z = chunkZ * 16 + rand.nextInt(16);
          generator.func_76484_a(world, rand, x, y, z);
        } 
      }  
    if (Back2Future.enableChorusFruit && world.field_73011_w.field_76574_g == 1) {
      int x = chunkX * 16 + rand.nextInt(16);
      int y = 256;
      int z = chunkZ * 16 + rand.nextInt(16);
      for (; y > 0 && 
        world.func_147439_a(x, y, z).isAir((IBlockAccess)world, x, y, z); y--);
      if (y > 0 && ChorusFlower.canPlantStay(world, x, y + 1, z))
        generateChorusPlant(world, x, y + 1, z, 0); 
    } 
  }
  
  public static void generateChorusPlant(World world, int x, int y, int z, int pass) {
    int height;
    for (height = 0; height < 4; height++) {
      if (!ChorusFlower.canPlantStay(world, x, y + height, z)) {
        world.func_147465_d(x, y + height, z, ModBlocks.chorus_flower, 5, 2);
        break;
      } 
      world.func_147449_b(x, y + height, z, ModBlocks.chorus_plant);
    } 
    if (height > 1) {
      world.func_147449_b(x, y + height, z, ModBlocks.chorus_plant);
      boolean grew = false;
      if (pass < 5) {
        ForgeDirection[] dirs = { ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.NORTH, ForgeDirection.SOUTH };
        for (int j = 0; j < world.field_73012_v.nextInt(4); j++) {
          ForgeDirection dir = dirs[world.field_73012_v.nextInt(dirs.length)];
          int xx = x + dir.offsetX;
          int yy = y + height + dir.offsetY;
          int zz = z + dir.offsetZ;
          if (world.func_147437_c(xx, yy, zz) && 
            ChorusFlower.isSpaceAroundFree(world, xx, yy, zz, dir.getOpposite())) {
            generateChorusPlant(world, xx, yy, zz, pass + 1);
            grew = true;
          } 
        } 
      } 
      if (!grew)
        world.func_147465_d(x, y + height, z, ModBlocks.chorus_flower, 5, 2); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\world\B2FWorldGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */