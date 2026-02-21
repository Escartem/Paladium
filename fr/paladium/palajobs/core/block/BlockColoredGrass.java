package fr.paladium.palajobs.core.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.registry.BlocksRegistry;
import fr.paladium.palajobs.core.tab.JobsTab;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockColoredGrass extends Block implements IGrowable {
  @SideOnly(Side.CLIENT)
  private IIcon field_149991_b;
  
  @SideOnly(Side.CLIENT)
  private IIcon field_149993_M;
  
  @SideOnly(Side.CLIENT)
  private IIcon field_149994_N;
  
  public BlockColoredGrass(String name) {
    super(Material.field_151577_b);
    func_149647_a((CreativeTabs)JobsTab.getInstance());
    func_149672_a(field_149779_h);
    func_149663_c(name);
    func_149658_d(name);
    func_149711_c(0.6F);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    return (p_149691_1_ == 1) ? this.field_149991_b : ((p_149691_1_ == 0) ? Blocks.field_150346_d.func_149733_h(p_149691_1_) : this.field_149761_L);
  }
  
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }
  
  public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
    return false;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Blocks.field_150346_d.func_149650_a(0, p_149650_2_, p_149650_3_);
  }
  
  public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
    return true;
  }
  
  public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
    for (int i = 0; i < 10; i++)
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, i)); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_) {
    if (p_149673_5_ == 1)
      return this.field_149991_b; 
    if (p_149673_5_ == 0)
      return Blocks.field_150346_d.func_149733_h(p_149673_5_); 
    Material material = p_149673_1_.func_147439_a(p_149673_2_, p_149673_3_ + 1, p_149673_4_).func_149688_o();
    return (material != Material.field_151597_y && material != Material.field_151596_z) ? this.field_149761_L : this.field_149993_M;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.field_149761_L = p_149651_1_.func_94245_a("palajobs:" + func_149641_N() + "_side");
    this.field_149991_b = p_149651_1_.func_94245_a("palajobs:" + func_149641_N() + "_top");
    this.field_149993_M = p_149651_1_.func_94245_a("palajobs:" + func_149641_N() + "_side");
    this.field_149994_N = p_149651_1_.func_94245_a("palajobs:" + func_149641_N() + "_side");
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149635_D() {
    double d0 = 0.5D;
    double d1 = 1.0D;
    return ColorizerGrass.func_77480_a(d0, d1);
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149741_i(int p_149741_1_) {
    return getColorByMetadata(p_149741_1_);
  }
  
  public static int getColorByMetadata(int metadata) {
    switch (metadata) {
      case 0:
        return (new Color(20, 20, 20)).getRGB();
      case 1:
        return (new Color(212, 0, 0)).getRGB();
      case 2:
        return (new Color(0, 60, 0)).getRGB();
      case 3:
        return (new Color(88, 41, 0)).getRGB();
      case 4:
        return (new Color(2, 15, 245)).getRGB();
      case 5:
        return (new Color(128, 2, 245)).getRGB();
      case 6:
        return (new Color(214, 237, 2)).getRGB();
      case 7:
        return (new Color(252, 123, 3)).getRGB();
      case 8:
        return (new Color(252, 3, 152)).getRGB();
      case 9:
        return (new Color(67, 245, 2)).getRGB();
    } 
    return (new Color(255, 255, 255)).getRGB();
  }
  
  public static String getColorById(int metadata) {
    switch (metadata) {
      case 0:
        return "black";
      case 1:
        return "red";
      case 2:
        return "darkgreen";
      case 3:
        return "brown";
      case 4:
        return "blue";
      case 5:
        return "purple";
      case 6:
        return "yellow";
      case 7:
        return "orange";
      case 8:
        return "pink";
      case 9:
        return "green";
    } 
    return "white";
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149720_d(IBlockAccess iblockaccess, int x, int y, int z) {
    int metadata = iblockaccess.func_72805_g(x, y, z);
    return getColorByMetadata(metadata);
  }
  
  @SideOnly(Side.CLIENT)
  public static IIcon getIconSideOverlay() {
    return BlocksRegistry.COLORED_GRASS.field_149994_N;
  }
  
  public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
    int l = 0;
    label23: while (l < 128) {
      int i1 = p_149853_3_;
      int j1 = p_149853_4_ + 1;
      int k1 = p_149853_5_;
      int l1 = 0;
      while (l1 < l / 16) {
        i1 += p_149853_2_.nextInt(3) - 1;
        j1 += (p_149853_2_.nextInt(3) - 1) * p_149853_2_.nextInt(3) / 2;
        k1 += p_149853_2_.nextInt(3) - 1;
        if (p_149853_1_.func_147439_a(i1, j1 - 1, k1) == Blocks.field_150349_c) {
          if (!p_149853_1_.func_147439_a(i1, j1, k1).func_149721_r()) {
            l1++;
            continue;
          } 
          continue label23;
        } 
        continue label23;
      } 
      if (p_149853_1_.func_147439_a(i1, j1, k1).func_149688_o() == Material.field_151579_a)
        if (p_149853_2_.nextInt(8) != 0) {
          if (Blocks.field_150329_H.func_149718_j(p_149853_1_, i1, j1, k1))
            p_149853_1_.func_147465_d(i1, j1, k1, (Block)Blocks.field_150329_H, 1, 3); 
        } else {
          p_149853_1_.func_72807_a(i1, k1).plantFlower(p_149853_1_, p_149853_2_, i1, j1, k1);
        }  
      l++;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\block\BlockColoredGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */