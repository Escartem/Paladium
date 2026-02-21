package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.renders.tiles;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.blocks.ModelPrimarySlayerEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityPrimarySlayerEgg;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityPrimarySlayerEggRenderer extends TileEntitySpecialRenderer {
  public static ModelPrimarySlayerEgg getModel() {
    return model;
  }
  
  private static final ModelPrimarySlayerEgg model = new ModelPrimarySlayerEgg();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/primary_slayer_egg.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityPrimarySlayerEgg)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityPrimarySlayerEgg tile, double x, double y, double z, float ticks) {
    float scale = 1.8F;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 2.7D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glScalef(scale, scale, scale);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\client\renders\tiles\TileEntityPrimarySlayerEggRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */