package fr.paladium.palamod.modules.luckyblock.blocks.black;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.tileentity.black.TileEntityFakeFire;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockFakeFire extends BlockFire {
  @SideOnly(Side.CLIENT)
  private IIcon[] field_149850_M;
  
  public BlockFakeFire() {
    func_149675_a(true);
    func_149663_c("fake_fire");
    func_149711_c(0.0F);
    func_149715_a(1.0F);
    func_149672_a(field_149766_f);
    func_149649_H();
    func_149658_d("fire");
  }
  
  public void func_149699_a(World world, int x, int y, int z, EntityPlayer p_149699_5_) {
    world.func_147468_f(x, y, z);
  }
  
  public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {}
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityFakeFire();
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
  
  public int func_149645_b() {
    return 3;
  }
  
  public int func_149745_a(Random p_149745_1_) {
    return 0;
  }
  
  public int func_149738_a(World p_149738_1_) {
    return 30;
  }
  
  public boolean func_149698_L() {
    return false;
  }
  
  public boolean func_149703_v() {
    return false;
  }
  
  public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
    return World.func_147466_a((IBlockAccess)p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_);
  }
  
  public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
    if (!World.func_147466_a((IBlockAccess)p_149695_1_, p_149695_2_, p_149695_3_ - 1, p_149695_4_))
      p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_); 
  }
  
  public void func_149726_b(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
    if (p_149726_1_.field_73011_w.field_76574_g > 0 || 
      !Blocks.field_150427_aO.func_150000_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_))
      if (!World.func_147466_a((IBlockAccess)p_149726_1_, p_149726_2_, p_149726_3_ - 1, p_149726_4_)) {
        p_149726_1_.func_147468_f(p_149726_2_, p_149726_3_, p_149726_4_);
      } else {
        p_149726_1_.func_147464_a(p_149726_2_, p_149726_3_, p_149726_4_, (Block)this, 
            func_149738_a(p_149726_1_) + p_149726_1_.field_73012_v.nextInt(10));
      }  
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149734_b(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
    if (p_149734_5_.nextInt(24) == 0)
      p_149734_1_.func_72980_b((p_149734_2_ + 0.5F), (p_149734_3_ + 0.5F), (p_149734_4_ + 0.5F), "fire.fire", 1.0F + p_149734_5_
          
          .nextFloat(), p_149734_5_.nextFloat() * 0.7F + 0.3F, false); 
    if (!World.func_147466_a((IBlockAccess)p_149734_1_, p_149734_2_, p_149734_3_ - 1, p_149734_4_) && 
      !Blocks.field_150480_ab.canCatchFire((IBlockAccess)p_149734_1_, p_149734_2_, p_149734_3_ - 1, p_149734_4_, ForgeDirection.UP)) {
      if (Blocks.field_150480_ab.canCatchFire((IBlockAccess)p_149734_1_, p_149734_2_ - 1, p_149734_3_, p_149734_4_, ForgeDirection.EAST))
        for (int l = 0; l < 2; l++) {
          float f = p_149734_2_ + p_149734_5_.nextFloat() * 0.1F;
          float f1 = p_149734_3_ + p_149734_5_.nextFloat();
          float f2 = p_149734_4_ + p_149734_5_.nextFloat();
          p_149734_1_.func_72869_a("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
        }  
      if (Blocks.field_150480_ab.canCatchFire((IBlockAccess)p_149734_1_, p_149734_2_ + 1, p_149734_3_, p_149734_4_, ForgeDirection.WEST))
        for (int l = 0; l < 2; l++) {
          float f = (p_149734_2_ + 1) - p_149734_5_.nextFloat() * 0.1F;
          float f1 = p_149734_3_ + p_149734_5_.nextFloat();
          float f2 = p_149734_4_ + p_149734_5_.nextFloat();
          p_149734_1_.func_72869_a("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
        }  
      if (Blocks.field_150480_ab.canCatchFire((IBlockAccess)p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_ - 1, ForgeDirection.SOUTH))
        for (int l = 0; l < 2; l++) {
          float f = p_149734_2_ + p_149734_5_.nextFloat();
          float f1 = p_149734_3_ + p_149734_5_.nextFloat();
          float f2 = p_149734_4_ + p_149734_5_.nextFloat() * 0.1F;
          p_149734_1_.func_72869_a("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
        }  
      if (Blocks.field_150480_ab.canCatchFire((IBlockAccess)p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_ + 1, ForgeDirection.NORTH))
        for (int l = 0; l < 2; l++) {
          float f = p_149734_2_ + p_149734_5_.nextFloat();
          float f1 = p_149734_3_ + p_149734_5_.nextFloat();
          float f2 = (p_149734_4_ + 1) - p_149734_5_.nextFloat() * 0.1F;
          p_149734_1_.func_72869_a("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
        }  
      if (Blocks.field_150480_ab.canCatchFire((IBlockAccess)p_149734_1_, p_149734_2_, p_149734_3_ + 1, p_149734_4_, ForgeDirection.DOWN))
        for (int l = 0; l < 2; l++) {
          float f = p_149734_2_ + p_149734_5_.nextFloat();
          float f1 = (p_149734_3_ + 1) - p_149734_5_.nextFloat() * 0.1F;
          float f2 = p_149734_4_ + p_149734_5_.nextFloat();
          p_149734_1_.func_72869_a("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
        }  
    } else {
      for (int l = 0; l < 3; l++) {
        float f = p_149734_2_ + p_149734_5_.nextFloat();
        float f1 = p_149734_3_ + p_149734_5_.nextFloat() * 0.5F + 0.5F;
        float f2 = p_149734_4_ + p_149734_5_.nextFloat();
        p_149734_1_.func_72869_a("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
      } 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this
      .field_149850_M = new IIcon[] { p_149651_1_.func_94245_a(func_149641_N() + "_layer_0"), p_149651_1_.func_94245_a(func_149641_N() + "_layer_1") };
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149840_c(int p_149840_1_) {
    return this.field_149850_M[p_149840_1_];
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    return this.field_149850_M[0];
  }
  
  public MapColor func_149728_f(int p_149728_1_) {
    return MapColor.field_151656_f;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\black\BlockFakeFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */