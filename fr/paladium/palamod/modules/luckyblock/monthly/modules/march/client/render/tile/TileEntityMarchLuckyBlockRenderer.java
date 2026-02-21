package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.tile;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.block.ModelMarchLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityMarchLuckyBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityMarchLuckyBlockRenderer extends TileEntitySpecialRenderer {
  public static ModelMarchLuckyBlock getModel() {
    return model;
  }
  
  private static final ModelMarchLuckyBlock model = new ModelMarchLuckyBlock();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/march_lucky_block.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityMarchLuckyBlock)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityMarchLuckyBlock tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\render\tile\TileEntityMarchLuckyBlockRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */