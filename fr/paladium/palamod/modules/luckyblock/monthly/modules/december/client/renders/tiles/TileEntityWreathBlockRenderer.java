package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.renders.tiles;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.models.blocks.ModelWreath;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.december.common.tiles.TileEntityChristmasWreath;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityWreathBlockRenderer extends TileEntitySpecialRenderer {
  public static ModelWreath getModel() {
    return model;
  }
  
  private static final ModelWreath model = new ModelWreath();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/christmas_wreath.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityChristmasWreath)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityChristmasWreath tile, double x, double y, double z, float ticks) {
    int metadata = tile.field_145847_g;
    float scale = 0.9F;
    switch (metadata) {
      case 3:
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 1.35D, z + 0.05D);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glScalef(scale, scale, scale);
        func_147499_a(texture);
        model.renderAll();
        GL11.glPopMatrix();
        return;
      case 4:
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.95D, y + 1.35D, z + 0.5D);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(scale, scale, scale);
        func_147499_a(texture);
        model.renderAll();
        GL11.glPopMatrix();
        return;
      case 5:
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.05D, y + 1.35D, z + 0.5D);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(scale, scale, scale);
        func_147499_a(texture);
        model.renderAll();
        GL11.glPopMatrix();
        return;
    } 
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.35D, z + 0.95D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    GL11.glScalef(scale, scale, scale);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\client\renders\tiles\TileEntityWreathBlockRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */