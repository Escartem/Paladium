package fr.paladium.palajobs.core.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;

public class BlockColoredTallGrass extends BlockBush implements IGrowable, IShearable {
  @SideOnly(Side.CLIENT)
  private IIcon[] field_149870_b;
  
  public BlockColoredTallGrass() {
    super(Material.field_151582_l);
    float f = 0.4F;
    func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    func_149672_a(field_149779_h);
    func_149663_c("tallgrass");
    func_149647_a((CreativeTabs)JobsTab.getInstance());
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    if (p_149691_2_ >= this.field_149870_b.length)
      p_149691_2_ = 0; 
    return this.field_149870_b[p_149691_2_];
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149635_D() {
    double d0 = 0.5D;
    double d1 = 1.0D;
    return ColorizerGrass.func_77480_a(d0, d1);
  }
  
  public boolean func_149718_j(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_) {
    return super.func_149718_j(p_149718_1_, p_149718_2_, p_149718_3_, p_149718_4_);
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return null;
  }
  
  public int func_149679_a(int p_149679_1_, Random p_149679_2_) {
    return 1 + p_149679_2_.nextInt(p_149679_1_ * 2 + 1);
  }
  
  public void func_149636_a(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {
    super.func_149636_a(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
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
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.field_149870_b = new IIcon[11];
    for (int var2 = 0; var2 < this.field_149870_b.length; var2++)
      this.field_149870_b[var2] = p_149651_1_.func_94245_a("palajobs:tallgrass_" + BlockColoredGrass.getColorById(var2)); 
  }
  
  public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
    int l = p_149851_1_.func_72805_g(p_149851_2_, p_149851_3_, p_149851_4_);
    return (l != 0);
  }
  
  public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
    return true;
  }
  
  public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {}
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    if (world.field_73012_v.nextInt(8) != 0)
      return ret; 
    ItemStack seed = ForgeHooks.getGrassSeed(world);
    if (seed != null)
      ret.add(seed); 
    return ret;
  }
  
  public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
    return true;
  }
  
  public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    ret.add(new ItemStack((Block)this, 1, world.func_72805_g(x, y, z)));
    return ret;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\block\BlockColoredTallGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */