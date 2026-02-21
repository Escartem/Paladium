package fr.paladium.palamod.modules.back2future.client.renderer.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.client.renderer.tileentity.TileEntityEndRodRenderer;
import fr.paladium.palamod.modules.back2future.lib.RenderIDs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class BlockEndRodRender implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
    OpenGLHelper.translate(-0.5F, -0.5F, -0.5F);
    TileEntityEndRodRenderer.renderRod(renderer, block, meta);
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    return false;
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }
  
  public int getRenderId() {
    return RenderIDs.END_ROD;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\block\BlockEndRodRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */