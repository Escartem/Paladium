package fr.paladium.palajobs.core.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.registry.BlocksRegistry;
import fr.paladium.palajobs.core.tab.JobsTab;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockColoredGrassSeeds extends BlockBush implements IGrowable {
  @SideOnly(Side.CLIENT)
  private IIcon[] field_149870_b;
  
  public BlockColoredGrassSeeds(String name) {
    super(Material.field_151582_l);
    float f = 0.4F;
    func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    func_149647_a((CreativeTabs)JobsTab.getInstance());
    func_149663_c(name);
    func_149658_d(name);
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random random) {
    super.func_149674_a(world, x, y, z, random);
    if (world.func_72957_l(x, y + 1, z) < 9)
      return; 
    float f = getGrowthRate(world, x, y, z);
    if (random.nextInt((int)(25.0F / f) + 1) != 0)
      return; 
    Block block = world.func_147439_a(x, y - 1, z);
    int medatada = world.func_72805_g(x, y, z);
    if (block != null && block.func_149688_o().func_76220_a() && block.isFertile(world, x, y, z)) {
      world.func_147449_b(x, y - 1, z, BlocksRegistry.COLORED_GRASS);
      world.func_72921_c(x, y - 1, z, medatada, 2);
      world.func_147468_f(x, y, z);
    } 
  }
  
  protected boolean func_149854_a(Block block) {
    return (block == Blocks.field_150458_ak);
  }
  
  public boolean func_149718_j(World world, int x, int y, int z) {
    Block blockBelow = world.func_147439_a(x, y - 1, z);
    return (blockBelow.func_149688_o().func_76220_a() && blockBelow.isFertile(world, x, y - 1, z));
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149741_i(int p_149741_1_) {
    return BlockColoredGrass.getColorByMetadata(p_149741_1_);
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return null;
  }
  
  public int func_149679_a(int p_149679_1_, Random p_149679_2_) {
    return 1 + p_149679_2_.nextInt(p_149679_1_ * 2 + 1);
  }
  
  public int func_149643_k(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_) {
    return p_149643_1_.func_72805_g(p_149643_2_, p_149643_3_, p_149643_4_);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
    for (int i = 0; i < 10; i++)
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, i)); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    if (p_149691_2_ >= this.field_149870_b.length)
      p_149691_2_ = 0; 
    return this.field_149870_b[p_149691_2_];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.field_149870_b = new IIcon[11];
    for (int var2 = 0; var2 < this.field_149870_b.length; var2++)
      this.field_149870_b[var2] = p_149651_1_.func_94245_a("palajobs:colored_grass_seeds_" + BlockColoredGrass.getColorById(var2)); 
  }
  
  public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
    int l = p_149851_1_.func_72805_g(p_149851_2_, p_149851_3_, p_149851_4_);
    return (l != 0);
  }
  
  public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
    return true;
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
    return new ArrayList<>();
  }
  
  public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {}
  
  private float getGrowthRate(World world, int x, int y, int z) {
    float f = 1.0F;
    Block block = world.func_147439_a(x, y, z - 1);
    Block block1 = world.func_147439_a(x, y, z + 1);
    Block block2 = world.func_147439_a(x - 1, y, z);
    Block block3 = world.func_147439_a(x + 1, y, z);
    Block block4 = world.func_147439_a(x - 1, y, z - 1);
    Block block5 = world.func_147439_a(x + 1, y, z - 1);
    Block block6 = world.func_147439_a(x + 1, y, z + 1);
    Block block7 = world.func_147439_a(x - 1, y, z + 1);
    boolean flag = (block2 == this || block3 == this);
    boolean flag1 = (block == this || block1 == this);
    boolean flag2 = (block4 == this || block5 == this || block6 == this || block7 == this);
    for (int l = x - 1; l <= x + 1; l++) {
      for (int i1 = z - 1; i1 <= z + 1; i1++) {
        float f1 = 0.0F;
        if (world.func_147439_a(l, y - 1, i1).canSustainPlant((IBlockAccess)world, l, y - 1, i1, ForgeDirection.UP, (IPlantable)this)) {
          f1 = 1.0F;
          if (world.func_147439_a(l, y - 1, i1).isFertile(world, l, y - 1, i1))
            f1 = 3.0F; 
        } 
        if (l != x || i1 != z)
          f1 /= 4.0F; 
        f += f1;
      } 
    } 
    if (flag2 || (flag && flag1))
      f /= 2.0F; 
    return f;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\block\BlockColoredGrassSeeds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */