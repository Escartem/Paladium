package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class InvertedDaylightDetector extends NewDaylightSensor {
  private static final int[] invertedValues = new int[] { 
      15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 
      5, 4, 3, 2, 1, 0 };
  
  public InvertedDaylightDetector() {
    func_149658_d("daylight_detector_inverted_top");
    func_149663_c(Utils.getUnlocalisedName("daylight_detector_inverted"));
  }
  
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K)
      world.func_147449_b(x, y, z, ModBlocks.daylight_sensor); 
    return true;
  }
  
  public void func_149957_e(World world, int x, int y, int z) {
    if (!world.field_73011_w.field_76576_e) {
      int meta = world.func_72805_g(x, y, z);
      int light = world.func_72972_b(EnumSkyBlock.Sky, x, y, z) - world.field_73008_k;
      float angle = world.func_72929_e(1.0F);
      if (angle < 3.1415927F) {
        angle += (0.0F - angle) * 0.2F;
      } else {
        angle += (6.2831855F - angle) * 0.2F;
      } 
      light = Math.round(light * MathHelper.func_76134_b(angle));
      if (light < 0)
        light = 0; 
      if (light > 15)
        light = 15; 
      light = invertedValues[light];
      if (meta != light)
        world.func_72921_c(x, y, z, light, 3); 
    } 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return (side == 1) ? this.field_149761_L : Blocks.field_150453_bW.func_149691_a(side, meta);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    this.field_149761_L = reg.func_94245_a(func_149641_N());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\InvertedDaylightDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */