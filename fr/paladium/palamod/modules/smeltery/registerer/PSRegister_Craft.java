package fr.paladium.palamod.modules.smeltery.registerer;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.smeltery.crafting.GrinderRecipe;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PSRegister_Craft {
  public static void init() {
    paladiumGrinderRecipies();
    craftingRecipies();
  }
  
  private static void paladiumGrinderRecipies() {
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.PATERN_BROADSWORD), new ItemStack((Item)PSRegister_Items.PATERN_SOCKET), new ItemStack((Item)PSRegister_Items.HEAD_BROADSWORD), 4);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.PATERN_PICKAXE), new ItemStack((Item)PSRegister_Items.PATERN_SOCKET), new ItemStack((Item)PSRegister_Items.HEAD_PICKAXE), 3);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.PATERN_FASTSWORD), new ItemStack((Item)PSRegister_Items.PATERN_SOCKET), new ItemStack((Item)PSRegister_Items.HEAD_FASTSWORD), 1);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.PATERN_SWORD), new ItemStack((Item)PSRegister_Items.PATERN_SOCKET), new ItemStack((Item)PSRegister_Items.HEAD_SWORD), 2);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.PATERN_HAMMER), new ItemStack((Item)PSRegister_Items.PATERN_SOCKET), new ItemStack((Item)PSRegister_Items.HEAD_HAMMER), 6);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.PATERN_SHOVEL), new ItemStack((Item)PSRegister_Items.PATERN_SOCKET), new ItemStack((Item)PSRegister_Items.HEAD_SHOVEL), 1);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.PATERN_AXE), new ItemStack((Item)PSRegister_Items.PATERN_SOCKET), new ItemStack((Item)PSRegister_Items.HEAD_AXE), 3);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.PATERN_INGOT), new ItemStack((Item)PSRegister_Items.PATERN_SOCKET), new ItemStack(ItemsRegister.PALADIUM_INGOT), 1);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.PATERN_BLOCK), new ItemStack((Item)PSRegister_Items.PATERN_SOCKET), new ItemStack(BlocksRegister.BLOCK_PALADIUM), 9);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.HEAD_BROADSWORD), new ItemStack(ItemsRegister.STICK_PALADIUM), new ItemStack((Item)PSRegister_Items.BROADSWORD_PALADIUM), 0);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.HEAD_BROADSWORD), new ItemStack(Items.field_151055_y), new ItemStack((Item)PSRegister_Items.BROADSWORD_PALADIUM), 1);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.HEAD_PICKAXE), new ItemStack(Items.field_151055_y), new ItemStack(ItemsRegister.PALADIUM_PICKAXE), 0);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.HEAD_SWORD), new ItemStack(Items.field_151055_y), new ItemStack(ItemsRegister.PALADIUM_SWORD), 0);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.HEAD_FASTSWORD), new ItemStack(ItemsRegister.STICK_PALADIUM), new ItemStack((Item)PSRegister_Items.FASTSWORD_PALADIUM), 0);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.HEAD_FASTSWORD), new ItemStack(Items.field_151055_y), new ItemStack((Item)PSRegister_Items.FASTSWORD_PALADIUM), 1);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.HEAD_HAMMER), new ItemStack(Items.field_151055_y), new ItemStack((Item)PSRegister_Items.HAMMER_PALADIUM), 1);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.HEAD_HAMMER), new ItemStack(ItemsRegister.STICK_PALADIUM), new ItemStack((Item)PSRegister_Items.HAMMER_PALADIUM), 0);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.HEAD_AXE), new ItemStack(Items.field_151055_y), new ItemStack(ItemsRegister.PALADIUM_AXE), 0);
    GrinderRecipe.getManager().add(new ItemStack((Item)PSRegister_Items.HEAD_SHOVEL), new ItemStack(Items.field_151055_y), new ItemStack(ItemsRegister.PALADIUM_SHOVEL), 0);
    GrinderRecipe.getManager().addUpgrade((Item)PSRegister_Items.HAMMER_PALADIUM, (Item)PSRegister_Items.MODIFIER_SMELT, 8);
    GrinderRecipe.getManager().addUpgrade((Item)PSRegister_Items.HAMMER_PALADIUM, (Item)PSRegister_Items.MODIFIER_SPEED, 2);
    GrinderRecipe.getManager().addUpgrade((Item)PSRegister_Items.HAMMER_PALADIUM, (Item)PSRegister_Items.MODIFIER_FORTUNE, 1);
    GrinderRecipe.getManager().addUpgrade(ItemsRegister.GOD_PICKAXE, ItemsRegister.GOD_PICKAXE_AUTOSMELT, 15);
    GrinderRecipe.getManager().addUpgrade((Item)PSRegister_Items.HAMMER_PALADIUM, (Item)PSRegister_Items.MODIFIER_MOREUPGRADE, 7);
    GrinderRecipe.getManager().addUpgrade((Item)PSRegister_Items.BROADSWORD_PALADIUM, (Item)PSRegister_Items.MODIFIER_DAMAGE, 3);
    GrinderRecipe.getManager().addUpgrade((Item)PSRegister_Items.BROADSWORD_PALADIUM, (Item)PSRegister_Items.MODIFIER_FLAME, 4);
    GrinderRecipe.getManager().addUpgrade((Item)PSRegister_Items.BROADSWORD_PALADIUM, (Item)PSRegister_Items.MODIFIER_KNOCKBACK, 5);
    GrinderRecipe.getManager().addUpgrade((Item)PSRegister_Items.BROADSWORD_PALADIUM, (Item)PSRegister_Items.MODIFIER_MOREUPGRADE, 7);
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.MODIFIER_SPEED, 1), new Object[] { " X ", "XZX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(ItemsRegister.ORB_SPEED) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.MODIFIER_SMELT, 1), new Object[] { " X ", "XZX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151072_bj) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.MODIFIER_MOREUPGRADE, 1), new Object[] { " X ", "XZX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.COMPRESSED_PALADIUM), Character.valueOf('Z'), new ItemStack((Item)PSRegister_Items.HAMMER_PALADIUM) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.MODIFIER_KNOCKBACK, 1), new Object[] { " X ", "XZX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(ItemsRegister.ORB_KNOCKBACK) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.MODIFIER_FORTUNE, 1), new Object[] { " X ", "XZX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(Blocks.field_150340_R) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.MODIFIER_FLAME, 1), new Object[] { " X ", "XZX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151033_d) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.MODIFIER_DAMAGE, 1), new Object[] { " X ", "XZX", " X ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(Items.field_151009_A) });
  }
  
  public static void craftingRecipies() {
    GameRegistry.addRecipe(new ItemStack((Block)PSRegister_Blocks.GRINDER_BLOCK), new Object[] { "YYY", "YXY", "YYY", 
          Character.valueOf('Y'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('X'), new ItemStack((Block)BlocksRegister.PALADIUM_FURNACE) });
    GameRegistry.addRecipe(new ItemStack((Block)PSRegister_Blocks.GRINDER_CASING_BLOCK), new Object[] { "Y Y", "Y Y", "Y Y", 
          Character.valueOf('Y'), ItemsRegister.PALADIUM_INGOT });
    GameRegistry.addRecipe(new ItemStack((Block)PSRegister_Blocks.GRINDER_FRAME_BLOCK), new Object[] { "YZY", "   ", "YZY", Character.valueOf('Y'), ItemsRegister.PALADIUM_INGOT, Character.valueOf('Z'), ItemsRegister.TITANE_INGOT });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.PATERN_PICKAXE, 1), new Object[] { "YXY", "XZX", "YXY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(ItemsRegister.TITANE_PICKAXE), Character.valueOf('Y'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.PATERN_SWORD, 1), new Object[] { "YXY", "XZX", "YXY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(ItemsRegister.TITANE_SWORD), Character.valueOf('Y'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.PATERN_FASTSWORD, 1), new Object[] { "YXY", "XZX", "YXY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack((Item)PSRegister_Items.FASTSWORD_TITANE), Character.valueOf('Y'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.PATERN_BROADSWORD, 1), new Object[] { "YXY", "XZX", "YXY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack((Item)PSRegister_Items.BROADSWORD_TITANE), Character.valueOf('Y'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.PATERN_HAMMER, 1), new Object[] { "YXY", "XZX", "YXY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack((Item)PSRegister_Items.HAMMER_TITANE), Character.valueOf('Y'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.PATERN_INGOT, 1), new Object[] { "YXY", "XZX", "YXY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(ItemsRegister.TITANE_INGOT), Character.valueOf('Y'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.PATERN_BLOCK, 1), new Object[] { "XXX", "YXY", "XXX", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT), Character.valueOf('Y'), new ItemStack(BlocksRegister.BLOCK_PALADIUM) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.PATERN_SHOVEL, 1), new Object[] { "YXY", "XZX", "YXY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(ItemsRegister.TITANE_SHOVEL), Character.valueOf('Y'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.PATERN_AXE, 1), new Object[] { "YXY", "XZX", "YXY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Z'), new ItemStack(ItemsRegister.TITANE_AXE), Character.valueOf('Y'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.PATERN_SOCKET, 1), new Object[] { "YXY", "X X", "YXY", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.PALADIUM_INGOT), Character.valueOf('Y'), new ItemStack(ItemsRegister.TITANE_INGOT) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.HAMMER_AMETHYST, 1), new Object[] { "XXX", "XXX", " Y ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.HAMMER_TITANE, 1), new Object[] { "XXX", "XXX", " Y ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.BROADSWORD_AMETHYST, 1), new Object[] { "XX", "XX", "Y ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.AMETHYST_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.FASTSWORD_AMETHYST, 1), new Object[] { "X", "X", "Y", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.STICK_AMETHYST), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.FASTSWORD_TITANE, 1), new Object[] { "X", "X", "Y", Character.valueOf('X'), new ItemStack(ItemsRegister.STICK_TITANE), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.BROADSWORD_TITANE, 1), new Object[] { "XX", "XX", "Y ", 
          Character.valueOf('X'), new ItemStack(ItemsRegister.TITANE_INGOT), Character.valueOf('Y'), new ItemStack(Items.field_151055_y) });
    GameRegistry.addRecipe(new ItemStack((Block)BlocksRegister.COBBLEBREAKER), new Object[] { 
          "CPC", "CVC", "CFC", 
          Character.valueOf('C'), Blocks.field_150347_e, Character.valueOf('D'), ItemsRegister.PALADIUM_PICKAXE, Character.valueOf('V'), ItemsRegister.VOIDSTONE, Character.valueOf('F'), 
          PSRegister_Items.MODIFIER_FORTUNE });
    GameRegistry.addRecipe(new ItemStack((Item)PSRegister_Items.HAMMER_SMITH, 1), new Object[] { "XXX", "XXX", " Y ", 
          Character.valueOf('X'), Items.field_151042_j, Character.valueOf('Y'), Items.field_151055_y });
    GameRegistry.addRecipe(new ItemStack(BlocksRegister.FLOWER_FARM), new Object[] { 
          "OGO", "POD", "OBO", 
          Character.valueOf('O'), Blocks.field_150364_r, Character.valueOf('G'), new ItemStack((Block)Blocks.field_150329_H, 1, 1), Character.valueOf('P'), Blocks.field_150328_O, Character.valueOf('D'), 
          Blocks.field_150327_N, Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 15) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\registerer\PSRegister_Craft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */