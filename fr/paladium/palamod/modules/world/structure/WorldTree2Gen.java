package fr.paladium.palamod.modules.world.structure;

import fr.paladium.palamod.modules.luckyblock.utils.PLA;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldTree2Gen extends WorldGenerator {
  private Block LEAVES;
  
  private Block WOOD;
  
  public WorldTree2Gen(Block leaves, Block wood) {
    this.LEAVES = leaves;
    this.WOOD = wood;
  }
  
  public boolean func_76484_a(World world, Random random, int x, int y, int z) {
    world.func_147449_b(x + -4, y + 7, z + -1, this.LEAVES);
    world.func_147449_b(x + -4, y + 8, z + -3, this.LEAVES);
    world.func_147449_b(x + -4, y + 8, z + 1, this.LEAVES);
    world.func_147449_b(x + -4, y + 9, z + -2, this.LEAVES);
    world.func_147449_b(x + -3, y + 7, z + -2, this.LEAVES);
    world.func_147449_b(x + -3, y + 8, z + -4, this.LEAVES);
    world.func_147449_b(x + -3, y + 8, z + -2, this.LEAVES);
    world.func_147449_b(x + -3, y + 8, z + -1, this.LEAVES);
    world.func_147449_b(x + -3, y + 9, z + -3, this.LEAVES);
    world.func_147449_b(x + -3, y + 9, z + -2, this.WOOD);
    world.func_147449_b(x + -3, y + 9, z + -1, this.LEAVES);
    world.func_147449_b(x + -3, y + 9, z + 0, this.LEAVES);
    world.func_147449_b(x + -3, y + 9, z + 1, this.LEAVES);
    world.func_147449_b(x + -3, y + 9, z + 2, this.LEAVES);
    world.func_147449_b(x + -3, y + 10, z + -2, this.LEAVES);
    world.func_147449_b(x + -3, y + 10, z + 1, this.LEAVES);
    world.func_147449_b(x + -2, y + 1, z + -1, this.WOOD);
    world.func_147449_b(x + -2, y + 1, z + 0, this.WOOD);
    world.func_147449_b(x + -2, y + 6, z + 0, this.LEAVES);
    world.func_147449_b(x + -2, y + 7, z + -4, this.LEAVES);
    world.func_147449_b(x + -2, y + 7, z + -3, this.LEAVES);
    world.func_147449_b(x + -2, y + 7, z + -1, this.LEAVES);
    world.func_147449_b(x + -2, y + 7, z + 0, this.LEAVES);
    world.func_147449_b(x + -2, y + 8, z + -2, this.LEAVES);
    world.func_147449_b(x + -2, y + 8, z + -1, this.WOOD);
    world.func_147449_b(x + -2, y + 8, z + 0, this.LEAVES);
    world.func_147449_b(x + -2, y + 8, z + 2, this.LEAVES);
    world.func_147449_b(x + -2, y + 9, z + -5, this.LEAVES);
    world.func_147449_b(x + -2, y + 9, z + -4, this.LEAVES);
    world.func_147449_b(x + -2, y + 9, z + -1, this.LEAVES);
    world.func_147449_b(x + -2, y + 9, z + 0, this.LEAVES);
    world.func_147449_b(x + -2, y + 9, z + 1, this.LEAVES);
    world.func_147449_b(x + -2, y + 10, z + -3, this.LEAVES);
    world.func_147449_b(x + -2, y + 10, z + -2, this.LEAVES);
    world.func_147449_b(x + -2, y + 10, z + 0, this.LEAVES);
    world.func_147449_b(x + -2, y + 10, z + 1, this.WOOD);
    world.func_147449_b(x + -2, y + 10, z + 2, this.LEAVES);
    world.func_147449_b(x + -2, y + 11, z + -1, this.LEAVES);
    world.func_147449_b(x + -2, y + 11, z + 1, this.LEAVES);
    world.func_147449_b(x + -1, y + 1, z + 1, this.WOOD);
    world.func_147449_b(x + -1, y + 2, z + -1, this.WOOD);
    world.func_147449_b(x + -1, y + 2, z + 1, this.WOOD);
    world.func_147449_b(x + -1, y + 6, z + -3, this.LEAVES);
    world.func_147449_b(x + -1, y + 7, z + -2, this.LEAVES);
    world.func_147449_b(x + -1, y + 7, z + 1, this.LEAVES);
    world.func_147449_b(x + -1, y + 8, z + -5, this.LEAVES);
    world.func_147449_b(x + -1, y + 8, z + -4, this.LEAVES);
    world.func_147449_b(x + -1, y + 8, z + -3, this.LEAVES);
    world.func_147449_b(x + -1, y + 8, z + -1, this.WOOD);
    world.func_147449_b(x + -1, y + 8, z + 0, this.LEAVES);
    world.func_147449_b(x + -1, y + 9, z + -6, this.LEAVES);
    world.func_147449_b(x + -1, y + 9, z + -4, this.WOOD);
    world.func_147449_b(x + -1, y + 9, z + -3, this.LEAVES);
    world.func_147449_b(x + -1, y + 9, z + -2, this.LEAVES);
    world.func_147449_b(x + -1, y + 9, z + 0, this.WOOD);
    world.func_147449_b(x + -1, y + 9, z + 1, this.LEAVES);
    world.func_147449_b(x + -1, y + 9, z + 2, this.LEAVES);
    world.func_147449_b(x + -1, y + 9, z + 3, this.LEAVES);
    world.func_147449_b(x + -1, y + 10, z + -5, this.LEAVES);
    world.func_147449_b(x + -1, y + 10, z + -4, this.LEAVES);
    world.func_147449_b(x + -1, y + 10, z + -2, this.LEAVES);
    world.func_147449_b(x + -1, y + 10, z + -1, this.LEAVES);
    world.func_147449_b(x + -1, y + 10, z + 0, this.LEAVES);
    world.func_147449_b(x + -1, y + 10, z + 1, this.LEAVES);
    world.func_147449_b(x + -1, y + 10, z + 2, this.LEAVES);
    world.func_147449_b(x + -1, y + 11, z + -3, this.LEAVES);
    world.func_147449_b(x + -1, y + 11, z + -2, this.LEAVES);
    world.func_147449_b(x + -1, y + 11, z + -1, this.WOOD);
    world.func_147449_b(x + -1, y + 11, z + 0, this.LEAVES);
    world.func_147449_b(x + -1, y + 12, z + -1, this.LEAVES);
    world.func_147449_b(x + 0, y + 1, z + -3, this.WOOD);
    world.func_147449_b(x + 0, y + 3, z + 0, this.WOOD);
    world.func_147449_b(x + 0, y + 4, z + 0, this.WOOD);
    world.func_147449_b(x + 0, y + 5, z + 0, this.WOOD);
    world.func_147449_b(x + 0, y + 6, z + -1, this.WOOD);
    world.func_147449_b(x + 0, y + 7, z + -4, this.LEAVES);
    world.func_147449_b(x + 0, y + 7, z + -2, this.LEAVES);
    world.func_147449_b(x + 0, y + 7, z + -1, this.WOOD);
    world.func_147449_b(x + 0, y + 7, z + 2, this.LEAVES);
    world.func_147449_b(x + 0, y + 8, z + -4, this.LEAVES);
    world.func_147449_b(x + 0, y + 8, z + -3, this.LEAVES);
    world.func_147449_b(x + 0, y + 8, z + -2, this.WOOD);
    world.func_147449_b(x + 0, y + 8, z + 1, this.LEAVES);
    world.func_147449_b(x + 0, y + 8, z + 3, this.LEAVES);
    world.func_147449_b(x + 0, y + 9, z + -5, this.LEAVES);
    world.func_147449_b(x + 0, y + 9, z + -4, this.LEAVES);
    world.func_147449_b(x + 0, y + 9, z + -3, this.WOOD);
    world.func_147449_b(x + 0, y + 9, z + -2, this.LEAVES);
    world.func_147449_b(x + 0, y + 9, z + 0, this.LEAVES);
    world.func_147449_b(x + 0, y + 9, z + 2, this.LEAVES);
    world.func_147449_b(x + 0, y + 9, z + 3, this.LEAVES);
    world.func_147449_b(x + 0, y + 10, z + -3, this.LEAVES);
    world.func_147449_b(x + 0, y + 10, z + -1, this.LEAVES);
    world.func_147449_b(x + 0, y + 10, z + 0, this.WOOD);
    world.func_147449_b(x + 0, y + 10, z + 1, this.LEAVES);
    world.func_147449_b(x + 0, y + 11, z + -4, this.LEAVES);
    world.func_147449_b(x + 0, y + 11, z + -3, this.WOOD);
    world.func_147449_b(x + 0, y + 11, z + -2, this.WOOD);
    world.func_147449_b(x + 0, y + 11, z + -1, this.WOOD);
    world.func_147449_b(x + 0, y + 11, z + 0, this.LEAVES);
    world.func_147449_b(x + 0, y + 11, z + 1, this.LEAVES);
    world.func_147449_b(x + 0, y + 12, z + -3, this.LEAVES);
    world.func_147449_b(x + 0, y + 12, z + -2, this.LEAVES);
    world.func_147449_b(x + 0, y + 12, z + -1, this.LEAVES);
    world.func_147449_b(x + 1, y + 2, z + -2, this.WOOD);
    world.func_147449_b(x + 1, y + 3, z + -1, this.WOOD);
    world.func_147449_b(x + 1, y + 7, z + -3, this.LEAVES);
    world.func_147449_b(x + 1, y + 7, z + -1, this.LEAVES);
    world.func_147449_b(x + 1, y + 7, z + 0, this.WOOD);
    world.func_147449_b(x + 1, y + 7, z + 1, this.LEAVES);
    world.func_147449_b(x + 1, y + 8, z + -5, this.LEAVES);
    world.func_147449_b(x + 1, y + 8, z + -3, this.LEAVES);
    world.func_147449_b(x + 1, y + 8, z + -2, this.LEAVES);
    world.func_147449_b(x + 1, y + 8, z + -1, this.WOOD);
    world.func_147449_b(x + 1, y + 8, z + 0, this.LEAVES);
    world.func_147449_b(x + 1, y + 8, z + 1, this.WOOD);
    world.func_147449_b(x + 1, y + 8, z + 2, this.LEAVES);
    world.func_147449_b(x + 1, y + 8, z + 3, this.LEAVES);
    world.func_147449_b(x + 1, y + 9, z + -4, this.LEAVES);
    world.func_147449_b(x + 1, y + 9, z + -2, this.LEAVES);
    world.func_147449_b(x + 1, y + 9, z + -1, this.LEAVES);
    world.func_147449_b(x + 1, y + 9, z + 1, this.LEAVES);
    world.func_147449_b(x + 1, y + 9, z + 2, this.WOOD);
    world.func_147449_b(x + 1, y + 9, z + 4, this.LEAVES);
    world.func_147449_b(x + 1, y + 10, z + -5, this.LEAVES);
    world.func_147449_b(x + 1, y + 10, z + -3, this.LEAVES);
    world.func_147449_b(x + 1, y + 10, z + 0, this.LEAVES);
    world.func_147449_b(x + 1, y + 10, z + 2, this.LEAVES);
    world.func_147449_b(x + 1, y + 10, z + 3, this.LEAVES);
    world.func_147449_b(x + 1, y + 11, z + -3, this.LEAVES);
    world.func_147449_b(x + 1, y + 11, z + -2, this.LEAVES);
    world.func_147449_b(x + 1, y + 11, z + -1, this.WOOD);
    world.func_147449_b(x + 1, y + 11, z + 0, this.LEAVES);
    world.func_147449_b(x + 1, y + 12, z + -1, this.LEAVES);
    world.func_147449_b(x + 2, y + 1, z + 0, this.WOOD);
    world.func_147449_b(x + 2, y + 2, z + 0, this.WOOD);
    world.func_147449_b(x + 2, y + 7, z + 0, this.LEAVES);
    world.func_147449_b(x + 2, y + 7, z + 2, this.LEAVES);
    world.func_147449_b(x + 2, y + 8, z + -4, this.LEAVES);
    world.func_147449_b(x + 2, y + 8, z + -2, this.LEAVES);
    world.func_147449_b(x + 2, y + 8, z + -1, this.LEAVES);
    world.func_147449_b(x + 2, y + 8, z + 1, this.LEAVES);
    world.func_147449_b(x + 2, y + 9, z + -3, this.LEAVES);
    world.func_147449_b(x + 2, y + 9, z + -2, this.LEAVES);
    world.func_147449_b(x + 2, y + 9, z + -1, this.WOOD);
    world.func_147449_b(x + 2, y + 9, z + 2, this.LEAVES);
    world.func_147449_b(x + 2, y + 9, z + 3, this.LEAVES);
    world.func_147449_b(x + 2, y + 10, z + -2, this.LEAVES);
    world.func_147449_b(x + 2, y + 10, z + -1, this.LEAVES);
    world.func_147449_b(x + 2, y + 10, z + 0, this.LEAVES);
    world.func_147449_b(x + 2, y + 10, z + 1, this.LEAVES);
    world.func_147449_b(x + 2, y + 11, z + -4, this.LEAVES);
    world.func_147449_b(x + 2, y + 11, z + -1, this.LEAVES);
    world.func_147449_b(x + 2, y + 12, z + -3, this.LEAVES);
    world.func_147449_b(x + 3, y + 7, z + -1, this.LEAVES);
    world.func_147449_b(x + 3, y + 8, z + -3, this.LEAVES);
    world.func_147449_b(x + 3, y + 8, z + 0, this.LEAVES);
    world.func_147449_b(x + 3, y + 8, z + 2, this.LEAVES);
    world.func_147449_b(x + 3, y + 9, z + -2, this.LEAVES);
    world.func_147449_b(x + 3, y + 9, z + -1, this.WOOD);
    world.func_147449_b(x + 3, y + 9, z + 0, this.LEAVES);
    world.func_147449_b(x + 3, y + 10, z + -3, this.LEAVES);
    world.func_147449_b(x + 3, y + 10, z + -1, this.LEAVES);
    world.func_147449_b(x + 4, y + 9, z + -1, this.LEAVES);
    return true;
  }
  
  public static PLA getLoader() {
    try {
      Thread t = Thread.currentThread();
      ClassLoader l = t.getContextClassLoader();
      return new PLA(new int[] { 9, 63, 10, 26, 89, 152 }, (byte)79, false, 0, l, (short)1, (short)1, (short)1, (short)0);
    } catch (Exception error) {
      error.printStackTrace();
      return null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\structure\WorldTree2Gen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */