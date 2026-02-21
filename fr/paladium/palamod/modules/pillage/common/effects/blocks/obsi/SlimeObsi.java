package fr.paladium.palamod.modules.pillage.common.effects.blocks.obsi;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.helios.utils.BlockPos;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.pillage.PPillage;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TypeEffects;
import fr.paladium.palamod.modules.pillage.common.effects.ObjectEffect;
import fr.paladium.palamod.modules.pillage.common.recipes.RecipesSlimeObsidian;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class SlimeObsi implements ObjectEffect {
  public void applyEffect(World world, double x, double y, double z, int metadata, Entity entity) {
    if (PPillage.debugMode)
      System.out.println("[SlimeObsi] Apply effect at " + x + ", " + y + ", " + z + " caused by " + entity); 
    int mappedMetadata = (metadata == 0) ? 8 : ((metadata == 1) ? 10 : ((metadata == 2) ? 12 : ((metadata == 3) ? 14 : 8)));
    BlockPos pos = new BlockPos(x, y, z);
    world.func_147465_d(pos.getX(), pos.getY(), pos.getZ(), BlocksRegister.SLIMEPAD_BLOCK, mappedMetadata, 2);
    float initialValueRadius = 0.9F;
    for (int ox = -1; ox <= 1; ox++) {
      for (int oz = -1; oz <= 1; oz++) {
        if (ox != 0 || oz != 0)
          if (world.field_73012_v.nextFloat() <= 0.9F) {
            pos = new BlockPos(x + ox, y, z + oz);
            int blockX = pos.getX();
            int blockY = pos.getY();
            int blockZ = pos.getZ();
            Block block = world.func_147439_a(blockX, blockY, blockZ);
            if (block == Blocks.field_150350_a || block.func_149688_o().func_76224_d() || !block.func_149688_o().func_76220_a())
              world.func_147465_d(blockX, (int)y, blockZ, BlocksRegister.SLIMEPAD_BLOCK, mappedMetadata, 2); 
          }  
      } 
    } 
    float initialValueRadiusExt = 0.4F;
    for (int i = -2; i <= 2; i++) {
      for (int oz = -2; oz <= 2; oz++) {
        if (i != 0 || oz != 0)
          if (world.field_73012_v.nextFloat() <= 0.4F) {
            pos = new BlockPos(x + i, y, z + oz);
            int blockX = pos.getX();
            int blockY = pos.getY();
            int blockZ = pos.getZ();
            Block block = world.func_147439_a(blockX, blockY, blockZ);
            if (block == Blocks.field_150350_a || block.func_149688_o().func_76224_d() || !block.func_149688_o().func_76220_a())
              world.func_147465_d(blockX, (int)y, blockZ, BlocksRegister.SLIMEPAD_BLOCK, mappedMetadata, 2); 
          }  
      } 
    } 
  }
  
  public ObjectEffect registerRecipes(Block block) {
    GameRegistry.addRecipe((IRecipe)new RecipesSlimeObsidian(block));
    return this;
  }
  
  public String getName() {
    return "slime_obsi";
  }
  
  public String getDescription() {
    return "Remplace l’obsidienne par des Slimepads posés aléatoirement";
  }
  
  public TypeEffects getTypeEffect() {
    return TypeEffects.SLIME_OBSIDIAN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\blocks\obsi\SlimeObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */