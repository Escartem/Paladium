package fr.paladium.palamod.modules.hunter.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.common.Registry;
import java.awt.Color;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPerlinpinpinPower extends Block implements ITooltipWiki {
  @SideOnly(Side.CLIENT)
  private static IIcon cross;
  
  @SideOnly(Side.CLIENT)
  private static IIcon line_vertical;
  
  @SideOnly(Side.CLIENT)
  private static IIcon line_horizontal;
  
  @SideOnly(Side.CLIENT)
  private static IIcon cross_default;
  
  public BlockPerlinpinpinPower() {
    super(Material.field_151594_q);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    func_149647_a((CreativeTabs)Registry.HUNTER_TAB);
    func_149663_c("perlinpinpin_power");
    func_149658_d("palamod:perlinpinpin_power");
    func_149711_c(1.0F);
  }
  
  public IIcon func_149673_e(IBlockAccess iblockaccess, int x, int y, int z, int meta) {
    switch (iblockaccess.func_72805_g(x, y, z)) {
      case 0:
        return getRedstoneWireIcon("cross");
      case 1:
        return getRedstoneWireIcon("line_vertical");
      case 2:
        return getRedstoneWireIcon("line_horizontal");
      case 3:
        return getRedstoneWireIcon("cross");
      case 4:
        return getRedstoneWireIcon("line_vertical");
      case 5:
        return getRedstoneWireIcon("line_horizontal");
      case 6:
        return getRedstoneWireIcon("cross_default");
    } 
    return getRedstoneWireIcon("cross_default");
  }
  
  public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
    return (World.func_147466_a((IBlockAccess)p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_) || p_149742_1_
      
      .func_147439_a(p_149742_2_, p_149742_3_ - 1, p_149742_4_) == Blocks.field_150426_aN);
  }
  
  public int func_149720_d(IBlockAccess iblockaccess, int x, int y, int z) {
    return (new Color(45, 4, 158)).getRGB();
  }
  
  public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
    return null;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public void func_149695_a(World world, int x, int y, int z, Block b) {
    if (b == this) {
      boolean hasNeighbor = false;
      if (world.func_147439_a(x + 1, y, z) == this) {
        hasNeighbor = true;
        return;
      } 
      if (world.func_147439_a(x - 1, y, z) == this) {
        hasNeighbor = true;
        return;
      } 
      if (world.func_147439_a(x, y, z + 1) == this) {
        hasNeighbor = true;
        return;
      } 
      if (world.func_147439_a(x, y, z - 1) == this) {
        hasNeighbor = true;
        return;
      } 
      if (!hasNeighbor)
        world.func_72921_c(x, y, z, 6, 1); 
    } 
    super.func_149695_a(world, x, y, z, b);
  }
  
  public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    int result = 6;
    if (world.func_147439_a(x, y, z + 1) == BlocksRegister.PERLINPINPIN_POWER) {
      world.func_72921_c(x, y, z + 1, 2, 1);
      if (world.func_147439_a(x + 1, y, z + 1) == BlocksRegister.PERLINPINPIN_POWER)
        world.func_72921_c(x, y, z + 1, 0, 1); 
      if (world.func_147439_a(x - 1, y, z + 1) == BlocksRegister.PERLINPINPIN_POWER)
        world.func_72921_c(x, y, z + 1, 0, 1); 
      result = 2;
    } 
    if (world.func_147439_a(x, y, z - 1) == BlocksRegister.PERLINPINPIN_POWER) {
      world.func_72921_c(x, y, z - 1, 2, 1);
      if (world.func_147439_a(x + 1, y, z - 1) == BlocksRegister.PERLINPINPIN_POWER)
        world.func_72921_c(x, y, z - 1, 0, 1); 
      if (world.func_147439_a(x - 1, y, z - 1) == BlocksRegister.PERLINPINPIN_POWER)
        world.func_72921_c(x, y, z - 1, 0, 1); 
      result = 2;
      if (world.func_147439_a(x + 1, y, z) == BlocksRegister.PERLINPINPIN_POWER && world
        .func_147439_a(x, y, z + 1) == BlocksRegister.PERLINPINPIN_POWER)
        result = 0; 
    } 
    if (world.func_147439_a(x + 1, y, z) == BlocksRegister.PERLINPINPIN_POWER) {
      world.func_72921_c(x + 1, y, z, 1, 1);
      if (world.func_147439_a(x + 1, y, z + 1) == BlocksRegister.PERLINPINPIN_POWER)
        world.func_72921_c(x + 1, y, z, 0, 1); 
      if (world.func_147439_a(x + 1, y, z - 1) == BlocksRegister.PERLINPINPIN_POWER)
        world.func_72921_c(x + 1, y, z, 0, 1); 
      result = 1;
      if (world.func_147439_a(x + 1, y, z) == BlocksRegister.PERLINPINPIN_POWER && world
        .func_147439_a(x, y, z - 1) == BlocksRegister.PERLINPINPIN_POWER)
        result = 0; 
      if (world.func_147439_a(x + 1, y, z) == BlocksRegister.PERLINPINPIN_POWER && world
        .func_147439_a(x, y, z + 1) == BlocksRegister.PERLINPINPIN_POWER)
        result = 0; 
    } 
    if (world.func_147439_a(x - 1, y, z) == BlocksRegister.PERLINPINPIN_POWER) {
      world.func_72921_c(x - 1, y, z, 1, 1);
      if (world.func_147439_a(x - 1, y, z + 1) == BlocksRegister.PERLINPINPIN_POWER)
        world.func_72921_c(x - 1, y, z, 0, 1); 
      if (world.func_147439_a(x - 1, y, z - 1) == BlocksRegister.PERLINPINPIN_POWER)
        world.func_72921_c(x - 1, y, z, 0, 1); 
      result = 1;
      if (world.func_147439_a(x - 1, y, z) == BlocksRegister.PERLINPINPIN_POWER && world
        .func_147439_a(x, y, z - 1) == BlocksRegister.PERLINPINPIN_POWER)
        result = 0; 
      if (world.func_147439_a(x - 1, y, z) == BlocksRegister.PERLINPINPIN_POWER && world
        .func_147439_a(x, y, z + 1) == BlocksRegister.PERLINPINPIN_POWER)
        result = 0; 
    } 
    return result;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random random) {
    int l = world.func_72805_g(x, y, z);
    if (l >= 3 && l != 6) {
      double d0 = x + 0.5D + (random.nextFloat() - 0.5D) * 0.2D;
      double d1 = (y + 0.0625F);
      double d2 = z + 0.5D + (random.nextFloat() - 0.5D) * 0.2D;
      float f = l / 15.0F;
      float f1 = f * 0.6F + 0.4F;
      if (l == 0)
        f1 = 0.0F; 
      float f2 = f * f * 0.7F - 0.5F;
      float f3 = f * f * 0.6F - 0.7F;
      if (f2 < 0.0F)
        f2 = 0.0F; 
      if (f3 < 0.0F)
        f3 = 0.0F; 
      world.func_72869_a("mobSpell", d0, d1, d2, f1, f2, f3);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    this;
    cross = register.func_94245_a(func_149641_N() + "_cross");
    this;
    cross_default = register.func_94245_a(func_149641_N() + "_cross_default");
    this;
    line_vertical = register.func_94245_a(func_149641_N() + "_line_vertical");
    this;
    line_horizontal = register.func_94245_a(func_149641_N() + "_line_horizontal");
    this;
    this.field_149761_L = cross_default;
  }
  
  @SideOnly(Side.CLIENT)
  public static IIcon getRedstoneWireIcon(String str) {
    return str.equals("cross_default") ? cross_default : (
      str.equals("cross") ? cross : (
      str.equals("line_vertical") ? line_vertical : (
      str.equals("line_horizontal") ? line_horizontal : null)));
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#10.-les-objets-relatifs-au-metier-dhunter";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\blocks\BlockPerlinpinpinPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */