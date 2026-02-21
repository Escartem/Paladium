package fr.paladium.palamod.modules.back2future.client.renderer.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.tileentities.TileEntityNewBeacon;
import java.util.List;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityNewBeaconRenderer extends TileEntitySpecialRenderer {
  private static final ResourceLocation BEAM_TEXTURE = new ResourceLocation("textures/entity/beacon_beam.png");
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float partialTickTime) {
    TileEntityNewBeacon beacon = (TileEntityNewBeacon)tile;
    float f1 = beacon.func_146002_i();
    OpenGLHelper.alphaFunc(516, 0.1F);
    if (f1 > 0.0F) {
      Tessellator tessellator = Tessellator.field_78398_a;
      List<TileEntityNewBeacon.BeamSegment> list = beacon.getSegments();
      int j = 0;
      for (int i = 0; i < list.size(); i++) {
        TileEntityNewBeacon.BeamSegment beamsegment = list.get(i);
        int l = j + beamsegment.func_177264_c();
        func_147499_a(BEAM_TEXTURE);
        GL11.glTexParameterf(3553, 10242, 10497.0F);
        GL11.glTexParameterf(3553, 10243, 10497.0F);
        OpenGLHelper.disableLighting();
        OpenGLHelper.disableCull();
        OpenGLHelper.disableBlend();
        OpenGLHelper.depthMask(true);
        OpenGlHelper.func_148821_a(770, 1, 1, 0);
        float f2 = (float)tile.func_145831_w().func_82737_E() + partialTickTime;
        float f3 = -f2 * 0.2F - MathHelper.func_76141_d(-f2 * 0.1F);
        double d3 = f2 * 0.025D * -1.5D;
        tessellator.func_78382_b();
        double d4 = 0.2D;
        double d5 = 0.5D + Math.cos(d3 + 2.356194490192345D) * d4;
        double d6 = 0.5D + Math.sin(d3 + 2.356194490192345D) * d4;
        double d7 = 0.5D + Math.cos(d3 + 0.7853981633974483D) * d4;
        double d8 = 0.5D + Math.sin(d3 + 0.7853981633974483D) * d4;
        double d9 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * d4;
        double d10 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * d4;
        double d11 = 0.5D + Math.cos(d3 + 5.497787143782138D) * d4;
        double d12 = 0.5D + Math.sin(d3 + 5.497787143782138D) * d4;
        double d13 = 0.0D;
        double d14 = 1.0D;
        double d15 = (-1.0F + f3);
        double d16 = (beamsegment.func_177264_c() * f1) * 0.5D / d4 + d15;
        tessellator.func_78369_a(beamsegment.func_177263_b()[0], beamsegment.func_177263_b()[1], beamsegment
            .func_177263_b()[2], 0.125F);
        tessellator.func_78374_a(x + d5, y + l, z + d6, d14, d16);
        tessellator.func_78374_a(x + d5, y + j, z + d6, d14, d15);
        tessellator.func_78374_a(x + d7, y + j, z + d8, d13, d15);
        tessellator.func_78374_a(x + d7, y + l, z + d8, d13, d16);
        tessellator.func_78374_a(x + d11, y + l, z + d12, d14, d16);
        tessellator.func_78374_a(x + d11, y + j, z + d12, d14, d15);
        tessellator.func_78374_a(x + d9, y + j, z + d10, d13, d15);
        tessellator.func_78374_a(x + d9, y + l, z + d10, d13, d16);
        tessellator.func_78374_a(x + d7, y + l, z + d8, d14, d16);
        tessellator.func_78374_a(x + d7, y + j, z + d8, d14, d15);
        tessellator.func_78374_a(x + d11, y + j, z + d12, d13, d15);
        tessellator.func_78374_a(x + d11, y + l, z + d12, d13, d16);
        tessellator.func_78374_a(x + d9, y + l, z + d10, d14, d16);
        tessellator.func_78374_a(x + d9, y + j, z + d10, d14, d15);
        tessellator.func_78374_a(x + d5, y + j, z + d6, d13, d15);
        tessellator.func_78374_a(x + d5, y + l, z + d6, d13, d16);
        tessellator.func_78381_a();
        OpenGLHelper.enableBlend();
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        OpenGLHelper.depthMask(false);
        tessellator.func_78382_b();
        tessellator.func_78369_a(beamsegment.func_177263_b()[0], beamsegment.func_177263_b()[1], beamsegment
            .func_177263_b()[2], 0.125F);
        d3 = 0.2D;
        d4 = 0.2D;
        d5 = 0.8D;
        d6 = 0.2D;
        d7 = 0.2D;
        d8 = 0.8D;
        d9 = 0.8D;
        d10 = 0.8D;
        d11 = 0.0D;
        d12 = 1.0D;
        d13 = (-1.0F + f3);
        d14 = (beamsegment.func_177264_c() * f1) + d13;
        tessellator.func_78374_a(x + d3, y + l, z + d4, d12, d14);
        tessellator.func_78374_a(x + d3, y + j, z + d4, d12, d13);
        tessellator.func_78374_a(x + d5, y + j, z + d6, d11, d13);
        tessellator.func_78374_a(x + d5, y + l, z + d6, d11, d14);
        tessellator.func_78374_a(x + d9, y + l, z + d10, d12, d14);
        tessellator.func_78374_a(x + d9, y + j, z + d10, d12, d13);
        tessellator.func_78374_a(x + d7, y + j, z + d8, d11, d13);
        tessellator.func_78374_a(x + d7, y + l, z + d8, d11, d14);
        tessellator.func_78374_a(x + d5, y + l, z + d6, d12, d14);
        tessellator.func_78374_a(x + d5, y + j, z + d6, d12, d13);
        tessellator.func_78374_a(x + d9, y + j, z + d10, d11, d13);
        tessellator.func_78374_a(x + d9, y + l, z + d10, d11, d14);
        tessellator.func_78374_a(x + d7, y + l, z + d8, d12, d14);
        tessellator.func_78374_a(x + d7, y + j, z + d8, d12, d13);
        tessellator.func_78374_a(x + d3, y + j, z + d4, d11, d13);
        tessellator.func_78374_a(x + d3, y + l, z + d4, d11, d14);
        tessellator.func_78381_a();
        OpenGLHelper.enableLighting();
        OpenGLHelper.enableTexture2D();
        OpenGLHelper.depthMask(true);
        j = l;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\tileentity\TileEntityNewBeaconRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */