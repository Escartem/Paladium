package fr.paladium.palawither.api;

import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palawither.common.block.BlockWitherReceptacle;
import net.minecraft.block.Block;

public class BlocksRegister {
  public static Block WITHER_RECEPTACLE;
  
  public static void register() {
    RegistryUtils.block(new Block[] { WITHER_RECEPTACLE = (Block)new BlockWitherReceptacle() });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\api\BlocksRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */