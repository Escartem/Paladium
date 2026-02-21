package fr.paladium.palamod.modules.luckyblock.utils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AITool {
  public static int distanceToFloor(Entity entity) {
    int i = MathHelper.func_76128_c(entity.field_70165_t);
    int j = MathHelper.func_76128_c(entity.field_70163_u);
    int k = MathHelper.func_76128_c(entity.field_70161_v);
    for (int x = 0; x < 64; x++) {
      Block block = entity.field_70170_p.func_147439_a(i, j - x, k);
      if (block != Blocks.field_150350_a)
        return x; 
    } 
    return 0;
  }
  
  public static double waterSurfaceAtGivenPosition(double posX, double posY, double posZ, World worldIn) {
    int i = MathHelper.func_76128_c(posX);
    int j = MathHelper.func_76128_c(posY);
    int k = MathHelper.func_76128_c(posZ);
    Block block = worldIn.func_147439_a(i, j, k);
    if (block != Blocks.field_150350_a && block.func_149688_o() == Material.field_151586_h)
      for (int x = 1; x < 64; x++) {
        block = worldIn.func_147439_a(i, j + x, k);
        if (block == Blocks.field_150350_a || block.func_149688_o() != Material.field_151586_h)
          return (j + x); 
      }  
    return 0.0D;
  }
  
  public static double waterSurfaceAtGivenEntity(Entity entity) {
    return waterSurfaceAtGivenPosition(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70170_p);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\AITool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */