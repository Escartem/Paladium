package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.modules.luckyblock.blocks.models.ModelBallBasket;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityBallBasketRenderer extends TileEntitySpecialRenderer {
  private static ModelBallBasket model = new ModelBallBasket();
  
  private static ResourceLocation texture;
  
  public static ModelBallBasket getModel() {
    return model;
  }
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public TileEntityBallBasketRenderer() {
    texture = new ResourceLocation("palamod", "textures/blocks/ball_basket/ball_basket.png");
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityBallBasket)te, x, y, z, ticks);
  }
  
  public void renderTileEntity() {
    func_147497_a(TileEntityRendererDispatcher.field_147556_a);
  }
  
  private void renderBlockTileEntityAt(TileEntityBallBasket tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 0.8D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    int meta = tile.func_145831_w().func_72805_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    func_147499_a(texture);
    GL11.glScaled(0.5D, 0.5D, 0.5D);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityBallBasketRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */