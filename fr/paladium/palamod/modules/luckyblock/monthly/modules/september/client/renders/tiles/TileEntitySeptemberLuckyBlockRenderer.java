package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.tiles;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.blocks.ModelSeptemberLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.tiles.TileEntitySeptemberLuckyBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntitySeptemberLuckyBlockRenderer extends TileEntitySpecialRenderer {
  public static ModelSeptemberLuckyBlock getModel() {
    return model;
  }
  
  private static final ModelSeptemberLuckyBlock model = new ModelSeptemberLuckyBlock();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/september_lucky_block.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntitySeptemberLuckyBlock)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntitySeptemberLuckyBlock tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\renders\tiles\TileEntitySeptemberLuckyBlockRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */