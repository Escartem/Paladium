package fr.paladium.palamod.modules.back2future.client.renderer.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.lib.RenderIDs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class BlockSlimeBlockRender implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
    Tessellator tessellator = Tessellator.field_78398_a;
    OpenGLHelper.blendFunc(770, 771);
    OpenGLHelper.enableBlend();
    OpenGLHelper.translate(-0.5F, -0.5F, -0.5F);
    float f = 0.0625F;
    renderer.func_147782_a((f * 3.0F), (f * 3.0F), (f * 3.0F), (f * 13.0F), (f * 13.0F), (f * 13.0F));
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 0, meta));
    tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 1, meta));
    tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 2, meta));
    tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 3, meta));
    tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 4, meta));
    tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 5, meta));
    tessellator.func_78381_a();
    renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 0, meta));
    tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 1, meta));
    tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 2, meta));
    tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 3, meta));
    tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 4, meta));
    tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, renderer
        .func_147787_a(block, 5, meta));
    tessellator.func_78381_a();
    OpenGLHelper.translate(0.5F, 0.5F, 0.5F);
    OpenGLHelper.disableBlend();
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    renderer.func_147784_q(ModBlocks.slime, x, y, z);
    float f = 0.0625F;
    renderer.func_147782_a((f * 3.0F), (f * 3.0F), (f * 3.0F), (f * 13.0F), (f * 13.0F), (f * 13.0F));
    renderer.field_147837_f = true;
    boolean flag = renderer.func_147784_q(ModBlocks.slime, x, y, z);
    renderer.field_147837_f = false;
    return flag;
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }
  
  public int getRenderId() {
    return RenderIDs.SLIME_BLOCK;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\block\BlockSlimeBlockRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */