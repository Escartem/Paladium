package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDirectional;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockCorruptedBed extends BlockBed implements ITooltipInformations {
  public static final int[][] field_149981_a = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
  
  @SideOnly(Side.CLIENT)
  private IIcon[] field_149980_b;
  
  @SideOnly(Side.CLIENT)
  private IIcon[] field_149982_M;
  
  @SideOnly(Side.CLIENT)
  private IIcon[] field_149983_N;
  
  private static final String DESCRIPTION = "DESC.TXT : ITEM:BED que ne peut être ACTION:USE que dans le WORLD:NETHER. ACTION:EXPLODE si ACTION:USE dans le WORLD:OVERWORLD.";
  
  private static final String WARNING_MESSAGE = "&aCe lit est corrompu, mais pas à ce point !";
  
  public BlockCorruptedBed() {
    func_149647_a(PLuckyBlock.TAB);
    func_149711_c(0.2F);
    func_149663_c("corrupted_bed");
    func_149658_d("palamod:corrupted_bed");
    func_149649_H();
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    if (world.field_72995_K)
      return true; 
    if (world.func_72807_a(x, z) != BiomeGenBase.field_76778_j) {
      explode(world, x, y, z);
      return true;
    } 
    MonthlyUtils.sendMessage(player, new String[] { "&aCe lit est corrompu, mais pas à ce point !" });
    PaladiumScheduler.INSTANCE.runTaskLater(() -> {
          if (world.func_147439_a(x, y, z) == this)
            explode(world, x, y, z); 
        }100L);
    return true;
  }
  
  public void explode(World world, int x, int y, int z) {
    double d2 = x + 0.5D;
    double d0 = y + 0.5D;
    double d1 = z + 0.5D;
    int i1 = world.func_72805_g(x, y, z);
    int k1 = func_149895_l(i1);
    world.func_147468_f(x, y, z);
    x += field_149981_a[k1][0];
    z += field_149981_a[k1][1];
    if (world.func_147439_a(x, y, z) == this) {
      world.func_147468_f(x, y, z);
      d2 = (d2 + x + 0.5D) / 2.0D;
      d0 = (d0 + y + 0.5D) / 2.0D;
      d1 = (d1 + z + 0.5D) / 2.0D;
    } 
    world.func_72885_a((Entity)null, (x + 0.5F), (y + 0.5F), (z + 0.5F), 5.0F, true, true);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
    if (p_149691_1_ == 0)
      return Blocks.field_150344_f.func_149733_h(p_149691_1_); 
    int k = func_149895_l(p_149691_2_);
    int l = Direction.field_71584_h[k][p_149691_1_];
    int i1 = isBlockHeadOfBed(p_149691_2_) ? 1 : 0;
    return ((i1 != 1 || l != 2) && (i1 != 0 || l != 3)) ? ((l != 5 && l != 4) ? this.field_149983_N[i1] : this.field_149982_M[i1]) : this.field_149980_b[i1];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    this.field_149983_N = new IIcon[] { p_149651_1_.func_94245_a(func_149641_N() + "_feet_top"), p_149651_1_.func_94245_a(func_149641_N() + "_head_top") };
    this.field_149980_b = new IIcon[] { p_149651_1_.func_94245_a(func_149641_N() + "_feet_end"), p_149651_1_.func_94245_a(func_149641_N() + "_head_end") };
    this.field_149982_M = new IIcon[] { p_149651_1_.func_94245_a(func_149641_N() + "_feet_side"), p_149651_1_.func_94245_a(func_149641_N() + "_head_side") };
  }
  
  public int func_149645_b() {
    return 14;
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
    func_149978_e();
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
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return isBlockHeadOfBed(p_149650_1_) ? Item.func_150899_d(0) : ItemsRegister.ITEM_CORRUPTED_BED;
  }
  
  private void func_149978_e() {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
  }
  
  public static boolean isBlockHeadOfBed(int p_149975_0_) {
    return ((p_149975_0_ & 0x8) != 0);
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
  
  public static ChunkCoordinates func_149977_a(World p_149977_0_, int p_149977_1_, int p_149977_2_, int p_149977_3_, int p_149977_4_) {
    int i1 = p_149977_0_.func_72805_g(p_149977_1_, p_149977_2_, p_149977_3_);
    int j1 = BlockDirectional.func_149895_l(i1);
    for (int k1 = 0; k1 <= 1; k1++) {
      int l1 = p_149977_1_ - field_149981_a[j1][0] * k1 - 1;
      int i2 = p_149977_3_ - field_149981_a[j1][1] * k1 - 1;
      int j2 = l1 + 2;
      int k2 = i2 + 2;
      for (int l2 = l1; l2 <= j2; l2++) {
        for (int i3 = i2; i3 <= k2; i3++) {
          if (World.func_147466_a((IBlockAccess)p_149977_0_, l2, p_149977_2_ - 1, i3) && !p_149977_0_.func_147439_a(l2, p_149977_2_, i3).func_149688_o().func_76218_k() && !p_149977_0_.func_147439_a(l2, p_149977_2_ + 1, i3).func_149688_o().func_76218_k()) {
            if (p_149977_4_ <= 0)
              return new ChunkCoordinates(l2, p_149977_2_, i3); 
            p_149977_4_--;
          } 
        } 
      } 
    } 
    return null;
  }
  
  public void func_149690_a(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
    if (!isBlockHeadOfBed(p_149690_5_))
      super.func_149690_a(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, 0); 
  }
  
  public int func_149656_h() {
    return 1;
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
    return Items.field_151104_aV;
  }
  
  public void func_149681_a(World p_149681_1_, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_) {
    if (p_149681_6_.field_71075_bZ.field_75098_d && isBlockHeadOfBed(p_149681_5_)) {
      int i1 = func_149895_l(p_149681_5_);
      p_149681_2_ -= field_149981_a[i1][0];
      p_149681_4_ -= field_149981_a[i1][1];
      if (p_149681_1_.func_147439_a(p_149681_2_, p_149681_3_, p_149681_4_) == this)
        p_149681_1_.func_147468_f(p_149681_2_, p_149681_3_, p_149681_4_); 
    } 
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("DESC.TXT : ITEM:BED que ne peut être ACTION:USE que dans le WORLD:NETHER. ACTION:EXPLODE si ACTION:USE dans le WORLD:OVERWORLD.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\blocks\BlockCorruptedBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */