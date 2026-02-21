package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.tile;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.block.MonsterBlackHoleModel;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityMonsterBlackHole;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityMonsterBlackHoleRender extends TileEntitySpecialRenderer {
  public static MonsterBlackHoleModel getModel() {
    return model;
  }
  
  private static final MonsterBlackHoleModel model = new MonsterBlackHoleModel();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/models/black_hole_core.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityMonsterBlackHole)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityMonsterBlackHole tile, double x, double y, double z, float ticks) {
    float scale = 0.9F;
    int meta = tile.func_145831_w().func_72805_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    double centerX = tile.field_145851_c + 0.5D;
    double centerY = tile.field_145848_d + 0.5D;
    double centerZ = tile.field_145849_e + 0.5D;
    int numberOfParticles = 10;
    for (int i = 0; i < numberOfParticles; i++) {
      double angle = 6.283185307179586D * i / numberOfParticles;
      double xOffset = Math.cos(angle) * 0.3D;
      double zOffset = Math.sin(angle) * 0.3D;
      double yOffset;
      for (yOffset = -0.2D; yOffset <= 0.2D; yOffset += 0.1D)
        tile.func_145831_w().func_72869_a("reddust", centerX + xOffset, centerY + yOffset, centerZ + zOffset, -1.0D, -1.0D, -1.0D); 
    } 
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\render\tile\TileEntityMonsterBlackHoleRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */