package fr.paladium.palamod.modules.hunter.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.smeltery.registerer.PSRegister_Items;
import fr.paladium.palaspawner.common.registry.SpawnerBlockRegistry;
import fr.paladium.palaspawner.common.registry.SpawnerItemRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Crafts {
  public static void register() {
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.HUNTER_BACKPACK, 1, 0), new Object[] { 
          "ICI", "IHI", "LIL", 
          Character.valueOf('I'), ItemsRegister.AMETHYST_INGOT, Character.valueOf('C'), BlocksRegister.AMETHYST_CHEST, Character.valueOf('H'), Blocks.field_150438_bZ, 
          Character.valueOf('L'), 
          Items.field_151116_aA });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.HUNTER_BACKPACK, 1, 1), new Object[] { 
          "ICI", "IHI", "LIL", 
          Character.valueOf('I'), ItemsRegister.TITANE_INGOT, Character.valueOf('C'), BlocksRegister.TITANE_CHEST, Character.valueOf('H'), Blocks.field_150438_bZ, Character.valueOf('L'), 
          Items.field_151116_aA });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.HUNTER_BACKPACK, 1, 2), new Object[] { 
          "ICI", "IHI", "LIL", 
          Character.valueOf('I'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('C'), BlocksRegister.PALADIUM_CHEST, Character.valueOf('H'), Blocks.field_150438_bZ, 
          Character.valueOf('L'), 
          Items.field_151116_aA });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.HUNTER_BACKPACK, 1, 3), new Object[] { 
          "ICI", "IHI", "LEL", 
          Character.valueOf('I'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('C'), BlocksRegister.PALADIUM_CHEST, Character.valueOf('H'), Blocks.field_150438_bZ, 
          Character.valueOf('L'), 
          Items.field_151116_aA, Character.valueOf('E'), ItemsRegister.ENDIUM_NUGGET });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.HUNTER_COMPASS, 1, 0), new Object[] { 
          "FUF", "ICI", "FIF", 
          Character.valueOf('F'), ItemsRegister.FINDIUM, Character.valueOf('U'), ItemsRegister.UNCLAIMFINDER, Character.valueOf('I'), ItemsRegister.AMETHYST_INGOT, 
          Character.valueOf('C'), 
          Items.field_151111_aL });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.HUNTER_COMPASS, 1, 1), new Object[] { 
          "FUF", "ICI", "FIF", 
          Character.valueOf('F'), ItemsRegister.FINDIUM, Character.valueOf('U'), ItemsRegister.UNCLAIMFINDER_ORANGE, Character.valueOf('I'), ItemsRegister.TITANE_INGOT, 
          Character.valueOf('C'), 
          Items.field_151111_aL });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.HUNTER_COMPASS, 1, 2), new Object[] { 
          "FUF", "ICI", "FIF", 
          Character.valueOf('F'), ItemsRegister.FINDIUM, Character.valueOf('U'), ItemsRegister.UNCLAIMFINDER_ROUGE, Character.valueOf('I'), ItemsRegister.PALADIUM_INGOT, 
          Character.valueOf('C'), 
          Items.field_151111_aL });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.HUNTER_COMPASS, 1, 3), new Object[] { 
          "FUF", "ICI", "FEF", 
          Character.valueOf('F'), ItemsRegister.FINDIUM, Character.valueOf('U'), ItemsRegister.UNCLAIMFINDER_ROUGE, Character.valueOf('I'), ItemsRegister.PALADIUM_INGOT, 
          Character.valueOf('E'), 
          ItemsRegister.ENDIUM_NUGGET, Character.valueOf('C'), Items.field_151111_aL });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.HUNTER_COMPASS, 1, 4), new Object[] { 
          "FUF", "ICI", "FIF", 
          Character.valueOf('F'), ItemsRegister.FINDIUM, Character.valueOf('U'), ItemsRegister.UNCLAIMFINDER_ROUGE, Character.valueOf('I'), ItemsRegister.PALADIUM_INGOT, 
          Character.valueOf('C'), 
          ItemsRegister.COMPRESSED_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.HUNTER_AMULET), new Object[] { 
          "BNB", " S ", " P ", Character.valueOf('B'), Items.field_151065_br, 
          Character.valueOf('N'), Items.field_151156_bN, Character.valueOf('S'), ItemsRegister.DIAMOND_STRING, Character.valueOf('P'), 
          ItemsRegister.STICK_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.SEEING_AMULET), new Object[] { 
          "ABA", "CDC", "EFE", Character.valueOf('A'), BlocksRegister.FLOWER_HARPAGOPHYTUM, 
          Character.valueOf('B'), ItemsRegister.ORB_SPEED, Character.valueOf('C'), ItemsRegister.ORB_HEAL, 
          Character.valueOf('D'), 
          ItemsRegister.VOIDSTONE, Character.valueOf('E'), BlocksRegister.FLOWER_CLATHRUSARCHERI, 
          Character.valueOf('F'), ItemsRegister.ORB_STRENGHT });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.MAGMA_AMULET), new Object[] { 
          "MFM", "PCP", "MPM", Character.valueOf('M'), Items.field_151064_bs, 
          Character.valueOf('F'), ItemsRegister.FINDIUM, Character.valueOf('P'), new ItemStack((Item)Items.field_151068_bn, 1, 8259), 
          Character.valueOf('C'), 
          ItemsRegister.PALADIUM_CORE });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.ELEVATOR_BLOCK), new Object[] { 
          "AAA", "BCB", "DED", Character.valueOf('A'), Blocks.field_150325_L, 
          Character.valueOf('B'), ItemsRegister.FURNACE_UPGRADE, Character.valueOf('C'), Blocks.field_150331_J, Character.valueOf('D'), 
          Blocks.field_150344_f, 
          Character.valueOf('E'), Items.field_151137_ax });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.ENDIUM_NAMETAG), new Object[] { 
          " A ", "BCB", "DBD", Character.valueOf('A'), ItemsRegister.ENDIUM_NUGGET, 
          Character.valueOf('B'), ItemsRegister.FINDIUM, Character.valueOf('C'), Items.field_151057_cb, Character.valueOf('D'), 
          ItemsRegister.PALADIUM_INGOT });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.CAPTURE_SWORD), new Object[] { 
          "ABC", "DEF", "GHI", Character.valueOf('A'), ItemsRegister.STICK_SPEED, 
          Character.valueOf('B'), PSRegister_Items.HEAD_BROADSWORD, Character.valueOf('C'), ItemsRegister.STICK_STRENGHT, 
          Character.valueOf('D'), 
          PSRegister_Items.HEAD_FASTSWORD, Character.valueOf('E'), ItemsRegister.COMPRESSED_PALADIUM, 
          Character.valueOf('F'), PSRegister_Items.HEAD_SWORD, Character.valueOf('G'), ItemsRegister.STICK_HEAL, 
          Character.valueOf('H'), ItemsRegister.STICK_PALADIUM, Character.valueOf('I'), 
          ItemsRegister.STICK_DAMAGE });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.CAPTURE_STONE), new Object[] { 
          "AXA", "BOC", "AYA", 
          
          Character.valueOf('A'), Items.field_151065_br, 
          Character.valueOf('B'), BlocksRegister.BLOCK_AMETHYST, 
          Character.valueOf('C'), BlocksRegister.BLOCK_TITANE, 
          Character.valueOf('O'), 
          Blocks.field_150343_Z, 
          Character.valueOf('X'), ItemsRegister.COMPRESSED_TITANE, 
          Character.valueOf('Y'), ItemsRegister.COMPRESSED_AMETHYST });
    try {
      GameRegistry.addRecipe(new ItemStack(SpawnerBlockRegistry.EMPTY_MOB_SPAWNER), new Object[] { 
            "ABA", "CDE", "AFA", 
            Character.valueOf('A'), ItemsRegister.FINDIUM, Character.valueOf('B'), Items.field_151144_bL, Character.valueOf('C'), ItemsRegister.COMPRESSED_PALADIUM, Character.valueOf('D'), 
            SpawnerBlockRegistry.EMPTY_BROKEN_MOB_SPAWNER, 
            Character.valueOf('E'), 
            GameRegistry.findItem("guardiangolem", "guardian_stone"), Character.valueOf('F'), BlocksRegister.CRUSHER });
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("##Hunter - empty mob spawner craft failed");
    } 
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.BAMBOO_BLOCK), new Object[] { "AAA", "AAA", "AAA", Character.valueOf('A'), BlocksRegister.BAMBOO });
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegister.BAMBOO, 9), new Object[] { new ItemStack(BlocksRegister.BAMBOO_BLOCK) });
    GameRegistry.addRecipe(new ItemStack((Item)SpawnerItemRegistry.CAVERN_HAMMER), new Object[] { "AAA", "BCB", "AAA", Character.valueOf('A'), BlocksRegister.BLOCK_TITANE, 
          Character.valueOf('B'), Blocks.field_150343_Z, Character.valueOf('C'), PSRegister_Items.HAMMER_PALADIUM });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.SNAIL_SHELL, 1, 1), new Object[] { 
          "A A", "BCD", " E ", Character.valueOf('A'), Blocks.field_150327_N, 
          Character.valueOf('B'), Blocks.field_150337_Q, Character.valueOf('C'), ItemsRegister.SNAIL_SHELL, Character.valueOf('D'), 
          Blocks.field_150338_P, 
          Character.valueOf('E'), Items.field_151054_z });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.PERLINPINPIN_POWER), new Object[] { 
          "ABC", "BDB", "EBF", 
          Character.valueOf('A'), Items.field_151114_aO, Character.valueOf('B'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('C'), Items.field_151102_aT, Character.valueOf('D'), 
          ItemsRegister.BLUE_GLUEBALL, 
          Character.valueOf('E'), Items.field_151016_H, Character.valueOf('F'), Items.field_151137_ax });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.ENDIUM_TOTEM), new Object[] { 
          "ABC", "DEF", "GHI", Character.valueOf('A'), ItemsRegister.SNAKE_VENOM, 
          Character.valueOf('B'), ItemsRegister.PARROT_FEATHER, Character.valueOf('C'), ItemsRegister.TURTLE_SCALES, 
          Character.valueOf('D'), 
          ItemsRegister.GOAT_SHOE, Character.valueOf('E'), Blocks.field_150381_bn, 
          Character.valueOf('F'), ItemsRegister.WITHER_SKULLFRAGMENT, Character.valueOf('G'), ItemsRegister.MEDUSE_HOOK, Character.valueOf('H'), ItemsRegister.SNAIL_SHELL, 
          Character.valueOf('I'), 
          ItemsRegister.PANDA_DROOL });
    try {
      GameRegistry.addRecipe(new ItemStack(BlocksRegister.SACRIFICE_HOTEL), new Object[] { 
            "ABA", "CDE", "FFF", 
            Character.valueOf('A'), ItemsRegister.PALAMIXEDCOAL, Character.valueOf('B'), Items.field_151122_aG, Character.valueOf('C'), ItemsRegister.HUNTER_AMULET, Character.valueOf('D'), 
            GameRegistry.findItem("guardiangolem", "mini_golem"), Character.valueOf('E'), ItemsRegister.SEEING_AMULET, 
            Character.valueOf('F'), Blocks.field_150343_Z });
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("##Hunter - endium totem craft failed");
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\init\Crafts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */