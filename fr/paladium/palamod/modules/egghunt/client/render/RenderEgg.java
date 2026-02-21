package fr.paladium.palamod.modules.egghunt.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderEgg implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    Tessellator tessellator = Tessellator.field_78398_a;
    int k = 0;
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    tessellator.func_78382_b();
    for (int l = 0; l < 8; l++) {
      byte b0 = 0;
      byte b1 = 1;
      if (l == 0)
        b0 = 2; 
      if (l == 1)
        b0 = 3; 
      if (l == 2)
        b0 = 4; 
      if (l == 3) {
        b0 = 5;
        b1 = 2;
      } 
      if (l == 4) {
        b0 = 6;
        b1 = 3;
      } 
      if (l == 5) {
        b0 = 7;
        b1 = 5;
      } 
      if (l == 6) {
        b0 = 6;
        b1 = 2;
      } 
      if (l == 7)
        b0 = 3; 
      float f5 = b0 / 16.0F;
      float f6 = 1.0F - k / 16.0F;
      float f7 = 1.0F - (k + b1) / 16.0F;
      k += b1;
      renderer.func_147782_a((0.5F - f5), f7, (0.5F - f5), (0.5F + f5), f6, (0.5F + f5));
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
      renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a(block, 0, metadata));
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a(block, 1, metadata));
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a(block, 2, metadata));
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a(block, 3, metadata));
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a(block, 4, metadata));
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, renderer
          .func_147787_a(block, 5, metadata));
    } 
    tessellator.func_78381_a();
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    boolean flag = false;
    int l = 0;
    for (int i1 = 0; i1 < 8; i1++) {
      byte b0 = 0;
      byte b1 = 1;
      if (i1 == 0)
        b0 = 2; 
      if (i1 == 1)
        b0 = 3; 
      if (i1 == 2)
        b0 = 4; 
      if (i1 == 3) {
        b0 = 5;
        b1 = 2;
      } 
      if (i1 == 4) {
        b0 = 6;
        b1 = 3;
      } 
      if (i1 == 5) {
        b0 = 7;
        b1 = 5;
      } 
      if (i1 == 6) {
        b0 = 6;
        b1 = 2;
      } 
      if (i1 == 7)
        b0 = 3; 
      float f = b0 / 16.0F;
      float f1 = 1.0F - l / 16.0F;
      float f2 = 1.0F - (l + b1) / 16.0F;
      l += b1;
      renderer.func_147782_a((0.5F - f), f2, (0.5F - f), (0.5F + f), f1, (0.5F + f));
      renderer.func_147784_q(block, x, y, z);
    } 
    flag = true;
    renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    return flag;
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }
  
  public int getRenderId() {
    return -27;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\client\render\RenderEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */