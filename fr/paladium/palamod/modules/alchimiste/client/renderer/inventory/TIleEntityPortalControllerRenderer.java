package fr.paladium.palamod.modules.alchimiste.client.renderer.inventory;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.alchimiste.client.model.ModelBlock;
import fr.paladium.palamod.modules.alchimiste.proxies.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class TIleEntityPortalControllerRenderer implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block b, int metadata, int modelId, RenderBlocks renderer) {
    GL11.glPushMatrix();
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glTranslated(0.0D, -1.5D, 0.0D);
    ResourceLocation tex = new ResourceLocation("textures/blocks/stone.png");
    if (b == BlocksRegister.AMETHYSTE_PORTAL_BLOCK) {
      tex = new ResourceLocation("palamod", "textures/blocks/alchimiste/amethyste_portal_block.png");
    } else if (b == BlocksRegister.TITANE_PORTAL_BLOCK) {
      tex = new ResourceLocation("palamod", "textures/blocks/alchimiste/titane_portal_block.png");
    } else if (b == BlocksRegister.PALADIUM_PORTAL_BLOCK) {
      tex = new ResourceLocation("palamod", "textures/blocks/alchimiste/paladium_portal_block.png");
    } else if (b == BlocksRegister.ENDIUM_PORTAL_BLOCK) {
      tex = new ResourceLocation("palamod", "textures/blocks/alchimiste/endium_portal_block.png");
    } 
    Minecraft.func_71410_x().func_110434_K().func_110577_a(tex);
    (new ModelBlock()).renderAll();
    GL11.glPopMatrix();
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    return false;
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }
  
  public int getRenderId() {
    return ClientProxy.portalController;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\renderer\inventory\TIleEntityPortalControllerRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */