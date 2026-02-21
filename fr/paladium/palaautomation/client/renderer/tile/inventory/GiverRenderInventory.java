package fr.paladium.palaautomation.client.renderer.tile.inventory;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.paladium.palaautomation.client.ClientProxy;
import fr.paladium.palaautomation.client.renderer.tile.TileEntityGiverRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class GiverRenderInventory implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
    if (block instanceof fr.paladium.palaautomation.common.block.BlockGiver) {
      GL11.glPushMatrix();
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      GL11.glTranslatef(0.0F, -1.0F, 0.0F);
      Minecraft.func_71410_x().func_110434_K().func_110577_a(TileEntityGiverRenderer.TEXTURE);
      TileEntityGiverRenderer.MODEL.renderAll();
      GL11.glPopMatrix();
    } 
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    return false;
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }
  
  public int getRenderId() {
    return ClientProxy.renderGiverId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\client\renderer\tile\inventory\GiverRenderInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */