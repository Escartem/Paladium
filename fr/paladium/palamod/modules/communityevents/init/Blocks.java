package fr.paladium.palamod.modules.communityevents.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.communityevents.blocks.BlockBarrelWood;
import fr.paladium.palamod.modules.communityevents.blocks.BlockFeveArty;
import fr.paladium.palamod.modules.communityevents.blocks.BlockFeveDancarok;
import fr.paladium.palamod.modules.communityevents.blocks.BlockFeveRavirok;
import fr.paladium.palamod.modules.communityevents.blocks.BlockFeveTedarok;
import fr.paladium.palamod.modules.communityevents.blocks.BlockGalette;
import fr.paladium.palamod.modules.communityevents.blocks.BlockRainbowSkull;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityBarrelWood;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityFeveArty;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityFeveDancarok;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityFeveRavirok;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityFeveTedarok;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityRainbowSkull;
import net.minecraft.block.Block;

public class Blocks {
  public static void register() {
    BlocksRegister.RAINBOW_SKULL = (Block)new BlockRainbowSkull();
    GameRegistry.registerBlock(BlocksRegister.RAINBOW_SKULL, BlocksRegister.RAINBOW_SKULL.func_149739_a());
    GameRegistry.registerTileEntity(TileEntityRainbowSkull.class, "palamod:tileEntityRainbowSkull");
    BlocksRegister.BARREL_WOOD = (Block)new BlockBarrelWood();
    GameRegistry.registerBlock(BlocksRegister.BARREL_WOOD, BlocksRegister.BARREL_WOOD.func_149739_a());
    GameRegistry.registerTileEntity(TileEntityBarrelWood.class, "palamod:tileEntityBarrelWood");
    BlocksRegister.GALETTE_CAKE = (Block)new BlockGalette();
    GameRegistry.registerBlock(BlocksRegister.GALETTE_CAKE, BlocksRegister.GALETTE_CAKE.func_149739_a());
    BlocksRegister.FEVE_ARTY = (Block)new BlockFeveArty();
    GameRegistry.registerBlock(BlocksRegister.FEVE_ARTY, BlocksRegister.FEVE_ARTY.func_149739_a());
    GameRegistry.registerTileEntity(TileEntityFeveArty.class, "palamod:tileEntityFeveArty");
    BlocksRegister.FEVE_DANCAROK = (Block)new BlockFeveDancarok();
    GameRegistry.registerBlock(BlocksRegister.FEVE_DANCAROK, BlocksRegister.FEVE_DANCAROK.func_149739_a());
    GameRegistry.registerTileEntity(TileEntityFeveDancarok.class, "palamod:tileEntityFeveDancarok");
    BlocksRegister.FEVE_TEDAROK = (Block)new BlockFeveTedarok();
    GameRegistry.registerBlock(BlocksRegister.FEVE_TEDAROK, BlocksRegister.FEVE_TEDAROK.func_149739_a());
    GameRegistry.registerTileEntity(TileEntityFeveTedarok.class, "palamod:tileEntityFeveTedarok");
    BlocksRegister.FEVE_RAVIROK = (Block)new BlockFeveRavirok();
    GameRegistry.registerBlock(BlocksRegister.FEVE_RAVIROK, BlocksRegister.FEVE_RAVIROK.func_149739_a());
    GameRegistry.registerTileEntity(TileEntityFeveRavirok.class, "palamod:tileEntityFeveRavirok");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\init\Blocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */