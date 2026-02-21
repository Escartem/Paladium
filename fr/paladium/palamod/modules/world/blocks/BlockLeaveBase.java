package fr.paladium.palamod.modules.world.blocks;

import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.blocks.BaseBlock;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.zephyrui.lib.color.Color;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockLeaveBase extends BaseBlock implements IShearable {
  private final int dimMinerColor = (new Color(100, 100, 100)).getRGB();
  
  private final int chance;
  
  private final Block dropBlock;
  
  public BlockLeaveBase(String unlocalizedName, int chance, Block drop) {
    super(Material.field_151584_j, 0.2F, "leave/" + unlocalizedName);
    func_149647_a((CreativeTabs)Registry.PLANTS_TAB);
    func_149713_g(1);
    func_149672_a(Block.field_149779_h);
    this.chance = chance;
    this.dropBlock = drop;
    func_149663_c(unlocalizedName);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
    return true;
  }
  
  public boolean func_149646_a(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
    return Blocks.field_150362_t.func_149646_a(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
  }
  
  public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
    return true;
  }
  
  public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    ret.add(new ItemStack((Block)this, 1, world.func_72805_g(x, y, z) & 0x3));
    return ret;
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    int random = (int)(Math.random() * 10000.0D);
    ArrayList<ItemStack> itemStacks = new ArrayList<>();
    if (random <= this.chance)
      itemStacks.add(new ItemStack(this.dropBlock)); 
    return itemStacks;
  }
  
  public int func_149720_d(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
    if (PMiner.proxy.isMinerDimension())
      return this.dimMinerColor; 
    return super.func_149720_d(p_149720_1_, p_149720_2_, p_149720_3_, p_149720_4_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\blocks\BlockLeaveBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */