package fr.paladium.palamod.modules.enderchest.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.enderchest.block.BlockPaladiumEnderChest;
import fr.paladium.palamod.modules.enderchest.tileentity.TileEntityPaladiumEnderChest;
import net.minecraft.block.Block;

public class BlockRegistry {
  public static void register() {
    BlocksRegister.PALADIUM_ENDER_CHEST = new BlockPaladiumEnderChest("paladium_ender_chest");
    GameRegistry.registerBlock((Block)BlocksRegister.PALADIUM_ENDER_CHEST, BlocksRegister.PALADIUM_ENDER_CHEST.func_149739_a());
    GameRegistry.registerTileEntity(TileEntityPaladiumEnderChest.class, "palamod:tileEntityPaladiumEnderChest");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\init\BlockRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */