package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.renders.tiles;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.models.blocks.ModelFirePlace;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles.TileEntityFirePlace;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityFirePlaceBlockRenderer extends TileEntitySpecialRenderer {
  public static ModelFirePlace getModel() {
    return model;
  }
  
  private static final ModelFirePlace model = new ModelFirePlace();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/fire_place.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityFirePlace)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityFirePlace tile, double x, double y, double z, float ticks) {
    int metadata = tile.field_145847_g;
    float scale = 0.8F;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.2D, z + 0.5D);
    GL11.glRotatef(180.0F, MonthlyUtils.getRotation(metadata), 0.0F, 1.0F);
    GL11.glScalef(scale, scale, scale);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\client\renders\tiles\TileEntityFirePlaceBlockRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */