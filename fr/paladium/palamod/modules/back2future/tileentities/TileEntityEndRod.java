package fr.paladium.palamod.modules.back2future.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityEndRod extends TileEntity {
  public boolean canUpdate() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }
  
  @SideOnly(Side.CLIENT)
  public double func_145833_n() {
    return Double.MAX_VALUE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\tileentities\TileEntityEndRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */