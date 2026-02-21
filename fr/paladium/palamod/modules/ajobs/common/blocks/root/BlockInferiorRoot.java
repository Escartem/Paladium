package fr.paladium.palamod.modules.ajobs.common.blocks.root;

import fr.paladium.palajobs.core.tab.JobsTab;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.ajobs.common.registerer.BlocksRegistry;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockInferiorRoot extends BlockLeavesBase implements IShearable {
  public BlockInferiorRoot(String unlocalizedName) {
    super(Material.field_151584_j, false);
    func_149711_c(0.2F);
    func_149713_g(1);
    func_149672_a(field_149779_h);
    func_149663_c(unlocalizedName);
    func_149658_d("palajobs:" + unlocalizedName);
    func_149647_a((CreativeTabs)JobsTab.getInstance());
  }
  
  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    Location location = new Location(world, x, (y - 1), z);
    for (int currentY = (int)location.getY(); currentY > 0; currentY--) {
      Block currentBlock = world.func_147439_a((int)location.getX(), currentY, (int)location.getZ());
      if (!currentBlock.equals(BlocksRegistry.ROOT_LAST))
        break; 
      world.func_147468_f((int)location.getX(), currentY, (int)location.getZ());
    } 
    super.func_149749_a(world, x, y, z, block, meta);
  }
  
  public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
    return true;
  }
  
  public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
    return true;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public int func_149645_b() {
    return 1;
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    ret.add(new ItemStack((Block)this, 1));
    return ret;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\blocks\root\BlockInferiorRoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */