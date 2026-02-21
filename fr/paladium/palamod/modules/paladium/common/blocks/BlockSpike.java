package fr.paladium.palamod.modules.paladium.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.registerer.PRegister_Renderer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSpike extends Block implements ITooltipWiki {
  protected String field_149770_b;
  
  String texture1;
  
  float damage;
  
  private IIcon icon;
  
  public BlockSpike(String unlocalizedName, String name, String texture, String texture1, float damage, boolean isResistant) {
    super(Material.field_151573_f);
    func_149752_b(8.0F);
    setHarvestLevel("pickaxe", 2);
    func_149663_c(name);
    func_149711_c(12.0F);
    func_149658_d("palamod:" + texture);
    if (isResistant) {
      func_149752_b(100.0F);
    } else {
      func_149752_b(1.0F);
    } 
    this.texture1 = texture1;
    this.damage = damage;
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return PRegister_Renderer.renderBlockSpikeId;
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity target) {
    if (world.field_72995_K || !(world instanceof net.minecraft.world.WorldServer))
      return; 
    target.func_70097_a(DamageSource.field_76367_g, this.damage);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public void func_149651_a(IIconRegister icons) {
    super.func_149651_a(icons);
    this.icon = icons.func_94245_a("palamod:" + this.texture1);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2) {
    int side = par2 % 6;
    if (par1 == Facing.field_71588_a[side])
      return this.field_149761_L; 
    return this.icon;
  }
  
  public int func_149660_a(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
    int meta = par5 % 6;
    int flag = 0;
    ForgeDirection side = ForgeDirection.getOrientation(meta);
    if (!par1World.isSideSolid(par2 - side.offsetX, par3 - side.offsetY, par4 - side.offsetZ, side
        .getOpposite()))
      for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
        if (side != dir) {
          if (par1World.isSideSolid(par2 - dir.offsetX, par3 - dir.offsetY, par4 - dir.offsetZ, dir
              .getOpposite()))
            return flag + dir.ordinal(); 
          if (par1World.func_147439_a(par2 - dir.offsetX, par3 - dir.offsetY, par4 - dir.offsetZ) == this)
            par5 = par1World.func_72805_g(par2 - dir.offsetX, par3 - dir.offsetY, par4 - dir.offsetZ) % 6; 
        } 
      }  
    return flag + par5;
  }
  
  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
    double h = 0.0625D;
    int side = par1World.func_72805_g(par2, par3, par4) % 6;
    switch (side) {
      case 0:
        return AxisAlignedBB.func_72330_a(par2 + h, par3 + h, par4 + h, par2 + 1.0D - h, par3 + 1.0D, par4 + 1.0D - h);
      case 1:
        return AxisAlignedBB.func_72330_a(par2 + h, par3, par4 + h, par2 + 1.0D - h, par3 + 1.0D - h, par4 + 1.0D - h);
      case 2:
        return AxisAlignedBB.func_72330_a(par2 + h, par3 + h, par4 + h, par2 + 1.0D - h, par3 + 1.0D - h, par4 + 1.0D);
      case 3:
        return AxisAlignedBB.func_72330_a(par2 + h, par3 + h, par4, par2 + 1.0D - h, par3 + 1.0D - h, par4 + 1.0D - h);
      case 4:
        return AxisAlignedBB.func_72330_a(par2 + h, par3 + h, par4 + h, par2 + 1.0D, par3 + 1.0D - h, par4 + 1.0D - h);
      case 5:
        return AxisAlignedBB.func_72330_a(par2, par3 + h, par4 + h, par2 + 1.0D - h, par3 + 1.0D - h, par4 + 1.0D - h);
    } 
    return AxisAlignedBB.func_72330_a(par2 + h, par3 + h, par4 + h, par2 + 1.0D - h, par3 + 1.0D - h, par4 + 1.0D - h);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-la-construction#2.-les-spikes";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockSpike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */