package fr.paladium.palamod.modules.miner.proxy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.paladium.palamod.modules.miner.blocks.BlockPaladiumHopper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

class null implements ISimpleBlockRenderingHandler {
  public boolean shouldRender3DInInventory(int modelId) {
    return false;
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    ClientProxy.this.renderBlockPaladiumHopper(renderer, (BlockPaladiumHopper)block, x, y, z);
    return true;
  }
  
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
  
  public int getRenderId() {
    return ClientProxy.renderPaladiumHopper;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\proxy\ClientProxy$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */