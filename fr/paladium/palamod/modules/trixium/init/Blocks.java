package fr.paladium.palamod.modules.trixium.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.trixium.blocks.BlockCloud;
import fr.paladium.palamod.modules.trixium.items.ItemCloud;
import fr.paladium.palamod.modules.trixium.tileentities.TileEntityCloud;
import net.minecraft.block.Block;

public class Blocks {
  public static void register() {
    BlocksRegister.BLOCK_CLOUD = (Block)new BlockCloud();
    GameRegistry.registerBlock(BlocksRegister.BLOCK_CLOUD, ItemCloud.class, BlocksRegister.BLOCK_CLOUD.func_149739_a());
    GameRegistry.registerTileEntity(TileEntityCloud.class, "tileEntityCloud");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\init\Blocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */