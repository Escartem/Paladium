package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.lib.RenderIDs;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityEndRod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class EndRod extends BlockContainer implements IConfigurable {
  public EndRod() {
    super(Material.field_151576_e);
    func_149711_c(0.0F);
    func_149715_a(0.9375F);
    func_149658_d("end_rod");
    func_149663_c(Utils.getUnlocalisedName("end_rod"));
    func_149676_a(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
    func_149647_a(Back2Future.enableChorusFruit ? Back2Future.creativeTab : null);
  }
  
  public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
    return !(entity instanceof net.minecraft.entity.boss.EntityDragon);
  }
  
  public void func_149719_a(IBlockAccess world, int x, int y, int z) {
    ForgeDirection dir = ForgeDirection.getOrientation(world.func_72805_g(x, y, z));
    if (dir == ForgeDirection.DOWN || dir == ForgeDirection.UP) {
      func_149676_a(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
    } else if (dir == ForgeDirection.WEST || dir == ForgeDirection.EAST) {
      func_149676_a(0.0F, 0.375F, 0.375F, 1.0F, 0.625F, 0.625F);
    } else if (dir == ForgeDirection.NORTH || dir == ForgeDirection.SOUTH) {
      func_149676_a(0.375F, 0.375F, 0.0F, 0.625F, 0.625F, 1.0F);
    } 
  }
  
  public int func_149645_b() {
    return RenderIDs.END_ROD;
  }
  
  public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    ForgeDirection dir = ForgeDirection.getOrientation(side).getOpposite();
    if (world.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) != this)
      dir = dir.getOpposite(); 
    return dir.ordinal();
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityEndRod();
  }
  
  public boolean isEnabled() {
    return Back2Future.enableChorusFruit;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\EndRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */