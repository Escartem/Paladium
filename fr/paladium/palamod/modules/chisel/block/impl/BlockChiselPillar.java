package fr.paladium.palamod.modules.chisel.block.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.chisel.block.BlockChisel;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockChiselPillar extends BlockChisel {
  private final String texturesPath;
  
  private final String unlocalizedName;
  
  private IIcon topIcon;
  
  public BlockChiselPillar(Material material, float hardness, String texturesPath, String unlocalizedName) {
    super(material, hardness);
    this.texturesPath = texturesPath;
    this.unlocalizedName = unlocalizedName;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2) {
    int side = par2 % 6;
    if (par1 == Facing.field_71588_a[side] || par1 == Facing.field_71588_a[Facing.field_71588_a[side]])
      return this.topIcon; 
    return this.field_149761_L;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iiconRegister) {
    this.field_149761_L = iiconRegister.func_94245_a("palamod:" + this.texturesPath + "/" + this.unlocalizedName + "_side");
    this.topIcon = iiconRegister.func_94245_a("palamod:" + this.texturesPath + "/" + this.unlocalizedName + "_top");
  }
  
  public int func_149660_a(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
    int meta = par5 % 6;
    int flag = 0;
    ForgeDirection side = ForgeDirection.getOrientation(meta);
    if (!par1World.isSideSolid(par2 - side.offsetX, par3 - side.offsetY, par4 - side.offsetZ, side.getOpposite()))
      for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
        if (side != dir) {
          if (par1World.isSideSolid(par2 - dir.offsetX, par3 - dir.offsetY, par4 - dir.offsetZ, dir.getOpposite()))
            return 0 + dir.ordinal(); 
          if (par1World.func_147439_a(par2 - dir.offsetX, par3 - dir.offsetY, par4 - dir.offsetZ) == this)
            par5 = par1World.func_72805_g(par2 - dir.offsetX, par3 - dir.offsetY, par4 - dir.offsetZ) % 6; 
        } 
      }  
    return 0 + par5;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\block\impl\BlockChiselPillar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */