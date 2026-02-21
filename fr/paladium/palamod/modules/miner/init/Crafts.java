package fr.paladium.palamod.modules.miner.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Items;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Crafts {
  public static void register() {
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.SPAWNER_FINDER, 1, 0), new Object[] { " F ", "PBP", " F ", Character.valueOf('F'), BlocksRegister.BLOCK_FINDIUM, Character.valueOf('P'), ItemsRegister.PALADIUM_CORE, Character.valueOf('B'), SpawnerBlockRegistry.EMPTY_BROKEN_MOB_SPAWNER });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.BUILDER_WAND, 1, 0), new Object[] { " BB", " SB", "S  ", Character.valueOf('B'), BlocksRegister.BLOCK_AMETHYST, Character.valueOf('S'), ItemsRegister.STICK_AMETHYST });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.BUILDER_WAND, 1, 1), new Object[] { " BB", " SB", "S  ", Character.valueOf('B'), BlocksRegister.BLOCK_TITANE, Character.valueOf('S'), ItemsRegister.STICK_TITANE });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.BUILDER_WAND, 1, 2), new Object[] { " BB", " SB", "S  ", Character.valueOf('B'), BlocksRegister.BLOCK_PALADIUM, Character.valueOf('S'), ItemsRegister.STICK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.BUILDER_WAND, 1, 3), new Object[] { "  E", " S ", "S  ", Character.valueOf('E'), ItemsRegister.ENDIUM_NUGGET, Character.valueOf('S'), ItemsRegister.STICK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.AMETHYST_EXCAVATOR, 1, 0), new Object[] { "PPP", " S ", " S ", Character.valueOf('P'), ItemsRegister.AMETHYST_SHOVEL, Character.valueOf('S'), ItemsRegister.STICK_AMETHYST });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.TITANE_EXCAVATOR, 1, 0), new Object[] { "PPP", " S ", " S ", Character.valueOf('P'), ItemsRegister.TITANE_SHOVEL, Character.valueOf('S'), ItemsRegister.STICK_TITANE });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.PALADIUM_EXCAVATOR, 1, 0), new Object[] { "PPP", " S ", " S ", Character.valueOf('P'), ItemsRegister.PALADIUM_SHOVEL, Character.valueOf('S'), ItemsRegister.STICK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.PALADIUM_HOPPER, 1, 0), new Object[] { "P P", "PCP", " P ", Character.valueOf('P'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('C'), BlocksRegister.TITANE_CHEST });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.COBBLEBREAKER_AMETHYST_UPGRADE, 1, 0), new Object[] { "ACA", "A A", "ASA", Character.valueOf('A'), ItemsRegister.AMETHYST_INGOT, Character.valueOf('C'), BlocksRegister.COBBLEBREAKER, Character.valueOf('S'), PSRegister_Items.MODIFIER_SPEED });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.COBBLEBREAKER_TITANE_UPGRADE, 1, 0), new Object[] { "T T", "TUT", "T T", Character.valueOf('T'), ItemsRegister.TITANE_INGOT, Character.valueOf('U'), ItemsRegister.COBBLEBREAKER_AMETHYST_UPGRADE });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.COBBLEBREAKER_PALADIUM_UPGRADE, 1, 0), new Object[] { "P P", "PUP", "P P", Character.valueOf('P'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('U'), ItemsRegister.COBBLEBREAKER_TITANE_UPGRADE });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.MAGNET, 1, 0), new Object[] { "P P", "B B", "BBB", Character.valueOf('P'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('B'), Blocks.field_150339_S });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.DRAWBRIDGE, 1, 0), new Object[] { 
          "P P", "BDB", "RRR", Character.valueOf('P'), Blocks.field_150331_J, Character.valueOf('B'), ModBlocks.slime, Character.valueOf('D'), Blocks.field_150367_z, Character.valueOf('R'), 
          Items.field_151137_ax });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.SEALED_XP_BOTTLE, 1, 0), new Object[] { "   ", "PBP", " P ", Character.valueOf('P'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('B'), Items.field_151069_bo });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.GOD_PICKAXE, 1, 0), new Object[] { 
          "APT", " S ", " S ", Character.valueOf('A'), ItemsRegister.AMETHYST_PICKAXE, Character.valueOf('P'), ItemsRegister.PALADIUM_PICKAXE, Character.valueOf('T'), ItemsRegister.TITANE_PICKAXE, Character.valueOf('S'), 
          ItemsRegister.STICK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.MOULA_STONE, 1, 0), new Object[] { 
          "EPE", "TVT", "EAE", Character.valueOf('E'), Items.field_151079_bi, Character.valueOf('P'), ItemsRegister.TITANE_INGOT, Character.valueOf('P'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('A'), 
          ItemsRegister.AMETHYST_INGOT, Character.valueOf('V'), ItemsRegister.VOIDSTONE });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.GOD_PICKAXE_AUTOSMELT, 1, 0), new Object[] { "XUX", "XXX", "   ", Character.valueOf('X'), Blocks.field_150359_w, Character.valueOf('U'), BlocksRegister.PALADIUM_FURNACE });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.ENDIUM_CAVE_BLOCK, 1, 0), new Object[] { "CCC", "CEC", "CCC", Character.valueOf('C'), BlocksRegister.CAVE_BLOCK, Character.valueOf('E'), ItemsRegister.ENDIUM_NUGGET });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\init\Crafts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */