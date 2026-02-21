package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.tiles;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.models.blocks.ModelDolphin;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityDolphinPlush;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityDolphinPlushRenderer extends TileEntitySpecialRenderer {
  public static ModelDolphin getModel() {
    return model;
  }
  
  private static final ModelDolphin model = new ModelDolphin();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/dolphin_plush.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityDolphinPlush)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityDolphinPlush tile, double x, double y, double z, float ticks) {
    int metadata = tile.field_145847_g;
    float scale = 0.75F;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.2D, z + 0.5D);
    GL11.glRotatef(180.0F, getRotation(metadata), 0.0F, 1.0F);
    GL11.glScalef(scale, scale, scale);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
  
  private float getRotation(int metadata) {
    switch (metadata) {
      case 2:
        return 0.0F;
      case 3:
        return 180.0F;
      case 4:
        return 1.0F;
      case 5:
        return -1.0F;
    } 
    return 0.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\renders\tiles\TileEntityDolphinPlushRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */