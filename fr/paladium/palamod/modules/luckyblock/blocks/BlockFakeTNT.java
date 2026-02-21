package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.block.BlockTNT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockFakeTNT extends BlockTNT {
  public BlockFakeTNT() {
    func_149663_c("paladium_fake_tnt");
    func_149658_d("minecraft:tnt");
    func_149711_c(0.0F);
    func_149672_a(field_149779_h);
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public void func_149723_a(World p_149723_1_, int p_149723_2_, int p_149723_3_, int p_149723_4_, Explosion p_149723_5_) {}
  
  public void func_150114_a(World world, int x, int y, int z, int meta, EntityLivingBase entity) {
    if (!world.field_72995_K && (
      meta & 0x1) == 1)
      world.func_72876_a((Entity)entity, x, y, z, 5.0F, false); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockFakeTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */