package fr.paladium.palamod.modules.hunter.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.hunter.blocks.BlockBamboo;
import fr.paladium.palamod.modules.hunter.blocks.BlockBambooBlock;
import fr.paladium.palamod.modules.hunter.blocks.BlockElevator;
import fr.paladium.palamod.modules.hunter.blocks.BlockEndiumTotem;
import fr.paladium.palamod.modules.hunter.blocks.BlockPerlinpinpinPower;
import fr.paladium.palamod.modules.hunter.blocks.BlockSacrificeHotel;
import net.minecraft.block.Block;

public class Blocks {
  public static void register() {
    BlocksRegister.ELEVATOR_BLOCK = (Block)new BlockElevator();
    BlocksRegister.PERLINPINPIN_POWER = (Block)new BlockPerlinpinpinPower();
    BlocksRegister.SACRIFICE_HOTEL = (Block)new BlockSacrificeHotel();
    BlocksRegister.ENDIUM_TOTEM = (Block)new BlockEndiumTotem();
    BlocksRegister.BAMBOO = (Block)new BlockBamboo();
    BlocksRegister.BAMBOO_BLOCK = (Block)new BlockBambooBlock();
    GameRegistry.registerBlock(BlocksRegister.ELEVATOR_BLOCK, BlocksRegister.ELEVATOR_BLOCK
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.PERLINPINPIN_POWER, BlocksRegister.PERLINPINPIN_POWER
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.SACRIFICE_HOTEL, BlocksRegister.SACRIFICE_HOTEL
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.ENDIUM_TOTEM, BlocksRegister.ENDIUM_TOTEM
        .func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BAMBOO, BlocksRegister.BAMBOO.func_149739_a());
    GameRegistry.registerBlock(BlocksRegister.BAMBOO_BLOCK, BlocksRegister.BAMBOO_BLOCK
        .func_149739_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\init\Blocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */