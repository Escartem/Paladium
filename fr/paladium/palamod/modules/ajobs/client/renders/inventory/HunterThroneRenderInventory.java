package fr.paladium.palamod.modules.ajobs.client.renders.inventory;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.paladium.palamod.modules.ajobs.client.JClientProxy;
import fr.paladium.palamod.modules.ajobs.client.renders.blocks.TileEntityHunterThroneRenderer;
import fr.paladium.palamod.modules.ajobs.common.registerer.BlocksRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class HunterThroneRenderInventory implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
    float scale = 0.55F;
    if (block == BlocksRegistry.HUNTER_THRONE) {
      GL11.glPushMatrix();
      GL11.glScalef(scale, scale, scale);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      GL11.glTranslatef(0.0F, -0.1F, 0.0F);
      Minecraft.func_71410_x().func_110434_K().func_110577_a(TileEntityHunterThroneRenderer.getTexture());
      TileEntityHunterThroneRenderer.getModel().renderAll();
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
    return JClientProxy.renderHunterThrone;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\client\renders\inventory\HunterThroneRenderInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */