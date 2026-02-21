package fr.paladium.palamod.modules.back2future.client.renderer.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

@SideOnly(Side.CLIENT)
public class TileEntityEndRodRenderer extends TileEntitySpecialRenderer {
  private RenderBlocks renderer = new RenderBlocks();
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float partialTicks) {
    OpenGLHelper.pushMatrix();
    OpenGLHelper.translate(x, y, z);
    OpenGLHelper.colour(16777215);
    func_147499_a(TextureMap.field_110575_b);
    ForgeDirection dir = ForgeDirection.getOrientation(tile.func_145832_p());
    switch (dir) {
      case DOWN:
        OpenGLHelper.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        OpenGLHelper.translate(0.0F, -1.0F, -1.0F);
        break;
      case EAST:
        OpenGLHelper.rotate(270.0F, 0.0F, 0.0F, 1.0F);
        OpenGLHelper.translate(-1.0F, 0.0F, 0.0F);
        break;
      case NORTH:
        OpenGLHelper.rotate(270.0F, 1.0F, 0.0F, 0.0F);
        OpenGLHelper.translate(0.0F, -1.0F, 0.0F);
        break;
      case SOUTH:
        OpenGLHelper.rotate(90.0F, 1.0F, 0.0F, 0.0F);
        OpenGLHelper.translate(0.0F, 0.0F, -1.0F);
        break;
      case WEST:
        OpenGLHelper.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        OpenGLHelper.translate(0.0F, -1.0F, 0.0F);
        break;
    } 
    renderRod(this.renderer, tile.func_145838_q(), tile.func_145832_p());
    OpenGLHelper.popMatrix();
  }
  
  public static void renderRod(RenderBlocks renderer, Block block, int meta) {
    Tessellator tessellator = Tessellator.field_78398_a;
    double x = 0.4375D;
    double y = 0.0D;
    double z = 0.4375D;
    renderer.func_147782_a(0.0D, 0.0625D, 0.0D, 0.125D, 1.0D, 0.125D);
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, x, y, z, renderer
        .func_147787_a(block, 0, meta));
    tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, x, y, z, renderer
        .func_147787_a(block, 1, meta));
    tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147761_c(block, x, y, z, renderer
        .func_147787_a(block, 2, meta));
    tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147734_d(block, x, y, z, renderer
        .func_147787_a(block, 3, meta));
    tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147798_e(block, x, y, z, renderer
        .func_147787_a(block, 4, meta));
    tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147764_f(block, x, y, z, renderer
        .func_147787_a(block, 5, meta));
    tessellator.func_78381_a();
    x = 0.25D;
    y = 0.0D;
    z = 0.25D;
    renderer.func_147782_a(0.125D, 0.0D, 0.125D, 0.375D, 0.0625D, 0.375D);
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, x, y, z, renderer
        .func_147787_a(block, 0, meta));
    tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, x, y, z, renderer
        .func_147787_a(block, 1, meta));
    y = -0.8125D;
    renderer.func_147782_a(0.125D, 0.8125D, 0.125D, 0.375D, 0.875D, 0.375D);
    tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147761_c(block, x, y, z, renderer
        .func_147787_a(block, 2, meta));
    tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147734_d(block, x, y, z, renderer
        .func_147787_a(block, 3, meta));
    tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147798_e(block, x, y, z, renderer
        .func_147787_a(block, 4, meta));
    tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147764_f(block, x, y, z, renderer
        .func_147787_a(block, 5, meta));
    tessellator.func_78381_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\tileentity\TileEntityEndRodRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */