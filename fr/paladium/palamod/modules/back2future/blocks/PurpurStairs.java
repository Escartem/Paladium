package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.BlockStairs;
import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;

public class PurpurStairs extends BlockStairs implements IConfigurable {
  public PurpurStairs() {
    super(ModBlocks.purpur_block, 0);
    func_149711_c(0.8F);
    func_149713_g(0);
    func_149663_c(Utils.getUnlocalisedName("purpur_stairs"));
    func_149647_a(Back2Future.enableChorusFruit ? Back2Future.creativeTab : null);
  }
  
  public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
    return !(entity instanceof net.minecraft.entity.boss.EntityDragon);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableChorusFruit;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\PurpurStairs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */