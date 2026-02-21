package fr.paladium.palawarzoneevent.module.warzone.common.block;

import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class WarzoneBlocks {
  public static Block WARZONE_SIGN;
  
  public static Block WARZONE_SIGN_PLACEHOLDER;
  
  public static void init(CreativeTabs creativeTab) {
    RegistryUtils.block(new Block[] { WARZONE_SIGN = new BlockWarzoneSign(creativeTab), WARZONE_SIGN_PLACEHOLDER = new BlockWarzonePlaceholder(creativeTab) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\block\WarzoneBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */