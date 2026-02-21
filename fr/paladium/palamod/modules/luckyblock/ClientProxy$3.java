package fr.paladium.palamod.modules.luckyblock;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.paladium.palamod.modules.luckyblock.blocks.halloween.BlockHopperHalloween;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

class null implements ISimpleBlockRenderingHandler {
  public boolean shouldRender3DInInventory(int modelId) {
    return false;
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    ClientProxy.this.renderBlockPaladiumHopper(renderer, (BlockHopperHalloween)block, x, y, z);
    return true;
  }
  
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
  
  public int getRenderId() {
    return ClientProxy.renderHopperHalloween;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\ClientProxy$3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */