package fr.paladium.palamod.modules.factions;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.factions.client.registers.ItemsRegister;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Crafts {
  public static void register() {
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.COMPASS_EXPLORATION, 1, 0), new Object[] { 
          "ABA", "CDC", "AEA", 
          
          Character.valueOf('A'), ItemsRegister.FINDIUM, 
          Character.valueOf('B'), ItemsRegister.UNCLAIMFINDER_ROUGE, 
          Character.valueOf('C'), ItemsRegister.AMETHYST_INGOT, 
          Character.valueOf('D'), 
          Items.field_151111_aL, 
          Character.valueOf('E'), Items.field_151148_bJ });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.COMPASS_BASE_CLAIM, 1, 0), new Object[] { 
          "ABA", "CDC", "AEA", 
          
          Character.valueOf('A'), ItemsRegister.FINDIUM, 
          Character.valueOf('B'), ItemsRegister.UNCLAIMFINDER_ROUGE, 
          Character.valueOf('C'), ItemsRegister.TITANE_INGOT, 
          Character.valueOf('D'), 
          Items.field_151111_aL, 
          Character.valueOf('E'), Blocks.field_150343_Z });
    GameRegistry.addRecipe(new ItemStack(ItemsRegister.COMPASS_HUNTING_TREASURE, 1, 0), new Object[] { 
          "ABA", "CDC", "AEA", 
          
          Character.valueOf('A'), ItemsRegister.FINDIUM, 
          Character.valueOf('B'), ItemsRegister.UNCLAIMFINDER_ROUGE, 
          Character.valueOf('C'), ItemsRegister.PALADIUM_INGOT, 
          Character.valueOf('D'), 
          Items.field_151111_aL, 
          Character.valueOf('E'), Blocks.field_150486_ae });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\factions\Crafts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */