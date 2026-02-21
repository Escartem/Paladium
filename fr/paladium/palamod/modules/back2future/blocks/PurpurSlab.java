package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;

public class PurpurSlab extends GenericSlab {
  public PurpurSlab() {
    super(Material.field_151576_e, ModBlocks.purpur_block);
    func_149752_b(30.0F);
    func_149711_c(2.0F);
    func_149663_c(Utils.getUnlocalisedName("purpur_slab"));
    func_149647_a(Back2Future.enableChorusFruit ? Back2Future.creativeTab : null);
  }
  
  public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
    return !(entity instanceof net.minecraft.entity.boss.EntityDragon);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableChorusFruit;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\PurpurSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */