package fr.paladium.palamod.modules.paladium.utils.usebugs.unlootable.configurations;

import fr.paladium.palamod.modules.paladium.utils.usebugs.unlootable.IUnlootableConfiguration;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class MasterConfiguration implements IUnlootableConfiguration {
  public boolean check(World world, int x, int y, int z, int originX, int originY, int originZ, boolean hasOrigin) {
    boolean impillable = true;
    for (int oy = -1; oy < 2; oy++) {
      for (int ox = -1; ox < 2; ox++) {
        for (int oz = -1; oz < 2; oz++) {
          if (ox != 0 || oy != 0 || oz != 0)
            if (!hasOrigin || 
              x + ox != originX || y + oy != originY || z + oz != originZ) {
              Block b = world.func_147439_a(x + ox, y + oy, z + oz);
              if (b instanceof fr.paladium.palamod.modules.paladium.common.blocks.BlockSpike || b == Blocks.field_150353_l || b == Blocks.field_150356_k)
                return true; 
              if (world.func_147437_c(x + ox, y + oy, z + oz) || (b != Blocks.field_150357_h && b != Blocks.field_150467_bQ))
                impillable = false; 
            }  
        } 
      } 
    } 
    return impillable;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\util\\usebug\\unlootable\configurations\MasterConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */