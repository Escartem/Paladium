package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockFakeLava extends Block {
  public BlockFakeLava() {
    super(Material.field_151582_l);
    this.field_149787_q = false;
    func_149711_c(100.0F);
    func_149715_a(1.0F);
    func_149663_c("fakelava");
    func_149658_d("lava_still");
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
    return null;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockFakeLava.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */