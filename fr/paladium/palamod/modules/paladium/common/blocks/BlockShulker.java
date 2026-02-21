package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockShulker extends Block {
  @SideOnly(Side.CLIENT)
  private IIcon icons_faces;
  
  @SideOnly(Side.CLIENT)
  private IIcon icons_top;
  
  @SideOnly(Side.CLIENT)
  private IIcon icons_left;
  
  @SideOnly(Side.CLIENT)
  private IIcon icons_back;
  
  @SideOnly(Side.CLIENT)
  private IIcon icons_right;
  
  public int variante;
  
  private boolean left = false;
  
  private boolean right = false;
  
  private boolean back = false;
  
  public BlockShulker(int variante) {
    super(Material.field_151573_f);
    this.variante = variante;
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c("shulker_" + variante);
    func_149658_d("palamod:shulker/shulker");
  }
  
  public BlockShulker(int variante, boolean left, boolean right, boolean back) {
    super(Material.field_151573_f);
    this.variante = variante;
    this.back = back;
    this.right = right;
    this.left = left;
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c("shulker_" + variante);
    func_149658_d("palamod:shulker/shulker");
  }
  
  public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
    int l = MathHelper.func_76128_c((p_149689_5_.field_70177_z * 4.0F / 360.0F) + 2.5D) & 0x3;
    p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);
  }
  
  public void func_149651_a(IIconRegister register) {
    this.icons_top = register.func_94245_a(func_149641_N() + "_" + this.variante + "_top");
    this.field_149761_L = register.func_94245_a(func_149641_N() + "_" + this.variante + "_side");
    if (this.back)
      this.icons_back = register.func_94245_a(func_149641_N() + "_" + this.variante + "_back"); 
    if (this.right)
      this.icons_right = register.func_94245_a(func_149641_N() + "_" + this.variante + "_right"); 
    if (this.left)
      this.icons_left = register.func_94245_a(func_149641_N() + "_" + this.variante + "_left"); 
    this.icons_faces = register.func_94245_a(func_149641_N() + "_" + this.variante + "_face");
  }
  
  public IIcon func_149691_a(int side, int meta) {
    if (side == 1)
      return this.icons_top; 
    if (side == 0)
      return this.icons_top; 
    if ((meta == 2 && side == 2) || (meta == 3 && side == 5) || (meta == 0 && side == 3) || (meta == 1 && side == 4))
      return this.icons_faces; 
    if (this.left && ((meta == 2 && side == 5) || (meta == 3 && side == 4) || (meta == 0 && side == 2) || (meta == 1 && side == 3)))
      return this.icons_left; 
    if (this.right && ((meta == 2 && side == 3) || (meta == 3 && side == 2) || (meta == 0 && side == 4) || (meta == 1 && side == 5)))
      return this.icons_right; 
    if (this.back && ((meta == 2 && side == 4) || (meta == 3 && side == 3) || (meta == 0 && side == 5) || (meta == 1 && side == 2)))
      return this.icons_back; 
    return this.field_149761_L;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockShulker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */