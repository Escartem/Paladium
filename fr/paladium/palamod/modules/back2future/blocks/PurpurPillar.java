package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class PurpurPillar extends BlockRotatedPillar implements IConfigurable {
  public PurpurPillar() {
    super(Material.field_151576_e);
    func_149711_c(1.5F);
    func_149752_b(10.0F);
    func_149672_a(field_149780_i);
    func_149658_d("purpur_pillar");
    func_149663_c(Utils.getUnlocalisedName("purpur_pillar"));
    func_149647_a(Back2Future.enableChorusFruit ? Back2Future.creativeTab : null);
  }
  
  public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
    return !(entity instanceof net.minecraft.entity.boss.EntityDragon);
  }
  
  @SideOnly(Side.CLIENT)
  protected IIcon func_150163_b(int side) {
    return this.field_149761_L;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    super.func_149651_a(reg);
    this.field_150164_N = reg.func_94245_a(func_149641_N() + "_top");
  }
  
  public boolean isEnabled() {
    return Back2Future.enableChorusFruit;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\PurpurPillar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */