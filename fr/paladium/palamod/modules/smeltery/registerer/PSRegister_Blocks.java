package fr.paladium.palamod.modules.smeltery.registerer;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.modules.smeltery.blocks.BaseBlockGrinder;
import fr.paladium.palamod.modules.smeltery.blocks.BlockGrinder;
import net.minecraft.block.Block;

public class PSRegister_Blocks {
  public static BlockGrinder GRINDER_BLOCK;
  
  public static BaseBlockGrinder GRINDER_CASING_BLOCK;
  
  public static BaseBlockGrinder GRINDER_FRAME_BLOCK;
  
  public static void init() {
    GRINDER_BLOCK = new BlockGrinder("grinder_block");
    GRINDER_CASING_BLOCK = new BaseBlockGrinder("grinder_casing_block");
    GRINDER_FRAME_BLOCK = new BaseBlockGrinder("grinder_frame_block");
    GameRegistry.registerBlock((Block)GRINDER_BLOCK, GRINDER_BLOCK.func_149739_a());
    GameRegistry.registerBlock((Block)GRINDER_CASING_BLOCK, GRINDER_CASING_BLOCK.func_149739_a());
    GameRegistry.registerBlock((Block)GRINDER_FRAME_BLOCK, GRINDER_FRAME_BLOCK.func_149739_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\registerer\PSRegister_Blocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */