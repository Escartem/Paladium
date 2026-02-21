package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.tiles;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.blocks.ModelPrimaryTaupikoEgg;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.tiles.TileEntityPrimaryTaupikoEgg;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityPrimaryTaupikoEggRenderer extends TileEntitySpecialRenderer {
  public static ModelPrimaryTaupikoEgg getModel() {
    return model;
  }
  
  private static final ModelPrimaryTaupikoEgg model = new ModelPrimaryTaupikoEgg();
  
  public static ResourceLocation getTexture() {
    return texture;
  }
  
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/blocks/primary_taupiko_egg.png");
  
  public void func_147500_a(TileEntity te, double x, double y, double z, float ticks) {
    renderBlockTileEntityAt((TileEntityPrimaryTaupikoEgg)te, x, y, z, ticks);
  }
  
  private void renderBlockTileEntityAt(TileEntityPrimaryTaupikoEgg tile, double x, double y, double z, float ticks) {
    float scale = 1.3F;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.9D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    GL11.glScalef(scale, scale, scale);
    func_147499_a(texture);
    model.renderAll();
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\renders\tiles\TileEntityPrimaryTaupikoEggRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */