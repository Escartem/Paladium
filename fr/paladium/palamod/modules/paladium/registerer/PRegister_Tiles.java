package fr.paladium.palamod.modules.paladium.registerer;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.paladium.common.blocks.TileEntityTypeMachine;
import fr.paladium.palamod.modules.paladium.common.logics.AlchemyCreatorLogic;
import fr.paladium.palamod.modules.paladium.common.logics.AmethystChestLogic;
import fr.paladium.palamod.modules.paladium.common.logics.BowMachineLogic;
import fr.paladium.palamod.modules.paladium.common.logics.EndiumChestLogic;
import fr.paladium.palamod.modules.paladium.common.logics.ForgeLogic;
import fr.paladium.palamod.modules.paladium.common.logics.GPaladiumChestLogic;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumChestLogic;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumFurnaceLogic;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumMachineLogic;
import fr.paladium.palamod.modules.paladium.common.logics.TileCobbleBreaker;
import fr.paladium.palamod.modules.paladium.common.logics.TileCrusher;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityOnlineDetector;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityStatue;
import fr.paladium.palamod.modules.paladium.common.logics.TileEntityTotem;
import fr.paladium.palamod.modules.paladium.common.logics.TileHarpagophytumFlower;
import fr.paladium.palamod.modules.paladium.common.logics.TitaneChestLogic;
import fr.paladium.palamod.modules.paladium.common.tileentities.TileEntityPrintPress;

public class PRegister_Tiles {
  public static void init() {
    GameRegistry.registerTileEntityWithAlternatives(PaladiumMachineLogic.class, "palamod:paladiumMachine", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileHarpagophytumFlower.class, "palamod:harpagophytumFlower", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(PaladiumChestLogic.class, "palamod:paladiumChest", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityOnlineDetector.class, "palamod:onlinedetector", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(AlchemyCreatorLogic.class, "palamod:alchemyCreator", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(BowMachineLogic.class, "palamod:bowMachine", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(PaladiumFurnaceLogic.class, "palamod:paladiumFurnace", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(ForgeLogic.class, "palamod:forge", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileCobbleBreaker.class, "palamod:cobbleBreaker", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileCrusher.class, "palamod:crusher", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(EndiumChestLogic.class, "palamod:endiumChest", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TitaneChestLogic.class, "palamod:titaneChest", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(AmethystChestLogic.class, "palamod:amethystChest", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(GPaladiumChestLogic.class, "palamod:gpaladiumChest", new String[0]);
    GameRegistry.registerTileEntity(TileEntityTotem.class, BlocksRegister.Totem
        .func_149739_a());
    GameRegistry.registerTileEntity(TileEntityStatue.class, BlocksRegister.STATUE
        .func_149739_a());
    GameRegistry.registerTileEntityWithAlternatives(TileEntityPrintPress.class, "palamod:tileentity_printpress", new String[0]);
    GameRegistry.registerTileEntityWithAlternatives(TileEntityTypeMachine.class, "palamod:tileentity_typingmachine", new String[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\registerer\PRegister_Tiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */