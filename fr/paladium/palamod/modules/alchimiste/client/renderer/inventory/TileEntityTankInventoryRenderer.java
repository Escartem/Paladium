package fr.paladium.palamod.modules.alchimiste.client.renderer.inventory;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.paladium.palamod.modules.alchimiste.client.renderer.tesr.TileEntityTankRenderer;
import fr.paladium.palamod.modules.alchimiste.common.blocks.BlockTank;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.EnumTank;
import fr.paladium.palamod.modules.alchimiste.proxies.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class TileEntityTankInventoryRenderer implements ISimpleBlockRenderingHandler {
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    GL11.glPushMatrix();
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glTranslated(0.0D, -1.5D, 0.0D);
    BlockTank tank = (BlockTank)block;
    ResourceLocation toRender = new ResourceLocation("textures/blocks/stone.png");
    switch (tank.getTank()) {
      case AMETHYSTE:
        toRender = new ResourceLocation("palamod", "textures/blocks/tanks/amethyste_tank.png");
        break;
      case PALADIUM:
        toRender = new ResourceLocation("palamod", "textures/blocks/tanks/paladium_tank.png");
        break;
      case TITANE:
        toRender = new ResourceLocation("palamod", "textures/blocks/tanks/titane_tank.png");
        break;
      case GOLD:
        toRender = new ResourceLocation("palamod", "textures/blocks/tanks/gold_tank.png");
        break;
    } 
    Minecraft.func_71410_x().func_110434_K().func_110577_a(toRender);
    TileEntityTankRenderer.blockModel.renderAll();
    GL11.glPopMatrix();
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    return false;
  }
  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }
  
  public int getRenderId() {
    return ClientProxy.tanksRenderId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\renderer\inventory\TileEntityTankInventoryRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */