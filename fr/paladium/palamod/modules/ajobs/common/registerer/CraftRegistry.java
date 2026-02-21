package fr.paladium.palamod.modules.ajobs.common.registerer;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.mount.core.items.ItemsRegister;
import fr.paladium.palajobs.core.registry.ItemsRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftRegistry {
  private static CraftRegistry instance;
  
  public void register() {
    GameRegistry.addShapelessRecipe(new ItemStack(BlocksRegistry.ROOT_PLANK, 4), new Object[] { new ItemStack(BlocksRegistry.ROOT_LOG) });
    GameRegistry.addRecipe(new ItemStack(ItemsRegistry.ROOT_SEEDS_ITEM), new Object[] { 
          " S ", "DWD", "PPP", 
          
          Character.valueOf('S'), Items.field_151014_N, 
          Character.valueOf('D'), BlocksRegister.DEAD_WOOD, 
          Character.valueOf('W'), ItemsRegister.BUCKET_ANGELIC, 
          Character.valueOf('P'), 
          Blocks.field_150330_I });
    GameRegistry.addRecipe(new ItemStack(ItemsRegistry.PALADIUM_FISHING_ROD), new Object[] { "  P", " SX", "S X", 
          
          Character.valueOf('P'), ItemsRegister.PALADIUM_INGOT, 
          Character.valueOf('S'), ItemsRegister.STICK_PALADIUM, 
          Character.valueOf('X'), Items.field_151007_F });
    GameRegistry.addRecipe(new ItemStack(BlocksRegistry.MINER_THRONE), new Object[] { "EOE", "OFO", "EOE", 
          
          Character.valueOf('E'), ItemsRegister.ENDIUM_FRAGMENT, 
          Character.valueOf('O'), BlocksRegister.OBSI_ANTIAGGRO, 
          Character.valueOf('F'), BlocksRegister.BLOCK_FINDIUM });
    GameRegistry.addRecipe(new ItemStack(BlocksRegistry.FARMER_THRONE), new Object[] { "EOE", "OFO", "EOE", 
          
          Character.valueOf('E'), ItemsRegister.ENDIUM_FRAGMENT, 
          Character.valueOf('O'), ItemsRegister.SEEDPLANTER_LVL5, 
          Character.valueOf('F'), BlocksRegister.BLOCK_FINDIUM });
    GameRegistry.addRecipe(new ItemStack(BlocksRegistry.HUNTER_THRONE), new Object[] { "EOE", "OFO", "EOE", 
          
          Character.valueOf('E'), ItemsRegister.ENDIUM_FRAGMENT, 
          Character.valueOf('O'), ItemsRegister.PALADIUM_HORN, 
          Character.valueOf('F'), BlocksRegister.BLOCK_FINDIUM });
    GameRegistry.addRecipe(new ItemStack(BlocksRegistry.ALCHEMIST_THRONE), new Object[] { "EOE", "OFO", "EOE", 
          
          Character.valueOf('E'), ItemsRegister.ENDIUM_FRAGMENT, 
          Character.valueOf('O'), BlocksRegister.PALADIUM_PORTAL_BLOCK, 
          Character.valueOf('F'), BlocksRegister.BLOCK_FINDIUM });
  }
  
  public static CraftRegistry getInstance() {
    if (instance == null)
      instance = new CraftRegistry(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\registerer\CraftRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */