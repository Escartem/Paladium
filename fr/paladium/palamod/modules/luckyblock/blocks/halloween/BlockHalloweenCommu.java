package fr.paladium.palamod.modules.luckyblock.blocks.halloween;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockHalloweenCommu extends BlockDirectional {
  private final boolean isLight;
  
  @SideOnly(Side.CLIENT)
  private IIcon icons_faces;
  
  @SideOnly(Side.CLIENT)
  private IIcon icons_top;
  
  public BlockHalloweenCommu(boolean isLight) {
    super(Material.field_151585_k);
    func_149658_d("palamod:halloween_commu");
    func_149663_c("halloween_commu" + (isLight ? "_on" : ""));
    func_149711_c(6.0F);
    if (!isLight)
      func_149647_a(PLuckyBlock.TAB); 
    this.isLight = isLight;
    if (isLight)
      func_149715_a(1.0F); 
  }
  
  public void func_149726_b(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
    super.func_149726_b(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
    if (!p_149726_1_.field_72995_K)
      if (this.isLight && 
        !p_149726_1_.func_72864_z(p_149726_2_, p_149726_3_, p_149726_4_)) {
        p_149726_1_.func_147464_a(p_149726_2_, p_149726_3_, p_149726_4_, (Block)this, 4);
      } else if (!this.isLight && p_149726_1_
        .func_72864_z(p_149726_2_, p_149726_3_, p_149726_4_)) {
        p_149726_1_.func_147465_d(p_149726_2_, p_149726_3_, p_149726_4_, BlocksRegister.HALLOWEEN_COMMU_ON, p_149726_1_
            
            .func_72805_g(p_149726_2_, p_149726_3_, p_149726_4_), 2);
      }  
  }
  
  public void func_149695_a(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
    if (!p_149695_1_.field_72995_K)
      if (this.isLight && 
        !p_149695_1_.func_72864_z(p_149695_2_, p_149695_3_, p_149695_4_)) {
        p_149695_1_.func_147464_a(p_149695_2_, p_149695_3_, p_149695_4_, (Block)this, 4);
      } else if (!this.isLight && p_149695_1_
        .func_72864_z(p_149695_2_, p_149695_3_, p_149695_4_)) {
        p_149695_1_.func_147465_d(p_149695_2_, p_149695_3_, p_149695_4_, BlocksRegister.HALLOWEEN_COMMU_ON, p_149695_1_
            
            .func_72805_g(p_149695_2_, p_149695_3_, p_149695_4_), 2);
      }  
  }
  
  public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
    if (!p_149674_1_.field_72995_K && this.isLight && 
      !p_149674_1_.func_72864_z(p_149674_2_, p_149674_3_, p_149674_4_))
      p_149674_1_.func_147465_d(p_149674_2_, p_149674_3_, p_149674_4_, BlocksRegister.HALLOWEEN_COMMU, p_149674_1_
          .func_72805_g(p_149674_2_, p_149674_3_, p_149674_4_), 2); 
  }
  
  public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    int l = MathHelper.func_76128_c((p_149689_5_.field_70177_z * 4.0F / 360.0F) + 2.5D) & 0x3;
    p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.func_150898_a(BlocksRegister.HALLOWEEN_COMMU);
  }
  
  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
    return Item.func_150898_a(BlocksRegister.HALLOWEEN_COMMU);
  }
  
  protected ItemStack func_149644_j(int p_149644_1_) {
    return new ItemStack(BlocksRegister.HALLOWEEN_COMMU);
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons_top = register.func_94245_a(func_149641_N() + "_top");
    this.field_149761_L = register.func_94245_a(func_149641_N() + "_side");
    this
      .icons_faces = register.func_94245_a(func_149641_N() + "_" + (this.isLight ? "on" : "off"));
  }
  
  public IIcon func_149691_a(int side, int meta) {
    return (side == 1) ? this.icons_top : ((side == 0) ? this.icons_top : ((meta == 2 && side == 2) ? this.icons_faces : ((meta == 3 && side == 5) ? this.icons_faces : ((meta == 0 && side == 3) ? this.icons_faces : ((meta == 1 && side == 4) ? this.icons_faces : this.field_149761_L)))));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\halloween\BlockHalloweenCommu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */