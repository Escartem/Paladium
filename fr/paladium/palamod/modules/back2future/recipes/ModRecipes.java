package fr.paladium.palamod.modules.back2future.recipes;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.blocks.ConcreteRegistry;
import fr.paladium.palamod.modules.back2future.lib.EnumColour;
import fr.paladium.palamod.modules.back2future.lib.EnumDyeColor;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
  public static String[] dyes = new String[] { 
      "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", 
      "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite" };
  
  private static final ItemStack _SAND_ = new ItemStack((Block)Blocks.field_150354_m);
  
  private static final ItemStack _GRAVEL_ = new ItemStack(Blocks.field_150351_n);
  
  public static void init() {
    if (Back2Future.enableBanners) {
      RecipeSorter.register("palamod.RecipeDuplicatePattern", RecipeDuplicatePattern.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
      RecipeSorter.register("palamod.RecipeAddPattern", RecipeAddPattern.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped");
    } 
    registerOreDictionary();
    registerRecipes();
    tweakRecipes();
  }
  
  private static void tweakRecipes() {
    if (Back2Future.enableDoors) {
      Items.field_151135_aq.func_77625_d(64);
      Items.field_151139_aw.func_77625_d(64);
      removeFirstRecipeFor(Items.field_151135_aq);
      removeFirstRecipeFor(Items.field_151139_aw);
    } 
    if (Back2Future.enableFences) {
      removeFirstRecipeFor(Blocks.field_150422_aJ);
      removeFirstRecipeFor(Blocks.field_150396_be);
      Blocks.field_150422_aJ.func_149647_a(null);
      Blocks.field_150396_be.func_149647_a(null);
    } 
    if (Back2Future.enableBurnableBlocks) {
      Blocks.field_150480_ab.setFireInfo(Blocks.field_150396_be, 5, 20);
      Blocks.field_150480_ab.setFireInfo(Blocks.field_150422_aJ, 5, 20);
      Blocks.field_150480_ab.setFireInfo((Block)Blocks.field_150330_I, 60, 100);
    } 
  }
  
  private static void registerOreDictionary() {
    OreDictionary.registerOre("chestWood", new ItemStack((Block)Blocks.field_150486_ae));
    OreDictionary.registerOre("trapdoorWood", Blocks.field_150415_aT);
    if (Back2Future.enablePrismarine) {
      OreDictionary.registerOre("shardPrismarine", new ItemStack(ModItems.prismarine_shard));
      OreDictionary.registerOre("crystalPrismarine", new ItemStack(ModItems.prismarine_crystals));
      OreDictionary.registerOre("blockPrismarine", new ItemStack(ModBlocks.prismarine, 1, 32767));
    } 
    if (Back2Future.enableStones) {
      OreDictionary.registerOre("stoneGranite", new ItemStack(ModBlocks.stone, 1, 1));
      OreDictionary.registerOre("stoneDiorite", new ItemStack(ModBlocks.stone, 1, 3));
      OreDictionary.registerOre("stoneAndesite", new ItemStack(ModBlocks.stone, 1, 5));
      OreDictionary.registerOre("stoneGranitePolished", new ItemStack(ModBlocks.stone, 1, 2));
      OreDictionary.registerOre("stoneDioritePolished", new ItemStack(ModBlocks.stone, 1, 4));
      OreDictionary.registerOre("stoneAndesitePolished", new ItemStack(ModBlocks.stone, 1, 6));
    } 
    if (Back2Future.enableSlimeBlock)
      OreDictionary.registerOre("blockSlime", new ItemStack(ModBlocks.slime)); 
    if (Back2Future.enableIronTrapdoor)
      OreDictionary.registerOre("trapdoorIron", ModBlocks.iron_trapdoor); 
    if (Back2Future.enableBeetroot)
      OreDictionary.registerOre("cropBeetroot", ModItems.beetroot); 
    if (Back2Future.enableChorusFruit)
      OreDictionary.registerOre("brickEndStone", ModBlocks.end_bricks); 
  }
  
  private static void registerRecipes() {
    if (Back2Future.enableStoneBrickRecipes) {
      addShapelessRecipe(new ItemStack(Blocks.field_150341_Y), new Object[] { new ItemStack(Blocks.field_150347_e), new ItemStack(Blocks.field_150395_bd) });
      addShapelessRecipe(new ItemStack(Blocks.field_150417_aV, 1, 1), new Object[] { new ItemStack(Blocks.field_150417_aV), new ItemStack(Blocks.field_150395_bd) });
      addShapedRecipe(new ItemStack(Blocks.field_150417_aV, 1, 3), new Object[] { "x", "x", Character.valueOf('x'), new ItemStack((Block)Blocks.field_150333_U, 1, 5) });
      GameRegistry.addSmelting(new ItemStack(Blocks.field_150417_aV), new ItemStack(Blocks.field_150417_aV, 1, 2), 0.0F);
    } 
    if (Back2Future.enableSlimeBlock) {
      addShapedRecipe(new ItemStack(ModBlocks.slime), new Object[] { "xxx", "xxx", "xxx", Character.valueOf('x'), "slimeball" });
      addShapelessRecipe(new ItemStack(Items.field_151123_aH, 9), new Object[] { ModBlocks.slime });
    } 
    if (Back2Future.enableCoarseDirt)
      addShapedRecipe(new ItemStack(ModBlocks.coarse_dirt, 4), new Object[] { "xy", "yx", Character.valueOf('x'), new ItemStack(Blocks.field_150346_d), 
            Character.valueOf('y'), new ItemStack(Blocks.field_150351_n) }); 
    if (Back2Future.enableMutton)
      GameRegistry.addSmelting(ModItems.raw_mutton, new ItemStack(ModItems.cooked_mutton), 0.35F); 
    if (Back2Future.enableIronTrapdoor)
      addShapedRecipe(new ItemStack(ModBlocks.iron_trapdoor), new Object[] { "xx", "xx", Character.valueOf('x'), "ingotIron" }); 
    if (Back2Future.enableStones) {
      addShapedRecipe(new ItemStack(ModBlocks.stone, 2, 3), new Object[] { "xy", "yx", Character.valueOf('x'), new ItemStack(Blocks.field_150347_e), 
            Character.valueOf('y'), "gemQuartz" });
      addShapedRecipe(new ItemStack(ModBlocks.stone, 4, 4), new Object[] { "xx", "xx", Character.valueOf('x'), "stoneDiorite" });
      addShapelessRecipe(new ItemStack(ModBlocks.stone, 2, 5), new Object[] { new ItemStack(Blocks.field_150347_e), "stoneDiorite" });
      addShapedRecipe(new ItemStack(ModBlocks.stone, 4, 6), new Object[] { "xx", "xx", Character.valueOf('x'), "stoneAndesite" });
      addShapelessRecipe(new ItemStack(ModBlocks.stone, 2, 1), new Object[] { "gemQuartz", "stoneDiorite" });
      addShapedRecipe(new ItemStack(ModBlocks.stone, 4, 2), new Object[] { "xx", "xx", Character.valueOf('x'), "stoneGranite" });
    } 
    for (EnumDyeColor dye : EnumDyeColor.values()) {
      GameRegistry.addShapelessRecipe(new ItemStack((Block)ConcreteRegistry.getPowderFromDye(dye), 8), new Object[] { _SAND_, _SAND_, _SAND_, _SAND_, _GRAVEL_, _GRAVEL_, _GRAVEL_, _GRAVEL_, new ItemStack(Items.field_151100_aR, 1, dye
              
              .getDyeDamage()) });
    } 
    if (Back2Future.enablePrismarine) {
      int PLAIN = 0;
      int BRICKS = 1;
      int DARK = 2;
      addShapedRecipe(new ItemStack(ModBlocks.prismarine, 1, DARK), new Object[] { "xxx", "xyx", "xxx", Character.valueOf('x'), "shardPrismarine", 
            Character.valueOf('y'), "dyeBlack" });
      addShapedRecipe(new ItemStack(ModBlocks.prismarine, 1, PLAIN), new Object[] { "xx", "xx", Character.valueOf('x'), "shardPrismarine" });
      addShapedRecipe(new ItemStack(ModBlocks.prismarine, 1, BRICKS), new Object[] { "xxx", "xxx", "xxx", Character.valueOf('x'), "shardPrismarine" });
      addShapedRecipe(new ItemStack(ModBlocks.sea_lantern), new Object[] { "xyx", "yyy", "xyx", Character.valueOf('x'), "shardPrismarine", 
            Character.valueOf('y'), "crystalPrismarine" });
      if (Back2Future.enableRecipeForPrismarine && !Loader.isModLoaded("Botania")) {
        addShapedRecipe(new ItemStack(ModItems.prismarine_shard, 4), new Object[] { "xy", "zx", Character.valueOf('x'), "gemQuartz", 
              Character.valueOf('y'), "dyeBlue", Character.valueOf('z'), "dyeGreen" });
        addShapedRecipe(new ItemStack(ModItems.prismarine_crystals, 4), new Object[] { "xy", "yx", Character.valueOf('x'), "gemQuartz", 
              Character.valueOf('y'), "dustGlowstone" });
      } 
    } 
    if (Back2Future.enableDoors) {
      int j;
      for (j = 0; j < ModBlocks.doors.length; j++) {
        addShapedRecipe(new ItemStack(ModBlocks.doors[j], 3), new Object[] { "xx", "xx", "xx", Character.valueOf('x'), new ItemStack(Blocks.field_150344_f, 1, j + 1) });
      } 
      addShapedRecipe(new ItemStack(Items.field_151135_aq, 3), new Object[] { "xx", "xx", "xx", Character.valueOf('x'), "plankWood" });
      addShapedRecipe(new ItemStack(Items.field_151139_aw, 3), new Object[] { "xx", "xx", "xx", Character.valueOf('x'), "ingotIron" });
      for (j = 0; j < ModBlocks.trapdoors.length; j++) {
        addShapedRecipe(new ItemStack(ModBlocks.trapdoors[j], 2), new Object[] { "xxx", "xxx", Character.valueOf('x'), new ItemStack(Blocks.field_150344_f, 1, j + 1) });
      } 
      removeFirstRecipeFor(Blocks.field_150415_aT);
      addShapedRecipe(new ItemStack(Blocks.field_150415_aT, 2), new Object[] { "xxx", "xxx", Character.valueOf('x'), new ItemStack(Blocks.field_150344_f, 1, 0) });
    } 
    if (Back2Future.enableRedSandstone) {
      addShapedRecipe(new ItemStack(ModBlocks.red_sandstone), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack((Block)Blocks.field_150354_m, 1, 1) });
      addShapedRecipe(new ItemStack(ModBlocks.red_sandstone, 1, 1), new Object[] { "x", "x", Character.valueOf('x'), new ItemStack(ModBlocks.red_sandstone_slab) });
      addShapedRecipe(new ItemStack(ModBlocks.red_sandstone, 4, 2), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(ModBlocks.red_sandstone) });
      addShapedRecipe(new ItemStack(ModBlocks.red_sandstone_slab, 6), new Object[] { "xxx", Character.valueOf('x'), ModBlocks.red_sandstone });
      addShapedRecipe(new ItemStack(ModBlocks.red_sandstone_stairs, 4), new Object[] { "x  ", "xx ", "xxx", Character.valueOf('x'), ModBlocks.red_sandstone });
    } 
    if (Back2Future.enableFences) {
      int j;
      for (j = 0; j < ModBlocks.fences.length; j++) {
        addShapedRecipe(new ItemStack(ModBlocks.fences[j], 3), new Object[] { "xyx", "xyx", Character.valueOf('x'), new ItemStack(Blocks.field_150344_f, 1, j), 
              Character.valueOf('y'), "stickWood" });
      } 
      addShapedRecipe(new ItemStack(ModBlocks.fences[0], 3), new Object[] { "xyx", "xyx", Character.valueOf('x'), "plankWood", Character.valueOf('y'), "stickWood" });
      addShapelessRecipe(new ItemStack(Blocks.field_150422_aJ), new Object[] { ModBlocks.fences[0] });
      addShapelessRecipe(new ItemStack(ModBlocks.fences[0]), new Object[] { Blocks.field_150422_aJ });
      for (j = 0; j < ModBlocks.gates.length; j++) {
        addShapedRecipe(new ItemStack(ModBlocks.gates[j]), new Object[] { "yxy", "yxy", Character.valueOf('x'), new ItemStack(Blocks.field_150344_f, 1, j + 1), 
              Character.valueOf('y'), "stickWood" });
      } 
      addShapedRecipe(new ItemStack(Blocks.field_150396_be), new Object[] { "yxy", "yxy", Character.valueOf('x'), "plankWood", Character.valueOf('y'), "stickWood" });
    } 
    int i;
    for (i = 0; i < ModBlocks.buttons.length; i++) {
      addShapedRecipe(new ItemStack(ModBlocks.buttons[i], 1), new Object[] { "x", Character.valueOf('x'), new ItemStack(Blocks.field_150344_f, 1, i + 1) });
    } 
    addShapedRecipe(new ItemStack(Blocks.field_150471_bO, 1), new Object[] { "x", Character.valueOf('x'), "plankWood" });
    for (i = 0; i < ModBlocks.pressurePlates.length; i++) {
      addShapedRecipe(new ItemStack(ModBlocks.pressurePlates[i], 1), new Object[] { "xx", Character.valueOf('x'), new ItemStack(Blocks.field_150344_f, 1, i + 1) });
    } 
    addShapedRecipe(new ItemStack(Blocks.field_150452_aw, 1), new Object[] { "xx", Character.valueOf('x'), "plankWood" });
    if (Back2Future.enableBanners) {
      for (EnumColour colour : EnumColour.values()) {
        addShapedRecipe(new ItemStack(ModBlocks.banner, 1, colour.getDamage()), new Object[] { "xxx", "xxx", " y ", 
              Character.valueOf('x'), new ItemStack(Blocks.field_150325_L, 1, colour
                .getDamage()), Character.valueOf('y'), "stickWood" });
      } 
      GameRegistry.addRecipe(new RecipeDuplicatePattern());
      GameRegistry.addRecipe(new RecipeAddPattern());
    } 
    if (Back2Future.enableArmourStand)
      addShapedRecipe(new ItemStack(ModItems.armour_stand), new Object[] { "xxx", " x ", "xyx", Character.valueOf('x'), "stickWood", 
            Character.valueOf('y'), new ItemStack((Block)Blocks.field_150333_U) }); 
    if (Back2Future.enableRabbit) {
      addShapedRecipe(new ItemStack(ModItems.rabbit_stew), new Object[] { 
            " R ", "CPM", " B ", Character.valueOf('R'), new ItemStack(ModItems.cooked_rabbit), 
            Character.valueOf('C'), Items.field_151172_bF, Character.valueOf('P'), Items.field_151168_bH, Character.valueOf('M'), 
            Blocks.field_150338_P, 
            Character.valueOf('B'), Items.field_151054_z });
      addShapedRecipe(new ItemStack(ModItems.rabbit_stew), new Object[] { 
            " R ", "CPD", " B ", Character.valueOf('R'), new ItemStack(ModItems.cooked_rabbit), 
            Character.valueOf('C'), Items.field_151172_bF, Character.valueOf('P'), Items.field_151168_bH, Character.valueOf('D'), 
            Blocks.field_150337_Q, 
            Character.valueOf('B'), Items.field_151054_z });
      GameRegistry.addSmelting(ModItems.raw_rabbit, new ItemStack(ModItems.cooked_rabbit), 0.35F);
      addShapedRecipe(new ItemStack(Items.field_151116_aA), new Object[] { "xx", "xx", Character.valueOf('x'), ModItems.rabbit_hide });
    } 
    if (Back2Future.enableOldGravel)
      addShapedRecipe(new ItemStack(ModBlocks.old_gravel, 4), new Object[] { "xy", "yx", Character.valueOf('x'), ModBlocks.coarse_dirt, 
            Character.valueOf('y'), Blocks.field_150351_n }); 
    if (Back2Future.enableSponge) {
      addShapelessRecipe(new ItemStack(ModBlocks.sponge), new Object[] { Blocks.field_150360_v });
      addShapelessRecipe(new ItemStack(Blocks.field_150360_v), new Object[] { ModBlocks.sponge });
      GameRegistry.addSmelting(new ItemStack(ModBlocks.sponge, 1, 1), new ItemStack(ModBlocks.sponge), 0.0F);
    } 
    if (Back2Future.enableBeetroot) {
      addShapedRecipe(new ItemStack(ModItems.beetroot_soup), new Object[] { "xxx", "xxx", " y ", Character.valueOf('x'), "cropBeetroot", 
            Character.valueOf('y'), Items.field_151054_z });
      addShapelessRecipe(new ItemStack(Items.field_151100_aR, 1, 1), new Object[] { "cropBeetroot" });
    } 
    if (Back2Future.enableChorusFruit) {
      addShapedRecipe(new ItemStack(ModBlocks.purpur_block, 4), new Object[] { "xx", "xx", Character.valueOf('x'), ModItems.popped_chorus_fruit });
      addShapedRecipe(new ItemStack(ModBlocks.purpur_stairs, 4), new Object[] { "x  ", "xx ", "xxx", Character.valueOf('x'), ModBlocks.purpur_block });
      addShapedRecipe(new ItemStack(ModBlocks.purpur_slab, 6), new Object[] { "xxx", Character.valueOf('x'), ModBlocks.purpur_block });
      addShapedRecipe(new ItemStack(ModBlocks.purpur_pillar), new Object[] { "x", "x", Character.valueOf('x'), ModBlocks.purpur_slab });
      addShapedRecipe(new ItemStack(ModBlocks.end_bricks), new Object[] { "xx", "xx", Character.valueOf('x'), Blocks.field_150377_bs });
      GameRegistry.addSmelting(new ItemStack(ModItems.chorus_fruit), new ItemStack(ModItems.popped_chorus_fruit), 0.0F);
      addShapedRecipe(new ItemStack(ModBlocks.end_rod), new Object[] { "x", "y", Character.valueOf('x'), Items.field_151072_bj, Character.valueOf('y'), ModItems.popped_chorus_fruit });
    } 
    if (Back2Future.enableCryingObsidian)
      addShapelessRecipe(new ItemStack(ModBlocks.crying_obsidian), new Object[] { Blocks.field_150343_Z, "gemLapis" }); 
    if (Back2Future.enableLingeringPotions)
      addShapelessRecipe(new ItemStack(ModItems.dragon_breath), new Object[] { new ItemStack((Item)Items.field_151068_bn, 1, 8195), ModItems.chorus_fruit, ModItems.chorus_fruit }); 
    if (Back2Future.enableRoses) {
      addShapelessRecipe(new ItemStack(Items.field_151100_aR, 1, 1), new Object[] { ModBlocks.rose });
      addShapedRecipe(new ItemStack((Block)Blocks.field_150398_cm, 1, 4), new Object[] { "xx", "xx", "xx", Character.valueOf('x'), new ItemStack(ModBlocks.rose) });
      addShapedRecipe(new ItemStack(ModBlocks.rose, 12), new Object[] { "xx", Character.valueOf('x'), new ItemStack((Block)Blocks.field_150398_cm, 1, 4) });
    } 
    if (Back2Future.enableTippedArrows && Back2Future.enableLingeringPotions) {
      RecipeSorter.register("palamod.RecipeTippedArrow", RecipeTippedArrow.class, RecipeSorter.Category.SHAPED, "after:minecraft:shaped");
      GameRegistry.addRecipe((IRecipe)new RecipeTippedArrow(new ItemStack(ModItems.tipped_arrow), new Object[] { "xxx", "xyx", "xxx", 
              Character.valueOf('x'), Items.field_151032_g, Character.valueOf('y'), new ItemStack(ModItems.lingering_potion, 1, 32767) }));
    } 
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 0), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log_stripped, 1, 0) });
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 1), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log_stripped, 1, 1) });
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 2), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log_stripped, 1, 2) });
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 3), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log_stripped, 1, 3) });
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 4), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log2_stripped, 1, 0) });
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 5), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log2_stripped, 1, 1) });
    addShapedRecipe(new ItemStack(ModBlocks.log_bark, 3, 0), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(Blocks.field_150364_r, 1, 0) });
    addShapedRecipe(new ItemStack(ModBlocks.log_bark, 3, 1), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(Blocks.field_150364_r, 1, 1) });
    addShapedRecipe(new ItemStack(ModBlocks.log_bark, 3, 2), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(Blocks.field_150364_r, 1, 2) });
    addShapedRecipe(new ItemStack(ModBlocks.log_bark, 3, 3), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(Blocks.field_150364_r, 1, 3) });
    addShapedRecipe(new ItemStack(ModBlocks.log_bark, 3, 4), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(Blocks.field_150363_s, 1, 0) });
    addShapedRecipe(new ItemStack(ModBlocks.log_bark, 3, 5), new Object[] { "xx", "xx", Character.valueOf('x'), new ItemStack(Blocks.field_150363_s, 1, 1) });
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 0), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log_bark, 1, 0) });
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 1), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log_bark, 1, 1) });
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 2), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log_bark, 1, 2) });
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 3), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log_bark, 1, 3) });
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 4), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log_bark, 1, 4) });
    addShapedRecipe(new ItemStack(Blocks.field_150344_f, 4, 5), new Object[] { "x", Character.valueOf('x'), new ItemStack(ModBlocks.log_bark, 1, 5) });
  }
  
  private static void addShapedRecipe(ItemStack output, Object... objects) {
    GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(output, objects));
  }
  
  private static void addShapelessRecipe(ItemStack output, Object... objects) {
    GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(output, objects));
  }
  
  private static void removeFirstRecipeFor(Block block) {
    removeFirstRecipeFor(Item.func_150898_a(block));
  }
  
  private static void removeFirstRecipeFor(Item item) {
    for (Object recipe : CraftingManager.func_77594_a().func_77592_b()) {
      if (recipe != null) {
        ItemStack stack = ((IRecipe)recipe).func_77571_b();
        if (stack != null && stack.func_77973_b() == item) {
          CraftingManager.func_77594_a().func_77592_b().remove(recipe);
          return;
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\recipes\ModRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */