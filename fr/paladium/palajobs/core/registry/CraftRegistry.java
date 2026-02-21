package fr.paladium.palajobs.core.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftRegistry {
  private static CraftRegistry instance;
  
  public void register() {
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 0), new Object[] { new ItemStack(Items.field_151100_aR, 1, 0), new ItemStack(Items.field_151014_N) });
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 1), new Object[] { new ItemStack(Items.field_151100_aR, 1, 1), new ItemStack(Items.field_151014_N) });
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 2), new Object[] { new ItemStack(Items.field_151100_aR, 1, 2), new ItemStack(Items.field_151014_N) });
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 3), new Object[] { new ItemStack(Items.field_151100_aR, 1, 3), new ItemStack(Items.field_151014_N) });
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 4), new Object[] { new ItemStack(Items.field_151100_aR, 1, 4), new ItemStack(Items.field_151014_N) });
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 5), new Object[] { new ItemStack(Items.field_151100_aR, 1, 5), new ItemStack(Items.field_151014_N) });
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 6), new Object[] { new ItemStack(Items.field_151100_aR, 1, 11), new ItemStack(Items.field_151014_N) });
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 7), new Object[] { new ItemStack(Items.field_151100_aR, 1, 14), new ItemStack(Items.field_151014_N) });
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 8), new Object[] { new ItemStack(Items.field_151100_aR, 1, 9), new ItemStack(Items.field_151014_N) });
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 9), new Object[] { new ItemStack(Items.field_151100_aR, 1, 10), new ItemStack(Items.field_151014_N) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegistry.COLORED_TALL_GRASS, 1, 0), new Object[] { new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 0), new ItemStack((Block)Blocks.field_150329_H, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegistry.COLORED_TALL_GRASS, 1, 1), new Object[] { new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 1), new ItemStack((Block)Blocks.field_150329_H, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegistry.COLORED_TALL_GRASS, 1, 2), new Object[] { new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 2), new ItemStack((Block)Blocks.field_150329_H, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegistry.COLORED_TALL_GRASS, 1, 3), new Object[] { new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 3), new ItemStack((Block)Blocks.field_150329_H, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegistry.COLORED_TALL_GRASS, 1, 4), new Object[] { new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 4), new ItemStack((Block)Blocks.field_150329_H, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegistry.COLORED_TALL_GRASS, 1, 5), new Object[] { new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 5), new ItemStack((Block)Blocks.field_150329_H, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegistry.COLORED_TALL_GRASS, 1, 6), new Object[] { new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 6), new ItemStack((Block)Blocks.field_150329_H, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegistry.COLORED_TALL_GRASS, 1, 7), new Object[] { new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 7), new ItemStack((Block)Blocks.field_150329_H, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegistry.COLORED_TALL_GRASS, 1, 8), new Object[] { new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 8), new ItemStack((Block)Blocks.field_150329_H, 1, 1) });
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegistry.COLORED_TALL_GRASS, 1, 9), new Object[] { new ItemStack(BlocksRegistry.COLORED_GRASS_SEEDS, 1, 9), new ItemStack((Block)Blocks.field_150329_H, 1, 1) });
  }
  
  public static CraftRegistry getInstance() {
    if (instance == null)
      instance = new CraftRegistry(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\registry\CraftRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */