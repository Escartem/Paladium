package fr.paladium.palamod.modules.world.blocks.bush;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.palamod.modules.world.structure.WorldTree1Gen;
import fr.paladium.palamod.modules.world.structure.WorldTree2Gen;
import fr.paladium.palamod.modules.world.structure.WorldTree3Gen;
import fr.paladium.palamod.modules.world.structure.WorldTree4Gen;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BaseSaplingCustom extends BlockBush implements IGrowable {
  private IIcon icon;
  
  public static final int JUDEECERCIS = 1;
  
  public static final int JACARANDA = 2;
  
  public static final int ERABLE = 3;
  
  public static final int OSTRYA = 4;
  
  private int TYPE;
  
  public BaseSaplingCustom(int type) {
    float f = 0.4F;
    func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    func_149647_a((CreativeTabs)Registry.PLANTS_TAB);
    this.TYPE = type;
    switch (this.TYPE) {
      case 1:
        func_149663_c("sapling.judeecercis");
        return;
      case 2:
        func_149663_c("sapling.jacaranda");
        return;
      case 3:
        func_149663_c("sapling.erable");
        return;
      case 4:
        func_149663_c("sapling.ostrya");
        return;
    } 
    func_149663_c("sapling.null");
  }
  
  public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
    if (!p_149674_1_.field_72995_K) {
      super.func_149674_a(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
      if (p_149674_1_.func_72957_l(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9 && p_149674_5_
        .nextInt(7) == 0)
        func_149879_c(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_); 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    return this.icon;
  }
  
  public void func_149879_c(World p_149879_1_, int p_149879_2_, int p_149879_3_, int p_149879_4_, Random p_149879_5_) {
    int l = p_149879_1_.func_72805_g(p_149879_2_, p_149879_3_, p_149879_4_);
    if ((l & 0x8) == 0) {
      p_149879_1_.func_72921_c(p_149879_2_, p_149879_3_, p_149879_4_, l | 0x8, 4);
    } else {
      func_149878_d(p_149879_1_, p_149879_2_, p_149879_3_, p_149879_4_, p_149879_5_);
    } 
  }
  
  public void func_149878_d(World world, int x, int y, int z, Random random) {
    if (!TerrainGen.saplingGrowTree(world, random, x, y, z))
      return; 
    world.func_147468_f(x, y, z);
    switch (this.TYPE) {
      case 1:
        generateRandomTree(PWorld.LEAVE_JUDEECERCIS, PWorld.LOG_JUDEECERCIS).func_76484_a(world, random, x, y - 1, z);
        break;
      case 2:
        generateRandomTree(PWorld.LEAVE_JACARANDA, PWorld.LOG_JACARANDA).func_76484_a(world, random, x, y - 1, z);
        break;
      case 3:
        generateRandomTree(PWorld.LEAVE_ERABLE, PWorld.LOG_ERABLE).func_76484_a(world, random, x, y - 1, z);
        break;
      case 4:
        generateRandomTree(PWorld.LEAVE_OSTRYA, PWorld.LOG_OSTRYA).func_76484_a(world, random, x, y - 1, z);
        break;
    } 
  }
  
  public WorldGenerator generateRandomTree(Block leave, Block log) {
    int random = (int)(Math.random() * 3.0D);
    if (random <= 1)
      return (WorldGenerator)new WorldTree1Gen(leave, log); 
    if (random <= 2)
      return (WorldGenerator)new WorldTree2Gen(leave, log); 
    if (random <= 3)
      return (WorldGenerator)new WorldTree3Gen(leave, log); 
    if (random <= 4)
      return (WorldGenerator)new WorldTree4Gen(leave, log); 
    return null;
  }
  
  public int func_149692_a(int p_149692_1_) {
    return MathHelper.func_76125_a(p_149692_1_ & 0x7, 0, 5);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegister) {
    switch (this.TYPE) {
      case 1:
        this.icon = iconRegister.func_94245_a("palamod:sapling/judeecercis");
        return;
      case 2:
        this.icon = iconRegister.func_94245_a("palamod:sapling/jacaranda");
        return;
      case 3:
        this.icon = iconRegister.func_94245_a("palamod:sapling/erable");
        return;
      case 4:
        this.icon = iconRegister.func_94245_a("palamod:sapling/ostrya");
        return;
    } 
    this.icon = iconRegister.func_94245_a("palamod:sapling/default");
  }
  
  public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_) {
    return true;
  }
  
  public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
    return (p_149852_1_.field_73012_v.nextFloat() < 0.45D);
  }
  
  public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
    func_149879_c(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\blocks\bush\BaseSaplingCustom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */