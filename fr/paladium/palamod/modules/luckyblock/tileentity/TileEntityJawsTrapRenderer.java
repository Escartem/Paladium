package fr.paladium.palamod.modules.luckyblock.tileentity;

import fr.paladium.palamod.modules.luckyblock.blocks.models.ModelJawsTrap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityJawsTrapRenderer extends TileEntitySpecialRenderer {
  private static ModelJawsTrap model = new ModelJawsTrap();
  
  public static ModelJawsTrap getModel() {
    return model;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/jawstrap/jawstrap.png");
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityJawsTrap)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityJawsTrap tile, double x, double y, double z, float ticks) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(getTexture());
    model.renderAll(tile);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\tileentity\TileEntityJawsTrapRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */