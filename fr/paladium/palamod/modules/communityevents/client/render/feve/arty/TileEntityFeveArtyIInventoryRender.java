package fr.paladium.palamod.modules.communityevents.client.render.feve.arty;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.communityevents.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class TileEntityFeveArtyIInventoryRender implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    if (block == BlocksRegister.FEVE_ARTY) {
      GL11.glPushMatrix();
      GL11.glRotatef(180.0F, 180.0F, 0.0F, 1.0F);
      GL11.glTranslatef(0.0F, -1.0F, 0.0F);
      Minecraft.func_71410_x().func_110434_K().func_110577_a(TileEntityFeveArtyRender.texture);
      TileEntityFeveArtyRender.model.renderAll();
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
    return ClientProxy.renderFeveArty;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\client\render\feve\arty\TileEntityFeveArtyIInventoryRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */