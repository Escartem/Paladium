package fr.paladium.palamod.modules.alchimiste.common.generator;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCustomFlower extends WorldGenerator {
  private Block block;
  
  WorldGenCustomFlower(Block par1) {
    this.block = par1;
  }
  
  public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5) {
    BiomeGenBase b = par1World.func_72807_a(par3, par5);
    if (b.field_76791_y.equals("Plains") || b.field_76791_y.equals("Extreme Hills") || b.field_76791_y
      .equals("Forest"))
      for (int l = 0; l < 2; l++) {
        int i1 = par3 + par2Random.nextInt(1) - par2Random.nextInt(1);
        int j1 = par4 + par2Random.nextInt(1) - par2Random.nextInt(1);
        int k1 = par5 + par2Random.nextInt(1) - par2Random.nextInt(1);
        if (par1World.func_147437_c(i1, j1, k1) && this.block.func_149742_c(par1World, i1, j1, k1)) {
          int l1 = 1 + par2Random.nextInt(par2Random.nextInt(3) + 1);
          for (int i2 = 0; i2 < l1; i2++) {
            if (this.block.func_149718_j(par1World, i1, j1 + i2, k1))
              par1World.func_147465_d(i1, j1 + i2, k1, this.block, 0, 1); 
          } 
        } 
      }  
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\generator\WorldGenCustomFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */