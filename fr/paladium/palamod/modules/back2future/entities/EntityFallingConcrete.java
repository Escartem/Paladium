package fr.paladium.palamod.modules.back2future.entities;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.world.World;

public class EntityFallingConcrete extends EntityFallingBlock {
  private Block fallTile;
  
  private int meta;
  
  public EntityFallingConcrete(World worldIn) {
    super(worldIn);
  }
  
  public EntityFallingConcrete(World worldIn, double x, double y, double z, Block block) {
    this(worldIn, x, y, z, block, 0);
  }
  
  public EntityFallingConcrete(World worldIn, double x, double y, double z, Block block, int meta) {
    super(worldIn, x, y, z, block, meta);
    this.fallTile = block;
    this.meta = meta;
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v)
      .func_149688_o() == Material.field_151586_h || this.field_70170_p
      
      .func_147439_a((int)this.field_70165_t - 1, (int)this.field_70163_u, (int)this.field_70161_v - 1)
      .func_149688_o() == Material.field_151586_h) {
      BlockPos pos = new BlockPos((Entity)this);
      this.field_70170_p.func_147465_d(pos.getX(), pos.getY(), pos.getZ(), this.fallTile, this.meta, 3);
      func_70106_y();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityFallingConcrete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */