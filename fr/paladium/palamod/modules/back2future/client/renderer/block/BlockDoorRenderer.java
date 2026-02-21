package fr.paladium.palamod.modules.back2future.client.renderer.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.lib.RenderIDs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class BlockDoorRenderer implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    Tessellator tessellator = Tessellator.field_78398_a;
    int meta = world.func_72805_g(x, y, z);
    if ((meta & 0x8) != 0) {
      if (world.func_147439_a(x, y - 1, z) != block)
        return false; 
    } else if (world.func_147439_a(x, y + 1, z) != block) {
      return false;
    } 
    int brightness = block.func_149677_c(world, x, y, z);
    tessellator.func_78380_c((renderer.field_147855_j > 0.0D) ? brightness : block
        .func_149677_c(world, x, y - 1, z));
    tessellator.func_78386_a(0.5F, 0.5F, 0.5F);
    renderer.func_147768_a(block, x, y, z, renderer.func_147793_a(block, world, x, y, z, 0));
    if ((meta & 0x8) != 0) {
      tessellator.func_78380_c((renderer.field_147857_k < 1.0D) ? brightness : block
          .func_149677_c(world, x, y + 1, z));
      tessellator.func_78386_a(1.0F, 1.0F, 1.0F);
      renderer.field_147867_u = 2;
      renderer.func_147806_b(block, x, y, z, renderer.func_147793_a(block, world, x, y, z, 1));
      renderer.field_147867_u = 0;
    } 
    tessellator.func_78380_c((renderer.field_147851_l > 0.0D) ? brightness : block
        .func_149677_c(world, x, y, z - 1));
    tessellator.func_78386_a(0.8F, 0.8F, 0.8F);
    renderer.func_147761_c(block, x, y, z, renderer.func_147793_a(block, world, x, y, z, 2));
    renderer.field_147842_e = false;
    tessellator.func_78380_c((renderer.field_147853_m < 1.0D) ? brightness : block
        .func_149677_c(world, x, y, z + 1));
    tessellator.func_78386_a(0.8F, 0.8F, 0.8F);
    renderer.func_147734_d(block, x, y, z, renderer.func_147793_a(block, world, x, y, z, 3));
    renderer.field_147842_e = false;
    tessellator.func_78380_c((renderer.field_147859_h > 0.0D) ? brightness : block
        .func_149677_c(world, x - 1, y, z));
    tessellator.func_78386_a(0.6F, 0.6F, 0.6F);
    renderer.func_147798_e(block, x, y, z, renderer.func_147793_a(block, world, x, y, z, 4));
    renderer.field_147842_e = false;
    tessellator.func_78380_c((renderer.field_147861_i < 1.0D) ? brightness : block
        .func_149677_c(world, x + 1, y, z));
    tessellator.func_78386_a(0.6F, 0.6F, 0.6F);
    renderer.func_147764_f(block, x, y, z, renderer.func_147793_a(block, world, x, y, z, 5));
    renderer.field_147842_e = false;
    return true;
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return false;
  }
  
  public int getRenderId() {
    return RenderIDs.DOOR;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\block\BlockDoorRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */