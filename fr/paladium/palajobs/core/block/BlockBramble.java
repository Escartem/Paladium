package fr.paladium.palajobs.core.block;

import fr.paladium.palajobs.core.tab.JobsTab;
import fr.paladium.palajobs.core.tileentity.TileEntityBramble;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockBramble extends Block {
  public BlockBramble() {
    super(Material.field_151577_b);
    func_149647_a((CreativeTabs)JobsTab.getInstance());
    func_149672_a(field_149779_h);
    func_149663_c("bramble");
    func_149658_d("palajobs:bramble");
    func_149711_c(0.6F);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityBramble();
  }
  
  public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
    float f = 0.125F;
    return AxisAlignedBB.func_72330_a(p_149668_2_, p_149668_3_, p_149668_4_, (p_149668_2_ + 1), ((p_149668_3_ + 1) - f), (p_149668_4_ + 1));
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity player) {
    player.field_70159_w *= 0.4D;
    player.field_70179_y *= 0.4D;
    player.func_70097_a(DamageSource.field_76376_m, 1.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\block\BlockBramble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */