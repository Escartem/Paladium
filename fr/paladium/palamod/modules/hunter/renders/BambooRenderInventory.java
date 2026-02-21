package fr.paladium.palamod.modules.hunter.renders;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.hunter.proxies.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class BambooRenderInventory implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
    if (block == BlocksRegister.BAMBOO) {
      GL11.glPushMatrix();
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(0.0F, -1.0F, 0.0F);
      if (meta == 0) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("palamod", "textures/blocks/bamboo/bambou_stage_1_0.png"));
      } else if (meta == 1) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("palamod", "textures/blocks/bamboo/bambou_stage_0.png"));
      } else if (meta == 2) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("palamod", "textures/blocks/bamboo/bambou_stage_1_1.png"));
      } else if (meta == 3) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("palamod", "textures/blocks/bamboo/bambou_stage_2_0.png"));
      } else if (meta == 4) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("palamod", "textures/blocks/bamboo/bambou_stage_2_1.png"));
      } else if (meta == 5) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("palamod", "textures/blocks/bamboo/bambou_stage_2_2.png"));
      } 
      TileEntityBambooRenderer.getModel().renderAll();
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
    return ClientProxy.renderBamboo;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\renders\BambooRenderInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */