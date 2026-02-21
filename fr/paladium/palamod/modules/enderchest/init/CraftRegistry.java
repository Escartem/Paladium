package fr.paladium.palamod.modules.enderchest.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CraftRegistry {
  public static void register() {
    GameRegistry.addShapelessRecipe(new ItemStack((Block)BlocksRegister.PALADIUM_ENDER_CHEST), new Object[] { new ItemStack(Blocks.field_150477_bB) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\init\CraftRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */