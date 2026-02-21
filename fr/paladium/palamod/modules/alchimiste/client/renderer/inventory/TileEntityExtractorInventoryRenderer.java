package fr.paladium.palamod.modules.alchimiste.client.renderer.inventory;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.paladium.palamod.modules.alchimiste.client.renderer.tesr.TileEntityExtractorRenderer;
import fr.paladium.palamod.modules.alchimiste.proxies.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class TileEntityExtractorInventoryRenderer implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    GL11.glPushMatrix();
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    GL11.glTranslatef(0.2F, -2.35F, 0.0F);
    GL11.glScaled(2.0D, 2.0D, 2.0D);
    Minecraft.func_71410_x().func_110434_K().func_110577_a(TileEntityExtractorRenderer.on);
    TileEntityExtractorRenderer.model.renderAll();
    GL11.glPopMatrix();
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    return false;
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }
  
  public int getRenderId() {
    return ClientProxy.extractorRenderId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\renderer\inventory\TileEntityExtractorInventoryRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */