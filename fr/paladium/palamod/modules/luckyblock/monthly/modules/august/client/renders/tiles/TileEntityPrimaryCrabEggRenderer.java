package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.tiles;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.models.blocks.ModelPrimaryCrabEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tiles.TileEntityPrimaryCrabEgg;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityPrimaryCrabEggRenderer extends TileEntitySpecialRenderer {
  public static ModelPrimaryCrabEgg getModel() {
    return model;
  }
  
  private static final ModelPrimaryCrabEgg model = new ModelPrimaryCrabEgg();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/primary_crab_egg.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityPrimaryCrabEgg)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityPrimaryCrabEgg tile, double x, double y, double z, float ticks) {
    float scale = 1.6F;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.55D, y + 2.4D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glScalef(scale, scale, scale);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\renders\tiles\TileEntityPrimaryCrabEggRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */