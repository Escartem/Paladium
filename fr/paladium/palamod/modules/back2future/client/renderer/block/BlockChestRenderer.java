package fr.paladium.palamod.modules.back2future.client.renderer.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class BlockChestRenderer implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    return (renderer.func_147744_b() && renderer.func_147784_q(block, x, y, z));
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return false;
  }
  
  public int getRenderId() {
    return Blocks.field_150486_ae.func_149645_b();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\block\BlockChestRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */