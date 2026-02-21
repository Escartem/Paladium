package fr.paladium.palamod.modules.paladium.common.blocks.fluids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockAngelicWater extends BlockFluidClassic implements IAsgardBlockFluidBase {
  @SideOnly(Side.CLIENT)
  protected IIcon stillIcon;
  
  @SideOnly(Side.CLIENT)
  protected IIcon flowingIcon;
  
  private int i = 0;
  
  public BlockAngelicWater(Fluid fluid, Material material) {
    super(fluid, material);
    func_149711_c(100.0F);
    func_149663_c("angelicwater");
  }
  
  public IIcon func_149691_a(int side, int meta) {
    return (side == 0 || side == 1) ? this.stillIcon : this.flowingIcon;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    this.stillIcon = register.func_94245_a("palamod:fluids/angelicwater_still");
    this.flowingIcon = register.func_94245_a("palamod:fluids/angelicwater_flowing");
  }
  
  public int canDisplacePre(Block block, IBlockAccess world, int x, int y, int z) {
    if (block instanceof fr.paladium.palamod.modules.pillage.common.blocks.tree.BirchWaterSapling)
      return -1; 
    if (block.func_149688_o().func_76224_d() && block instanceof BlockSulfuricWater)
      return 1; 
    return 0;
  }
  
  public int displaceIfPossiblePre(Block block, World world, int x, int y, int z) {
    if (block instanceof fr.paladium.palamod.modules.pillage.common.blocks.tree.BirchWaterSapling)
      return -1; 
    if (block.func_149688_o().func_76224_d() && block instanceof BlockSulfuricWater)
      return 1; 
    return 0;
  }
  
  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    super.func_149670_a(world, x, y, z, entity);
    if (world.field_72995_K || !(entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)entity;
    this.i++;
    if (player.func_110143_aJ() < player.func_110138_aP() && this.i >= 40) {
      this.i = 0;
      player.func_70691_i(1.0F);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\fluids\BlockAngelicWater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */