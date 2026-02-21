package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.tile;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.block.AlienPlushModel;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityAlienPlush;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityAlienPlushRender extends TileEntitySpecialRenderer {
  public static AlienPlushModel getModel() {
    return model;
  }
  
  private static final AlienPlushModel model = new AlienPlushModel();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/alien_plush.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityAlienPlush)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityAlienPlush tile, double x, double y, double z, float ticks) {
    float scale = 0.9F;
    int meta = tile.func_145831_w().func_72805_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    switch (meta) {
      case 3:
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 1.35D, z + 0.5D);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(scale, scale, scale);
        func_147499_a(texture);
        model.renderAll();
        GL11.glPopMatrix();
        return;
      case 4:
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 1.35D, z + 0.5D);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(scale, scale, scale);
        func_147499_a(texture);
        model.renderAll();
        GL11.glPopMatrix();
        return;
      case 5:
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 1.35D, z + 0.5D);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(scale, scale, scale);
        func_147499_a(texture);
        model.renderAll();
        GL11.glPopMatrix();
        return;
    } 
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.35D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glScalef(scale, scale, scale);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\render\tile\TileEntityAlienPlushRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */