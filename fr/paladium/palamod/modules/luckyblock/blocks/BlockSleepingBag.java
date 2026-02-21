package fr.paladium.palamod.modules.luckyblock.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntitySleepingBag;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSleepingBag extends BlockDirectional {
  public static final int[][] footBlockToHeadBlockMap = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
  
  public static final int[][] field_149981_a = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
  
  public BlockSleepingBag() {
    super(Material.field_151580_n);
    setBounds();
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("sleeping_bed");
    func_149658_d("palamod:sleeping_bed");
    func_149711_c(1.0F);
  }
  
  public boolean func_149727_a(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (p_149727_1_.field_72995_K)
      return true; 
    EntityPlayer.EnumStatus enumstatus = p_149727_5_.func_71018_a(p_149727_2_, p_149727_3_, p_149727_4_);
    if (enumstatus == EntityPlayer.EnumStatus.OK) {
      func_149979_a(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, true);
      return true;
    } 
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    return Blocks.field_150325_L.func_149733_h(p_149691_1_);
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderSleepingBed;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    setBounds();
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return null;
  }
  
  public int func_149745_a(Random p_149745_1_) {
    return 1;
  }
  
  public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
    int l = p_149695_1_.func_72805_g(p_149695_2_, p_149695_3_, p_149695_4_);
    int i1 = func_149895_l(l);
    if (isBlockHeadOfBed(l)) {
      if (p_149695_1_.func_147439_a(p_149695_2_ - field_149981_a[i1][0], p_149695_3_, p_149695_4_ - field_149981_a[i1][1]) != this)
        p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_); 
    } else if (p_149695_1_.func_147439_a(p_149695_2_ + field_149981_a[i1][0], p_149695_3_, p_149695_4_ + field_149981_a[i1][1]) != this) {
      p_149695_1_.func_147468_f(p_149695_2_, p_149695_3_, p_149695_4_);
      if (!p_149695_1_.field_72995_K)
        func_149697_b(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, l, 0); 
    } 
  }
  
  private void setBounds() {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
  }
  
  public static boolean isBlockHeadOfBed(int par0) {
    return ((par0 & 0x8) != 0);
  }
  
  public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
    if (par6EntityPlayer.field_71075_bZ.field_75098_d && isBlockHeadOfBed(par5)) {
      int i1 = func_149895_l(par5);
      par2 -= footBlockToHeadBlockMap[i1][0];
      par4 -= footBlockToHeadBlockMap[i1][1];
      if (par1World.func_147439_a(par2, par3, par4) == this)
        par1World.func_147468_f(par2, par3, par4); 
    } 
  }
  
  private void func_149978_e() {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
  }
  
  public static boolean func_149976_c(int p_149976_0_) {
    return ((p_149976_0_ & 0x4) != 0);
  }
  
  public static void func_149979_a(World p_149979_0_, int p_149979_1_, int p_149979_2_, int p_149979_3_, boolean p_149979_4_) {
    int l = p_149979_0_.func_72805_g(p_149979_1_, p_149979_2_, p_149979_3_);
    if (p_149979_4_) {
      l |= 0x4;
    } else {
      l &= 0xFFFFFFFB;
    } 
    p_149979_0_.func_72921_c(p_149979_1_, p_149979_2_, p_149979_3_, l, 4);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public int func_149656_h() {
    return 1;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntitySleepingBag();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockSleepingBag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */